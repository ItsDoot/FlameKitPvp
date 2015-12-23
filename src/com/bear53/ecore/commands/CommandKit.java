package com.bear53.ecore.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandKit implements CommandExecutor {

	Core plugin;

	public CommandKit(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player p = (Player) sender;
		List<String> playerKits = plugin.getConfig().getStringList(
				"players." + p.getUniqueId() + ".kits");
		p.sendMessage(Core.prefix + ChatColor.YELLOW + "Availiable kits: "
				+ playerKits);
		return true;
	}
}
