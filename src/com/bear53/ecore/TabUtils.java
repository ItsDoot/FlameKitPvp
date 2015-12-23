package com.bear53.ecore;

import java.lang.reflect.Field;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TabUtils {
	public static void sendTabHeaderFooter(Player player, String header,
			String footer) {
		CraftPlayer craftplayer = (CraftPlayer) player;
		PlayerConnection connection = craftplayer.getHandle().playerConnection;
		IChatBaseComponent JSONheader = ChatSerializer.a("{\"text\": \""
				+ header + "\"}");
		IChatBaseComponent JSONfooter = ChatSerializer.a("{\"text\": \""
				+ footer + "\"}");
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		try {
			Field headerField = packet.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(packet, JSONheader);
			headerField.setAccessible(!headerField.isAccessible());

			Field footerField = packet.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(packet, JSONfooter);
			footerField.setAccessible(!footerField.isAccessible());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		connection.sendPacket(packet);
	}

	public static void sendTabHeader(Player player, String header) {
		CraftPlayer craftplayer = (CraftPlayer) player;
		PlayerConnection connection = craftplayer.getHandle().playerConnection;
		IChatBaseComponent JSONheader = ChatSerializer.a("{\"text\": \""
				+ header + "\"}");
		IChatBaseComponent JSONfooter = ChatSerializer.a("{\"text\": \"\"}");
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		try {
			Field headerField = packet.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(packet, JSONheader);
			headerField.setAccessible(!headerField.isAccessible());

			Field footerField = packet.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(packet, JSONfooter);
			footerField.setAccessible(!footerField.isAccessible());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		connection.sendPacket(packet);
	}

	public static void sendTabFooter(Player player, String footer) {
		CraftPlayer craftplayer = (CraftPlayer) player;
		PlayerConnection connection = craftplayer.getHandle().playerConnection;
		IChatBaseComponent JSONheader = ChatSerializer.a("{\"text\": \"\"}");
		IChatBaseComponent JSONfooter = ChatSerializer.a("{\"text\": \""
				+ footer + "\"}");
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		try {
			Field headerField = packet.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(packet, JSONheader);
			headerField.setAccessible(!headerField.isAccessible());

			Field footerField = packet.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(packet, JSONfooter);
			footerField.setAccessible(!footerField.isAccessible());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		connection.sendPacket(packet);
	}
}