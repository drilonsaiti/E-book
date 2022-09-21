package com.example.ebook.models;

import java.util.Objects;

public class ListBook {

    public String link;
    public String bookName;
    public String authorLabel;

    public ListBook(String link, String bookName, String authorLabel) {
        this.link = link;
        this.bookName = bookName;
        this.authorLabel = authorLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListBook)) return false;
        ListBook that = (ListBook) o;
        return bookName.equals(that.bookName);
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
