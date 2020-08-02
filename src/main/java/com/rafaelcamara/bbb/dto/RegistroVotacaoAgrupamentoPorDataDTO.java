package com.rafaelcamara.bbb.dto;

public class RegistroVotacaoAgrupamentoPorDataDTO {

	private Integer mes;
	private Integer dia;
	private Integer hora;
	private Integer minuto;
	private Long total;
	
	public RegistroVotacaoAgrupamentoPorDataDTO() {
		super();
	}

	public RegistroVotacaoAgrupamentoPorDataDTO(Integer mes, Integer dia, Integer hora, Integer minuto, Long total) {
		super();
		this.mes = mes;
		this.dia = dia;
		this.hora = hora;
		this.minuto = minuto;
		this.total = total;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public Integer getMinuto() {
		return minuto;
	}

	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
		
}
