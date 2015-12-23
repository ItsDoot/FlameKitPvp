package com.bear53.ecore.permissions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;
import com.bear53.ecore.kitpvp.KitPvp;

public class CommandAddPerm implements CommandExecutor {

	Core plugin;

	public CommandAddPerm(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if ((sender.isOp()) || (!(sender instanceof Player))) {
			if (args.length != 2) {
				sender.sendMessage(ChatColor.RED
						+ "Invalid arguments! Usage: /addperm <player> <perm>");
			} else {
				Player p = Bukkit.getServer().getPlayer(args[0]);
				if (p == null) {
					sender.sendMessage(ChatColor.RED + "Error: The player '"
							+ args[0] + "' cannot be found.");
				} else {
					String perm = args[1];
					PermissionSettings.getInstance().addPerm(p, perm);
					sender.sendMessage(ChatColor.GREEN
							+ "Successfuly added permission " + perm + " to "
							+ p.getName());
				}
			}
		} else {
			sender.sendMessage(KitPvp.noPerm);
		}
		return true;
	}
}
