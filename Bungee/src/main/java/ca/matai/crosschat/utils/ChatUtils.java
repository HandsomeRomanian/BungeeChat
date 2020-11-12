package ca.matai.crosschat.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatUtils {

    public static TextComponent formatColors(String message) {
        return new TextComponent(ChatColor.translateAlternateColorCodes('&', message));
        // message = ChatColor.translateAlternateColorCodes('&', message);
        // Pattern hexPattern = Pattern.compile("(#([a-fA-F0-9]{6}))");
        // Matcher hexMatch = hexPattern.matcher(message);
        // if(hexMatch.find()){
        //     StringBuffer buffer = new StringBuffer();
        //     do{
        //             hexMatch.appendReplacement(buffer, "" + ChatColor.of(hexMatch.group(1)));
        //     }while(hexMatch.find());
        //     hexMatch.appendTail(buffer);
        //     message = buffer.toString();
        // }
        // return message;

        // String[] array = message.split("&");
        // ComponentBuilder test = new ComponentBuilder("");
        // for (int i = 0; i < array.length; i++) {
        //     String signed = array[i];
        //     char code = 'r';
        //     if (i == 0 && message.charAt(0) == '&') {
        //         code = signed.charAt(0);
        //         signed = signed.substring(1);
        //     } else if (i != 0) {
        //         code = signed.charAt(0);
        //         signed = signed.substring(1);
        //     }
        //     test.append(signed);
        //     switch (code) {
        //         case '0':
        //             test.color(ChatColor.BLACK);
        //             break;
        //         case '1':
        //             test.color(ChatColor.DARK_BLUE);
        //             break;
        //         case '2':
        //             test.color(ChatColor.DARK_GREEN);
        //             break;
        //         case '3':
        //             test.color(ChatColor.DARK_AQUA);
        //             break;
        //         case '4':
        //             test.color(ChatColor.DARK_RED);
        //             break;
        //         case '5':
        //             test.color(ChatColor.DARK_PURPLE);
        //             break;
        //         case '6':
        //             test.color(ChatColor.GOLD);
        //             break;
        //         case '7':
        //             test.color(ChatColor.GRAY);
        //             break;
        //         case '8':
        //             test.color(ChatColor.DARK_GRAY);
        //             break;
        //         case '9':
        //             test.color(ChatColor.BLUE);
        //             break;
        //         case 'a':
        //             test.color(ChatColor.GREEN);
        //             break;
        //         case 'b':
        //             test.color(ChatColor.AQUA);
        //             break;
        //         case 'c':
        //             test.color(ChatColor.RED);
        //             break;
        //         case 'd':
        //             test.color(ChatColor.LIGHT_PURPLE);
        //             break;
        //         case 'e':
        //             test.color(ChatColor.YELLOW);
        //             break;
        //         case 'f':
        //             test.color(ChatColor.WHITE);
        //             break;
        //         case 'l':
        //             test.bold(true);
        //             break;
        //         case 'm':
        //             test.strikethrough(true);
        //             break;
        //         case 'n':
        //             test.underlined(true);
        //             break;
        //         case 'o':
        //             test.italic(true);
        //             break;
        //         case 'r':
        //             test.color(ChatColor.RESET);
        //             test.underlined(false);
        //             test.strikethrough(false);
        //             test.bold(false);
        //             test.italic(false);
        //             break;
        //         case 'k':
        //             test.color(ChatColor.MAGIC);
        //     }

        // }
        // return test;
    }

}
