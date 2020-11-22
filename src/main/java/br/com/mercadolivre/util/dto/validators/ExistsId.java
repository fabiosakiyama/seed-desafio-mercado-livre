package br.com.mercadolivre.util.dto.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsIdValidator.class)
public @interface ExistsId {
	String message() default "{exists.id}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	Class<?> klass();
	
	boolean isOptional() default false;
}
