package me.asz.playertrackers.event;

import me.asz.playertrackers.service.Tracker;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public abstract class TrackerEvent extends Event implements Cancellable {

    protected Tracker tracker;
    protected boolean isCancelled;

    protected TrackerEvent(Tracker _tracker) {
        tracker = _tracker;
        isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Tracker _tracker) {
        tracker = _tracker;
    }

}
