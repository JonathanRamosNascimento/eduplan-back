package com.unievangelica.eduplan.api.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unievangelica.eduplan.api.response.Response;
import com.unievangelica.eduplan.api.entity.PlanoDeEnsino;
import com.unievangelica.eduplan.api.entity.User;
import com.unievangelica.eduplan.api.enums.ProfileEnum;
import com.unievangelica.eduplan.api.security.jwt.JwtTokenUtil;
import com.unievangelica.eduplan.api.service.PlanoDeEnsinoService;
import com.unievangelica.eduplan.api.service.UserService;

@RestController
@RequestMapping("/api/plano-de-ensino")
@CrossOrigin(origins = "*")
public class PlanoDeEnsinoController {

	@Autowired
	private PlanoDeEnsinoService planoDeEnsinoService;

	@Autowired
	protected JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@PostMapping()
	@PreAuthorize("hasAnyRole('DOCENTE','DIRETOR')")
	public ResponseEntity<Response<PlanoDeEnsino>> create(HttpServletRequest request,
			@RequestBody PlanoDeEnsino planoDeEnsino, BindingResult result) {
		Response<PlanoDeEnsino> response = new Response<PlanoDeEnsino>();
		try {
			validateCreatePlanoDeEnsino(planoDeEnsino, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			planoDeEnsino.setUser(userFromRequest(request));
			planoDeEnsino.setData(new Date());
			PlanoDeEnsino planoDeEnsinoPersisted = (PlanoDeEnsino) planoDeEnsinoService.createOrUpdate(planoDeEnsino);
			response.setData(planoDeEnsinoPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateCreatePlanoDeEnsino(PlanoDeEnsino planoDeEnsino, BindingResult result) {
		if (planoDeEnsino.getDisciplina() == null) {
			result.addError(new ObjectError("Plano de Ensino", "Titulo não information"));
			return;
		}
	}

	public User userFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String email = jwtTokenUtil.getUsernameFromToken(token);
		return userService.findByEmail(email);
	}

	@PutMapping()
	@PreAuthorize("hasAnyRole('DOCENTE','DIRETOR')")
	public ResponseEntity<Response<PlanoDeEnsino>> update(HttpServletRequest request,
			@RequestBody PlanoDeEnsino planoDeEnsino, BindingResult result) {
		Response<PlanoDeEnsino> response = new Response<PlanoDeEnsino>();
		try {
			validateUpdatePlanoDeEnsino(planoDeEnsino, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			PlanoDeEnsino planoDeEnsinoCurrent = planoDeEnsinoService.findById(planoDeEnsino.getId());
			planoDeEnsino.setUser(planoDeEnsinoCurrent.getUser());
			planoDeEnsino.setData(planoDeEnsinoCurrent.getData());
			PlanoDeEnsino planoDeEnsinoPersisted = (PlanoDeEnsino) planoDeEnsinoService.createOrUpdate(planoDeEnsino);
			response.setData(planoDeEnsinoPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateUpdatePlanoDeEnsino(PlanoDeEnsino planoDeEnsino, BindingResult result) {
		if (planoDeEnsino.getId() == null) {
			result.addError(new ObjectError("Plano de Ensino", "Id no information"));
			return;
		}
		if (planoDeEnsino.getDisciplina() == null) {
			result.addError(new ObjectError("Plano de Ensino", "Title no information"));
			return;
		}
	}

	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('DOCENTE','DIRETOR')")
	public ResponseEntity<Response<PlanoDeEnsino>> findById(@PathVariable("id") String id) {
		Response<PlanoDeEnsino> response = new Response<PlanoDeEnsino>();
		PlanoDeEnsino planoDeEnsino = planoDeEnsinoService.findById(id);
		if (planoDeEnsino == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(planoDeEnsino);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('DOCENTE','DIRETOR')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
		Response<String> response = new Response<String>();
		PlanoDeEnsino planoDeEnsino = planoDeEnsinoService.findById(id);
		if (planoDeEnsino == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		planoDeEnsinoService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}

	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('DOCENTE','DIRETOR')")
	public ResponseEntity<Response<Page<PlanoDeEnsino>>> findAll(HttpServletRequest request, @PathVariable int page,
			@PathVariable int count) {

		Response<Page<PlanoDeEnsino>> response = new Response<Page<PlanoDeEnsino>>();
		Page<PlanoDeEnsino> planosDeEnsino = null;
		User userRequest = userFromRequest(request);
		if (userRequest.getProfile().equals(ProfileEnum.ROLE_DIRETOR)) {
			planosDeEnsino = planoDeEnsinoService.listPlanoDeEnsino(page, count);
		} else if (userRequest.getProfile().equals(ProfileEnum.ROLE_DOCENTE)) {
			planosDeEnsino = planoDeEnsinoService.findByCurrentUser(page, count, userRequest.getId());
		}
		response.setData(planosDeEnsino);
		return ResponseEntity.ok(response);
	}
}
