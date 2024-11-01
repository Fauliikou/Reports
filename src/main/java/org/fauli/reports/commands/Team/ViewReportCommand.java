package org.fauli.reports.commands.Team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.fauli.reports.commands.ReportCommand;
import org.fauli.reports.enums.MessageEnum;
import org.fauli.reports.utils.Report;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ViewReportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageEnum.ERROR_NO_PERMISSION.getMessage());
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("Reports.ViewReports")) {
            player.sendMessage(MessageEnum.ERROR_NO_PERMISSION.getMessage());
            return false;
        }

        player.sendMessage(MiniMessage.miniMessage().deserialize("<b><gradient:#6ADDA6:#A8B47B>===== Reports =====</gradient></b>"));

        if (ReportCommand.reportPlayers.isEmpty()) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<b><color:red>No open reports at the moment.</color></b>"));
            return true;
        }

        for (Map.Entry<Player, Report> entry : ReportCommand.reportPlayers.entrySet()) {
            Player reporter = entry.getKey();
            Report report = entry.getValue();

            String statusSymbol = report.isAccepted() ? "✔" : "❌";
            Component reportMessage = MiniMessage.miniMessage().deserialize(
                    "<gray>Report from <aqua>" + reporter.getName() + "</aqua> - Type: <yellow>" + report.getType() +
                            "</yellow> - Status: " + statusSymbol + "</gray>"
            );

            player.sendMessage(reportMessage);
        }
        return true;
    }
}