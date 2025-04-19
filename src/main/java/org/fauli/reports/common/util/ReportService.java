package org.fauli.reports.common.util;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.fauli.reports.Main;
import org.fauli.reports.common.model.Report;

import java.util.HashMap;
import java.util.UUID;

public class ReportService {

    @Getter
    public HashMap<UUID, Report> reports = new HashMap<>();

    public void createReport(Player creator, Report report) {
        reports.put(creator.getUniqueId(), report);
        Main.getInstance().getSLF4JLogger().info("Report created by " + creator.getName() + ": " + report);

        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.hasPermission("reports.view")) {
                all.sendMessage("§5Report created by§8: §7" + creator.getName() + " §5Type: §8" + report.getReportType().getName());
            }
        }
    }

    public void closeReport(Report report) {
        Player creator = report.getCreator();
        reports.remove(creator.getUniqueId());
        Main.getInstance().getSLF4JLogger().info("Report closed: " + report);

        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.hasPermission("reports.view")) {
                all.sendMessage("§5Report from§8: §7" + report.getCreator().getName() + " §5was closed by§8: §7" + report.getStaffMember().getName());
            }
        }
    }
}
