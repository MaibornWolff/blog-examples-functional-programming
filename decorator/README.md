# OOP Design Patterns in Funktionaler Programmierung
Als Schablone zur Lösung wiederkehrender Entwurfsprobleme haben sich in der objektorientierten Programmierung Design-Patterns etabliert. Diese Patterns beschreiben Objekte und deren Beziehungen mit dem Ziel ein Entwurfsproblem im OOP-Sinne möglichst elegant zu lösen. Da mittlerweile in vielen objektorientierten Sprachen auch funktionale Paradigmen einfließen, ist es an der Zeit zu beurteilen wie diese Lösungsschablonen funktional umgesetzt werden können. 

## Wie unterscheidet sich Funktionales Programmierung?
Die funktionale Programmierung hat unter anderem das Ziel durch
starke Abstraktion eine gute Wiederverwendung von Programm-Bausteinen zu ermöglichen. Die Grundlage hierfür sind Funktionen, die wie in der mathematischen Definition eine Beziehung zwischen einer Ausgangsmenge und einer Zielmenge beschreiben. Diese Funktionen können in der funktionalen Programmierung zusätzlich zu *herkömmlichen Daten* anderen Funktionen übergeben und von diesen wieder zurückgegeben werden. Dank dieser Flexibilität können Verarbeitungsketten durch die Komposition von Funktionen erstellt werden. Mit Hilfe dieser Grundlagen wollen wir im folgenden ein Entwurfsproblem funktional lösen.

## Das Beispiel
Wir stellen uns vor, wir sind Inhaber einer Pizzeria. Um uns von den Konkurrenten zu unterscheiden bieten wir unseren Kunden die Möglichkeit ihre Pizzen aus unterschiedlichsten Zutaten flexibel zusammenzustellen. Eine *Basis-Pizza* mit Tomaten und Käse kann nach Belieben mit einer Reihe weiterer Zutaten belegt werden. IT-unterstützt wollen wir die Beschreibung der Pizza erstellen und den Gesamtpreis berechnen. 

## Das Decorator Pattern
Für diesen Anwendungsfall eignet sich das Decorator Pattern
(vgl. [https://de.wikipedia.org/wiki/Decorator](https://de.wikipedia.org/wiki/Decorator)). Es dient dazu Objekte flexibel um Verhalten anzureichern ohne dazu eine Vielzahl von Unterklassen zu erstellen. Die objektorientierte Umsetzung dieses Beispiels kann unter (GIT-HUB) nachgelesen werden. 
Mit Hilfe der funktionalen Anteile von Java 8 lässt sich die Implementierung des Decorator Patterns etwas anders lösen.  
Wie im OOP-Fall definieren wir ein Interface für die `Pizza` und implementieren dieses für die `BasePizza` mit Tomaten und Käse.

```java
public interface Pizza {

    Double price();

    String description();
}
```

```java
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
```

Die Dekoratoren implementieren wir im Gegensatz zu OOP als statische Methoden (Funktionen), die eine Pizza entgegennehmen und diese angereichert um den Preis der Zutat und deren Beschreibung zurückgeben. Hier sieht man auch schon den größten Unterschied zu OOP: Dadurch, dass Funktionen nun *first class citizens* sind (d.h. sie können wie herkömmliche Daten Funktionen übergeben bzw. von diesen zurückgegeben werden), muss man dieses Verhalten nicht mehr über ein kompliziertes Objektgeflecht modellieren.

```java
public class Decorator {

    public static Pizza withHam(Pizza pizza) {
        return new Pizza() {

            @Override
            public Double price() {
                return pizza.price() + 0.5;
            }

            @Override
            public String description() {
                return pizza.description() + ", Salami";
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
```

Unsere Wunschpizza erhalten wir dann durch Funktions-Komposition der Dekoratoren und Anwendung dieser auf die *BasePizza*

```java
public class Main {

    public static void main(String... args) {

        Pizza basePizza = new BasePizza();
        Function<Pizza, Pizza> ham = 
            Decorator::withHam;
        Function<Pizza, Pizza> mushrooms = 
            Decorator::withMushrooms;
        
        Pizza pizzaRegina = 
            ham.compose(mushrooms).apply(basePizza);

        System.out.println(pizzaRegina.description());
        System.out.println("Price: " + pizzaRegina.price() + " €");

    }

}
```

## Abschluss
Wie man sieht, ist das Decorator Pattern durch die reichhaltigeren Möglichkeiten funktionaler Sprachen nicht mehr notwendig. Dies bekräftigt das Zitat von Rich Hickey Autor von Clojure „Patterns mean: I have run out of language“

