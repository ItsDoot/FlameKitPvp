package com.bear53.ecore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Ping;

public class CommandPing implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ping")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Only players can use this command");
			}
			if (args.length == 0) {
				Player p = (Player) sender;
				p.sendMessage(ChatColor.GOLD + "Ping of " + p.getName()
						+ ChatColor.YELLOW + ": " + ChatColor.BLUE
						+ Ping.getPing(p));
			} else if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.RED + "Error: Player "
							+ target + " could not be found!");
				} else {
					sender.sendMessage(ChatColor.GOLD + "Ping of "
							+ target.getName() + ChatColor.YELLOW + ": "
							+ ChatColor.BLUE + Ping.getPing(target));
				}
			}
		}
		return true;
	}
}
