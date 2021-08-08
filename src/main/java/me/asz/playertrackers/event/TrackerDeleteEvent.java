package me.asz.playertrackers.event;

import me.asz.playertrackers.event.reason.TrackerDeleteReason;
import me.asz.playertrackers.service.Tracker;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class TrackerDeleteEvent extends TrackerEvent{

    private static final HandlerList HANDLER_LIST = new HandlerList();

    protected final TrackerDeleteReason reason;

    public TrackerDeleteEvent(Tracker _tracker, TrackerDeleteReason _reason) {
        super(_tracker);
        reason = _reason;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public TrackerDeleteReason getReason() {
        return reason;
    }

}
