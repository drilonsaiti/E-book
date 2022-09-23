package com.example.ebook.services.impl;

import com.example.ebook.models.AuthorOfBooks;
import com.example.ebook.models.ListBook;
import com.example.ebook.models.classes.AuthorOfBooksSPARQL;
import com.example.ebook.models.classes.ListBooksSPARQL;
import com.example.ebook.services.AuthorOfBooksServices;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorOfBooksServiceImpl implements AuthorOfBooksServices {

    @Override
    public List<AuthorOfBooks> getList(String serach) {
        List<AuthorOfBooks> authorOfBooks = new ArrayList<>();
        try(QueryExecution qexec = QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql", AuthorOfBooksSPARQL.getQuery(serach))) {
            ResultSet result = qexec.execSelect();
            while (result.hasNext()){
                QuerySolution soln = result.nextSolution();
                String name = "";
                if(soln.get("?book") != null) {
                    name = soln.get("?book").toString();
                    //name = name.substring(0,name.length()-3);
                }

                authorOfBooks.add(new AuthorOfBooks(name));
            }
        }
        return authorOfBooks;
    }
}
