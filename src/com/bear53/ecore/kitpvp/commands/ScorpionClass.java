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

import com.bear53.ecore.Core;
import com.bear53.ecore.kitpvp.KitPvp;

public class ScorpionClass implements CommandExecutor {

	Core plugin;

	public ScorpionClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("Scorpion")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin
						.getConfig()
						.getStringList(
								"players." + p.getUniqueId().toString()
										+ ".kits").contains("Scorpion")) {
					KitPvp.activeKit.add(p.getName());
					KitPvp.scorp.add(p.getName());
					p.sendMessage(ChatColor.GREEN
							+ "You have activated the Scorpion Kit!");
					ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
					ItemStack chestplate = new ItemStack(
							Material.CHAINMAIL_CHESTPLATE);
					ItemStack leggins = new ItemStack(
							Material.CHAINMAIL_LEGGINGS);
					ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
					ItemStack sword = new ItemStack(Material.STONE_SWORD);
					p.getInventory().clear();
					p.getInventory().setChestplate(chestplate);
					p.getInventory().setLeggings(leggins);
					p.getInventory().setHelmet(helmet);
					p.getInventory().setBoots(boots);
					ItemStack itemshop = new ItemStack(Material.BLAZE_POWDER);
					ItemMeta shopmeta = itemshop.getItemMeta();
					shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
					ArrayList<String> shoplore = new ArrayList<String>();
					shoplore.add(ChatColor.RED
							+ "Be sure to have space in your inventory!");
					shopmeta.setLore(shoplore);
					itemshop.setItemMeta(shopmeta);
					p.getInventory().setItem(8, itemshop);
					p.getInventory().addItem(new ItemStack[] { sword });
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
