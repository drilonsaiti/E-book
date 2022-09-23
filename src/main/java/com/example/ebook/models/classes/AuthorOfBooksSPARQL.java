package com.example.ebook.models.classes;

public class AuthorOfBooksSPARQL {

    public static String getQuery(String search){
        String dbr = "<http://dbpedia.org/resource/"+search+">";

        String query =
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
        "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "prefix dbo: <http://dbpedia.org/ontology/>"+
                "prefix dbr: <http://dbpedia.org/resource/>"+
                "prefix dbp: <http://dbpedia.org/property/>"+
                "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"+
                "SELECT  ?book " +
                "WHERE" +
                "  { " +
                " ?books  dbo:author "+dbr+";" +
                "                   rdfs:label ?bookName." +
                "FILTER(lang(?bookName) = 'en')." +
                "BIND(STR(?bookName) as ?book)" +
                "  }";

        return query;
    }
}
