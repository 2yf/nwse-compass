package me.nwse.Compass.comnand;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.nwse.Compass.PluginBootstrap;
import me.nwse.Compass.util.Color;
import me.nwse.Compass.util.Tracker;

public class CompassCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String a, String[] args) {
		
		Player p = (Player) s;
		
		if (p instanceof Player) {
			
			if (args.length == 1) {
				
				Player t = Bukkit.getPlayer(args[0]);
				
				if (t != null) {
					
					if (t.getWorld().getEnvironment() == World.Environment.NORMAL) {
						
						Tracker.Tracklist.put(p.getName(), t.getName());
						
						if (p.getInventory().contains(Material.COMPASS)) {
							
							String message = PluginBootstrap.getPluginConfig().PLAYER_HAS_COMPASS_ALREADY_MESSAGE
									.replace("%target-name%", t.getName());
							p.sendMessage(Color.color(message));
							return false;
						}
						p.getInventory().addItem(new ItemStack(Material.COMPASS));
						p.setCompassTarget(t.getLocation());
							
						String message = PluginBootstrap.getPluginConfig().COMPASS_GIVEN_SUCCESSFULLY_MESSAGE
								.replace("%target-name%", t.getName());
						p.sendMessage(Color.color(message));
						return true;
					}
						
					String message = PluginBootstrap.getPluginConfig().TARGET_FOUND_IN_ANOTHER_WORLD_MESSAGE
							.replace("%target-name%", t.getName())
							.replace("%target-world%", t.getWorld().getEnvironment().toString());
					p.sendMessage(Color.color(message));
					return true;
				}
					
				String message = PluginBootstrap.getPluginConfig().TARGET_OFFLINE_MESSAGE
						.replace("%target-name%", args[0]);
				p.sendMessage(Color.color(message));
				return false;
			}
				
			String message = PluginBootstrap.getPluginConfig().CORRECT_USAGE_MESSAGE;
			p.sendMessage(Color.color(message));
			return false;
		}
		Bukkit.getLogger().log(Level.WARNING ,"This command can not be executed by console!");
		return false;
	}
}
