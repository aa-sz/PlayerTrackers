package me.asz.playertrackers;

import me.asz.playertrackers.command.GetTrackerCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerTrackers extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("gettracker").setExecutor(new GetTrackerCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
