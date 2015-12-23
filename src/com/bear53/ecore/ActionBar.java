package com.bear53.ecore;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {

	public static void sendActionbar(Player p, String message) {
		if (p != null) {
			IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer
					.a("{\"text\": \""
							+ ChatColor.translateAlternateColorCodes('&',
									message) + "\"}");

			PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);

			((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
		}
	}
}
