package com.bear53.ecore.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandBroadcast implements CommandExecutor {

	Core plugin;

	public CommandBroadcast(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("alert")) {
			List<String> owners = plugin.getConfig().getStringList("owners");
			List<String> admins = plugin.getConfig().getStringList("admins");
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (owners.contains(player.getName())
						|| (admins.contains(player.getName()))) {
					if (args.length == 0) {
						player.sendMessage(ChatColor.RED
								+ "Error: Usage: /alert [message]");
						player.sendMessage(ChatColor.BLUE
								+ "You can use this command to alert every user in the server");
					} else if (args.length >= 1) {
						String message = "";
						for (String part : args) {
							if (message != "")
								message += " ";
							message += part;
						}
						Bukkit.getServer().broadcastMessage(
								ChatColor.DARK_GRAY
										+ "["
										+ ChatColor.DARK_RED
										+ "Alert"
										+ ChatColor.DARK_GRAY
										+ "]"
										+ ChatColor.RESET
										+ " "
										+ ChatColor
												.translateAlternateColorCodes(
														'&', message));
					} else {
						sender.sendMessage(ChatColor.RED + "No Permission!");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "No Permission!");
				}
			} else {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED
							+ "Error: Usage: /alert [message]");
					sender.sendMessage(ChatColor.BLUE
							+ "You can use this command to alert every user in the server");
				} else if (args.length >= 1) {
					String message = "";
					for (String part : args) {
						if (message != "")
							message += " ";
						message += part;
					}
					Bukkit.getServer().broadcastMessage(
							ChatColor.DARK_GRAY
									+ "["
									+ ChatColor.DARK_RED
									+ "Alert"
									+ ChatColor.DARK_GRAY
									+ "]"
									+ ChatColor.RESET
									+ " "
									+ ChatColor.translateAlternateColorCodes(
											'&', message));
				}
			}
		}
		return true;
	}
}
