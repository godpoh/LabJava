/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoriodosjava;

import java.util.ArrayList;
import java.util.HashMap;

public class Pizza {

    private String name;

    private ArrayList<String> ingredients;
    public static HashMap<String, Double> sizes = new HashMap<>();
    public static HashMap<String, Double> additionalItems = new HashMap<>();
    public static HashMap<String, Double> pastaTypes = new HashMap<>();
    public static ArrayList<Pizza> pizzaTypes = new ArrayList<>();

    static {
        sizes.put("Mediana", 5500.0);
        sizes.put("Grande", 6500.0);
        sizes.put("Familiar", 7500.0);

        additionalItems.put("Refresco", 1500.0);
        additionalItems.put("Postre", 1500.0);
        additionalItems.put("Pan de Ajo", 1000.0);
        additionalItems.put("Ninguno", 0.0);

        pastaTypes.put("Delgada", 0.10);
        pastaTypes.put("Gruesa", 0.05);

        pizzaTypes.add(new Pizza("Pepperoni", "Queso", "Pepperoni"));
        pizzaTypes.add(new Pizza("Hawaiana", "Queso", "Jamon", "Piña"));
        pizzaTypes.add(new Pizza("Suprema", "Queso", "Jamon", "Pepperoni", "Cebolla", "Chile dulce", "Aceitunas"));
        pizzaTypes.add(new Pizza("Napolitana", "Tomate", "Ajo", "Mozzarella", "Albahaca"));
    }

    public Pizza(String name, String... ingredients) {
        this.name = name;
        this.ingredients = new ArrayList<>();
        for (String ingredient : ingredients) {
            this.ingredients.add(ingredient);
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public static HashMap<String, Double> getSizes() {
        return sizes;
    }

    public static HashMap<String, Double> getAdditionalItems() {
        return additionalItems;
    }

    public static HashMap<String, Double> getPastaTypes() {
        return pastaTypes;
    }

    public static ArrayList<Pizza> getPizzaTypes() {
        return pizzaTypes;
    }
}
