package fr.anthonydu77.arenevip;

import fr.anthonydu77.arenevip.commands.PluginInfoCommands;
import fr.anthonydu77.arenevip.listeners.PlayerJoinEvents;
import fr.anthonydu77.arenevip.listeners.PlayerLeaveEvent;
import fr.anthonydu77.arenevip.managers.YmlFile;
import fr.anthonydu77.arenevip.managers.config.PluginSettings;
import io.rqndomhax.GetVersaAPI;
import io.rqndomhax.VersaAPI;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by Anthonydu77 30/07/2021 inside the package - fr.anthonydu77.arenevip
 */

public class Main extends JavaPlugin {

    private static Main instance;
    private static PluginSettings pluginSettings;
    VersaAPI api;
    private NPC arene;
    private static final Logger log = Logger.getLogger("Minecraft");

    public static Main getInstance() {
        return instance;
    }
    public static PluginSettings getPLuginSetting() {
        return pluginSettings;
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
        registerAPI();
        setupNPC();



        log.info(getLog_Prefix() + getDescription().getName() + " status is ready");
        log.info(getLog_Prefix() + "---------- " + getDescription().getFullName() + " ----------");
    }

    @Override
    public void onDisable() {
        log.info(getLog_Prefix() + "---------- " + getDescription().getFullName() + " ----------");
        log.info(getLog_Prefix() + getDescription().getName() + " is shutting off");
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));

        arene.despawn();
        arene.destroy();

        log.info(getLog_Prefix() + getDescription().getName() + " status is off");
        log.info(getLog_Prefix() + "---------- " + getDescription().getFullName() + " ----------");
    }

    private void setupNPC() {
        //JoinArene
        arene = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, UUID.randomUUID(), 0, "&eAgricultures");
        arene.data().set(NPC.PLAYER_SKIN_UUID_METADATA, "Anthonydu77");
        arene.data().set(NPC.PLAYER_SKIN_USE_LATEST, false);
        Location npcAgricultureLoc = new Location(Bukkit.getWorld(pluginSettings.getWorld()),
                pluginSettings.getX(), pluginSettings.getY(), pluginSettings.getZ(), pluginSettings.getYaw(), pluginSettings.getPitch());
        arene.spawn(npcAgricultureLoc);
    }

    private void registerAPI() {
        GetVersaAPI getApi = (GetVersaAPI) Bukkit.getServicesManager().load(GetVersaAPI.class); // We're getting the API
        if (getApi == null) {    // If the API couldn't be found we'll stop the plugin
            onDisable();
            return;
        }
        api = getApi.getAPI();    // We're saving the api for later use

        log.info(getLog_Prefix() + "Register API is done !");
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinEvents(), this);
        pm.registerEvents(new PlayerLeaveEvent(), this);
        log.info(getLog_Prefix() + "Register Events is done !");
    }

    private void registerYamls() {

        YmlFile.LANG.create(log);

        pluginSettings = (PluginSettings) registerYamls(YmlFile.CONFIG, PluginSettings.class);

        log.info(getLog_Prefix() + "Register Yaml is done !");
    }

    private void regsiterCommands() {

        Objects.requireNonNull(getCommand("plversion")).setExecutor(new PluginInfoCommands());
        Objects.requireNonNull(getCommand("plname")).setExecutor(new PluginInfoCommands());
        Objects.requireNonNull(getCommand("pldescription")).setExecutor(new PluginInfoCommands());
        Objects.requireNonNull(getCommand("plauthors")).setExecutor(new PluginInfoCommands());

        log.info(getLog_Prefix() + "Register commands is done !");
    }

    private Object registerYamls(YmlFile yml, Class<?> clazz) {
        yml.create(log);
        try (final Reader reader = Files.newBufferedReader(yml.getFile().toPath(), StandardCharsets.UTF_8)) {
            final Yaml yaml = new Yaml(new CustomClassLoaderConstructor(getClassLoader()));
            yaml.setBeanAccess(BeanAccess.FIELD);
            log.info(Main.getLog_Prefix() + "Configuration file read successfully");
            return yaml.loadAs(reader, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


