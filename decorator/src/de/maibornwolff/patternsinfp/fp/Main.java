package de.maibornwolff.patternsinfp.fp;

import java.util.function.Function;

public class Main {

    public static void main(String... args) {

        Pizza basePizza = new BasePizza();
        Function<Pizza, Pizza> ham = Decorator::withHam;
        Function<Pizza, Pizza> mushrooms = Decorator::withMushrooms;
        Pizza pizzaRegina = ham.compose(mushrooms).apply(basePizza);

        System.out.println(pizzaRegina.description());
        System.out.println("Preis: " + pizzaRegina.price() + " €");

    }

}
