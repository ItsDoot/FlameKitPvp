package com.bear53.ecore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandProfileCode implements CommandExecutor {

	Core plugin;

	public CommandProfileCode(Core pl) {
		this.plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("profilecode")) {
			p.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH
					+ "------------------" + ChatColor.RESET + ChatColor.GREEN
					+ " Your Profile Code " + ChatColor.YELLOW + ""
					+ ChatColor.STRIKETHROUGH + "------------------");
			p.sendMessage(ChatColor.GREEN
					+ "Code"
					+ ChatColor.GRAY
					+ ": "
					+ ChatColor.GREEN
					+ plugin.getConfig().getString(
							"players." + p.getUniqueId().toString()
									+ ".profilecode"));
			p.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH
					+ "----------------------------------------------------");
		}
		return true;
	}
}
