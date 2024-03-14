package org.remiluka.features.drinking;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.remiluka.Main;
import org.remiluka.features.drinking.drinks.*;
import org.bukkit.util.Vector;

import java.util.*;

public class DrinkMaster {
    public static NamespacedKey DRINK_ID_KEY = new NamespacedKey(Main.getInstance(), "drink-id");

    private static final HashMap<String, Drink> _drinks = new HashMap<>();

    private static void registerDrink(Drink drink) {
        _drinks.put(drink.getId(), drink);
    }

    public static void registerDrinks() {
        registerDrink(new EightySix());
        registerDrink(new Heineken());
        registerDrink(new Remisterio());
    }

    public static Drink getDrinkById(String drinkId) {
        return _drinks.get(drinkId);
    }

    public static Set<String> getDrinkIds() {
        return Collections.unmodifiableSet(_drinks.keySet());
    }

    public static boolean isDrink(String drinkId) {
        return _drinks.containsKey(drinkId);
    }
}
