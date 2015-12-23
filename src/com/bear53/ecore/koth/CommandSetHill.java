package com.bear53.ecore.koth;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandSetHill implements CommandExecutor {

	Core plugin;

	public CommandSetHill(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sethill")) {
			Player p = (Player) sender;
			List<String> owners = plugin.getConfig().getStringList("owners");
			if (owners.contains(p.getName())) {
				Location loc = p.getLocation();
				plugin.getConfig().set("Hills." + "castle" + ".world",
						String.valueOf(loc.getWorld().getName()));
				plugin.getConfig().set("Hills." + "castle" + ".x",
						Double.valueOf(loc.getX()));
				plugin.getConfig().set("Hills." + "castle" + ".y",
						Double.valueOf(loc.getY()));
				plugin.getConfig().set("Hills." + "castle" + ".z",
						Double.valueOf(loc.getZ()));
				plugin.saveConfig();
				p.sendMessage(ChatColor.GREEN + "Created Hill "
						+ ChatColor.YELLOW + "castle" + ChatColor.GREEN + "!");
			} else {
				p.sendMessage(ChatColor.RED + "Error: No Permission!");
			}
		}
		return true;
	}
}
