package de.maibornwolff.patternsinfp.oop;

public class Main {

    public static void main(String... args) {

        Pizza pizzaRegina = new MushroomsDecorator(new HamDecorator(new BasePizza()));

        System.out.println(pizzaRegina.description());
        System.out.println("Preis: " + pizzaRegina.price() + " €");

    }

}
