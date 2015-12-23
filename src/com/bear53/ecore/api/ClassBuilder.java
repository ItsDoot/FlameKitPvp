package com.bear53.ecore.api;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public abstract class ClassBuilder {
	public static Object buildWitherSpawnPacket(int id, Location loc,
			Object dataWatcher) throws Exception {
		Object packet = NMSClass.PacketPlayOutSpawnEntityLiving.newInstance();
		AccessUtil.setAccessible(
				NMSClass.PacketPlayOutSpawnEntityLiving.getDeclaredField("a"))
				.set(packet, Integer.valueOf(id));
		AccessUtil.setAccessible(
				NMSClass.PacketPlayOutSpawnEntityLiving.getDeclaredField("b"))
				.set(packet, Integer.valueOf(64));
		AccessUtil.setAccessible(
				NMSClass.PacketPlayOutSpawnEntityLiving.getDeclaredField("c"))
				.set(packet, Integer.valueOf((int) loc.getX()));
		AccessUtil.setAccessible(
				NMSClass.PacketPlayOutSpawnEntityLiving.getDeclaredField("d"))
				.set(packet,
						Integer.valueOf(MathUtil.floor(loc.getY() * 32.0D)));
		AccessUtil.setAccessible(
				NMSClass.PacketPlayOutSpawnEntityLiving.getDeclaredField("e"))
				.set(packet, Integer.valueOf((int) loc.getZ()));

		AccessUtil
				.setAccessible(
						NMSClass.PacketPlayOutSpawnEntityLiving
								.getDeclaredField("i"))
				.set(packet,
						Byte.valueOf((byte) MathUtil.d(loc.getYaw() * 256.0F / 360.0F)));
		AccessUtil
				.setAccessible(
						NMSClass.PacketPlayOutSpawnEntityLiving
								.getDeclaredField("j"))
				.set(packet,
						Byte.valueOf((byte) MathUtil.d(loc.getPitch() * 256.0F / 360.0F)));
		AccessUtil
				.setAccessible(
						NMSClass.PacketPlayOutSpawnEntityLiving
								.getDeclaredField("k"))
				.set(packet,
						Byte.valueOf((byte) MathUtil.d(loc.getPitch() * 256.0F / 360.0F)));
		AccessUtil.setAccessible(
				NMSClass.PacketPlayOutSpawnEntityLiving.getDeclaredField("l"))
				.set(packet, dataWatcher);

		return packet;
	}

	@SuppressWarnings("deprecation")
	public static Object buildWitherSkullSpawnPacket(Object skull)
			throws Exception {
		Object spawnPacketSkull = NMSClass.PacketPlayOutSpawnEntity
				.getConstructor(new Class[] { NMSClass.Entity, Integer.TYPE })
				.newInstance(
						new Object[] {
								skull,
								Short.valueOf(EntityType.WITHER_SKULL
										.getTypeId()) });
		AccessUtil.setAccessible(
				NMSClass.PacketPlayOutSpawnEntity.getDeclaredField("j")).set(
				spawnPacketSkull, Integer.valueOf(66));

		return spawnPacketSkull;
	}

	public static Object buildSkullMetaPacket(int id, Object dataWatcher)
			throws Exception {
		setDataWatcherValue(dataWatcher, 0, Byte.valueOf((byte) 32));
		Object packet = NMSClass.PacketPlayOutEntityMetadata
				.getConstructor(
						new Class[] { Integer.TYPE, NMSClass.DataWatcher,
								Boolean.TYPE }).newInstance(
						new Object[] { Integer.valueOf(id), dataWatcher,
								Boolean.valueOf(true) });

		return packet;
	}

	public static Object buildNameMetadataPacket(int id, Object dataWatcher,
			int nameIndex, int visibilityIndex, String name) throws Exception {
		dataWatcher = setDataWatcherValue(dataWatcher, nameIndex,
				name != null ? name : "");
		dataWatcher = setDataWatcherValue(dataWatcher, visibilityIndex,
				Byte.valueOf((byte) ((name != null) && (!name.isEmpty()) ? 1
						: 0)));
		Object metaPacket = NMSClass.PacketPlayOutEntityMetadata
				.getConstructor(
						new Class[] { Integer.TYPE, NMSClass.DataWatcher,
								Boolean.TYPE }).newInstance(
						new Object[] { Integer.valueOf(id), dataWatcher,
								Boolean.valueOf(true) });

		return metaPacket;
	}

	public static Object updateEntityLocation(Object entity, Location loc)
			throws Exception {
		NMSClass.Entity.getDeclaredField("locX").set(entity,
				Double.valueOf(loc.getX()));
		NMSClass.Entity.getDeclaredField("locY").set(entity,
				Double.valueOf(loc.getY()));
		NMSClass.Entity.getDeclaredField("locZ").set(entity,
				Double.valueOf(loc.getZ()));
		return entity;
	}

	public static Object buildDataWatcher(@Nullable Object entity)
			throws Exception {
		Object dataWatcher = NMSClass.DataWatcher.getConstructor(
				new Class[] { NMSClass.Entity }).newInstance(
				new Object[] { entity });
		return dataWatcher;
	}

	public static Object buildWatchableObject(int index, Object value)
			throws Exception {
		return buildWatchableObject(getDataWatcherValueType(value), index,
				value);
	}

	public static Object buildWatchableObject(int type, int index, Object value)
			throws Exception {
		return NMSClass.WatchableObject.getConstructor(
				new Class[] { Integer.TYPE, Integer.TYPE, Object.class })
				.newInstance(
						new Object[] { Integer.valueOf(type),
								Integer.valueOf(index), value });
	}

	public static Object setDataWatcherValue(Object dataWatcher, int index,
			Object value) throws Exception {
		int type = getDataWatcherValueType(value);

		Object map = AccessUtil.setAccessible(
				NMSClass.DataWatcher.getDeclaredField("dataValues")).get(
				dataWatcher);
		NMUClass.gnu_trove_map_hash_TIntObjectHashMap.getDeclaredMethod("put",
				new Class[] { Integer.TYPE, Object.class }).invoke(
				map,
				new Object[] { Integer.valueOf(index),
						buildWatchableObject(type, index, value) });

		return dataWatcher;
	}

	public static Object getDataWatcherValue(Object dataWatcher, int index)
			throws Exception {
		Object map = AccessUtil.setAccessible(
				NMSClass.DataWatcher.getDeclaredField("dataValues")).get(
				dataWatcher);
		Object value = NMUClass.gnu_trove_map_hash_TIntObjectHashMap
				.getDeclaredMethod("get", new Class[] { Integer.TYPE }).invoke(
						map, new Object[] { Integer.valueOf(index) });

		return value;
	}

	public static int getWatchableObjectIndex(Object object) throws Exception {
		int index = AccessUtil.setAccessible(
				NMSClass.WatchableObject.getDeclaredField("b")).getInt(object);
		return index;
	}

	public static int getWatchableObjectType(Object object) throws Exception {
		int type = AccessUtil.setAccessible(
				NMSClass.WatchableObject.getDeclaredField("a")).getInt(object);
		return type;
	}

	public static Object getWatchableObjectValue(Object object)
			throws Exception {
		Object value = AccessUtil.setAccessible(
				NMSClass.WatchableObject.getDeclaredField("c")).get(object);
		return value;
	}

	public static int getDataWatcherValueType(Object value) {
		int type = 0;
		if ((value instanceof Number)) {
			if ((value instanceof Byte)) {
				type = 0;
			} else if ((value instanceof Short)) {
				type = 1;
			} else if ((value instanceof Integer)) {
				type = 2;
			} else if ((value instanceof Float)) {
				type = 3;
			}
		} else if ((value instanceof String)) {
			type = 4;
		} else if ((value != null)
				&& (value.getClass().equals(NMSClass.ItemStack))) {
			type = 5;
		} else if ((value != null)
				&& ((value.getClass().equals(NMSClass.ChunkCoordinates)) || (value
						.getClass().equals(NMSClass.BlockPosition)))) {
			type = 6;
		} else if ((value != null)
				&& (value.getClass().equals(NMSClass.Vector3f))) {
			type = 7;
		}
		return type;
	}

	public static Object buildArmorStandSpawnPacket(Object armorStand)
			throws Exception {
		Object spawnPacket = NMSClass.PacketPlayOutSpawnEntityLiving
				.getConstructor(new Class[] { NMSClass.EntityLiving })
				.newInstance(new Object[] { armorStand });
		AccessUtil.setAccessible(
				NMSClass.PacketPlayOutSpawnEntityLiving.getDeclaredField("b"))
				.setInt(spawnPacket, 30);

		return spawnPacket;
	}

	public static Object buildTeleportPacket(int id, Location loc,
			boolean onGround, boolean heightCorrection) throws Exception {
		Object packet = NMSClass.PacketPlayOutEntityTeleport.newInstance();
		AccessUtil.setAccessible(
				NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("a"))
				.set(packet, Integer.valueOf(id));
		AccessUtil.setAccessible(
				NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("b"))
				.set(packet, Integer.valueOf((int) (loc.getX() * 32.0D)));
		AccessUtil.setAccessible(
				NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("c"))
				.set(packet, Integer.valueOf((int) (loc.getY() * 32.0D)));
		AccessUtil.setAccessible(
				NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("d"))
				.set(packet, Integer.valueOf((int) (loc.getZ() * 32.0D)));
		AccessUtil
				.setAccessible(
						NMSClass.PacketPlayOutEntityTeleport
								.getDeclaredField("e"))
				.set(packet,
						Byte.valueOf((byte) (int) (loc.getYaw() * 256.0F / 360.0F)));
		AccessUtil
				.setAccessible(
						NMSClass.PacketPlayOutEntityTeleport
								.getDeclaredField("f"))
				.set(packet,
						Byte.valueOf((byte) (int) (loc.getPitch() * 256.0F / 360.0F)));

		return packet;
	}
}