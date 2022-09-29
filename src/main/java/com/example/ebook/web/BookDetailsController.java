package com.example.ebook.web;


import com.example.ebook.models.BookDetails;
import com.example.ebook.models.DetailsAuthor;
import com.example.ebook.services.BookDetailsServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookDetailsController {

    private final BookDetailsServices bookDetailsServices;

    public BookDetailsController(BookDetailsServices bookDetailsServices) {
        this.bookDetailsServices = bookDetailsServices;
    }

    @GetMapping("/book/{bookName}")
    public String getAuthor(@PathVariable String bookName, Model model){
        //String authorForSPARQL = author.replaceAll(" ","_");
        System.out.println(bookName);
        List<BookDetails> bookDetails = this.bookDetailsServices.getBookDetails(bookName).stream().distinct().collect(Collectors.toList());
        model.addAttribute("books",bookDetails);
        model.addAttribute("namelink",bookDetails.get(0).getBookName());
        return "book-details";
    }
}
