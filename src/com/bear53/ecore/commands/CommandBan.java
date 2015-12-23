package com.bear53.ecore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandBan implements CommandExecutor {

	Core plugin;

	public CommandBan(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender.isOp()) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED
						+ "Invalid arguments! Usage: /ban <player> [reason]");
			} else if (args.length == 1) {
				Player p = Bukkit.getServer().getPlayer(args[0]);
				if (p == null) {
					sender.sendMessage(ChatColor.RED + "Error: The player '"
							+ args[0] + "' cannot be found.");
				} else if (p.isOp()) {
					sender.sendMessage(ChatColor.RED
							+ "You cannot ban that player.");
				} else {
					p.kickPlayer(ChatColor.RED
							+ "You have been banned from the Flame Network!\n\n"
							+ ChatColor.GRAY + "Breaking a rule\n\n"
							+ ChatColor.RED + "Banned by: " + ChatColor.GRAY
							+ sender.getName());

					this.plugin.getConfig().set(
							"players." + p.getUniqueId() + ".isBanned",
							Boolean.valueOf(true));
					this.plugin.getConfig().set(
							"players." + p.getUniqueId() + ".banReason",
							"Breaking a rule");
					this.plugin.getConfig().set(
							"players." + p.getUniqueId() + ".bannedBy",
							sender.getName());

					this.plugin.saveConfig();
					for (Player ops : Bukkit.getServer().getOnlinePlayers()) {
						if (ops.isOp()) {
							ops.sendMessage(Core.prefix + ChatColor.GRAY
									+ "Player " + ChatColor.RED + p.getName()
									+ ChatColor.GRAY + " has been banned by "
									+ ChatColor.RED + sender.getName()
									+ ChatColor.GRAY + " for breaking a rule.");
						}
					}
				}
			} else {
				Player p = Bukkit.getServer().getPlayer(args[0]);
				if (p == null) {
					sender.sendMessage(ChatColor.RED + "Error: The player '"
							+ args[0] + "' cannot be found.");
				} else if (p.isOp()) {
					sender.sendMessage(ChatColor.RED
							+ "You cannot ban that player.");
				} else {
					StringBuilder str = new StringBuilder();
					for (int i = 1; i < args.length; i++) {
						str.append(args[i] + " ");
					}
					String msg = str.toString().trim();

					this.plugin.getConfig().set(
							"players." + p.getUniqueId() + ".isBanned",
							Boolean.valueOf(true));
					this.plugin.getConfig().set(
							"players." + p.getUniqueId() + ".banReason", msg);
					this.plugin.getConfig().set(
							"players." + p.getUniqueId() + ".bannedBy",
							sender.getName());

					this.plugin.saveConfig();

					p.kickPlayer(ChatColor.RED
							+ "You have been banned from the Flame Network!\n\n"
							+ ChatColor.GRAY + msg + "\n\n" + ChatColor.RED
							+ "Banned by: " + ChatColor.GRAY + sender.getName());
					for (Player ops : Bukkit.getServer().getOnlinePlayers()) {
						if (ops.isOp()) {
							ops.sendMessage(Core.prefix + "Player "
									+ ChatColor.RED + p.getName()
									+ ChatColor.GRAY + "has been banned by "
									+ ChatColor.RED + sender.getName()
									+ ChatColor.GRAY + " for " + msg + ".");
						}
					}
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "No permission.");
		}
		return true;
	}
}
