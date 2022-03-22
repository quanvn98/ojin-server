package com.product.ojinserver.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.ojinserver.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, UUID> {

}
