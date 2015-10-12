package net.subaraki.gravestone.common;

import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.packets.ServerPacket;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class ServerProxy{

	public ServerProxy() {
	}

	public void registerRendering() {
		GraveStones.channel.register(new ServerPacket());
	}

	public void setCustomNameBoolean(TileEntityGravestone te, boolean b) {
	}
}
