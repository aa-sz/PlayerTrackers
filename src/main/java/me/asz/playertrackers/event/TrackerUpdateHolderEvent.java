package me.asz.playertrackers.event;

import me.asz.playertrackers.service.Tracker;
import me.asz.playertrackers.service.holder.TrackedHolder;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class TrackerUpdateHolderEvent extends TrackerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    protected TrackedHolder newHolder;

    public TrackerUpdateHolderEvent(Tracker _tracker, TrackedHolder _newHolder) {
        super(_tracker);
        newHolder = _newHolder;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public TrackedHolder getCurrentHolder() {
        return tracker.getCurrentHolder();
    }

    public TrackedHolder getNewHolder() {
        return newHolder;
    }

    public void setHolder(TrackedHolder holder) {
        newHolder = holder;
    }

}
