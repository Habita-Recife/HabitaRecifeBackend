package br.com.habita_recife.habita_recife_backend.features_authentication.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Habita Recife API")
                        .version("1.0")
                        .description("Documentação da API do sistema de gestão condominial."))
                .addTagsItem(new Tag().name("Usuários").description("Gerenciamento de usuários"))
                .addTagsItem(new Tag().name("CondominioController").description("Gericiamento do condominio cadastrado."))
                .addTagsItem(new Tag().name("SindicoController").description("Gericiamento do Sindico cadastrado."))
                .addTagsItem(new Tag().name("MoradorController").description("Gericiamento do Morador cadastrado."))
                .addTagsItem(new Tag().name("PorteiroController").description("Gericiamento do Porteiro cadastrado."))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Utilize o token JWT nas requisições")));
    }
}
