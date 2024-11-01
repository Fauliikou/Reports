package org.fauli.reports.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.fauli.reports.enums.MessageEnum;
import org.fauli.reports.utils.Report;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ReportChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageEnum.ERROR_NO_PERMISSION.getMessage());
            return false;
        }

        Player player = (Player) sender;

        if (!Report.isAccepted()) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<b><color:red>You aren't in a Report!</color></b>"));
            return false;
        }

        if (Report.getTeamMember() == null) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<b><color:red>Wait until your Report is Accepted!</color></b>"));
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<b><color:red>You have to add a message!</color></b>"));
            return false;
        }

        String message = String.join(" ", args);

        for (Map.Entry<Player, Report> entry : ReportCommand.reportPlayers.entrySet()) {
            Player reporter = entry.getKey();

            Report.getTeamMember().sendMessage("§l§5Ｒᴇᴘᴏʀᴛ §8| §5" + player.getName() + " " + message);
            reporter.sendMessage("§l§5Ｒᴇᴘᴏʀᴛ §8| §5" + player.getName() + " " + message);
        }

        return true;
    }
}