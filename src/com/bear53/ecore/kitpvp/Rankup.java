package com.bear53.ecore.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.bear53.ecore.Core;

public class Rankup implements Listener {

	Core plugin;

	public Rankup(Core pl) {
		this.plugin = pl;
	}

	@EventHandler
	public void onRankUp(PlayerDeathEvent e) {
		Player dead = e.getEntity();
		Player killer = dead.getKiller();

		if (killer == null) {
			return;
		}

		if (killer == dead) {
			return;
		}

		int level = plugin.getConfig().getInt(
				"players." + killer.getUniqueId().toString() + ".level");
		int kills = plugin.getConfig().getInt(
				"players." + killer.getUniqueId().toString() + ".kills");
		if (level == 1 && kills == 5) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 2 && kills == 10) {
            level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 3 && kills == 15) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 4 && kills == 20) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 5 && kills == 25) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 6 && kills == 30) {
			plugin.getLogger().info("Player level up to level 7");
			level = level + 1;
			System.out.println("Saved Data");
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
				System.out.println("broadcasted!");
		} else if (level == 7 && kills == 35) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 8 && kills == 40) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 9 && kills == 45) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 10 && kills == 50) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 11 && kills == 55) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 12 && kills == 60) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 13 && kills == 65) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 14 && kills == 70) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 15 && kills == 75) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 16 && kills == 80) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 17 && kills == 85) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 18 && kills == 90) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
		} else if (level == 19 && kills == 95) {
			level = level + 1;
			Bukkit.getServer().broadcastMessage(
					ChatColor.BLUE
					+ killer.getName()
					+ ChatColor.DARK_AQUA
					+ " just ranked up to level "
					+ ChatColor.GREEN
					+ level
					+ ChatColor.DARK_AQUA + "!");
			killer.sendMessage(ChatColor.GREEN
					+ "Congrats! You have reached the "
					+ ChatColor.DARK_AQUA + "max " + ChatColor.GREEN
					+ "level!");
			killer.sendMessage(ChatColor.GREEN
					+ "You can use /prestige to reset your K/D ratio and unlock a special kit!");
			plugin.getConfig().set(
					"players." + killer.getUniqueId().toString()
							+ ".prestigeMode", Boolean.valueOf(true));
		}
        
        plugin.getConfig().set("players." + killer.getUniqueId().toString() + ".level", level);
        plugin.saveConfig();
	}
}
