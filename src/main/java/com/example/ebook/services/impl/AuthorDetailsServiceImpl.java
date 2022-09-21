package com.example.ebook.services.impl;

import com.example.ebook.models.DetailsAuthor;
import com.example.ebook.models.classes.AuthorDetailsSPARQL;
import com.example.ebook.services.AuthorDetailsServices;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorDetailsServiceImpl implements AuthorDetailsServices {
    @Override
    public List<DetailsAuthor> getAuthors(String search) {
        List<DetailsAuthor> detailsAuthors = new ArrayList<>();
        System.out.println("IN FUNC " + search);
        AuthorDetailsSPARQL sparql = new AuthorDetailsSPARQL(search);
        int i = 0;
        try(QueryExecution qexec = QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql", sparql.getQuery())) {
            System.out.println(qexec.getQuery());
            ResultSet result = qexec.execSelect();
            while (result.hasNext()){
                i++;
                QuerySolution soln = result.nextSolution();
                System.out.println("SOL " + soln.toString());
                String name = "";
                String abstracts = "";
                String birhtDate = "";
                String deathDate = "";
                String linkPhoto = "";
                String birthPlace = "";
                float lat = 0;
                float longt = 0;

                if (soln.get("?authorlabel") != null){
                    name = soln.get("?authorlabel").toString();
                }
                if(soln.get("?birthDate") != null){
                    birhtDate = soln.get("?birthDate").toString();
                }
                if(soln.get("?linkPhoto") != null){
                    linkPhoto = soln.get("?linkPhoto").toString();
                }
                if(soln.get("?deathDate") != null){
                    deathDate = soln.get("?deathDate").toString();
                }
                if(soln.get("?birthCity") != null){
                    birthPlace = soln.get("?birthCity").toString();
                }else{
                    birthPlace = soln.get("?birthPlace").toString();
                }
                if(soln.get("?lat") != null){
                    lat = Float.parseFloat(soln.get("?lat").toString().substring(0,soln.get("?lat").toString().indexOf("^")));
                }
                if(soln.get("?long") != null){
                    longt = Float.parseFloat(soln.get("?long").toString().substring(0,soln.get("?long").toString().indexOf("^")));
                }

                if(soln.get("?abt") != null){
                    abstracts = soln.get("?abt").toString();
                }

                detailsAuthors.add(new DetailsAuthor(name,abstracts,birhtDate,deathDate,linkPhoto,birthPlace,lat,longt));
            if(i == 1){
                break;
            }
            }
        }
        detailsAuthors.forEach(System.out::println);
        return detailsAuthors;
    }
}
