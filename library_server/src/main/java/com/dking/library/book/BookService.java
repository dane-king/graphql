package com.dking.library.book;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GraphQLQuery
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GraphQLQuery(name = "book")
    public Optional<Book> getBookById(@GraphQLArgument(name = "id") Long id) {
        return bookRepository.findById(id);
    }


    @GraphQLQuery
    public Book saveBook(@GraphQLContext Book book) {
        return bookRepository.save(book);
    }

    @GraphQLQuery
    public void deleteBook(@GraphQLArgument(name = "id") Long id) {
        bookRepository.deleteById(id);
    }
}
