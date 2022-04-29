package br.com.nava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.nava.entities.ProfessorEntity;


@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Integer>{
	// ORM -> Object Relational Mapping
	
		// findBy indica que o Spring irá montar o SQL
		// SELECT * FROM PROFESSORES WHERE NOME LIKE '%nome%'
		public List<ProfessorEntity> findByNomeContains(String nome);
		
		// JPQL -> Java Persist Query Language
		// CONTAINS
		@Query(value = "SELECT p FROM ProfessorEntity p WHERE p.nome like %:nome%")
		// STARTS WITH
		//@Query(value = "SELECT p FROM ProfessorEntity p WHERE p.nome like :nome%")
		// ENDS WITH
		//@Query(value = "SELECT p FROM ProfessorEntity p WHERE p.nome like %:nome")
		public List<ProfessorEntity> searchByNome(@Param("nome") String nome);
		
		@Query(value = "SELECT p FROM ProfessorEntity p WHERE p.nome like %?1%")
		public List<ProfessorEntity> searchByNome2(String nome);
		
		@Query(value = "SELECT * FROM PROFESSORES p WHERE p.nome like %?1%", nativeQuery = true)
		public List<ProfessorEntity> searchByNomeNativeSQL(String nome);
}
