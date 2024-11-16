package br.com.fiap.smartwatts.model;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Validator validator;
    private Usuario usuario;

    @Mock
    private Role role;

    @BeforeEach
    public void setUp() {
        // Inicializa o Mockito
        MockitoAnnotations.openMocks(this);

        // Configura o validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        // Configura a instância de Role para os testes
        role = new Role();
        role.setName("USER");

        // Configura a instância de Usuario com um papel
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        usuario = new Usuario("usuarioTeste", "senha123", roles);
    }

    @Test
    public void testValidacaoUsuarioValido() {
        // Valida a instância do usuário
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        // Nenhuma violação deve ocorrer se o usuário for válido
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testValidacaoUsuarioNomeVazio() {
        // Define o nome de usuário como vazio
        usuario.setUsername("");

        // Valida o usuário
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        // Deve haver uma violação para o campo 'username'
        assertFalse(violations.isEmpty());
        assertEquals("Por favor preencha o campo.", violations.iterator().next().getMessage());
    }
    @Test
    public void testGetterSetter() {
        // Testando o getter e setter de 'username'
        usuario.setUsername("novoUsuario");
        assertEquals("novoUsuario", usuario.getUsername());

        // Testando o getter e setter de 'password'
        usuario.setPassword("novaSenha");
        assertEquals("novaSenha", usuario.getPassword());
    }

    @Test
    public void testRelacionamentoRoles() {
        // Testando a adição de roles
        Set<Role> roles = usuario.getRoles();
        assertNotNull(roles);
        assertFalse(roles.isEmpty());
        assertEquals(1, roles.size());
        assertEquals("USER", roles.iterator().next().getName());
    }

}