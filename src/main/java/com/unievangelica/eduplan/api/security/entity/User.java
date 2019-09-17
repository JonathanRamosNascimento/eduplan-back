package com.unievangelica.eduplan.api.security.entity;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.unievangelica.eduplan.api.security.enums.ProfileEnum;

@Getter
@Setter
@Document
public class User {

	@Id
	private String id;

	private String nome;

	private String matricula;

	@Indexed(unique = true)
	@NotBlank(message = "Email obrigatorio")
	@Email(message = "Email invalido")
	private String email;

	@NotBlank(message = "Password obrigadotiro")
	@Size(min = 6)
	private String password;

	private ProfileEnum profile;
}
