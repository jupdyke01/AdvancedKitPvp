package me.jupdyke01.AdvancedKitPvp.Kits;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class Kit {

	private String name;
	private ArrayList<ItemStack> items;
	private ItemStack icon;
	
	public Kit(String name, ArrayList<ItemStack> items, ItemStack icon) {
		this.name = name;
		this.items = items;
		this.icon = icon;
	}
	
	public ItemStack getIcon() {
		return icon;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<ItemStack> getItems() {
		return items;
	}
	
	public void addItem(ItemStack item) {
		items.add(item);
	}
	
}
