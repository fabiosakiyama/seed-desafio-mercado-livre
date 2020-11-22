package br.com.mercadolivre.domain.usuario.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.mercadolivre.domain.usuario.model.Usuario;
import lombok.Data;

@Data
public class NovoUsuarioRequest {
	
	@Email
	@NotBlank
	private String login;
	
	@NotBlank
	@Size(min = 6)
	private String password;
	
	public Usuario toModel(PasswordEncoder passwordEncoder) {
		return new Usuario(this.login, passwordEncoder.encode(this.password));
	}
}