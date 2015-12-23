package com.bear53.ecore.api;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.bear53.ecore.Core;

public class BossBar extends BukkitRunnable {

	protected static int ENTITY_DISTANCE = 32;
	protected final int ID;
	protected final Player receiver;
	protected String message;
	protected float health;
	protected float healthMinus;
	protected float minHealth = 1.0F;
	protected Location location;
	protected World world;
	protected boolean visible = false;
	protected Object dataWatcher;

	protected BossBar(Player player, String message, float percentage,
			int timeout, float minHealth) {
		this.ID = new Random().nextInt();

		this.receiver = player;
		this.message = message;
		this.health = (percentage / 100.0F * getMaxHealth());
		this.minHealth = minHealth;
		this.world = player.getWorld();
		this.location = makeLocation(player.getLocation());
		if (percentage <= minHealth) {
			BossBarAPI.removeBar(player);
		}
		if (timeout > 0) {
			this.healthMinus = (getMaxHealth() / timeout);
			runTaskTimer(Core.instance, 20L, 20L);
		}
	}

	protected Location makeLocation(Location base) {
		return base.getDirection().multiply(ENTITY_DISTANCE)
				.add(base.toVector()).toLocation(this.world);
	}

	public float getMaxHealth() {
		return 300.0F;
	}

	public void setHealth(float percentage) {
		this.health = (percentage / 100.0F * getMaxHealth());
		if (this.health <= this.minHealth) {
			BossBarAPI.removeBar(this.receiver);
		} else {
			sendMetadata();
		}
	}

	public float getHealth() {
		return this.health;
	}

	public void setMessage(String message) {
		this.message = message;
		if (isVisible()) {
			sendMetadata();
		}
	}

	public String getMessage() {
		return this.message;
	}

	public Location getLocation() {
		return this.location;
	}

	public void run() {
		this.health -= this.healthMinus;
		if (this.health <= this.minHealth) {
			BossBarAPI.removeBar(this.receiver);
		} else {
			sendMetadata();
		}
	}

	public void setVisible(boolean flag) {
		if (flag == this.visible) {
			return;
		}
		if (flag) {
			spawn();
		} else {
			destroy();
		}
	}

	public boolean isVisible() {
		return this.visible;
	}

	public void updateMovement() {
		if (!this.visible) {
			return;
		}
		this.location = makeLocation(this.receiver.getLocation());
		try {
			Object packet = ClassBuilder.buildTeleportPacket(this.ID,
					getLocation(), false, false);
			BossBarAPI.sendPacket(this.receiver, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void updateDataWatcher() {
		if (this.dataWatcher == null) {
			try {
				this.dataWatcher = ClassBuilder.buildDataWatcher(null);
				ClassBuilder.setDataWatcherValue(this.dataWatcher, 17,
						new Integer(0));
				ClassBuilder.setDataWatcherValue(this.dataWatcher, 18,
						new Integer(0));
				ClassBuilder.setDataWatcherValue(this.dataWatcher, 19,
						new Integer(0));
				ClassBuilder.setDataWatcherValue(this.dataWatcher, 20,
						new Integer(1000));

				ClassBuilder.setDataWatcherValue(this.dataWatcher, 0,
						Byte.valueOf((byte) 32));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			ClassBuilder.setDataWatcherValue(this.dataWatcher, 6,
					Float.valueOf(this.health));

			ClassBuilder.setDataWatcherValue(this.dataWatcher, 11,
					Byte.valueOf((byte) 1));
			ClassBuilder.setDataWatcherValue(this.dataWatcher, 3,
					Byte.valueOf((byte) 1));

			ClassBuilder
					.setDataWatcherValue(this.dataWatcher, 10, this.message);
			ClassBuilder.setDataWatcherValue(this.dataWatcher, 2, this.message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void sendMetadata() {
		updateDataWatcher();
		try {
			Object metaPacket = ClassBuilder.buildNameMetadataPacket(this.ID,
					this.dataWatcher, 2, 3, this.message);
			BossBarAPI.sendPacket(this.receiver, metaPacket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void spawn() {
		try {
			updateMovement();
			updateDataWatcher();
			Object packet = ClassBuilder.buildWitherSpawnPacket(this.ID,
					getLocation(), this.dataWatcher);
			BossBarAPI.sendPacket(this.receiver, packet);
			this.visible = true;
			sendMetadata();
			updateMovement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void destroy() {
		try {
			cancel();
		} catch (IllegalStateException localIllegalStateException) {
		}
		try {
			Object packet = NMSClass.PacketPlayOutEntityDestroy
					.getConstructors();
			BossBarAPI.sendPacket(this.receiver, packet);
			this.visible = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}