package net.azisaba.playersettings.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.azisaba.playersettings.PlayerSettings;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerJoinLeaveListener implements Listener {

	private final PlayerSettings plugin;

	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		plugin.getManager().loadData(p.getUniqueId());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		plugin.getManager().saveAndUnload(p.getUniqueId());
	}
}
