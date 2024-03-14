package org.remiluka.features.drinking.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.remiluka.features.drinking.InfluenceManager;

public class InfluenceListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        InfluenceManager.trackNewPlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        InfluenceManager.resetPlayerInfluence(event.getPlayer());
    }
}
