package ru.example.cloudModuleTest.AutorService.CloudModuleTestAuthorService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.cloudModuleTest.AutorService.CloudModuleTestAuthorService.service.AuthorService;
import ru.example.cloudModuleTest.AutorService.CloudModuleTestAuthorService.model.Author;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("authors")
public class AuthorRestController {
    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(final AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     *
     * @return Author List
     */
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public List<Author> findAll() {
        return authorService.getAllAuthors();
    }

    /**
     *
     * @param id - author id
     * @return Author
     */
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> findAuthorById(final @PathVariable Long id) {
        return authorService.getAuthorById(id)
            .map(e -> ResponseEntity.status(HttpStatus.OK).body(e))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
