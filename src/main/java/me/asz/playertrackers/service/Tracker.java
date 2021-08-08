package me.asz.playertrackers.service;

import me.asz.playertrackers.service.holder.TrackedHolder;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Tracker {

    private final UUID uuid;
    private final Player owner;
    private TrackedHolder currentHolder;

    public Tracker(UUID _uuid, Player _owner, TrackedHolder _currentHolder) {
        uuid = _uuid;
        owner = _owner;
        currentHolder = _currentHolder;
    }

    public UUID getUUID() { return uuid; }
    public Player getOwner() { return owner; }
    public TrackedHolder getCurrentHolder() { return currentHolder; }

    public void setCurrentHolder(TrackedHolder _currentHolder) { currentHolder = _currentHolder; }

}
