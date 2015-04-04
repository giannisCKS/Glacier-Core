package com.joeyblankendaal.glacier.core.event.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Join implements Listener {
    private JavaPlugin plugin;
    private Player player;

    public Join(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEvent(PlayerJoinEvent event) {
        player = event.getPlayer();

        if (player.hasPermission("glacier.core.join.notify") || player.isOp()) {
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("events.join.messages.notify").replaceAll("<player>", player.getDisplayName())));
        } else {
            event.setJoinMessage(null);
        }
    }
}