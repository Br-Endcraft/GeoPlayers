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
		public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
			if(sender.isOp() && args.length == 2){
				if(args[0].equalsIgnoreCase("block")){
					RegionCodes rc = RegionCodes.getRegionFromString(args[1].toUpperCase());
					if(GeoPlayers.blocked.contains(rc)){
						GeoPlayers.blocked.remove(rc);
						sender.sendMessage(rc.getName() + " was removed.");
					} else {
						GeoPlayers.blocked.add(rc);
						sender.sendMessage(rc.getName() + " was added.");
					}
					return true;
				}
			}
			geo = new Players(instance);
			sender.sendMessage(geo.getTotal());
			return false;
		}
}
