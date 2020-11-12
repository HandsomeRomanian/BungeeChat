package ca.matai.crosschat.commands;

import ca.matai.crosschat.CrossChatBungee;
import ca.matai.crosschat.commands.subcommands.Reload;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends Command implements TabExecutor {
    private List<SubCommand> lstSubCommands = new ArrayList<>();

    public CommandManager() {
        super("CrossChat");
        lstSubCommands.add(new Reload());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
            if (args.length > 0) {
                SubCommand sub = getSubCommands().stream().filter(x -> x.getName().equalsIgnoreCase(args[0])).findFirst().orElse(null);
                if (sub == null) {
                    helpMessage(sender);
                }
                try {
                    sub.perform(sender, args);
                }catch (Exception e){
                }
            } else {
                helpMessage(sender);
            }

    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        ArrayList<String> output = new ArrayList<>();
        for (SubCommand command : getSubCommands()){
            if (args.length < 2){
                if (sender instanceof ProxiedPlayer && sender.hasPermission(command.getPermission()))
                    output.add(command.getName());
            }
        }
        return output;
    }

    public List<SubCommand> getSubCommands() {
        return lstSubCommands;
    }

    public void helpMessage(CommandSender p) {
        p.sendMessage(new TextComponent(ChatColor.AQUA+"[CrossChat]"));
        p.sendMessage(new TextComponent(ChatColor.AQUA + "Version: " + CrossChatBungee.instance.getDescription().getVersion()));
        p.sendMessage(new TextComponent(ChatColor.AQUA + "Available commands: "));
        getSubCommands().forEach(x -> p.sendMessage(new TextComponent(ChatColor.AQUA + x.getSyntax())));
    }
}
