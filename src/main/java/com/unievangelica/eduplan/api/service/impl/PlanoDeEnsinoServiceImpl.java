package com.unievangelica.eduplan.api.service.impl;

import com.unievangelica.eduplan.api.repository.PlanoDeEnsinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.unievangelica.eduplan.api.security.entity.PlanoDeEnsino;
import com.unievangelica.eduplan.api.service.PlanoDeEnsinoService;

@Component
public class PlanoDeEnsinoServiceImpl implements PlanoDeEnsinoService {
	
	@Autowired
	private PlanoDeEnsinoRepository planoDeEnsinoRepository;
	
	public PlanoDeEnsino createOrUpdate(PlanoDeEnsino planoDeEnsino) {
		return this.planoDeEnsinoRepository.save(planoDeEnsino);
	}

	public PlanoDeEnsino findById(String id) {
		return this.planoDeEnsinoRepository.findOne(id);
	}

	public void delete(String id) {
		this.planoDeEnsinoRepository.delete(id);
	}

	public Page<PlanoDeEnsino> listPlanoDeEnsino(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.planoDeEnsinoRepository.findAll(pages);
	}
	
	public Iterable<PlanoDeEnsino> findAll() {
		return this.planoDeEnsinoRepository.findAll();
	}
	
	public Page<PlanoDeEnsino> findByCurrentUser(int page, int count, String userId) {
		Pageable pages = new PageRequest(page, count);
		return this.planoDeEnsinoRepository.findByUserIdOrderByDataDesc(pages,userId);
	}
	
	public Page<PlanoDeEnsino> findByParameters(int page, int count,String disciplina) {
		Pageable pages = new PageRequest(page, count);
		return this.planoDeEnsinoRepository.
				findByDisciplinaIgnoreCaseContainingOrderByDataDesc(disciplina,pages);
	}
	
	public Page<PlanoDeEnsino> findByParametersAndCurrentUser(int page, int count,String disciplina,String userId) {
		Pageable pages = new PageRequest(page, count);
		return this.planoDeEnsinoRepository.
				findByDisciplinaIgnoreCaseContainingAndUserIdOrderByDataDesc(disciplina,userId,pages);
	}
	
	public Page<PlanoDeEnsino> findByNumero(int page, int count,Integer numero){
		Pageable pages = new PageRequest(page, count);
		return this.planoDeEnsinoRepository.findByNumero(numero, pages);
	}
}