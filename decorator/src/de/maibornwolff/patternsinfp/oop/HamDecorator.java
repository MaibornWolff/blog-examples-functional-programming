package de.maibornwolff.patternsinfp.oop;


public class HamDecorator extends Decorator {

    public HamDecorator(Pizza myPizza) {
        super(myPizza);
    }


    @Override
    public Double price() {
        return super.price() + 0.5;
    }

    @Override
    public String description() {
        return super.description() + ", Salami";
    }
}
