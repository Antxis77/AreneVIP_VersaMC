package fr.anthonydu77.arenevip.managers;

import fr.anthonydu77.arenevip.Main;
import fr.anthonydu77.arenevip.commands.PluginInfoCommands;
import fr.anthonydu77.arenevip.listeners.*;
import fr.anthonydu77.arenevip.managers.config.PluginSettings;
import fr.mrmicky.fastboard.FastBoard;
import io.rqndomhax.GetVersaAPI;
import io.rqndomhax.VersaAPI;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
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

import static jdk.xml.internal.SecuritySupport.getClassLoader;

/**
 * Created by Anthonydu77 07/11/2021 inside the package - fr.anthonydu77.arenevip.managers
 */

public class Setup {

    private static PluginSettings pluginSettings;
    private static VersaAPI api;
    private NPC arene;
    private static final Logger log = Logger.getLogger("Minecraft");

    public static VersaAPI getAPI() { return api;}
    public static PluginSettings getPLuginSetting() {
        return pluginSettings;
    }


    public static String getLog_Prefix() {
        return "[Arene VIP] - ";
    }

    public void register(Main main) {
        PluginDescriptionFile description = main.getDescription();

        log.info(getLog_Prefix() + "---------- " + description.getFullName() + " ----------");
        log.info(getLog_Prefix() + "Starting " + description.getName() + " ...");
        log.info(getLog_Prefix() + "Author : " + description.getAuthors());
        log.info(getLog_Prefix() + "Description : " + description.getDescription());
        log.info(getLog_Prefix() + "Version : " + description.getVersion());
        log.info(getLog_Prefix() + "If you have any problem contact me at discord : Antho77_#1536");

        /**
         * For init Yml configuration
         */
        YmlFile.LANG.create(log);
        pluginSettings = (PluginSettings) registerYamls(YmlFile.CONFIG, PluginSettings.class);
        log.info(getLog_Prefix() + "Register Yaml is done !");

        /**
         * For init Events configuration
         */
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerClickEvents(), main);
        pm.registerEvents(new PlayerDeadEvents(), main);
        pm.registerEvents(new PlayerJoinLeaveEvent(), main);
        pm.registerEvents(new PlayerModCancelledEvents(), main);
        log.info(getLog_Prefix() + "Register Events is done !");

        /**
         * For init Commands configuration
         */
        Objects.requireNonNull(main.getCommand("plversion")).setExecutor(new PluginInfoCommands());
        Objects.requireNonNull(main.getCommand("plname")).setExecutor(new PluginInfoCommands());
        Objects.requireNonNull(main.getCommand("pldescription")).setExecutor(new PluginInfoCommands());
        Objects.requireNonNull(main.getCommand("plauthors")).setExecutor(new PluginInfoCommands());
        log.info(getLog_Prefix() + "Register commands is done !");

        /**
        * For init API configuration
        */
        GetVersaAPI getApi = (GetVersaAPI) Bukkit.getServicesManager().load(GetVersaAPI.class); // We're getting the API
        if (getApi == null) {    // If the API couldn't be found we'll stop the plugin
            main.onDisable();
            return;
        }
        api = getApi.getAPI();    // We're saving the api for later use
        main.getServer().getScheduler().runTaskTimer(main, () -> {
            for (FastBoard board : PlayerJoinLeaveEvent.boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);
        log.info(getLog_Prefix() + "Register API is done !");

        /**
         * For init NPC configuration
         */
        arene = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, UUID.randomUUID(), 0, "&eMaitre Gladiateur");
        arene.data().set(NPC.PLAYER_SKIN_UUID_METADATA, "moansx");
        arene.data().set(NPC.PLAYER_SKIN_USE_LATEST, false);
        Location npcAgricultureLoc = new Location(Bukkit.getWorld(pluginSettings.getNpc_world()),
                pluginSettings.getNpc_x(), pluginSettings.getNpc_y(), pluginSettings.getNpc_z(), pluginSettings.getNpc_yaw(), pluginSettings.getNpc_pitch());
        arene.spawn(npcAgricultureLoc);

        /**
         * For init World configuration
         */
        World world = Bukkit.getWorld("world");
        world.setDifficulty(Difficulty.PEACEFUL);

        log.info(getLog_Prefix() + description.getName() + " status is ready");
        log.info(getLog_Prefix() + "---------- " + description.getFullName() + " ----------");
    }

    public void logout(Main main) {
        PluginDescriptionFile description = main.getDescription();

        log.info(getLog_Prefix() + "---------- " + description.getFullName() + " ----------");
        log.info(getLog_Prefix() + description.getName() + " is shutting off");
        log.info(String.format("[%s] Disabled Version %s", description.getName(), description.getVersion()));

        arene.despawn();
        arene.destroy();

        log.info(getLog_Prefix() + description.getName() + " status is off");
        log.info(getLog_Prefix() + "---------- " + description.getFullName() + " ----------");
    }

    private Object registerYamls(YmlFile yml, Class<?> clazz) {
        yml.create(log);
        try (final Reader reader = Files.newBufferedReader(yml.getFile().toPath(), StandardCharsets.UTF_8)) {
            final Yaml yaml = new Yaml(new CustomClassLoaderConstructor(getClassLoader()));
            yaml.setBeanAccess(BeanAccess.FIELD);
            log.info(getLog_Prefix() + "Configuration file read successfully");
            return yaml.loadAs(reader, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updateBoard(FastBoard board) {
        board.updateLines("§9");
        board.updateLines("§9§lMon Profil");
        board.updateLines("§8➤ §fRank : §e" + getAPI().getUserManager().getUser(board.getPlayer()).getInfos().getObject("ranks"));
        board.updateLines("§8➤ §fPièce : §e" + getAPI().getUserManager().getUser(board.getPlayer()).getInfos().getObject("coins") +" ⛃");
        board.updateLines("§e");
        board.updateLines("§9§lServeur");
        board.updateLines("§8➤ §fJoueurs : §e" + Bukkit.getOnlinePlayers().size());
        board.updateLines("§8➤ §fLobby : §e#1");
        board.updateLines("§1");
        board.updateLines(ChatColor.GOLD + "play.versamc.fr");
    }
}
