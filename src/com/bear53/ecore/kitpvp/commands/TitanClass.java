package com.bear53.ecore.kitpvp.commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.bear53.ecore.Core;
import com.bear53.ecore.kitpvp.KitPvp;

public class TitanClass implements CommandExecutor, Listener {

	Core plugin;

	public TitanClass(Core pl) {
		this.plugin = pl;
	}

	private ArrayList<Player> titanmode = new ArrayList<Player>();

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("Titan")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin
						.getConfig()
						.getStringList(
								"players." + p.getUniqueId().toString()
										+ ".kits").contains("Titan")) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN
							+ "You have activated the Titan Kit!");

					ItemStack Sword = new ItemStack(Material.IRON_SWORD);
					ItemMeta s = Sword.getItemMeta();
					s.setDisplayName("§a§lTitan Mode");
					Sword.setItemMeta(s);
					ItemStack Chest = new ItemStack(Material.IRON_CHESTPLATE);
					ItemStack Helm = new ItemStack(Material.IRON_HELMET);
					ItemStack Legs = new ItemStack(Material.IRON_LEGGINGS);
					ItemStack Boot = new ItemStack(Material.IRON_BOOTS);
					Sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
					Legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
					Chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
							1);
					Boot.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
					Helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

					p.getInventory().clear();
					p.getInventory().addItem(new ItemStack[] { Sword });
					p.getInventory().setHelmet(Helm);
					p.getInventory().setChestplate(Chest);
					p.getInventory().setLeggings(Legs);
					p.getInventory().setBoots(Boot);
					ItemStack itemshop = new ItemStack(Material.BLAZE_POWDER);
					ItemMeta shopmeta = itemshop.getItemMeta();
					shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
					ArrayList<String> shoplore = new ArrayList<String>();
					shoplore.add(ChatColor.RED
							+ "Be sure to have space in your inventory!");
					shopmeta.setLore(shoplore);
					itemshop.setItemMeta(shopmeta);
					p.getInventory().setItem(8, itemshop);
					if (plugin.getConfig().getBoolean("potion-enabled")) {
						KitPvp.giveHeath(p);
					} else {
						KitPvp.giveSoup(p);
					}
				} else {
					p.sendMessage(KitPvp.noPerm);
				}
			} else {
				p.sendMessage(KitPvp.activekit);
			}
		}
		return true;
	}

	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {
		ItemStack i = e.getItem();
		final Player p = e.getPlayer();
		if (i == null) {
			return;
		}
		if (!i.hasItemMeta()) {
			return;
		}
		if (!i.getItemMeta().hasDisplayName()) {
			return;
		}
		if (i.getItemMeta().getDisplayName().startsWith("§a§lTitan Mode")) {
			if (e.getAction().equals(Action.RIGHT_CLICK_AIR)
					|| (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
				e.setCancelled(true);
				titanmode.add(p);
				p.addPotionEffect(new PotionEffect(
						PotionEffectType.DAMAGE_RESISTANCE, 200, 1));
				p.sendMessage("§a§l!! §7You are now invincible for 10 seconds!");
				ItemStack n = i;
				ItemMeta m = n.getItemMeta();
				m.setDisplayName("§c§lTitan Mode - Needs Recharging");
				n.setItemMeta(m);
				e.getPlayer().setItemInHand(n);
				Bukkit.getServer().getScheduler()
						.scheduleSyncDelayedTask(plugin, new Runnable() {
							public void run() {
								titanmode.remove(p);
							}
						}, 200L);
				return;
			} else {
				return;
			}
		}
		if (i.getItemMeta().getDisplayName().startsWith("§c§lTitan Mode")) {
			if (e.getAction().equals(Action.RIGHT_CLICK_AIR)
					|| e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				e.setCancelled(true);
				p.sendMessage("§c§l!! §7You need to recharge before using this again!");
				return;
			} else {
				return;
			}
		}
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if ((e.getEntity() instanceof Player)) {
			Player p = (Player) e.getEntity();
			if (titanmode.contains(p)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerHit(EntityDamageByEntityEvent e) {
		if ((e.getEntity() instanceof Player)) {
			Player p = (Player) e.getEntity();
			if ((e.getDamager() instanceof Player)) {
				Player d = (Player) e.getDamager();
				if (titanmode.contains(p)) {
					e.setCancelled(true);
					d.sendMessage("§c§l!! §7You can not hurt a player in titan mode!");
				}
			}
		}
		if ((e.getDamager() instanceof Player)) {
			Player d = (Player) e.getDamager();
			if (titanmode.contains(d)) {
				e.setCancelled(true);
				d.sendMessage("§c§l!! §7You can not damage others while in titan mode.");
			}
		}
	}

	HashMap<Player, Integer> time = new HashMap<Player, Integer>();

	@EventHandler
	public void onTitanRecharge(final PlayerToggleSneakEvent e) {
		if (!e.getPlayer().isSneaking()) {
			ItemStack n = new ItemStack(Material.IRON_SWORD);
			n.addEnchantment(Enchantment.DAMAGE_ALL, 1);
			ItemMeta m = n.getItemMeta();
			m.setDisplayName("§c§lTitan Mode - Needs Recharging");
			n.setItemMeta(m);
			if (e.getPlayer().getItemInHand().equals(n)) {
				this.time.remove(e.getPlayer());
				this.time.put(e.getPlayer(), Integer.valueOf(0));
				new BukkitRunnable() {
					public void run() {
						if (e.getPlayer().isSneaking()) {
							time.put(e.getPlayer(),
									Integer.valueOf(((Integer) time.get(e
											.getPlayer())).intValue() + 1));
							if (((Integer) time.get(e.getPlayer())).intValue() == 10) {
								e.getPlayer().sendMessage(
										"§a§l!! §7Titan recharged.");
								for (int i = 0; i < 36; i++) {
									if (e.getPlayer().getInventory().getItem(i) != null) {
										if (e.getPlayer().getInventory()
												.getItem(i).hasItemMeta()) {
											if (e.getPlayer().getInventory()
													.getItem(i).getItemMeta()
													.hasDisplayName()) {
												if (e.getPlayer()
														.getInventory()
														.getItem(i)
														.getItemMeta()
														.getDisplayName()
														.startsWith(
																"§c§lTitan Mode")) {
													ItemStack n = e.getPlayer()
															.getInventory()
															.getItem(i);
													ItemMeta m = n
															.getItemMeta();
													m.setDisplayName("§a§lTitan Mode");
													n.setItemMeta(m);
													e.getPlayer()
															.getInventory()
															.setItem(i, n);
													e.getPlayer()
															.updateInventory();
												}
											}
										}
									}
								}
								time.remove(e.getPlayer());
								cancel();
							}
						} else {
							time.remove(e.getPlayer());
							cancel();
						}
					}
				}.runTaskTimer(plugin, 0L, 20L);
			} else {
				return;
			}
		} else {
			return;
		}
	}
}
