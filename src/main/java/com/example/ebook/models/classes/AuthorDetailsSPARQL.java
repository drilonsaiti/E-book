package com.example.ebook.models.classes;

public class AuthorDetailsSPARQL {

    public String userSearch;

    public AuthorDetailsSPARQL(String userSearch) {
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
                "SELECT ?abt ?long ?lat ?birthCity ?birthPlace ?deathDate ?linkPhoto ?birthDate ?authorlabel  " +
                "WHERE {" +


                "dbr:"+this.userSearch+ " rdfs:label ?authorlabel. " +
                "OPTIONAL{" +
                "dbr:"+this.userSearch+ " dbo:birthDate ?birthDate." +
                "}" +
                "OPTIONAL{" +
                "dbr:"+this.userSearch+ " dbo:thumbnail  ?linkPhoto." +
                "}" +
                "OPTIONAL{" +
                "dbr:"+this.userSearch+   " dbo:deathDate ?deathDate." +
                "}" +
               "OPTIONAL{     " +
                "dbr:"+this.userSearch+
                "              dbp:birthPlace  ?birthPlace" +
                "}"+
                "OPTIONAL{" +
                "                            ?birthPlace rdfs:label ?birthPlaceName;" +
                "                                              geo:lat ?lat;" +
                "                                             geo:long ?long." +
                "FILTER (lang(?birthPlaceName) = 'en')" +
                "}"+
                "dbr:"+this.userSearch+   " dbo:abstract ?abstract." +
                "FILTER(lang(?authorlabel) = 'en')" +
                "FILTER(lang(?abstract) = 'en')"+
                "BIND (STR(?abstract)  AS ?abt). "+
                "BIND (STR(?birthPlaceName)  AS ?birthCity). "+
                "}";;
        return query;
    }


}
