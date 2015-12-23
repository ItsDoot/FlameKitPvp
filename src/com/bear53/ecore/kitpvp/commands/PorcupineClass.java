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

public class PorcupineClass implements CommandExecutor {

	Core plugin;

	public PorcupineClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("Porcupine")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin
						.getConfig()
						.getStringList(
								"players." + p.getUniqueId().toString()
										+ ".kits").contains("PorcupineClass")) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN
							+ "You have activated the PorcupineClass Kit!");
					ItemStack Sword = new ItemStack(Material.STONE_SWORD);
					ItemStack Chest = new ItemStack(Material.IRON_CHESTPLATE);
					ItemStack Helm = new ItemStack(Material.IRON_HELMET);
					ItemStack Legs = new ItemStack(Material.IRON_LEGGINGS);
					ItemStack Boot = new ItemStack(Material.IRON_BOOTS);
					Sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
					Legs.addEnchantment(Enchantment.THORNS, 1);
					Chest.addEnchantment(Enchantment.THORNS, 1);
					Boot.addEnchantment(Enchantment.THORNS, 1);
					Helm.addEnchantment(Enchantment.THORNS, 1);
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
