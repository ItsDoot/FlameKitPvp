package com.bear53.ecore.events;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.bear53.ecore.Core;

public class ItemPickupEvent implements Listener {

	Core plugin;

	public ItemPickupEvent(Core pl) {
		this.plugin = pl;
	}

	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent event) {
		if (event.getItem().getItemStack().getItemMeta().getDisplayName() != null) {
			if (event.getItem().getItemStack().getItemMeta().getDisplayName()
					.contains("§cBlood")) {
				event.setCancelled(true);
			} else {
				event.setCancelled(true);
			}
		} else {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onHopperPickup(InventoryPickupItemEvent event) {
		if (event.getItem().getItemStack().getItemMeta().getDisplayName() != null) {
			if (event.getItem().getItemStack().getItemMeta().getDisplayName()
					.contains("§cBlood")) {
				event.setCancelled(true);
			}
		}
		if (event.getItem().getItemStack().getItemMeta().getDisplayName() == null) {
			return;
		}
		event.getItem().getItemStack().getItemMeta();
	}

	@EventHandler
	public void onEntityDeath1(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		World eworld = entity.getLocation().getWorld();

		eworld.playEffect(entity.getLocation(), Effect.STEP_SOUND, 152);
		eworld.playEffect(entity.getLocation(), Effect.STEP_SOUND, 55);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Entity player = event.getEntity().getPlayer();

		Location loc = player.getLocation();

		ItemStack itemstack = new ItemStack(Material.GHAST_TEAR);
		itemstack.setDurability((short) 1);
		ItemMeta itemMeta = itemstack.getItemMeta();
		itemMeta.setDisplayName("§cBlood");
		itemstack.setItemMeta(itemMeta);

		ItemStack itemstack1 = new ItemStack(Material.GHAST_TEAR);
		itemstack1.setDurability((short) 1);
		ItemMeta itemMeta1 = itemstack1.getItemMeta();
		itemMeta1.setDisplayName("§cBlood 1");
		itemstack1.setItemMeta(itemMeta1);

		ItemStack itemstack2 = new ItemStack(Material.GHAST_TEAR);
		itemstack2.setDurability((short) 1);
		ItemMeta itemMeta2 = itemstack2.getItemMeta();
		itemMeta2.setDisplayName("§cBlood 2");
		itemstack2.setItemMeta(itemMeta2);

		ItemStack itemstack3 = new ItemStack(Material.GHAST_TEAR);
		itemstack3.setDurability((short) 1);
		ItemMeta itemMeta3 = itemstack3.getItemMeta();
		itemMeta3.setDisplayName("§cBlood 3");
		itemstack3.setItemMeta(itemMeta3);

		ItemStack itemstack4 = new ItemStack(Material.GHAST_TEAR);
		itemstack4.setDurability((short) 1);
		ItemMeta itemMeta4 = itemstack4.getItemMeta();
		itemMeta4.setDisplayName("§cBlood 4");
		itemstack4.setItemMeta(itemMeta4);

		ItemStack itemstack5 = new ItemStack(Material.GHAST_TEAR);
		itemstack5.setDurability((short) 1);
		ItemMeta itemMeta5 = itemstack5.getItemMeta();
		itemMeta5.setDisplayName("§cBlood 5");
		itemstack5.setItemMeta(itemMeta5);

		ItemStack itemstack6 = new ItemStack(Material.GHAST_TEAR);
		itemstack6.setDurability((short) 1);
		ItemMeta itemMeta6 = itemstack6.getItemMeta();
		itemMeta6.setDisplayName("§cBlood 6");
		itemstack6.setItemMeta(itemMeta6);

		ItemStack itemstack7 = new ItemStack(Material.GHAST_TEAR);
		itemstack7.setDurability((short) 1);
		ItemMeta itemMeta7 = itemstack7.getItemMeta();
		itemMeta7.setDisplayName("§cBlood 7");
		itemstack7.setItemMeta(itemMeta7);

		ItemStack itemstack8 = new ItemStack(Material.GHAST_TEAR);
		itemstack8.setDurability((short) 1);
		ItemMeta itemMeta8 = itemstack8.getItemMeta();
		itemMeta8.setDisplayName("§cBlood 8");
		itemstack8.setItemMeta(itemMeta8);

		ItemStack itemstack9 = new ItemStack(Material.GHAST_TEAR);
		itemstack9.setDurability((short) 1);
		ItemMeta itemMeta9 = itemstack9.getItemMeta();
		itemMeta9.setDisplayName("§cBlood 9");
		itemstack9.setItemMeta(itemMeta9);

		ItemStack itemstack10 = new ItemStack(Material.GHAST_TEAR);
		itemstack10.setDurability((short) 1);
		ItemMeta itemMeta10 = itemstack10.getItemMeta();
		itemMeta10.setDisplayName("§cBlood 10");
		itemstack10.setItemMeta(itemMeta10);

		ItemStack itemstack11 = new ItemStack(Material.GHAST_TEAR);
		itemstack11.setDurability((short) 1);
		ItemMeta itemMeta11 = itemstack11.getItemMeta();
		itemMeta11.setDisplayName("§cBlood 11");
		itemstack11.setItemMeta(itemMeta11);

		final Item item = loc.getWorld().dropItemNaturally(loc, itemstack);
		final Item item1 = loc.getWorld().dropItemNaturally(loc, itemstack1);
		final Item item2 = loc.getWorld().dropItemNaturally(loc, itemstack2);
		final Item item3 = loc.getWorld().dropItemNaturally(loc, itemstack3);
		final Item item4 = loc.getWorld().dropItemNaturally(loc, itemstack4);
		final Item item5 = loc.getWorld().dropItemNaturally(loc, itemstack5);
		final Item item6 = loc.getWorld().dropItemNaturally(loc, itemstack6);
		final Item item7 = loc.getWorld().dropItemNaturally(loc, itemstack7);
		final Item item8 = loc.getWorld().dropItemNaturally(loc, itemstack8);
		final Item item9 = loc.getWorld().dropItemNaturally(loc, itemstack9);
		final Item item10 = loc.getWorld().dropItemNaturally(loc, itemstack10);
		final Item item11 = loc.getWorld().dropItemNaturally(loc, itemstack11);

		item.setPickupDelay(999999999);
		item1.setPickupDelay(999999999);
		item2.setPickupDelay(999999999);
		item3.setPickupDelay(999999999);
		item4.setPickupDelay(999999999);
		item5.setPickupDelay(999999999);
		item6.setPickupDelay(999999999);
		item7.setPickupDelay(999999999);
		item8.setPickupDelay(999999999);
		item9.setPickupDelay(999999999);
		item10.setPickupDelay(999999999);
		item11.setPickupDelay(999999999);

		item.setVelocity(new Vector(Math.random() * 0.1D, 0.4D,
				Math.random() * 0.1D));
		item1.setVelocity(new Vector(Math.random() * -0.1D, 0.4D,
				Math.random() * 0.1D));
		item1.setVelocity(new Vector(Math.random() * 0.1D, 0.4D, Math.random()
				* -0.1D));
		item1.setVelocity(new Vector(Math.random() * -0.1D, 0.4D, Math.random()
				* -0.1D));
		item4.setVelocity(new Vector(Math.random() * 0.1D, 0.4D,
				Math.random() * 0.1D));
		item5.setVelocity(new Vector(Math.random() * -0.1D, 0.4D,
				Math.random() * 0.1D));
		item6.setVelocity(new Vector(Math.random() * 0.1D, 0.4D, Math.random()
				* -0.1D));
		item7.setVelocity(new Vector(Math.random() * -0.1D, 0.4D, Math.random()
				* -0.1D));
		item8.setVelocity(new Vector(Math.random() * 0.1D, 0.4D,
				Math.random() * 0.1D));
		item9.setVelocity(new Vector(Math.random() * -0.1D, 0.4D,
				Math.random() * 0.1D));
		item10.setVelocity(new Vector(Math.random() * 0.1D, 0.4D, Math.random()
				* -0.1D));
		item11.setVelocity(new Vector(Math.random() * -0.1D, 0.4D, Math
				.random() * -0.1D));

		event.getEntity().playSound(player.getLocation(), Sound.HURT_FLESH,
				1.0F, 0.0F);
		event.getEntity().playSound(player.getLocation(), Sound.WITHER_SPAWN,
				1.0F, 1.0F);

		loc.getWorld().playEffect(player.getLocation(), Effect.STEP_SOUND, 152);
		loc.getWorld().playEffect(player.getLocation(), Effect.STEP_SOUND, 55);

		Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin,
				new Runnable() {
					public void run() {
						item.remove();
						item1.remove();
						item2.remove();
						item3.remove();
						item4.remove();
						item5.remove();
						item6.remove();
						item7.remove();
						item8.remove();
						item9.remove();
						item10.remove();
						item11.remove();
					}
				}, 40L);
		if (event.getEntity().getPlayer() == null) {
			return;
		}
		if (event.getEntity() == null) {
			return;
		}
		if (event.getEntity().getKiller() == null) {
			return;
		}
		if ((event.getEntity().getKiller() != null)
				&& ((event.getEntity().getKiller() instanceof Player))) {
			event.getEntity()
					.getKiller()
					.playSound(event.getEntity().getKiller().getLocation(),
							Sound.SUCCESSFUL_HIT, 10.0F, 1.0F);
		}
	}
}
