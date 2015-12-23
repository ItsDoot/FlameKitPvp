package com.bear53.ecore.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.bear53.ecore.Core;
import com.bear53.ecore.duels.Duels;
import com.bear53.ecore.teams.Team;
import com.bear53.ecore.teams.TeamManager;

public class HeadShot implements Listener {

	@EventHandler
	public void d(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			if (event.getEntity() instanceof Player) {
				Player damaged = (Player) event.getEntity();
				Player damager = (Player) event.getDamager();
				if (damager != null) {
					if (TeamManager.getInstance().getTeam(damaged) != null
							&& TeamManager.getInstance().getTeam(damager) != null) {
						Team a = TeamManager.getInstance().getTeam(damaged), b = TeamManager
								.getInstance().getTeam(damager);
						if (a.equals(b)) {
							if (Bukkit.getPlayer(Duels.challenger) != null) {
							Player nulll = Bukkit.getPlayer(Duels.challenger);
								if (nulll == a || nulll == b) {
									if (!a.allowFriendlyFire()) {
										event.setCancelled(false);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void dd(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			if (event.getEntity() instanceof Player) {
				Player damaged = (Player) event.getEntity();
				Player damager = (Player) event.getDamager();
				if (damager != null) {
					if (TeamManager.getInstance().getTeam(damaged) != null
							&& TeamManager.getInstance().getTeam(damager) != null) {
						Team a = TeamManager.getInstance().getTeam(damaged), b = TeamManager
								.getInstance().getTeam(damager);
						// Player nulll = Bukkit.getPlayer(Duels.challenger
						// .toString());
						if (a.equals(b)) {
							if (!a.allowFriendlyFire()) {
								event.setCancelled(true);
								// } else if (nulll != null) {
								// if (!(b == Bukkit.getPlayer(Duels.challenger
								// .toString()))) {
								// if (!a.allowFriendlyFire()) {
								// event.setCancelled(false);
								// }
								// }
							}
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("unused")
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			Player damaged = (Player) event.getEntity(), damager = null;

			if (event.getDamager() instanceof Player) {
				damager = (Player) event.getDamager();
			} else if (event.getDamager() instanceof Projectile) {
				if (((Projectile) event.getDamager()).getShooter() instanceof Player) {
					damager = (Player) ((Projectile) event.getDamager())
							.getShooter();
				}
			}
		}
		if (event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) {
			return;
		}
		Projectile proj = (Projectile) event.getDamager();
		if (!(proj.getShooter() instanceof Player)) {
			return;
		}
		Entity shot = event.getEntity();

		double y = proj.getLocation().getY();
		double shotY = shot.getLocation().getY();
		boolean headshot = y - shotY > 1.35D;
		Player shotPlayer = null;
		if ((shot instanceof Player)) {
			shotPlayer = (Player) shot;
		}
		if (headshot) {
			event.setDamage(event.getDamage() * 2.0D);
			String message = Core.prefix + ChatColor.GREEN
					+ "You have got a HeadShot on " + ChatColor.BLUE
					+ ((Player) shot).getDisplayName();
			((Player) proj.getShooter()).sendMessage(message);
			PotionEffect blind = new PotionEffect(PotionEffectType.BLINDNESS,
					40, 2);
			PotionEffect nausea = new PotionEffect(PotionEffectType.CONFUSION,
					100, 2);
			shotPlayer.addPotionEffect(blind, true);
			shotPlayer.addPotionEffect(nausea, true);
		}
	}
}
