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

	@DBRef
	private User user;

	private Date data;

	@DBRef
	private Subject disciplina;

	private String turno;

	private String periodo;

	private String ano;

	private String semestre;
}