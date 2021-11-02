package fr.anthonydu77.arenevip.commands;

import fr.anthonydu77.arenevip.Main;
import fr.anthonydu77.arenevip.managers.lang.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import fr.anthonydu77.arenevip.managers.classutils.TabCompletor;

import java.util.Collections;
import java.util.List;

/**
 * Created by Anthonydu77 03/08/2021 inside the package - fr.anthonydu77.template.commands
 */

public class PluginInfoCommands implements TabExecutor {
    final private Main instance = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.SERVEUR_NAME_CONSOLE.get() + Lang.MOD_TO_CONSOLE.get());
            return false;
        }

        Player player = (Player) sender;

        /**
         * @Command (name = " / plversion ", desc = " To see the plugin version")
         * @CommandPermissions ({ template.allaccess })
         */
        if (label.equalsIgnoreCase("plversion")){
            if (player.hasPermission(Lang.ADMIN_PERM.get())){
                if (args.length == 0){
                    player.sendMessage(Lang.SERVEUR_NAME.get() + "Version : " + instance.getDescription().getVersion());
                } else {
                    player.sendMessage(Lang.SERVEUR_NAME.get() + "Use syntaxe : /plversion");
                }
            } else {
                player.sendMessage(Lang.SERVEUR_NAME.get() + Lang.NO_PERMISSION.get());
            }
        }

        /**
         * @Command (name = " / plname ", desc = " To see the plugin name")
         * @CommandPermissions ({ template.allaccess })
         */
        if (label.equalsIgnoreCase("plname")){
            if (player.hasPermission(Lang.ADMIN_PERM.get())){
                if (args.length == 0){
                    player.sendMessage(Lang.SERVEUR_NAME.get() + "Name : " + instance.getDescription().getName());
                } else {
                    player.sendMessage(Lang.SERVEUR_NAME.get() + "Use syntaxe : /plname");
                }
            } else {
                player.sendMessage(Lang.SERVEUR_NAME.get() + Lang.NO_PERMISSION.get());
            }
        }

        /**
         * @Command (name = " / pldescription ", desc = " To see the plugin name")
         * @CommandPermissions ({ template.allaccess })
         */
        if (label.equalsIgnoreCase("pldescription")){
            if (player.hasPermission(Lang.ADMIN_PERM.get())){
                if (args.length == 0){
                    player.sendMessage(Lang.SERVEUR_NAME.get() + "Description : " + instance.getDescription().getDescription());
                } else {
                    player.sendMessage(Lang.SERVEUR_NAME.get() + "Use syntaxe : /pldescription");
                }
            } else {
                player.sendMessage(Lang.SERVEUR_NAME.get() + Lang.NO_PERMISSION.get());
            }
        }

        /**
         * @Command (name = " / plauthors ", desc = " To see the plugin name")
         * @CommandPermissions ({ template.allaccess })
         */
        if (label.equalsIgnoreCase("plauthors")){
            if (player.hasPermission(Lang.ADMIN_PERM.get())){
                if (args.length == 0){
                    player.sendMessage(Lang.SERVEUR_NAME.get() + "Authors : " + instance.getDescription().getAuthors());
                } else {
                    player.sendMessage(Lang.SERVEUR_NAME.get() + "Use syntaxe : /plauthors");
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
