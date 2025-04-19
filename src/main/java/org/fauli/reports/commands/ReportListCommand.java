package org.fauli.reports.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.fauli.reports.Main;
import org.fauli.reports.common.util.ReportService;
import org.jetbrains.annotations.NotNull;

public class ReportListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("reports.view")) {
            player.sendMessage("§cYou do not have permission to view the report list.");
            return false;
        }

        String header = "§8=====[§5 Report List §8]=====";
        player.sendMessage(header);

        ReportService reportService = Main.getInstance().reportService;

        if (reportService.getReports().isEmpty()) {
            player.sendMessage("§7No reports available.");
        } else {
            reportService.getReports().forEach((creator, report) -> {

                String accepted = !report.isAccepted() ? "§cnot accepted" : "§aaccepted";

                String reportInfo = "§7Report from §5" + creator + " §7of type §5" + report.getReportType().getName() + " §7is " + accepted;
            });
        }

        return false;
    }
}
