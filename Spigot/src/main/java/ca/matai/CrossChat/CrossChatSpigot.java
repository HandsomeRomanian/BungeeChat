package ca.matai.CrossChat;

import ca.matai.CrossChat.listeners.ChatListener;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;

public class CrossChatSpigot extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "message:sent");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }
    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        ChatListener info = new ChatListener(event.getPlayer().getDisplayName(),event.getMessage());
        event.getPlayer().sendPluginMessage(JavaPlugin.getPlugin(CrossChatSpigot.class),"message:sent",info.getPluginMessage());
        event.setCancelled(true);
    }


}