package fr.anthonydu77.arenevip;

import fr.anthonydu77.arenevip.commands.PluginInfoCommands;
import fr.anthonydu77.arenevip.listeners.PlayerJoinEvents;
import fr.anthonydu77.arenevip.listeners.PlayerLeaveEvent;
import fr.anthonydu77.arenevip.managers.YmlFile;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by Anthonydu77 30/07/2021 inside the package - fr.anthonydu77.arenevip
 */

public class Main extends JavaPlugin {

    private static Main instance;
    private static final Logger log = Logger.getLogger("Minecraft");

    public static Main getInstance() {
        return instance;
    }
    public static String getLog_Prefix() {
        return "[Arene VIP] - ";
    }

    @Override
    public void onEnable() {
        log.info(getLog_Prefix() + "---------- " + getDescription().getFullName() + " ----------");
        log.info(getLog_Prefix() + "Starting " + getDescription().getName() + " ...");
        log.info(getLog_Prefix() + "Author : " + getDescription().getAuthors());
        log.info(getLog_Prefix() + "Description : " + getDescription().getDescription());
        log.info(getLog_Prefix() + "Version : " + getDescription().getVersion());
        log.info(getLog_Prefix() + "If you have any problem contact me at discord : Antho77_#1536");
        instance = this;

        registerYamls();
        registerEvents();
        regsiterCommands();

        log.info(getLog_Prefix() + getDescription().getName() + " status is ready");
        log.info(getLog_Prefix() + "---------- " + getDescription().getFullName() + " ----------");
    }

    @Override
    public void onDisable() {
        log.info(getLog_Prefix() + "---------- " + getDescription().getFullName() + " ----------");
        log.info(getLog_Prefix() + getDescription().getName() + " is shutting off");
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));



        log.info(getLog_Prefix() + getDescription().getName() + " status is off");
        log.info(getLog_Prefix() + "---------- " + getDescription().getFullName() + " ----------");
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinEvents(), this);
        pm.registerEvents(new PlayerLeaveEvent(), this);
        log.info(getLog_Prefix() + "Register Events is done !");
    }

    private void registerYamls() {

        YmlFile.LANG.create(log);

        log.info(getLog_Prefix() + "Register Yaml is done !");
    }

    private void regsiterCommands() {

        Objects.requireNonNull(getCommand("plversion")).setExecutor(new PluginInfoCommands());
        Objects.requireNonNull(getCommand("plname")).setExecutor(new PluginInfoCommands());
        Objects.requireNonNull(getCommand("pldescription")).setExecutor(new PluginInfoCommands());
        Objects.requireNonNull(getCommand("plauthors")).setExecutor(new PluginInfoCommands());

        log.info(getLog_Prefix() + "Register commands is done !");
    }
}


