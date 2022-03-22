package com.product.ojinserver.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.ojinserver.entity.Article;
import com.product.ojinserver.repository.ArticleRepository;

@RestController
public class ArticleController {

	private final ArticleRepository articleRepository;

	public ArticleController(ArticleRepository _articleRepository) {
		this.articleRepository = _articleRepository;
	}

	@GetMapping("/articles")
	public List<Article> getArticles() {
		return articleRepository.findAll();
	}

}
