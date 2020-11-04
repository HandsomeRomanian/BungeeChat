package ca.matai.crosschat;

import ca.matai.crosschat.listeners.MessageListener;
import net.md_5.bungee.api.plugin.Plugin;


public class CrossChatBungee extends Plugin  {


    @Override
    public void onEnable() {
        getProxy().registerChannel( "message:sent" );
        getProxy().getPluginManager().registerListener(this,new MessageListener());
    }



}