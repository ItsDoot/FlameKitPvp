package com.bear53.ecore.kitpvp;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.bear53.ecore.Core;

public class PrestigeClass implements CommandExecutor, Listener {

	Core plugin;

	public PrestigeClass(Core pl) {
		this.plugin = pl;
	}

	@EventHandler
	public void speed(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR
				|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if (p.getItemInHand() != null) {
				if (p.getItemInHand().getItemMeta() != null) {
					if (p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if (p.getItemInHand()
								.getItemMeta()
								.getDisplayName()
								.equalsIgnoreCase(
										ChatColor.RED + "Prestige Effects")) {
							p.addPotionEffect(new PotionEffect(
									PotionEffectType.SPEED, Integer
											.valueOf(300), 2));
							ItemStack prestigeSpeed = new ItemStack(
									Material.FEATHER, 1);
							ItemMeta fm = prestigeSpeed.getItemMeta();
							fm.setDisplayName(ChatColor.RED
									+ "Prestige Effects");
							ArrayList<String> fl = new ArrayList<String>();
							fl.add(ChatColor.AQUA
									+ "Gives a 30 second speed boost");
							fl.add(ChatColor.AQUA
									+ "Gives you 5 seconds of flight!");
							fm.setLore(fl);
							prestigeSpeed.setItemMeta(fm);
							p.getInventory().removeItem(prestigeSpeed);
							p.updateInventory();
						}
					}
				}
			}
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("Prestigeclass")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin
						.getConfig()
						.getStringList(
								"players." + p.getUniqueId().toString()
										+ ".kits").contains("Prestige")) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN
							+ "You have activated the Prestige Kit!");
					int prestigeLevel = plugin.getConfig().getInt(
							"players." + p.getUniqueId().toString()
									+ ".prestige");
					if (prestigeLevel == 1) {
						ItemStack s = new ItemStack(Material.GOLD_SWORD);
						s.addEnchantment(Enchantment.DAMAGE_ALL, 3);
						s.addEnchantment(Enchantment.DURABILITY, 3);
						ItemStack h = new ItemStack(Material.GOLD_HELMET);
						h.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack c = new ItemStack(Material.GOLD_CHESTPLATE);
						c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack l = new ItemStack(Material.GOLD_LEGGINGS);
						l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack b = new ItemStack(Material.GOLD_BOOTS);
						b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack prestigeSpeed = new ItemStack(
								Material.FEATHER);
						ItemMeta fm = prestigeSpeed.getItemMeta();
						fm.setDisplayName(ChatColor.RED + "Prestige Effects");
						ArrayList<String> fl = new ArrayList<String>();
						fl.add(ChatColor.AQUA + "Gives a 30 second speed boost");
						fl.add(ChatColor.AQUA
								+ "Gives you 5 seconds of flight!");
						fm.setLore(fl);
						prestigeSpeed.setItemMeta(fm);

						p.getInventory().clear();
						KitPvp.clearEffects(p);
						ItemStack itemshop = new ItemStack(
								Material.BLAZE_POWDER);
						ItemMeta shopmeta = itemshop.getItemMeta();
						shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
						ArrayList<String> shoplore = new ArrayList<String>();
						shoplore.add(ChatColor.RED
								+ "Be sure to have space in your inventory!");
						shopmeta.setLore(shoplore);
						itemshop.setItemMeta(shopmeta);
						p.getInventory().setItem(8, itemshop);
						p.getInventory().addItem(new ItemStack[] { s });
						p.getInventory().addItem(
								new ItemStack[] { prestigeSpeed });
						p.getInventory().setHelmet(h);
						p.getInventory().setChestplate(c);
						p.getInventory().setLeggings(l);
						p.getInventory().setBoots(b);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
						if (plugin.getConfig().getBoolean("potion-enabled")) {
							KitPvp.giveHeath(p);
						} else {
							KitPvp.giveSoup(p);
						}
					} else if (prestigeLevel == 2) {
						ItemStack s = new ItemStack(Material.GOLD_SWORD);
						s.addEnchantment(Enchantment.DAMAGE_ALL, 5);
						s.addEnchantment(Enchantment.DURABILITY, 3);
						ItemStack h = new ItemStack(Material.GOLD_HELMET);
						h.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack c = new ItemStack(Material.GOLD_CHESTPLATE);
						c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack l = new ItemStack(Material.GOLD_LEGGINGS);
						l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack b = new ItemStack(Material.GOLD_BOOTS);
						b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack prestigeSpeed = new ItemStack(
								Material.FEATHER);
						ItemMeta fm = prestigeSpeed.getItemMeta();
						fm.setDisplayName(ChatColor.RED + "Prestige Effects");
						ArrayList<String> fl = new ArrayList<String>();
						fl.add(ChatColor.AQUA + "Gives a 30 second speed boost");
						fl.add(ChatColor.AQUA
								+ "Gives you 5 seconds of flight!");
						fm.setLore(fl);
						prestigeSpeed.setItemMeta(fm);

						p.getInventory().clear();
						KitPvp.clearEffects(p);
						ItemStack itemshop = new ItemStack(
								Material.BLAZE_POWDER);
						ItemMeta shopmeta = itemshop.getItemMeta();
						shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
						ArrayList<String> shoplore = new ArrayList<String>();
						shoplore.add(ChatColor.RED
								+ "Be sure to have space in your inventory!");
						shopmeta.setLore(shoplore);
						itemshop.setItemMeta(shopmeta);
						p.getInventory().setItem(8, itemshop);
						p.getInventory().addItem(new ItemStack[] { s });
						p.getInventory().addItem(
								new ItemStack[] { prestigeSpeed });
						p.getInventory().setHelmet(h);
						p.getInventory().setChestplate(c);
						p.getInventory().setLeggings(l);
						p.getInventory().setBoots(b);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
						if (plugin.getConfig().getBoolean("potion-enabled")) {
							KitPvp.giveHeath(p);
						} else {
							KitPvp.giveSoup(p);
						}
					} else if (prestigeLevel == 3) {
						ItemStack s = new ItemStack(Material.GOLD_SWORD);
						s.addEnchantment(Enchantment.DAMAGE_ALL, 5);
						s.addEnchantment(Enchantment.DURABILITY, 3);
						s.addEnchantment(Enchantment.KNOCKBACK, 1);
						ItemStack h = new ItemStack(Material.GOLD_HELMET);
						h.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack c = new ItemStack(Material.GOLD_CHESTPLATE);
						c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack l = new ItemStack(Material.GOLD_LEGGINGS);
						l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack b = new ItemStack(Material.GOLD_BOOTS);
						b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack prestigeSpeed = new ItemStack(
								Material.FEATHER, 2);
						ItemMeta fm = prestigeSpeed.getItemMeta();
						fm.setDisplayName(ChatColor.RED + "Prestige Effects");
						ArrayList<String> fl = new ArrayList<String>();
						fl.add(ChatColor.AQUA + "Gives a 30 second speed boost");
						fl.add(ChatColor.AQUA
								+ "Gives you 5 seconds of flight!");
						fm.setLore(fl);
						prestigeSpeed.setItemMeta(fm);

						p.getInventory().clear();
						KitPvp.clearEffects(p);
						ItemStack itemshop = new ItemStack(
								Material.BLAZE_POWDER);
						ItemMeta shopmeta = itemshop.getItemMeta();
						shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
						ArrayList<String> shoplore = new ArrayList<String>();
						shoplore.add(ChatColor.RED
								+ "Be sure to have space in your inventory!");
						shopmeta.setLore(shoplore);
						itemshop.setItemMeta(shopmeta);
						p.getInventory().setItem(8, itemshop);
						p.getInventory().addItem(new ItemStack[] { s });
						p.getInventory().addItem(
								new ItemStack[] { prestigeSpeed });
						p.getInventory().setHelmet(h);
						p.getInventory().setChestplate(c);
						p.getInventory().setLeggings(l);
						p.getInventory().setBoots(b);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
						if (plugin.getConfig().getBoolean("potion-enabled")) {
							KitPvp.giveHeath(p);
						} else {
							KitPvp.giveSoup(p);
						}
					} else if (prestigeLevel == 4) {
						ItemStack s = new ItemStack(Material.GOLD_SWORD);
						s.addEnchantment(Enchantment.DAMAGE_ALL, 4);
						s.addEnchantment(Enchantment.DURABILITY, 3);
						s.addEnchantment(Enchantment.KNOCKBACK, 2);
						ItemStack h = new ItemStack(Material.GOLD_HELMET);
						h.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack c = new ItemStack(Material.GOLD_CHESTPLATE);
						c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack l = new ItemStack(Material.GOLD_LEGGINGS);
						l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack b = new ItemStack(Material.GOLD_BOOTS);
						b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack prestigeSpeed = new ItemStack(
								Material.FEATHER, 2);
						ItemMeta fm = prestigeSpeed.getItemMeta();
						fm.setDisplayName(ChatColor.RED + "Prestige Effects");
						ArrayList<String> fl = new ArrayList<String>();
						fl.add(ChatColor.AQUA + "Gives a 30 second speed boost");
						fl.add(ChatColor.AQUA
								+ "Gives you 5 seconds of flight!");
						fm.setLore(fl);
						prestigeSpeed.setItemMeta(fm);

						p.getInventory().clear();
						KitPvp.clearEffects(p);
						ItemStack itemshop = new ItemStack(
								Material.BLAZE_POWDER);
						ItemMeta shopmeta = itemshop.getItemMeta();
						shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
						ArrayList<String> shoplore = new ArrayList<String>();
						shoplore.add(ChatColor.RED
								+ "Be sure to have space in your inventory!");
						shopmeta.setLore(shoplore);
						itemshop.setItemMeta(shopmeta);
						p.getInventory().setItem(8, itemshop);
						p.getInventory().addItem(new ItemStack[] { s });
						p.getInventory().addItem(
								new ItemStack[] { prestigeSpeed });
						p.getInventory().setHelmet(h);
						p.getInventory().setChestplate(c);
						p.getInventory().setLeggings(l);
						p.getInventory().setBoots(b);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
						if (plugin.getConfig().getBoolean("potion-enabled")) {
							KitPvp.giveHeath(p);
						} else {
							KitPvp.giveSoup(p);
						}
					} else if (prestigeLevel == 5) {
						ItemStack s = new ItemStack(Material.IRON_SWORD);
						s.addEnchantment(Enchantment.DAMAGE_ALL, 3);
						s.addEnchantment(Enchantment.DURABILITY, 3);
						ItemStack h = new ItemStack(Material.GOLD_HELMET);
						h.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack c = new ItemStack(Material.GOLD_CHESTPLATE);
						c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack l = new ItemStack(Material.GOLD_LEGGINGS);
						l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack b = new ItemStack(Material.GOLD_BOOTS);
						b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack prestigeSpeed = new ItemStack(
								Material.FEATHER, 2);
						ItemMeta fm = prestigeSpeed.getItemMeta();
						fm.setDisplayName(ChatColor.RED + "Prestige Effects");
						ArrayList<String> fl = new ArrayList<String>();
						fl.add(ChatColor.AQUA + "Gives a 30 second speed boost");
						fl.add(ChatColor.AQUA
								+ "Gives you 5 seconds of flight!");
						fm.setLore(fl);
						prestigeSpeed.setItemMeta(fm);

						p.getInventory().clear();
						KitPvp.clearEffects(p);
						ItemStack itemshop = new ItemStack(
								Material.BLAZE_POWDER);
						ItemMeta shopmeta = itemshop.getItemMeta();
						shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
						ArrayList<String> shoplore = new ArrayList<String>();
						shoplore.add(ChatColor.RED
								+ "Be sure to have space in your inventory!");
						shopmeta.setLore(shoplore);
						itemshop.setItemMeta(shopmeta);
						ItemStack Grenade = new ItemStack(Material.SLIME_BALL);
						ItemMeta Grenademeta = Grenade.getItemMeta();
						Grenademeta.setDisplayName(ChatColor.RED
								+ "Lethal Grenade");
						Grenade.setItemMeta(Grenademeta);
						p.getInventory().setItem(8, itemshop);
						p.getInventory().addItem(new ItemStack[] { s });
						p.getInventory().addItem(
								new ItemStack[] { prestigeSpeed });
						p.getInventory().addItem(Grenade);
						p.getInventory().setHelmet(h);
						p.getInventory().setChestplate(c);
						p.getInventory().setLeggings(l);
						p.getInventory().setBoots(b);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
						if (plugin.getConfig().getBoolean("potion-enabled")) {
							KitPvp.giveHeath(p);
						} else {
							KitPvp.giveSoup(p);
						}
					} else if (prestigeLevel == 6) {
						ItemStack s = new ItemStack(Material.IRON_SWORD);
						s.addEnchantment(Enchantment.DAMAGE_ALL, 3);
						s.addEnchantment(Enchantment.DURABILITY, 3);
						s.addEnchantment(Enchantment.FIRE_ASPECT, 1);
						ItemStack h = new ItemStack(Material.GOLD_HELMET);
						h.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack c = new ItemStack(Material.GOLD_CHESTPLATE);
						c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack l = new ItemStack(Material.GOLD_LEGGINGS);
						l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack b = new ItemStack(Material.GOLD_BOOTS);
						b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack prestigeSpeed = new ItemStack(
								Material.FEATHER, 2);
						ItemMeta fm = prestigeSpeed.getItemMeta();
						fm.setDisplayName(ChatColor.RED + "Prestige Effects");
						ArrayList<String> fl = new ArrayList<String>();
						fl.add(ChatColor.AQUA + "Gives a 30 second speed boost");
						fl.add(ChatColor.AQUA
								+ "Gives you 5 seconds of flight!");
						fm.setLore(fl);
						prestigeSpeed.setItemMeta(fm);

						p.getInventory().clear();
						KitPvp.clearEffects(p);
						ItemStack itemshop = new ItemStack(
								Material.BLAZE_POWDER);
						ItemMeta shopmeta = itemshop.getItemMeta();
						shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
						ArrayList<String> shoplore = new ArrayList<String>();
						shoplore.add(ChatColor.RED
								+ "Be sure to have space in your inventory!");
						shopmeta.setLore(shoplore);
						itemshop.setItemMeta(shopmeta);
						ItemStack Grenade = new ItemStack(Material.SLIME_BALL);
						ItemMeta Grenademeta = Grenade.getItemMeta();
						Grenademeta.setDisplayName(ChatColor.RED
								+ "Lethal Grenade");
						Grenade.setItemMeta(Grenademeta);
						p.getInventory().setItem(8, itemshop);
						p.getInventory().addItem(new ItemStack[] { s });
						p.getInventory().addItem(
								new ItemStack[] { prestigeSpeed });
						p.getInventory().addItem(Grenade);
						p.getInventory().setHelmet(h);
						p.getInventory().setChestplate(c);
						p.getInventory().setLeggings(l);
						p.getInventory().setBoots(b);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
						if (plugin.getConfig().getBoolean("potion-enabled")) {
							KitPvp.giveHeath(p);
						} else {
							KitPvp.giveSoup(p);
						}
					} else if (prestigeLevel == 7) {
						ItemStack s = new ItemStack(Material.IRON_SWORD);
						s.addEnchantment(Enchantment.DAMAGE_ALL, 3);
						s.addEnchantment(Enchantment.DURABILITY, 3);
						s.addEnchantment(Enchantment.FIRE_ASPECT, 1);
						ItemStack h = new ItemStack(Material.IRON_HELMET);
						h.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								2);
						ItemStack c = new ItemStack(Material.IRON_CHESTPLATE);
						c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								2);
						ItemStack l = new ItemStack(Material.IRON_LEGGINGS);
						l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								2);
						ItemStack b = new ItemStack(Material.IRON_BOOTS);
						b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								2);
						ItemStack prestigeSpeed = new ItemStack(
								Material.FEATHER, 2);
						ItemMeta fm = prestigeSpeed.getItemMeta();
						fm.setDisplayName(ChatColor.RED + "Prestige Effects");
						ArrayList<String> fl = new ArrayList<String>();
						fl.add(ChatColor.AQUA + "Gives a 30 second speed boost");
						fl.add(ChatColor.AQUA
								+ "Gives you 5 seconds of flight!");
						fm.setLore(fl);
						prestigeSpeed.setItemMeta(fm);

						p.getInventory().clear();
						KitPvp.clearEffects(p);
						ItemStack itemshop = new ItemStack(
								Material.BLAZE_POWDER);
						ItemMeta shopmeta = itemshop.getItemMeta();
						shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
						ArrayList<String> shoplore = new ArrayList<String>();
						shoplore.add(ChatColor.RED
								+ "Be sure to have space in your inventory!");
						shopmeta.setLore(shoplore);
						itemshop.setItemMeta(shopmeta);
						ItemStack Grenade = new ItemStack(Material.SLIME_BALL);
						ItemMeta Grenademeta = Grenade.getItemMeta();
						Grenademeta.setDisplayName(ChatColor.RED
								+ "Lethal Grenade");
						Grenade.setItemMeta(Grenademeta);
						p.getInventory().setItem(8, itemshop);
						p.getInventory().addItem(new ItemStack[] { s });
						p.getInventory().addItem(
								new ItemStack[] { prestigeSpeed });
						p.getInventory().addItem(Grenade);
						p.getInventory().setHelmet(h);
						p.getInventory().setChestplate(c);
						p.getInventory().setLeggings(l);
						p.getInventory().setBoots(b);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
						if (plugin.getConfig().getBoolean("potion-enabled")) {
							KitPvp.giveHeath(p);
						} else {
							KitPvp.giveSoup(p);
						}
					} else if (prestigeLevel == 8) {
						ItemStack s = new ItemStack(Material.IRON_SWORD);
						s.addEnchantment(Enchantment.DAMAGE_ALL, 3);
						s.addEnchantment(Enchantment.DURABILITY, 3);
						s.addEnchantment(Enchantment.FIRE_ASPECT, 2);
						ItemStack h = new ItemStack(Material.IRON_HELMET);
						h.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack c = new ItemStack(Material.IRON_CHESTPLATE);
						c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack l = new ItemStack(Material.IRON_LEGGINGS);
						l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack b = new ItemStack(Material.IRON_BOOTS);
						b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack prestigeSpeed = new ItemStack(
								Material.FEATHER, 2);
						ItemMeta fm = prestigeSpeed.getItemMeta();
						fm.setDisplayName(ChatColor.RED + "Prestige Effects");
						ArrayList<String> fl = new ArrayList<String>();
						fl.add(ChatColor.AQUA + "Gives a 30 second speed boost");
						fl.add(ChatColor.AQUA
								+ "Gives you 5 seconds of flight!");
						fm.setLore(fl);
						prestigeSpeed.setItemMeta(fm);

						p.getInventory().clear();
						KitPvp.clearEffects(p);
						ItemStack itemshop = new ItemStack(
								Material.BLAZE_POWDER);
						ItemMeta shopmeta = itemshop.getItemMeta();
						shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
						ArrayList<String> shoplore = new ArrayList<String>();
						shoplore.add(ChatColor.RED
								+ "Be sure to have space in your inventory!");
						shopmeta.setLore(shoplore);
						itemshop.setItemMeta(shopmeta);
						ItemStack Grenade = new ItemStack(Material.SLIME_BALL);
						ItemMeta Grenademeta = Grenade.getItemMeta();
						Grenademeta.setDisplayName(ChatColor.RED
								+ "Lethal Grenade");
						Grenade.setItemMeta(Grenademeta);
						p.getInventory().setItem(8, itemshop);
						p.getInventory().addItem(new ItemStack[] { s });
						p.getInventory().addItem(
								new ItemStack[] { prestigeSpeed });
						p.getInventory().addItem(Grenade);
						p.getInventory().setHelmet(h);
						p.getInventory().setChestplate(c);
						p.getInventory().setLeggings(l);
						p.getInventory().setBoots(b);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
						if (plugin.getConfig().getBoolean("potion-enabled")) {
							KitPvp.giveHeath(p);
						} else {
							KitPvp.giveSoup(p);
						}
					} else if (prestigeLevel == 9) {
						ItemStack s = new ItemStack(Material.IRON_SWORD);
						s.addEnchantment(Enchantment.DAMAGE_ALL, 3);
						s.addEnchantment(Enchantment.DURABILITY, 3);
						s.addEnchantment(Enchantment.FIRE_ASPECT, 2);
						ItemStack h = new ItemStack(Material.IRON_HELMET);
						h.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack c = new ItemStack(Material.IRON_CHESTPLATE);
						c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack l = new ItemStack(Material.IRON_LEGGINGS);
						l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack b = new ItemStack(Material.IRON_BOOTS);
						b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
								3);
						ItemStack prestigeSpeed = new ItemStack(
								Material.FEATHER, 3);
						ItemMeta fm = prestigeSpeed.getItemMeta();
						fm.setDisplayName(ChatColor.RED + "Prestige Effects");
						ArrayList<String> fl = new ArrayList<String>();
						fl.add(ChatColor.AQUA + "Gives a 30 second speed boost");
						fl.add(ChatColor.AQUA
								+ "Gives you 5 seconds of flight!");
						fm.setLore(fl);
						prestigeSpeed.setItemMeta(fm);

						p.getInventory().clear();
						KitPvp.clearEffects(p);
						ItemStack itemshop = new ItemStack(
								Material.BLAZE_POWDER);
						ItemMeta shopmeta = itemshop.getItemMeta();
						shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
						ArrayList<String> shoplore = new ArrayList<String>();
						shoplore.add(ChatColor.RED
								+ "Be sure to have space in your inventory!");
						shopmeta.setLore(shoplore);
						itemshop.setItemMeta(shopmeta);
						ItemStack Grenade = new ItemStack(Material.SLIME_BALL,
								2);
						ItemMeta Grenademeta = Grenade.getItemMeta();
						Grenademeta.setDisplayName(ChatColor.RED
								+ "Lethal Grenade");
						Grenade.setItemMeta(Grenademeta);
						p.getInventory().setItem(8, itemshop);
						p.getInventory().addItem(new ItemStack[] { s });
						p.getInventory().addItem(
								new ItemStack[] { prestigeSpeed });
						p.getInventory().addItem(Grenade);
						p.getInventory().setHelmet(h);
						p.getInventory().setChestplate(c);
						p.getInventory().setLeggings(l);
						p.getInventory().setBoots(b);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
						if (plugin.getConfig().getBoolean("potion-enabled")) {
							KitPvp.giveHeath(p);
						} else {
							KitPvp.giveSoup(p);
						}
					} else if (prestigeLevel == 10) {
						ItemStack s = new ItemStack(Material.IRON_SWORD);
						s.addEnchantment(Enchantment.DAMAGE_ALL, 4);
						s.addEnchantment(Enchantment.DURABILITY, 3);
						s.addEnchantment(Enchantment.FIRE_ASPECT, 2);
						ItemStack h = new ItemStack(Material.DIAMOND_HELMET);
						// h.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
						// 3);
						ItemStack c = new ItemStack(Material.DIAMOND_CHESTPLATE);
						// c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
						// 3);
						ItemStack l = new ItemStack(Material.DIAMOND_LEGGINGS);
						// l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
						// 3);
						ItemStack b = new ItemStack(Material.DIAMOND_BOOTS);
						// b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
						// 3);
						ItemStack prestigeSpeed = new ItemStack(
								Material.FEATHER, 4);
						ItemMeta fm = prestigeSpeed.getItemMeta();
						fm.setDisplayName(ChatColor.RED + "Prestige Effects");
						ArrayList<String> fl = new ArrayList<String>();
						fl.add(ChatColor.AQUA + "Gives a 30 second speed boost");
						fl.add(ChatColor.AQUA
								+ "Gives you 5 seconds of flight!");
						fm.setLore(fl);
						prestigeSpeed.setItemMeta(fm);

						p.getInventory().clear();
						KitPvp.clearEffects(p);
						ItemStack itemshop = new ItemStack(
								Material.BLAZE_POWDER);
						ItemMeta shopmeta = itemshop.getItemMeta();
						shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
						ArrayList<String> shoplore = new ArrayList<String>();
						shoplore.add(ChatColor.RED
								+ "Be sure to have space in your inventory!");
						shopmeta.setLore(shoplore);
						itemshop.setItemMeta(shopmeta);
						ItemStack Grenade = new ItemStack(Material.SLIME_BALL,
								5);
						ItemMeta Grenademeta = Grenade.getItemMeta();
						Grenademeta.setDisplayName(ChatColor.RED
								+ "Lethal Grenade");
						Grenade.setItemMeta(Grenademeta);
						p.getInventory().setItem(8, itemshop);
						p.getInventory().addItem(new ItemStack[] { s });
						p.getInventory().addItem(
								new ItemStack[] { prestigeSpeed });
						p.getInventory().addItem(Grenade);
						p.getInventory().setHelmet(h);
						p.getInventory().setChestplate(c);
						p.getInventory().setLeggings(l);
						p.getInventory().setBoots(b);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
						if (plugin.getConfig().getBoolean("potion-enabled")) {
							KitPvp.giveHeath(p);
						} else {
							KitPvp.giveSoup(p);
						}
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
}