package com.unievangelica.eduplan.api.service;

import com.unievangelica.eduplan.api.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface SubjectService {

    Subject createOrUpdate(Subject subject);

    Subject findById(String id);

    void delete(String id);

    Page<Subject> listSubject(int page, int count);

    Iterable<Subject> findAll();
}
