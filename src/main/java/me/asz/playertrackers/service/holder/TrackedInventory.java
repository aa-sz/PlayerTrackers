package me.asz.playertrackers.service.holder;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

public class TrackedInventory extends TrackedHolder {

    private Inventory inventory;

    public TrackedInventory(Inventory _inventory) {
        inventory = _inventory;
        updateLocation();
    }

    @Override
    protected Location retrieveLocation() {
        return inventory.getLocation();
    }
}
