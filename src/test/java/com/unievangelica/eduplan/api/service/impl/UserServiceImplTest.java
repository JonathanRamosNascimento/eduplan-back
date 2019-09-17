package com.unievangelica.eduplan.api.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unievangelica.eduplan.api.repository.UserRepository;
import com.unievangelica.eduplan.api.security.entity.User;
import com.unievangelica.eduplan.api.security.enums.ProfileEnum;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findByEmail() {
		String email = "teste@email.org";
		User userEsperado = this.getUserMock("1");
		when(userRepository.findByEmail(email)).thenReturn(userEsperado);
		User userRecebido = userServiceImpl.findByEmail(email);
		assertEquals(userEsperado, userRecebido);
	}

	@Test
	public void createOrUpdate() {
		User userEsperado = this.getUserMock("1");
		when(userRepository.save(userEsperado)).thenReturn(userEsperado);
		User userRecebido = userServiceImpl.createOrUpdate(userEsperado);
		assertEquals(userEsperado, userRecebido);
	}

	@Test
	public void findById() {
		String id = "1";
		User userEsperado = this.getUserMock("1");
		when(userRepository.findOne(id)).thenReturn(userEsperado);
		User userRecebido = userServiceImpl.findById(id);
		assertEquals(userEsperado, userRecebido);
	}

	@Test
	public void delete() {
		String id = "1";
		userServiceImpl.delete(id);
	}

	@Test
	public void findAll() {
		int page = 1;
		int count = 10;
		Pageable pages = new PageRequest(page, count);
		Page<User> usersPageEsperado = this.getUserPageMock();
		when(userRepository.findAll(pages)).thenReturn(usersPageEsperado);
		Page<User> usersPageRecebido = userServiceImpl.findAll(page, count);
		assertEquals(usersPageEsperado, usersPageRecebido);
	}

	private User getUserMock(String id) {
		User user = new User();

		user.setId(id);
		user.setNome("Nome Teste");
		user.setEmail("teste@email.org");
		user.setMatricula("1234567890");
		user.setProfile(ProfileEnum.ROLE_DIRETOR);
		user.setPassword("senhateste");

		return user;
	}

	private Page<User> getUserPageMock() {
		User user1 = this.getUserMock("1");
		User user2 = this.getUserMock("2");

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		Page<User> usersPage = new PageImpl<User>(users);

		return usersPage;
	}

}
