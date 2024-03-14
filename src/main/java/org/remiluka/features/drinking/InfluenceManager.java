package org.remiluka.features.drinking;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.remiluka.Main;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;

public final class InfluenceManager {
    private static final HashMap<UUID, Double> _players = new HashMap<>();
    private static int _taskId;

    private static final double influenceLostPerHour = 0.2d;
    private static final int taskFreq = 600;
    private static final double hoursBetweenTasks = 24d / taskFreq;
    private static final int ticksPerDay = 24000;
    private static final DecimalFormat decimalFormatter = new DecimalFormat("###.##");

    public static void init() {
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            _players.put(onlinePlayer.getUniqueId(), 0.0d);
        }

        _taskId = new BukkitRunnable() {
            @Override
            public void run() {
                influenceTask();
            }
        }.runTaskTimer(Main.getInstance(), 0, ticksPerDay / taskFreq).getTaskId();
    }

    public static void cleanUp() {
        Bukkit.getScheduler().cancelTask(_taskId);

        _players.clear();
    }

    public static void trackNewPlayer(Player player) {
        if (!_players.containsKey(player.getUniqueId()))
            _players.put(player.getUniqueId(), 0.0d);
    }

    public static void resetPlayerInfluence(Player player) {
        _players.replace(player.getUniqueId(), 0.0d);
    }

    public static double getPlayerInfluence(Player player) {
        return _players.get(player.getUniqueId());
    }

    public static void addPlayerInfluence(Player player, double alcoolLevel, double alcoolQtyCl) {
        double addedInfluence = ((alcoolQtyCl * 10d) * (alcoolLevel / 100d) * 0.800d) / (70d * 0.70d);

        _players.replace(player.getUniqueId(), getPlayerInfluence(player) + addedInfluence);
    }

    public static void setPlayerInfluence(Player player, double newInfluence) {
        _players.replace(player.getUniqueId(), Double.max(newInfluence, 0.0d));
    }

    private static void influenceTask() {
        _randomMovement += Math.PI / 4;

        for (Map.Entry<UUID, Double> entry : _players.entrySet()) {
            Player player = Bukkit.getPlayer(entry.getKey());
            double influence = entry.getValue();

            if (player != null) {
                influencePlayer(player);
                if (influence > 0.0d)
                    player.sendActionBar(Component.text("Vous etes sous influence! (" + decimalFormatter.format(influence) + " g/L)"));
            }

            _players.replace(entry.getKey(), Double.max(influence - (influenceLostPerHour * hoursBetweenTasks), 0.0d));
        }
    }

    private static boolean isDrunk(Player player) {
        return InfluenceManager.getPlayerInfluence(player) > 0.6d;
    }

    private static double _randomMovement = 0.0d;
    private static void influencePlayer(Player player) {
        if (isDrunk(player)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ticksPerDay / taskFreq) + 60, 0, false, false));

            player.setVelocity(player.getVelocity().add(new Vector((Math.cos(_randomMovement) > 0 ? 1d : -1d) / 3d, 0, (Math.sin(_randomMovement) > 0 ? 1d : -1d) / 3d)));

//            if (random.nextBoolean()) {
//                return;
//            }
//
//            Vector velocity = player.getVelocity();
//            int direction = random.nextInt(4);
//            double moveSpeed =  random.nextDouble(0.5d);
//
//            switch (direction){
//                case 0:
//                    velocity.setX(-moveSpeed); //left
//                    break;
//                case 1:
//                    velocity.setX(moveSpeed); //right
//                    break;
//                case 2:
//                    velocity.setY(-moveSpeed); //rear
//                    break;
//                case 3:
//                    velocity.setY(moveSpeed); //front
//                    break;
//                default:
//                    break;
//            }
//
//            player.setVelocity(velocity);
        }
    }
}
