package com.bear53.ecore.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import com.bear53.ecore.Core;

public class SoupSigns implements Listener {

	Core plugin;

	public SoupSigns(Core pl) {
		this.plugin = pl;
	}

	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("Soup")) {
			if (plugin.getConfig().getBoolean("potion-enabled")) {
				e.setLine(0, "§8[§4FPotions§8]");
				e.setLine(1, "§4§oPotions");
			} else {
				e.setLine(0, "§8[§4FSoup§8]");
				e.setLine(1, "§4§oSoup");
			}
		}
	}

	@EventHandler
	public void coloredSigns(SignChangeEvent e) {
		String[] lines = e.getLines();
		lines[0] = lines[0].replaceAll("&", "§");
		lines[1] = lines[1].replaceAll("&", "§");
		lines[2] = lines[2].replaceAll("&", "§");
	}

	@EventHandler
	public void inv(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		Potion is = new Potion(PotionType.INSTANT_HEAL);
		is.setSplash(true);
		is.setLevel(2);
		ItemStack potion = is.toItemStack(1);

		Inventory potion1 = Bukkit.getServer().createInventory(p, 36,
				ChatColor.GOLD + "Potions");
		Inventory inve = Bukkit.getServer().createInventory(p, 36,
				ChatColor.GOLD + "Soup");

		for (int i = 0; i < 36; i++) {
			inve.addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
		}

		for (int i = 0; i < 36; i++) {
			potion1.addItem(new ItemStack[] { potion });
		}
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& (e.getClickedBlock() != null)
				&& ((e.getClickedBlock().getType() == Material.WALL_SIGN) || (e
						.getClickedBlock().getType() == Material.SIGN_POST))) {
			Sign s = (Sign) e.getClickedBlock().getState();
			String[] lines = s.getLines();
			if ((lines.length > 2) && (lines[1].equals("§4§oSoup"))
					&& (lines.length > 1)
					&& (lines[0].equals("§8[§4FSoup§8]"))) {
				p.openInventory(inve);
			} else if ((lines.length > 2) && (lines[1].equals("§4§oPotions"))
					&& (lines.length > 1)
					&& (lines[0].equals("§8[§4FPotions§8]"))) {
				p.openInventory(potion1);
			}
		}
	}
}
