package com.example.BookProject.controller;

import com.example.BookProject.entity.Author;
import com.example.BookProject.entity.Book;
import com.example.BookProject.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService authorRepos;

    @PostMapping("/author/create")
    public String createAuthor(@RequestParam("authorName") List<Book> authorName) {
        Author author = new Author();
        author.setBookAuthor(authorName);
        authorRepos.createBook(author);
        return "redirect:/author"; // Перенаправление на страницу со списком авторов
    }

    @GetMapping("/author")
    public String author(Map<String, Object> model) {
        Iterable<Author> authors = authorRepos.getAllBooks();
        model.put("authors", authors);
        return "author";
    }


    @GetMapping("/author/{id}")
    public String authorDetails(@PathVariable Integer id, Model model) {
        Author author = authorRepos.getBookById(id);
        model.addAttribute("author", author);;
        return "author";
    }
}