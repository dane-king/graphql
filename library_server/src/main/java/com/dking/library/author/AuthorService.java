package com.dking.library.author;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GraphQLQuery
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @GraphQLQuery
    public Optional<Author> getAuthorById(@GraphQLArgument(name = "id") Long id) {
        return authorRepository.findById(id);
    }

    @GraphQLQuery
    public Author saveAuthor(@GraphQLContext Author author) {
        return authorRepository.save(author);
    }

    @GraphQLQuery
    public void deleteAuthor(@GraphQLArgument(name = "id") Long id) {
        authorRepository.deleteById(id);
    }
}
