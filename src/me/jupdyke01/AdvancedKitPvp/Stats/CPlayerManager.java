package me.jupdyke01.AdvancedKitPvp.Stats;

import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.jupdyke01.AdvancedKitPvp.Main;

public class CPlayerManager implements Listener {

	public HashMap<String, CPlayer> players = new HashMap<>();
	private Main main;
	
	public CPlayerManager(Main main) {
		this.main = main;
	}
		
	@EventHandler
	public void onPlayerJoin2(PlayerJoinEvent e) {
		main.getUCFGM().LoadPlayerFile(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		main.getUCFGM().SavePlayerFile(e.getPlayer());
	}
	
}
