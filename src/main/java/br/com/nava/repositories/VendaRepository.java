package br.com.nava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nava.entities.VendaEntity;

@Repository
public interface VendaRepository extends JpaRepository<VendaEntity, Integer>{

}
