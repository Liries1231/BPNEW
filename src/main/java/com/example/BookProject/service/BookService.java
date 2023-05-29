package com.example.BookProject.service;

import com.example.BookProject.entity.Author;
import com.example.BookProject.entity.Book;
import com.example.BookProject.repos.BookRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    private BookRepos bookRepos;


    public BookRepos getBookRepos() {
        return bookRepos;
    }

    public void setBookRepos(BookRepos bookRepos) {
        this.bookRepos = bookRepos;
    }


    public BookService(BookRepos bookRepos) {
        this.bookRepos = bookRepos;
    }

    public List<Book> getAllBooks() {
        return bookRepos.findAll();
    }

    public Book createBook(Book book) {
        return bookRepos.save(book);

    }

    public Book getBookById(int id) {
        return bookRepos.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));
    }


    public void deleteById(int id) {
        bookRepos.deleteById(id);
    }

    public Book updateBook(int id, Book updatedBook) {
        Book existingBook = getBookById(id);

        existingBook.setName(updatedBook.getName());
        existingBook.setAuthor(updatedBook.getAuthor());
        // Обновление других полей книги

        return bookRepos.save(existingBook);

    }

    public Page<Book> getBooksPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepos.findAll(pageable);
    }
}
