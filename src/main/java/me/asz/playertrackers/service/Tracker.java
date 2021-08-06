package me.asz.playertrackers.service;

import me.asz.playertrackers.service.holder.TrackerHolder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class Tracker {

    private UUID uuid;
    private Player owner;
    private TrackerHolder currentHolder;

    public Tracker(UUID _uuid, Player _owner, TrackerHolder _currentHolder) {
        uuid = _uuid;
        owner = _owner;
        currentHolder = _currentHolder;
    }

    public UUID getUUID() { return uuid; }
    public Player getOwner() { return owner; }
    public TrackerHolder getCurrentHolder() { return currentHolder; }

    public void setCurrentHolder(TrackerHolder _currentHolder) { currentHolder = _currentHolder; }

}
