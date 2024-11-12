package br.com.fiap.smartwattts.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class MaioridadeValidator implements ConstraintValidator<Maioridade, LocalDate> {

    @Override
    public boolean isValid(LocalDate dataNascimento, ConstraintValidatorContext context) {
        if (dataNascimento == null) {
            return false;
        }
        return Period.between(dataNascimento, LocalDate.now()).getYears() >= 18;
    }
}
