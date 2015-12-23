package com.bear53.ecore.kitpvp;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.bear53.ecore.Core;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class Grenades implements Listener {

	Core plugin;

	public Grenades(Core pl) {
		this.plugin = pl;
	}

	@EventHandler
	public void flash(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR
				|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if (p.getItemInHand() != null) {
				if (p.getItemInHand().hasItemMeta()) {
					if (p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if (p.getItemInHand()
								.getItemMeta()
								.getDisplayName()
								.equalsIgnoreCase(
										ChatColor.RED + "Flash Grenade")) {
							ItemStack Grenade = new ItemStack(Material.SULPHUR);
							ItemMeta Grenademeta = Grenade.getItemMeta();
							Grenademeta.setDisplayName(ChatColor.RED
									+ "Flash Grenade");
							Grenade.setItemMeta(Grenademeta);
							p.getInventory().removeItem(Grenade);
							Location l = p.getLocation();
							ArrayList<Player> entities = new ArrayList<Player>();
							for (Player e1 : l.getWorld().getPlayers()) {
								if (l.distance(e1.getLocation()) <= 10) {
									entities.add(e1);
									if (entities.contains(p)) {
										entities.remove(p);
									}
								}
							}
							for (Player affected : Bukkit.getServer()
									.getOnlinePlayers()) {
								if (entities.contains(affected)) {
									affected.addPotionEffect(new PotionEffect(
											PotionEffectType.BLINDNESS, Integer
													.valueOf(200), 2));
									affected.addPotionEffect(new PotionEffect(
											PotionEffectType.CONFUSION, Integer
													.valueOf(200), 2));
									affected.sendMessage(ChatColor.RED
											+ "You were flashed by "
											+ ChatColor.AQUA + p.getName());
									entities.clear();
								}
							}
							p.sendMessage(ChatColor.GREEN
									+ "You used a Flash Grenade!");
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void damage(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR
				|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if (p.getItemInHand() != null) {
				if (p.getItemInHand().hasItemMeta()) {
					if (p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if (p.getItemInHand()
								.getItemMeta()
								.getDisplayName()
								.equalsIgnoreCase(
										ChatColor.RED + "Lethal Grenade")) {
							ItemStack Grenade = new ItemStack(
									Material.SLIME_BALL);
							ItemMeta Grenademeta = Grenade.getItemMeta();
							Grenademeta.setDisplayName(ChatColor.RED
									+ "Lethal Grenade");
							Grenade.setItemMeta(Grenademeta);
							p.getInventory().removeItem(Grenade);
							Location l = p.getLocation();
							ArrayList<Player> entities = new ArrayList<Player>();
							for (Player e1 : l.getWorld().getPlayers()) {
								if (l.distance(e1.getLocation()) <= 10) {
									entities.add(e1);
									if (entities.contains(p)) {
										entities.remove(p);
									}
								}
							}
							for (Player affected : Bukkit.getServer()
									.getOnlinePlayers()) {
								if (entities.contains(affected)) {
									if (!isInSpawn(p)) {
										affected.damage(10D);
										affected.sendMessage(ChatColor.RED
												+ "You were damaged by a Lethal Grenade!");
										if (affected.getHealth() <= 0D) {
											Bukkit.broadcastMessage(ChatColor.AQUA
													+ affected.getName()
													+ ChatColor.GRAY
													+ " was killed by a "
													+ ChatColor.RED
													+ "Lethal Grenade");
											entities.remove(affected.getName());
										}
										entities.clear();
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
	public void slowness(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR
				|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if (p.getItemInHand() != null) {
				if (p.getItemInHand().hasItemMeta()) {
					if (p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if (p.getItemInHand()
								.getItemMeta()
								.getDisplayName()
								.equalsIgnoreCase(
										ChatColor.RED + "Slowness Grenade")) {
							ItemStack slow = new ItemStack(
									Material.MAGMA_CREAM, 1);
							ItemMeta slowm = slow.getItemMeta();
							slowm.setDisplayName(ChatColor.RED
									+ "Slowness Grenade");
							slow.setItemMeta(slowm);
							p.getInventory().removeItem(slow);
							Location l = p.getLocation();
							ArrayList<Player> entities = new ArrayList<Player>();
							for (Player e1 : l.getWorld().getPlayers()) {
								if (l.distance(e1.getLocation()) <= 10) {
									entities.add(e1);
									if (entities.contains(p)) {
										entities.remove(p);
									}
								}
							}
							for (Player affected : Bukkit.getServer()
									.getOnlinePlayers()) {
								if (entities.contains(affected)) {
									affected.addPotionEffect(new PotionEffect(
											PotionEffectType.SLOW, Integer
													.valueOf(200), 2));
									affected.sendMessage(ChatColor.RED
											+ "You have been slowed by "
											+ ChatColor.AQUA + p.getName());
									entities.clear();
								}
							}
							p.sendMessage(ChatColor.GREEN
									+ "You used a Slowness Grenade!");
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void c(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			e.getDrops().clear();
		}
	}

	@EventHandler
	public void cD(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getEntity();
		if (e.getCause().equals(DamageCause.CUSTOM)) {
			if (isInSpawn(p)) {
				heal(p);
			}
		}
	}

	public void heal(Player p) {
		double maxHealth = p.getMaxHealth();
		p.setHealth(maxHealth);
	}

	public boolean isInSpawn(final Player player) {

		RegionManager region_manager = plugin.getWG().getRegionManager(
				player.getWorld());
		ApplicableRegionSet set = region_manager.getApplicableRegions(player
				.getLocation());

		if (set.getRegions().isEmpty()) {
			return false;
		}

		for (ProtectedRegion region : set.getRegions()) {
			if (region.getId().equalsIgnoreCase("spawn")) {
				return true;
			}
		}
		return false;
	}
}
