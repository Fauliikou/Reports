package org.fauli.reports.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.fauli.reports.enums.MessageEnum;
import org.fauli.reports.enums.ReportType;
import org.fauli.reports.utils.InventoryUtils.CustomItem;
import org.fauli.reports.utils.InventoryUtils.InventoryManager;
import org.fauli.reports.utils.InventoryUtils.ItemBuilder;
import org.fauli.reports.utils.Report;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class ReportCommand implements CommandExecutor {

    public static HashMap<Player, Report> reportPlayers = new HashMap<>();


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageEnum.ERROR_NO_PERMISSION.getMessage());
        }

        Player player = (Player) sender;

        if (reportPlayers.containsKey(player)) {
            player.sendMessage(MessageEnum.ERROR_ALREADY_OPENED_REPORT.getMessage());
            return false;
        }

        Component title = MiniMessage.miniMessage().deserialize("<b><gradient:#6ADDA6:#A8B47B>Ｒᴇᴘᴏʀᴛѕ</gradient></b>");
        Component questionTitle = MiniMessage.miniMessage().deserialize("<b><gradient:#6ADDA6:#A8B47B>Ｑᴜᴇѕᴛɪᴏɴ</gradient></b>");
        Component bugTitle = MiniMessage.miniMessage().deserialize("<b><gradient:#6ADDA6:#A8B47B>Ｂᴜɢ</gradient></b>");
        Component playerReportTitle = MiniMessage.miniMessage().deserialize("<b><gradient:#6ADDA6:#A8B47B>Ｐʟᴀʏᴇʀ</gradient></b>");

        Component questionLore = MiniMessage.miniMessage().deserialize("<b><gradient:#6ADDA6:#A8B47B>Ask a Question!</gradient></b>");
        Component bugLore = MiniMessage.miniMessage().deserialize("<b><gradient:#6ADDA6:#A8B47B>Report a bug!</gradient></b>");
        Component playerReportLore = MiniMessage.miniMessage().deserialize("<b><gradient:#6ADDA6:#A8B47B>Report a Player!</gradient></b>");

        InventoryManager inventoryManager = new InventoryManager(player, 9 * 3, title, true, true);

        inventoryManager.setItem(new CustomItem(11, new ItemBuilder(Material.OAK_SIGN).name(questionTitle).lore(List.of(questionLore)).build()) {
            @Override
            public void onClick(InventoryClickEvent event) {
                reportPlayers.put(player, new Report(ReportType.QUESTION, null));
                player.sendMessage(MessageEnum.MESSAGE_REPORT_OPENED.getMessage());
                player.closeInventory();
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (!all.hasPermission("Reports.ViewReports")) {
                        return;
                    }
                    all.sendMessage(MiniMessage.miniMessage().deserialize("<b><gradient:#8918A1:#526DA2>There is a new Report!</gradient></b>"));
                }
            }
        });
        inventoryManager.setItem(new CustomItem(13, new ItemBuilder(Material.LAVA_BUCKET).lore(List.of(bugLore)).name(bugTitle).build()) {
            @Override
            public void onClick(InventoryClickEvent event) {
                reportPlayers.put(player, new Report(ReportType.BUG, null));
                player.sendMessage(MessageEnum.MESSAGE_REPORT_OPENED.getMessage());
                player.closeInventory();
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (!all.hasPermission("Reports.ViewReports")) {
                        return;
                    }
                    all.sendMessage(MiniMessage.miniMessage().deserialize("<b><gradient:#8918A1:#526DA2>There is a new Report!</gradient></b>"));
                }
            }
        });
        inventoryManager.setItem(new CustomItem(15, new ItemBuilder(Material.IRON_SWORD).name(playerReportTitle).lore(List.of(playerReportLore)).build()) {
            @Override
            public void onClick(InventoryClickEvent event) {
                reportPlayers.put(player, new Report(ReportType.PLAYER, null));
                player.sendMessage(MessageEnum.MESSAGE_REPORT_OPENED.getMessage());
                player.closeInventory();
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (!all.hasPermission("Reports.ViewReports")) {
                        return;
                    }
                    all.sendMessage(MiniMessage.miniMessage().deserialize("<b><gradient:#8918A1:#526DA2>There is a new Report!</gradient></b>"));
                }
            }
        });
        return true;
    }
}