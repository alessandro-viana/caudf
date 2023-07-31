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
	private String protocol;
	@PastOrPresent(message = "A data não pode ser futura")
	private Instant date;

	public ComplaintDTO() {
	}
	
	public ComplaintDTO(Long id, String number, String protocol, Instant date) {
		super();
		this.id = id;
		this.number = number;
		this.protocol = protocol;
		this.date = date;
	}
	
	public ComplaintDTO(Complaint entity) {
		id = entity.getId();
		number = entity.getNumber();
		protocol = entity.getProtocol();
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

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}
	
	

}
