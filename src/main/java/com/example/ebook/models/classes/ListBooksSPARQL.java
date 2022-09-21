package com.example.ebook.models.classes;

public abstract class ListBooksSPARQL {

    public static String query =
            "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                    "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                    "prefix dbo: <http://dbpedia.org/ontology/>"+
                    "prefix dbr: <http://dbpedia.org/resource/>"+
                    "prefix dbp: <http://dbpedia.org/property/>"+
                    "SELECT ?genreName ?authorName ?bookName " +
                    "WHERE {" +
                    "  ?s rdf:type dbo:Book ." +
                    "  ?s dbp:genre ?genre." +
                    "  ?genre rdfs:label ?genres." +
                    "  ?s dbo:author ?author" +
                    "  OPTIONAL {" +
                    "    ?s rdfs:label ?desc FILTER (lang(?desc) = 'en') BIND (STR(?desc)  AS ?bookName). " +

                    "  }" +
                    "  OPTIONAL {" +
                    "    ?author rdfs:label ?authorlabel FILTER (lang(?authorlabel) = 'en') BIND (STR(?authorlabel)  AS ?authorName)." +
                    "  }" +
                    "FILTER (lang(?genres) = 'en')." +
                    "BIND (STR(?genres)  AS ?genreName). " +
                    "} ";

    public ListBooksSPARQL() {
    }


}
