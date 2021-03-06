package com.unievangelica.eduplan.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.unievangelica.eduplan.api.entity.PlanoDeEnsino;

public interface PlanoDeEnsinoRepository extends MongoRepository<PlanoDeEnsino, String> {

	Page<PlanoDeEnsino> findByUserIdOrderByDataDesc(Pageable pages, String userId);
}
