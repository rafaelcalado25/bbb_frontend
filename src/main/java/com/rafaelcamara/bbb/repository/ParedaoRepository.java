package com.rafaelcamara.bbb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaelcamara.bbb.entity.Paredao;

@Repository
public interface ParedaoRepository extends JpaRepository<Paredao, Long> {
	
	public List<Paredao> findByFechadoOrderByDhFechamento(Boolean fechado);
	
	public Paredao findByFechadoTrueAndDhFechamentoIsNull();

}
