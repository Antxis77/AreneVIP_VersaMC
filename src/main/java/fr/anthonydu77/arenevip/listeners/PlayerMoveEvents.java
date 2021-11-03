package fr.anthonydu77.arenevip.listeners;

import fr.anthonydu77.arenevip.Main;
import fr.anthonydu77.arenevip.managers.classutils.Cuboid;
import fr.anthonydu77.arenevip.managers.config.PluginSettings;
import org.apache.commons.lang.math.IntRange;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

/**
 * Created by Anthonydu77 03/11/2021 inside the package - fr.anthonydu77.arenevip.listeners
 */

public class PlayerMoveEvents implements Listener {

    final private Main instance = Main.getInstance();
    final private PluginSettings settings = Main.getPLuginSetting();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        Location toparene = new Location(Bukkit.getWorld("world"), settings.getArene_top_x(), settings.getArene_top_y(), settings.getArene_top_z());
        Location bottomarene = new Location(Bukkit.getWorld("world"), settings.getArene_bottom_x(), settings.getArene_bottom_y(), settings.getArene_bottom_z());
        Location playerloc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());

        if (inCuboid(playerloc, toparene, bottomarene)) {

        }


        Cuboid myStoredRegions = new Cuboid(Bukkit.getWorld("world"), settings.getArene_top_x(), settings.getArene_top_y(), settings.getArene_top_z(),
                                                                            settings.getArene_bottom_x(), settings.getArene_bottom_y(), settings.getArene_bottom_z());

        for (Cuboid region : myStoredRegions) {
            if (region.contains(e.getTo()) && !region.contains(e.getFrom())) {
                // player is moving into a region!  do something
            } else if (!region.contains(e.getTo()) && region.contains(e.getFrom())) {
                // player is moving out of a region!  do something
            }
        }


    }

    public boolean inCuboid(Location origin, Location l1, Location l2){
        return new IntRange(l1.getX(), l2.getX()).containsDouble(origin.getX())
                && new IntRange(l1.getY(), l2.getY()).containsDouble(origin.getY())
                &&  new IntRange(l1.getZ(), l2.getZ()).containsDouble(origin.getZ());
    }
}