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

public class ThanksgivingClass implements CommandExecutor {

	Core plugin;

	public ThanksgivingClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("thanksgiving")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin.getConfig().getBoolean("isThanksgiving")) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN + "You have activated the "
							+ ChatColor.YELLOW + "Thanksgiving "
							+ ChatColor.GREEN + "Kit!");

					ItemStack hat = new ItemStack(Material.LEATHER_HELMET);
					ItemStack c = new ItemStack(Material.LEATHER_CHESTPLATE);
					ItemStack l = new ItemStack(Material.LEATHER_LEGGINGS);
					ItemStack b = new ItemStack(Material.LEATHER_BOOTS);
					ItemStack s = new ItemStack(Material.PUMPKIN_PIE);
					hat.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
					c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
					l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
					b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
					s.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
					s.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);

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
