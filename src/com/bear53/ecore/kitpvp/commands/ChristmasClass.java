package com.bear53.ecore.kitpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.bear53.ecore.Core;
import com.bear53.ecore.kitpvp.KitPvp;

public class ChristmasClass extends ClassBase implements CommandExecutor {

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

					KitPvp.clearEffects(p);
					p.getInventory().clear();

					p.getInventory().setItem(0, getPrimaryWeapon());
					p.getInventory().setItem(8, getItemShop());

					p.getInventory().setHelmet(getHelmet());
					p.getInventory().setChestplate(getChestplate());
					p.getInventory().setLeggings(getLeggings());
					p.getInventory().setBoots(getBoots());

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

	@Override
	public ItemStack getPrimaryWeapon() {
		ItemStack s = new ItemStack(Material.GOLD_SWORD);
		s.addEnchantment(Enchantment.FIRE_ASPECT, 2);
		s.addEnchantment(Enchantment.KNOCKBACK, 1);
		return s;
	}

	@Override
	public ItemStack getHelmet() {
		ItemStack hat = new ItemStack(Material.WOOL, 1, (short) 14);
		return hat;
	}

	@Override
	public ItemStack getChestplate() {
		ItemStack c = new ItemStack(Material.IRON_CHESTPLATE);
		return c;
	}

	@Override
	public ItemStack getLeggings() {
		ItemStack l = new ItemStack(Material.IRON_LEGGINGS);
		return l;
	}

	@Override
	public ItemStack getBoots() {
		ItemStack b = new ItemStack(Material.IRON_BOOTS);
		return b;
	}
}