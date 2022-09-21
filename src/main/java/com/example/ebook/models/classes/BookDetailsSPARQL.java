package com.example.ebook.models.classes;

public class BookDetailsSPARQL {

    public String userSearch;

    public BookDetailsSPARQL(String userSearch) {
        this.userSearch = userSearch;
    }

    public String getQuery() {
        System.out.println("in class " + this.userSearch);
        String query =  "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "prefix dbo: <http://dbpedia.org/ontology/>"+
                "prefix dbr: <http://dbpedia.org/resource/>"+
                "prefix dbp: <http://dbpedia.org/property/>"+
                "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"+
                "SELECT DISTINCT ?bookName ?genreType ?numPages ?relaseDate ?authorlabel ?abstract " +
                "WHERE { "+
                "dbr:"+userSearch + " rdfs:label ?bookName."+
                "dbr:"+userSearch +" dbp:genre ?genre."+
                " ?genre rdfs:label ?genreName."+
                "dbr:"+userSearch +" dbo:numberOfPages ?numPages;"+
                "dbp:releaseDate ?rlsDate;"+
                "dbo:abstract ?abt;"+
                "dbo:author ?author."+
                " OPTIONAL {" +
                "?author rdfs:label ?authorlabel FILTER (lang(?authorlabel) = 'en')." +
                "}"+
                "FILTER(lang(?genreName) = 'en')"+
                "FILTER(lang(?bookName) = 'en')"+
                "FILTER(lang(?abt) = 'en')."+
                " BIND (STR(?genreName)  AS ?genreType). "+
                "BIND (STR(?rlsDate)  AS ?relaseDate). "+
                "BIND (STR(?abt)  AS ?abstract). "+
                "}";
        return query;
    }

}
