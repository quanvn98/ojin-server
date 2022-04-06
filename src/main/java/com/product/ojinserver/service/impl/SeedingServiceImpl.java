package com.product.ojinserver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.product.ojinserver.entity.Article;
import com.product.ojinserver.entity.Role;
import com.product.ojinserver.entity.User;
import com.product.ojinserver.repository.ArticleRepository;
import com.product.ojinserver.repository.RoleRepository;
import com.product.ojinserver.repository.UserRepository;
import com.product.ojinserver.service.SeedingService;

@Service
public class SeedingServiceImpl implements SeedingService {

	private final ArticleRepository articleRepository;
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public SeedingServiceImpl(ArticleRepository _articleRepository, RoleRepository _roleRepository,
			UserRepository _userRepository, PasswordEncoder _passwordEncoder) {
		this.articleRepository = _articleRepository;
		this.roleRepository = _roleRepository;
		this.userRepository = _userRepository;
		this.passwordEncoder = _passwordEncoder;
	}

	public void seedArticles() {
		long count = articleRepository.count();

		if (count == 0) {
			List<Article> articles = new ArrayList<Article>();

			Article article1 = new Article("Title 1", "Description 1");
			articles.add(article1);
			Article article2 = new Article("Title 2", "Description 2");
			articles.add(article2);
			Article article3 = new Article("Title 3", "Description 3");
			articles.add(article3);

			articleRepository.saveAll(articles);
		}
	}

	public void seedRoles() {
		long count = roleRepository.count();

		if (count == 0) {
			List<Role> roles = new ArrayList<Role>();

			Role role1 = new Role("ROLE_ADMIN");
			roles.add(role1);
			Role role2 = new Role("ROLE_USER");
			roles.add(role2);

			roleRepository.saveAll(roles);
		}
	}

	@Transactional
	public void seedUsers() {
		final String ADMIN_USERNAME = "0000000000";
		final String ADMIN_PASSWORD = "123456";

		final String USER_USERNAME = "1111111111";
		final String USER_PASSWORD = "123456";

		Role roleAdmin = roleRepository.findByAuthority("ROLE_ADMIN").orElseGet(() -> null);
		Role roleUser = roleRepository.findByAuthority("ROLE_USER").orElseGet(() -> null);

		boolean adminExists = userRepository.existsByUsername(ADMIN_USERNAME);
		boolean userExists = userRepository.existsByUsername(USER_USERNAME);

		if (!adminExists && roleAdmin != null && roleUser != null) {
			User admin = new User(ADMIN_USERNAME, passwordEncoder.encode(ADMIN_PASSWORD),
					"Demo Admin", true);

			admin.addRole(roleAdmin);
			admin.addRole(roleUser);

			userRepository.save(admin);
		}

		if (!userExists && roleUser != null) {
			User user = new User(USER_USERNAME, passwordEncoder.encode(USER_PASSWORD), "Demo User",
					true);

			user.addRole(roleUser);

			userRepository.save(user);
		}
	}

}
