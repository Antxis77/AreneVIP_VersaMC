package fr.anthonydu77.arenevip.commands;

import fr.anthonydu77.arenevip.Main;
import fr.anthonydu77.arenevip.managers.classutils.TabCompletor;
import fr.anthonydu77.arenevip.managers.config.PluginSettings;
import fr.anthonydu77.arenevip.managers.lang.Lang;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Anthonydu77 02/11/2021 inside the package - fr.anthonydu77.arenevip.commands
 */

public class ConfigCommands implements TabExecutor {
    final private Main instance = Main.getInstance();
    private NPC arene;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.SERVEUR_NAME_CONSOLE.get() + Lang.MOD_TO_CONSOLE.get());
            return false;
        }

        Player player = (Player) sender;

        /**
         * @Command (name = " / setnpc ", desc = " To see the plugin version")
         * @CommandPermissions ({ arenevip.admin })
         */
        if (label.equalsIgnoreCase("setnpc")){
            if (player.hasPermission(Lang.ADMIN_PERM.get())){
                if (args.length == 5){
                    Main.getPLuginSetting().setX(Integer.parseInt(args[0]));
                    Main.getPLuginSetting().setY(Integer.parseInt(args[1]));
                    Main.getPLuginSetting().setZ(Integer.parseInt(args[2]));
                    Main.getPLuginSetting().setYaw(Integer.parseInt(args[3]));
                    Main.getPLuginSetting().setPitch(Integer.parseInt(args[4]));

                    Main.getPLuginSetting().setWorld(String.valueOf(player.getWorld()));

                    arene.despawn();
                    arene.destroy();

                    arene = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, UUID.randomUUID(), 0, "&eAgricultures");
                    arene.data().set(NPC.PLAYER_SKIN_UUID_METADATA, "Anthonydu77");
                    arene.data().set(NPC.PLAYER_SKIN_USE_LATEST, false);
                    Location npcAgricultureLoc = new Location(Bukkit.getWorld(Main.getPLuginSetting().getWorld()),
                            Main.getPLuginSetting().getX(), Main.getPLuginSetting().getY(), Main.getPLuginSetting().getZ(),
                            Main.getPLuginSetting().getYaw(), Main.getPLuginSetting().getPitch());
                    arene.spawn(npcAgricultureLoc);

                } else {
                    player.sendMessage(Lang.SERVEUR_NAME.get() + "Use syntaxe : /setnpc X Y Z YAW PITCH");
                }
            } else {
                player.sendMessage(Lang.SERVEUR_NAME.get() + Lang.NO_PERMISSION.get());
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return TabCompletor.onTabComplete(Collections.emptyList(), sender, args);
    }
}
