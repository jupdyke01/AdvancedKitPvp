package me.jupdyke01.AdvancedKitPvp.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.jupdyke01.AdvancedKitPvp.Main;
import me.jupdyke01.AdvancedKitPvp.Kits.Kit;

public class KitCMD implements CommandExecutor {

	private Main main;
	
	public KitCMD(CommandManager cm) {
		main = cm.getMain();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		for (Kit kit : main.getKitManager().kits) {
			main.getKitManager().giveKit(Bukkit.getPlayer("jupdyke01"), kit);
		}
		
		return true;
	}

}
