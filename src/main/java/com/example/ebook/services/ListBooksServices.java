package com.example.ebook.services;

import com.example.ebook.models.ListBook;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ListBooksServices {
    List<ListBook> getBooks();
    Page<ListBook> findPagianted(int pageNo, int pageSize, String search);
}
