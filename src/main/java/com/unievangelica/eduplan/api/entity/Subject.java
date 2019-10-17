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

    private String observacoes;

    private String biblioBasica;

    private String biblioComplementar;

}
