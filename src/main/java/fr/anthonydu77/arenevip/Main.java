package fr.anthonydu77.arenevip;

import fr.anthonydu77.arenevip.managers.Setup;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by Anthonydu77 30/07/2021 inside the package - fr.anthonydu77.arenevip
 */

public class Main extends JavaPlugin implements Listener {

    private static Main instance;
    private Setup setup;
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        this.setup = new Setup();
        setup.register(this);
        instance = this;
    }

    @Override
    public void onDisable() {
        this.setup = new Setup();
        setup.logout(this);
    }
}


