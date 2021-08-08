package me.asz.playertrackers.event;

import me.asz.playertrackers.service.Tracker;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class TrackerCreateEvent extends TrackerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public TrackerCreateEvent(Tracker _tracker) {
        super(_tracker);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
