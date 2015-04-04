package com.joeyblankendaal.glacier.core;

import com.joeyblankendaal.glacier.core.command.Heal;
import org.bukkit.plugin.java.JavaPlugin;

import com.joeyblankendaal.glacier.core.command.More;

public class Main extends JavaPlugin {
    @Override
    public void onDisable() {
        saveConfig();

        System.out.println("&f[&b" + getDescription().getName() + "&f] &aConfigurations saved...");

        System.out.println("&f[&b" + getDescription().getName() + "&f] &aDisabled &e" + getDescription().getName() + " v" + getDescription().getVersion());
    }

    @Override
    public void onEnable() {
        getConfig().addDefault("commands.heal.enabled", true);
        getConfig().addDefault("commands.heal.messages.successes.self", "&aYour health has been fully restored.");
        getConfig().addDefault("commands.heal.messages.successes.others-player", "&e<target>&a's health has been fully restored.");
        getConfig().addDefault("commands.heal.messages.successes.others-target", "&aYour health has been fully restored by &e<player>&a.");

        getConfig().addDefault("commands.more.enabled", true);
        getConfig().addDefault("commands.more.messages.errors.no-item-in-hand", "&cYou currently have no item in your hand.");
        getConfig().addDefault("commands.more.messages.successes.self", "&aYou now have a stack of &e<item>&a.");

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

        if (getConfig().getBoolean("commands.heal.enabled")) {
            getCommand("heal").setExecutor(new Heal(this));
        }

        if (getConfig().getBoolean("commands.more.enabled")) {
            getCommand("more").setExecutor(new More(this));
        }

        System.out.println("&f[&b" + getDescription().getName() + "&f] &aCommands loaded...");

        System.out.println("&f[&b" + getDescription().getName() + "&f] &aEnabled &e" + getDescription().getName() + " v" + getDescription().getVersion());
    }
}