package com.bear53.ecore.kitpvp;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandPrestige implements CommandExecutor {

	Core plugin;

	public CommandPrestige(Core pl) {
		this.plugin = pl;
	}

	public boolean prestigeMode = false;

	public int task1;

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Error: You must be a player to use this command");
		}
		final Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("prestige")) {
			if (args.length == 1) {
				if (!prestigeMode) {
					p.sendMessage(ChatColor.RED
							+ "You must be level 20 to use this command!");
				} else if (prestigeMode && args[0].equalsIgnoreCase("confirm")) {
					Bukkit.getScheduler().cancelTask(task1);
					prestigeMode = false;
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString() + ".coins",
							Integer.valueOf(100));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString() + ".kills",
							Integer.valueOf(0));
					plugin.getConfig()
							.set("players." + p.getUniqueId().toString()
									+ ".deaths", Integer.valueOf(0));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString()
									+ ".killstreak", Integer.valueOf(0));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString()
									+ ".curkillstreak", Integer.valueOf(0));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString()
									+ ".1v1wins", Integer.valueOf(0));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString()
									+ ".1v1loses", Integer.valueOf(0));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString() + ".level",
							Integer.valueOf(1));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString()
									+ ".prestigeMode", Boolean.valueOf(false));
					if (!plugin.getConfig().contains(
							"players." + p.getUniqueId().toString()
									+ ".prestige")) {
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".prestige", Integer.valueOf(1));
					} else {
						int n = plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".prestige");
						if (n == 9) {
							plugin.getConfig().set(
									"players." + p.getUniqueId().toString()
											+ ".prestige", Integer.valueOf(10));
						}
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".prestige", Integer.valueOf(n + 1));
					}
					plugin.saveConfig();

					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "update");

					p.sendMessage(ChatColor.GREEN
							+ "You have successfuly prestiged! Enjoy your rewards!");
					p.sendMessage(ChatColor.GREEN + "You have unlocked the "
							+ ChatColor.GOLD + "" + ChatColor.BOLD
							+ "Prestige " + ChatColor.GREEN + "Kit!");
					Bukkit.broadcastMessage(ChatColor.BLUE + p.getName()
							+ ChatColor.GOLD + " Has just " + ChatColor.GREEN
							+ "" + ChatColor.BOLD + "Prestiged!");
					prestigeRewards(p);
				}
			} else if (args.length == 0) {
				if (!plugin.getConfig().contains(
						"players." + p.getUniqueId().toString()
								+ ".prestigeMode")) {
					p.sendMessage(ChatColor.RED
							+ "You must be level 20 to use this command!");
				}
				if (plugin.getConfig().getBoolean(
						"players." + p.getUniqueId().toString()
								+ ".prestigeMode")) {
					p.sendMessage(ChatColor.RED
							+ "Are you sure you want to continue?");
					p.sendMessage(ChatColor.RED
							+ "This will reset ALL your stats!");
					p.sendMessage(ChatColor.GOLD
							+ "Type /prestige confirm if you wish to continue");
					p.sendMessage(ChatColor.RED
							+ "This will time out in 10 seconds!");
					prestigeMode = true;
					task1 = Bukkit.getScheduler().scheduleSyncDelayedTask(
							plugin, new Runnable() {
								public void run() {
									prestigeMode = false;
									p.sendMessage(ChatColor.RED
											+ "Prestige request has timed out!");
								}
							}, 200);
				} else {

				}
			}
		}
		return true;
	}

	public void prestigeRewards(Player p) {
		List<String> playerKits = plugin.getConfig().getStringList(
				"players." + p.getUniqueId() + ".kits");
		if (!playerKits.contains("Prestige")) {
			playerKits.add("Prestige");
			plugin.getConfig().set("players." + p.getUniqueId() + ".kits",
					playerKits);
			int coins = plugin.getConfig().getInt(
					"players." + p.getUniqueId().toString() + ".coins");
			plugin.getConfig().set(
					"players." + p.getUniqueId().toString() + ".coins",
					Integer.valueOf(coins + 1000));
			plugin.saveConfig();
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "update");
			p.sendMessage("§a§l+1000" + ChatColor.GOLD + " Coins");
		} else {
			int coins = plugin.getConfig().getInt(
					"players." + p.getUniqueId().toString() + ".coins");
			plugin.getConfig().set(
					"players." + p.getUniqueId().toString() + ".coins",
					Integer.valueOf(coins + 2000));
			plugin.saveConfig();
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "update");
			p.sendMessage("§a§l+2000" + ChatColor.GOLD + " Coins");
		}
	}
}
