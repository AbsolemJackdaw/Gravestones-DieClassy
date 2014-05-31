package net.subaraki.gravestone.packets;

import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.subaraki.gravestone.PlayerData;
import net.subaraki.gravestone.block.inventory.TileEntityGrave;
import net.subaraki.gravestone.block.inventory.TileEntityGrave.EnumGrave;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public class ServerPacket {

	public static final int SET_GRAVE_MODEL = 0;
	public static final int CHANGE_GRAVE = 1;

	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {

		if(!event.packet.channel().equals("gravestone")) {
			return;
		}

		EntityPlayerMP p = ((NetHandlerPlayServer) event.handler).playerEntity;
		ByteBufInputStream dis = new ByteBufInputStream(event.packet.payload());
		event.packet.payload();

		try {
			int guiID = dis.readInt();

			switch (guiID) {
			case SET_GRAVE_MODEL:
				int id = dis.readInt();
				PlayerData.get(p).setGraveModel(id);
				break;

			case CHANGE_GRAVE:
				int x = dis.readInt();
				int y = dis.readInt();
				int z = dis.readInt();

				TileEntity te =p.worldObj.getTileEntity(x, y, z);
				if(te instanceof TileEntityGrave){
					TileEntityGrave grave = (TileEntityGrave)te;

					int sw = dis.readInt();

					switch(sw){
					case 0:
						grave.changeGrave(EnumGrave.VANILLA);
						break;
					case 1:
						grave.changeGrave(EnumGrave.RPGI);
						break;
					case 2:
						grave.changeGrave(EnumGrave.TC);
						break;
					}
					grave.tab = sw;
				}
				break;

			default:
				break;
			}


			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
