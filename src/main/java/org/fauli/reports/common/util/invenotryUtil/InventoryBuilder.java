package org.fauli.reports.common.util.invenotryUtil;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.fauli.reports.Main;

import java.util.ArrayList;
import java.util.List;

public class InventoryBuilder implements Listener {

    private final Inventory inventory;
    private final boolean fillRest;
    private final List<CustomItem> customItems;

    public InventoryBuilder(Component title, int size, boolean fillRest) {
        this.inventory = Bukkit.createInventory(null, size, title);
        this.fillRest = fillRest;
        this.customItems = new ArrayList<>();
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
        if (fillRest) {
            fillInventory();
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void openInventoryForPlayer(Player player) {
        player.openInventory(inventory);
    }

    private void fillInventory() {
        ItemStack fillStuff = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        fillStuff.editMeta(meta -> meta.setCustomModelData(10));
        inventory.setContents(new ItemStack[inventory.getSize()]);
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, fillStuff);
        }
    }

    public void addItem(CustomItem customItem) {
        if (customItem.slot >= 0) {
            inventory.setItem(customItem.slot, customItem.itemStack);
        } else {
            for (int i = 0; i < inventory.getSize(); i++) {
                if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) {
                    inventory.setItem(i, customItem.itemStack);
                    break;
                }
            }
        }
        customItems.add(customItem);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(inventory)) {
            event.setCancelled(true);
            for (CustomItem item : customItems) {
                if (event.getSlot() == item.slot) {
                    item.onClick(event);
                    break;
                }
            }
        }
    }
}