package net.azisaba.playersettings;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.azisaba.playersettings.listener.PlayerJoinLeaveListener;
import net.azisaba.playersettings.util.SettingsManager;

import lombok.Getter;

public class PlayerSettings extends JavaPlugin {

	@Getter
	private static PlayerSettings plugin;
	@Getter
	private SettingsManager manager;

	@Override
	public void onEnable() {
		plugin = this;
		manager = new SettingsManager(this);

		Bukkit.getPluginManager().registerEvents(new PlayerJoinLeaveListener(this), this);

		Bukkit.getLogger().info(getName() + " enabled.");
	}

	@Override
	public void onDisable() {

		manager.saveAll();

		Bukkit.getLogger().info(getName() + " disabled.");
	}
}
