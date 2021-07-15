package io.github.luizotavio.util;

import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.function.Consumer;

public class Shopkeeper extends EntityVillager {

    public static Builder newBuilder() {
        return new Builder();
    }

    private Consumer<Player> consumer;

    private Shopkeeper(World world) {
        super(world);
    }

    public Consumer<Player> getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer<Player> consumer) {
        this.consumer = consumer;
    }

    public static class Builder {
        private Builder() {}

        private Location location;
        private Consumer<Player> interactEvent;

        public Builder location(Location location) {
            this.location = location;
            return this;
        }

        public Builder setInteractEvent(Consumer<Player> interactEvent) {
            this.interactEvent = interactEvent;
            return this;
        }

        public Shopkeeper summon() {
            WorldServer worldServer = ((CraftWorld) location.getWorld()).getHandle();

            Shopkeeper shopkeeper = new Shopkeeper(worldServer);
            Villager villager = (Villager) shopkeeper.getBukkitEntity();

            villager.setRemoveWhenFarAway(false);
            villager.setNoDamageTicks(Integer.MAX_VALUE);

            shopkeeper.noclip = true;

            NBTTagCompound nbt = new NBTTagCompound();
            shopkeeper.e(nbt);
            nbt.setBoolean("Silent", true);
            nbt.setBoolean("NoAI", true);
            shopkeeper.f(nbt);

            shopkeeper.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), 0);
            shopkeeper.setConsumer(interactEvent);

            worldServer.addEntity(shopkeeper, CreatureSpawnEvent.SpawnReason.CUSTOM);

            return shopkeeper;
        }
    }
}
