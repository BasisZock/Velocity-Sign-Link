package BasisZock.github.io.velocitySignLink.commands;

import BasisZock.github.io.velocitySignLink.DatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class DeleteSignCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }

        Block targetBlock = player.getTargetBlockExact(5);
        if (targetBlock == null || !(targetBlock.getState() instanceof Sign)) {
            player.sendMessage(ChatColor.RED + "You are not looking at a sign!");
            return true;
        }
            // Retrieve sign location
        int x = targetBlock.getX();
        int y = targetBlock.getY();
        int z = targetBlock.getZ();
        String world = player.getWorld().getName();
        Integer wasDeleted = DatabaseManager.deleteSign(x, y, z, world);
        if (wasDeleted == 2) {
            player.sendMessage(ChatColor.GREEN + "Sign data deleted successfully!");
        }
        else if (wasDeleted == 1) {
            player.sendMessage(ChatColor.RED + "Failed to delete sign, this sign has no data.");
        }
        else {
            player.sendMessage(ChatColor.RED + "There was an error deleting the sign, check the console for more information.");
        }
        return true;
    }
}
