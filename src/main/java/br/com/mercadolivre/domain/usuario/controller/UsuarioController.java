package br.com.mercadolivre.domain.usuario.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.domain.usuario.dto.NovoUsuarioRequest;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private EntityManager entityManager;
	
	private PasswordEncoder passwordEncoder;

	public UsuarioController(EntityManager entityManager, PasswordEncoder passwordEncoder) {
		this.entityManager = entityManager;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Void> salvaUsuario(@Valid @RequestBody NovoUsuarioRequest request){
		entityManager.persist(request.toModel(passwordEncoder));
		return ResponseEntity.ok().build();
	}
}
