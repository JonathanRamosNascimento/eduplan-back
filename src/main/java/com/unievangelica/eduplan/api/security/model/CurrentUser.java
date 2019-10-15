package com.unievangelica.eduplan.api.security.model;

import com.unievangelica.eduplan.api.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentUser {
	
	private String token;
	private User user;

	public CurrentUser(String token, User user) {
		this.token = token;
		this.user = user;
	}
}
