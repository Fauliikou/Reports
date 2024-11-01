package org.fauli.reports.listener;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.fauli.reports.commands.ReportCommand;
import org.fauli.reports.utils.Report;

import java.util.Map;

public class QuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        for (Map.Entry<Player, Report> entry : ReportCommand.reportPlayers.entrySet()) {
            Player reporter = entry.getKey();
            Player teamMember = Report.getTeamMember();

            if (reporter.equals(player)) {
                ReportCommand.reportPlayers.remove(player);
                teamMember.sendMessage(MiniMessage.miniMessage().deserialize("<b><color:red>Ticket was closed because the Player left!.</color></b>"));
                Report.setTeamMember(null);
            }
        }
    }
}
