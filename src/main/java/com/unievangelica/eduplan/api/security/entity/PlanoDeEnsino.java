package com.unievangelica.eduplan.api.security.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class PlanoDeEnsino {

	@Id
	private String id;

	@DBRef(lazy = true)
	private User user;

	private Date data;

	private String disciplina;

	private Integer numero;

	private String turno;

	private String chteorica;

	private String chpratica;

	private String chtotal;

	private String creditos;

	private String periodo;

	private String ano;

	private String semestre;

	private String ementa;

	private String justificativa;

	private String objetivos;

	private String competenciaPreTextual;

	private String competenciaPessoal;

	private String competenciaInterpessoal;

	private String competenciaTecnica;

	private String competenciaPosTextual;

	private String interPreTextual;

	private String interdisciplinaridade;

	private String unidadeDidatica;

	private String cronogramaAulas;

	private String desenvolvimento;

	private String avaliacaoPreTextual;

	private String primeiraVa;

	private String segundaVa;

	private String terceiraVa;

	private String observacoes;

	private String biblioBasica;

	private String biblioComplementar;
}
