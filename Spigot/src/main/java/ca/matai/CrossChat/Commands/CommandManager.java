package ca.matai.CrossChat.Commands;

import ca.matai.CrossChat.Commands.SubCommands.ToggleCrossTalk;
import ca.matai.CrossChat.CrossChatSpigot;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {
    private List<SubCommand> lstSubCommands = new ArrayList<>();

    public CommandManager() {
        lstSubCommands.add(new ToggleCrossTalk());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player ply = (Player) sender;
            if (args.length > 0) {

                SubCommand sub = getSubCommands().stream().filter(x -> x.getName().equalsIgnoreCase(args[0])).findFirst().orElse(null);
                if (sub == null) {
                    helpMessage(ply);
                    return true;
                }
                sub.perform(ply, args);
            } else {
                helpMessage(ply);
            }

        } else {
            JavaPlugin.getPlugin(CrossChatSpigot.class).getLogger().info(ChatColor.RED + "You cannot use this command in console.");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }

    public List<SubCommand> getSubCommands() {
        return lstSubCommands;
    }

    public void helpMessage(Player p) {
        p.sendMessage(ChatColor.YELLOW + "" + ChatColor.AQUA + "@Player" + ChatColor.YELLOW + "]");
        p.sendMessage(ChatColor.AQUA + "Version: " + JavaPlugin.getPlugin(CrossChatSpigot.class).getDescription().getVersion());
        p.sendMessage(ChatColor.AQUA + "Available commands: ");
        getSubCommands().forEach(x -> p.sendMessage(ChatColor.AQUA + x.getSyntax()));
    }

}
