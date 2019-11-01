package com.unievangelica.eduplan.api.repository;

import com.unievangelica.eduplan.api.entity.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository extends MongoRepository<Subject, String> {
}