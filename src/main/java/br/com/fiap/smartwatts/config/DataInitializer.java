package br.com.fiap.smartwatts.config;

import br.com.fiap.smartwatts.model.Role;
import br.com.fiap.smartwatts.model.Usuario;
import br.com.fiap.smartwatts.repositories.RoleRepository;
import br.com.fiap.smartwatts.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                adminRole.setLabel("Admin");
                return roleRepository.save(adminRole);
            });

            roleRepository.findByName("ROLE_USER").orElseGet(() -> {
                Role userRole = new Role();
                userRole.setName("ROLE_USER");
                userRole.setLabel("User");
                return roleRepository.save(userRole);
            });

            userRepository.findByUsername("admin").orElseGet(() -> {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRoles(Set.of(roleRepository.findByName("ROLE_ADMIN").get()));
                return userRepository.save(admin);
            });

            userRepository.findByUsername("user1").orElseGet(() -> {
                Usuario user = new Usuario();
                user.setUsername("user1");
                user.setPassword(passwordEncoder.encode("password"));
                user.setRoles(Set.of(roleRepository.findByName("ROLE_USER").get()));
                return userRepository.save(user);
            });
        };
    }
}