package me.nwse.Compass.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import me.nwse.Compass.PluginBootstrap;
import me.nwse.Compass.util.Color;
import me.nwse.Compass.util.Tracker;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class PlayerInteractListener implements Listener {
	
	PluginBootstrap plugin;
    public PlayerInteractListener(PluginBootstrap plugin){
        this.plugin = plugin;
    }

	@EventHandler(priority = EventPriority.MONITOR)
	public void onItemUse(PlayerInteractEvent e) {
		
		Player p = (Player) e.getPlayer();
		
		if (e.getHand() != EquipmentSlot.HAND) {
			return;
		}
		
		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		
		if (p.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
			
			if (!Tracker.Tracklist.containsKey(p.getName())) {
				
				String message = PluginBootstrap.getPluginConfig().TARGET_NOT_SPECIFIED_MESSAGE
						.replace("%target-name%", p.getName());
				p.sendMessage(Color.color(message));
				return;
			}
			
			Player t = Bukkit.getPlayer(Tracker.Tracklist.get(p.getName()));
			
			if (t == null) {
				
				@SuppressWarnings("null")
				String message = PluginBootstrap.getPluginConfig().TARGET_OFFLINE_MESSAGE
						.replace("%target-name%", t.getName());
				p.sendMessage(Color.color(message));
				return;
				
			}
			
			if (t.getWorld().getEnvironment() != World.Environment.NORMAL) {
				
				String message = PluginBootstrap.getPluginConfig().TARGET_FOUND_IN_ANOTHER_WORLD_MESSAGE
						.replace("%target-name%", t.getName())
						.replace("%target-world%", t.getWorld().getEnvironment().toString());
				p.sendMessage(Color.color(message));
				return;
			}
			
			Tracker.Tracklist.put(p.getName(), t.getName());
			p.setCompassTarget(t.getLocation());
			
			String message = PluginBootstrap.getPluginConfig().TARGET_FOUND_IN_OVERWORLD_MESSAGE
					.replace("%target-name%", t.getName())
					.replace("%target-world%", t.getWorld().getEnvironment().toString());
			p.sendMessage(Color.color(message));	
			
			Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
				
				@Override
				public void run() {
					
					if (PluginBootstrap.getPluginConfig().COMPASS_SHOULD_UPDATE_ITSELF == true) {
						
						if (p.getInventory().contains(Material.COMPASS)) {
							
							if (t.getWorld().getEnvironment() == World.Environment.NORMAL) {
								Tracker.Tracklist.put(p.getName(), t.getName());
								p.setCompassTarget(t.getLocation());
								
								if (PluginBootstrap.getPluginConfig().COMPASS_SHOULD_SHOW_Y_LEVEL == true) {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Color.color(PluginBootstrap.getPluginConfig().TARGET_Y_LEVEL_MESSAGE + "&a&l" + t.getLocation().getBlockY())
											.replace("%target-name%", t.getName())));
								}
								return;
								
							}
							return;
							
						}
						return;
						
					}

					if (p.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
						
						if (t.getWorld().getEnvironment() == World.Environment.NORMAL) {
							Tracker.Tracklist.put(p.getName(), t.getName());
							p.setCompassTarget(t.getLocation());
							
							if (PluginBootstrap.getPluginConfig().COMPASS_SHOULD_SHOW_Y_LEVEL == true) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Color.color(PluginBootstrap.getPluginConfig().TARGET_Y_LEVEL_MESSAGE + "&a&l" + t.getLocation().getBlockY())
										.replace("%target-name%", t.getName())));
							}
							return;
							
						}
						return;
					}
					return;
					
				}
			}, PluginBootstrap.getPluginConfig().COMPASS_UPDATE_INTERVAL, PluginBootstrap.getPluginConfig().COMPASS_UPDATE_INTERVAL);
		}
	}
}
