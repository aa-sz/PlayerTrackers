package me.asz.playertrackers.listener;

import de.tr7zw.nbtapi.NBTItem;
import me.asz.playertrackers.service.TrackerService;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class TrackerPersistenceListener implements Listener {

    private boolean isItemTracker(ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        return nbtItem.hasKey("tracker") && TrackerService.getInstance().hasTracker(nbtItem.getUUID("tracker"));
    }

    @EventHandler
    public void onItemEntityDespawn(ItemDespawnEvent e) {
        e.setCancelled(isItemTracker(e.getEntity().getItemStack()));
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Item) {
            ItemStack item = ((Item) e.getEntity()).getItemStack();

            if (isItemTracker(item)) {
                e.setCancelled(true);

                TrackerService.getInstance().deleteTracker(item);
                e.getEntity().remove();
            }
        }
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e) {
        if (isItemTracker(e.getItem())) {
            TrackerService.getInstance().deleteTracker(e.getItem());
        }
    }

    @EventHandler
    public void onItemBreak(PlayerItemBreakEvent e) {
        if (isItemTracker(e.getBrokenItem())) {
            TrackerService.getInstance().deleteTracker(e.getBrokenItem());
        }
    }

}
