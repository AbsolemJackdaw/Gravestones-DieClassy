package net.subaraki.gravestone.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSwitchSlotLayout implements IMessage{

	/**
	 * Location of the tile entity that needs to be accessed. 
	 */
	public int x,y,z;

	/**
	 * The grave inventory layout determined by this enum
	 */
	public int graveSlotType;

	/**Placeholder. Do not use to send the packet !*/
	public PacketSwitchSlotLayout() {

	}

	/**
	 *@param x,y,z : Location of the tile entity that needs to be accessed.  
	 *@param eg : The grave inventory layout determined by this enum
	 */
	public PacketSwitchSlotLayout(int x, int y, int z, int slotType) {

		this.x = x;
		this.y = y;
		this.z = z;
		this.graveSlotType = slotType;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();

		graveSlotType = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(graveSlotType);
	}


	public static class PacketSwitchSlotLayoutHandler implements IMessageHandler<PacketSwitchSlotLayout, IMessage>{

		@Override
		public IMessage onMessage(PacketSwitchSlotLayout message, MessageContext ctx) {

			World world = ctx.getServerHandler().playerEntity.worldObj;
			TileEntity tile = world.getTileEntity(message.x, message.y, message.z);

			if(tile != null && tile instanceof TileEntityGravestone){

				TileEntityGravestone grave = (TileEntityGravestone)tile;
				grave.updateSlotContents(message.graveSlotType);
				grave.tab = message.graveSlotType;
			}else
				GraveStones.printDebugMessage("Tile Entity did not exist ! Could not acces inventory" );

			return null;
		}


	}

}
