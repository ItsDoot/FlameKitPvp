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

public class GhostClass implements CommandExecutor {

	Core plugin;

	public GhostClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("ghost")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin.getConfig().getInt(
						"players." + p.getUniqueId().toString() + ".1v1wins") >= 20) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN
							+ "You have activated the Ghost Kit!");

					ItemStack Sword = new ItemStack(Material.IRON_SWORD);
					ItemStack spade = new ItemStack(Material.FEATHER);
					ItemMeta sm = spade.getItemMeta();
					sm.setDisplayName(ChatColor.AQUA + "Ghost Ability");
					p.getInventory().clear();
					KitPvp.clearEffects(p);
					p.getInventory().addItem(new ItemStack[] { Sword });
					p.getInventory().addItem(new ItemStack[] { spade });
					p.getInventory().setHelmet(null);
					p.getInventory().setChestplate(null);
					p.getInventory().setLeggings(null);
					p.getInventory().setBoots(null);
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
					p.addPotionEffect(new PotionEffect(
							PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
					p.addPotionEffect(new PotionEffect(
							PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
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
