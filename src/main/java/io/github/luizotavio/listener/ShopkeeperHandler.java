package io.github.luizotavio.listener;

import io.github.luizotavio.util.Shopkeeper;
import net.minecraft.server.v1_8_R3.Entity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ShopkeeperHandler implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onVillagerInteract(PlayerInteractAtEntityEvent event) {
        Entity entity = ((CraftEntity) event.getRightClicked()).getHandle();

        if (!(entity instanceof Shopkeeper)) return;

        ((Shopkeeper) entity).getConsumer().accept(event.getPlayer());

        event.setCancelled(true);
    }

}
