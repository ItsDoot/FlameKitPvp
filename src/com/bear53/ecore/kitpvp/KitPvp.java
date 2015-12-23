package com.bear53.ecore.kitpvp;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import com.bear53.ecore.Core;

public class KitPvp {

	Core plugin;

	public KitPvp(Core pl) {
		this.plugin = pl;
	}

	public static String noPerm = ChatColor.DARK_GRAY + "["
			+ ChatColor.DARK_RED + "Flame" + ChatColor.DARK_GRAY + "] "
			+ ChatColor.RED + "You don't have access to this kit!";
	public static String activekit = ChatColor.DARK_GRAY + "["
			+ ChatColor.DARK_RED + "Flame" + ChatColor.DARK_GRAY + "] "
			+ "You already activated a kit!";

	public static ArrayList<String> activeKit = new ArrayList<String>();
	public static ArrayList<String> scorp = new ArrayList<String>();
	public static ArrayList<String> hulk = new ArrayList<String>();

	public static void giveSoup(Player p) {
		for (int i = 0; i < 36; i++) {
			p.getInventory().addItem(
					new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
		}
	}

	public static void giveHeath(Player p) {
		for (int i = 0; i < 36; i++) {
			Potion is = new Potion(PotionType.INSTANT_HEAL);
			is.setSplash(true);
			is.setLevel(2);
			ItemStack potion = is.toItemStack(1);
			p.getInventory().addItem(new ItemStack[] { potion });
		}
	}

	public static void kitMsg(String kit, Player p) {
		p.sendMessage(ChatColor.GREEN + "You have activated the " + kit
				+ " Kit!");
	}

	public static void clearEffects(Player p) {
		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
	}
}
