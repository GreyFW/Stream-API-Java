package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        final String path = "src\\main\\resources\\books.json";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Visitor> visitors;
        try (FileReader reader = new FileReader(path)) {
            Type visitorListType = new TypeToken<List<Visitor>>(){}.getType();
            visitors = gson.fromJson(reader, visitorListType);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
            return;
        }

        List<Visitor> allVisitors = visitors;
        // дубль на всякий случай



        System.out.println("\n\tЗАДАНИЕ 1:\nВывести список посетителей и их кол-во.");
        long visitorsCount = visitors.size();
        for (Visitor visitor : visitors) {
            System.out.println(visitor + "\n");
        }
        System.out.println("Кол-во посетителей: " + visitorsCount);



        System.out.println("\n\tЗАДАНИЕ 2:\nСписок и кол-во книг из избранного без повт.");
        Set<Book> books;
        books = visitors
                .stream() // закидываем всё в поток
                .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                // вытаскиваем список книг из каждого элемента потока (посетителя)
                .collect(Collectors.toSet()); // преобразуем результат потока в мн-во

        System.out.println(books);
        System.out.println("Кол-во книг = " + books.size());




        System.out.println("\n\tЗАДАНИЕ 3: \nОтсортировать по году издания и вывести список книг.\n");
        Set<Book> byYearBooks = books;

        byYearBooks
                .stream()
                .sorted(Comparator.comparingInt(Book::getPublishingYear))
                .collect(Collectors.toList());

        System.out.println(byYearBooks);



        System.out.println("\n\tЗАДАНИЕ 4: \nПроверить, есть ли у кого-то в избранном книга автора Jane Austen.");
        boolean janeAustenBook = books.stream()
                .anyMatch(book -> "Jane Austen".equals(book.getAuthor()));
        // метод возвращает true/false и останавливает работу при первом же совпадении.

        if (janeAustenBook) {
            System.out.println("Да.");
        } else System.out.println("No");



        System.out.println("\n\tЗАДАНИЕ 5: \nМакс. число добавленных в избранное книг");
        Set<Integer> countOfBooks;
        countOfBooks = allVisitors
                .stream()
                .map(visitor -> visitor.getFavoriteBooks().size()).collect(Collectors.toSet());

        System.out.print(Collections.max(countOfBooks));



        System.out.println("\n\tЗАДАНИЕ 6: \nSMS-сообщения \n");
        List<Integer> booksCount = allVisitors.stream()
                .map(visitor -> visitor.getFavoriteBooks().size()).collect(Collectors.toList());

        int sum = booksCount.stream()
                .mapToInt(Integer::intValue).sum();
        double averageBooksCount = (double) sum / booksCount.size();


        allVisitors.stream()
                .filter(visitor -> visitor.isSubscribed())
                .forEach(visitor -> {
                    int favBooksCount = visitor.getFavoriteBooks().size();
                    Sms sms;
                    if (favBooksCount > averageBooksCount) {
                        sms = new Sms("you're a bookworm", visitor.getPhone());
                        System.out.println(sms);
                    } else if (favBooksCount < averageBooksCount) {
                        sms = new Sms("read more", visitor.getPhone());
                        System.out.println(sms);
                    } else {
                        sms = new Sms("fine", visitor.getPhone());
                        System.out.println(sms);
                    }
                });
    }
}