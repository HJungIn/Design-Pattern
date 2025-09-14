package com.example.visitor.hexagonal.domain.model;

import com.example.visitor.hexagonal.domain.visitor.Visitor;

import java.awt.*;

public class Book implements ItemElement {
    private String title;
    private int price;

    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public int getPrice() { return price; }
    public String getTitle() { return title; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}