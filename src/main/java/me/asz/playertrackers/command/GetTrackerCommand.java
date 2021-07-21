package me.asz.playertrackers.command;

import de.tr7zw.nbtapi.NBTItem;
import me.asz.playertrackers.TrackerService;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class GetTrackerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID trackerUUID = TrackerService.getInstance().createTracker(player);

            ItemStack item = new ItemStack(Material.ENDER_EYE, 1);

            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6Rastreador de jugadores");
            item.setItemMeta(meta);

            NBTItem nbtItem = new NBTItem(item);
            nbtItem.setUUID("tracker", trackerUUID);
            item = nbtItem.getItem();

            player.getInventory().addItem(item);
        }

        return false;
    }
}
