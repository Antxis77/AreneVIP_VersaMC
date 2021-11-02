package fr.anthonydu77.arenevip.listeners;

import fr.anthonydu77.arenevip.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Anthonydu77 30/07/2021 inside the package - fr.anthonydu77.template.listeners
 */

public class PlayerJoinEvents implements Listener {

    final private Main instance = Main.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        instance.getLogger().info(player.getDisplayName() + " join the server !");
        Bukkit.broadcastMessage(ChatColor.GREEN +  "[+] " + player.getDisplayName() + " join the server");
    }
}
