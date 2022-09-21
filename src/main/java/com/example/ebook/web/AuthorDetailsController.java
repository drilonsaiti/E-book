package com.example.ebook.web;

import com.example.ebook.models.DetailsAuthor;
import com.example.ebook.services.AuthorDetailsServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthorDetailsController {

    private final AuthorDetailsServices authorDetailsServices;

    public AuthorDetailsController(AuthorDetailsServices authorDetailsServices) {
        this.authorDetailsServices = authorDetailsServices;
    }

    @GetMapping("/authors/{author}")
    public String getAuthor(@PathVariable String author, Model model){
        //String authorForSPARQL = author.replaceAll(" ","_");
        System.out.println(author);
        List<DetailsAuthor> detailsAuthorList = this.authorDetailsServices.getAuthors(author).stream().distinct().collect(Collectors.toList());
        model.addAttribute("authors",detailsAuthorList);

        return "author";
    }
}
