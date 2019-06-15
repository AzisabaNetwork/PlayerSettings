package net.azisaba.playersettings.util;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import net.azisaba.playersettings.PlayerSettings;

import lombok.NonNull;

public class SettingsManager {

	private static File dataFolder = null;

	private HashMap<UUID, SettingsData> dataMap = new HashMap<>();

	public SettingsManager(@NonNull PlayerSettings plugin) {
		dataFolder = new File(plugin.getDataFolder(), "SettingsData");
	}

	public void loadData(@NonNull UUID uuid) {
		if (dataMap.containsKey(uuid)) {
			return;
		}

		SettingsData data = new SettingsData(uuid);
		data.load();

		dataMap.put(uuid, data);
	}

	public SettingsData getSettingsData(@NonNull UUID uuid) {
		loadData(uuid);
		return dataMap.get(uuid);
	}

	public void saveAndUnload(@NonNull UUID uuid) {
		if (!dataMap.containsKey(uuid)) {
			return;
		}

		SettingsData data = dataMap.get(uuid);
		data.save();

		dataMap.remove(uuid);
	}

	public SettingsData getSettingsData(@NonNull Player player) {
		return getSettingsData(player.getUniqueId());
	}

	protected static File getDataFolder() {
		return dataFolder;
	}
}
