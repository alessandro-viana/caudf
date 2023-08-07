package br.gov.caudf.sistemas.dto;

import java.io.Serializable;
import java.time.Instant;

import br.gov.caudf.sistemas.entities.Complaint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class ComplaintDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "Campo Obrigatório")
	private String number;
	@PastOrPresent(message = "A data não pode ser futura")
	private Instant date;

	public ComplaintDTO() {
	}
	
	public ComplaintDTO(Long id, String number, Instant date) {
		super();
		this.id = id;
		this.number = number;
		this.date = date;
	}
	
	public ComplaintDTO(Complaint entity) {
		id = entity.getId();
		number = entity.getNumber();
		date = entity.getDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}
	
	

}
