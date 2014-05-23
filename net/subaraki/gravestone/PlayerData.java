package net.subaraki.gravestone;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerData implements IExtendedEntityProperties {

	EntityPlayer player;
	public static final String PROPS = "graveData";

	private int graveID;

	public PlayerData(EntityPlayer player) {
		this.player = player;
	}

	public static final void register(EntityPlayer player) {

		player.registerExtendedProperties(PROPS, new PlayerData(player));
	}

	public static final PlayerData get(EntityPlayer p) {

		return (PlayerData) p.getExtendedProperties(PROPS);
	}

	public int getGraveModel(){
		return graveID;
	}

	public void setGraveModel(int i){
		graveID = i;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger("graveid", graveID);
		compound.setTag(PROPS, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(PROPS);
		graveID = properties.getInteger("graveid");
	}

	@Override
	public void init(Entity entity, World world) {

	}

}
