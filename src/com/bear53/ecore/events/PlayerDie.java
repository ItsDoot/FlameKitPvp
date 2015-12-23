package com.bear53.ecore.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.bear53.ecore.Core;
import com.bear53.ecore.TabUtils;
import com.bear53.ecore.duels.Duels;
import com.bear53.ecore.kitpvp.KitPvp;
import com.bear53.ecore.koth.HillMoveEvent;
import com.bear53.ecore.koth.LocationUtils;

public class PlayerDie implements Listener {

	Core plugin;

	public PlayerDie(Core pl) {
		this.plugin = pl;
	}

	LocationUtils locu = new LocationUtils(plugin);

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDeath(PlayerDeathEvent e) {
		e.setDeathMessage("");
		final Player dead = (Player) e.getEntity();
		final Player killer = (Player) e.getEntity().getKiller();

		KitPvp.activeKit.remove(dead.getName());

		if (killer != null) {
			if (e.getEntity() == e.getEntity().getKiller()) {
				return;
			}

			if (e.getEntity().getKiller() != null) {
				Integer Coins = Integer.valueOf(plugin.getConfig()
						.getInt("players." + killer.getUniqueId().toString()
								+ ".coins"));
				this.plugin.getConfig()
						.set("players." + killer.getUniqueId().toString()
								+ ".coins",
								Integer.valueOf(Coins.intValue() + 50));
				killer.sendMessage("§a§l+50" + ChatColor.GOLD + " Coins");
				Bukkit.broadcastMessage(ChatColor.AQUA + dead.getName()
						+ ChatColor.GRAY + " was slain by " + ChatColor.AQUA
						+ killer.getName() + ChatColor.GRAY + "!");
				e.getDrops().clear();
				int k = plugin.getConfig().getInt(
						"players." + killer.getUniqueId() + ".kills");
				this.plugin.getConfig().set(
						"players."
								+ e.getEntity().getKiller().getUniqueId()
										.toString() + ".kills",
						Integer.valueOf(k + 1));
				int d = plugin.getConfig().getInt(
						"players." + dead.getUniqueId() + ".deaths");
				this.plugin.getConfig().set(
						"players." + dead.getUniqueId().toString() + ".deaths",
						Integer.valueOf(d + 1));
				this.plugin.getConfig().set(
						"players." + dead.getUniqueId().toString()
								+ ".curkillstreak", 0);

				int killstreak = this.plugin.getConfig().getInt(
						"players."
								+ e.getEntity().getKiller().getUniqueId()
										.toString() + ".curkillstreak");
				killstreak++;
				this.plugin.getConfig().set(
						"players."
								+ e.getEntity().getKiller().getUniqueId()
										.toString() + ".curkillstreak",
						Integer.valueOf(killstreak));
				if (killstreak > this.plugin.getConfig().getInt(
						"players."
								+ e.getEntity().getKiller().getUniqueId()
										.toString() + ".killstreak")) {
					this.plugin.getConfig().set(
							"players."
									+ e.getEntity().getKiller().getUniqueId()
											.toString() + ".killstreak",
							Integer.valueOf(killstreak));
				}
				if (killstreak == 5) {
					Bukkit.getServer()
							.broadcastMessage(
									"§a"
											+ e.getEntity().getKiller()
													.getName()
											+ " §ehas gotten §d§lRegeneration II §efor 30 seconds");
					e.getEntity()
							.getKiller()
							.addPotionEffect(
									new PotionEffect(
											PotionEffectType.REGENERATION, 600,
											1));
				} else if (killstreak == 10) {
					Bukkit.getServer().broadcastMessage(
							"§a" + e.getEntity().getKiller().getName()
									+ " §ehas gotten a §6§lfix all");
					if (killer.getInventory().getHelmet() != null) {
						Material old = killer.getInventory().getHelmet()
								.getType();
						ItemStack helm = new ItemStack(old);
						killer.getInventory().setHelmet(helm);
					}
					if (killer.getInventory().getChestplate() != null) {
						Material old = killer.getInventory().getChestplate()
								.getType();
						ItemStack chest = new ItemStack(old);
						killer.getInventory().setChestplate(chest);
					}
					if (killer.getInventory().getLeggings() != null) {
						Material old = killer.getInventory().getLeggings()
								.getType();
						ItemStack legs = new ItemStack(old);
						killer.getInventory().setLeggings(legs);
					}
					if (killer.getInventory().getBoots() != null) {
						Material old = killer.getInventory().getBoots()
								.getType();
						ItemStack boots = new ItemStack(old);
						killer.getInventory().setBoots(boots);
					}

					/*
					 * Bukkit.getServer().dispatchCommand(
					 * Bukkit.getServer().getConsoleSender(), "manuaddp " +
					 * e.getEntity().getKiller().getName() + " essentials.fix");
					 * Bukkit.getServer().dispatchCommand(
					 * Bukkit.getServer().getConsoleSender(), "manuaddp " +
					 * e.getEntity().getKiller().getName() +
					 * " essentials.fix.*");
					 * Bukkit.getServer().dispatchCommand(e
					 * .getEntity().getKiller(), "fix all");
					 * Bukkit.getServer().dispatchCommand(
					 * Bukkit.getServer().getConsoleSender(), "manudelp " +
					 * e.getEntity().getKiller().getName() + " essentials.fix");
					 * Bukkit.getServer().dispatchCommand(
					 * Bukkit.getServer().getConsoleSender(), "manudelp " +
					 * e.getEntity().getKiller().getName() +
					 * " essentials.fix.*");
					 */
				} else if (killstreak == 15) {
					Bukkit.getServer()
							.broadcastMessage(
									"§a"
											+ e.getEntity().getKiller()
													.getName()
											+ " §ehas gotten §b§lSpeed IV §aAnd §d§lRegeneration II §efor 10 seconds.");
					e.getEntity()
							.getKiller()
							.addPotionEffect(
									new PotionEffect(PotionEffectType.SPEED,
											200, 3));
					e.getEntity()
							.getKiller()
							.addPotionEffect(
									new PotionEffect(
											PotionEffectType.REGENERATION, 200,
											1));
				} else if (killstreak == 20) {
					Bukkit.getServer().broadcastMessage(
							"§a" + e.getEntity().getKiller().getName()
									+ " §ehas gotten a §6§lGod Apple.");

					killer.getInventory().addItem(
							new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1));
				} else if (killstreak == 25) {
					Bukkit.getServer()
							.broadcastMessage(
									"§a"
											+ e.getEntity().getKiller()
													.getName()
											+ " §ehas gotten §d§lRegeneration II §efor 30 seconds");
					e.getEntity()
							.getKiller()
							.addPotionEffect(
									new PotionEffect(
											PotionEffectType.REGENERATION, 600,
											1));
				} else if (killstreak == 30) {
					Bukkit.getServer().broadcastMessage(
							"§a" + e.getEntity().getKiller().getName()
									+ " §ehas gotten a §6§lfix all");
					if (killer.getInventory().getHelmet() != null) {
						Material old = killer.getInventory().getHelmet()
								.getType();
						if (killer.getInventory().getHelmet().getEnchantments()
								.size() >= 1) {
							Map<Enchantment, Integer> enchant = killer
									.getInventory().getHelmet()
									.getEnchantments();
							ItemStack helm = new ItemStack(old);
							helm.addEnchantments(enchant);
							killer.getInventory().setHelmet(helm);
						} else {
							ItemStack helm = new ItemStack(old);
							killer.getInventory().setHelmet(helm);
						}
					}
					if (killer.getInventory().getChestplate() != null) {
						Material old = killer.getInventory().getChestplate()
								.getType();
						if (killer.getInventory().getChestplate()
								.getEnchantments().size() >= 1) {
							Map<Enchantment, Integer> enchant = killer
									.getInventory().getChestplate()
									.getEnchantments();
							ItemStack chest = new ItemStack(old);
							chest.addEnchantments(enchant);
							killer.getInventory().setChestplate(chest);
							;
						} else {
							ItemStack chest = new ItemStack(old);
							killer.getInventory().setChestplate(chest);
						}
					}
					if (killer.getInventory().getLeggings() != null) {
						Material old = killer.getInventory().getLeggings()
								.getType();
						if (killer.getInventory().getLeggings()
								.getEnchantments().size() >= 1) {
							Map<Enchantment, Integer> enchant = killer
									.getInventory().getLeggings()
									.getEnchantments();
							ItemStack legs = new ItemStack(old);
							legs.addEnchantments(enchant);
							killer.getInventory().setLeggings(legs);
						} else {
							ItemStack legs = new ItemStack(old);
							killer.getInventory().setLeggings(legs);
						}
					}
					if (killer.getInventory().getBoots() != null) {
						Material old = killer.getInventory().getBoots()
								.getType();
						if (killer.getInventory().getBoots().getEnchantments()
								.size() >= 1) {
							Map<Enchantment, Integer> enchant = killer
									.getInventory().getBoots()
									.getEnchantments();
							ItemStack boots = new ItemStack(old);
							boots.addEnchantments(enchant);
							killer.getInventory().setBoots(boots);
						} else {
							ItemStack boots = new ItemStack(old);
							killer.getInventory().setBoots(boots);
						}
					}

					/*
					 * Bukkit.getServer().dispatchCommand(
					 * Bukkit.getServer().getConsoleSender(), "manuaddp " +
					 * e.getEntity().getKiller().getName() + " essentials.fix");
					 * Bukkit.getServer().dispatchCommand(
					 * Bukkit.getServer().getConsoleSender(), "manuaddp " +
					 * e.getEntity().getKiller().getName() +
					 * " essentials.fix.*");
					 * Bukkit.getServer().dispatchCommand(e
					 * .getEntity().getKiller(), "fix all");
					 * Bukkit.getServer().dispatchCommand(
					 * Bukkit.getServer().getConsoleSender(), "manudelp " +
					 * e.getEntity().getKiller().getName() + " essentials.fix");
					 * Bukkit.getServer().dispatchCommand(
					 * Bukkit.getServer().getConsoleSender(), "manudelp " +
					 * e.getEntity().getKiller().getName() +
					 * " essentials.fix.*");
					 */
				} else if (killstreak == 35) {
					Bukkit.getServer()
							.broadcastMessage(
									"§a"
											+ e.getEntity().getKiller()
													.getName()
											+ " §ehas gotten §b§lSpeed IV §aAnd §d§lRegeneration II §efor 10 seconds.");
					e.getEntity()
							.getKiller()
							.addPotionEffect(
									new PotionEffect(PotionEffectType.SPEED,
											200, 3));
					e.getEntity()
							.getKiller()
							.addPotionEffect(
									new PotionEffect(
											PotionEffectType.REGENERATION, 200,
											1));
				} else if (killstreak == 40) {
					Bukkit.getServer().broadcastMessage(
							"§a" + e.getEntity().getKiller().getName()
									+ " §ehas gotten a §6§lGod Apple.");

					killer.getInventory().addItem(
							new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1));
				}
				for (int i = 3; i < 10; i++) {
					if (killstreak == 10 * i) {
						Bukkit.getServer().broadcastMessage(
								"§a" + e.getEntity().getKiller().getName()
										+ " §ehas gotten §a§l50 §6Coins!");
						Integer Coins1 = Integer
								.valueOf(this.plugin.getConfig().getInt(
										"players."
												+ killer.getUniqueId()
														.toString() + ".coins"));
						this.plugin.getConfig().set(
								"players." + killer.getUniqueId().toString()
										+ ".coins",
								Integer.valueOf(Coins1.intValue() + 50));
						killer.sendMessage("§a§l+50" + ChatColor.GOLD
								+ " Coins");
					}
				}
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "update");

				int level = plugin.getConfig()
						.getInt("players." + killer.getUniqueId().toString()
								+ ".level");
				int kills = plugin.getConfig()
						.getInt("players." + killer.getUniqueId().toString()
								+ ".kills");
				if (level == 1 && kills >= 5) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 2 && kills >= 10) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 3 && kills >= 15) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 4 && kills >= 20) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 5 && kills >= 25) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 6 && kills >= 30) {
					plugin.getLogger().info("Player level up to level 7");
					level = level + 1;
					System.out.println("Saved Data");
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
					System.out.println("broadcasted!");
				} else if (level == 7 && kills >= 35) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 8 && kills >= 40) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 9 && kills >= 45) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 10 && kills >= 50) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 11 && kills >= 55) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 12 && kills >= 60) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 13 && kills >= 65) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 14 && kills >= 70) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 15 && kills >= 75) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 16 && kills >= 80) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 17 && kills >= 85) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 18 && kills >= 90) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
				} else if (level == 19 && kills >= 95) {
					level = level + 1;
					Bukkit.getServer().broadcastMessage(
							ChatColor.BLUE + killer.getName()
									+ ChatColor.DARK_AQUA
									+ " just ranked up to level "
									+ ChatColor.GREEN + level
									+ ChatColor.DARK_AQUA + "!");
					if (plugin.getConfig().getInt(
							"players." + killer.getUniqueId().toString()
									+ ".prestige") == 10) {
						killer.sendMessage(ChatColor.GREEN
								+ "Congrats! You have reached the "
								+ ChatColor.DARK_AQUA + "max "
								+ ChatColor.GREEN + "level!");
					} else {
						plugin.getConfig().set(
								"players." + killer.getUniqueId().toString()
										+ ".prestigeMode",
								Boolean.valueOf(true));
						killer.sendMessage(ChatColor.GREEN
								+ "Congrats! You have reached the "
								+ ChatColor.DARK_AQUA + "max "
								+ ChatColor.GREEN + "level!");
						killer.sendMessage(ChatColor.GREEN
								+ "You can use /prestige to reset your K/D ratio and unlock a special kit!");
					}
				}

				plugin.getConfig()
						.set("players." + killer.getUniqueId().toString()
								+ ".level", level);
				plugin.saveConfig();
			}
		}
	}

	@EventHandler
	public void c(PlayerDeathEvent e) {
		Player dead = (Player) e.getEntity();
		Player killer = dead.getKiller();
		if (HillMoveEvent.win.containsKey(dead.getName())) {
			HillMoveEvent.win.put(dead.getName(), 0);
			HillMoveEvent.win.remove(dead.getName());
			HillMoveEvent.activePlayer = killer.getName();
			dead.sendMessage(ChatColor.RED + "You lost the hill to "
					+ killer.getName());
			Bukkit.getServer().getScheduler().cancelTask(HillMoveEvent.task1);
			HillMoveEvent.active = false;
			if (locu.isWithinHill(killer.getLocation())) {
				Bukkit.getServer().broadcastMessage(
						ChatColor.GOLD + killer.getName() + ChatColor.GREEN
								+ " Captured the hill from " + dead.getName());
				KitPvp.activeKit.remove(dead.getName());
			}
		}
	}

	@EventHandler
	public void d(PlayerDeathEvent e) {
		final Player dead = (Player) e.getEntity();
		final Player killer = dead.getKiller();
		if (Duels.challenger != null) {
			if (Duels.challenger.equalsIgnoreCase(killer.getName())
					|| (Duels.challenger.equalsIgnoreCase(dead.getName()))) {
				double x = plugin.getConfig().getDouble("spawn.x");
				double y = plugin.getConfig().getDouble("spawn.y");
				double z = plugin.getConfig().getDouble("spawn.z");
				float pitch = (float) plugin.getConfig().getDouble(
						"spawn.pitch");
				float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
				String world = plugin.getConfig().getString("spawn.world");
				final Location loc = new Location(Bukkit.getServer().getWorld(
						world), x, y, z, yaw, pitch);
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
						new Runnable() {
							public void run() {
								killer.teleport(loc);
								killer.getInventory().clear();
								killer.getInventory().setHelmet(null);
								killer.getInventory().setChestplate(null);
								killer.getInventory().setLeggings(null);
								killer.getInventory().setBoots(null);

								killer.setFireTicks(0);
								killer.setGameMode(GameMode.SURVIVAL);
								killer.setFoodLevel(20);
								killer.setHealthScale(20.0D);
								killer.setHealth(20.0D);
								int wins = plugin.getConfig().getInt(
										"players."
												+ killer.getUniqueId()
														.toString()
												+ ".1v1wins");
								int loses = plugin.getConfig().getInt(
										"players."
												+ dead.getUniqueId().toString()
												+ ".1v1loses");

								plugin.getConfig().set(
										"players."
												+ killer.getUniqueId()
														.toString()
												+ ".1v1wins",
										Integer.valueOf(wins + 1));

								plugin.getConfig().set(
										"players."
												+ dead.getUniqueId().toString()
												+ ".1v1loses",
										Integer.valueOf(loses + 1));
								plugin.saveConfig();
								ItemStack stats = new ItemStack(Material.PAPER);
								ItemMeta statsmeta = stats.getItemMeta();
								statsmeta.setDisplayName(ChatColor.RED
										+ "Stats");
								ArrayList<String> statslore = new ArrayList<String>();
								statslore.add(ChatColor.GREEN
										+ "Click to see your");
								statslore
										.add(ChatColor.GREEN + "Kitpvp stats!");
								statsmeta.setLore(statslore);
								stats.setItemMeta(statsmeta);

								ItemStack kit = new ItemStack(Material.WATCH);
								ItemMeta kitmeta = kit.getItemMeta();
								kitmeta.setDisplayName(ChatColor.AQUA
										+ "Kit Selector");
								ArrayList<String> kitlore = new ArrayList<String>();
								kitlore.add(ChatColor.AQUA + "Right Click to");
								kitlore.add(ChatColor.AQUA + "Chose Kit!");
								kitmeta.setLore(kitlore);
								kit.setItemMeta(kitmeta);

								ItemStack shop = new ItemStack(Material.CHEST);
								ItemMeta shopp = shop.getItemMeta();
								shopp.setDisplayName(ChatColor.YELLOW
										+ "Donor Ranks!");
								ArrayList<String> slore = new ArrayList<String>();
								slore.add(ChatColor.YELLOW + "Right Click to");
								slore.add(ChatColor.YELLOW
										+ "View all donation ranks!");
								shopp.setLore(slore);
								shop.setItemMeta(shopp);

								ItemStack mode = new ItemStack(
										Material.DIAMOND_SWORD);
								ItemMeta modemeta = mode.getItemMeta();
								modemeta.setDisplayName(ChatColor.DARK_RED
										+ "PvP Modes");
								ArrayList<String> modelore = new ArrayList<String>();
								modelore.add(ChatColor.AQUA
										+ "Right click to view");
								modelore.add(ChatColor.AQUA
										+ "Avaliable Pvp modes!");
								modemeta.setLore(modelore);
								mode.setItemMeta(modemeta);

								ItemStack pvp = new ItemStack(
										Material.CLAY_BALL);
								ItemMeta pvpmeta = pvp.getItemMeta();
								pvpmeta.setDisplayName(ChatColor.YELLOW + "1v1");
								ArrayList<String> lore = new ArrayList<String>();
								lore.add(ChatColor.AQUA
										+ "Right click a player to 1v1!");
								pvpmeta.setLore(lore);
								pvp.setItemMeta(pvpmeta);

								ItemStack itemshop = new ItemStack(
										Material.BLAZE_POWDER);
								ItemMeta shopmeta = itemshop.getItemMeta();
								shopmeta.setDisplayName(ChatColor.GREEN
										+ "Item Shop");
								ArrayList<String> shoplore = new ArrayList<String>();
								shoplore.add(ChatColor.RED
										+ "Be sure to have space in your inventory!");
								shopmeta.setLore(shoplore);
								itemshop.setItemMeta(shopmeta);
								killer.getInventory().setItem(8, itemshop);

								TabUtils.sendTabHeaderFooter(killer,
										ChatColor.RED + "Flame"
												+ ChatColor.AQUA + " Network",
										ChatColor.GRAY + "IP " + ChatColor.GOLD
												+ "mc.flamesurvival.com"
												+ ChatColor.GRAY + "\nShop @ "
												+ ChatColor.GREEN
												+ "store.flamehub.net");

								KitPvp.activeKit.remove(killer.getName());
								KitPvp.scorp.remove(killer.getName());

								Duels.arenaactive = false;
								Duels.challenger = null;

								killer.getInventory().addItem(
										new ItemStack[] { kit });
								killer.getInventory().addItem(
										new ItemStack[] { stats });
								killer.getInventory().addItem(
										new ItemStack[] { shop });
								killer.getInventory().addItem(
										new ItemStack[] { pvp });
								killer.getInventory().addItem(mode);
								Bukkit.dispatchCommand(
										Bukkit.getConsoleSender(), "update");
							}
						}, 220);
				Bukkit.broadcastMessage(ChatColor.AQUA + killer.getName()
						+ ChatColor.GRAY + " beat " + ChatColor.AQUA
						+ dead.getName() + ChatColor.GRAY + " in a 1v1!");
				dead.sendMessage(ChatColor.RED + "You lost a 1v1 to "
						+ killer.getName());
				killer.sendMessage(ChatColor.GREEN + "You won a 1v1 vs "
						+ dead.getName());
				killer.sendMessage(ChatColor.YELLOW
						+ "Teleporting to spawn in 10 seconds...");
				this.plugin.getConfig().set(
						"players." + dead.getUniqueId().toString()
								+ ".curkillstreak", Integer.valueOf(0));
				this.plugin.saveConfig();
			}
		}
	}
}
