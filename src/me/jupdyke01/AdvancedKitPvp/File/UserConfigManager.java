package me.jupdyke01.AdvancedKitPvp.File;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.jupdyke01.AdvancedKitPvp.Main;
import me.jupdyke01.AdvancedKitPvp.Stats.CPlayer;


public class UserConfigManager {
	
	private Main main;
	
	public UserConfigManager(Main main) {
		this.main = main;
	}

	public void setup() {
		if (!main.getDataFolder().exists()) {
			main.getDataFolder().mkdir();
		}
		File f = new File(main.getDataFolder(), "Users");
		if (!(f.exists()))
			f.mkdirs();
	}

	public void CreatePlayerFile(Player player) {
		File f = new File (main.getDataFolder() + File.separator + "Users", player.getUniqueId().toString() + ".yml");
		if (!(f.exists())) {
			try {
				f.createNewFile();
				FileConfiguration config = YamlConfiguration.loadConfiguration(f);
				config.set("Name", player.getName());
				config.set("kills", 0);
				config.set("deaths", 0);
				config.save(f);
			} catch (IOException e) {
			}
		}
	}

	public void LoadPlayerFile(Player player) {
		CreatePlayerFile(player);
		File f = new File (main.getDataFolder() + File.separator + "Users", player.getUniqueId().toString() + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		if (!(main.getCPlayerManager().players.containsKey(player.getName()))) {
			main.getCPlayerManager().players.put(player.getName(), new CPlayer(player.getName(), player.getUniqueId().toString(), config.getInt("kills"), config.getInt("deaths")));
		}
	}
	public void SavePlayerFile(Player player) {
		CreatePlayerFile(player);
		File f = new File (main.getDataFolder() + File.separator + "Users", player.getUniqueId().toString() + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		if (f.exists()) {
			if (main.getCPlayerManager().players.containsKey(player.getName())) {
				try {
					config.set("kills", main.getCPlayerManager().players.get(player.getName()).getKills());
					config.set("deaths", main.getCPlayerManager().players.get(player.getName()).getDeaths());
					config.save(f);
				} catch (IOException e) {

				}
			}
		}
	}
}