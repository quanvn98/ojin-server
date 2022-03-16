package com.product.ojinserver.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.ojinserver.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {

}
