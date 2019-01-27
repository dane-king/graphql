package com.dking.library.config;

import com.dking.library.author.Author;
import com.dking.library.author.AuthorService;
import com.dking.library.book.Book;
import com.dking.library.book.BookService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

@Configuration
public class LibraryConfiguration {

    @Bean
    GraphQL getGraphQL(BookService bookService, AuthorService authorService) {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withResolverBuilders(
                        //Resolve by annotations
                        new AnnotatedResolverBuilder())
                .withOperationsFromSingleton(bookService)
                .withOperationsFromSingleton(authorService)
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();
        return GraphQL.newGraphQL(schema).build();
    }

    @Bean
    ApplicationRunner init(AuthorService authorService, BookService bookService) {
        return args -> {
            Stream.of("L for Dummies:John Jackson",
                    "Introduction to L:Sarah Silver",
                    "The L Programming Language:Nate Neville",
                    "The L Cookbook:Cara Clarion",
                    "Teach Yourself L in 30 days:Yvonne Young").forEach(str -> {
                Book book = new Book();
                String[] strArray = str.split(":");
                book.setTitle(strArray[0]);
                book.setDescription("new book");
                Author author = new Author();
                author.addBook(book);
                String[] names = strArray[1].split(" ");
                author.setName(names[0] + " " + names[1]);

                authorService.saveAuthor(author);
            });
            authorService.getAuthors().forEach(System.out::println);
            bookService.getBooks().forEach(System.out::println);
        };
    }

}
