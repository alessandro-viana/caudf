package br.gov.caudf.sistemas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.caudf.sistemas.dto.ComplaintDTO;
import br.gov.caudf.sistemas.entities.Complaint;
import br.gov.caudf.sistemas.repositories.ComplaintRepository;
import br.gov.caudf.sistemas.services.exceptions.DatabaseException;
import br.gov.caudf.sistemas.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


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

	@Transactional(readOnly = true)
	public ComplaintDTO findById(Long id) {
		Optional<Complaint> obj = repository.findById(id);
		Complaint entity = obj
				.orElseThrow(() -> new ResourceNotFoundException("Entidade não existe no banco de dados."));
		return new ComplaintDTO(entity);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Esse ID não existe no banco de dados delete " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de Integridado do Banco de Dados");
		}
	}

	@Transactional
	public ComplaintDTO update(Long id, ComplaintDTO dto) {
		try {
			Complaint entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ComplaintDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Esse ID não existe no banco de dados" + id);
		}
	}

	/*
	 * @Transactional(readOnly = true) public ComplaintDTO update(Long id,
	 * ComplaintDTO dto) { Optional<Complaint> obj =
	 * Optional.ofNullable(repository.getOne(id)); Complaint entity = obj
	 * .orElseThrow(() -> new
	 * ResourceNotFoundException("Entidade não existe no banco de dados"));
	 * copyDtoToEntity(dto, entity); entity = repository.save(entity); return new
	 * ComplaintDTO(entity); }
	 */

	private void copyDtoToEntity(ComplaintDTO dto, Complaint entity) {
		entity.setNumber(dto.getNumber());
		entity.setProtocol(dto.getProtocol());
		entity.setDate(dto.getDate());
	}

}
