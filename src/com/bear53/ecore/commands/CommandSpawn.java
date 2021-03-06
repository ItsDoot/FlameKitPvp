package com.bear53.ecore.commands;

import java.util.ArrayList;

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
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import com.bear53.ecore.Core;
import com.bear53.ecore.TabUtils;
import com.bear53.ecore.kitpvp.KitPvp;

public class CommandSpawn implements CommandExecutor, Listener {

	Core plugin;

	public CommandSpawn(Core pl) {
		this.plugin = pl;
	}

	public static ArrayList<Player> move = new ArrayList<Player>();
	int task1;

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			final Player p = (Player) sender;

			double x = plugin.getConfig().getDouble("spawn.x");
			double y = plugin.getConfig().getDouble("spawn.y");
			double z = plugin.getConfig().getDouble("spawn.z");
			float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");
			float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
			String world = plugin.getConfig().getString("spawn.world");
			final Location loc = new Location(Bukkit.getServer()
					.getWorld(world), x, y, z, yaw, pitch);
			move.add(p);
			p.sendMessage(ChatColor.YELLOW
					+ "Teleporting in 5 seconds do not move");
			task1 = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
					new Runnable() {
						public void run() {
							if (move.contains(p)) {
								for (PotionEffect effect : p
										.getActivePotionEffects()) {
									p.removePotionEffect(effect.getType());
								}
								p.teleport(loc);
								p.getInventory().clear();
								p.getInventory().setHelmet(null);
								p.getInventory().setChestplate(null);
								p.getInventory().setLeggings(null);
								p.getInventory().setBoots(null);

								p.setFireTicks(0);
								p.setGameMode(GameMode.SURVIVAL);
								p.setFoodLevel(20);
								p.setHealthScale(20.0D);
								p.setHealth(20.0D);

								move.remove(p);

								ItemStack stats = new ItemStack(Material.PAPER);
								ItemMeta statsmeta = stats.getItemMeta();
								statsmeta.setDisplayName(ChatColor.RED
										+ "Stats");
								ArrayList<String> statslore = new ArrayList<String>();
								statslore.add(ChatColor.GREEN
										+ "Click to see your");
								statslore
										.add(ChatColor.GREEN + "Kitpvp stats!");
								statsmeta.setLore(statslore);
								stats.setItemMeta(statsmeta);

								ItemStack kit = new ItemStack(Material.WATCH);
								ItemMeta kitmeta = kit.getItemMeta();
								kitmeta.setDisplayName(ChatColor.AQUA
										+ "Kit Selector");
								ArrayList<String> kitlore = new ArrayList<String>();
								kitlore.add(ChatColor.AQUA + "Right Click to");
								kitlore.add(ChatColor.AQUA + "Chose Kit!");
								kitmeta.setLore(kitlore);
								kit.setItemMeta(kitmeta);

								ItemStack shop = new ItemStack(Material.CHEST);
								ItemMeta shopp = shop.getItemMeta();
								shopp.setDisplayName(ChatColor.YELLOW
										+ "Donor Ranks!");
								ArrayList<String> slore = new ArrayList<String>();
								slore.add(ChatColor.YELLOW + "Right Click to");
								slore.add(ChatColor.YELLOW
										+ "View all donation ranks!");
								shopp.setLore(slore);
								shop.setItemMeta(shopp);

								ItemStack mode = new ItemStack(
										Material.DIAMOND_SWORD);
								ItemMeta modemeta = mode.getItemMeta();
								modemeta.setDisplayName(ChatColor.DARK_RED
										+ "PvP Modes");
								ArrayList<String> modelore = new ArrayList<String>();
								modelore.add(ChatColor.AQUA
										+ "Right click to view");
								modelore.add(ChatColor.AQUA
										+ "Avaliable Pvp modes!");
								modemeta.setLore(modelore);
								mode.setItemMeta(modemeta);

								ItemStack pvp = new ItemStack(
										Material.CLAY_BALL);
								ItemMeta pvpmeta = pvp.getItemMeta();
								pvpmeta.setDisplayName(ChatColor.YELLOW + "1v1");
								ArrayList<String> lore = new ArrayList<String>();
								lore.add(ChatColor.AQUA
										+ "Right click a player to 1v1!");
								pvpmeta.setLore(lore);
								pvp.setItemMeta(pvpmeta);

								TabUtils.sendTabHeaderFooter(p,
										ChatColor.RED + "Flame"
												+ ChatColor.AQUA + " Network",
										ChatColor.GRAY + "IP " + ChatColor.GOLD
												+ "mc.flamesurvival.com"
												+ ChatColor.GRAY + "\nShop @ "
												+ ChatColor.GREEN
												+ "store.flamehub.net");

								KitPvp.activeKit.remove(p.getName());
								KitPvp.scorp.remove(p.getName());

								ItemStack itemshop = new ItemStack(
										Material.BLAZE_POWDER);
								ItemMeta shopmeta = itemshop.getItemMeta();
								shopmeta.setDisplayName(ChatColor.GREEN
										+ "Item Shop");
								ArrayList<String> shoplore = new ArrayList<String>();
								shoplore.add(ChatColor.RED
										+ "Be sure to have space in your inventory!");
								shopmeta.setLore(shoplore);
								itemshop.setItemMeta(shopmeta);
								p.getInventory().setItem(8, itemshop);

								p.getInventory().addItem(
										new ItemStack[] { kit });
								p.getInventory().addItem(
										new ItemStack[] { stats });
								p.getInventory().addItem(
										new ItemStack[] { shop });
								p.getInventory().addItem(
										new ItemStack[] { pvp });
								p.getInventory().addItem(mode);
							}
						}
					}, 100);
		}
		return true;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void TPMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Location from = e.getFrom();
		Location to = e.getTo();
		double fromx = from.getX();
		double fromy = from.getY();
		double fromz = from.getZ();
		double tox = to.getX();
		double toy = to.getY();
		double toz = to.getZ();
		if (move.contains(p)) {
			if (fromx > tox || fromy > toy || fromz > toz || fromx < tox
					|| fromy < toy || fromz < toz) {
				move.remove(p);
				p.sendMessage(ChatColor.RED + "Teleportation Cancelled");
			}
		}
	}
}