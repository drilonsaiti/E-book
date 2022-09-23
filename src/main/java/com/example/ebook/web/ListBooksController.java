package com.example.ebook.web;

import com.example.ebook.models.ListBook;
import com.example.ebook.services.ListBooksServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ListBooksController {

    private final ListBooksServices booksServices;

    public ListBooksController(ListBooksServices booksServices) {
        this.booksServices = booksServices;
    }

    @GetMapping({"/","/books"})
    public String getBooks(Model model){
        List<ListBook> books = this.booksServices.getBooks().stream().distinct().collect(Collectors.toList());

        model.addAttribute("size",books.size());
        model.addAttribute("books",books);

        return "books";
    }

    @GetMapping("search-book")
    public String searchBook(Model model){
        List<ListBook> books = this.booksServices.getBooks().stream()
                .filter(b -> b.bookName.contains("Emma") || b.authorLabel.contains("Emma") || b.genre.contains("Emma")).toList();

        model.addAttribute("size",books.size());
        model.addAttribute("books",books);

        return "profile";
    }
}
