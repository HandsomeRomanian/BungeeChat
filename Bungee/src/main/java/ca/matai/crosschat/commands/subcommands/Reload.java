package ca.matai.crosschat.commands.subcommands;

import ca.matai.crosschat.CrossChatBungee;
import ca.matai.crosschat.commands.SubCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;

public class Reload extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reload the plugin configuration from file.";
    }

    @Override
    public String getSyntax() {
        return "/crosschat reload";
    }

    @Override
    public String getPermission() {
        return "crosschat.reload";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (sender instanceof  ProxiedPlayer)
            if(!this.permissionCheck((ProxiedPlayer)sender))
                return;
        if (!CrossChatBungee.instance.getDataFolder().exists()) {
            CrossChatBungee.instance.getDataFolder().mkdir();
            File file = new File(CrossChatBungee.instance.getDataFolder(), "config.yml");
            if (!file.exists()) {
                try (InputStream in = CrossChatBungee.instance.getResourceAsStream("config.yml")) {
                    Files.copy(in, file.toPath());
                } catch (Exception e) {
                }
            }
        }
        try {
            CrossChatBungee.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(CrossChatBungee.instance.getDataFolder(), "config.yml"));
            sender.sendMessage(new TextComponent(ChatColor.BLUE + "[CrossChat]" + ChatColor.RESET + " Reload completed." ));
        } catch (IOException e) {
            CrossChatBungee.instance.getProxy().getLogger().log(Level.SEVERE, ChatColor.BLUE + "[CrossChat]" + ChatColor.RED + " Configuration file could not be loaded, using default config.");
            sender.sendMessage(new TextComponent(ChatColor.BLUE + "[CrossChat]" + ChatColor.YELLOW + " Reload failed" ));
        }

    }
}
