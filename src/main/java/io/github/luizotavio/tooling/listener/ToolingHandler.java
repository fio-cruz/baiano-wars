package io.github.luizotavio.tooling.listener;

import io.github.luizotavio.tooling.container.Container;
import io.github.luizotavio.tooling.container.holder.ContainerHolder;
import io.github.luizotavio.tooling.container.icon.Icon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;

public class ToolingHandler implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    private void onClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        final InventoryView inventoryView = player.getOpenInventory();

        final Inventory topInventory = inventoryView.getTopInventory();
        final InventoryHolder holder = topInventory.getHolder();
        final int slot = event.getRawSlot();

        if (!(holder instanceof ContainerHolder)) return;

        event.setCancelled(true);

        if (event.getClickedInventory() != topInventory) return;

        final ContainerHolder containerHolder = (ContainerHolder) holder;
        final Container container = containerHolder.getContainer();

        if (slot == -999 || slot >= container.getSize().getAmount()) return;

        final Icon icon = container.with(event.getRawSlot());

        if (icon != null && icon.getConsumer() != null) icon.getConsumer().accept(icon, event);

        container.update();
    }
}
