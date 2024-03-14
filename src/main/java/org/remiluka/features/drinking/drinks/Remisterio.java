package org.remiluka.features.drinking.drinks;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Remisterio extends Drink {
    public Remisterio() {
        super("remisterio",
                Component.text("Remisterio").color(TextColor.color(0xFFAC4C)),
                Component.text("Tu va t'envoler mon reuf"),
                0.0d,
                33);
    }

    @Override
    public void applyEffects(Player player) {
        player.setVelocity(player.getVelocity().add(new Vector(0, 1, 0)));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 0, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1, false, false));
    }
}
