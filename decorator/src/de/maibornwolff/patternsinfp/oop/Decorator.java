package de.maibornwolff.patternsinfp.oop;


public abstract class Decorator implements Pizza {

    private Pizza myPizza;

    public Decorator(Pizza myPizza) {
        this.myPizza = myPizza;
    }

    @Override
    public Double price() {
        return myPizza.price();
    }

    @Override
    public String description() {
        return myPizza.description();
    }




}
