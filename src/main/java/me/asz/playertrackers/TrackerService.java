package me.asz.playertrackers;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

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

    public UUID createTracker(Player owner) {
        Tracker tracker = new Tracker(UUID.randomUUID(), owner, owner.getInventory());
        addTracker(tracker);

        return tracker.getUUID();
    }

    public void addTracker(Tracker tracker) {
        ArrayList<UUID> ownerTrackerList = ownerMap.getOrDefault(tracker.getOwner(), new ArrayList<UUID>());

        ownerTrackerList.add(tracker.getUUID());

        ownerMap.put(tracker.getOwner(), ownerTrackerList);
        trackerMap.put(tracker.getUUID(), tracker);
    }

    public void deleteTracker(UUID uuid) {
        Player owner = trackerMap.get(uuid).getOwner();

        ownerMap.get(owner).remove(uuid);

        trackerMap.remove(uuid);
    }

    public void updateHolder(UUID uuid, Inventory newHolder) {
        trackerMap.get(uuid).setCurrentHolder(newHolder);
    }

    @Nullable
    public Location getTrackerLocation(UUID uuid) {
        Tracker tracker = trackerMap.get(uuid);
        if (tracker != null)
            return tracker.getCurrentHolder().getLocation();
        else
            return null;
    }

}
