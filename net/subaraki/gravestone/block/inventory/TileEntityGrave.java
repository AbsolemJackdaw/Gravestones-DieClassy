package net.subaraki.gravestone.block.inventory;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class TileEntityGrave extends TileEntity implements IInventory
{

	/**all saved itemstacks
	 *
	 * 0-39  are vanilla
	 * 40-46 is rpg invententory
	 * 47-73 is tconstruct knapsack
	 * 74-77 is tconstruct armor
	 * */
	public ItemStack[] list = new ItemStack[128];

	/**slots in the container shown*/
	public ItemStack[] slots = new ItemStack[40];

	/**current tab displayed*/
	public int tab = 0;

	/**playername saved to nbt. used to reconstruct the stubplayer*/
	public String playername = "";

	public int modelType = 0;

	public float ModelRotation = 0;

	/**gets set temporarely. loaded back from a string*/
	public EntityPlayer entityPlayerStub;

	public String message1 = "";
	public String message2= "";

	public boolean customName = false;

	public boolean hasItems = false;

	public String locked = "";

	/**used in slot grave. used to prevent people from taking other items*/
	public boolean otherPlayerHasTakenItemStack = false;

	Random rand = new Random();

	public void setGraveData(String playername, int modelid){
		this.playername = playername;
		modelType = modelid;
	}


	@Override
	public int getSizeInventory()
	{
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return this.slots[par1];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack par2ItemStack)
	{
		int slotID = tab == 0 ? slot : tab == 1 ? slot + 40 : slot + 47;

		this.slots[slot] = par2ItemStack;
		this.list[slotID] = par2ItemStack;

		if ((par2ItemStack != null) && (par2ItemStack.stackSize > this.getInventoryStackLimit())) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int ammount)
	{
		int slotID = tab == 0 ? slot : tab == 1 ? slot + 40 : slot + 47;

		if (this.slots[slot] != null)
		{
			ItemStack itemstack;

			if (this.slots[slot].stackSize <= ammount)
			{
				itemstack = this.slots[slot];
				this.slots[slot] = null;
				this.list[slotID] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.slots[slot].splitStack(ammount);

				if (this.slots[slot].stackSize == 0)
				{
					this.slots[slot] = null;
					this.list[slotID] = null;
				}

				return itemstack;
			}
		}

		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		//		if (this.slots[par1] != null)
		//		{
		//			ItemStack itemstack = this.slots[par1];
		//			this.slots[par1] = null;
		//			return itemstack;
		//		}
		//		else
		//		{
		return null;
		//		}
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false :
			par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	public String getInvName()
	{
		return "Grave";
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	public boolean isInvNameLocalized()
	{
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		playername = nbt.getString("name");
		message1 = nbt.getString("message");
		message2 = nbt.getString("message2");
		modelType = nbt.getInteger("Meta");
		ModelRotation = nbt.getFloat("rotation");

		otherPlayerHasTakenItemStack = nbt.getBoolean("isLooted");

		NBTTagList tagList = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if ((slot >= 0) && (slot < slots.length)) {
				slots[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}

		NBTTagList tagList2 = nbt.getTagList("ListItems", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < tagList2.tagCount(); i++) {
			NBTTagCompound tag = tagList2.getCompoundTagAt(i);
			byte slot = tag.getByte("ListSlot");
			if ((slot >= 0) && (slot < list.length)) {
				list[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setString("name", playername);
		par1NBTTagCompound.setString("message", message1);
		par1NBTTagCompound.setString("message2", message2);
		par1NBTTagCompound.setInteger("Meta", modelType);
		par1NBTTagCompound.setFloat("rotation", ModelRotation);
		par1NBTTagCompound.setBoolean("isLooted", otherPlayerHasTakenItemStack);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.slots.length; ++i)
		{
			if (this.slots[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				slots[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		par1NBTTagCompound.setTag("Items", nbttaglist);


		NBTTagList nbttaglist2 = new NBTTagList();

		for (int i = 0; i < this.list.length; ++i)
		{
			if (this.list[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("ListSlot", (byte)i);
				list[i].writeToNBT(nbttagcompound1);
				nbttaglist2.appendTag(nbttagcompound1);
			}
		}
		par1NBTTagCompound.setTag("ListItems", nbttaglist2);
	}

	public void dropContents(World world, int x, int y, int z) {

		if (this != null) {
			for (int slotIndex = 0; slotIndex < this.list.length; slotIndex++) {
				ItemStack items = this.getStackInSlot(slotIndex);

				if (items != null) {
					float var10 = (rand.nextFloat() * 0.8F) + 0.1F;
					float var11 = (rand.nextFloat() * 0.8F) + 0.1F;
					EntityItem entityItem;

					for (float var12 = (rand.nextFloat() * 0.8F) + 0.1F; items.stackSize > 0; world.spawnEntityInWorld(entityItem)) {
						int var13 = rand.nextInt(21) + 10;

						if (var13 > items.stackSize) {
							var13 = items.stackSize;
						}

						items.stackSize -= var13;
						entityItem = new EntityItem(world, x + var10, y + var11, z + var12, new ItemStack(items.getItem(), var13, items.getItemDamage()));
						entityItem.motionX = rand.nextGaussian() * 0.05F;
						entityItem.motionY = rand.nextGaussian() * 0.25F;
						entityItem.motionZ = rand.nextGaussian() * 0.05F;

						if (items.hasTagCompound()) {
							entityItem.getEntityItem().setTagCompound((NBTTagCompound) items.getTagCompound().copy());
						}
					}
				}
			}
		}
	}

	public String setName(String name)
	{
		playername = name;
		return playername;
	}

	public Entity setPlayer(EntityPlayer player){
		entityPlayerStub = player;
		return player;
	}
	public void setDeathMessage(String message){
		message1 = message;
	}
	public void setDeathMessage2(String message){
		message2 = message;
	}
	public void setMeta(int i){
		modelType = i;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		for (ItemStack element : list) {
			if(element != null){
				//if al stacks are null, hasItems = false;
				hasItems = true;
				break;
			}else{
				hasItems = false;
			}
		}
	}

	@Override
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}

	@Override
	public String getInventoryName() {
		return "Grave";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}


	public void changeGrave(EnumGrave num){

		for(int i = 0; i < slots.length; i ++){
			slots[i] = null;
		}

		switch(num){
		case VANILLA:

			for(int i = 0; i < slots.length; i ++){
				slots[i] = list[i];
			}

			break;

		case RPGI:
			for(int i = 0; i < 7; i ++){
				slots[i] = list[i+40];
			}

			break;

		case TC:
			for(int i = 0; i < 27; i ++){
				slots[i] = list[i+47];
			}
			for(int i = 0; i < 4; i ++){
				slots[i] = list[i+74];
			}
			break;

		default:
			break;

		}

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	public enum EnumGrave{
		VANILLA,
		RPGI,
		TC
	}
}
