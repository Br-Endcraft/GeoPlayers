package me.jonasxpx.geoplayers;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerRegistredEvent extends Event{

	private static final HandlerList handlers = new HandlerList();
	
	private Player player;
	private final RegionCodes rc;
	
	public PlayerRegistredEvent(Player player, RegionCodes rc) {
		this.player = player;
		this.rc = rc;;
	}

	
	public Player getPlayer() {
		return player;
	}
	
	public RegionCodes getRc() {
		return rc;
	}


	public HandlerList getHandlers() {
	    return handlers;
	}
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
