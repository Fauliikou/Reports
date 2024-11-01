package org.fauli.reports.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public enum MessageEnum {
    MESSAGE_REPORT_OPENED(MiniMessage.miniMessage().deserialize("<b><gradient:#6ADDA6:#A8B47B>Sucessfully created a Report!</gradient></b>")),
    ERROR_ALREADY_OPENED_REPORT(MiniMessage.miniMessage().deserialize("<b><gradient:#6ADDA6:#A8B47B>You already opened a report!</gradient></b>")),
    REPORT_PREFIX(MiniMessage.miniMessage().deserialize("<b><gradient:#8918A1:#526DA2>Ｒᴇᴘᴏʀᴛ</gradient></b>")),
    ERROR_NO_PERMISSION(MiniMessage.miniMessage().deserialize("<b><gradient:#BF420E:#FA5D5D>Error not enough permissions</gradient></b>"));

    private final Component message;

    MessageEnum(Component message) {
        this.message = message;
    }

    public Component getMessage() {
        return message;
    }
}
