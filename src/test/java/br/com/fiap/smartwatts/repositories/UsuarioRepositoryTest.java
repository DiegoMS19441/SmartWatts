package br.com.fiap.smartwatts.repositories;

import br.com.fiap.smartwatts.dto.UserForm;
import br.com.fiap.smartwatts.model.Role;
import br.com.fiap.smartwatts.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UsuarioRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void findByUsername() {
        UserForm userForm = new UserForm();
        userForm.setUsername("teste_user");
        userForm.setPassword("senha123");
        userForm.setRoles(List.of("USER", "ADMIN")); // Lista de roles como strings

        Usuario usuario = criarUsuario(userForm);

        Usuario encontrado = entityManager
                .createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                .setParameter("username", "teste_user")
                .getSingleResult();

        // Validação do resultado
        assertEquals(usuario.getUsername(), encontrado.getUsername());
        assertEquals(usuario.getPassword(), encontrado.getPassword());
        assertEquals(usuario.getRoles(), encontrado.getRoles());
    }

    private Usuario criarUsuario(UserForm data) {
        // Converter roles de strings para objetos Role e persistir
        Set<Role> roles = data.getRoles().stream()
                .map(roleName -> criarRole(roleName, "Label para " + roleName))
                .collect(Collectors.toSet());

        // Criar e persistir o usuário
        Usuario novoUsuario = new Usuario(
                data.getUsername(),
                data.getPassword(),
                roles
        );
        entityManager.persist(novoUsuario);
        return novoUsuario;
    }

    private Role criarRole(String name, String label) {
        // Criar e persistir uma role
        Role role = new Role();
        role.setName(name);
        role.setLabel(label);
        entityManager.persist(role); // Persistir no banco
        return role;
    }


    @Test
    void findByUsername_whenUserNotFound_thenThrowException() {
        // Tentativa de recuperar um usuário que não existe
        String nonExistentUsername = "user_not_found";

        assertThrows(NoResultException.class, () -> {
            entityManager
                    .createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                    .setParameter("username", nonExistentUsername)
                    .getSingleResult();
        });
    }
}