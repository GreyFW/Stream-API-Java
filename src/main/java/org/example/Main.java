package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Collections.*;

public class Main {
    public static void main(String[] args) {
        final String path = "src\\main\\resources\\books.json";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Visitor> visitors;
        try (FileReader reader = new FileReader(path)) {
            Type visitorListType = new TypeToken<List<Visitor>>() {
            }.getType();
            visitors = gson.fromJson(reader, visitorListType);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
            return;
        }

        System.out.println("\n\tЗАДАНИЕ 1:\nВывести список посетителей и их кол-во.\n");
        long visitorsCount = visitors.size();
        for (Visitor visitor : visitors) {
            System.out.println(visitor + "\n");
        }
        System.out.println("Кол-во посетителей: " + visitorsCount);

        System.out.println("\n\tЗАДАНИЕ 2:\nСписок и кол-во книг из избранного без повт.\n");


    }
}