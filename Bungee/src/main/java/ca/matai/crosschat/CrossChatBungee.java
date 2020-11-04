package ca.matai.crosschat;

import ca.matai.crosschat.commands.CommandManager;
import ca.matai.crosschat.listeners.MessageListener;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;


public class CrossChatBungee extends Plugin  {

    public static Configuration configuration;
    public static CrossChatBungee instance;

    @Override
    public void onEnable() {
        instance = this;
        try {
            configuration = getConfig();
        } catch (Exception e) {
            getProxy().getLogger().log(Level.SEVERE,
                    ChatColor.AQUA+"[CrossChat]"+ChatColor.RED+" Configuration file could not be loaded, using default config.");
        }
        getProxy().registerChannel( getConfigurationValue("communicationChannel"));
        getProxy().getPluginManager().registerListener(this,new MessageListener());

        this.getProxy().getPluginManager().registerCommand(this,new CommandManager());
    }

    /**
     * Retrieves the config object, if it dosent exist generates it.
     * @return Return the Configuration object generated from the config file in your plugins folder.
     * @throws IOException Error thrown if config.yml could not be read.
     */
    private static Configuration getConfig() throws IOException {
        Configuration configuration;
        if (! instance.getDataFolder().exists()){
            instance.getDataFolder().mkdir();
        File file = new File(instance.getDataFolder(), "config.yml");
            if (!file.exists()) {
                try (InputStream in = instance.getResourceAsStream("config.yml")) {
                    Files.copy(in, file.toPath());
                } catch (Exception e) {
                    instance.getProxy().getLogger().log(Level.SEVERE,
                            ChatColor.RED+"[CrossChat]"+"getConfig readfile");
                }
            }
        }
        configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(instance.getDataFolder(), "config.yml"));
        return configuration;
    }

    /**
     * Will Check if the config file contains all the required entry for the plugin to work.
     * If an entry dosen't exist it will generate and use the default value.
     * @return Verified Configuration object with all config entries existing in it.
     */
    private void FixConfig(){

        for (String fixkey : getDefaultConfig().getKeys()){ // Fix config object
            if(configuration.get(fixkey) == null || configuration.get(fixkey).equals("")){
                configuration.set(fixkey, getDefaultConfig().get(fixkey));
            }
        }
        try { //Save it to config.yml file.
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(getDataFolder(), "config.yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets an entry in the config object, if it's empty it gets the default value.
     * @param key Key of the Configuration Entry.
     * @return Return value of the entered Key.
     */
    public String getConfigurationValue(String key){
        if (configuration.get(key) == null || configuration.get(key).equals("") ){
            FixConfig();
            return (String) getDefaultConfig().get(key);
        }
        else{
           return (String) configuration.get(key);
        }
    }

    /**
     * Generates a Configuration object in case config.yml can't be read.
     * @return Configuration object that would be in
     */
    private static Configuration getDefaultConfig(){
        Configuration configuration = new Configuration();

        configuration.set("separator"," → ");
        configuration.set("communicationChannel","CrossChat:Message");

        return configuration;
    }

}