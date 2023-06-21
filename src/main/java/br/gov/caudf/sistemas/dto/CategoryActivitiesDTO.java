package br.gov.caudf.sistemas.dto;

import java.io.Serializable;

import br.gov.caudf.sistemas.entities.CategoryActivities;

public class CategoryActivitiesDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	public CategoryActivitiesDTO() {

	}

	public CategoryActivitiesDTO(CategoryActivities entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public CategoryActivitiesDTO(Long id, String name) {
		this.id = id;
		this.name = name;
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

}
