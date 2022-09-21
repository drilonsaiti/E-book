package com.example.ebook.services;

import com.example.ebook.models.DetailsAuthor;

import java.util.List;

public interface AuthorDetailsServices {
    List<DetailsAuthor> getAuthors(String search);
}
