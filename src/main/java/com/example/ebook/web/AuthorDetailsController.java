package com.example.ebook.web;

import com.example.ebook.models.AuthorOfBooks;
import com.example.ebook.models.DetailsAuthor;
import com.example.ebook.services.AuthorDetailsServices;
import com.example.ebook.services.AuthorOfBooksServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthorDetailsController {

    private final AuthorDetailsServices authorDetailsServices;
    private final AuthorOfBooksServices authorOfBooksServices;

    public AuthorDetailsController(AuthorDetailsServices authorDetailsServices, AuthorOfBooksServices authorOfBooksServices) {
        this.authorDetailsServices = authorDetailsServices;
        this.authorOfBooksServices = authorOfBooksServices;
    }

   /* public String getAuthor(@PathVariable String author, Model model){
        //String authorForSPARQL = author.replaceAll(" ","_");
        System.out.println(author);
        List<DetailsAuthor> detailsAuthorList = this.authorDetailsServices.getAuthors(author).stream().distinct().collect(Collectors.toList());
        model.addAttribute("authors",detailsAuthorList);

        return "author";
    }*/

    @GetMapping("/authors/{author}")
    public String getAuthor(@PathVariable String author,Model model){
        //String authorForSPARQL = author.replaceAll(" ","_");

        List<DetailsAuthor> detailsAuthorList = this.authorDetailsServices.getAuthors(author).stream().distinct().collect(Collectors.toList());
        List<AuthorOfBooks> authorOfBooks = this.authorOfBooksServices.getList(author).stream().distinct().toList();
        model.addAttribute("authors",detailsAuthorList);
        model.addAttribute("books",authorOfBooks);

        return "author";
    }
}
