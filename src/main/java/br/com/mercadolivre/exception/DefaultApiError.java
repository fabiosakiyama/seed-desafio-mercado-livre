package br.com.mercadolivre.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class DefaultApiError {

	private List<String> errors;

	public DefaultApiError(List<String> errors) {
		this.errors = errors;
	}

	public DefaultApiError(String error) {
		errors = Arrays.asList(error);
	}
}
