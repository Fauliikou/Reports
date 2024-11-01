package org.fauli.reports;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.fauli.reports.commands.ReportChatCommand;
import org.fauli.reports.commands.ReportCommand;
import org.fauli.reports.commands.Team.AcceptReportCommand;
import org.fauli.reports.commands.Team.CloseReportCommand;
import org.fauli.reports.commands.Team.ViewReportCommand;
import org.fauli.reports.listener.QuitListener;


public final class Main extends JavaPlugin {

    @Getter
    public static Main instance;

    @Override
    public void onEnable() {

        instance = this;

        MiniMessage miniMessage = MiniMessage.miniMessage();

        String text = "<gradient:#3CEF7B:#CDF803>Reports </gradient>" + "<gradient:#EF3C3C:#F8B603>started!</gradient>";

        Component parsed = miniMessage.deserialize(text);

        Bukkit.getConsoleSender().sendMessage(parsed);

        this.getCommand("report").setExecutor(new ReportCommand());
        this.getCommand("reports").setExecutor(new ViewReportCommand());
        this.getCommand("reportchat").setExecutor(new ReportChatCommand());
        this.getCommand("Closereport").setExecutor(new CloseReportCommand());
        this.getCommand("acceptreport").setExecutor(new AcceptReportCommand());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new QuitListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
