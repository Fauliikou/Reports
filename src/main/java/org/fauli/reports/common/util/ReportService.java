package org.fauli.reports.common.util;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.fauli.reports.Main;
import org.fauli.reports.common.model.Report;

import java.util.HashMap;
import java.util.UUID;

public class ReportService {

    @Getter
    public HashMap<Report, UUID> reports = new HashMap<>();

    public void createReport(Player creator, Report report) {
        reports.put(report, creator.getUniqueId());
        Main.getInstance().getSLF4JLogger().info("Report created by " + creator.getName() + ": " + report);
    }

    public void closeReport(Report report) {
        reports.remove(report);
        Main.getInstance().getSLF4JLogger().info("Report closed: " + report);
    }
}
