package ca.matai.CrossChat;

import ca.matai.CrossChat.listeners.ChatListener;
import org.bukkit.Bukkit;
import com.palmergames.bukkit.towny.TownyAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.Configuration;
import java.io.IOException;


public class CrossChatSpigot extends JavaPlugin implements Listener {

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        config = this.getConfig();

        try {
            TownyAPI.getInstance();
            getLogger().info("Towny Detected");
        }catch (NoClassDefFoundError e){
            getLogger().info("No Towny Detected");
        }

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, this.getConfig().getString("communicationChannel"));
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }



    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) throws IOException {
        if (1==1){

        }
        ChatListener info = new ChatListener(event.getPlayer().getDisplayName(),event.getMessage());
        event.getPlayer().sendPluginMessage(JavaPlugin.getPlugin(CrossChatSpigot.class),this.getConfig().getString("communicationChannel"),info.getPluginMessage());
        event.setCancelled(true);
    }


}