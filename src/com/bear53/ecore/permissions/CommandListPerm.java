package com.bear53.ecore.permissions;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;
import com.bear53.ecore.kitpvp.KitPvp;

public class CommandListPerm implements CommandExecutor {

	Core plugin;

	public CommandListPerm(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if ((sender.isOp()) || (!(sender instanceof Player))) {
			if (args.length != 1) {
				sender.sendMessage(ChatColor.RED
						+ "Invalid arguments! Usage: /listperm <player>");
			} else {
				Player p = Bukkit.getServer().getPlayer(args[0]);
				if (p == null) {
					sender.sendMessage(ChatColor.RED + "Error: The player '"
							+ args[0] + "' cannot be found.");
				} else {
					List<String> show = PermissionSettings.getInstance()
							.getPerms(p);
					sender.sendMessage(ChatColor.BLUE + p.getName()
							+ "'s Permissions");
					sender.sendMessage(ChatColor.GRAY + ""
							+ ChatColor.STRIKETHROUGH
							+ "-----------------------");
					sender.sendMessage(ChatColor.GREEN + "" + show);
					sender.sendMessage(ChatColor.GRAY + ""
							+ ChatColor.STRIKETHROUGH
							+ "-----------------------");
				}
			}
		} else {
			sender.sendMessage(KitPvp.noPerm);
		}
		return true;
	}
}
