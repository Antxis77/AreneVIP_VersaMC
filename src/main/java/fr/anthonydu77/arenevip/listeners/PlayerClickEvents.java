package fr.anthonydu77.arenevip.listeners;

import fr.anthonydu77.arenevip.Main;
import fr.anthonydu77.arenevip.managers.config.PluginSettings;
import fr.anthonydu77.arenevip.managers.classutils.ItemBuilder;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Anthonydu77 03/11/2021 inside the package - fr.anthonydu77.arenevip.listeners
 */

public class PlayerClickEvents implements Listener {

    final private Main instance = Main.getInstance();
    final private PluginSettings settings = Main.getPLuginSetting();

    @EventHandler
    public void onClickEvent(NPCRightClickEvent e) {
        Player player = e.getClicker();
        int id = e.getNPC().getId();

        switch (id) {
            case 0:
                // TODO: 03/11/2021 TP player dans arene avec stuff.
                player.teleport(new Location(player.getWorld(), settings.getArene_x(), settings.getArene_y(), settings.getArene_z()));
                //player.setWalkSpeed(1.6f);

                ItemBuilder sword = new ItemBuilder(Material.IRON_SWORD).setName(ChatColor.GOLD + "Glaive").setInfinityDurability();

                player.sendMessage(ChatColor.GOLD + "Bienvenue dans l'Arène !");

                player.getInventory().setItem(0, sword.toItemStack());
                break;

            default:
                break;
        }
        

    }

}
