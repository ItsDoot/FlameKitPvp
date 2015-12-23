package com.bear53.ecore.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandStaff implements CommandExecutor {

	Core plugin;

	public CommandStaff(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		List<String> moderators = plugin.getConfig()
				.getStringList("moderators");
		List<String> owners = plugin.getConfig().getStringList("owners");
		List<String> admins = plugin.getConfig().getStringList("admins");
		List<String> helpers = plugin.getConfig().getStringList("helpers");
		if (cmd.getName().equalsIgnoreCase("list")) {
			StringBuilder players = new StringBuilder();
			if (Bukkit.getServer().getOnlinePlayers().size() >= 2) {
				for (Player user : Bukkit.getOnlinePlayers()) {
					if ((user != sender)
							&& (moderators.contains(user.getName()) || (admins
									.contains(user.getName()) || (owners
									.contains(user.getName()) || (helpers
									.contains(user.getName())))))) {
						players.append(ChatColor.RED + user.getName()
								+ ChatColor.DARK_RED + ", ");
					}
				}
				if ((moderators.contains(sender.getName()) || (admins
						.contains(sender.getName()) || (owners.contains(sender
						.getName()))))) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', plugin.getConfig().getString("listLineTop")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							plugin.getConfig()
									.getString("playersOnline")
									.replaceAll(
											"%online%",
											""
													+ Bukkit.getServer()
															.getOnlinePlayers()
															.size())
									.replaceAll(
											"%max%",
											""
													+ Bukkit.getServer()
															.getMaxPlayers())));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							plugin.getConfig()
									.getString("staffOnline")
									.replaceAll(
											"%players%",
											players + "" + ChatColor.GRAY
													+ ChatColor.RED
													+ sender.getName())));
					sender.sendMessage(ChatColor
							.translateAlternateColorCodes('&', plugin
									.getConfig().getString("listLineBottom")));
				} else {
					String list = players.substring(0, players.length() - 2);
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', plugin.getConfig().getString("listLineTop")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							plugin.getConfig()
									.getString("playersOnline")
									.replaceAll(
											"%online%",
											""
													+ Bukkit.getServer()
															.getOnlinePlayers()
															.size())
									.replaceAll(
											"%max%",
											""
													+ Bukkit.getServer()
															.getMaxPlayers())));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', plugin.getConfig().getString("staffOnline")
									.replaceAll("%players%", list)));
					sender.sendMessage(ChatColor
							.translateAlternateColorCodes('&', plugin
									.getConfig().getString("listLineBottom")));
				}
			} else if ((sender instanceof Player)) {
				if (sender.hasPermission("list.staff")) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', plugin.getConfig().getString("listLineTop")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							plugin.getConfig()
									.getString("playersOnline")
									.replaceAll(
											"%online%",
											""
													+ Bukkit.getServer()
															.getOnlinePlayers()
															.size())
									.replaceAll(
											"%max%",
											""
													+ Bukkit.getServer()
															.getMaxPlayers())));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							plugin.getConfig()
									.getString("staffOnline")
									.replaceAll("%players%",
											ChatColor.RED + sender.getName())));
					sender.sendMessage(ChatColor
							.translateAlternateColorCodes('&', plugin
									.getConfig().getString("listLineBottom")));
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', plugin.getConfig().getString("listLineTop")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							plugin.getConfig()
									.getString("playersOnline")
									.replaceAll(
											"%online%",
											""
													+ Bukkit.getServer()
															.getOnlinePlayers()
															.size())
									.replaceAll(
											"%max%",
											""
													+ Bukkit.getServer()
															.getMaxPlayers())));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							plugin.getConfig()
									.getString("noStaffOnlineMessage")));
					sender.sendMessage(ChatColor
							.translateAlternateColorCodes('&', plugin
									.getConfig().getString("listLineBottom")));
				}
			}
			return true;
		}
		return false;
	}
}
