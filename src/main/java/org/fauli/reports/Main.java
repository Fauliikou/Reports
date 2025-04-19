package org.fauli.reports;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.fauli.reports.commands.CloseReportCommand;
import org.fauli.reports.commands.ReportChatCommand;
import org.fauli.reports.commands.ReportCommand;
import org.fauli.reports.commands.ReportListCommand;
import org.fauli.reports.common.util.ReportService;

public final class Main extends JavaPlugin {

    @Getter
    public static Main instance;

    @Getter
    public ReportService reportService;

    @Override
    public void onEnable() {

        instance = this;

        reportService = new ReportService();

        PluginManager pluginManager = Bukkit.getPluginManager();

        getCommand("report").setExecutor(new ReportCommand());
        getCommand("reportlist").setExecutor(new ReportListCommand());
        getCommand("reportchat").setExecutor(new ReportChatCommand());
        getCommand("closereport").setExecutor(new CloseReportCommand());

        getSLF4JLogger().info("Reports plugin by Fauli started!");
    }

    @Override
    public void onDisable() {

    }
}