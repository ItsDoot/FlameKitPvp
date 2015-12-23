package com.bear53.ecore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandUnBan implements CommandExecutor {
	Core plugin;

	public CommandUnBan(Core pl) {
		plugin = pl;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender.isOp()) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED
						+ "Invalid arguments! Usage: /unban <player>");
			} else if (args.length == 1) {
				String unbanned = args[0];
				String p = Bukkit.getServer().getOfflinePlayer(unbanned)
						.getUniqueId().toString();
				if (plugin.getConfig().getString("players." + p + ".isBanned") != null) {
					if (plugin.getConfig().getBoolean(
							"players." + p + "isBanned")) {
						plugin.getConfig().set("players." + p + ".isBanned",
								Boolean.valueOf(false));
						plugin.saveConfig();
						for (Player ops : Bukkit.getServer().getOnlinePlayers()) {
							if (ops.isOp()) {
								ops.sendMessage(Core.prefix + "Player "
										+ ChatColor.RED + args[0]
										+ ChatColor.GRAY
										+ "has been unbanned by "
										+ ChatColor.RED + sender.getName()
										+ ChatColor.GRAY + ".");
							}
						}
					} else {
						sender.sendMessage(ChatColor.RED + "The player "
								+ unbanned + " cannot be unbanned");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "The player " + unbanned
							+ " cannot be unbanned");
				}
			} else {
				sender.sendMessage(ChatColor.RED
						+ "Invalid arguments! Usage: /unban <player>");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "No permission.");
		}
		return true;
	}
}
