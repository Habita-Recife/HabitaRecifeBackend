package br.com.habita_recife.habita_recife_backend.features_authentication.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenService jwtTokenService, UserDetailsService userDetailsService) {
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = jwtTokenService.resolveToken(request);

        if (token != null && jwtTokenService.validateToken(token)) {
            // Extrair o email do token
            String email = jwtTokenService.getEmailFromToken(token);

            // Carregar os detalhes do usuário usando o email extraído do token
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // Extrair as roles do token JWT (certifique-se de que o token contém a claim de roles)
            List<String> roles = jwtTokenService.getRolesFromToken(token);  // Certifique-se de que esse método retorna as roles corretamente

            // Criar o objeto de autenticação com o usuário e suas roles
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // Definir detalhes adicionais (como IP, etc.)
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            var authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            // Definir o contexto de segurança com o token de autenticação
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
