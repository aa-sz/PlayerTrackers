package me.asz.playertrackers.service;

import de.tr7zw.nbtapi.NBTItem;
import me.asz.playertrackers.service.holder.TrackedEntity;
import me.asz.playertrackers.service.holder.TrackedHolder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TrackerService {
    private static TrackerService service;

    private HashMap<UUID, Tracker> trackerMap = new HashMap<>();
    private HashMap<Player, ArrayList<UUID>> ownerMap = new HashMap<>();

    public TrackerService() {
        service = this;
    }

    public static TrackerService getInstance() {
        if (service == null)
            service = new TrackerService();

        return service;
    }

    public boolean hasTracker(UUID tracker) {
        return trackerMap.containsKey(tracker);
    }

    public UUID createTracker(Player owner) {
        Tracker tracker = new Tracker(UUID.randomUUID(), owner, new TrackedEntity(owner));
        addTracker(tracker);

        return tracker.getUUID();
    }

    public void addTracker(Tracker tracker) {
        ArrayList<UUID> ownerTrackerList = ownerMap.getOrDefault(tracker.getOwner(), new ArrayList<>());

        ownerTrackerList.add(tracker.getUUID());

        ownerMap.put(tracker.getOwner(), ownerTrackerList);
        trackerMap.put(tracker.getUUID(), tracker);
    }

    public void deleteTracker(UUID uuid) {
        Player owner = trackerMap.get(uuid).getOwner();

        ownerMap.get(owner).remove(uuid);

        trackerMap.remove(uuid);
    }

    public void deleteTracker(NBTItem nbtItem) {
        deleteTracker(nbtItem.getUUID("tracker"));
    }

    public void deleteTracker(ItemStack item) {
        deleteTracker(new NBTItem(item));
    }

    public void updateHolder(UUID uuid, TrackedHolder newHolder) {
        trackerMap.get(uuid).setCurrentHolder(newHolder);
    }

    public ArrayList<UUID> getTrackers(Player owner) {
        if(owner == null)
            return null;

        return ownerMap.get(owner);
    }

    public Location getTrackerLocation(UUID uuid) {
        Tracker tracker = trackerMap.get(uuid);
        if (tracker != null)
            return tracker.getCurrentHolder().getLocation();
        else
            return null;
    }

}
