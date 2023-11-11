package ru.example.cloudModuleTest.ArticleService.CloudModuleTestArticleService.service;

import org.springframework.stereotype.Service;
import ru.example.cloudModuleTest.ArticleService.CloudModuleTestArticleService.model.Article;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("MagicNumber")
@Service
public class ArticleService {
    private List<Article> articles = Stream.of(
        new Article(1L, 1L, "article1", "text1"),
        new Article(2L, 2L, "article2", "text2"),
        new Article(3L, 5L, "article3", "text3"),
        new Article(4L, 3L, "article4", "text4"),
        new Article(5L, 2L, "article5", "text5"),
        new Article(6L, 4L, "article6", "text6"),
        new Article(7L, 5L, "article7", "text7")
    ).toList();

    /**
     *
     * @return article list
     */
    public List<Article> getAllArticles() {
        return articles;
    }

    /**
     *
     * @param id - article id
     * @return article
     */
    public Optional<Article> getArticleById(final Long id) {
        return articles.stream().filter(e -> e.getId().equals(id)).findAny();
    }
}
