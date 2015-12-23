package com.bear53.ecore.kitpvp.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.bear53.ecore.Core;
import com.bear53.ecore.kitpvp.KitPvp;

public class ThorClass implements Listener, CommandExecutor {

	Core plugin;

	public ThorClass(Core pl) {
		this.plugin = pl;
	}

	public List<String> thorDelay = new ArrayList<String>();

	@EventHandler
	public void onThorInteract(PlayerInteractEvent event) {
		try {
			if ((event.getPlayer() != null) && (event.getAction() != null)
					&& (event.getAction() == Action.RIGHT_CLICK_BLOCK)
					&& (event.getClickedBlock() != null)
					&& (event.getItem() != null)
					&& (event.getItem().getType() == Material.GOLD_AXE)) {
				if (!this.thorDelay.contains(event.getPlayer().getName())) {
					event.getPlayer()
							.getWorld()
							.strikeLightning(
									event.getClickedBlock()
											.getWorld()
											.getHighestBlockAt(
													event.getClickedBlock()
															.getLocation())
											.getLocation().clone()
											.add(0.0D, 1.0D, 0.0D))
							.setFireTicks(0);
					if (5 > 0) {
						final String pName = event.getPlayer().getName();
						this.thorDelay.add(pName);
						event.getPlayer().getServer().getScheduler()
								.runTaskLater(this.plugin, new Runnable() {
									public void run() {
										thorDelay.remove(pName);
									}
								}, 5 * 20L);
					}
				} else {
					int thorDelayTime = 5;
					if (thorDelayTime != 1) {
						event.getPlayer()
								.sendMessage(
										ChatColor.RED
												+ "You must wait "
												+ 5
												+ " seconds before casting down thor again.");
					} else {
						event.getPlayer()
								.sendMessage(
										ChatColor.RED
												+ "You must wait "
												+ 1
												+ " second before casting down thor again.");
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("thor")) {
			if (plugin.getConfig().getInt(
					"players." + p.getUniqueId().toString() + ".1v1wins") >= 40) {
				KitPvp.activeKit.add(p.getName());
				p.sendMessage(ChatColor.GREEN
						+ "You have activated the Thor Kit!");

				ItemStack Sword = new ItemStack(Material.IRON_SWORD);
				ItemStack spade = new ItemStack(Material.GOLD_AXE);
				ItemMeta sm = spade.getItemMeta();
				sm.setDisplayName(ChatColor.GOLD + "Thor Ability!");
				spade.setItemMeta(sm);
				p.getInventory().clear();
				KitPvp.clearEffects(p);
				p.getInventory().addItem(new ItemStack[] { Sword });
				p.getInventory().addItem(new ItemStack[] { spade });
				p.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET));
				p.getInventory().setChestplate(
						new ItemStack(Material.GOLD_CHESTPLATE));
				p.getInventory().setLeggings(
						new ItemStack(Material.GOLD_LEGGINGS));
				p.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS));
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
				ItemStack itemshop = new ItemStack(Material.BLAZE_POWDER);
				ItemMeta shopmeta = itemshop.getItemMeta();
				shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
				ArrayList<String> shoplore = new ArrayList<String>();
				shoplore.add(ChatColor.RED
						+ "Be sure to have space in your inventory!");
				shopmeta.setLore(shoplore);
				itemshop.setItemMeta(shopmeta);
				p.getInventory().setItem(8, itemshop);
				if (plugin.getConfig().getBoolean("potion-enabled")) {
					KitPvp.giveHeath(p);
				} else {
					KitPvp.giveSoup(p);
				}
				KitPvp.clearEffects(p);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
						Integer.MAX_VALUE, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,
						Integer.MAX_VALUE, 1));
			} else {
				p.sendMessage(KitPvp.noPerm);
			}
		} else {
			p.sendMessage(KitPvp.activekit);
		}
		return true;
	}
}
