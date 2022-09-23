package com.example.ebook.services.impl;

import com.example.ebook.models.ListBook;
import com.example.ebook.models.classes.ListBooksSPARQL;
import com.example.ebook.services.ListBooksServices;
import org.apache.jena.query.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Page<ListBook> findPagianted(int pageNo, int pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        List<ListBook> listBooks = new ArrayList<>();
        if (search != null){
            listBooks = this.getBooks().stream()
                    .filter(b -> b.bookName.contains(search) ||
                            b.authorLabel.contains(search) || b.genre.contains(search)).distinct().toList();
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), listBooks.size());
            return new PageImpl<ListBook>(listBooks.subList(start,end),pageable,listBooks.size());
        }
            listBooks = this.getBooks().stream().distinct().collect(Collectors.toList());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), listBooks.size());
        return new PageImpl<ListBook>(listBooks.subList(start,end),pageable,listBooks.size());
    }


}
