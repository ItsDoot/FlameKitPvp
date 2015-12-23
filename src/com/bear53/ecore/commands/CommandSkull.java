package com.bear53.ecore.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.bear53.ecore.Core;

public class CommandSkull implements CommandExecutor, Listener {

	Core plugin;

	public CommandSkull(Core pl) {
		this.plugin = pl;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if ((sender instanceof Player)) {
			if (cmd.getName().equalsIgnoreCase("skull")) {
				List<String> owners = plugin.getConfig()
						.getStringList("owners");
				if (owners.contains(sender.getName())) {
					if (args.length == 0) {
						Player p = (Player) sender;
						ItemStack skull = new ItemStack(397, 1, (short) 3);
						SkullMeta meta3 = (SkullMeta) skull.getItemMeta();
						meta3.setDisplayName(ChatColor.GREEN + p.getName()
								+ "'s " + ChatColor.GRAY + "Head");
						meta3.setOwner(p.getName());
						skull.setItemMeta(meta3);
						p.getInventory().addItem(skull);
						p.sendMessage(ChatColor.GRAY + "You recived "
								+ ChatColor.GREEN + p.getName() + "'s "
								+ ChatColor.GRAY + "Head");
					} else if (args.length == 1) {
						Player p = (Player) sender;
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if (target == null) {
							sender.sendMessage(ChatColor.RED
									+ "Error: The player '" + args[0]
									+ "' cannot be found.");
						} else {
							ItemStack skull = new ItemStack(397, 1, (short) 3);
							SkullMeta meta3 = (SkullMeta) skull.getItemMeta();
							meta3.setDisplayName(ChatColor.GREEN
									+ target.getName() + "'s " + ChatColor.GRAY
									+ "Head");
							meta3.setOwner(target.getName());
							skull.setItemMeta(meta3);
							p.getInventory().addItem(skull);
							p.sendMessage(ChatColor.GRAY + "You recived "
									+ ChatColor.GREEN + target.getName()
									+ "'s " + ChatColor.GRAY + "Head");
						}
					} else {
						return false;
					}
				} else {
					sender.sendMessage(ChatColor.RED + "No permission.");
				}
			}
		}
		return true;
	}

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player player = e.getPlayer();
			BlockState block = e.getClickedBlock().getState();
			if (block instanceof Skull) {
				Skull skull = (Skull) block;
				String skullowner = skull.getOwner();
				if (skullowner == null) {
					return;
				} else {
					player.sendMessage(ChatColor.GRAY + "This is "
							+ ChatColor.GREEN + skullowner + "'s "
							+ ChatColor.GRAY + "Head");
				}
			} else {
				return;
			}
		} else {
			return;
		}
	}
}
