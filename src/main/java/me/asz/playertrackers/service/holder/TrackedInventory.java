package me.asz.playertrackers.service.holder;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

public class TrackedInventory implements TrackerHolder {

    private Inventory inventory;

    public TrackedInventory(Inventory _inventory) {
        inventory = _inventory;
    }

    @Override
    public Location getLocation() {
        return inventory.getLocation();
    }
}
