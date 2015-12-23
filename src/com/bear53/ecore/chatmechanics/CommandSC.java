package com.bear53.ecore.chatmechanics;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSC implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player pl = (Player) sender;
		if (pl == null)
			return true;

		if (!pl.hasPermission("evolution.staff")) {
			pl.sendMessage(ChatColor.RED + "You are " + ChatColor.UNDERLINE
					+ "not" + ChatColor.RED
					+ " authorized to use this command.");
			return true;
		}

		if (args.length == 0) {
			if (!ChatMechanics.staff_only.contains(pl.getName())) {
				ChatMechanics.staff_only.add(pl.getName());
				pl.sendMessage(ChatColor.GREEN
						+ "You have toggled staff-only chat.");
			} else {
				ChatMechanics.staff_only.remove(pl.getName());
				pl.sendMessage(ChatColor.GREEN
						+ "You toggled off staff-only chat.");
			}
			return true;
		}

		String msg = "";

		for (String s : args)
			msg += s + " ";
		msg = ChatColor.WHITE + msg;
		if (msg.endsWith(" "))
			msg = msg.substring(0, msg.length() - 1);
		if (pl.hasPermission("evolution.staff")) {
			ChatMechanics.sendStaffMessage(pl, msg);
		} else
			return true;

		ChatMechanics.log.info(ChatColor.stripColor("<" + "Staff" + ">" + " "
				+ pl.getName() + ": " + ChatColor.WHITE + msg));
		return true;
	}
}
