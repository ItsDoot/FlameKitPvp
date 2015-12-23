package com.bear53.ecore.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandAddKit implements CommandExecutor {

	Core plugin;

	public CommandAddKit(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if ((sender.isOp()) || (!(sender instanceof Player))) {
			if (args.length != 2) {
				sender.sendMessage(ChatColor.RED
						+ "Invalid arguments! Usage: /addkit <player> <kit>");
			} else {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				String kit = args[1];
				if (target == null) {
					sender.sendMessage(ChatColor.RED + "Error: The player '"
							+ args[0] + "' cannot be found.");
				} else {
					List<String> playerKits = plugin.getConfig().getStringList(
							"players." + target.getUniqueId() + ".kits");
					playerKits.add(kit);
					plugin.getConfig().set(
							"players." + target.getUniqueId().toString()
									+ ".kits", null);
					plugin.getConfig().set(
							"players." + target.getUniqueId() + ".kits",
							playerKits);
					plugin.saveConfig();
					target.sendMessage(Core.prefix + ChatColor.GREEN
							+ "Your kits have been updated!");
					List<String> updatedKits = plugin
							.getConfig()
							.getStringList(
									"players." + target.getUniqueId() + ".kits");
					target.sendMessage(Core.prefix + ChatColor.YELLOW
							+ "Availiable kits: " + updatedKits);
				}
			}
		}
		return true;
	}
}
