package com.example.ebook.models;

import java.util.Objects;

public class AuthorOfBooks {

    String name;

    public AuthorOfBooks(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorOfBooks)) return false;
        AuthorOfBooks that = (AuthorOfBooks) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public String getNameForSPARQL(){
        return this.name.replaceAll(" ","_");
    }

}
