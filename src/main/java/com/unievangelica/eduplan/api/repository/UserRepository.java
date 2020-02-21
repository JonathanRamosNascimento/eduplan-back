package com.unievangelica.eduplan.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.unievangelica.eduplan.api.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);
}
