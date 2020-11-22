package br.com.mercadolivre.domain.categoria.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.domain.categoria.dto.NovaCategoriaRequest;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	private EntityManager entityManager;
	
	public CategoriaController(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// 1
	@PostMapping
	@Transactional
	public ResponseEntity<Void> salvaCategoria(@Valid @RequestBody NovaCategoriaRequest request) {
		entityManager.persist(request.toModel(entityManager));
		return ResponseEntity.ok().build();
	}

}
