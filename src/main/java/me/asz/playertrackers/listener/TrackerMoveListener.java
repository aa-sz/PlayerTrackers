package me.asz.playertrackers.listener;

import de.tr7zw.nbtapi.NBTItem;
import me.asz.playertrackers.ItemUtil;
import me.asz.playertrackers.service.TrackerService;
import me.asz.playertrackers.service.holder.TrackedEntity;
import me.asz.playertrackers.service.holder.TrackedInventory;
import me.asz.playertrackers.service.holder.TrackedHolder;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class TrackerMoveListener implements Listener {

    private void handleItemMove(ItemStack item, TrackedHolder holder) {
        List<UUID> itemTrackers = ItemUtil.getTrackers(item);
        for (UUID uuid : itemTrackers) {
            Bukkit.getLogger().info("Tracker {" + uuid.toString() + "} moved!");
            TrackerService.getInstance().updateHolder(uuid, holder);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onHopperMove(InventoryMoveItemEvent e) {
        handleItemMove(e.getItem(), new TrackedInventory(e.getDestination()));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onHopperPickup(InventoryPickupItemEvent e) {
        handleItemMove(e.getItem().getItemStack(), new TrackedInventory(e.getInventory()));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onArmorStandInteract(PlayerArmorStandManipulateEvent e) {
        if (e.getPlayerItem() != null)
            handleItemMove(e.getPlayerItem(), new TrackedEntity(e.getRightClicked()));
        else
            handleItemMove(e.getArmorStandItem(), new TrackedEntity(e.getPlayer()));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityPickup(EntityPickupItemEvent e) {
        handleItemMove(e.getItem().getItemStack(), new TrackedEntity(e.getEntity()));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onItemEntitySpawn(ItemSpawnEvent e) {
        handleItemMove(e.getEntity().getItemStack(), new TrackedEntity(e.getEntity()));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent e) {
        Bukkit.getLogger().info(e.getAction().toString());

        ItemStack clickedItem = e.getCurrentItem();
        ItemStack hotbarItem;

        switch (e.getAction()) {
            case HOTBAR_MOVE_AND_READD:
                hotbarItem = e.getWhoClicked().getInventory().getItem(e.getHotbarButton());

                handleItemMove(hotbarItem, new TrackedInventory(e.getClickedInventory()));
                handleItemMove(clickedItem, new TrackedInventory(e.getView().getBottomInventory()));
                break;
            case HOTBAR_SWAP:
                hotbarItem = e.getWhoClicked().getInventory().getItem(e.getHotbarButton());

                if (hotbarItem != null)
                    handleItemMove(hotbarItem, new TrackedInventory(e.getClickedInventory()));
                else
                    handleItemMove(clickedItem, new TrackedInventory(e.getView().getBottomInventory()));
                break;
            case MOVE_TO_OTHER_INVENTORY:
                if (e.getClickedInventory().equals(e.getView().getTopInventory()))
                    handleItemMove(e.getCurrentItem(), new TrackedInventory(e.getView().getBottomInventory()));
                else
                    handleItemMove(e.getCurrentItem(), new TrackedInventory(e.getView().getTopInventory()));
                break;
            case PLACE_ALL:
            case PLACE_ONE:
            case PLACE_SOME:
                // This is probably broken
                handleItemMove(e.getCursor(), new TrackedInventory(e.getClickedInventory()));
                break;
        }
    }

}
