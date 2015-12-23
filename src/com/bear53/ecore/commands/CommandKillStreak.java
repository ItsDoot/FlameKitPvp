package com.bear53.ecore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandKillStreak implements CommandExecutor {

	Core plugin;

	public CommandKillStreak(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String cmdLabel, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("killstreak")) {
			if (args.length != 0) {
				sender.sendMessage(ChatColor.RED
						+ "You may not specify any arguments.");
				return true;
			}
			int killstreak = this.plugin.getConfig().getInt(
					"players." + p.getUniqueId().toString() + ".curkillstreak");
			sender.sendMessage("§e" + sender.getName() + "'s §akillstreak: §c"
					+ killstreak);
			return true;
		}
		return true;
	}
}
