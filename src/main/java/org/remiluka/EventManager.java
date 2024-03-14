package org.remiluka;

import org.bukkit.Bukkit;
import org.remiluka.features.drinking.events.InfluenceListener;
import org.remiluka.features.drinking.events.PlayerDrinking;

public final class EventManager {
    static void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerDrinking(), Main.getInstance());
        Bukkit.getPluginManager().registerEvents(new InfluenceListener(), Main.getInstance());
    }
}
