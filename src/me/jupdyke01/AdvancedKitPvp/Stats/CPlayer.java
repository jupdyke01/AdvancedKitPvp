package me.jupdyke01.AdvancedKitPvp.Stats;

public class CPlayer {
	private String name;
	private int kills, deaths;
	private String uuid;
	
	public CPlayer(String name, String uuid,  int kills, int deaths) {
		this.name = name;
		this.uuid = uuid;
		this.kills = kills;
		this.deaths = deaths;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int x) {
		kills = x;
	}
	public void addKills(int x) {
		kills += x;
	}
	
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int x) {
		deaths = x;
	}
	public void addDeaths(int x) {
		deaths += x;
	}
	
	
	public String getName() {
		return name;
	}
	public String getUUID() {
		return uuid;
	}
}
