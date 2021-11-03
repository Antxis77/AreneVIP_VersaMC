package fr.anthonydu77.arenevip.listeners;

import fr.anthonydu77.arenevip.Main;
import fr.anthonydu77.arenevip.managers.config.PluginSettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Created by Anthonydu77 03/11/2021 inside the package - fr.anthonydu77.arenevip.listeners
 */

public class PlayerDeadEvents implements Listener {

    final private Main instance = Main.getInstance();
    final private PluginSettings settings = Main.getPLuginSetting();

    @EventHandler
    public void onRevive(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        player.teleport(new Location(player.getWorld(), settings.getSpawn_x(), settings.getSpawn_y(), settings.getSpawn_z()));
    }

}
