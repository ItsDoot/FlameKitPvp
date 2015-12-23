package com.bear53.ecore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import com.bear53.ecore.Core;

public class CommandReload implements CommandExecutor {

	public Core plugin;

	public CommandReload(Core pl) {
		this.plugin = pl;

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("flamereload")) {
			if (args.length > 0) {
				sender.sendMessage(ChatColor.RED + "Too many arguments!");
				return false;
			}
			PluginDescriptionFile pdffile = plugin.getDescription();
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.isOp()) {
					plugin.reloadConfig();
					sender.sendMessage(Core.prefix + ChatColor.GRAY
							+ " Reloaded files succesfully");
					System.out.println("[FlamePvP] "
							+ (sender.getName() + " reloaded " + (pdffile
									.getFullName())));
				} else {
					sender.sendMessage(ChatColor.RED
							+ "You do not have access to that.");
				}
			} else {
				plugin.reloadConfig();
				System.out.println("[FlamePvP] " + pdffile.getName()
						+ " was succesfully reloaded");
			}
		}
		return true;
	}
}
