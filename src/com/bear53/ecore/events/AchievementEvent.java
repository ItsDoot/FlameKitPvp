package com.bear53.ecore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;

public class AchievementEvent implements Listener {

	@EventHandler
	public void OnAchievement(PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}
}
