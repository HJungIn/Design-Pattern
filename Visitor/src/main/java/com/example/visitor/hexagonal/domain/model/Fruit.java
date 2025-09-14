package com.example.visitor.hexagonal.domain.model;

import com.example.visitor.hexagonal.domain.visitor.Visitor;

import java.awt.*;

public class Fruit implements ItemElement {
    private String name;
    private int pricePerKg;
    private int weight;

    public Fruit(String name, int pricePerKg, int weight) {
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.weight = weight;
    }

    public int getPricePerKg() { return pricePerKg; }
    public int getWeight() { return weight; }
    public String getName() { return name; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}