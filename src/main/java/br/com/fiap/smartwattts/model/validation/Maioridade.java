package br.com.fiap.smartwattts.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MaioridadeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Maioridade {

    String message() default "O usuário deve ter pelo menos 18 anos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
