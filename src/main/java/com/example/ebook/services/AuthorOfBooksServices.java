package com.example.ebook.services;

import com.example.ebook.models.AuthorOfBooks;

import java.util.List;

public interface AuthorOfBooksServices {
    List<AuthorOfBooks> getList(String serach);
}
