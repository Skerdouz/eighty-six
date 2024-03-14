package org.remiluka.features.drinking.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.remiluka.features.drinking.drinks.Drink;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Default;
import revxrsal.commands.annotation.Range;
import revxrsal.commands.bukkit.EntitySelector;

import java.util.ArrayList;
import java.util.List;

import static org.remiluka.features.drinking.DrinkMaster.DRINK_ID_KEY;

public final class GiveDrink {
    @Command("givedrink")
    public static void Give(@Default("me") EntitySelector<Player> targets, Drink drink, @Default("1") @Range(min = 1, max = 64) long qty) {
        for (Player player : targets) {
            player.getInventory().addItem(createDrink(drink, (int) qty));
        }
    }

    private static ItemStack createDrink(Drink drink, int qty){
        ItemStack item = new ItemStack(Material.POTION, qty);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.getPersistentDataContainer().set(DRINK_ID_KEY, PersistentDataType.STRING, drink.getId());

        List<Component> lore = new ArrayList<>();

        lore.add(drink.getDescription().color(TextColor.color(0x808080)));
        lore.add(Component.empty());
        lore.add(Component.text("Taux d'alcooul: ").color(TextColor.color(0x808080)).append(Component.text(drink.getAlcoolLevel() + "%").color(TextColor.color(0xFF4141))));

        lore.replaceAll(line -> line.decoration(TextDecoration.ITALIC, false));

        itemMeta.lore(lore);
        itemMeta.displayName(drink.getName());
        itemMeta.addEnchant(Enchantment.LURE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(itemMeta);

        return item;
    }
}
