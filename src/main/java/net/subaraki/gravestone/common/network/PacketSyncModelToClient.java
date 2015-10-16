package net.subaraki.gravestone.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.handler.PlayerGraveData;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncModelToClient implements IMessage{

	public int meta;
	
	public PacketSyncModelToClient() {
	}
	
	public PacketSyncModelToClient(int meta) {
		this.meta = meta;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		meta = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(meta);
	}

	public static class PacketSyncModelToClientHandler implements IMessageHandler<PacketSyncModelToClient, IMessage>{

		@Override
		public IMessage onMessage(PacketSyncModelToClient message, MessageContext ctx) {
			EntityPlayer player = GraveStones.proxy.getClientPlayer();
			PlayerGraveData pgd = PlayerGraveData.get(player);
			pgd.setGraveModel(message.meta);
			
			return null;
		}
		
		
	}
}
