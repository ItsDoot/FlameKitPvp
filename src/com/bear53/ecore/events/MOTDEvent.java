package com.bear53.ecore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MOTDEvent implements Listener {

	@EventHandler
	public void onPing(ServerListPingEvent e) {
		// e.setMotd(ChatColor.DARK_GRAY + "◎" + ChatColor.DARK_RED + "Recharge"
		// + ChatColor.RED + "P" + ChatColor.DARK_RED + "v"
		// + ChatColor.RED + "P" + ChatColor.DARK_GRAY + "◎ \n"
		// + ChatColor.GRAY + "|" + ChatColor.RED + "" + ChatColor.BOLD
		// + "WARNING" + ChatColor.GRAY + "|" + ChatColor.DARK_RED
		// + " Under Development!");
		//e.setMotd("§8§l§m-[--§r§7§l§l§6< §c§lRechargePvP §6§l>§7§l>§8§l§m--]- \n§e>> §6§lKIT PVP §a§lNOW OPEN §e<< §6§lNEW KOTH! §9Come play!");
		e.setMotd("New IP! Join mc.flamesurvival.com!");
	}
}
