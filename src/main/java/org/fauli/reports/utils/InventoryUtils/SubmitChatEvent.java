package org.fauli.reports.utils.InventoryUtils;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class SubmitChatEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final String message;

    public SubmitChatEvent(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    public boolean isCancel() {
        return message.equalsIgnoreCase("cancel");
    }

    public String getMessage() {
        return message;
    }

    public Player getPlayer() {
        return player;
    }

    public void sendCancelMessage() {
        player.sendMessage("§cVorgang beendet.");
    }

    public UUID getPlayerData() {
        return player.getUniqueId();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}