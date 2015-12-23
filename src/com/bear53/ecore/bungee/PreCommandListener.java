package com.bear53.ecore.bungee;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.bear53.ecore.Core;

public class PreCommandListener implements Listener {

	public Core plugin;

	public PreCommandListener(Core pl) {
		this.plugin = pl;
	}

	@EventHandler
	public void onPlayerPreCommand(PlayerCommandPreprocessEvent event)
			throws IOException {
		List<String> perms = plugin.getConfig().getStringList("perms");
		Player player = event.getPlayer();
		if (this.plugin.servers == null) {
			return;
		}
		for (String s : this.plugin.servers) {
			if (event.getMessage().equalsIgnoreCase("/" + s)) {
				if ((perms.contains(s)) || (player.isOp())) {
					event.setCancelled(true);

					ByteArrayOutputStream b = new ByteArrayOutputStream();
					DataOutputStream out = new DataOutputStream(b);

					out.writeUTF("Connect");
					out.writeUTF(s);
					out.close();

					player.sendPluginMessage(this.plugin, "BungeeCord",
							b.toByteArray());
					return;
				}
				event.setCancelled(true);

				player.sendMessage(ChatColor.RED
						+ "You don't have permissions for that server!");
				return;
			}
		}
	}
}
