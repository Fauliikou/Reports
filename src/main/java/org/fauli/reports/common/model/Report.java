package org.fauli.reports.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class Report {

    private final Player creator;
    private Player staffMember;
    private final ReportType reportType;

    public boolean isAccepted() {
        return staffMember != null;
    }

    @AllArgsConstructor
    @Getter
    public enum ReportType {
        BUG("Bug"),
        QUESTION("Question"),
        PLAYER_REPORT("Player Report");

        private final String name;
    }
}
