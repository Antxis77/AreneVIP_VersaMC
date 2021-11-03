package fr.anthonydu77.arenevip.listeners;

import fr.anthonydu77.arenevip.Main;
import fr.anthonydu77.arenevip.managers.config.PluginSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by Anthonydu77 30/07/2021 inside the package - fr.anthonydu77.template.listeners
 */

public class PlayerJoinEvents implements Listener {

    final private Main instance = Main.getInstance();
    final private PluginSettings settings = Main.getPLuginSetting();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        player.setAllowFlight(false);
        player.setGameMode(GameMode.ADVENTURE);
        player.setFlying(false);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setWalkSpeed(1f);

        player.teleport(new Location(player.getWorld(), settings.getSpawn_x(), settings.getSpawn_y(), settings.getSpawn_z()));

        instance.getLogger().info(player.getDisplayName() + " join the server !");
        Bukkit.broadcastMessage(ChatColor.GREEN +  "[+] " + player.getDisplayName() + " join the server");
    }
}
