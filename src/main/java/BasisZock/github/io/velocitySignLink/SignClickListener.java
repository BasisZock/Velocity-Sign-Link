package BasisZock.github.io.velocitySignLink;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignClickListener implements Listener {
    private final Plugin plugin;
    private final Map<String, Long> cooldowns = new HashMap<>();
    private long cooldownTime; // in milliseconds

    public SignClickListener(Plugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    // Loads the cooldown value from the configuration file
    private void loadConfig() {
        FileConfiguration config = plugin.getConfig();
        cooldownTime = config.getLong("cooldown", 1000);
    }

    // Sends the player to the specified server via BungeeCord
    public void sendPlayerToServer(Player player, String serverName) {
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(byteArray);
            out.writeUTF("Connect");
            out.writeUTF(serverName);
            player.sendPluginMessage(plugin, "BungeeCord", byteArray.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Listens for player interaction with a sign and applies cooldown logic
    @EventHandler
    public void onSignClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (block == null || !(block.getState() instanceof Sign)) {
            return;
        }

        String playerName = player.getName();
        long currentTime = System.currentTimeMillis();

        // Check if player is under cooldown (unless they have bypass permission)
        if (!player.hasPermission("VeloSign.bypass.cooldown") && cooldowns.containsKey(playerName)) {
            long lastUse = cooldowns.get(playerName);
            if (currentTime - lastUse < cooldownTime) {
                long remaining = (cooldownTime - (currentTime - lastUse)) / 1000;
                player.sendMessage(ChatColor.RED + "Please wait " + remaining + " more second(s) before using this sign.");
                return;
            }
        }

        // Retrieve sign data from the database and send the player to the server
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        String world = player.getWorld().getName();
        String text = DatabaseManager.getSignTextByCoordinates(x, y, z, world);

        if (text != null) {
            sendPlayerToServer(player, text);
            cooldowns.put(playerName, currentTime);
        } else {
            player.sendMessage(ChatColor.YELLOW + "No data found for this sign.");
        }
    }
}
