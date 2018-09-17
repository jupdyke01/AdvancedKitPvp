package me.jupdyke01.AdvancedKitPvp.File;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.jupdyke01.AdvancedKitPvp.Main;

public class ConfigManager {

	private Main main;
	
	public FileConfiguration configcfg;
	public File config;
	public FileConfiguration kitscfg;
	public File kits;

	public ConfigManager(Main main) {
		this.main = main;
	}
	
	public void setup() {
		if (!(main.getDataFolder().exists())) {
			main.getDataFolder().mkdir();
		}

		config = new File(main.getDataFolder(), "config.yml");
		kits = new File(main.getDataFolder(), "kits.yml");

		if(!(config.exists())) {
			try {
				config.createNewFile();
			} catch(IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + " The config file for AdvancedKitPvp could not be loaded!");
			}
		}

		if(!(kits.exists())) {
			try {
				kits.createNewFile();
			} catch(IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + " The kits file for AdvancedKitPvp could not be loaded!");
			}
		}

		configcfg = YamlConfiguration.loadConfiguration(config);
		kitscfg = YamlConfiguration.loadConfiguration(kits);
	}

	// CONFIG
	public FileConfiguration getConfig() {
		return configcfg;
	}

	public void saveConfig() {
		try {
			configcfg.save(config);
		} catch(IOException e) {

		}
	}
	public void reloadConfig() {
		configcfg = YamlConfiguration.loadConfiguration(config);
	}

	// Kits
	public FileConfiguration getKits() {
		return kitscfg;
	}
	
	public void saveKits() {
		try {
			kitscfg.save(kits);
		} catch (IOException e) {

		}
	}
	public void reloadKits() {
		kitscfg = YamlConfiguration.loadConfiguration(kits);
	}
	
}

