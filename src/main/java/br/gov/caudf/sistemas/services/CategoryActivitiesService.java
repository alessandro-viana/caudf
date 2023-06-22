package br.gov.caudf.sistemas.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.caudf.sistemas.dto.CategoryActivitiesDTO;
import br.gov.caudf.sistemas.entities.CategoryActivities;
import br.gov.caudf.sistemas.repositories.CategoryActivitiesRepository;
import br.gov.caudf.sistemas.services.exceptions.DatabaseException;
import br.gov.caudf.sistemas.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryActivitiesService {

	@Autowired
	private CategoryActivitiesRepository repository;
	
	@Transactional(readOnly = true)
	public List<CategoryActivitiesDTO> findAll() {
		List<CategoryActivities> list = repository.findAll();
		return list.stream().map(x -> new CategoryActivitiesDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CategoryActivitiesDTO findById(Long id) {
		Optional<CategoryActivities> obj = repository.findById(id);
		CategoryActivities entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não existe no banco de dados."));
		return new CategoryActivitiesDTO(entity);
	}
	
	@Transactional
	public CategoryActivitiesDTO insert(CategoryActivitiesDTO dto) {
		CategoryActivities entity = new CategoryActivities();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryActivitiesDTO(entity);
	}
	
	@Transactional
	public CategoryActivitiesDTO update(Long id, CategoryActivitiesDTO dto) {
		try {
		CategoryActivities entity = repository.getOne(id);
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryActivitiesDTO(entity);
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
}
