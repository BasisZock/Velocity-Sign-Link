package BasisZock.github.io.velocitySignLink.commands;

import BasisZock.github.io.velocitySignLink.DatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Command to retrieve sign data and send the player to the linked server.
 * Usage: /getsign
 * Reads the sign the player is looking at, fetches data from the database, and sends the player to the linked server.
 */

public class GetSignCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {
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

        // Fetch sign text from the database
        String text = DatabaseManager.getSignTextByCoordinates(x, y, z, world);
        if (text != null) {
            player.sendMessage(ChatColor.AQUA + "Sign Data: " + ChatColor.WHITE + text);
        } else if (!player.hasPermission("VeloSign.noMessage")) {
            player.sendMessage(ChatColor.YELLOW + "No data found for this sign.");
        }
        return true;
    }
}
