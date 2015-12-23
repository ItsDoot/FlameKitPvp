package com.bear53.ecore.kitpvp;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bear53.ecore.Core;

public class HG implements Listener {

	Core plugin;

	public HG(Core pl) {
		this.plugin = pl;
	}

	public static Inventory Selection = Bukkit.createInventory(null, 9,
			ChatColor.AQUA + "Selection");

	static {
		ItemStack hg = new ItemStack(Material.WOOD_SWORD);
		ItemMeta hgmeta = hg.getItemMeta();
		hgmeta.setDisplayName(ChatColor.YELLOW + "HungerGames Pvp");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.AQUA + "Click to teleport to HungerGames style Pvp!");
		lore.add(ChatColor.AQUA + "Leather Armor and a Wood Sword!");
		hgmeta.setLore(lore);
		hg.setItemMeta(hgmeta);
		Selection.setItem(0, hg);

		ItemStack green = new ItemStack(Material.STAINED_GLASS_PANE, 1,
				(short) 15);
		ItemMeta greenm = green.getItemMeta();
		greenm.setDisplayName(" ");
		green.setItemMeta(greenm);

		ItemStack pvp = new ItemStack(Material.IRON_HELMET);
		ItemMeta pvpmeta = pvp.getItemMeta();
		pvpmeta.setDisplayName(ChatColor.YELLOW + "Original Pvp");
		ArrayList<String> pvplore = new ArrayList<String>();
		pvplore.add(ChatColor.AQUA + "Click to teleport to Original style Pvp!");
		pvplore.add(ChatColor.AQUA + "Iron Armor and a Iron Sword!");
		pvpmeta.setLore(pvplore);
		pvp.setItemMeta(pvpmeta);
		Selection.setItem(8, pvp);

		for (int i = 0; i < Selection.getSize(); i++) {
			if (Selection.getItem(i) == null) {
				Selection.setItem(i, green);
			}
		}
	}

	@EventHandler
	public void ServerInventoryOpenEvent(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand() != null) {
			if (p.getItemInHand().hasItemMeta()) {
				if (p.getItemInHand().getItemMeta().getDisplayName() != null) {
					if (p.getItemInHand().getItemMeta().getDisplayName()
							.equalsIgnoreCase(ChatColor.DARK_RED + "PvP Modes")) {
						e.setCancelled(true);
						p.sendMessage(ChatColor.WHITE
								+ "Loading active servers");
						p.openInventory(Selection);
					}
				}
			}
		}
	}

	@EventHandler
	public void onServerSelectEvent(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory() != null) {
			if (e.getInventory().getName() != null) {
				if (e.getInventory().getName()
						.equalsIgnoreCase(ChatColor.AQUA + "Selection")) {
					if (e.getCurrentItem() != null) {
						if (e.getCurrentItem().getItemMeta() != null) {
							if (e.getCurrentItem().getItemMeta()
									.getDisplayName() != null) {
								if (e.getCurrentItem()
										.getItemMeta()
										.getDisplayName()
										.equalsIgnoreCase(
												ChatColor.YELLOW
														+ "HungerGames Pvp")) {
									e.setCancelled(true);
									p.closeInventory();
									p.sendMessage(ChatColor.YELLOW
											+ "Teleporting...");
									double x = plugin.getConfig().getDouble(
											"hg.spawn.x");
									double y = plugin.getConfig().getDouble(
											"hg.spawn.y");
									double z = plugin.getConfig().getDouble(
											"hg.spawn.z");
									float pitch = (float) plugin.getConfig()
											.getDouble("hg.spawn.pitch");
									float yaw = (float) plugin.getConfig()
											.getDouble("hg.spawn.yaw");
									String world = plugin.getConfig()
											.getString("hg.spawn.world");
									Location loc = new Location(Bukkit
											.getServer().getWorld(world), x, y,
											z, yaw, pitch);
									p.teleport(loc);
									p.getInventory().clear();
									KitPvp.activeKit.add(p.getName());
									KitPvp.clearEffects(p);
									p.getInventory().setHelmet(null);
									p.getInventory().setChestplate(null);
									p.getInventory().setLeggings(
											new ItemStack(
													Material.LEATHER_LEGGINGS));
									p.getInventory().setBoots(
											new ItemStack(
													Material.LEATHER_BOOTS));
									p.getInventory().addItem(
											new ItemStack(Material.WOOD_SWORD));
									KitPvp.giveSoup(p);
									p.setGameMode(GameMode.SURVIVAL);
									p.sendMessage(ChatColor.GRAY
											+ "You are now in "
											+ ChatColor.YELLOW + "HG "
											+ ChatColor.GRAY + "pvp!");
								} else if (e
										.getCurrentItem()
										.getItemMeta()
										.getDisplayName()
										.equalsIgnoreCase(
												ChatColor.YELLOW
														+ "Original Pvp")) {
									e.setCancelled(true);
									p.closeInventory();
									p.sendMessage(ChatColor.YELLOW
											+ "Teleporting...");
									double x = plugin.getConfig().getDouble(
											"pvp.spawn.x");
									double y = plugin.getConfig().getDouble(
											"pvp.spawn.y");
									double z = plugin.getConfig().getDouble(
											"pvp.spawn.z");
									float pitch = (float) plugin.getConfig()
											.getDouble("pvp.spawn.pitch");
									float yaw = (float) plugin.getConfig()
											.getDouble("pvp.spawn.yaw");
									String world = plugin.getConfig()
											.getString("pvp.spawn.world");
									Location loc = new Location(Bukkit
											.getServer().getWorld(world), x, y,
											z, yaw, pitch);
									p.teleport(loc);
									p.getInventory().clear();
									KitPvp.activeKit.add(p.getName());
									KitPvp.clearEffects(p);
									p.getInventory()
											.setHelmet(
													new ItemStack(
															Material.IRON_HELMET));
									p.getInventory().setChestplate(
											new ItemStack(
													Material.IRON_CHESTPLATE));
									p.getInventory().setLeggings(
											new ItemStack(
													Material.IRON_LEGGINGS));
									p.getInventory().setBoots(
											new ItemStack(Material.IRON_BOOTS));
									p.getInventory().addItem(
											new ItemStack(Material.IRON_SWORD));
									KitPvp.giveSoup(p);
									p.setGameMode(GameMode.SURVIVAL);
									p.sendMessage(ChatColor.GRAY
											+ "You are now in "
											+ ChatColor.YELLOW + "Original "
											+ ChatColor.GRAY + "pvp!");
								} else {
									e.setCancelled(true);
								}
							}
						}
					}
				}
			}
		}
	}
}
