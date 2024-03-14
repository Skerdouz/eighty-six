package org.remiluka.features.drinking.drinks;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class EightySix extends Drink {
    public EightySix() {
        super("eighty_six",
                Component.text("8.6").color(TextColor.color(0x4242FF)),
                Component.text("Sa fé dé degat 1?"),
                8.6d,
                50);
    }

    @Override
    public void applyEffects(Player player) {

    }
}
