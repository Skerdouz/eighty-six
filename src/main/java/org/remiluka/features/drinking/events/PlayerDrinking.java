package org.remiluka.features.drinking.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.remiluka.features.drinking.DrinkMaster;
import org.remiluka.features.drinking.InfluenceManager;
import org.remiluka.features.drinking.drinks.Drink;

import static org.remiluka.features.drinking.DrinkMaster.DRINK_ID_KEY;

public class PlayerDrinking implements Listener {
    @EventHandler
    private void OnPlayerItemConsume(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        if (InfluenceManager.getPlayerInfluence(event.getPlayer()) >= 0.2d && event.getItem().getType() == Material.MILK_BUCKET) {
            event.setCancelled(true);
            return;
        }

        if (!pdc.has(DRINK_ID_KEY))
            return;

        String drinkId = pdc.get(DRINK_ID_KEY, PersistentDataType.STRING);

        if (!DrinkMaster.isDrink(drinkId))
            return;

        Drink drink = DrinkMaster.getDrinkById(drinkId);

        InfluenceManager.addPlayerInfluence(event.getPlayer(), drink.getAlcoolLevel(), drink.getDrinkSize());
        drink.applyEffects(event.getPlayer());
    }

}
