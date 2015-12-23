package com.bear53.ecore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandClear implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command");
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("clear")) {
			p.sendMessage(ChatColor.GRAY + "You have cleared your inventory!");
			p.getInventory().clear();
			p.updateInventory();
		}
		return true;
	}
}
