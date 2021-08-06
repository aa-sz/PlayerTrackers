package me.asz.playertrackers;

import me.asz.playertrackers.command.GetTrackerCommand;
import me.asz.playertrackers.command.TrackCommand;
import me.asz.playertrackers.listener.TrackerMoveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerTrackers extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("gettracker").setExecutor(new GetTrackerCommand());
        getCommand("track").setExecutor(new TrackCommand());

        getServer().getPluginManager().registerEvents(new TrackerMoveListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
