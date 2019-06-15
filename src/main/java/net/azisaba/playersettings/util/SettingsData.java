package net.azisaba.playersettings.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SettingsData {

	private final UUID uuid;

	private YamlConfiguration conf = null;
	private File file = null;

	protected void load() {
		File folder = SettingsManager.getDataFolder();
		file = new File(folder, uuid.toString() + ".yml");

		conf = YamlConfiguration.loadConfiguration(file);
	}

	public Object getObject(@NonNull String key) {
		errorIfNotCorrectKey(key);
		return conf.get(key, null);
	}

	public boolean getBoolean(@NonNull String key) {
		errorIfNotCorrectKey(key);
		return conf.getBoolean(key, false);
	}

	public String getString(@NonNull String key) {
		errorIfNotCorrectKey(key);
		return conf.getString(key, null);
	}

	public List<String> getStringList(@NonNull String key) {
		errorIfNotCorrectKey(key);

		if (!conf.isSet(key)) {
			return null;
		}

		return conf.getStringList(key);
	}

	public int getInt(@NonNull String key) {
		errorIfNotCorrectKey(key);
		return conf.getInt(key, -1);
	}

	public double getDouble(@NonNull String key) {
		errorIfNotCorrectKey(key);
		return conf.getDouble(key, -1d);
	}

	public long getLong(@NonNull String key) {
		errorIfNotCorrectKey(key);
		return conf.getLong(key, -1L);
	}

	public ItemStack getItemStack(@NonNull String key) {
		errorIfNotCorrectKey(key);
		return conf.getItemStack(key, null);
	}

	public boolean isSet(@NonNull String key) {
		errorIfNotCorrectKey(key);
		return conf.isSet(key);
	}

	public void set(@NonNull String key, Object obj) {
		errorIfNotCorrectKey(key);
		conf.set(key, obj);
	}

	public void save() {
		try {
			conf.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void errorIfNotCorrectKey(String key) {
		if (!Pattern.matches("^[0-9a-zA-Z\\.]+$", key)) {
			throw new IllegalArgumentException("You can only use Alphabet, Number and dot");
		}
	}
}
