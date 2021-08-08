package me.asz.playertrackers.listener;

import me.asz.playertrackers.event.TrackerCreateEvent;
import me.asz.playertrackers.event.TrackerDeleteEvent;
import me.asz.playertrackers.event.TrackerUpdateHolderEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LogListener implements Listener {

    @EventHandler
    public void onTrackerCreate(TrackerCreateEvent e) {
        Bukkit.getLogger().info("[Trackers] Created {" + e.getTracker().getUUID().toString() + "}");
    }

    @EventHandler
    public void onTrackerDelete(TrackerDeleteEvent e) {
        Bukkit.getLogger().info("[Trackers] Deleted {" + e.getTracker().getUUID().toString() + "} reason {" + e.getReason().name() + "}");
    }

    @EventHandler
    public void onTrackerUpdate(TrackerUpdateHolderEvent e) {
        Bukkit.getLogger().info("[Trackers] Updated {" + e.getTracker().getUUID().toString() + "} " + e.getNewHolder().getLocation().toString());
    }

}
