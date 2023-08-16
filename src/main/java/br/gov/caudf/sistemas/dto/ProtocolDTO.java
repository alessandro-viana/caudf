package br.gov.caudf.sistemas.dto;

import java.io.Serializable;
import java.time.Instant;

import br.gov.caudf.sistemas.entities.Protocol;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ProtocolDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String protocol;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant date;
	
	public ProtocolDTO() {
	}
	
	public ProtocolDTO(Long id, String protocol, Instant date) {
		super();
		this.id = id;
		this.protocol = protocol;
		this.date = date;
	}
	
	public ProtocolDTO(Protocol entity) {
		this.id = entity.getId();
		this.protocol = entity.getProtocol();
		this.date = entity.getDate();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
