package me.jupdyke01.AdvancedKitPvp.GUI;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.jupdyke01.AdvancedKitPvp.Main;
import me.jupdyke01.AdvancedKitPvp.Kits.Kit;
import me.jupdyke01.AdvancedKitPvp.Utils.IntUtils;
import me.jupdyke01.AdvancedKitPvp.Utils.LoreUtils;

public class KitGUI implements Listener, CommandExecutor {

	private Main main;
	int size;

	public KitGUI(Main main) {
		this.main = main;
		int amount = IntUtils.closestNumber(main.getKitManager().kits.size() * 2, 9) > 54 ? 54 : IntUtils.closestNumber(main.getKitManager().kits.size() * 2, 9);
		size = amount <= 0 ? 9 : amount;

	}

	public Inventory KitInventory(Player player) {
		Inventory i = Bukkit.createInventory(null, size, ChatColor.DARK_AQUA + "    ----===" + ChatColor.RED + "Pvp Kits" + ChatColor.DARK_AQUA + "===----");
		player.openInventory(i);

		ItemStack Filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemMeta FillerMeta = Filler.getItemMeta();
		FillerMeta.setDisplayName(" ");
		Filler.setItemMeta(FillerMeta);


		int x = 0;
		for (Kit kit : main.getKitManager().kits) {
			ItemStack item = kit.getIcon();
			ItemMeta itemMeta = item.getItemMeta();
			ArrayList<String> lore = new ArrayList<>();
			itemMeta.setDisplayName(kit.getName());
			for (ItemStack tempitem : kit.getItems()) {
				if (tempitem.hasItemMeta()) {
					if (tempitem.getItemMeta().hasDisplayName()) {
						lore.add(tempitem.getItemMeta().getDisplayName());
					} else {
						lore.add(LoreUtils.formatMaterial(tempitem.getType().toString()));
					}
				} else {
					lore.add(LoreUtils.formatMaterial(tempitem.getType().toString()));
				}
			}
			itemMeta.setLore(lore);
			item.setItemMeta(itemMeta);
			i.setItem(x, item);
			i.setItem(x+1, Filler);
			x++;
		}
		
		return i;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equals("pvpkits")){
				KitInventory(player);
			}
		}
		return true;
	}

	@EventHandler
	public void  KitList(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (event.getInventory() != null) {
			if (event.getCurrentItem() != null) {
				ItemStack item = event.getCurrentItem();
				Inventory open = event.getInventory();

				if (open == null) {
					return;
				}
				if (open.getTitle().contains("Pvp Kits")) {

					event.setCancelled(true);

					if (item == null || !item.hasItemMeta()) {
						return;
					}

					if (!item.getItemMeta().getDisplayName().equals(" ")) {
						main.getKitManager().giveKit(player, item.getItemMeta().getDisplayName());
					}
				}
			}
		}
	}
}
