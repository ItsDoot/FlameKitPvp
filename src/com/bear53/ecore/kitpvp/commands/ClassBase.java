package com.bear53.ecore.kitpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public abstract class ClassBase {

    public abstract ItemStack getPrimaryWeapon();

    public abstract ItemStack getHelmet();
    public abstract ItemStack getChestplate();
    public abstract ItemStack getLeggings();
    public abstract ItemStack getBoots();

    public final ItemStack getItemShop() {
        ItemStack itemShop = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta shopMeta = itemShop.getItemMeta();
        shopMeta.setDisplayName(ChatColor.GREEN + "Item Shop");
        ArrayList<String> shopLore = new ArrayList<>();
        shopLore.add(ChatColor.RED + "Be sure to have space in your inventory!");
        shopMeta.setLore(shopLore);
        itemShop.setItemMeta(shopMeta);
        return itemShop;
    }
}