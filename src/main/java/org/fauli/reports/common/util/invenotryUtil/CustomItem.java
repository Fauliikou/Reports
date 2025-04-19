package org.fauli.reports.common.util.invenotryUtil;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public abstract class CustomItem implements Listener {

    public final int slot;
    public final ItemStack itemStack;

    public CustomItem(int slot, ItemStack itemStack) {
        this.slot = slot;
        this.itemStack = itemStack;
    }

    public CustomItem(ItemStack itemStack) {
        this.slot = -1;
        this.itemStack = itemStack;
    }

    public abstract void onClick(InventoryClickEvent event);

    public void setDisplayName(String name) {
        if (itemStack != null) {
            itemStack.editMeta(meta -> {
                meta.setDisplayName(String.valueOf(Component.text(name).color(TextColor.fromHexString("#FFFFFF"))));
            });
        }
    }
}