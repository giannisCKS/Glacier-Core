package com.joeyblankendaal.glacier.core;

import org.bukkit.plugin.java.JavaPlugin;

import com.joeyblankendaal.glacier.core.command.More;

public class Main extends JavaPlugin {
    @Override
    public void onDisable() {
        System.out.println("&f[&b" + getDescription().getName() + "&f] &aDisabled &e" + getDescription().getName() + " v" + getDescription().getVersion());
    }

    @Override
    public void onEnable() {
        getConfig().addDefault("commands.more.enabled", true);
        getConfig().addDefault("commands.more.priorities.essentials", false);
        getConfig().addDefault("commands.more.messages.errors.no-item-in-hand", "&cYou currently have no item in your hand.");
        getConfig().addDefault("commands.more.messages.successes.main", "&aYou now have a stack of &e<item>&a.");

        getConfig().addDefault("messages.errors.no-permission", "&cI'm sorry, but you do not have permission to perform this command. Please contact the server administrator if you believe that this is in error.");
        getConfig().addDefault("messages.errors.only-players", "&cOnly players can perform this command.");
        getConfig().addDefault("messages.errors.too-many-arguments", "&cToo many arguments. Usage: \"<usage>\"");

        getConfig().options().copyDefaults(true);

        saveConfig();

        if (getConfig().getBoolean("commands.more.enabled")) {
            if (getServer().getPluginManager().getPlugin("Essentials").isEnabled()) {
                if (!getConfig().getBoolean("commands.more.priorities.essentials")) {
                    getCommand("more").setExecutor(new More(this));
                }
            } else {
                getCommand("more").setExecutor(new More(this));
            }
        }

        System.out.println("&f[&b" + getDescription().getName() + "&f] &aEnabled &e" + getDescription().getName() + " v" + getDescription().getVersion());
    }
}