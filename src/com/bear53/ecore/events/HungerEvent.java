package com.bear53.ecore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerEvent implements Listener {

	@EventHandler
	public void HungerChange(FoodLevelChangeEvent e) {
		if ((e.getEntity() instanceof Player)) {
			e.setCancelled(true);
		}
	}
}
