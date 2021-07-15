package io.github.luizotavio.util;

import io.github.luizotavio.util.type.BroadcastType;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Broadcast {

    public static Builder of(BroadcastType type) {
        return new Builder(type);
    }

    public static class Builder {
        private final BroadcastType type;

        private String[] message;
        private Player[] target;

        protected Builder(BroadcastType type) {
            this.type = type;
        }

        public Builder message(String... message) {
            this.message = message;
            return this;
        }

        public Builder target(Player... players) {
            this.target = players;
            return this;
        }

        public void build() {
            switch (type) {
                case ACTION:
                    final ChatComponentText text = new ChatComponentText(message[0]);

                    final PacketPlayOutChat chat = new PacketPlayOutChat(text, (byte) 2);

                    toPacket(chat, target);
                    break;
                case TITLE:
                    final ChatComponentText first = new ChatComponentText(message[0]);

                    final PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, first);

                    toPacket(title, target);

                    if (message.length == 2) {
                        final ChatComponentText second = new ChatComponentText(message[1]);

                        final PacketPlayOutTitle sub = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, second);

                        toPacket(sub, target);
                    }
            }
        }

        private void toPacket(Packet<?> packet, Player... players) {
            for (Player player : players) {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }
}

