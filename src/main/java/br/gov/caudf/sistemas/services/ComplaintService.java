package br.gov.caudf.sistemas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.caudf.sistemas.dto.ComplaintDTO;
import br.gov.caudf.sistemas.entities.Complaint;
import br.gov.caudf.sistemas.repositories.ComplaintRepository;

@Service
public class ComplaintService {
	
	@Autowired
	private ComplaintRepository repository;
	
	
	@Transactional
	public ComplaintDTO insert(ComplaintDTO dto) {
		Complaint entity = new Complaint();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ComplaintDTO(entity);
	}
	
	private void copyDtoToEntity(ComplaintDTO dto, Complaint entity) {
		entity.setNumber(dto.getNumber());
		entity.setProtocol(dto.getProtocol());
		entity.setDate(dto.getDate());
	}
	
	
}
