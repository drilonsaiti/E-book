package com.example.ebook.web;

import com.example.ebook.models.ListBook;
import com.example.ebook.services.ListBooksServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
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
        return findPagianted(null,1,model);
    }
    @GetMapping("/books/page/{pageNo}")
    public String findPagianted(@RequestParam(required = false)String search, @PathVariable(value = "pageNo") int pageNo, Model model){
        List<ListBook> books = new ArrayList<>();
        Page<ListBook> page = null;
        int pageSize = 20;
        page = this.booksServices.findPagianted(pageNo,pageSize,search);
        books = page.getContent();

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("search", search);
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
