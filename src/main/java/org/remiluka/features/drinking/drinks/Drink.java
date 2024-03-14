package org.remiluka.features.drinking.drinks;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public abstract class Drink {
    private final double _alcoolLevel;
    private final int _drinkSize;
    private final String _id;
    private final Component _name;
    private final Component _description;

    public Drink(String id, Component name, Component description, double alcoolLevel, int drinkSize) {
        _id = id;
        _name = name;
        _description = description;
        _alcoolLevel = alcoolLevel;
        _drinkSize = drinkSize;
    }

    public double getAlcoolLevel() {
        return _alcoolLevel;
    }
    public double getDrinkSize() {
        return _drinkSize;
    }

    public String getId() {
        return _id;
    }

    public Component getName() {
        return _name;
    }
    public Component getDescription() {
        return _description;
    }

    public abstract void applyEffects(Player player);
}
