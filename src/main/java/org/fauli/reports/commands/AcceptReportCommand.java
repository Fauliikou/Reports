package org.fauli.reports.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.fauli.reports.Main;
import org.fauli.reports.common.model.Report;
import org.fauli.reports.common.util.ReportService;
import org.jetbrains.annotations.NotNull;

public class AcceptReportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("reports.accept")) {
            player.sendMessage("§cYou do not have permission to accept reports.");
            return false;
        }

        if (args.length != 1) {
            player.sendMessage("§cUsage: /acceptreport <PlayerName>");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage("§cPlayer not found: " + args[0]);
            return false;
        }

        ReportService reportService = Main.getInstance().reportService;

        if (reportService.getReports().isEmpty()) {
            player.sendMessage("§cThere are no reports to accept.");
            return false;
        }

        if (!reportService.getReports().containsKey(target.getUniqueId())) {
            player.sendMessage("§cNo report found for player: " + target.getName());
            return false;
        }

        Report report = reportService.getReports().get(target.getUniqueId());
        if (report.isAccepted()) {
            player.sendMessage("§cReport already accepted.");
            return false;
        }

        report.setStaffMember(player);
        player.sendMessage("§aYou have accepted the report from " + target.getName());
        target.sendMessage("§aYour report has been accepted by " + player.getName());
        return false;
    }
}
