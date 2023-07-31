package br.gov.caudf.sistemas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.caudf.sistemas.entities.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
	
}
