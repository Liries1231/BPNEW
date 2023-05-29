package com.example.BookProject.controller;

import com.example.BookProject.entity.Author;
import com.example.BookProject.entity.Book;
import com.example.BookProject.repos.AuthorRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class AuthorController {
    @Autowired
    private AuthorRepos authorRepos;


    @PostMapping("main")
    public String createBook(@RequestParam("authorId") Integer authorId, @ModelAttribute Book book) {
        Author author = authorRepos.findById(authorId).orElse(null);
        book.setAuthorId(author);
        return "main"; // Или другой URL для перенаправления
    }
    @GetMapping("/author")
    public String author(Map<String, Object> model) {
        Iterable<Author> author = authorRepos.findAll();
        model.put("name", author);
        return "author";
    }

    @GetMapping("/author/{id}")
    public String authorDetails(@PathVariable Integer id, Model model) {
        Optional<Author> author = authorRepos.findById(id);
        author.ifPresent(value -> model.addAttribute("author", value));
        return "author";
    }



}
