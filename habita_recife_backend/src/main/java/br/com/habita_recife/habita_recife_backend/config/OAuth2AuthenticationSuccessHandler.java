package br.com.habita_recife.habita_recife_backend.config;


import br.com.habita_recife.habita_recife_backend.domain.model.Role;
import br.com.habita_recife.habita_recife_backend.domain.enums.RoleName;
import br.com.habita_recife.habita_recife_backend.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;
    private final RoleRepository roleRepository;


    @Autowired
    public OAuth2AuthenticationSuccessHandler(JwtTokenService jwtTokenService, UserDetailsService userDetailsService, RoleRepository roleRepository) {
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
        this.roleRepository = roleRepository;
        setRedirectStrategy(super.getRedirectStrategy());
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);


        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }


        clearAuthenticationAttributes(request);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }


    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        java.util.Map<String, Object> attributes = oAuth2User.getAttributes();


        String email = (String) attributes.get("email");
        if (email == null || email.isEmpty()) {

            return "/oauth2/error?error=email_not_found";
        }


        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();


        Set<Role> roleSet = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(roleStr -> {
                    String normalized = roleStr.replace("ROLE_", "").toLowerCase(); // Segurança extra
                    RoleName roleName = RoleName.fromString(normalized);
                    return roleRepository.findByRole(roleName)
                            .orElseThrow(() -> new RuntimeException("Role não encontrada: " + roleName));
                })
                .collect(Collectors.toSet());


        String token = jwtTokenService.generateToken(userDetails.getUsername(), userDetails.getUsername(), roleSet);


        return UriComponentsBuilder.fromUriString("/oauth2/redirect")
                .queryParam("token", token)
                .build().toUriString();


    }
}

