package com.example.dailyquotes;

public class QuoteModel {
    private String text;
    private String author;

    public QuoteModel(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public String getText() { return text; }
    public String getAuthor() { return author; }
}