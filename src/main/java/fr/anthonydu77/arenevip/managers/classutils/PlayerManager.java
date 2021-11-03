package fr.anthonydu77.arenevip.managers.classutils;

import io.rqndomhax.managers.IUserManager;
import io.rqndomhax.utils.User;

/**
 * Created by Anthonydu77 03/11/2021 inside the package - fr.anthonydu77.arenevip.managers.classutils
 */

public class PlayerManager {

    private final User user;
    private final IUserManager userManager;

    public PlayerManager(User user, IUserManager userManager) {
        this.user = user;
        this.userManager = userManager;
    }

    public String getInfo(String infoPath, String defaultValue) {

        Object result = user.getInfos().getObject(infoPath);

        if (!(result instanceof String)) {
            result = defaultValue;
            user.getInfos().addObject(infoPath, defaultValue);
        }

        return (String) result;
    }

    public int getUserCoins() {
        String userCoins = getInfo("coins", "200");
        return Integer.parseInt(userCoins);
    }

    public void setUserCoins(int coins) {
        user.getInfos().addObject("coins", String.valueOf(coins));
        userManager.updateUser(user);
    }

    public void addUserCoins(int coins) {
        String currentCoins = getInfo("coins", "200");

        user.getInfos().addObject("coins",
                String.valueOf(
                        Integer.parseInt(currentCoins) + coins
                ));
        userManager.updateUser(user);
    }

    public int getUserVersaCoins() {
        String userCoins = getInfo("versaCoins", "0");
        return Integer.parseInt(userCoins);
    }

    public void setUserVersaCoins(int coins) {
        user.getInfos().addObject("versaCoins", String.valueOf(coins));
        userManager.updateUser(user);
    }

    public void addUserVersaCoins(int coins) {
        String currentCoins = getInfo("versaCoins", "200");

        user.getInfos().addObject("versaCoins",
                String.valueOf(
                        Integer.parseInt(currentCoins) + coins
                ));
        userManager.updateUser(user);
    }

}