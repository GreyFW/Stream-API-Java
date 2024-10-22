package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Visitor {
    private String name;
    private String surname;
    private String phone;
    private List<Book> favoriteBooks;
    private boolean subscribed;

    @Override
    public String toString() {
        return ("Имя: " + name +
                "\nФамилия: " + surname +
                "\nТелефон: " + phone +
                "\n\t\tЛюбимые книги: " + favoriteBooks);
    }
}
