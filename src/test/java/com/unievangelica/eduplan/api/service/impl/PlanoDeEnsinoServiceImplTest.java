package com.unievangelica.eduplan.api.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unievangelica.eduplan.api.repository.PlanoDeEnsinoRepository;
import com.unievangelica.eduplan.api.entity.PlanoDeEnsino;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlanoDeEnsinoServiceImplTest {

	@Mock
	private PlanoDeEnsinoRepository planoDeEnsinoRepository;

	@InjectMocks
	private PlanoDeEnsinoServiceImpl planoDeEnsinoServiceImpl;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createOrUpdate() {
		PlanoDeEnsino planoDeEnsinoEsperado = this.getPlanoDeEnsinoMock("1");
		when(planoDeEnsinoRepository.save(planoDeEnsinoEsperado)).thenReturn(planoDeEnsinoEsperado);
		PlanoDeEnsino planoDeEnsinoRecebido = planoDeEnsinoServiceImpl.createOrUpdate(planoDeEnsinoEsperado);
		assertEquals(planoDeEnsinoEsperado, planoDeEnsinoRecebido);
	}

	@Test
	public void findById() {
		String id = "1";
		PlanoDeEnsino planoDeEnsinoEsperado = this.getPlanoDeEnsinoMock("1");
		when(planoDeEnsinoRepository.findOne(id)).thenReturn(planoDeEnsinoEsperado);
		PlanoDeEnsino planoDeEnsinoRecebido = planoDeEnsinoServiceImpl.findById(id);
		assertEquals(planoDeEnsinoEsperado, planoDeEnsinoRecebido);
	}

	@Test
	public void delete() {
		String id = "1";
		planoDeEnsinoServiceImpl.delete(id);
	}

	@Test
	public void listPlanoDeEnsino() {
		int page = 1;
		int count = 10;
		Pageable pages = new PageRequest(page, count);
		Page<PlanoDeEnsino> planosDeEnsinoPageEsperado = this.getPlanoDeEnsinoPageMock();
		when(planoDeEnsinoRepository.findAll(pages)).thenReturn(planosDeEnsinoPageEsperado);
		Page<PlanoDeEnsino> planosDeEnsinoPageRecebido = planoDeEnsinoServiceImpl.listPlanoDeEnsino(page, count);
		assertEquals(planosDeEnsinoPageEsperado, planosDeEnsinoPageRecebido);
	}

	@Test
	public void findAll() {
		List<PlanoDeEnsino> planosDeEnsinoEsperado = this.getPlanoDeEnsinoListMock();
		Iterable<PlanoDeEnsino> planosDeEnsinoIterableEsperado = planosDeEnsinoEsperado;
		when(planoDeEnsinoRepository.findAll()).thenReturn(planosDeEnsinoEsperado);
		Iterable<PlanoDeEnsino> planosDeEnsinoIterableRecebido = planoDeEnsinoServiceImpl.findAll();
		assertEquals(planosDeEnsinoIterableEsperado, planosDeEnsinoIterableRecebido);
	}

	@Test
	public void findByCurrentUser() {
		int page = 1;
		int count = 10;
		String userId = "2";
		Pageable pages = new PageRequest(page, count);
		Page<PlanoDeEnsino> planosDeEnsinoPageEsperado = this.getPlanoDeEnsinoPageMock();
		when(planoDeEnsinoRepository.findByUserIdOrderByDataDesc(pages, userId)).thenReturn(planosDeEnsinoPageEsperado);
		Page<PlanoDeEnsino> planosDeEnsinoPageRecebido = planoDeEnsinoServiceImpl.findByCurrentUser(page, count,
				userId);
		assertEquals(planosDeEnsinoPageEsperado, planosDeEnsinoPageRecebido);
	}

	@Test
	public void findByParameters() {
		int page = 1;
		int count = 10;
		String disciplina = "Matemática";
		Pageable pages = new PageRequest(page, count);
		Page<PlanoDeEnsino> planosDeEnsinoPageEsperado = this.getPlanoDeEnsinoPageMock();
		when(planoDeEnsinoRepository.findByDisciplinaIgnoreCaseContainingOrderByDataDesc(disciplina, pages))
				.thenReturn(planosDeEnsinoPageEsperado);
		Page<PlanoDeEnsino> planosDeEnsinoPageRecebido = planoDeEnsinoServiceImpl.findByParameters(page, count,
				disciplina);
		assertEquals(planosDeEnsinoPageEsperado, planosDeEnsinoPageRecebido);
	}

	@Test
	public void findByParametersAndCurrentUser() {
		int page = 1;
		int count = 10;
		String disciplina = "Matemática";
		String userId = "3";
		Pageable pages = new PageRequest(page, count);
		Page<PlanoDeEnsino> planosDeEnsinoPageEsperado = this.getPlanoDeEnsinoPageMock();
		when(planoDeEnsinoRepository.findByDisciplinaIgnoreCaseContainingAndUserIdOrderByDataDesc(disciplina, userId,
				pages)).thenReturn(planosDeEnsinoPageEsperado);
		Page<PlanoDeEnsino> planosDeEnsinoPageRecebido = planoDeEnsinoServiceImpl.findByParametersAndCurrentUser(page,
				count, disciplina, userId);
		assertEquals(planosDeEnsinoPageEsperado, planosDeEnsinoPageRecebido);
	}

	@Test
	public void findByNumero() {
		int page = 1;
		int count = 10;
		Integer numero = 44;
		Pageable pages = new PageRequest(page, count);
		Page<PlanoDeEnsino> planosDeEnsinoPageEsperado = this.getPlanoDeEnsinoPageMock();
		when(planoDeEnsinoRepository.findByNumero(numero, pages)).thenReturn(planosDeEnsinoPageEsperado);
		Page<PlanoDeEnsino> planosDeEnsinoPageRecebido = planoDeEnsinoServiceImpl.findByNumero(page, count, numero);
		assertEquals(planosDeEnsinoPageEsperado, planosDeEnsinoPageRecebido);
	}

	private PlanoDeEnsino getPlanoDeEnsinoMock(String id) {
		PlanoDeEnsino planoDeEnsino = new PlanoDeEnsino();
		planoDeEnsino.setId(id);
		planoDeEnsino.setAno("2018");
		return planoDeEnsino;
	}

	private Page<PlanoDeEnsino> getPlanoDeEnsinoPageMock() {
		List<PlanoDeEnsino> planosDeEnsino = this.getPlanoDeEnsinoListMock();
		Page<PlanoDeEnsino> planosDeEnsinoPage = new PageImpl<PlanoDeEnsino>(planosDeEnsino);
		return planosDeEnsinoPage;
	}

	private List<PlanoDeEnsino> getPlanoDeEnsinoListMock() {
		PlanoDeEnsino planoDeEnsino1 = this.getPlanoDeEnsinoMock("1");
		PlanoDeEnsino planoDeEnsino2 = this.getPlanoDeEnsinoMock("2");

		List<PlanoDeEnsino> planosDeEnsino = new ArrayList<PlanoDeEnsino>();
		planosDeEnsino.add(planoDeEnsino1);
		planosDeEnsino.add(planoDeEnsino2);

		return planosDeEnsino;
	}

}