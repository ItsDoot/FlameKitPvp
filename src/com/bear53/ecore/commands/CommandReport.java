package com.bear53.ecore.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandReport implements CommandExecutor {

	Core plugin;

	public CommandReport(Core pl) {
		this.plugin = pl;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		List<String> moderators = plugin.getConfig()
				.getStringList("moderators");
		List<String> owners = plugin.getConfig().getStringList("owners");
		List<String> admins = plugin.getConfig().getStringList("admins");
		List<String> report = plugin.getConfig().getStringList("reports.");
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Too few arguments! Usage: /report <player> <reason>");
			return true;
		}
		if (args.length == 1) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Too few arguments! Usage: /report <player> <reason>");
			return true;
		}
		if (args.length >= 2) {
			String targetName = args[0];
			Player player = (Player) sender;
			Player target = Bukkit.getPlayer(targetName);
			if (target == null) {
				player.sendMessage(ChatColor.RED + "Error: The player "
						+ ChatColor.GRAY + targetName + ChatColor.RED
						+ " cannot be found!");
				return true;
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				sb.append(args[i]);
				if (i < args.length) {
					sb.append(" ");
				}
			}
			player.sendMessage(ChatColor.GREEN + "Thank you." + ChatColor.GOLD
					+ " The issue will be delt with shortly.");
			for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
				if (owners.contains(staff.getName())
						|| (moderators.contains(staff.getName()) || (admins
								.contains(staff.getName())))) {
					staff.sendMessage(ChatColor.DARK_GRAY + "["
							+ ChatColor.DARK_RED + "Staff Alert"
							+ ChatColor.DARK_GRAY + "] " + ChatColor.RED
							+ "Player " + ChatColor.GRAY + targetName
							+ ChatColor.RED + " has been reported for "
							+ ChatColor.GRAY + sb.toString() + ChatColor.RED
							+ " By " + ChatColor.GRAY + player.getName());
				} else {

				}
			}
			return true;

		}
		return true;
	}
}
