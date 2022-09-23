package com.example.ebook.services.impl;

import com.example.ebook.models.ListBook;
import com.example.ebook.models.classes.ListBooksSPARQL;
import com.example.ebook.services.ListBooksServices;
import org.apache.jena.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListBooksServiceImpl implements ListBooksServices {

    public List<ListBook> getBooks(){
        List<ListBook> listBookList = new ArrayList<>();
        try(QueryExecution qexec = QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql",ListBooksSPARQL.query)) {
            System.out.println(qexec.getQuery());

            ResultSet result = qexec.execSelect();
            while (result.hasNext()){
                QuerySolution soln = result.nextSolution();
                String author = "";
                if(soln.get("?authorName") != null) {
                    author = soln.get("?authorName").toString();
                    //author =  author.substring(0,author.length()-3);
                }
                String genre = "";
                if(soln.get("?genreType") != null){
                    genre = soln.get("?genreType").toString();
                }
                String name = "";
                if(soln.get("?bookName") != null) {
                    name = soln.get("?bookName").toString();
                    //name = name.substring(0,name.length()-3);
                }

                listBookList.add(new ListBook(name,author,genre));
            }
        }
        return listBookList;
    }
}
