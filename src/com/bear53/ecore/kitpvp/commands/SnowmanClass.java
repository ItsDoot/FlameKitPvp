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

public class SnowmanClass implements CommandExecutor {

	Core plugin;

	public SnowmanClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("snowman")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin.getConfig().getInt(
						"players." + p.getUniqueId().toString() + ".1v1wins") >= 30) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN
							+ "You have activated the Snowman Kit!");

					ItemStack Sword = new ItemStack(Material.STONE_SWORD);
					ItemStack h = new ItemStack(Material.CHAINMAIL_HELMET);
					ItemStack c = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
					ItemStack l = new ItemStack(Material.CHAINMAIL_LEGGINGS);
					ItemStack b = new ItemStack(Material.CHAINMAIL_BOOTS);
					ItemStack spade = new ItemStack(Material.IRON_SPADE);
					ItemMeta sm = spade.getItemMeta();
					sm.setDisplayName(ChatColor.AQUA + "Snowball Launcher");
					spade.setItemMeta(sm);
					Sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
					h.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
					c.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
					l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
					b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
					p.getInventory().clear();
					KitPvp.clearEffects(p);
					p.getInventory().addItem(new ItemStack[] { Sword });
					p.getInventory().addItem(new ItemStack[] { spade });
					p.getInventory().setHelmet(h);
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