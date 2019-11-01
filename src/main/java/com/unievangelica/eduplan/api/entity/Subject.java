package com.unievangelica.eduplan.api.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Subject {

    @Id
    private String id;

    private String nome;

    private String chteorica;

    private String chpratica;

    private String chtotal;

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