package org.remiluka.features.drinking.commands;

import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import org.remiluka.features.drinking.InfluenceManager;
import revxrsal.commands.annotation.Command;


public final class Blargk {
    @Command("blargk")
    public static void EmptyYourStomach(CommandSender sender){
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Command can only be used by Player");
            return;
        }

        InfluenceManager.setPlayerInfluence(player, InfluenceManager.getPlayerInfluence(player) - 0.68d);
    }
}
