package com.joeyblankendaal.glacier.core.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Feed implements CommandExecutor {
    private JavaPlugin plugin;
    private Player player;
    private Player target;

    public Feed(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;

            if (player.hasPermission("glacier.core.feed") || player.isOp()) {
                if (args.length == 0) {
                    player.setExhaustion(0);
                    player.setFoodLevel(20);

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + plugin.getConfig().getString("commands.feed.messages.successes.self")));
                } else if (args.length == 1) {
                    target = Bukkit.getPlayer(args[0]);

                    if (target == null) {
                        System.out.println(ChatColor.translateAlternateColorCodes('&', "&f[&b" + plugin.getDescription().getName() + "&f] &c" + plugin.getConfig().getString("messages.errors.player-not-online")));
                    } else {
                        target.setExhaustion(0);
                        target.setFoodLevel(20);

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + plugin.getConfig().getString("commands.feed.messages.successes.others-player").replaceAll("<target>", target.getDisplayName())));

                        if (target.hasPermission("glacier.core.feed") || target.isOp()) {
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + plugin.getConfig().getString("commands.feed.messages.successes.others-target").replaceAll("<player>", player.getDisplayName())));
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + plugin.getConfig().getString("messages.errors.too-many-arguments").replaceAll("<usage>", "/feed [player]")));
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + plugin.getConfig().getString("messages.errors.no-permission")));
            }
        } else {
            if (args.length == 0) {
                System.out.println(ChatColor.translateAlternateColorCodes('&', "&f[&b" + plugin.getDescription().getName() + "&f] &c" + plugin.getConfig().getString("messages.errors.too-few-arguments").replaceAll("<usage>", "feed <player>")));
            } else if (args.length == 1) {
                target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    System.out.println(ChatColor.translateAlternateColorCodes('&', "&f[&b" + plugin.getDescription().getName() + "&f] &c" + plugin.getConfig().getString("messages.errors.player-not-online")));
                } else {
                    target.setExhaustion(0);
                    target.setFoodLevel(20);

                    System.out.println(ChatColor.translateAlternateColorCodes('&', "&f[&b" + plugin.getDescription().getName() + "&f] &a" + plugin.getConfig().getString("commands.feed.messages.successes.others-player").replaceAll("<target>", target.getDisplayName())));

                    if (target.hasPermission("glacier.core.feed") || target.isOp()) {
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + plugin.getConfig().getString("commands.feed.messages.successes.others-target").replaceAll("<player>", "Console")));
                    }
                }
            } else {
                System.out.println(ChatColor.translateAlternateColorCodes('&', "&f[&b" + plugin.getDescription().getName() + "&f] &c" + plugin.getConfig().getString("messages.errors.too-many-arguments").replaceAll("<usage>", "feed <player>")));
            }
        }

        return false;
    }
}