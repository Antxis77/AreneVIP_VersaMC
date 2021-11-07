package fr.anthonydu77.arenevip.listeners;

import fr.anthonydu77.arenevip.Main;
import fr.anthonydu77.arenevip.managers.Setup;
import fr.anthonydu77.arenevip.managers.config.PluginSettings;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Anthonydu77 30/07/2021 inside the package - fr.anthonydu77.template.listeners
 */

public class PlayerJoinLeaveEvent implements Listener {

    final private Main instance = Main.getInstance();
    public static final Map<UUID, FastBoard> boards = new HashMap<>();
    final private PluginSettings settings = Setup.getPLuginSetting();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        player.setAllowFlight(false);
        player.setGameMode(GameMode.ADVENTURE);
        player.setFlying(false);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();

        FastBoard objectiveSign = new FastBoard(player);

        objectiveSign.updateTitle("§9");
        objectiveSign.updateTitle("§9§lMon Profil");
        objectiveSign.updateTitle("§8➤ §fRank : §e" + Setup.getAPI().getUserManager().getUser(player).getInfos().getObject("ranks"));
        objectiveSign.updateTitle("§8➤ §fPièce : §e" + Setup.getAPI().getUserManager().getUser(player).getInfos().getObject("coins") +" ⛃");
        objectiveSign.updateTitle("§e");
        objectiveSign.updateTitle("§9§lServeur");
        objectiveSign.updateTitle("§8➤ §fJoueurs : §e" + Bukkit.getOnlinePlayers().size());
        objectiveSign.updateTitle("§8➤ §fLobby : §e#1");
        objectiveSign.updateTitle("§1");
        objectiveSign.updateTitle(ChatColor.GOLD + "play.versamc.fr");
        objectiveSign.updateLines();

        this.boards.put(player.getUniqueId(), objectiveSign);

        player.teleport(new Location(player.getWorld(), settings.getSpawn_x(), settings.getSpawn_y(), settings.getSpawn_z()));

        instance.getLogger().info(player.getDisplayName() + " join the server !");
        Bukkit.broadcastMessage(ChatColor.GREEN +  "[+] " + player.getDisplayName() + " join the server");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        FastBoard board = this.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }

        instance.getLogger().info(player.getDisplayName() + " leave the serveur !");
        Bukkit.broadcastMessage(ChatColor.RED +  "[-] " + player.getDisplayName() + " leave the server");
    }
}
