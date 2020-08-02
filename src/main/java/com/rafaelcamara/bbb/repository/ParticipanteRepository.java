package com.rafaelcamara.bbb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaelcamara.bbb.entity.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
	
	public List<Participante> findByEliminadoFalse();

}
