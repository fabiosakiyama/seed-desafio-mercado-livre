package br.com.mercadolivre.domain.usuario.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email
	@NotBlank
	@Column(unique = true)
	private String login;
	
	@NotBlank
	@Size(min = 6)
	private String password;
	
	@NotNull
	@PastOrPresent
	private Instant instanteDoCadastro;
	
	@Deprecated
	public Usuario() {}

	public Usuario(String login, String password) {
		this.login = login;
		this.password = password;
		this.instanteDoCadastro = Instant.now();
	}
}
