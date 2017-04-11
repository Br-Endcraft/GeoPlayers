package me.jonasxpx.geoplayers;

import java.util.HashMap;

import org.bukkit.Bukkit;

public class Players {

	private GeoPlayers instance;
	public Players() {
	}
	public Players(GeoPlayers instance) {
		this.instance = instance;
	}
	
	public String getTotal(){
		HashMap<String, Integer> total = new HashMap<>();
		for(String s : instance.players.keySet()){
			if(Bukkit.getPlayer(s) == null){
				continue;
			}
			RegionCodes r = GeoPlayers.players.get(s);
			if(total.containsKey(r.getName())){
				total.replace(r.getName(), total.get(r.getName()) + 1);
			} else {
				total.put(r.getName(), 1);
			}
		}
		System.out.println(GeoPlayers.players.toString());
		StringBuilder sb = new StringBuilder();
		total.forEach((k,v) -> sb.append("§b" + k + ": §c"+ v +" jogador(es)\n"));
		return sb.toString();
	}
}
