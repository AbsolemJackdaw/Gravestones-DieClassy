package net.subaraki.gravestone.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.common.network.PacketSyncModelToClient;

public class PlayerGraveData implements IExtendedEntityProperties {

	EntityPlayer player;
	public static final String PROPS = "graveData";

	private int graveID;

	public PlayerGraveData(EntityPlayer player) {
		this.player = player;
	}

	public static final void register(EntityPlayer player) {

		player.registerExtendedProperties(PROPS, new PlayerGraveData(player));
	}

	public static final PlayerGraveData get(EntityPlayer p) {

		return (PlayerGraveData) p.getExtendedProperties(PROPS);
	}

	public int getGraveModel(){
		return graveID;
	}

	public void setGraveModel(int i){
		graveID = i;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		compound.setInteger("grave_ID", graveID);
		
		if(this.player != null && player.worldObj.isRemote)
			GraveStones.printDebugMessage("C save " + graveID + " ");
		if(this.player != null && !player.worldObj.isRemote)
			GraveStones.printDebugMessage("S save " + graveID + " ");
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		graveID = compound.getInteger("grave_ID");
		
		if(this.player != null && player.worldObj.isRemote)
			GraveStones.printDebugMessage("C load " + graveID + " ");
		if(this.player != null && !player.worldObj.isRemote)
			GraveStones.printDebugMessage("S load " + graveID + " ");
		
	}

	@Override
	public void init(Entity entity, World world) {

	}

	/**Sometimes, the client is out of sync. calling this will fix that*/
	public void sync(){
		if(player != null)
			if(player instanceof EntityPlayerMP)
				GraveStones.instance.network.sendTo(new PacketSyncModelToClient(graveID), (EntityPlayerMP) player);
	}
}
