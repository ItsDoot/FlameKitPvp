package com.bear53.ecore.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.server.v1_8_R3.EntityWither;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.WorldServer;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.bear53.ecore.Core;

public class BossBarAPI implements Listener {
	private static final Map<UUID, BossBar> barMap = new ConcurrentHashMap<>();

	public static void setMessage(Player player, String message) {
		setMessage(player, message, 100.0F);
	}

	public static void setMessage(Player player, String message,
			float percentage) {
		setMessage(player, message, percentage, 0);
	}

	public static void setMessage(Player player, String message,
			float percentage, int timeout) {
		setMessage(player, message, percentage, timeout, 100.0F);
	}

	public static void setMessage(Player player, String message,
			float percentage, int timeout, float minHealth) {
		if (!barMap.containsKey(player.getUniqueId())) {
			barMap.put(player.getUniqueId(), new BossBar(player, message,
					percentage, timeout, minHealth));
		}
		BossBar bar = (BossBar) barMap.get(player.getUniqueId());
		if (!bar.message.equals(message)) {
			bar.setMessage(message);
		}
		float newHealth = percentage / 100.0F * bar.getMaxHealth();
		if (bar.health != newHealth) {
			bar.setHealth(newHealth);
		}
		if (!bar.isVisible()) {
			bar.setVisible(true);
		}
	}

	public static String getMessage(Player player) {
		BossBar bar = getBossBar(player);
		if (bar == null) {
			return null;
		}
		return bar.getMessage();
	}

	public static boolean hasBar(@Nonnull Player player) {
		return barMap.containsKey(player.getUniqueId());
	}

	public static void removeBar(@Nonnull Player player) {
		BossBar bar = getBossBar(player);
		if (bar == null) {
			return;
		}
		bar.setVisible(false);
		barMap.remove(player.getUniqueId());
	}

	public static void setHealth(Player player, float percentage) {
		BossBar bar = getBossBar(player);
		if (bar == null) {
			return;
		}
		bar.setHealth(percentage);
	}

	public static float getHealth(@Nonnull Player player) {
		BossBar bar = getBossBar(player);
		if (bar == null) {
			return -1.0F;
		}
		return bar.getHealth();
	}

	@Nullable
	public static BossBar getBossBar(@Nonnull Player player) {
		if (player == null) {
			return null;
		}
		return (BossBar) barMap.get(player.getUniqueId());
	}

	public static Collection<BossBar> getBossBars() {
		List<BossBar> list = new ArrayList<BossBar>(barMap.values());
		return list;
	}

	protected static void sendPacket(Player p, Object packet) {
		if ((p == null) || (packet == null)) {
			throw new IllegalArgumentException(
					"player and packet cannot be null");
		}
		try {
			Object handle = Reflection.getHandle(p);
			Object connection = Reflection.getField(handle.getClass(),
					"playerConnection").get(handle);
			Reflection.getMethod(connection.getClass(), "sendPacket",
					new Class[] { Reflection.getNMSClass("Packet") }).invoke(
					connection, new Object[] { packet });
		} catch (Exception localException) {
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onQuit(PlayerQuitEvent e) {
		removeBar(e.getPlayer());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onKick(PlayerKickEvent e) {
		removeBar(e.getPlayer());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onTeleport(PlayerTeleportEvent e) {
		handlePlayerTeleport(e.getPlayer(), e.getFrom(), e.getTo());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onRespawn(PlayerRespawnEvent e) {
		handlePlayerTeleport(e.getPlayer(), e.getPlayer().getLocation(),
				e.getRespawnLocation());
	}

	protected void handlePlayerTeleport(Player player, Location from,
			Location to) {
		if (!hasBar(player)) {
			return;
		}
		final BossBar bar = getBossBar(player);
		bar.setVisible(false);
		new BukkitRunnable() {
			public void run() {
				bar.setVisible(true);
			}
		}.runTaskLater(Core.instance, 2L);
	}

	public static void newBar(Player player) {
		Location loc = player.getLocation();
		WorldServer world = ((CraftWorld) player.getLocation().getWorld())
				.getHandle();
		EntityWither wither = new EntityWither(world);
		wither.setLocation(loc.getX(), loc.getY() - 10, loc.getZ(), 0, 0);
		wither.setCustomName("This is a test!");
		Packet<?> packet = new PacketPlayOutSpawnEntityLiving(wither);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

	@EventHandler
	public void onMove(final PlayerMoveEvent e) {
		final BossBar bar = getBossBar(e.getPlayer());
		if (bar != null) {
			new BukkitRunnable() {
				public void run() {
					if (!e.getPlayer().isOnline()) {
						return;
					}
					bar.updateMovement();
				}
			}.runTaskLater(Core.instance, 0L);
		}
	}
}
