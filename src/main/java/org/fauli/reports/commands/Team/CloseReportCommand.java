package org.fauli.reports.commands.Team;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.fauli.reports.commands.ReportCommand;
import org.fauli.reports.enums.MessageEnum;
import org.fauli.reports.utils.Report;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class CloseReportCommand implements CommandExecutor {
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

        if (Report.getTeamMember() != player) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<b><color:red>You aren't in a Report.</color></b>"));
            return false;
        }

        Report report = ReportCommand.reportPlayers.get(player);

        if (report == null) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<b><color:red>No report found to close!</color></b>"));
            return false;
        }

        ReportCommand.reportPlayers.remove(player);
        Report.setTeamMember(null);

        player.sendMessage(MiniMessage.miniMessage().deserialize("<b><gradient:#6ADDA6:#A8B47B>Report closed Successfully!</gradient></b>"));
        for (Map.Entry<Player, Report> entry : ReportCommand.reportPlayers.entrySet()) {
            Player reporter = entry.getKey();

            reporter.sendMessage(MiniMessage.miniMessage().deserialize("<b><gradient:#6ADDA6:#A8B47B>Report closed Successfully!</gradient></b>"));
        }

        return true;
    }
}
