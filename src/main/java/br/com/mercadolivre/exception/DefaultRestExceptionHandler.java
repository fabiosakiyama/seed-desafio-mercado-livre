package br.com.mercadolivre.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		Locale locale = LocaleContextHolder.getLocale();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + extracted(error) + ": " + messageSource.getMessage(error, locale));
		}
		DefaultApiError apiError = new DefaultApiError(errors);
		return handleExceptionInternal(ex, apiError, headers, status, request);
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
			WebRequest request) {
		String error = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
		DefaultApiError apiError = new DefaultApiError(error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}


	private Object extracted(FieldError error) {
		if(error.getRejectedValue() != null) {
			return " '" + error.getRejectedValue() + "' ";
		}
		return "";
	}
}
