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
 * Command to save the target server of a sign.
 * Usage: /savesign <serverName>
 * The command saves the sign data to the database.
 */

public class SaveSignCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }
        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /savesign <serverName>");
            return true;
        }

        String textInput = args[0];
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

        // Save sign data to the database
        DatabaseManager.saveSign(x, y, z, world, textInput);
        player.sendMessage(ChatColor.GREEN + "Sign saved successfully!");
        return true;
    }
}
