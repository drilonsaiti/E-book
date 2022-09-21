package com.example.ebook.services;

import com.example.ebook.models.BookDetails;

import java.util.List;

public interface BookDetailsServices {
    List<BookDetails> getBookDetails(String search);
}
