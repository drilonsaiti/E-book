package com.example.ebook.services.impl;

import com.example.ebook.models.BookDetails;
import com.example.ebook.models.DetailsAuthor;
import com.example.ebook.models.classes.AuthorDetailsSPARQL;
import com.example.ebook.models.classes.BookDetailsSPARQL;
import com.example.ebook.services.BookDetailsServices;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookDetailsServiceImpl implements BookDetailsServices {
    @Override
    public List<BookDetails> getBookDetails(String search) {
        List<BookDetails> bookDetails = new ArrayList<>();
        System.out.println("IN FUNC " + search);
        BookDetailsSPARQL sparql = new BookDetailsSPARQL(search);
        int i = 0;
        try(QueryExecution qexec = QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql", sparql.getQuery())) {
            System.out.println(qexec.getQuery());
            ResultSet result = qexec.execSelect();
            while (result.hasNext()){
                i++;
                QuerySolution soln = result.nextSolution();
                System.out.println("SOL " + soln.toString());
                String bookName = "";
                String authorName = "";
                String abstracts = "";
                String genreType ="";
                int numPages = 0;
                String relaseDate = "";

                if (soln.get("?bookName") != null){
                    bookName = soln.get("?bookName").toString();
                }
                if(soln.get("?genreType") != null){
                    genreType = soln.get("?genreType").toString();
                }
                if(soln.get("?numPages") != null){
                    numPages = Integer.parseInt(soln.get("?numPages").toString().substring(0,soln.get("?numPages").toString().indexOf("^")));
                }
                if(soln.get("?relaseDate") != null){
                    relaseDate = soln.get("?relaseDate").toString();
                }
                if(soln.get("?authorlabel") != null){
                    authorName = soln.get("?authorlabel").toString();
                }
                if(soln.get("?abstract") != null){
                    abstracts = soln.get("?abstract").toString();
                }
                bookDetails.add(new BookDetails(bookName,authorName,abstracts,genreType,numPages,relaseDate));

                if(i == 1){
                    break;
                }
            }
        }
        bookDetails.forEach(System.out::println);
        return bookDetails;
    }
}
