package me.jonasxpx.geoplayers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class RegisterGeoPlayer implements Runnable{
	
	private final String p;
	private final String address;
	private static final String URL_CHECK = "http://144.217.93.236:8080/json/";
	
	public RegisterGeoPlayer(String player, String address) {
		this.p = player;
		this.address = address;
	}
	
	@Override
	public void run() {
		try {
			URL url = new URL(URL_CHECK + address);
			InputStream in = url.openConnection().getInputStream();
			StringBuilder sb = new StringBuilder();
			int b;
			while((b = in.read()) != -1){
				sb.append((char)b);
			}
			int index = sb.indexOf("country_code\":");
			int lenght = "country_code\":".length()+1;
			String code = sb.toString().substring(index + lenght, index + lenght + 2);
			RegionCodes rc = RegionCodes.getRegionFromString(code);
			GeoAPI.registerPlayerGeo(p, rc);
			new BukkitRunnable() {
				@Override
				public void run() {
					GeoPlayers.instance.getServer().getPluginManager().callEvent(new PlayerRegistredEvent(Bukkit.getPlayerExact(p), rc));
				}
			}.runTask(GeoPlayers.instance);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
