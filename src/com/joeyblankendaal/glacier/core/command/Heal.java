package com.joeyblankendaal.glacier.core.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Heal implements CommandExecutor {
    private JavaPlugin plugin;
    private Player player;
    private Player target;

    public Heal(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;

            if (player.hasPermission("glacier.core.heal") || player.isOp()) {
                ChatColor.translateAlternateColorCodes('&', "&a" + plugin.getConfig().getString("commands.heal.messages.successes.self"));
            } else {
                if (args.length == 0) {
                    player.setHealth(20.0);

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + plugin.getConfig().getString("commands.heal.messages.successes.self")));
                } else if (args.length == 1) {
                    target = Bukkit.getPlayer(args[0]);

                    if (target == null) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + plugin.getConfig().getString("messages.errors.player-not-online")));
                    } else {
                        target.setHealth(20.0);

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + plugin.getConfig().getString("commands.heal.messages.successes.others-player").replaceAll("<target>", target.getDisplayName())));

                        if (target.hasPermission("glacier.core.heal") || target.isOp()) {
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + plugin.getConfig().getString("commands.heal.messages.successes.others-target").replaceAll("<player>", player.getDisplayName())));
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + plugin.getConfig().getString("messages.errors.too-many-arguments").replaceAll("<usage>", "/heal [player]")));
                }
            }
        } else {
            if (args.length == 0) {
                System.out.println(ChatColor.translateAlternateColorCodes('&', "&f[&b" + plugin.getDescription().getName() + "&f] &c" + plugin.getConfig().getString("messages.errors.too-few-arguments").replaceAll("<usage>", "heal <player>")));
            } else if (args.length == 1) {
                target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    System.out.println(ChatColor.translateAlternateColorCodes('&', "&f[&b" + plugin.getDescription().getName() + "&f] &c" + plugin.getConfig().getString("messages.errors.player-not-online")));
                } else {
                    target.setHealth(20.0);

                    System.out.println(ChatColor.translateAlternateColorCodes('&', "&f[&b" + plugin.getDescription().getName() + "&f] &a" + plugin.getConfig().getString("commands.heal.messages.successes.others-player").replaceAll("<target>", target.getDisplayName())));

                    if (target.hasPermission("glacier.core.heal") || target.isOp()) {
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + plugin.getConfig().getString("commands.heal.messages.successes.others-target").replaceAll("<player>", "Console")));
                    }
                }
            } else {
                System.out.println(ChatColor.translateAlternateColorCodes('&', "&f[&b" + plugin.getDescription().getName() + "&f] &c" + plugin.getConfig().getString("messages.errors.too-many-arguments").replaceAll("<usage>", "heal <player>")));
            }
        }

        return false;
    }
}
