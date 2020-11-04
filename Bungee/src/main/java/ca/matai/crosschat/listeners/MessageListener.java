package ca.matai.crosschat.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class listener implements Listener {

    @EventHandler
    public void PluginMessageListener(PluginMessageEvent event){
        if (event.getTag().equalsIgnoreCase("message:sent")){
            ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
            String playername = in.readUTF();
            String chatmessage = in.readUTF();
            ProxyServer.getInstance().broadcast(new TextComponent(playername + "→" + chatmessage));
        }
    }

}
