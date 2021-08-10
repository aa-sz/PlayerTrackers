package me.asz.playertrackers;

import de.tr7zw.nbtapi.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemUtil {

    public static List<UUID> getTrackers(NBTItem item) {
        NBTCompoundList compoundList = item.getCompoundList("tracker");

        List<UUID> list = new ArrayList<>();

        for (NBTListCompound compound : compoundList) {
            list.add(compound.getUUID("uuid"));
        }

        return list;
    }

    public static List<UUID> getTrackers(ItemStack item) {
        return getTrackers(new NBTItem(item));
    }

    public static NBTItem addTracker(NBTItem item, UUID uuid) {
        if (!getTrackers(item).contains(uuid)) {
            NBTCompoundList compoundList = item.getCompoundList("tracker");
            NBTListCompound newCompound = compoundList.addCompound();

            newCompound.setUUID("uuid", uuid);
        }

        return item;
    }

    public static ItemStack addTracker(ItemStack item, UUID uuid) {
        return addTracker(new NBTItem(item), uuid).getItem();
    }

}
