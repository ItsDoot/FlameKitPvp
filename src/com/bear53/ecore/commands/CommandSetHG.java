package com.bear53.ecore.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandSetHG implements CommandExecutor {

	Core plugin;

	public CommandSetHG(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if ((sender instanceof Player)) {
			Player p = (Player) sender;
			if (sender.isOp()) {
				Location loc = p.getLocation();

				this.plugin.getConfig().set("hg.spawn.x",
						Double.valueOf(loc.getX()));
				this.plugin.getConfig().set("hg.spawn.y",
						Double.valueOf(loc.getY()));
				this.plugin.getConfig().set("hg.spawn.z",
						Double.valueOf(loc.getZ()));
				this.plugin.getConfig().set("hg.spawn.pitch",
						Double.valueOf(loc.getPitch()));
				this.plugin.getConfig().set("hg.spawn.yaw",
						Double.valueOf(loc.getYaw()));
				this.plugin.getConfig().set("hg.spawn.world",
						p.getWorld().getName());

				this.plugin.saveConfig();

				p.sendMessage(ChatColor.GREEN
						+ "HG has been set at your location.");
			} else {
				sender.sendMessage(ChatColor.RED + "No permission.");
			}
		}
		return true;
	}
}
