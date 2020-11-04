package ca.matai.CrossChat;

import ca.matai.CrossChat.listeners.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class CrossChatSpigot extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "crosschat:message");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }
    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        ChatListener info = new ChatListener(event.getPlayer().getDisplayName(),event.getMessage());
        event.getPlayer().sendPluginMessage(JavaPlugin.getPlugin(CrossChatSpigot.class),"crosschat:message",info.getPluginMessage());
        event.setCancelled(true);
    }


}