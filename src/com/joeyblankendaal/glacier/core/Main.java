package com.joeyblankendaal.glacier.core;

import com.joeyblankendaal.glacier.core.command.*;
import com.joeyblankendaal.glacier.core.event.server.PingList;
import org.bukkit.plugin.java.JavaPlugin;

import com.joeyblankendaal.glacier.core.event.player.Join;
import com.joeyblankendaal.glacier.core.event.player.Quit;

public class Main extends JavaPlugin {
    @Override
    public void onDisable() {
        saveConfig();

        System.out.println("&f[&b" + getDescription().getName() + "&f] &aConfigurations saved...");

        System.out.println("&f[&b" + getDescription().getName() + "&f] &aDisabled &e" + getDescription().getName() + " v" + getDescription().getVersion());
    }

    @Override
    public void onEnable() {
        getConfig().addDefault("commands.feed.enabled", true);
        getConfig().addDefault("commands.feed.messages.successes.self", "&aYour hunger has been fully restored.");
        getConfig().addDefault("commands.feed.messages.successes.others-player", "&e<target>&a's hunger has been fully restored.");
        getConfig().addDefault("commands.feed.messages.successes.others-target", "&aYour hunger has been fully restored by &e<player>&a.");

        getConfig().addDefault("commands.heal.enabled", true);
        getConfig().addDefault("commands.heal.messages.successes.self", "&aYour health has been fully restored.");
        getConfig().addDefault("commands.heal.messages.successes.others-player", "&e<target>&a's health has been fully restored.");
        getConfig().addDefault("commands.heal.messages.successes.others-target", "&aYour health has been fully restored by &e<player>&a.");

        getConfig().addDefault("commands.kill.enabled", true);
        getConfig().addDefault("commands.kill.messages.successes.self", "&aYou have killed yourself.");
        getConfig().addDefault("commands.kill.messages.successes.others-player", "&e<target>&a has been killed.");
        getConfig().addDefault("commands.kill.messages.successes.others-target", "&aYou have been killed by &e<player>&a.");

        getConfig().addDefault("commands.more.enabled", true);
        getConfig().addDefault("commands.more.messages.errors.no-item-in-hand", "&cYou currently have no item in your hand.");
        getConfig().addDefault("commands.more.messages.successes.self", "&aYou now have a stack of &e<item>&a.");

        getConfig().addDefault("commands.weather.enabled", true);
        getConfig().addDefault("commands.weather.messages.errors.invalid-type", "&e<type> &ais not a valid weather type.");
        getConfig().addDefault("commands.weather.messages.successes.self", "&aThe weather in has been set to &e<type>&a.");

        getConfig().addDefault("events.join.enabled", true);
        getConfig().addDefault("events.join.messages.notify", "&e<player> has joined the game.");

        getConfig().addDefault("events.ping-list.enabled", true);
        getConfig().addDefault("events.ping-list.settings.max-players", 20);

        getConfig().addDefault("events.quit.enabled", true);
        getConfig().addDefault("events.quit.messages.notify", "&e<player> has left the game.");

        getConfig().addDefault("messages.errors.no-permission", "&cI'm sorry, but you do not have permission to perform this command. Please contact the server administrator if you believe that this is in error.");
        getConfig().addDefault("messages.errors.only-console", "&cOnly the console can perform this command.");
        getConfig().addDefault("messages.errors.only-players", "&cOnly players can perform this command.");
        getConfig().addDefault("messages.errors.player-not-online", "&c<player> is not online.");
        getConfig().addDefault("messages.errors.unknown-command", "&fUnknown command. Type \"/help\" for help.");
        getConfig().addDefault("messages.errors.too-few-arguments", "&cToo few arguments. Usage: \"<usage>\"");
        getConfig().addDefault("messages.errors.too-many-arguments", "&cToo many arguments. Usage: \"<usage>\"");

        getConfig().options().copyDefaults(true);

        saveConfig();

        System.out.println("&f[&b" + getDescription().getName() + "&f] &aConfigurations loaded...");

        if (getConfig().getBoolean("events.join.enabled")) {
            getServer().getPluginManager().registerEvents(new Join(this), this);
        }

        if (getConfig().getBoolean("events.ping-list.enabled")) {
            getServer().getPluginManager().registerEvents(new PingList(this), this);
        }

        if (getConfig().getBoolean("events.quit.enabled")) {
            getServer().getPluginManager().registerEvents(new Quit(this), this);
        }

        System.out.println("&f[&b" + getDescription().getName() + "&f] &aEvents loaded...");

        if (getConfig().getBoolean("commands.feed.enabled")) {
            getCommand("feed").setExecutor(new Feed(this));
        }

        if (getConfig().getBoolean("commands.heal.enabled")) {
            getCommand("heal").setExecutor(new Heal(this));
        }

        if (getConfig().getBoolean("commands.kill.enabled")) {
            getCommand("kill").setExecutor(new Kill(this));
        }

        if (getConfig().getBoolean("commands.more.enabled")) {
            getCommand("more").setExecutor(new More(this));
        }

        if (getConfig().getBoolean("commands.weather.enabled")) {
            getCommand("weather").setExecutor(new Weather(this));
        }

        System.out.println("&f[&b" + getDescription().getName() + "&f] &aCommands loaded...");

        System.out.println("&f[&b" + getDescription().getName() + "&f] &aEnabled &e" + getDescription().getName() + " v" + getDescription().getVersion());
    }
}