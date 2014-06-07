package net.subaraki.gravestone;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.subaraki.gravestone.block.inventory.TileEntityGrave;
import net.subaraki.gravestone.config.ConfigGraveStones;
import net.subaraki.gravestone.proxy.ClientProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EventHandler {


	public EventHandler() {

		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);

	}

	@SubscribeEvent
	public void onEntityConstruction(EntityConstructing event) {
		if ((event.entity instanceof EntityPlayer)&& (PlayerData.get((EntityPlayer) event.entity) == null)) {
			PlayerData.register((EntityPlayer) event.entity);
		}
	}


	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void keyHandling(KeyInputEvent evt){

		if(ConfigGraveStones.enableGravesTroughKey)
			if(ClientProxy.keyGui.isPressed()){
				if(Minecraft.getMinecraft().currentScreen == null){
					EntityPlayer p = Minecraft.getMinecraft().thePlayer;

					p.openGui(GraveStones.instance, 1, Minecraft.getMinecraft().theWorld,(int)p.posX, (int)p.posY, (int)p.posZ);
				}
			}
	}

	@SubscribeEvent
	public void onClone(Clone evt){
		NBTTagCompound tag = new NBTTagCompound();

		PlayerData.get(evt.entityPlayer).saveNBTData(tag);

		PlayerData.get(evt.original).loadNBTData(tag);
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onDeathEvent(LivingDeathEvent evt){

		if(evt.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)evt.entityLiving;

			//dont place a grave when they should keep the contents
			if (player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
				return;
			}

			int x = MathHelper.floor_double(player.posX),
					y = MathHelper.floor_double(player.posY),
					z = MathHelper.floor_double(player.posZ);

			/**decreases Y. stops when the found block is not air*/
			if(player.worldObj.isAirBlock(x, y, z)){
				if(y < 0){
					return;
				}

				while(player.worldObj.isAirBlock(x, y, z)){
					y--;
				}
			}

			int X = 100, Z = 100;
			int x2=0, z2=0, dx = 0, dz = -1;
			int t = Math.max(X,Z);
			int maxI = t*t;
			boolean flag = false;

			for (int i=0; i < maxI; i++){

				if (((-X/2) <= x2) && (x2 <= (X/2)) && ((-Z/2) <= z2) && (z2 <= (Z/2))) {

					int y2 = 0;
					while(!player.worldObj.getBlock(x+x2, y+1 + y2, z+z2).getMaterial().equals(Material.air)){
						y2++;
					}

					if(player.worldObj.getBlock(x+x2, y+y2, z+z2).getMaterial().isSolid()){
						if(player.worldObj.getBlock(x+x2, y+1+y2, z+z2).getMaterial().equals(Material.air)){
							FMLLog.getLogger().info("Potential grave at " + (x+x2) +" " + (y+y2)+ " "+ (z+z2));

							placeGrave(player, x+x2, y+y2, z+z2);

							flag = true;
							break;
						}
					}
				}

				if( (x2 == z2) || ((x2 < 0) && (x2 == -z2)) || ((x2 > 0) && (x2 == (1-z2)))) {
					t=dx; dx=-dz; dz=t;
				}
				x2+=dx; z2+=dz;
			}

			if(!flag){
				TileEntityGrave te = new TileEntityGrave();
				te.modelType = PlayerData.get(player).getGraveModel();

				player.worldObj.setTileEntity(x, y, z, te);
				player.worldObj.markBlockForUpdate(x, y, z);
			}
		}
	}


	private void placeGrave(EntityPlayer player, int x, int y, int z){
		player.worldObj.setBlock(x, y+1, z, GraveStones.graveStone);


		TileEntityGrave te = new TileEntityGrave();
		InventoryPlayer inv = player.inventory;

		int graveID = PlayerData.get(player).getGraveModel();
		int max = 9;
		
		if(!ConfigGraveStones.enableGravesTroughKey)
			graveID = ConfigGraveStones.graveOrder[Math.max((player.experienceLevel / ConfigGraveStones.graveLevel), max)];

		te.setGraveData(player.getCommandSenderName(), graveID);


		for(int slot = 0; slot <inv.getSizeInventory(); slot++)
		{
			ItemStack is = inv.getStackInSlot(slot);
			if((is != null) && (slot < te.getSizeInventory()))
			{
				te.tab = 0;
				te.setInventorySlotContents(slot, is);
				inv.setInventorySlotContents(slot, null);
			}
		}

		addOtherInventory(te, player);

		player.worldObj.setTileEntity(x, y+1, z, te);
		player.worldObj.markBlockForUpdate(x, y+1, z);
		te.markDirty();
	}


	private void addOtherInventory(TileEntityGrave te, EntityPlayer p){

		if(GraveStones.hasRpgI){
			try {

				Class<?> clazz = Class.forName("rpgInventory.gui.rpginv.PlayerRpgInventory");
				Method m = clazz.getDeclaredMethod("get", EntityPlayer.class);
				Object result = m.invoke(null, p);

				IInventory inv = (IInventory)result;

				FMLLog.getLogger().info("Dumping all Rpg Inventory content into grave");

				for(int i = 0; i < 7; i ++){
					ItemStack is = inv.getStackInSlot(i);
					te.list[i + 40] = is;
					inv.setInventorySlotContents(i, null);
				}
			}catch (Exception e) {
				FMLLog.getLogger().info("Error Encountered trying to acces RpgInventory Inventory Content. Please report to mod author");
			}
		}

		if(GraveStones.hasTC){
			try {
				Class<?> clazz = Class.forName("tconstruct.util.player.TPlayerStats");
				Method m = clazz.getDeclaredMethod("get", EntityPlayer.class);
				Object result = m.invoke(null, p);
				Field f = clazz.getDeclaredField("knapsack");
				IInventory sack = (IInventory)f.get(result);

				FMLLog.getLogger().info("Dumping all Tinkers Contruct Knapsack into grave");

				for(int i = 0; i < 27; i ++){
					ItemStack is = sack.getStackInSlot(i);
					te.list[i + 47] = is;
					sack.setInventorySlotContents(i, null);
				}
			} catch (Exception e) {
				FMLLog.getLogger().info("Error Encountered trying to acces Tinkers Construct Inventory Content. Please report to mod author");
			}

			try {
				Class<?> clazz = Class.forName("tconstruct.util.player.TPlayerStats");
				Method m = clazz.getDeclaredMethod("get", EntityPlayer.class);
				Object result = m.invoke(null, p);
				Field f = clazz.getDeclaredField("armor");
				IInventory inv = (IInventory)f.get(result);

				FMLLog.getLogger().info("Dumping all Tinkers Contruct Armor into grave");

				for(int i = 0; i < 4; i ++){
					ItemStack is = inv.getStackInSlot(i);
					te.list[i + 74] = is;
					inv.setInventorySlotContents(i, null);
				}
			} catch (Exception e) {
				FMLLog.getLogger().info("Error Encountered trying to acces Tinkers Construct Inventory Content. Please report to mod author");
			}
		}
		
		if(GraveStones.hasBaubel){
			try {

				Class<?> clazz = Class.forName("baubles.common.lib.PlayerHandler");
				Method m = clazz.getDeclaredMethod("getPlayerBaubles", EntityPlayer.class);
				Object result = m.invoke(null, p);

				IInventory inv = (IInventory)result;

				FMLLog.getLogger().info("Dumping all Baubles content into grave");

				for(int i = 0; i < 4; i ++){
					ItemStack is = inv.getStackInSlot(i);
					te.list[i + 78] = is;
					inv.setInventorySlotContents(i, null);
				}
			}catch (Exception e) {
				FMLLog.getLogger().info("Error Encountered trying to acces Baubles' Inventory Content. Please report to mod author");
			}
		}
		
		if(GraveStones.hasGalacti){
			try {

				Class<?> clazz = Class.forName("baubles.common.lib.PlayerHandler");
				Method m = clazz.getDeclaredMethod("getExtendedInventory", EntityPlayer.class);
				Object result = m.invoke(null, p);

				IInventory inv = (IInventory)result;

				FMLLog.getLogger().info("Dumping all GalacticCraft content into grave");

				for(int i = 0; i < 4; i ++){
					ItemStack is = inv.getStackInSlot(i);
					te.list[i + 82] = is;
					inv.setInventorySlotContents(i, null);
				}
			}catch (Exception e) {
				FMLLog.getLogger().info("Error Encountered trying to acces GalactiCraft's Inventory Content. Please report to mod author");
			}
		}
		
		if(GraveStones.hasMariCulture){
			try {

				Class<?> clazz = Class.forName("mariculture.magic.MirrorHelper");
				Method m = clazz.getDeclaredMethod("getInventory", EntityPlayer.class);
				Object result = m.invoke(null, p);

				ItemStack[] inv = (ItemStack[])result;

				FMLLog.getLogger().info("Dumping all Mariculture content into grave");

				for(int i = 0; i < 3; i ++){
					ItemStack is = inv[i];
					te.list[i + 88] = is;
				}
				
				ItemStack[] newstack = new ItemStack[3];
				
				//the save method is static
				//the save method only has to be called to save the new (empty) itemstack
				Method m2 = clazz.getDeclaredMethod("save", EntityPlayer.class, ItemStack[].class);
				Object saveEmptyArray = m.invoke(null, p, newstack);
				
			}catch (Exception e) {
				FMLLog.getLogger().info("Error Encountered trying to acces Mariculture's Inventory Content. Please report to mod author");
			}
		}
	}
}
