package me.asz.playertrackers.service.holder;

import org.bukkit.Location;

public abstract class TrackedHolder {

    private Location lastLocation;

    protected void updateLocation() {
        Location updatedLocation = retrieveLocation();
        if (updatedLocation != null) {
            lastLocation = updatedLocation;
        }
    }

    public Location getLocation() {
        updateLocation();
        return lastLocation;
    }

    protected abstract Location retrieveLocation();

}
