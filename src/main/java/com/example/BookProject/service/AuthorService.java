package com.example.BookProject.service;

import com.example.BookProject.entity.Author;
import com.example.BookProject.repos.AuthorRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthorService {


    @Autowired
    private AuthorRepos authorRepos;

    public Author getBookById(int id) {
        return authorRepos.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));
    }
    public Author createBook(Author author) {
        return authorRepos.save(author);


    }
    public Iterable<Author> getAllBooks() {
        return authorRepos.findAll();
    }
}
