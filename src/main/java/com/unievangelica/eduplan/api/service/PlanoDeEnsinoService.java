package com.unievangelica.eduplan.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.unievangelica.eduplan.api.entity.PlanoDeEnsino;

@Component
public interface PlanoDeEnsinoService {

	PlanoDeEnsino createOrUpdate(PlanoDeEnsino planoDeEnsino);

	PlanoDeEnsino findById(String id);

	void delete(String id);

	Page<PlanoDeEnsino> listPlanoDeEnsino(int page, int count);

	Page<PlanoDeEnsino> findByCurrentUser(int page, int count, String userId);

	Iterable<PlanoDeEnsino> findAll();
}
