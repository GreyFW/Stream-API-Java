package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String author;
    private String heading;
    private short publishingYear;
    private String isbn;
    private  String publisher;
}
