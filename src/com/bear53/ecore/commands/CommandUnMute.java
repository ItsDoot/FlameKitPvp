package com.bear53.ecore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandUnMute implements CommandExecutor {

	Core plugin;

	public CommandUnMute(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender.isOp()) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED
						+ "Invalid arguments! Usage: /unmute <player>");
			} else if (args.length == 1) {
				Player p = Bukkit.getServer().getPlayer(args[0]);
				if (p == null) {
					sender.sendMessage(ChatColor.RED + "Error: The player '"
							+ args[0] + "' cannot be found.");
				} else if (p.isOp()) {
					sender.sendMessage(ChatColor.RED
							+ "You cannot mute that player.");
				} else {
					this.plugin.getConfig().set(
							"players." + p.getUniqueId() + ".isMuted",
							Boolean.valueOf(false));

					p.sendMessage(Core.prefix + ChatColor.GRAY
							+ "You have been unmuted by " + ChatColor.RED
							+ sender.getName() + ChatColor.GRAY + ".");
					for (Player ops : Bukkit.getServer().getOnlinePlayers()) {
						if (ops.isOp()) {
							ops.sendMessage(Core.prefix + ChatColor.GRAY
									+ "Player " + ChatColor.RED + p.getName()
									+ ChatColor.GRAY + " has been unmuted by "
									+ ChatColor.RED + sender.getName()
									+ ChatColor.GRAY + ".");
						}
					}
					this.plugin.saveConfig();
				}
			} else {
				sender.sendMessage(ChatColor.RED
						+ "Invalid arguments! Usage: /unmute <player>");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "No permission.");
		}
		return true;
	}
}
