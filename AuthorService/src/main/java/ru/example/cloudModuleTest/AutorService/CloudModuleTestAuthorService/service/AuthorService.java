package ru.example.cloudModuleTest.AutorService.CloudModuleTestAuthorService.service;

import org.springframework.stereotype.Service;
import ru.example.cloudModuleTest.AutorService.CloudModuleTestAuthorService.model.Author;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("MagicNumber")
@Service
public class AuthorService {
    private final List<Author> authors = Stream.of(
        new Author(1L, "Author1"),
        new Author(2L, "Author2"),
        new Author(3L, "Author3"),
        new Author(4L, "Author4"),
        new Author(5L, "Author5"),
        new Author(6L, "Author6"),
        new Author(7L, "Author7")
    ).toList();

    /**
     *
     * @return author list
     */
    public List<Author> getAllAuthors() {
        return authors;
    }

    /**\
     *
     * @param id - author id
     * @return author
     */
    public Optional<Author> getAuthorById(final Long id) {
        return authors.stream().filter(e -> e.getId().equals(id)).findAny();
    }
}
