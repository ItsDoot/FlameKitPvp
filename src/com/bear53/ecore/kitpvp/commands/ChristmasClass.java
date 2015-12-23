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

public class ChristmasClass implements CommandExecutor {

	Core plugin;

	public ChristmasClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("Christmas")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin.getConfig().getBoolean("isChristmas")) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN + "You have activated the "
							+ ChatColor.DARK_RED + "S" + ChatColor.DARK_GREEN
							+ "a" + ChatColor.DARK_RED + "n"
							+ ChatColor.DARK_GREEN + "t" + ChatColor.DARK_RED
							+ "a " + ChatColor.GREEN + "Kit!");

					ItemStack hat = new ItemStack(Material.WOOL, 1, (short) 14);
					ItemStack c = new ItemStack(Material.IRON_CHESTPLATE);
					ItemStack l = new ItemStack(Material.IRON_LEGGINGS);
					ItemStack b = new ItemStack(Material.IRON_BOOTS);
					ItemStack s = new ItemStack(Material.GOLD_SWORD);
					s.addEnchantment(Enchantment.FIRE_ASPECT, 2);
					s.addEnchantment(Enchantment.KNOCKBACK, 1);

					p.getInventory().clear();
					KitPvp.clearEffects(p);
					p.getInventory().addItem(new ItemStack[] { s });
					p.getInventory().setHelmet(hat);
					p.getInventory().setChestplate(c);
					p.getInventory().setLeggings(l);
					p.getInventory().setBoots(b);
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