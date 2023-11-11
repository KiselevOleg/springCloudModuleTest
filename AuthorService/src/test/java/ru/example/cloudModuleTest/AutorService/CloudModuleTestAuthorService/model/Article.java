package ru.example.cloudModuleTest.AutorService.CloudModuleTestAuthorService.model;

import lombok.*;

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
