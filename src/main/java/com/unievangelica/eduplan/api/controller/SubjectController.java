package com.unievangelica.eduplan.api.controller;

import com.unievangelica.eduplan.api.entity.Subject;
import com.unievangelica.eduplan.api.response.Response;
import com.unievangelica.eduplan.api.security.jwt.JwtTokenUtil;
import com.unievangelica.eduplan.api.service.SubjectService;
import com.unievangelica.eduplan.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Response<Subject>> create(HttpServletRequest request, @RequestBody Subject subject, BindingResult result) {
        Response<Subject> response = new Response<Subject>();
        try {
            validateCreateSubject(subject, result);
            if(result.hasErrors()) {
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
        if(subject.getNome() == null) {
            result.addError(new ObjectError("Disciplina", "Nome da disciplina não informado"));
            return;
        }
    }
}
