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

public class ArcherClass implements CommandExecutor {

	Core plugin;

	public ArcherClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("Archer")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin
						.getConfig()
						.getStringList(
								"players." + p.getUniqueId().toString()
										+ ".kits").contains("Archer")) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN
							+ "You have activated the Archer Kit!");

					ItemStack Sword = new ItemStack(Material.STONE_SWORD);
					ItemStack LHelmet = new ItemStack(Material.CHAINMAIL_HELMET);
					ItemStack LChestplate = new ItemStack(
							Material.CHAINMAIL_CHESTPLATE);
					ItemStack LLeggings = new ItemStack(
							Material.CHAINMAIL_LEGGINGS);
					ItemStack LBoots = new ItemStack(Material.CHAINMAIL_BOOTS);
					ItemStack Bow = new ItemStack(Material.BOW);
					Sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
					Sword.addUnsafeEnchantment(Enchantment.DURABILITY, 100);
					LHelmet.addEnchantment(
							Enchantment.PROTECTION_ENVIRONMENTAL, 1);
					LChestplate.addEnchantment(
							Enchantment.PROTECTION_ENVIRONMENTAL, 1);
					LLeggings.addEnchantment(
							Enchantment.PROTECTION_ENVIRONMENTAL, 1);
					LBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
							1);
					Bow.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
					Bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
					p.getInventory().clear();
					KitPvp.clearEffects(p);
					ItemStack itemshop = new ItemStack(Material.BLAZE_POWDER);
					ItemMeta shopmeta = itemshop.getItemMeta();
					shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
					ArrayList<String> shoplore = new ArrayList<String>();
					shoplore.add(ChatColor.RED
							+ "Be sure to have space in your inventory!");
					shopmeta.setLore(shoplore);
					itemshop.setItemMeta(shopmeta);
					p.getInventory().setItem(8, itemshop);
					p.getInventory().addItem(new ItemStack[] { Sword });
					p.getInventory().addItem(new ItemStack[] { Bow });
					p.getInventory().setHelmet(LHelmet);
					p.getInventory().setChestplate(LChestplate);
					p.getInventory().setLeggings(LLeggings);
					p.getInventory().setBoots(LBoots);
					p.getInventory().addItem(
							new ItemStack[] { new ItemStack(Material.ARROW) });
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
