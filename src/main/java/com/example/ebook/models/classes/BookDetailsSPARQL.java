package com.example.ebook.models.classes;

public class BookDetailsSPARQL {

    public String userSearch;

    public BookDetailsSPARQL(String userSearch) {
        this.userSearch = userSearch;
    }

    public String getQuery() {
        System.out.println("in class " + this.userSearch);
        String dbr = "<http://dbpedia.org/resource/"+this.userSearch+">";
        String query =  "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "prefix dbo: <http://dbpedia.org/ontology/>"+
                "prefix dbr: <http://dbpedia.org/resource/>"+
                "prefix dbp: <http://dbpedia.org/property/>"+
                "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"+
                "SELECT DISTINCT ?bookName ?genreType ?numPages ?relaseDate ?authorlabel ?abstract " +
                "WHERE { "+
                dbr + " rdfs:label ?name."+
                dbr +" dbp:genre ?genre."+
                " ?genre rdfs:label ?genreName."+
                dbr + " dbo:abstract ?abt;"+
                "dbo:author ?author."+
                "OPTIONAL{"+
                dbr +" dbo:numberOfPages ?numPages."+
                "}"+
                " OPTIONAL {" +
                "?author rdfs:label ?authorName FILTER (lang(?authorName) = 'en')." +
                "}"+
                "OPTIONAL{" +
                dbr + " dbp:releaseDate ?rlsDate." +
                "}" +
                "OPTIONAL{" +
                dbr + " dbo:publicationDate ?rlsDate." +
                "}"+
                "FILTER(lang(?genreName) = 'en')"+
                "FILTER(lang(?name) = 'en')"+
                "FILTER(lang(?abt) = 'en')."+
                " BIND (STR(?genreName)  AS ?genreType). "+
                " BIND (STR(?name)  AS ?bookName). "+

                "BIND (STR(?rlsDate)  AS ?relaseDate). "+
                "BIND (STR(?abt)  AS ?abstract). "+
                "BIND (STR(?authorName)  AS ?authorlabel). "+
                "}";

        return query;
    }

}
