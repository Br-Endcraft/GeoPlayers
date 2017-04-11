package me.jonasxpx.geoplayers;

import java.util.HashMap;
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
	
	
	@Override
	public void onEnable() {
		players = new HashMap<>();
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
		}, this);
		getCommand("geo").setExecutor(new Commands(this));
	}
	
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}

}
