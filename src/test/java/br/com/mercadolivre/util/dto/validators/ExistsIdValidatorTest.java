package br.com.mercadolivre.util.dto.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.Annotation;

import javax.persistence.EntityManager;
import javax.validation.Payload;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class ExistsIdValidatorTest {
	
	@DisplayName("Validando id existente na base, campo obrigatório, parametro válido, não gera erro")
	@ParameterizedTest
    @ValueSource(longs =  {1})
	public void test1(Long id) {
		ExistsIdValidator validator = prepareValidator(new Object(), false, id);
		assertTrue(validator.isValid(id, null));
	}
	
	@DisplayName("Validando id existente na base, campo obrigatório, parametro nulo, gera erro")
	@Test
	public void test2() {
		ExistsIdValidator validator = prepareValidator(new Object(), false, null);
		assertFalse(validator.isValid(null, null));
	}
	
	@DisplayName("Validando id existente na base, campo opcional, parametro válido, não gera erro")
	@ParameterizedTest
    @ValueSource(longs =  {1})
	public void test3(Long id) {
		ExistsIdValidator validator = prepareValidator(new Object(), true, id);
		assertTrue(validator.isValid(id, null));
	}
	
	@DisplayName("Validando id existente na base, campo opcional, parametro nulo, não gera erro")
	@Test
	public void test4() {
		ExistsIdValidator validator = prepareValidator(new Object(), true, null);
		assertTrue(validator.isValid(null, null));
	}
	
	@DisplayName("Validando id não existente na base, campo obrigatório, parametro válido, gera erro")
	@ParameterizedTest
    @ValueSource(longs =  {1})
	public void test5(Long id) {
		ExistsIdValidator validator = prepareValidator(null, false, id);
		assertFalse(validator.isValid(id, null));
	}
	
	@DisplayName("Validando id não existente na base, campo obrigatório, parametro nulo, gera erro")
	@Test
	public void test6() {
		ExistsIdValidator validator = prepareValidator(null, false, null);
		assertFalse(validator.isValid(null, null));
	}
	
	@DisplayName("Validando id não existente na base, campo opcional, parametro válido, não gera erro")
	@ParameterizedTest
    @ValueSource(longs =  {1})
	public void test7(Long id) {
		ExistsIdValidator validator = prepareValidator(null, true, id);
		assertFalse(validator.isValid(id, null));
	}
	
	@DisplayName("Validando id não existente na base, campo opcional, parametro nulo, não gera erro")
	@Test
	public void test8() {
		ExistsIdValidator validator = prepareValidator(null, true, null);
		assertTrue(validator.isValid(null, null));
	}

	private ExistsIdValidator prepareValidator(Object databaseObject, boolean isOptional, Long id) {
		EntityManager manager = Mockito.mock(EntityManager.class, Mockito.RETURNS_DEEP_STUBS);
		Mockito.when(manager.find(Object.class, id)).thenReturn(databaseObject);
		ExistsIdValidator validator = new ExistsIdValidator(manager);
		validator.initialize(extracted(Object.class, isOptional));
		return validator;
	}
	
	private ExistsId extracted(Class<?> klass, boolean isOptional) {
		return new ExistsId() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return null;
			}
			
			@Override
			public Class<? extends Payload>[] payload() {
				return null;
			}
			
			@Override
			public String message() {
				return null;
			}
			
			@Override
			public Class<?>[] groups() {
				return null;
			}

			@Override
			public Class<?> klass() {
				return klass;
			}

			@Override
			public boolean isOptional() {
				return isOptional;
			}
		};
	}
}
