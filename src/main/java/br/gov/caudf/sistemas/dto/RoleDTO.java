package br.gov.caudf.sistemas.dto;

import java.io.Serializable;

import br.gov.caudf.sistemas.entities.Role;

public class RoleDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String authority;
	
	public RoleDTO() {
	}
	
	public RoleDTO(Long id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}
	
	public RoleDTO(Role role) {
		super();
		this.id = role.getId();
		this.authority = role.getAuthority();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
