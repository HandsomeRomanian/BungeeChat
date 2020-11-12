package ca.matai.crosschat.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public abstract class SubCommand {

    public Boolean permissionCheck(ProxiedPlayer p){
        if(!p.hasPermission(getPermission())){
            p.sendMessage(new TextComponent(ChatColor.AQUA+"[CrossChat]" + ChatColor.RED + "You do not have the permission to use this command!"));
            return false;
        }
        return true;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract String getPermission();

    public abstract void perform(CommandSender player, String args[]);
}