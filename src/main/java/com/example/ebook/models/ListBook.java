package com.example.ebook.models;

import java.util.Objects;

public class ListBook {

    public String bookName;
    public String authorLabel;
    public String genre;

    public ListBook(String bookName, String authorLabel, String genre) {
        this.bookName = bookName;
        this.authorLabel = authorLabel;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListBook)) return false;
        ListBook that = (ListBook) o;
        return bookName.equals(that.bookName);
    }

    public String authorLabel(){
        return "by " + this.authorLabel;
    }
    public String getAuthorForSPARQL(){
        return this.authorLabel.replaceAll(" ","_");
    }

    public String getNameForSPARQL(){
        return this.bookName.replaceAll(" ","_");
    }


    @Override
    public int hashCode() {
        return Objects.hash(bookName);
    }
}
