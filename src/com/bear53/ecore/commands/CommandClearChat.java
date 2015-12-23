package com.bear53.ecore.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.bear53.ecore.Core;

public class CommandClearChat implements CommandExecutor {

	Core plugin;

	public CommandClearChat(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clearchat")) {
			List<String> moderators = plugin.getConfig().getStringList(
					"moderators");
			List<String> owners = plugin.getConfig().getStringList("owners");
			List<String> admins = plugin.getConfig().getStringList("admins");
			if (moderators.contains(sender.getName())
					|| (owners.contains(sender.getName()) || (admins
							.contains(sender.getName())))) {
				if (args.length == 0) {
					for (int i = 0; i < 100; i++) {
						Bukkit.broadcastMessage("");
					}
					Bukkit.broadcastMessage(ChatColor
							.translateAlternateColorCodes('&', this.plugin
									.getConfig().getString("chatClearMessage")
									.replaceAll("%player%", sender.getName())));
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("-s")) {
						for (int i = 0; i < 100; i++) {
							Bukkit.broadcastMessage("");
						}
					} else {
						for (int i = 0; i < 100; i++) {
							Bukkit.broadcastMessage("");
						}
						Bukkit.broadcastMessage(ChatColor
								.translateAlternateColorCodes(
										'&',
										this.plugin
												.getConfig()
												.getString("chatClearMessage")
												.replace("%player%",
														sender.getName())));
						Bukkit.broadcastMessage(ChatColor
								.translateAlternateColorCodes(
										'&',
										this.plugin.getConfig()
												.getString("chatClearReason")
												.replace("%reason%", args[0])));
					}
				} else {
					for (int i = 0; i < 100; i++) {
						Bukkit.broadcastMessage("");
					}
					StringBuilder str = new StringBuilder();
					for (int m = 1; m < args.length; m++) {
						str.append(args[m] + " ");
					}
					String message = str.toString();

					Bukkit.broadcastMessage(ChatColor
							.translateAlternateColorCodes('&', this.plugin
									.getConfig().getString("chatClearMessage")
									.replace("%player%", sender.getName())));
					Bukkit.broadcastMessage(ChatColor
							.translateAlternateColorCodes('&', this.plugin
									.getConfig().getString("chatClearReason")
									.replace("%reason%", message)));
				}
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
						this.plugin.getConfig()
								.getString("noPermissionMessage")));
			}
			return true;
		}
		return true;
	}
}
