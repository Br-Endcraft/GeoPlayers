package me.jonasxpx.geoplayers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor{

	private Players geo;
	private GeoPlayers instance;
	public Commands(GeoPlayers instance) {
		this.instance = instance;
	}
		@Override
		public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
			geo = new Players(instance);
			sender.sendMessage(geo.getTotal());
			return false;
		}
}
