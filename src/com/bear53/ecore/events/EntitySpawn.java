package com.bear53.ecore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntitySpawn implements Listener {

	@EventHandler
	public void onCSpawn(CreatureSpawnEvent e) {
		if (e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM) {
			e.setCancelled(true);
		} else {
			return;
		}
	}

	@EventHandler
	public void fall(EntityDamageEvent e) {
		if (e.getCause() != DamageCause.FALL) {
			return;
		}
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		e.setCancelled(true);
	}
}
