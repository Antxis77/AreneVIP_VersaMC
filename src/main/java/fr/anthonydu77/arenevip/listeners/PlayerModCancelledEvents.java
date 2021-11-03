package fr.anthonydu77.arenevip.listeners;

import fr.anthonydu77.arenevip.Main;
import fr.anthonydu77.arenevip.managers.config.PluginSettings;
import io.rqndomhax.utils.ItemBuilder;
import org.apache.commons.lang.math.IntRange;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

/**
 * Created by Anthonydu77 03/11/2021 inside the package - fr.anthonydu77.arenevip.listeners
 */

public class PlayerModCancelledEvents implements Listener {

    final private Main instance = Main.getInstance();
    final private PluginSettings settings = Main.getPLuginSetting();

    @EventHandler
    public void onAttack(EntityDamageEvent e) {
        Player player = (Player) e.getEntity();

        Location toparene = new Location(Bukkit.getWorld("world"), settings.getArene_top_x(), settings.getArene_top_y(), settings.getArene_top_z());
        Location bottomarene = new Location(Bukkit.getWorld("world"), settings.getArene_bottom_x(), settings.getArene_bottom_y(), settings.getArene_bottom_z());
        Location playerloc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());

        if (!(inCuboid(playerloc, toparene, bottomarene))) {
            e.setCancelled(true);
        }
    }

    public boolean inCuboid(Location origin, Location l1, Location l2){
        return new IntRange(l1.getX(), l2.getX()).containsDouble(origin.getX())
                && new IntRange(l1.getY(), l2.getY()).containsDouble(origin.getY())
                &&  new IntRange(l1.getZ(), l2.getZ()).containsDouble(origin.getZ());
    }

}
