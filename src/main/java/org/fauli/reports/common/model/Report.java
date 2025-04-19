package org.fauli.reports.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Data
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
        BUG("Bug", Material.LAVA_BUCKET),
        QUESTION("Question", Material.OAK_SIGN),
        PLAYER_REPORT("Player Report", Material.IRON_SWORD);

        private final String name;
        private final Material displayItem;
    }
}
