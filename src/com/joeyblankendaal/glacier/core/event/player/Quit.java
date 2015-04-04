package com.joeyblankendaal.glacier.core.event.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Quit implements Listener {
    private JavaPlugin plugin;
    private Player player;

    public Quit(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEvent(PlayerQuitEvent event) {
        player = event.getPlayer();

        if (player.hasPermission("glacier.core.quit.notify") || player.isOp()) {
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("events.quit.messages.notify").replaceAll("<player>", player.getDisplayName())));
        } else {
            event.setQuitMessage(null);
        }
    }
}