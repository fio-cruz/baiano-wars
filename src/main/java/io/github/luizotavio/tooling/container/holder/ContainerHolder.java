package io.github.luizotavio.tooling.container.holder;

import io.github.luizotavio.tooling.container.Container;
import io.github.luizotavio.tooling.container.size.Size;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ContainerHolder implements InventoryHolder {

    private final Container container;
    private final Inventory inventory;

    public ContainerHolder(Container container) {
        final Size size = container.getSize();

        inventory = Bukkit.createInventory(this, size.getAmount(), container.getName());

        this.container = container;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public Container getContainer() {
        return container;
    }
}
