package me.asz.playertrackers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class Tracker {

    private UUID uuid;
    private Player owner;
    private Inventory currentHolder;

    public Tracker(UUID _uuid, Player _owner, Inventory _currentHolder) {
        uuid = _uuid;
        owner = _owner;
        currentHolder = _currentHolder;
    }

    public UUID getUUID() { return uuid; }
    public Player getOwner() { return owner; }
    public Inventory getCurrentHolder() { return currentHolder; }

    public void setCurrentHolder(Inventory _currentHolder) { currentHolder = _currentHolder; }

}
