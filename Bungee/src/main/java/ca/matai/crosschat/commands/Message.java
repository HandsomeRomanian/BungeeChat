package ca.matai.crosschat.commands;

import java.util.ArrayList;

import ca.matai.crosschat.utils.ChatUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

public class Message extends Command implements TabExecutor {

    public Message() {
        super("message", "crosschat.msg", new String[] { "message", "dm", "msg", "pm" });
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        try {
            ProxiedPlayer recipient = ProxyServer.getInstance().getPlayer(args[0]);

            ProxyServer.getInstance().getLogger().info(sender.getName());
            String outputRecipient = "&6[&c" + sender.getName() + "&6-> &cme &6]&r ";
            String outputSender = "&6[&cme &6-> &c" + recipient.getDisplayName() + "&6]&r ";
            for (int i = 1; i < args.length; i++) {
                outputSender += args[i] + " ";
                outputRecipient += args[i] + " ";
            }
            recipient.sendMessage(new TextComponent(ChatUtils.formatColors(outputRecipient)));
            sender.sendMessage(new TextComponent(ChatUtils.formatColors(outputSender)));
        } catch (Exception e) {
            sender.sendMessage(new TextComponent("Recipient is not online."));
        }

    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        ArrayList<String> output = new ArrayList<>();
        if (args.length == 1) {
            String check = args[args.length - 1];
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                if (player.getName().toLowerCase().startsWith(check)) {
                    output.add(player.getName());
                }
            }
        }
        return output;
    }

}
