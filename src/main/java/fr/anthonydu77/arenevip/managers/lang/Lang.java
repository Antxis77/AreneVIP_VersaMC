package fr.anthonydu77.arenevip.managers.lang;

import fr.anthonydu77.arenevip.Main;
import fr.anthonydu77.arenevip.managers.YmlFile;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anthonydu77 13/12/2020 inside the package - fr.anthonydu77.modmoderation.managers.lang
 */

public enum Lang {

    //#######       EssentialAdmin       #######
    SERVEUR_NAME,
    SERVEUR_NAME_CONSOLE,
    CONTACT_ADMIN,
    MOD_TO_CONSOLE,
    NO_PERMISSION,
    NO_ALLOW,
    MSG_FORMAT,
    NO_GAME,
    PLAYER_JOIN_EVENT,
    PLAYER_LEAVE_EVENT,

    ADMIN_PERM,
    JOUEUR_PERM,

    EMPTY;

    private static final Map<Lang, String> VALUES = new HashMap<>();

    static {
        for (Lang lang : values()) {
            VALUES.put(lang, lang.getFromFile());
        }
        Main.getInstance().getLogger().info("Lang file read successfuly !");
    }

    public String get() {
        return VALUES.get(this);
    }

    private String getFromFile() {
        FileConfiguration config = YmlFile.LANG.getConfig();
        String key = name().toLowerCase().replace('_', '-');
        String value = config.getString(key);

        if (value == null) {
            value = "";
        }

        return ChatColor.translateAlternateColorCodes('&', value);
    }
}
