package com.example.ebook.models.classes;

public abstract class ListBooksSPARQL {

    public static String query =
            "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                    "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                    "prefix dbo: <http://dbpedia.org/ontology/>"+
                    "prefix dbr: <http://dbpedia.org/resource/>"+
                    "prefix dbp: <http://dbpedia.org/property/>"+
                    "SELECT   ?bookName ?authorName ?genreType" +
                    "                            WHERE {" +
                    "                            ?s rdf:type dbo:Book ." +
                    "                             ?s dbp:genre ?genre." +
                    "                            ?genre    rdfs:label ?genreName." +
                    "                            ?s dbo:author ?author" +
                    "                            OPTIONAL {" +
                    "                            ?s rdfs:label ?desc FILTER (lang(?desc) = 'en') BIND(STR(?desc) as ?bookName)." +
                    "                            }" +
                    "                            OPTIONAL {" +
                    "                            ?author rdfs:label ?authorlabel FILTER (lang(?authorlabel) = 'en') BIND(STR(?authorlabel) as ?authorName)." +
                    "                            }" +
                    "                          FILTER (lang(?genreName) = 'en')." +
                    "                          BIND(STR(?genreName) as ?genreType)." +
                    "                            }";

    public ListBooksSPARQL() {
    }


}
