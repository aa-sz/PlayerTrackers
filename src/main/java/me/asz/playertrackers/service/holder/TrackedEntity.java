package me.asz.playertrackers.service.holder;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class TrackedEntity extends TrackerHolder {
    private Entity entity;

    public TrackedEntity(Entity _entity) {
        entity = _entity;
        updateLocation();
    }

    @Override
    protected Location retrieveLocation() {
        return entity.getLocation();
    }
}
