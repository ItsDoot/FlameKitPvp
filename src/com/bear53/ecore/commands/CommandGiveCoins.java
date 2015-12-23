package com.bear53.ecore.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandGiveCoins implements CommandExecutor {

	Core plugin;

	public CommandGiveCoins(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("givecoins")) {
			List<String> owners = plugin.getConfig().getStringList("owners");
			List<String> admins = plugin.getConfig().getStringList("admins");
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (owners.contains(player.getName())
						|| (admins.contains(player.getName()))) {
					if (args.length != 2) {
						player.sendMessage(ChatColor.RED
								+ "Error: Usage: /givecoins <player> <coins>");
					} else {
						Player p = Bukkit.getServer().getPlayer(args[0]);
						if (p == null) {
							sender.sendMessage(ChatColor.RED
									+ "Error: The player '" + args[0]
									+ "' cannot be found.");
						}
						int coins = Integer.valueOf(args[1]);
						Integer previous = Integer.valueOf(plugin.getConfig()
								.getInt("players." + p.getUniqueId().toString()
										+ ".coins"));
						plugin.getConfig()
								.set("players." + p.getUniqueId().toString()
										+ ".coins", previous.intValue() + coins);
						sender.sendMessage(ChatColor.YELLOW
								+ p.getName()
								+ "'s "
								+ ChatColor.GREEN
								+ "coins are now "
								+ ChatColor.GOLD
								+ plugin.getConfig().getInt(
										"players." + p.getUniqueId().toString()
												+ ".coins"));
						p.sendMessage(ChatColor.GREEN
								+ "Your coins are now "
								+ ChatColor.GOLD
								+ plugin.getConfig().getInt(
										"players." + p.getUniqueId().toString()
												+ ".coins"));
						Bukkit.dispatchCommand(sender, "updatescore");
						plugin.saveConfig();
					}
				} else {
					sender.sendMessage(ChatColor.RED + "No Permission!");
				}
			} else {
				if (args.length != 2) {
					sender.sendMessage(ChatColor.RED
							+ "Error: Usage: /givecoins <player> <coins>");
				} else {
					Player p = Bukkit.getServer().getPlayer(args[0]);
					if (p == null) {
						sender.sendMessage(ChatColor.RED
								+ "Error: The player '" + args[0]
								+ "' cannot be found.");
					}
					int coins = Integer.valueOf(args[1]);
					Integer previous = Integer.valueOf(plugin.getConfig()
							.getInt("players." + p.getUniqueId().toString()
									+ ".coins"));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString() + ".coins",
							previous.intValue() + coins);
					sender.sendMessage(ChatColor.YELLOW
							+ p.getName()
							+ "'s "
							+ ChatColor.GREEN
							+ "coins are now "
							+ ChatColor.GOLD
							+ plugin.getConfig().getInt(
									"players." + p.getUniqueId().toString()
											+ ".coins"));
					p.sendMessage(ChatColor.GREEN
							+ "Your coins are now "
							+ ChatColor.GOLD
							+ plugin.getConfig().getInt(
									"players." + p.getUniqueId().toString()
											+ ".coins"));
					Bukkit.dispatchCommand(sender, "updatescore");
					plugin.saveConfig();
				}
			}
		}
		return true;
	}
}
