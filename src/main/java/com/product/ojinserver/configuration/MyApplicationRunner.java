package com.product.ojinserver.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.product.ojinserver.entity.Article;
import com.product.ojinserver.repository.ArticleRepository;

@Component
public class MyApplicationRunner implements ApplicationRunner {

	private ArticleRepository articleRepository;

	public MyApplicationRunner(ArticleRepository _articleRepository) {
		this.articleRepository = _articleRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = articleRepository.count();

		if (count == 0) {
			List<Article> articles = new ArrayList<Article>();

			Article article1 = new Article(UUID.randomUUID(), "Title 1", "Description 1");
			articles.add(article1);
			Article article2 = new Article(UUID.randomUUID(), "Title 2", "Description 2");
			articles.add(article2);
			Article article3 = new Article(UUID.randomUUID(), "Title 3", "Description 3");
			articles.add(article3);

			articleRepository.saveAll(articles);
		}
	}

}
