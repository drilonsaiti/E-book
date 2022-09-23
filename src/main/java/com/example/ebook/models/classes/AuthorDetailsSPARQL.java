package com.example.ebook.models.classes;

public class AuthorDetailsSPARQL {

    public String userSearch;

    public AuthorDetailsSPARQL(String userSearch) {
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
                "SELECT ?abt ?long ?lat ?birthCity ?birthPlace ?deathDate ?linkPhoto ?birthDate ?authorlabel  " +
                "WHERE {" +


                dbr+ " rdfs:label ?authorName. " +
                "OPTIONAL{" +
                dbr+ " dbo:birthDate ?birthDate." +
                "}" +
                "OPTIONAL{" +
                dbr+ " dbo:thumbnail  ?linkPhoto." +
                "}" +
                "OPTIONAL{" +
                dbr+   " dbo:deathDate ?deathDate." +
                "}" +
               "OPTIONAL{     " +
                dbr+
                "              dbp:birthPlace  ?birthPlace" +
                "}"+
                "OPTIONAL{" +
                "                            ?birthPlace rdfs:label ?birthPlaceName;" +
                "                                              geo:lat ?lat;" +
                "                                             geo:long ?long." +
                "FILTER (lang(?birthPlaceName) = 'en')" +
                "}"+
                dbr+   " dbo:abstract ?abstract." +

                "FILTER(lang(?abstract) = 'en')"+
                "FILTER(lang(?authorName) = 'en')"+
                "BIND(STR(?authorName) as ?authorlabel)"+
                "BIND (STR(?abstract)  AS ?abt). "+
                "BIND (STR(?birthPlaceName)  AS ?birthCity). "+
                "}";;
        return query;
    }


}
