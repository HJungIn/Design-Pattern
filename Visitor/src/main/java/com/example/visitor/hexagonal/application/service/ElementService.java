package com.example.visitor.hexagonal.application.service;

import com.example.visitor.hexagonal.domain.model.Book;
import com.example.visitor.hexagonal.domain.model.Fruit;
import com.example.visitor.hexagonal.domain.model.ItemElement;
import com.example.visitor.hexagonal.domain.visitor.ShoppingCartVisitor;
import com.example.visitor.hexagonal.domain.visitor.Visitor;

public class ElementService {
    public static void main(String[] args) {
        ItemElement[] items = new ItemElement[]{
                new Book("Design Patterns", 5000),
                new Fruit("Apple", 200, 3)
        };

        Visitor visitor = new ShoppingCartVisitor();
        for (ItemElement item : items) {
            item.accept(visitor);
        }
    }
}
