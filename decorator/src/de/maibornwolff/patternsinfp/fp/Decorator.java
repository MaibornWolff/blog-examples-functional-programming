package de.maibornwolff.patternsinfp.fp;

public class Decorator {

    public static Pizza withHam(Pizza pizza) {
        return new Pizza() {

            @Override
            public Double price() {
                return pizza.price() + 0.5;
            }

            @Override
            public String description() {
                return pizza.description() + ", Schinken";
            }
        };
    }

    public static Pizza withMushrooms(Pizza pizza) {
        return new Pizza() {

            @Override
            public Double price() {
                return pizza.price() + 0.7;
            }

            @Override
            public String description() {
                return pizza.description() + ", Pilzen";
            }
        };
    }
}
