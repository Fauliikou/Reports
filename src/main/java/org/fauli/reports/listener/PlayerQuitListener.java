package org.fauli.reports.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.fauli.reports.Main;
import org.fauli.reports.common.model.Report;
import org.fauli.reports.common.util.ReportService;

public class PlayerQuitListener implements org.bukkit.event.Listener {

    @org.bukkit.event.EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        ReportService reportService = Main.getInstance().reportService;

        Report report = reportService.getReports().get(player.getUniqueId());

        if (reportService.getReports().containsKey(player.getUniqueId())) {
            Player staffMember = report.getStaffMember();

            if (staffMember != null) {
                staffMember.sendMessage("§cThe player you were reporting has left the server. The report has been closed.");
            }

            reportService.closeReport(report);
            return;
        }
    }
}
