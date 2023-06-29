package br.gov.caudf.sistemas.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.gov.caudf.sistemas.entities.Architect;
import br.gov.caudf.sistemas.entities.CategoryActivities;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class ArchitectDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Size(min =5, max = 90, message = "Deve ter entre 5 e 90 caracteres")
	@NotBlank(message = "Campo nome obrigatório")
	private String name;
	
	@NotBlank(message = "Número do CAU obrigatório")
	private String cau;
	private String description;
	private String imgUrl;
	
	@PastOrPresent(message = "A data não pode ser futura")
	private Instant date;
	
	private List<CategoryActivitiesDTO> categories = new ArrayList<>();
	
	public ArchitectDTO() {
	}

	public ArchitectDTO(Long id, String name, String cau, String description, String imgUrl, Instant date) {
		super();
		this.id = id;
		this.name = name;
		this.cau = cau;
		this.description = description;
		this.imgUrl = imgUrl;
		this.date = date;
	}
	
	public ArchitectDTO(Architect entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.cau = entity.getCau();
		this.description = entity.getDescription();
		this.imgUrl = entity.getImgUrl();
		this.date = entity.getDate();
	}
	
	public ArchitectDTO(Architect entity, Set<CategoryActivities> categories) {
		this(entity);
		categories.forEach(cat -> this.categories.add(new CategoryActivitiesDTO(cat)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCau() {
		return cau;
	}

	public void setCau(String cau) {
		this.cau = cau;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public List<CategoryActivitiesDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryActivitiesDTO> categories) {
		this.categories = categories;
	}
	
	
	

}