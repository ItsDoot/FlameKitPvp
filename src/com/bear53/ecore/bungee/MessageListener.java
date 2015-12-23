package com.bear53.ecore.bungee;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.bear53.ecore.Core;

public class MessageListener implements PluginMessageListener {

	public Core plugin;

	public MessageListener(Core pl) {
		this.plugin = pl;
	}

	public void onPluginMessageReceived(String channel, Player player,
			byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
		}
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(
				message));

		String sub = "";
		try {
			sub = in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (sub.equals("GetServers")) {
			try {
				this.plugin.servers = in.readUTF().split(", ");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
