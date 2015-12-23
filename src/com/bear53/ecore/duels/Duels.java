package com.bear53.ecore.duels;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bear53.ecore.Core;
import com.bear53.ecore.kitpvp.KitPvp;

public class Duels implements Listener {

	Core plugin;

	public Duels(Core pl) {
		this.plugin = pl;
	}

	public static String challenger;
	int task1;

	public static boolean arenaactive = false;

	public static Inventory myInventory = Bukkit.createInventory(null, 27,
			"PVP CHALLENGE");
	static {
		ItemStack accept = new ItemStack(Material.STAINED_GLASS_PANE, 1,
				(short) 5);
		ItemMeta meta1 = accept.getItemMeta();
		meta1.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD
				+ "Accept");
		accept.setItemMeta(meta1);

		ItemStack decline = new ItemStack(Material.STAINED_GLASS_PANE, 1,
				(short) 14);
		ItemMeta meta2 = decline.getItemMeta();
		meta2.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD
				+ "Decline");
		decline.setItemMeta(meta2);

		ItemStack green = new ItemStack(Material.STAINED_GLASS_PANE, 1,
				(short) 15);
		ItemMeta greenm = green.getItemMeta();
		greenm.setDisplayName(" ");
		green.setItemMeta(greenm);

		myInventory.setItem(0, accept);
		myInventory.setItem(1, accept);
		myInventory.setItem(2, accept);
		myInventory.setItem(9, accept);
		myInventory.setItem(10, accept);
		myInventory.setItem(11, accept);
		myInventory.setItem(18, accept);
		myInventory.setItem(19, accept);
		myInventory.setItem(20, accept);

		myInventory.setItem(6, decline);
		myInventory.setItem(7, decline);
		myInventory.setItem(8, decline);
		myInventory.setItem(15, decline);
		myInventory.setItem(16, decline);
		myInventory.setItem(17, decline);
		myInventory.setItem(24, decline);
		myInventory.setItem(25, decline);
		myInventory.setItem(26, decline);

		for (int i = 0; i < myInventory.getSize(); i++) {
			if (myInventory.getItem(i) == null) {
				myInventory.setItem(i, green);
			}
		}
	}

	@EventHandler
	public void touchytouchy(final PlayerInteractEntityEvent event) {
		final Player rightclick = (Player) event.getRightClicked();
		ItemStack pvp = new ItemStack(Material.CLAY_BALL);
		ItemMeta pvpmeta = pvp.getItemMeta();
		pvpmeta.setDisplayName(ChatColor.YELLOW + "1v1");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.AQUA + "Right click a player to 1v1!");
		pvpmeta.setLore(lore);
		pvp.setItemMeta(pvpmeta);
		if (rightclick instanceof Player) {
			if (event.getPlayer().getItemInHand().equals(pvp)) {
				if (!arenaactive) {
					rightclick.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD
							+ event.getPlayer().getDisplayName()
							+ ChatColor.RED + " Has challenged you for a 1v1!");
					rightclick.sendMessage(ChatColor.YELLOW
							+ "This request will time out in 10 seconds");
					rightclick.openInventory(myInventory);
					event.getPlayer().sendMessage(
							ChatColor.GREEN + "Challenged "
									+ rightclick.getName() + " to a 1v1!");
					event.getPlayer()
							.sendMessage(
									ChatColor.YELLOW
											+ "This request will time out in 10 seconds");
					challenger = event.getPlayer().getName().toString();
					arenaactive = true;
					task1 = Bukkit.getServer().getScheduler()
							.scheduleSyncDelayedTask(plugin, new Runnable() {
								public void run() {
									arenaactive = false;
									challenger = null;
									rightclick.closeInventory();
									event.getPlayer().sendMessage(
											ChatColor.RED
													+ "The 1v1 request to "
													+ rightclick.getName()
													+ " has timed out!");
									rightclick.sendMessage(ChatColor.RED
											+ "The 1v1 request from "
											+ event.getPlayer().getName()
											+ " has timed out!");
								}
							}, 200);
				} else {
					event.setCancelled(true);
					event.getPlayer().sendMessage(
							ChatColor.RED + "Error: All arenas full");
				}
			}
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		final Player player = (Player) event.getWhoClicked();
		Inventory inventory = event.getInventory();
		ItemStack clicked = event.getCurrentItem();
		if (inventory.getName().equals(myInventory.getName())) {
			ItemStack accept = new ItemStack(Material.STAINED_GLASS_PANE, 1,
					(short) 5);
			ItemMeta meta1 = accept.getItemMeta();
			meta1.setDisplayName(ChatColor.GREEN + "Accept");
			accept.setItemMeta(meta1);

			ItemStack decline = new ItemStack(Material.STAINED_GLASS_PANE, 1,
					(short) 14);
			ItemMeta meta2 = decline.getItemMeta();
			meta2.setDisplayName(ChatColor.RED + "Decline");
			decline.setItemMeta(meta2);
			if (clicked
					.getItemMeta()
					.getDisplayName()
					.equalsIgnoreCase(
							ChatColor.DARK_GREEN + "" + ChatColor.BOLD
									+ "Accept")) {
				event.setCancelled(true);
				player.closeInventory();
				Bukkit.getServer().getScheduler().cancelTask(task1);
				World world = player.getWorld();
				World world2 = Bukkit.getPlayer(challenger).getWorld();
				final Location location = new Location(world, -534.117,
						101.000, 81.700);
				final Location location2 = new Location(world2, -533.909,
						101.00, 36.422);
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
						new Runnable() {
							public void run() {
								player.teleport(location);
								Bukkit.getPlayer(challenger)
										.teleport(location2);

								ItemStack Sword = new ItemStack(
										Material.DIAMOND_SWORD);
								ItemStack chest = new ItemStack(
										Material.IRON_CHESTPLATE);
								ItemStack legs = new ItemStack(
										Material.IRON_LEGGINGS);
								ItemStack boots = new ItemStack(
										Material.IRON_BOOTS);
								ItemStack helm = new ItemStack(
										Material.IRON_HELMET);

								Player c = Bukkit.getPlayer(challenger);

								c.getInventory().clear();
								c.setGameMode(GameMode.SURVIVAL);
								c.getInventory().addItem(
										new ItemStack[] { Sword });
								c.getInventory().setHelmet(helm);
								c.getInventory().setChestplate(chest);
								c.getInventory().setLeggings(legs);
								c.getInventory().setBoots(boots);
								c.playSound(c.getLocation(), Sound.LEVEL_UP,
										3.0F, 2.0F);
								if (plugin.getConfig().getBoolean(
										"potion-enabled")) {
									KitPvp.giveHeath(c);
								} else {
									KitPvp.giveSoup(c);
								}
								c.updateInventory();
								c.sendMessage(ChatColor.GREEN
										+ "You have entered the arena!");

								player.getInventory().clear();
								player.setGameMode(GameMode.SURVIVAL);
								player.getInventory().addItem(
										new ItemStack[] { Sword });
								player.getInventory().setHelmet(helm);
								player.getInventory().setChestplate(chest);
								player.getInventory().setLeggings(legs);
								player.getInventory().setBoots(boots);
								player.playSound(player.getLocation(),
										Sound.LEVEL_UP, 3.0F, 2.0F);
								if (plugin.getConfig().getBoolean(
										"potion-enabled")) {
									KitPvp.giveHeath(player);
								} else {
									KitPvp.giveSoup(player);
								}
								player.updateInventory();
								player.sendMessage(ChatColor.GREEN
										+ "You have entered the arena!");
								Bukkit.broadcastMessage(ChatColor.AQUA
										+ c.getName() + ChatColor.GRAY
										+ " is now going against "
										+ ChatColor.AQUA + player.getName()
										+ ChatColor.GRAY + " in a 1v1!");
							}
						}, 100);
				Player c = Bukkit.getPlayer(challenger);
				player.sendMessage(ChatColor.GREEN + "You accepted "
						+ Bukkit.getPlayer(challenger).getName() + "'s 1v1");
				player.sendMessage(ChatColor.YELLOW
						+ "Teleporting in 5 seconds...");
				c.sendMessage(ChatColor.GREEN + player.getName()
						+ " has accepted your 1v1 request!");
				c.sendMessage(ChatColor.YELLOW + "Teleporting in 5 seconds...");
			} else if (clicked
					.getItemMeta()
					.getDisplayName()
					.equalsIgnoreCase(
							ChatColor.DARK_RED + "" + ChatColor.BOLD
									+ "Decline")) {
				event.setCancelled(true);
				player.closeInventory();
				Player rejected = Bukkit.getPlayer(challenger);
				rejected.sendMessage(ChatColor.RED + player.getName()
						+ " has rejected your 1v1!");
				arenaactive = false;
				challenger = null;
				Bukkit.getServer().getScheduler().cancelTask(task1);
			} else {
				event.setCancelled(true);
			}
		}
	}

	// @EventHandler
	// public void duelLog(PlayerQuitEvent e) {
	// if (e.getPlayer() == Bukkit.getPlayer(challenger)) {
	//
	// }
	// }
}
