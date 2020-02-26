package com.unievangelica.eduplan.api.service.impl;

import com.unievangelica.eduplan.api.entity.Subject;
import com.unievangelica.eduplan.api.repository.SubjectRepository;
import com.unievangelica.eduplan.api.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class SubjectServiceImpl implements SubjectService {

  @Autowired
  private SubjectRepository subjectRepository;

  @Override
  public Subject createOrUpdate(Subject subject) {
    return this.subjectRepository.save(subject);
  }

  @Override
  public Subject findById(String id) {
    return this.subjectRepository.findOne(id);
  }

  @Override
  public void delete(String id) {
    this.subjectRepository.delete(id);
  }

  @Override
  public Page<Subject> listSubject(int page, int count) {
    Pageable pages = new PageRequest(page, count);
    return this.subjectRepository.findAll(pages);
  }

  @Override
  public Iterable<Subject> findAll() {
    return this.subjectRepository.findAll();
  }
}
