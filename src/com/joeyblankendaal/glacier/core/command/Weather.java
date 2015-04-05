package com.joeyblankendaal.glacier.core.command;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Weather implements CommandExecutor {
    private JavaPlugin plugin;
    private Player player;
    private World world;

    public Weather(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;

            if (player.hasPermission("glacier.core.weather") || player.isOp()) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + plugin.getConfig().getString("messages.errors.too-few-arguments").replaceAll("<usage>", "/weather <type>")));
                } else if (args.length == 1) {
                    world = player.getWorld();

                    if (args[0].equalsIgnoreCase("clear") || args[0].equalsIgnoreCase("sun") || args[0].equalsIgnoreCase("sunny")) {
                        world.setStorm(false);

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + plugin.getConfig().getString("commands.weather.messages.successes.self").replaceAll("<type>", "sunny")));
                    } else if (args[0].equalsIgnoreCase("rain") || args[0].equalsIgnoreCase("rainy") || args[0].equalsIgnoreCase("storm") || args[0].equalsIgnoreCase("stormy")) {
                        world.setStorm(true);

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + plugin.getConfig().getString("commands.weather.messages.successes.self").replaceAll("<type>", "stormy")));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + plugin.getConfig().getString("commands.weather.messages.errors.invalid-type")));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + plugin.getConfig().getString("messages.errors.too-many-arguments").replaceAll("<usage>", "/weather <type>")));
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + plugin.getConfig().getString("messages.errors.no-permission")));
            }
        } else {
            System.out.println(ChatColor.translateAlternateColorCodes('&', "&f[&b" + plugin.getDescription().getName() + "&f] &c" + plugin.getConfig().getString("messages.errors.only-players")));
        }

        return false;
    }
}
