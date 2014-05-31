package net.subaraki.gravestone.packets;

import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;

public class ClientPacket extends ServerPacket {


	@SubscribeEvent
	public void onClientPacket(ClientCustomPacketEvent event) {

		if(!event.packet.channel().equals("gravestone")) {
			return;
		}

		ByteBufInputStream dis = new ByteBufInputStream(event.packet.payload());
		event.packet.payload();

		try {
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
