package net.subaraki.gravestone.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.gravestone.handler.PlayerGraveData;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncGraveModel implements IMessage {

	/**
	 * The tile meta the block has to be turned into
	 */
	public int meta;

	/**Placeholder. Do not use to send the packet !*/
	public PacketSyncGraveModel() {

	}
	
	/**
	 * @param meta : The tile meta the block has to be turned into
	 */
	public PacketSyncGraveModel(int meta) {

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
	
	public static class PacketSyncGraveModelHandler implements IMessageHandler<PacketSyncGraveModel, IMessage>{

		@Override
		public IMessage onMessage(PacketSyncGraveModel message, MessageContext ctx) {
			
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			PlayerGraveData pgd = PlayerGraveData.get(player);
			pgd.setGraveModel(message.meta);
			
			return null;
		}
		
		
		
	}

}
