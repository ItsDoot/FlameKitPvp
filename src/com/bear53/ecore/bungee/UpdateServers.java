package com.bear53.ecore.bungee;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.bear53.ecore.Core;

public class UpdateServers extends BukkitRunnable {
	public Core plugin;

	public UpdateServers(Core instance) {
		this.plugin = instance;
	}

	ByteArrayOutputStream b = new ByteArrayOutputStream();
	DataOutputStream out = new DataOutputStream(this.b);

	public void run() {
		System.out.println("Test");
		if (Bukkit.getOnlinePlayers().size() == 0) {
			return;
		}
		for (Player player : Bukkit.getOnlinePlayers()) {
			try {
				this.out.writeUTF("GetServers");
			} catch (IOException e) {
				e.printStackTrace();
			}
			player.sendPluginMessage(this.plugin, "BungeeCord",
					this.b.toByteArray());
		}
	}
}
