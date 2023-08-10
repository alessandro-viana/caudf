package br.gov.caudf.sistemas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.caudf.sistemas.dto.ProtocolDTO;
import br.gov.caudf.sistemas.entities.Protocol;
import br.gov.caudf.sistemas.repositories.ProtocolRepository;

@Service
public class ProtocolService {

	@Autowired
	private ProtocolRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ProtocolDTO> findAllPaged(PageRequest pageRequest) {
		Page<Protocol> list = repository.findAll(pageRequest);
		return list.map(x -> new ProtocolDTO(x));
	}
	
}
