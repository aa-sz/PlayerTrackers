package me.asz.playertrackers.command;

import me.asz.playertrackers.service.TrackerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class TrackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            List<UUID> playerTrackers = TrackerService.getInstance().getTrackers(player);

            if (playerTrackers != null && playerTrackers.size() > 0) {
                player.sendMessage(TrackerService.getInstance().getTrackerLocation(playerTrackers.get(0)).toString());
            }

            return true;
        }

        return false;
    }
}
