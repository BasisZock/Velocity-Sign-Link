package BasisZock.github.io.velocitySignLink;

import BasisZock.github.io.velocitySignLink.commands.DeleteSignCommand;
import BasisZock.github.io.velocitySignLink.commands.GetSignCommand;
import BasisZock.github.io.velocitySignLink.commands.SaveSignCommand;
import BasisZock.github.io.velocitySignLink.commands.SignCooldownCommand;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        //Enable bStats
        int pluginId = 25343;
        Metrics metrics = new Metrics(this, pluginId);
        // Initialize database and configuration
        DatabaseManager.createDatabase();
        DatabaseManager.connect();

        // Register commands
        getCommand("savesign").setExecutor(new SaveSignCommand());
        getCommand("getsign").setExecutor(new GetSignCommand());
        getCommand("signcooldown").setExecutor(new SignCooldownCommand());
        getCommand("deletesign").setExecutor(new DeleteSignCommand());


        // Register event listeners
        getServer().getPluginManager().registerEvents(new SignClickListener(this), this);

        // Register outgoing channel for BungeeCord connections
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        // Create a default config file if it doesn't exist
        saveDefaultConfig();
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic (if needed)
    }
}
