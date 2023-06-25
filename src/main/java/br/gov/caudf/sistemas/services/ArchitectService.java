package br.gov.caudf.sistemas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.caudf.sistemas.dto.ArchitectDTO;
import br.gov.caudf.sistemas.dto.CategoryActivitiesDTO;
import br.gov.caudf.sistemas.entities.Architect;
import br.gov.caudf.sistemas.entities.CategoryActivities;
import br.gov.caudf.sistemas.repositories.ArchitectRepository;
import br.gov.caudf.sistemas.repositories.CategoryActivitiesRepository;
import br.gov.caudf.sistemas.services.exceptions.DatabaseException;
import br.gov.caudf.sistemas.services.exceptions.ResourceNotFoundException;

@Service
public class ArchitectService {

	@Autowired
	private ArchitectRepository repository;
	@Autowired
	private CategoryActivitiesRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public Page<ArchitectDTO> findAllPaged(PageRequest pageRequest) {
		Page<Architect> list = repository.findAll(pageRequest);
		return list.map(x -> new ArchitectDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ArchitectDTO findById(Long id) {
		Optional<Architect> obj = repository.findById(id);
		Architect entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não existe no banco de dados."));
		return new ArchitectDTO(entity, entity.getCategories());
	}
	
	@Transactional
	public ArchitectDTO insert(ArchitectDTO dto) {
		Architect entity = new Architect();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ArchitectDTO(entity);
	}
		
	@Transactional
	public ArchitectDTO update(Long id, ArchitectDTO dto) {
		try {
		Architect entity = repository.getOne(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ArchitectDTO(entity);
		}
		catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Esse ID não existe no banco de dados " + id);
		}
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
	
	private void copyDtoToEntity(ArchitectDTO dto, Architect entity) {
		entity.setName(dto.getName());
		entity.setCau(dto.getCau());
		entity.setDescription(dto.getDescription());
		entity.setImgUrl(dto.getImgUrl());
		entity.setDate(dto.getDate());
		
		entity.getCategories().clear();
		
		for (CategoryActivitiesDTO catDto : dto.getCategories()) {
			CategoryActivities category = categoryRepository.getOne(catDto.getId());
			entity.getCategories().add(category);
		}
	}
}
