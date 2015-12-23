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

public class PyroClass implements CommandExecutor {

	Core plugin;

	public PyroClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("PyroClass")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin
						.getConfig()
						.getStringList(
								"players." + p.getUniqueId().toString()
										+ ".kits").contains("PyroClass")) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN
							+ "You have activated the PyroClass Kit!");
					ItemStack Sword = new ItemStack(Material.IRON_SWORD);
					ItemStack Chest = new ItemStack(Material.GOLD_CHESTPLATE);
					ItemStack Helm = new ItemStack(Material.GOLD_HELMET);
					ItemStack Legs = new ItemStack(Material.GOLD_LEGGINGS);
					ItemStack Boot = new ItemStack(Material.GOLD_BOOTS);
					ItemStack fire = new ItemStack(Material.BLAZE_ROD);
					ItemMeta firemeta = fire.getItemMeta();
					firemeta.setDisplayName(ChatColor.RED + "Flame Thrower");
					ArrayList<String> firelore = new ArrayList<String>();
					firelore.add(ChatColor.GREEN + "Right Click to");
					firelore.add(ChatColor.GREEN + "Launch a fireball!");
					fire.setItemMeta(firemeta);
					firemeta.setLore(firelore);
					Sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
					p.getInventory().clear();
					p.getInventory().addItem(new ItemStack[] { Sword });
					p.getInventory().addItem(new ItemStack[] { fire });
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
