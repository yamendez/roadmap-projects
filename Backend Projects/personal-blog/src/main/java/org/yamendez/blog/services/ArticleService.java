package org.yamendez.blog.services;

import org.yamendez.blog.models.Article;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
    List<Article> listAll();
    Optional<Article> findById(Long id);
    Optional<Article> create(Article article);
    Optional<Article> delete(String name);
}
