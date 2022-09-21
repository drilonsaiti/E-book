package com.example.ebook.models;

import java.util.Objects;

public class BookDetails {

    String bookName;
    String authorName;
    String abstracts;
    String genreType;
    int numPages;
    String relaseDate;

    public BookDetails(String bookName, String authorName, String abstracts, String genreType, int numPages, String relaseDate) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.abstracts = abstracts;
        this.genreType = genreType;
        this.numPages = numPages;
        this.relaseDate = relaseDate;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public String getGenreType() {
        return genreType;
    }

    public int getNumPages() {
        return numPages;
    }

    public String getRelaseDate() {
        return relaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDetails)) return false;
        BookDetails that = (BookDetails) o;
        return bookName.equals(that.bookName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName);
    }
}
