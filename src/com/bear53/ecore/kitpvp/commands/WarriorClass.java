package com.bear53.ecore.kitpvp.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bear53.ecore.Core;
import com.bear53.ecore.kitpvp.KitPvp;

public class WarriorClass implements CommandExecutor {

	Core plugin;

	public WarriorClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("Warrior")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin
						.getConfig()
						.getStringList(
								"players." + p.getUniqueId().toString()
										+ ".kits").contains("Warrior")) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN
							+ "You have activated the Warrior Kit!");

					ItemStack LHelmet = new ItemStack(Material.LEATHER_HELMET);
					ItemStack IChestplate = new ItemStack(
							Material.IRON_CHESTPLATE);
					ItemStack ILeggings = new ItemStack(Material.IRON_LEGGINGS);
					ItemStack LBoots = new ItemStack(Material.LEATHER_BOOTS);
					ItemStack ISword = new ItemStack(Material.IRON_SWORD);
					LHelmet.addEnchantment(Enchantment.DURABILITY, 3);
					LHelmet.addEnchantment(
							Enchantment.PROTECTION_ENVIRONMENTAL, 2);
					IChestplate.addEnchantment(
							Enchantment.PROTECTION_PROJECTILE, 2);
					ILeggings.addEnchantment(
							Enchantment.PROTECTION_ENVIRONMENTAL, 2);
					LBoots.addEnchantment(Enchantment.DURABILITY, 3);
					LBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
							2);
					ISword.addEnchantment(Enchantment.FIRE_ASPECT, 1);
					p.getInventory().clear();
					p.getInventory().addItem(new ItemStack[] { ISword });
					p.getInventory().setHelmet(LHelmet);
					p.getInventory().setChestplate(IChestplate);
					p.getInventory().setLeggings(ILeggings);
					p.getInventory().setBoots(LBoots);
					
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