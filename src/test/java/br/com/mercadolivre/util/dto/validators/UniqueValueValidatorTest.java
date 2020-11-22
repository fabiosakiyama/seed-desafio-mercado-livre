package br.com.mercadolivre.util.dto.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.validation.Payload;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class UniqueValueValidatorTest {
	
	@DisplayName("Non existent values")
	@ParameterizedTest(name = "should {arguments} not exist already ")
    @ValueSource(strings = {"nonExistentValue"})
	public void test1(String login) {
		EntityManager manager = Mockito.mock(EntityManager.class, Mockito.RETURNS_DEEP_STUBS);
		Mockito.when(manager.createQuery("SELECT t FROM Usuario t where t.login = :value").setParameter("value", login).getResultStream())
			.thenReturn(Stream.empty());
		UniqueValueValidator uniqueValueValidator = new UniqueValueValidator(manager);
		uniqueValueValidator.initialize(extracted("Usuario", "login"));
		assertTrue(uniqueValueValidator.isValid(login, null));
	}
	
	@DisplayName("Existent values")
	@ParameterizedTest(name = "should {arguments} exist already ")
    @ValueSource(strings = {"existentValue"})
	public void test2(String login) {
		EntityManager manager = Mockito.mock(EntityManager.class, Mockito.RETURNS_DEEP_STUBS);
		Mockito.when(manager.createQuery("SELECT t FROM Usuario t where t.login = :value").setParameter("value", login).getResultStream())
			.thenReturn(Stream.of(new Object()));
		UniqueValueValidator uniqueValueValidator = new UniqueValueValidator(manager);
		uniqueValueValidator.initialize(extracted("Usuario", "login"));
		assertFalse(uniqueValueValidator.isValid(login, null));
	}
	
	private UniqueValue extracted(String tableName, String columnName) {
		return new UniqueValue() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return null;
			}
			
			@Override
			public String tableName() {
				return tableName;
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
			public String columnName() {
				return columnName;
			}
		};
	}
}
