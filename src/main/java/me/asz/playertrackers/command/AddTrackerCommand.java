package me.asz.playertrackers.command;

import me.asz.playertrackers.ItemUtil;
import me.asz.playertrackers.service.TrackerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class AddTrackerCommand implements CommandExecutor  {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID trackerUUID = TrackerService.getInstance().createTracker(player);

            ItemStack holdingItem = player.getInventory().getItemInMainHand();
            holdingItem = ItemUtil.addTracker(holdingItem, trackerUUID);

            player.getInventory().setItemInMainHand(holdingItem);

            return true;
        }

        return false;
    }
}
