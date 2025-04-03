package BasisZock.github.io.velocitySignLink.commands;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Command to update the cooldown duration for sign usage.
 * Usage: /signcooldown <milliseconds>
 * Updates the config file with the new cooldown value.
 */

public class SignCooldownCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }
        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /signcooldown <milliseconds>");
            return true;
        }

        String cooldown = args[0];
        String filePath = "plugins/VelocitySignLink/config.yml";
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith("cooldown:")) {
                    int seconds = Integer.parseInt(cooldown) / 1000;
                    lines.set(i, "cooldown: " + cooldown + " # " + seconds + " second(s)");
                    break;
                }
            }
            Files.write(Paths.get(filePath), lines);
            player.sendMessage(ChatColor.GREEN + "Cooldown updated successfully!");
        } catch (IOException e) {
            player.sendMessage(ChatColor.RED + "Error updating cooldown. Check server logs.");
            e.printStackTrace();
        }
        return true;
    }
}
