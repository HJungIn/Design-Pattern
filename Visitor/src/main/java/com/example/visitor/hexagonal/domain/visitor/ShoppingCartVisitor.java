package com.example.visitor.hexagonal.domain.visitor;

import com.example.visitor.hexagonal.domain.model.Book;
import com.example.visitor.hexagonal.domain.model.Fruit;

public class ShoppingCartVisitor implements Visitor {
    @Override
    public void visit(Book book) {
        System.out.println("Book: " + book.getTitle() + " costs " + book.getPrice());
    }

    @Override
    public void visit(Fruit fruit) {
        int cost = fruit.getPricePerKg() * fruit.getWeight();
        System.out.println("Fruit: " + fruit.getName() + " costs " + cost);
    }
}