package me.nwse.Compass.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {

	//Messages
	public final String TARGET_NOT_SPECIFIED_MESSAGE;
	public final String TARGET_OFFLINE_MESSAGE;
	public final String TARGET_FOUND_IN_ANOTHER_WORLD_MESSAGE;
	public final String TARGET_FOUND_IN_OVERWORLD_MESSAGE;
	public final String PLAYER_HAS_COMPASS_ALREADY_MESSAGE;
	public final String COMPASS_GIVEN_SUCCESSFULLY_MESSAGE;
	public final String CORRECT_USAGE_MESSAGE;
	public final String TARGET_Y_LEVEL_MESSAGE;
	
	//Configuration
	public final int COMPASS_UPDATE_INTERVAL;
	public final boolean COMPASS_SHOULD_WORK_IN_NETHER;
	public final boolean COMPASS_SHOULD_SHOW_Y_LEVEL;
	public final boolean COMPASS_SHOULD_UPDATE_ITSELF;
	
	public Config(Plugin plugin) {
		
		FileConfiguration cfg = plugin.getConfig();
		
		//Messages
		TARGET_NOT_SPECIFIED_MESSAGE = cfg.getString("messages.target-not-specified");
		TARGET_OFFLINE_MESSAGE = cfg.getString("messages.target-offline");
		TARGET_FOUND_IN_ANOTHER_WORLD_MESSAGE = cfg.getString("messages.target-found-in-another-world");
		TARGET_FOUND_IN_OVERWORLD_MESSAGE = cfg.getString("messages.target-found-in-overworld");
		PLAYER_HAS_COMPASS_ALREADY_MESSAGE = cfg.getString("messages.player-has-compass-already");
		COMPASS_GIVEN_SUCCESSFULLY_MESSAGE = cfg.getString("messages.compass-given-successfully");
		CORRECT_USAGE_MESSAGE = cfg.getString("messages.correct-usage");
		TARGET_Y_LEVEL_MESSAGE = cfg.getString("messages.target-y-level");
		
		//Configuration
		COMPASS_UPDATE_INTERVAL = cfg.getInt("config.compass-update-interval");
		COMPASS_SHOULD_WORK_IN_NETHER = cfg.getBoolean("config.compass-should-work-in-nether");
		COMPASS_SHOULD_SHOW_Y_LEVEL = cfg.getBoolean("config.compass-should-show-y-level");
		COMPASS_SHOULD_UPDATE_ITSELF = cfg.getBoolean("config.compass-should-update-itself");

	}
	
}
