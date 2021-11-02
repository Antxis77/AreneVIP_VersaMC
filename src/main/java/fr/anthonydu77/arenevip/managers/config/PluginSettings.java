package fr.anthonydu77.arenevip.managers.config;

import org.bukkit.World;

/**
 * Created by Anthonydu77 02/11/2021 inside the package - fr.anthonydu77.arenevip.managers.config
 */

public class PluginSettings {

    private int x;
    private int y;
    private int z;
    private int yaw;
    private int pitch;

    private String world;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getYaw() {
        return yaw;
    }

    public void setYaw(int yaw) {
        this.yaw = yaw;
    }

    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }
}
