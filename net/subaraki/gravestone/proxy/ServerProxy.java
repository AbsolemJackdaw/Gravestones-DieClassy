package net.subaraki.gravestone.proxy;

import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.block.inventory.TileEntityGrave;
import net.subaraki.gravestone.packets.ServerPacket;

public class ServerProxy{

	public ServerProxy() {
	}

	public void registerRendering() {
		GraveStones.channel.register(new ServerPacket());
	}

	public void setCustomNameBoolean(TileEntityGrave te, boolean b) {
	}
}
