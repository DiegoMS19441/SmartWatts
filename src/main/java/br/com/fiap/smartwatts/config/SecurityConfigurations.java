package br.com.fiap.smartwatts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**", "/login", "/webjars/**").permitAll()
                        .requestMatchers("/admin/**", "/register").hasRole("ADMIN") // Restringe o acesso para ADMIN
                        .requestMatchers("/user/**").hasRole("USER") // Restringe o acesso para USER

                        //Morador
                        .requestMatchers("/morador/cadastrar").hasRole("ADMIN")
                        .requestMatchers("/morador/editar").hasRole("ADMIN")
                        .requestMatchers("/morador/remover").hasRole("ADMIN")
                        //Residência
                        .requestMatchers("/residencia/cadastrar").hasRole("ADMIN")
                        .requestMatchers("/residencia/editar").hasRole("ADMIN")
                        .requestMatchers("/residencia/remover").hasRole("ADMIN")
                        //Usuário
                        .requestMatchers("/usuario/cadastrar").hasRole("ADMIN")
                        .requestMatchers("/usuario/remover").hasRole("ADMIN")
                        //fatura
                        .requestMatchers("/fatura/cadastrar").hasRole("ADMIN")
                        .requestMatchers("/fatura/editar").hasRole("ADMIN")
                        .requestMatchers("/fatura/remover").hasRole("ADMIN")


                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página de login personalizada
                        .defaultSuccessUrl("/home") // Página de redirecionamento após sucesso no login
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // Redirecionamento após logout
                );


        return http.build();
    }
}
