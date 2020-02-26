package com.unievangelica.eduplan.api.entity;

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

	private String turno;

	private String periodo;

	private String ano;

	private String semestre;

	private String chtotal;

	private String chteorica;

	private String chpratica;

	private String ementa;

	private String objetivoGeral;

	private String objetivoEspecifico;

	private String habilidadeCompetencias;

	private String conteudoProgramatico;

	private String procedimentosDidaticos;

	private String atividadeIntegrativa;

	private String primeiraVA;

	private String segundaVA;

	private String terceiraVA;

	private String bibliografiaBasica;

	private String bibliografiaComplementar;
}