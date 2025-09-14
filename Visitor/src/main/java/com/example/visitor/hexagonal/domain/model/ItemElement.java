package com.example.visitor.hexagonal.domain.model;


import com.example.visitor.hexagonal.domain.visitor.Visitor;

public interface ItemElement  {
    void accept(Visitor visitor);
}
