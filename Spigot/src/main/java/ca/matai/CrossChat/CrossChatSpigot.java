package ca.matai.CrossChat;

import ca.matai.CrossChat.listeners.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;


public class CrossChatSpigot extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, this.getConfig().getString("communicationChannel"));

        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }



    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) throws IOException {
        ChatListener info = new ChatListener(event.getPlayer().getDisplayName(),event.getMessage());
        event.getPlayer().sendPluginMessage(JavaPlugin.getPlugin(CrossChatSpigot.class),this.getConfig().getString("communicationChannel"),info.getPluginMessage());
        event.setCancelled(true);
    }


}