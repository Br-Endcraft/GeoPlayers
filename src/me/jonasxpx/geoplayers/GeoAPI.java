package me.jonasxpx.geoplayers;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/**
 * Classe principal para recuperar o país do Jogador.
 *  
 * @author JonasXPX
 */
public abstract class GeoAPI {
	
	public static RegionCodes getRegion(Player player){
		return GeoPlayers.players.get(player.getName());
	}
	
	public static boolean contains(Player player){
		return GeoPlayers.players.containsKey(player.getName());
	}
	
	public static void registerPlayerGeo(String name, RegionCodes region){
		System.out.println("[GeoPlayers] capturado: " + name + " de " + region.getName());
		if(GeoPlayers.players.containsKey(name)){
			GeoPlayers.players.remove(name);
		}
		GeoPlayers.players.put(name, region);
	}
	public static String getTotal(){
		HashMap<String, Integer> total = new HashMap<>();
		for(String s : GeoPlayers.players.keySet()){
			if(!Bukkit.getPlayer(s).isOnline()){
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
