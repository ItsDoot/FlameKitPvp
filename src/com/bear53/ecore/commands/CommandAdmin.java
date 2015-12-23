package com.bear53.ecore.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bear53.ecore.Core;

public class CommandAdmin implements CommandExecutor, Listener {

	Core plugin;

	public CommandAdmin(Core pl) {
		this.plugin = pl;
	}

	private ArrayList<Player> admin = new ArrayList<Player>();

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED
					+ "Only players can use this command!");
		}
		Player p = (Player) sender;
		List<String> owners = plugin.getConfig().getStringList("owners");
		List<String> admins = plugin.getConfig().getStringList("admins");
		if (owners.contains(p.getName()) || (admins.contains(p.getName()))) {
			if (!admin.contains(p)) {
				admin.add(p);
				for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
					pl.hidePlayer(p);
				}
				p.sendMessage(Core.prefix + ChatColor.GREEN
						+ "You are now in admin mode");
				p.setGameMode(GameMode.CREATIVE);
				p.getInventory().clear();
				return true;
			} else {
				admin.remove(p);
				for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
					pl.showPlayer(p);
				}
				p.sendMessage(Core.prefix + ChatColor.RED
						+ "You are no longer in admin mode");
				p.setGameMode(GameMode.SURVIVAL);
				p.getInventory().clear();

				double x = plugin.getConfig().getDouble("spawn.x");
				double y = plugin.getConfig().getDouble("spawn.y");
				double z = plugin.getConfig().getDouble("spawn.z");
				float pitch = (float) plugin.getConfig().getDouble(
						"spawn.pitch");
				float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
				String world = plugin.getConfig().getString("spawn.world");
				Location loc = new Location(Bukkit.getServer().getWorld(world),
						x, y, z, yaw, pitch);
				p.teleport(loc);

				ItemStack stats = new ItemStack(Material.PAPER);
				ItemMeta statsmeta = stats.getItemMeta();
				statsmeta.setDisplayName(ChatColor.RED + "Stats");
				ArrayList<String> statslore = new ArrayList<String>();
				statslore.add(ChatColor.GREEN + "Click to see your");
				statslore.add(ChatColor.GREEN + "Kitpvp stats!");
				statsmeta.setLore(statslore);
				stats.setItemMeta(statsmeta);

				ItemStack kit = new ItemStack(Material.WATCH);
				ItemMeta kitmeta = kit.getItemMeta();
				kitmeta.setDisplayName(ChatColor.AQUA + "Kit Selector");
				ArrayList<String> kitlore = new ArrayList<String>();
				kitlore.add(ChatColor.AQUA + "Right Click to");
				kitlore.add(ChatColor.AQUA + "Chose Kit!");
				kitmeta.setLore(kitlore);
				kit.setItemMeta(kitmeta);

				ItemStack shop = new ItemStack(Material.CHEST);
				ItemMeta shopp = shop.getItemMeta();
				shopp.setDisplayName(ChatColor.YELLOW + "Donor Ranks!");
				ArrayList<String> slore = new ArrayList<String>();
				slore.add(ChatColor.YELLOW + "Right Click to");
				slore.add(ChatColor.YELLOW + "View all donation ranks!");
				shopp.setLore(slore);
				shop.setItemMeta(shopp);

				ItemStack mode = new ItemStack(Material.DIAMOND_SWORD);
				ItemMeta modemeta = mode.getItemMeta();
				modemeta.setDisplayName(ChatColor.DARK_RED + "PvP Modes");
				ArrayList<String> modelore = new ArrayList<String>();
				modelore.add(ChatColor.AQUA + "Right click to view");
				modelore.add(ChatColor.AQUA + "Avaliable Pvp modes!");
				modemeta.setLore(modelore);
				mode.setItemMeta(modemeta);

				ItemStack pvp = new ItemStack(Material.CLAY_BALL);
				ItemMeta pvpmeta = pvp.getItemMeta();
				pvpmeta.setDisplayName(ChatColor.YELLOW + "1v1");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.AQUA + "Right click a player to 1v1!");
				pvpmeta.setLore(lore);
				pvp.setItemMeta(pvpmeta);
				
				ItemStack itemshop = new ItemStack(Material.BLAZE_POWDER);
				ItemMeta shopmeta = itemshop.getItemMeta();
				shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
				ArrayList<String> shoplore = new ArrayList<String>();
				shoplore.add(ChatColor.RED
						+ "Be sure to have space in your inventory!");
				shopmeta.setLore(shoplore);
				itemshop.setItemMeta(shopmeta);
				p.getInventory().setItem(8, itemshop);

				p.getInventory().addItem(kit);
				p.getInventory().addItem(stats);
				p.getInventory().addItem(shop);
				p.getInventory().addItem(pvp);
				p.getInventory().addItem(mode);
				p.updateInventory();
				return true;
			}
		} else {
			p.sendMessage(Core.prefix + ChatColor.RED
					+ "You do not have permission to use this command!");
		}
		return true;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		for (Player pl : admin) {
			e.getPlayer().hidePlayer(pl);
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (admin.contains(e.getPlayer())) {
			admin.remove(e.getPlayer());
		} else {
			return;
		}
	}
}
