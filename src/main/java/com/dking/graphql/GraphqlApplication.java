package com.dking.graphql;

import com.dking.graphql.author.Author;
import com.dking.graphql.author.AuthorService;
import com.dking.graphql.book.Book;
import com.dking.graphql.book.BookService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@SpringBootApplication
public class GraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlApplication.class, args);
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
				String [] strArray =str.split(":");
				book.setTitle(strArray[0]);
				book.setDescription("new book");
				Author author=new Author();
				author.addBook(book);
				String[] names=strArray[1].split(" ");
				author.setName(names[0] + " " + names[1]);

				authorService.saveAuthor(author);
			});
			authorService.getAuthors().forEach(System.out::println);
			bookService.getBooks().forEach(System.out::println);
		};
	}
}


