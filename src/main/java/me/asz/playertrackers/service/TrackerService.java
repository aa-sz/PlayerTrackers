package me.asz.playertrackers.service;

import de.tr7zw.nbtapi.NBTItem;
import me.asz.playertrackers.ItemUtil;
import me.asz.playertrackers.event.TrackerCreateEvent;
import me.asz.playertrackers.event.TrackerDeleteEvent;
import me.asz.playertrackers.event.TrackerUpdateHolderEvent;
import me.asz.playertrackers.event.reason.TrackerDeleteReason;
import me.asz.playertrackers.service.holder.TrackedEntity;
import me.asz.playertrackers.service.holder.TrackedHolder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TrackerService {
    private static TrackerService service;

    private final HashMap<UUID, Tracker> trackerMap = new HashMap<>();
    private final HashMap<Player, ArrayList<UUID>> ownerMap = new HashMap<>();

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

        TrackerCreateEvent createEvent = new TrackerCreateEvent(tracker);
        Bukkit.getPluginManager().callEvent(createEvent);

        tracker = createEvent.getTracker();

        if (!createEvent.isCancelled()) {
            addTracker(tracker);

            return tracker.getUUID();
        }

        return null;
    }

    public void addTracker(Tracker tracker) {
        ArrayList<UUID> ownerTrackerList = ownerMap.getOrDefault(tracker.getOwner(), new ArrayList<>());

        ownerTrackerList.add(tracker.getUUID());

        ownerMap.put(tracker.getOwner(), ownerTrackerList);
        trackerMap.put(tracker.getUUID(), tracker);
    }

    //region deleteTracker

    public void deleteTracker(UUID uuid, TrackerDeleteReason reason) {
        TrackerDeleteEvent deleteEvent = new TrackerDeleteEvent(trackerMap.get(uuid), reason);
        Bukkit.getPluginManager().callEvent(deleteEvent);

        Tracker tracker = deleteEvent.getTracker();

        if (!deleteEvent.isCancelled()) {
            Player owner = tracker.getOwner();

            ownerMap.get(owner).remove(tracker.getUUID());
            trackerMap.remove(tracker.getUUID());
        }
    }

    public void deleteTracker(List<UUID> uuidList, TrackerDeleteReason reason) {
        for (UUID uuid : uuidList) {
            deleteTracker(uuid, reason);
        }
    }

    public void deleteTracker(NBTItem nbtItem, TrackerDeleteReason reason) {
        deleteTracker(ItemUtil.getTrackers(nbtItem), reason);
    }

    public void deleteTracker(ItemStack item, TrackerDeleteReason reason) {
        deleteTracker(ItemUtil.getTrackers(item), reason);
    }

    public void deleteTracker(UUID uuid) {
        deleteTracker(uuid, TrackerDeleteReason.OTHER);
    }

    public void deleteTracker(NBTItem nbtItem) {
        deleteTracker(nbtItem, TrackerDeleteReason.OTHER);
    }

    public void deleteTracker(ItemStack item) {
        deleteTracker(item, TrackerDeleteReason.OTHER);
    }

    //endregion

    public void updateHolder(UUID uuid, TrackedHolder newHolder) {
        TrackerUpdateHolderEvent updateEvent = new TrackerUpdateHolderEvent(trackerMap.get(uuid), newHolder);
        Bukkit.getPluginManager().callEvent(updateEvent);

        if (!updateEvent.isCancelled())
            updateEvent.getTracker().setCurrentHolder(newHolder);
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
