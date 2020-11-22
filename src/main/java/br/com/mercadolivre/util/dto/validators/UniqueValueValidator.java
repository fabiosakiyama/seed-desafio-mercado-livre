package br.com.mercadolivre.util.dto.validators;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

	private EntityManager manager;

	private String tableName;

	private String columnName;

	public UniqueValueValidator(EntityManager manager) {
		this.manager = manager;
	}

	public void initialize(UniqueValue constraint) {
		this.tableName = constraint.tableName();
		this.columnName = constraint.columnName();
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		return manager.createQuery("SELECT t FROM " + tableName + " t where t." + columnName + " = :value")
				.setParameter("value", value)
				.getResultStream().count() == 0;
	}
}
