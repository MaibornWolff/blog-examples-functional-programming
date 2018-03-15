package de.maibornwolff.patternsinfp.oop;


public class MushroomsDecorator extends Decorator{


    public MushroomsDecorator(Pizza myPizza) {
        super(myPizza);
    }


    @Override
    public Double price() {
        return super.price() + 0.7;
    }

    @Override
    public String description() {
        return super.description() + ", Pilze";
    }
}
