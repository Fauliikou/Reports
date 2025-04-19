package org.fauli.reports.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.fauli.reports.Main;
import org.fauli.reports.common.model.Report;
import org.fauli.reports.common.util.ReportService;
import org.jetbrains.annotations.NotNull;

public class CloseReportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("reports.close")) {
            player.sendMessage("§cYou do not have permission to close reports.");
            return false;
        }

        ReportService reportService = Main.getInstance().reportService;

        if (reportService.getReports().isEmpty()) {
            player.sendMessage("§cThere are no reports to close.");
            return false;
        }

        Report report = reportService.reports.values().stream()
                .filter(r -> r.getStaffMember() == player)
                .findFirst()
                .orElse(null);

        if (report == null) {
            player.sendMessage("§cYou are not assigned to any report.");
            return false;
        }

        reportService.closeReport(report);
        player.sendMessage("§aReport closed successfully.");
        report.getCreator().sendMessage("§aYour report has been closed by §7" + player.getName());
        return false;
    }
}
