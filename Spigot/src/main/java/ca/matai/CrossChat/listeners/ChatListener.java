package ca.matai.CrossChat.listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    public String PlayerName;

    public String Message;

    public ChatListener(String _player, String _message){
        this.PlayerName = _player;
        this.Message = _message;
    }


    public byte[] getPluginMessage() {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(this.PlayerName);
        out.writeUTF(this.Message);
        return out.toByteArray();
    }

}
