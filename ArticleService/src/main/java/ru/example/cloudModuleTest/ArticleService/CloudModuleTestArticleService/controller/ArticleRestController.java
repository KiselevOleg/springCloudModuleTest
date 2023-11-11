package ru.example.cloudModuleTest.ArticleService.CloudModuleTestArticleService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.cloudModuleTest.ArticleService.CloudModuleTestArticleService.model.Article;
import ru.example.cloudModuleTest.ArticleService.CloudModuleTestArticleService.service.ArticleService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("articles")
public class ArticleRestController {
    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(final ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     *
     * @return article List
     */
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public List<Article> findAll() {
        return articleService.getAllArticles();
    }

    /**
     *
     * @param id - author id
     * @return Author
     */
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> findArticleById(final @PathVariable Long id) {
        return articleService.getArticleById(id)
            .map(e -> ResponseEntity.status(HttpStatus.OK).body(e))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
