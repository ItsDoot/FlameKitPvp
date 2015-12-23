package com.bear53.ecore.kitpvp;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class PyroEvent implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerUse(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			if (p.getItemInHand().getType() == Material.BLAZE_ROD) {
				Fireball fire = (Fireball) p.getWorld().spawn(
						event.getPlayer().getLocation()
								.add(new Vector(0.0D, 1.0D, 0.0D)),
						Fireball.class);
				fire.setFireTicks(0);
				fire.setShooter(p);
			} else if (p.getItemInHand().getType() == Material.BLAZE_ROD) {
				Fireball fire = (Fireball) p.getWorld().spawn(
						event.getPlayer().getLocation()
								.add(new Vector(0.0D, 1.0D, 0.0D)),
						Fireball.class);
				fire.setFireTicks(0);
				fire.setShooter(p);
			}
		}
	}

	/*
	 * @EventHandler public void fb(EntityDamageByEntityEvent e) { if
	 * (e.getDamager() instanceof Fireball) { Fireball fb = (Fireball)
	 * e.getDamager(); if (fb.getShooter() instanceof Player) {
	 * 
	 * } } }
	 */

	@EventHandler(priority = EventPriority.HIGH)
	public void onCreeperExplode(EntityExplodeEvent event) {
		Entity e = event.getEntity();
		if ((e instanceof Fireball)) {
			Fireball fb = (Fireball) e;
			if ((fb.getShooter() instanceof Player)) {
				fb.setYield(0.0F);
			}
		}
	}
}
