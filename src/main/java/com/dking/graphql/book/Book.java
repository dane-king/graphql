package com.dking.graphql.book;

import com.dking.graphql.author.Author;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
        private Long id;
        private String title;
        private String description;
        private Set<Author> authors=new HashSet<>();

        public Book() {

        }

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
        @JoinTable(name = "book_author",
                joinColumns = @JoinColumn(name = "book_id"),
                inverseJoinColumns = @JoinColumn(name = "author_id"))
        public Set<Author> getAuthors() {
            return authors;
        }

        public void setAuthors(Set<Author> authors) {
            this.authors = authors;
        }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
