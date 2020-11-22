package br.com.mercadolivre.domain.categoria.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(unique = true)
	private String nome;
	
	@ManyToOne
	private Categoria categoriaMae;
	
	@OneToMany(mappedBy = "categoriaMae", orphanRemoval = true)
	private List<Categoria> categoriasFilhas = new ArrayList<>();
	
	@Deprecated
	public Categoria() {}

	public Categoria(@NotBlank String nome) {
		this.nome = nome;
	}

	public void setCategoriaMae(Categoria categoriaMae) {
		this.categoriaMae = categoriaMae;
	}
}
