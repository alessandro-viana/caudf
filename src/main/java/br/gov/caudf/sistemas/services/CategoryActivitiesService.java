package br.gov.caudf.sistemas.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.caudf.sistemas.dto.CategoryActivitiesDTO;
import br.gov.caudf.sistemas.entities.CategoryActivities;
import br.gov.caudf.sistemas.repositories.CategoryActivitiesRepository;
import br.gov.caudf.sistemas.services.exceptions.EntityNotFoundException;

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
		CategoryActivities entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade não existe no banco de dados."));
		return new CategoryActivitiesDTO(entity);
	}
}
