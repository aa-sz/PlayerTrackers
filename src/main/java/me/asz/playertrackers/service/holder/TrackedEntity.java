package me.asz.playertrackers.service.holder;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class TrackedEntity implements TrackerHolder {
    private Entity entity;

    public TrackedEntity(Entity _entity) {
        entity = _entity;
    }

    @Override
    public Location getLocation() {
        return entity.getLocation();
    }
}
