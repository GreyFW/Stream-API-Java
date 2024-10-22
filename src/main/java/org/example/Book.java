package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String name;
    private String author;
    private short publishingYear;
    private String isbn;
    private  String publisher;

    @Override
    public String toString() {
        return ("\n\tНазвание:" + name +
                "\n\tАвтор: " + author +
                "\n\tГод публ: " + publishingYear +
                "\n\tISBN: " + isbn +
                "\n\tИздатель: " + publisher + "\n");
    }
}
