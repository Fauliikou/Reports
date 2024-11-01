package org.fauli.reports.utils.InventoryUtils;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.fauli.reports.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryManager implements Listener {

    @Getter
    @Setter
    private Component name;
    @Getter
    @Setter
    private int size;
    @Getter
    @Setter
    private UUID uuid;
    @Getter
    @Setter
    public boolean canceled;
    @Getter
    @Setter
    private boolean fillRest;
    @Setter
    private Inventory inv;
    private final Map<Integer, CustomItem> customItems = new HashMap<>();

    public InventoryManager(Player player, int size, Component name, boolean canceled, boolean fillRest) {
        this.size = size;
        this.name = name;
        this.uuid = player.getUniqueId();
        this.canceled = canceled;
        this.fillRest = fillRest;
        this.inv = Bukkit.createInventory(null, size, name);

        if (this.fillRest) {
            for (int i = 0; i < this.size; i++) {
                if (inv.getItem(i) == null) {
                    inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).name(Component.text("")).build());
                }
            }
        }
        player.openInventory(inv);

        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    public InventoryManager(Player player, int size, Component name) {
        this(player, size, name, true, true);
    }

    public Inventory getInventory() {
        return inv;
    }

    public void setItem(CustomItem customItem) {
        inv.setItem(customItem.slot, customItem.itemStack);
        customItems.put(customItem.slot, customItem);
    }

    public void addItem(CustomItem customItem) {
        int emptySlot = inv.firstEmpty();
        if (emptySlot != -1) {
            inv.setItem(emptySlot, customItem.itemStack);
            customItems.put(emptySlot, customItem);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (!player.getUniqueId().equals(uuid)) return;

        int slot = event.getRawSlot();
        CustomItem customItem = customItems.get(slot);

        if (customItem != null) {
            customItem.onClick(event);
            event.setCancelled(true);
        }
    }
}