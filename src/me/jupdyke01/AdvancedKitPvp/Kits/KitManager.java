package me.jupdyke01.AdvancedKitPvp.Kits;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.jupdyke01.AdvancedKitPvp.Main;

public class KitManager {

	private Main main;

	public ArrayList<Kit> kits = new ArrayList<>();

	public KitManager(Main main) {
		this.main = main;
		loadKits();
	}

	public void addKit(Kit kit) {
		kits.add(kit);
	}

	public void remKit(Kit kit) {
		kits.remove(kit);
	}

	public Kit getKit(String name) {
		for (Kit kit : kits) {
			if (kit.getName().equals(name))
				return kit;
		}
		return null;
	}

	public void giveKit(Player p, String name) {
		Kit kit = getKit(name);
		for (ItemStack item : kit.getItems()) {
			p.getInventory().addItem(item);
		}
	}

	public void giveKit(Player p, Kit kit) {
		for (ItemStack item : kit.getItems()) {
			p.getInventory().addItem(item);
		}
	}

	public void loadKits() {
		ArrayList<Kit> tempKits = new ArrayList<>();
		ConfigurationSection section = main.getCFGM().getKits().getConfigurationSection("Kits");
		for (String kitname : section.getKeys(false)) {
			ArrayList<ItemStack> items = new ArrayList<>();
			ItemStack icon = new ItemStack(Material.WOOD_SWORD, 1);
			for (String itemname : section.getConfigurationSection(kitname).getKeys(false)) {
				if (!itemname.equals("id")) {
					ItemStack item = new ItemStack(getItem(section.getString(kitname + "." + itemname + ".id")));
					ItemMeta meta = item.getItemMeta();
					ArrayList<String> lore = new ArrayList<>();
					if (section.getString(kitname + "." + itemname + ".name") != null)
						meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', section.getString(kitname + "." + itemname + ".name")));
					if (section.getString(kitname + "." + itemname + ".lore") != null)
						lore.add(ChatColor.translateAlternateColorCodes('&', section.getString(kitname + "." + itemname + ".lore")));
					if (section.getString(kitname + "." + itemname + ".enchants") != null) {
						for (String enchantname : section.getStringList(kitname + "." + itemname + ".enchants")) {
							if (enchantname.split(":").length == 1) {
								meta.addEnchant(Enchantment.getByName(enchantname), 1, true);
							} else {
								meta.addEnchant(Enchantment.getByName(enchantname.split(":")[0]), Integer.parseInt(enchantname.split(":")[1]), true);
							}
						}
					}
					meta.setLore(lore);
					item.setItemMeta(meta);
					items.add(item);
				} else {
					icon = getItem(section.getConfigurationSection(kitname).getString("id"));
				}
			}
			tempKits.add(new Kit(kitname, items, icon));
		}
		kits.clear();
		kits.addAll(tempKits);
	}



	public ItemStack getItem(String icon) {
		Material mat = null;
		int data = 0;

		String obj[] = {};
		if (icon.contains(":"))
			obj = icon.split(":");


		if (obj.length == 2) {
			try {
				mat = Material.matchMaterial(obj[0]);
			} catch (Exception e) {
				return new ItemStack(Material.BOOK);
			}

			try {
				data = Integer.valueOf(obj[1]);
			} catch (NumberFormatException e) {
				return new ItemStack(Material.BOOK);
			}
		} else {
			try {
				mat = Material.matchMaterial(icon);
			} catch (Exception e) {
				return new ItemStack(Material.BOOK);
			}
		}

		ItemStack item = new ItemStack(mat, 1);
		item.setDurability((short) data);
		return item;
	}

}
