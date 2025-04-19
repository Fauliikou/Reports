package org.fauli.reports.common.inventories;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.fauli.reports.Main;
import org.fauli.reports.common.model.Report;
import org.fauli.reports.common.util.ReportService;
import org.fauli.reports.common.util.invenotryUtil.CustomItem;
import org.fauli.reports.common.util.invenotryUtil.InventoryBuilder;
import org.fauli.reports.common.util.invenotryUtil.ItemBuilder;

import java.util.concurrent.atomic.AtomicInteger;

import static net.kyori.adventure.text.Component.text;

public class ReportInventory {

    public void open(Player player) {
        InventoryBuilder inventoryBuilder = new InventoryBuilder(text("Report"), 9*3, true);

        AtomicInteger i = new AtomicInteger(11);

        for (Report.ReportType reportType : Report.ReportType.values()) {
            int slot = i.get();
            inventoryBuilder.addItem(new CustomItem(slot, new ItemBuilder(reportType.getDisplayItem()).name(text(reportType.getName())).build()) {
                @Override
                public void onClick(InventoryClickEvent event) {
                    player.sendMessage(text("§5Report created§8: §7" + reportType.getName()));
                    Report report = new Report(player, null, reportType);
                    ReportService reportService = Main.getInstance().reportService;
                    reportService.createReport(player, report);
                }
            });
            i.getAndAdd(2);
        }

        inventoryBuilder.openInventoryForPlayer(player);
    }
}
