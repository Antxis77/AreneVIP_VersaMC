package fr.anthonydu77.arenevip.listeners;

import fr.anthonydu77.arenevip.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Anthonydu77 30/07/2021 inside the package - fr.anthonydu77.template.listeners
 */

public class PlayerLeaveEvents implements Listener {

    final private Main instance = Main.getInstance();

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        instance.getLogger().info(player.getDisplayName() + " leave the serveur !");
        Bukkit.broadcastMessage(ChatColor.RED +  "[-] " + player.getDisplayName() + " leave the server");
    }
}
