package org.fauli.reports.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.fauli.reports.Main;
import org.fauli.reports.common.model.Report;
import org.fauli.reports.common.util.ReportService;
import org.jetbrains.annotations.NotNull;

public class ReportChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        ReportService reportService = Main.getInstance().reportService;
        Report report = reportService.getReports().get(player.getUniqueId());

        if (report == null) {
            player.sendMessage("§cYou do not have an active report.");
            return false;
        }

        if (args.length == 0) {
            player.sendMessage("§cPlease provide a message to send.");
            return false;
        }

        String message = String.join(" ", args);
        report.getStaffMember().sendMessage("§8[§5Report§8] §5" + player.getName() + "§8: §7" + message);
        player.sendMessage("§8[§5Report§8] §7" + message);

        return false;
    }
}
