package com.bear53.ecore.kitpvp.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.bear53.ecore.Core;
import com.bear53.ecore.kitpvp.KitPvp;

public class HeavyClass implements CommandExecutor {

	Core plugin;

	public HeavyClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("Heavy")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin
						.getConfig()
						.getStringList(
								"players." + p.getUniqueId().toString()
										+ ".kits").contains("Heavy")) {
					KitPvp.activeKit.add(p.getName());
					KitPvp.clearEffects(p);
					p.sendMessage(ChatColor.GREEN
							+ "You have activated the Heavy Kit!");
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,
							Integer.MAX_VALUE, 1));
					p.getInventory().clear();
					p.getInventory().addItem(
							new ItemStack(Material.DIAMOND_SWORD));
					p.getInventory().setHelmet(
							new ItemStack(Material.DIAMOND_HELMET));
					p.getInventory().setChestplate(
							new ItemStack(Material.DIAMOND_CHESTPLATE));
					p.getInventory().setLeggings(
							new ItemStack(Material.DIAMOND_LEGGINGS));
					p.getInventory().setBoots(
							new ItemStack(Material.DIAMOND_BOOTS));

					p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
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
