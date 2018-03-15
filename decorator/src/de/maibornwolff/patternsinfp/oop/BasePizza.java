package de.maibornwolff.patternsinfp.oop;

public class BasePizza implements Pizza {


    @Override
    public Double price() {
        return 5.0;
    }

    @Override
    public String description() {
        return "Pizza mit Tomaten, Käse";
    }
}
