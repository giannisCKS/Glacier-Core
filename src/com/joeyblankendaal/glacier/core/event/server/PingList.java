package com.joeyblankendaal.glacier.core.event.server;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PingList implements Listener {
    private JavaPlugin plugin;

    public PingList(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEvent(ServerListPingEvent event) {
        event.setMaxPlayers(plugin.getConfig().getInt("events.ping-list.settings.max-players"));
    }
}
