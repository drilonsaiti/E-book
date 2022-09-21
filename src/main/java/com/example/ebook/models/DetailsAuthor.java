package com.example.ebook.models;

import java.util.Objects;

public class DetailsAuthor {

    String name;
    String abstracts;
    String birhtDate;
    String deathDate;
    String linkPhoto;
    String birthPlace;
    float lat;
    float longt;

    public DetailsAuthor(String name, String abstracts, String birhtDate, String deathDate, String linkPhoto, String birthPlace, float lat, float longt) {
        this.name = name;
        this.abstracts = abstracts;
        this.birhtDate = birhtDate;
        this.deathDate = deathDate;
        this.linkPhoto = linkPhoto;
        this.birthPlace = birthPlace;
        this.lat = lat;
        this.longt = longt;
    }

    public String getName() {
        return name;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public String getBirhtDate() {
        return birhtDate;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public String getLinkPhoto() {
        return linkPhoto;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public float getLat() {
        return lat;
    }

    public float getLongt() {
        return longt;
    }

    @Override
    public String toString() {
        return "DetailsAuthor{" +
                "name='" + name + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", birhtDate='" + birhtDate + '\'' +
                ", deathDate='" + deathDate + '\'' +
                ", linkPhoto='" + linkPhoto + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", lat=" + lat +
                ", longt=" + longt +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetailsAuthor)) return false;
        DetailsAuthor that = (DetailsAuthor) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
