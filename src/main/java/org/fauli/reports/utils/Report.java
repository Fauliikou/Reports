package org.fauli.reports.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.fauli.reports.enums.ReportType;

public class Report {

    @Getter
    private ReportType type;

    @Getter
    @Setter
    private static boolean accepted;

    @Setter
    @Getter
    private static Player teamMember;

    public Report(ReportType type, Player teamMember) {
        this.type = type;
        this.accepted = false;
        this.teamMember = teamMember;
    }
}
