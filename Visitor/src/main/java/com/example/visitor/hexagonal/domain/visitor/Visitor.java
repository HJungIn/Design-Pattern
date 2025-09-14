package com.example.visitor.hexagonal.domain.visitor;

import com.example.visitor.hexagonal.domain.model.Book;
import com.example.visitor.hexagonal.domain.model.Fruit;

public interface Visitor {
    void visit(Book book);
    void visit(Fruit fruit);
}
