package com.bear53.ecore.kitpvp.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.bear53.ecore.Core;
import com.bear53.ecore.kitpvp.KitPvp;

public class StrikerClass implements CommandExecutor {

	Core plugin;

	public StrikerClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("Striker")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin
						.getConfig()
						.getStringList(
								"players." + p.getUniqueId().toString()
										+ ".kits").contains("Striker")) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN
							+ "You have activated the Striker Kit!");
					ItemStack Sword = new ItemStack(Material.DIAMOND_SWORD);
					ItemStack hat = new ItemStack(Material.LEATHER_HELMET);
					ItemStack Chest = new ItemStack(Material.LEATHER_CHESTPLATE);
					ItemStack Legs = new ItemStack(Material.LEATHER_LEGGINGS);
					ItemStack Boot = new ItemStack(Material.LEATHER_BOOTS);
					Sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
					hat.addEnchantment(Enchantment.DURABILITY, 3);
					Chest.addEnchantment(Enchantment.DURABILITY, 3);
					Legs.addEnchantment(Enchantment.DURABILITY, 3);
					Boot.addEnchantment(Enchantment.DURABILITY, 3);
					Chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
							2);
					Chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
							2);
					Legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
					Boot.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
					p.getInventory().clear();
					p.getInventory().addItem(new ItemStack[] { Sword });
					p.getInventory().setHelmet(hat);
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
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
							Integer.MAX_VALUE, 2));
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
