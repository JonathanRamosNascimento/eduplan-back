package com.unievangelica.eduplan.api.controller;

import com.unievangelica.eduplan.api.entity.Subject;
import com.unievangelica.eduplan.api.entity.User;
import com.unievangelica.eduplan.api.enums.ProfileEnum;
import com.unievangelica.eduplan.api.response.Response;
import com.unievangelica.eduplan.api.security.jwt.JwtTokenUtil;
import com.unievangelica.eduplan.api.service.SubjectService;
import com.unievangelica.eduplan.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/disciplina")
@CrossOrigin(origins = "*")
public class SubjectController {

  @Autowired
  private SubjectService subjectService;

  @Autowired
  protected JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserService userService;

  @PostMapping()
  @PreAuthorize("hasAnyRole('DIRETOR')")
  public ResponseEntity<Response<Subject>> create(HttpServletRequest request, @RequestBody Subject subject,
      BindingResult result) {
    Response<Subject> response = new Response<Subject>();
    try {
      validateCreateSubject(subject, result);
      if (result.hasErrors()) {
        result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(response);
      }
      Subject subjectPersisted = (Subject) subjectService.createOrUpdate(subject);
      response.setData(subjectPersisted);
    } catch (Exception e) {
      response.getErrors().add(e.getMessage());
      return ResponseEntity.badRequest().body(response);
    }
    return ResponseEntity.ok(response);
  }

  private void validateCreateSubject(Subject subject, BindingResult result) {
    if (subject.getNome() == null) {
      result.addError(new ObjectError("Disciplina", "Nome da disciplina não informado"));
      return;
    }
  }

  public User userFromRequest(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    String email = jwtTokenUtil.getUsernameFromToken(token);
    return userService.findByEmail(email);
  }

  @PutMapping()
  @PreAuthorize("hasAnyRole('DIRETOR')")
  public ResponseEntity<Response<Subject>> update(HttpServletRequest request, @RequestBody Subject subject,
      BindingResult result) {
    Response<Subject> response = new Response<Subject>();
    try {
      validateUpdateSubject(subject, result);
      if (result.hasErrors()) {
        result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(response);
      }
      Subject subjectPersisted = (Subject) subjectService.createOrUpdate(subject);
      response.setData(subjectPersisted);
    } catch (Exception e) {
      response.getErrors().add(e.getMessage());
      return ResponseEntity.badRequest().body(response);
    }
    return ResponseEntity.ok(response);
  }

  private void validateUpdateSubject(Subject subject, BindingResult result) {
    if (subject.getId() == null) {
      result.addError(new ObjectError("Disciplina", "Id não informado"));
      return;
    }
  }

  @GetMapping(value = "/{id}")
  @PreAuthorize("hasAnyRole('DOCENTE', 'DIRETOR')")
  public ResponseEntity<Response<Subject>> findById(@PathVariable("id") String id) {
    Response<Subject> response = new Response<Subject>();
    Subject subject = subjectService.findById(id);
    if (subject == null) {
      response.getErrors().add("Id não encontrado: " + id);
      return ResponseEntity.badRequest().body(response);
    }
    response.setData(subject);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/{id}")
  @PreAuthorize("hasAnyRole('DIRETOR')")
  public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
    Response<String> response = new Response<String>();
    Subject subject = subjectService.findById(id);
    if (subject == null) {
      response.getErrors().add("Id não encontrado: " + id);
      return ResponseEntity.badRequest().body(response);
    }
    subjectService.delete(id);
    return ResponseEntity.ok(new Response<String>());
  }

  @GetMapping(value = "{page}/{count}")
  @PreAuthorize("hasAnyRole('DOCENTE', 'DIRETOR')")
  public ResponseEntity<Response<Page<Subject>>> findAll(HttpServletRequest request, @PathVariable int page,
      @PathVariable int count) {
    Response<Page<Subject>> response = new Response<Page<Subject>>();
    Page<Subject> subjects = null;
    User userRequest = userFromRequest(request);
    if (userRequest.getProfile().equals(ProfileEnum.ROLE_DOCENTE)
        || userRequest.getProfile().equals(ProfileEnum.ROLE_DIRETOR)) {
      subjects = subjectService.listSubject(page, count);
    }
    response.setData(subjects);
    return ResponseEntity.ok(response);
  }
}