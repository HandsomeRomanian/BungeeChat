package ca.matai.CrossChat;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import ca.matai.CrossChat.listeners.ChatListener;
import me.clip.placeholderapi.PlaceholderAPI;

import java.io.IOException;

public class CrossChatSpigot extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            /*
             * We register the EventListener here, when PlaceholderAPI is installed. Since
             * all events are in the main class (this class), we simply use "this"
             */
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "papi ecloud download Player");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "papi reload");
        }

        this.getServer().getMessenger().registerOutgoingPluginChannel(this,
                this.getConfig().getString("communicationChannel"));

        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) throws IOException {

        event.setCancelled(true);
        String playerName = "";
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            try {
                playerName = getConfig().getString("namedisplay");
            } catch (Exception e) {
                playerName += "%player_displayname%";
            }
            PlaceholderAPI.setPlaceholders(event.getPlayer(), playerName);
        } else {
            playerName += event.getPlayer().getDisplayName();
        }

        ChatListener info = new ChatListener(playerName, event.getMessage());
        event.getPlayer().sendPluginMessage(JavaPlugin.getPlugin(CrossChatSpigot.class),
                this.getConfig().getString("communicationChannel"), info.getPluginMessage());
    }

}