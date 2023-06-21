package br.gov.caudf.sistemas.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.caudf.sistemas.dto.CategoryActivitiesDTO;
import br.gov.caudf.sistemas.services.CategoryActivitiesService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryActivitiesResource {
	
	@Autowired
	private CategoryActivitiesService service;
	
	@GetMapping
	public ResponseEntity<List<CategoryActivitiesDTO>> findAll(){
		List<CategoryActivitiesDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryActivitiesDTO> findById(@PathVariable Long id){
		CategoryActivitiesDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<CategoryActivitiesDTO> insert(@RequestBody CategoryActivitiesDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryActivitiesDTO> update(@PathVariable Long id, @RequestBody CategoryActivitiesDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

}
