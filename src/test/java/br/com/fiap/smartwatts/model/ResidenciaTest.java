package br.com.fiap.smartwatts.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ResidenciaTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testResidenciaValida() {
        Residencia residencia = new Residencia();
        residencia.setMoradores(5);
        residencia.setAndares(2);

        // Testa se a residência é válida
        Set<ConstraintViolation<Residencia>> violations = validator.validate(residencia);

        // Não deve haver violações de validação
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testResidenciaInvalidaAndares() {
        Residencia residencia = new Residencia();
        residencia.setAndares(0);  // Valor inválido

        // Testa se a validação falha
        Set<ConstraintViolation<Residencia>> violations = validator.validate(residencia);

        assertFalse(violations.isEmpty());
        assertEquals("Entrada inválida", violations.iterator().next().getMessage());
    }

    @Test
    public void testResidenciaInvalidaCamposNull() {
        Residencia residencia = new Residencia();
        residencia.setMoradores(null);  // Campo obrigatório não preenchido
        residencia.setAndares(null);  // Campo obrigatório não preenchido

        // Testa se a validação falha
        Set<ConstraintViolation<Residencia>> violations = validator.validate(residencia);

        assertFalse(violations.isEmpty());
        assertEquals("Por favor preencha o campo.", violations.iterator().next().getMessage());
    }
}
