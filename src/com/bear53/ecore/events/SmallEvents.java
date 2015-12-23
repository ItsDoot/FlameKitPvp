package com.bear53.ecore.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class SmallEvents implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (!p.isOp()) {
			e.setCancelled(true);
		} else {
			return;
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onRainStart(WeatherChangeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onFallDamage(EntityDamageEvent event) {
		if (((event.getEntity() instanceof Player))
				&& (event.getCause() == EntityDamageEvent.DamageCause.FALL)) {
			event.setCancelled(true);
		} else {
			return;
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (!p.isOp()) {
			e.setCancelled(true);
		} else {
			return;
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if (e.getItemDrop().getItemStack().getType()
				.equals(Material.MUSHROOM_SOUP)) {
			e.getItemDrop().remove();
		} else if (e.getItemDrop().getItemStack().getType()
				.equals(Material.BOWL)) {
			e.getItemDrop().remove();
		} else {
			e.setCancelled(true);
		}
	}
}
