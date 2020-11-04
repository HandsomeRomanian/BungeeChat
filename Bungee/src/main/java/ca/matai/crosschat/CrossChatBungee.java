package ca.matai.crosschat;

import ca.matai.crosschat.listeners.listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;


public class CrossChatBungee extends Plugin  {

    public static Configuration configuration;

    @Override
    public void onEnable() {
        getProxy().registerChannel( "message:sent" );
        getProxy().getPluginManager().registerListener(this,new listener());
    }



}