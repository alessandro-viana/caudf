package br.gov.caudf.sistemas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.caudf.sistemas.dto.ArchitectDTO;
import br.gov.caudf.sistemas.dto.ProtocolDTO;
import br.gov.caudf.sistemas.entities.Architect;
import br.gov.caudf.sistemas.entities.Protocol;
import br.gov.caudf.sistemas.repositories.ProtocolRepository;
import br.gov.caudf.sistemas.services.exceptions.ResourceNotFoundException;

@Service
public class ProtocolService {

	@Autowired
	private ProtocolRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ProtocolDTO> findAllPaged(PageRequest pageRequest) {
		Page<Protocol> list = repository.findAll(pageRequest);
		return list.map(x -> new ProtocolDTO(x));
	}

	@Transactional(readOnly = true)
	public ProtocolDTO findById(Long id) {
		Optional<Protocol> obj = repository.findById(id);
		Protocol entity = obj.orElseThrow(() -> new ResourceNotFoundException("Não existe esse ID no Banco de dados"));
		return new ProtocolDTO(entity);
	}
	
	/*
	 * @Transactional(readOnly = true) public ArchitectDTO findById(Long id) {
	 * Optional<Architect> obj = repository.findById(id); Architect entity =
	 * obj.orElseThrow(() -> new
	 * ResourceNotFoundException("Entidade não existe no banco de dados.")); return
	 * new ArchitectDTO(entity, entity.getCategories()); }
	 */
	
	
	
}
