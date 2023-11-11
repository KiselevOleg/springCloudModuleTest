package ru.example.cloudModuleTest.ArticleService.CloudModuleTestArticleService.model;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Long id;
    private Long authorId;
    private String name;
    private String text;
}
