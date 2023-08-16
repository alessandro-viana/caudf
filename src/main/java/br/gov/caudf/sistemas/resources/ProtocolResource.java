package br.gov.caudf.sistemas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caudf.sistemas.dto.ArchitectDTO;
import br.gov.caudf.sistemas.dto.ProtocolDTO;
import br.gov.caudf.sistemas.services.ProtocolService;

@RestController
@RequestMapping(value = "/protocols")
public class ProtocolResource {

	@Autowired
	private ProtocolService service;
	
	@GetMapping
	public ResponseEntity<Page<ProtocolDTO>> findAllPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<ProtocolDTO> list = service.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProtocolDTO> findById(@PathVariable Long id) {
		ProtocolDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	/*
	 * @GetMapping(value = "/{id}") public ResponseEntity<ArchitectDTO>
	 * findById(@PathVariable Long id){ ArchitectDTO dto = service.findById(id);
	 * return ResponseEntity.ok().body(dto); }
	 */
	

}
