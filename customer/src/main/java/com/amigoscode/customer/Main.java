package com.amigoscode.customer;

public class Main {
    public static void main(String[] args) {
        lower("Amigoscode", "bar");
        lower("Jamila", "bar");
    }

    static void lower(String input, String foo) {
        System.out.println(input.toLowerCase());
    }
}
