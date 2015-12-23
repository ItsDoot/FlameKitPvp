package com.bear53.ecore.kitpvp.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.bear53.ecore.Core;
import com.bear53.ecore.kitpvp.KitPvp;

public class NinjaClass implements CommandExecutor {

	Core plugin;

	public NinjaClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("Ninja")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin
						.getConfig()
						.getStringList(
								"players." + p.getUniqueId().toString()
										+ ".kits").contains("Ninja")) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN
							+ "You have activated the Ninja Kit!");
					ItemStack Sword = new ItemStack(Material.IRON_SWORD);
					ItemStack Helm = new ItemStack(Material.LEATHER_HELMET, 1);
					LeatherArmorMeta im = (LeatherArmorMeta) Helm.getItemMeta();
					im.setColor(Color.BLACK);
					Helm.setItemMeta(im);
					ItemStack Chest = new ItemStack(
							Material.LEATHER_CHESTPLATE, 1);
					LeatherArmorMeta im2 = (LeatherArmorMeta) Chest
							.getItemMeta();
					im2.setColor(Color.BLACK);
					Chest.setItemMeta(im2);
					ItemStack Legs = new ItemStack(Material.LEATHER_LEGGINGS, 1);
					LeatherArmorMeta im3 = (LeatherArmorMeta) Legs
							.getItemMeta();
					im3.setColor(Color.BLACK);
					Legs.setItemMeta(im3);
					ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS, 1);
					LeatherArmorMeta im4 = (LeatherArmorMeta) Boots
							.getItemMeta();
					im4.setColor(Color.BLACK);
					Boots.setItemMeta(im4);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
							50000000, 2));
					Sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
					Helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
					Chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
							4);
					Legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
					Boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
							4);

					p.getInventory().clear();
					p.getInventory().addItem(new ItemStack[] { Sword });
					p.getInventory().setHelmet(Helm);
					p.getInventory().setChestplate(Chest);
					p.getInventory().setLeggings(Legs);
					p.getInventory().setBoots(Boots);
					ItemStack itemshop = new ItemStack(Material.BLAZE_POWDER);
					ItemMeta shopmeta = itemshop.getItemMeta();
					shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
					ArrayList<String> shoplore = new ArrayList<String>();
					shoplore.add(ChatColor.RED
							+ "Be sure to have space in your inventory!");
					shopmeta.setLore(shoplore);
					itemshop.setItemMeta(shopmeta);
					p.getInventory().setItem(8, itemshop);
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
					if (plugin.getConfig().getBoolean("potion-enabled")) {
						KitPvp.giveHeath(p);
					} else {
						KitPvp.giveSoup(p);
					}

					p.addPotionEffect(new PotionEffect(
							PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
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