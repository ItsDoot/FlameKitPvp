package com.bear53.ecore.kitpvp.commands;

import com.bear53.ecore.Core;
import com.bear53.ecore.kitpvp.KitPvp;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HalloweenClass extends ClassBase implements CommandExecutor {

	private Core plugin;

	public HalloweenClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("halloween")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin.getConfig().getBoolean("isHalloween")) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN + "You have activated the "
                            + ChatColor.GOLD + "Halloween " + ChatColor.GREEN + "Kit!");

                    KitPvp.clearEffects(p);
					p.getInventory().clear();

					p.getInventory().setItem(0, getPrimaryWeapon());
                    p.getInventory().setItem(8, getItemShop());

					p.getInventory().setHelmet(getHelmet());
					p.getInventory().setChestplate(getChestplate());
					p.getInventory().setLeggings(getLeggings());
					p.getInventory().setBoots(getBoots());

					p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);

					if (plugin.getConfig().getBoolean("potion-enabled")) {
						KitPvp.giveHeath(p);
					} else {
						KitPvp.giveSoup(p);
					}
				} else {
					p.sendMessage(KitPvp.noPerm);
				}
			} else {
				p.sendMessage(KitPvp.activekit);
			}
		}
		return true;
	}

    @Override
    public ItemStack getPrimaryWeapon() {
        ItemStack sword = new ItemStack(Material.GOLD_SWORD);
        sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
        sword.addEnchantment(Enchantment.KNOCKBACK, 1);
        return sword;
    }

    @Override
    public ItemStack getHelmet() {
        return new ItemStack(Material.JACK_O_LANTERN);
    }

    @Override
    public ItemStack getChestplate() {
        return new ItemStack(Material.IRON_CHESTPLATE);
    }

    @Override
    public ItemStack getLeggings() {
        return new ItemStack(Material.IRON_LEGGINGS);
    }

    @Override
    public ItemStack getBoots() {
        return new ItemStack(Material.IRON_BOOTS);
    }
}
