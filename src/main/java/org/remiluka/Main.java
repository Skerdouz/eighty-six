package org.remiluka;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.remiluka.features.drinking.DrinkMaster;
import org.remiluka.features.drinking.InfluenceManager;

import java.util.logging.Level;

public class Main extends JavaPlugin implements Listener {
    private static Main _instance;

    public static Main getInstance() {
        return _instance;
    }

    @Override
    public void onEnable() {
        _instance = this;

        Bukkit.getLogger().log(Level.INFO, "Plugin is now enabled");

        // Register events and commands
        EventManager.registerEvents();
        CommandHandler.registerCommands();

        // Drinking feature
        DrinkMaster.registerDrinks();
        InfluenceManager.init();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().log(Level.INFO, "Plugin is now disabled");

        // Drinking feature
        InfluenceManager.cleanUp();
    }
}
