package me.nwse.Compass;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.nwse.Compass.comnand.CompassCommand;
import me.nwse.Compass.listener.PlayerInteractListener;
import me.nwse.Compass.util.Config;
public class PluginBootstrap extends JavaPlugin {
	
	public static Config config;
	private static PluginBootstrap main;

	private Logger logger = Bukkit.getLogger();

	@Override
	public void onEnable() {
		logger.log(Level.INFO, "Successfully enabled plugin!");
		
		getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
		getCommand("kompas").setExecutor(new CompassCommand());
		saveDefaultConfig();
		
		main = this;
		config = new Config(this);
	}
	
	@Override
	public void onDisable() {
		logger.log(Level.INFO, "Successfully disabled plugin!");
	}

	public static PluginBootstrap getMain() {
		return main;
	}

	public static Config getPluginConfig() {
		return config;
	}
}
