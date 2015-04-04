package com.joeyblankendaal.glacier.core.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class More implements CommandExecutor {
    private JavaPlugin plugin;
    private Player player;
    private ItemStack itemInHand;

    public More(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;

            if (player.hasPermission("glacier.core.more") || player.isOp()) {
                if (args.length == 0) {
                    itemInHand = player.getInventory().getItemInHand();

                    if (itemInHand.getType() == Material.AIR) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + plugin.getConfig().getString("commands.more.messages.errors.no-item-in-hand")));
                    } else {
                        itemInHand.setAmount(64);

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + plugin.getConfig().getString("commands.more.messages.successes.self").replaceAll("<item>", itemInHand.toString())));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + plugin.getConfig().getString("messages.errors.too-many-arguments").replaceAll("<usage>", "/more")));
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