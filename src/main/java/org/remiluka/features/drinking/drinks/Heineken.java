package org.remiluka.features.drinking.drinks;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class Heineken extends Drink {
    public Heineken() {
        super("heineken",
                Component.text("Heineken").color(TextColor.color(0x1D7E14)),
                Component.text("Sa fé dé degat 1?"),
                3.2d,
                33);
    }

    @Override
    public void applyEffects(Player player) {

    }
}
