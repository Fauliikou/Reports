package org.fauli.reports.commands.Team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import org.fauli.reports.commands.ReportCommand;
import org.fauli.reports.enums.MessageEnum;
import org.fauli.reports.utils.Report;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AcceptReportCommand implements CommandExecutor, TabCompleter {
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

        if (args.length != 1) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<b><color:red>Please enter the player name!.</color></b>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !ReportCommand.reportPlayers.containsKey(target)) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("\"<b><color:red>This Player doesnt have an open report!</color></b>\""));
            return false;
        }

        Report report = ReportCommand.reportPlayers.get(target);

        if (report.isAccepted()) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<b><color:red>This Report is already accepted!</color></b>"));
            return false;
        }

        report.setAccepted(true);
        report.setTeamMember(player);

        player.sendMessage("§5§lYou accepted the Report of: " + player.getName());
        target.sendMessage("§5§lYour Report was accepted by: " + Report.getTeamMember().getName());

        for (Player all : Bukkit.getOnlinePlayers()) {
            if (!all.hasPermission("Reports.ViewReports")) {
                return false;
            }

            all.sendMessage("§5§l" + player.getName() + " accepted the report of " + target.getName());
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<Player, Report> entry : ReportCommand.reportPlayers.entrySet()) {
            Player player = entry.getKey();
            list.add(player.getName());
        }
        return list;
    }
}
