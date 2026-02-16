package org.yamendez.blog.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.yamendez.blog.models.Article;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ArticleServiceImpl implements ArticleService{

    @Override
    public List<Article> listAll() {
        List<Article> articleList = new ArrayList<>();
        File dir = new File("articles");
        dir.mkdir();
        File[] filesList = dir.listFiles();
        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();

        Article article = null;
        if(filesList != null) {
            for(File f: filesList) {
                Article a = null;
                try {
                    a = mapper.readValue(f, Article.class);
                    articleList.add(a);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return articleList;
    }

    @Override
    public Optional<Article> findById(Long id) {
        File dir = new File("articles");
        dir.mkdir();
        File[] filesList = dir.listFiles();
        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
        boolean idExists = false;

        Article article = null;
        if(filesList != null) {
            for(File f: filesList) {
                Article a = null;
                try {
                    a = mapper.readValue(f, Article.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if(a.getId().equals(id)) {
                    article = new Article();
                    article.setId(a.getId());
                    article.setTitle(a.getTitle());
                    article.setDate(a.getDate());
                    article.setContent(a.getContent());
                    idExists = true;
                    break;
                }
            }
        }
        if (idExists) {
            return Optional.of(article);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Article> create(Article article){
        Optional<Article> optional = findById(article.getId());
        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
        try {
           if(optional.isEmpty()) {
               File newFile = new File("articles",article.getTitle()+".json");
               mapper.writeValue(newFile, article);
               return Optional.of(article);
           } else {
               File oldFile = new File("articles",optional.get().getTitle()+".json");
               File newFile = new File("articles",article.getTitle()+".json");
               newFile.createNewFile();

               Files.move(oldFile.toPath(), newFile.toPath().resolveSibling(newFile.getName()), REPLACE_EXISTING);

               mapper.writeValue(newFile, article);
               return Optional.of(article);
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Article> delete(String name) {
        return Optional.empty();
    }
}
