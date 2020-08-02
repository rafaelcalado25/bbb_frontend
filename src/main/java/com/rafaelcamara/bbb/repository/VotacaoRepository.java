package com.rafaelcamara.bbb.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rafaelcamara.bbb.dto.RegistroVotacaoAgrupamentoPorDataDTO;
import com.rafaelcamara.bbb.entity.Paredao;
import com.rafaelcamara.bbb.entity.Participante;
import com.rafaelcamara.bbb.entity.Usuario;
import com.rafaelcamara.bbb.entity.Votacao;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long> {
	
	
	@Query("SELECT count(v) from Votacao v  "
			+ " where v.paredao = :paredao and v.usuario = :usuario"
			+ " and v.dhVoto between :dh1 and :dh2")
	public Integer consultarVotacaoesUsuarioParedaoUltimoMinuto(@Param("paredao") Paredao paredao,
			@Param("usuario") Usuario usuario, @Param("dh1") Timestamp dh1,
			@Param("dh2") Timestamp dh2);
	
	@Query("SELECT count(v) from Votacao v  "
			+ " where v.paredao = :paredao and v.participante = :participante")
	public Integer consultarVotacaoesParticipanteParedao(@Param("paredao") Paredao paredao,
			@Param("participante") Participante participante);
	
	@Query("SELECT new com.rafaelcamara.bbb.dto.RegistroVotacaoAgrupamentoPorDataDTO("
			+ "month(v.dhVoto), day(v.dhVoto), hour(v.dhVoto), "
			+ " minute(v.dhVoto), count(v.id)) from Votacao as v where v.paredao= :paredao "
			+ " group by month(v.dhVoto), day(v.dhVoto),hour(v.dhVoto), minute(v.dhVoto)")
	public List<RegistroVotacaoAgrupamentoPorDataDTO> consultarVotacaoesPorHora(@Param("paredao") Paredao paredao);

}
