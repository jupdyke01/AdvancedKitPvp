package me.jupdyke01.AdvancedKitPvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.jupdyke01.AdvancedKitPvp.Commands.CommandManager;
import me.jupdyke01.AdvancedKitPvp.Commands.KitCMD;
import me.jupdyke01.AdvancedKitPvp.File.ConfigManager;
import me.jupdyke01.AdvancedKitPvp.File.UserConfigManager;
import me.jupdyke01.AdvancedKitPvp.GUI.KitGUI;
import me.jupdyke01.AdvancedKitPvp.Kits.KitManager;
import me.jupdyke01.AdvancedKitPvp.Stats.CPlayerManager;

public class Main extends JavaPlugin {
	
	ConfigManager cfgm;
	UserConfigManager ucfgm;
	CommandManager cm;
	KitManager km;
	CPlayerManager cpm;
	KitGUI kgui;

	public void onEnable() {
		setupConfigManagers();
		km = new KitManager(this);
		cpm = new CPlayerManager(this);
		cm = new CommandManager(this);
		kgui = new KitGUI(this);
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			ucfgm.LoadPlayerFile(player);
		}
	
		getCommand("pvpkit").setExecutor(new KitCMD(cm));
		getCommand("pvpkits").setExecutor(kgui);
		getServer().getPluginManager().registerEvents(kgui, this);

	}

	public void setupConfigManagers() {
		cfgm = new ConfigManager(this);
		ucfgm = new UserConfigManager(this);
		cfgm.setup();
		ucfgm.setup();
	}
	
	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			ucfgm.SavePlayerFile(player);
		}
	}
	
	public KitManager getKitManager() {
		return km;
	}
	
	public ConfigManager getCFGM() {
		return cfgm;
	}
	
	public UserConfigManager getUCFGM() {
		return ucfgm;
	}
	
	public CPlayerManager getCPlayerManager() {
		return cpm;
	}
	
}
