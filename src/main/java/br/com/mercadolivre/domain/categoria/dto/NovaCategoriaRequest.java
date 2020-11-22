package br.com.mercadolivre.domain.categoria.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import br.com.mercadolivre.domain.categoria.model.Categoria;
import br.com.mercadolivre.util.dto.validators.ExistsId;
import br.com.mercadolivre.util.dto.validators.UniqueValue;
import lombok.Data;

@Data
public class NovaCategoriaRequest {
	
	@NotBlank
	@UniqueValue(tableName = "Categoria", columnName = "nome")
	private String nome;
	
	@ExistsId(klass = Categoria.class, isOptional = true)
	private Long categoriaMaeId;

	//1
	public Categoria toModel(EntityManager entityManager) {
		Categoria categoria = new Categoria(nome);
		// 1
		if(categoriaMaeId != null) {
			Categoria categoriaMae = entityManager.find(Categoria.class, this.categoriaMaeId);
			Assert.notNull(categoriaMae, "exists.id");
			categoria.setCategoriaMae(categoriaMae);
		}
		return categoria;
	}
}
