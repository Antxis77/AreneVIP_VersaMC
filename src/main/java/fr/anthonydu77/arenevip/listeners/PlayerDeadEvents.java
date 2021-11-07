package fr.anthonydu77.arenevip.listeners;

import fr.anthonydu77.arenevip.managers.Setup;
import fr.anthonydu77.arenevip.managers.lang.Lang;
import fr.anthonydu77.arenevip.managers.lang.LangValue;
import io.rqndomhax.utils.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * Created by Anthonydu77 03/11/2021 inside the package - fr.anthonydu77.arenevip.listeners
 */

public class PlayerDeadEvents implements Listener {

    @EventHandler
    public void onDead(EntityDeathEvent e) {
        Player killer = e.getEntity().getKiller();
        Player player = (Player) e.getEntity();

        if (killer == null) {
            Bukkit.broadcastMessage(Lang.PLAYER_DEAD_EVENT.get()
                    .replace(LangValue.PLAYER.toName(), player.getName()));
        } else {
            Bukkit.broadcastMessage(Lang.PLAYER_KILL_EVENT.get()
                    .replace(LangValue.KILLER.toName(), killer.getName())
                    .replace(LangValue.PLAYER.toName(), player.getName()));
        }

        User user = Setup.getAPI().getUserManager().getUser(killer);
        double coins = (double) user.getInfos().getObject("coins");
        coins = coins + 0.3;
        user.getInfos().addObject("coins", String.valueOf(coins));
        Setup.getAPI().getUserManager().updateUser(user);
    }
}
