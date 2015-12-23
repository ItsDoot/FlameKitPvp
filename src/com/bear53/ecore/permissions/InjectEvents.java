package com.bear53.ecore.permissions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class InjectEvents implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		PermissionSettings.getInstance().injectPlayer(e.getPlayer());
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		PermissionSettings.getInstance().uninjectPlayer(e.getPlayer());
	}
}
