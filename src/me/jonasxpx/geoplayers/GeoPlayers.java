package me.jonasxpx.geoplayers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GeoPlayers extends JavaPlugin{

	protected static Map<String, RegionCodes> players;
	protected static List<RegionCodes> blocked;
	public static GeoPlayers instance;
	
	@Override
	public void onEnable() {
		instance = this;
		players = new HashMap<>();
		blocked = new ArrayList<RegionCodes>();
		for(Player p : getServer().getOnlinePlayers())
		{
			new Thread(new RegisterGeoPlayer(p.getName(), p.getAddress().getAddress().getHostAddress())).start();
		}
		getServer().getConsoleSender().sendMessage("§bGeoPlayers foi ativado | GeoPlayers was enabled.");
		getServer().getPluginManager().registerEvents(new Listener() {
		
			@EventHandler
			public void onlogin(PlayerLoginEvent e){
				new Thread(new RegisterGeoPlayer(e.getPlayer().getName(), e.getRealAddress().getHostAddress())).start();
			}
			@EventHandler
			public void onQuit(PlayerQuitEvent e){
				players.remove(e.getPlayer().getName());
			}
			@EventHandler
			public void onRegister(PlayerRegistredEvent e){
				if(blocked.contains(e.getRc())){
					e.getPlayer().kickPlayer("§cAlgo está errado, Tente novamente mais tarde.");
				}
			}
		}, this);
		getCommand("geo").setExecutor(new Commands(this));
	}
	
	@Override
	public void onDisable() {
		players.clear();
		blocked.clear();
		HandlerList.unregisterAll(this);
	}

}
