package net.subaraki.gravestone.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.client.ClientProxy;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GravestoneEventHandler {


	public GravestoneEventHandler() {

		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);

	}

	@SubscribeEvent
	public void onEntityJoinWorld (EntityJoinWorldEvent event) {

		if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote && PlayerGraveData.get(((EntityPlayer)event.entity))!=null)
			PlayerGraveData.get((EntityPlayer) event.entity).sync();
	}

	@SubscribeEvent
	public void onEntityConstruction(EntityConstructing event) {
		if ((event.entity instanceof EntityPlayer) && (PlayerGraveData.get((EntityPlayer) event.entity) == null)) {
			PlayerGraveData.register((EntityPlayer) event.entity);
		}
	}


	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void keyHandling(KeyInputEvent evt){

		if(ConfigHandler.enableGravesTroughKey)
			if(ClientProxy.keyGui.isPressed()){
				if(Minecraft.getMinecraft().currentScreen == null){
					EntityPlayer p = Minecraft.getMinecraft().thePlayer;

					p.openGui(GraveStones.instance, 1, Minecraft.getMinecraft().theWorld,(int)p.posX, (int)p.posY, (int)p.posZ);
				}
			}
	}

	@SubscribeEvent
	public void onCloneEvent(Clone event){
		PlayerGraveData dead = PlayerGraveData.get(event.original);
		PlayerGraveData clone = PlayerGraveData.get(event.entityPlayer);

		clone.setGraveModel(dead.getGraveModel());

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
			boolean liquid = false;

			for (int i=0; i < maxI; i++){

				if (((-X/2) <= x2) && (x2 <= (X/2)) && ((-Z/2) <= z2) && (z2 <= (Z/2))) {

					int y2 = 0;
					while(!player.worldObj.getBlock(x+x2, y+1 + y2, z+z2).getMaterial().equals(Material.air)){
						y2++;
					}

					if(player.worldObj.getBlock(x+x2, y+y2, z+z2).getMaterial().isLiquid()){
						GraveStones.printDebugMessage("You were standing in liquid !");
						y2--;
						liquid = true;
					}

					if(player.worldObj.getBlock(x+x2, y+y2, z+z2).getMaterial().isSolid()){
						if(player.worldObj.getBlock(x+x2, y+1+y2, z+z2).getMaterial().equals(Material.air) || liquid){
							GraveStones.printDebugMessage("Potential grave at " + (x+x2) +" " + (y+y2)+ " "+ (z+z2));

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

			if(!flag && liquid){

				int y2 =0;
				while(!player.worldObj.getBlock(x, y+1 +y2, z).getMaterial().equals(Material.air)){
					y2++;
				}

				if(player.worldObj.getBlock(x, y+y2, z).getMaterial().isLiquid()){
					if(player.worldObj.getBlock(x, y+1+y2, z).getMaterial().equals(Material.air))
						player.worldObj.setBlock(x, y+y2, z, Blocks.cobblestone);

					placeGrave(player, x, y+y2, z);
				}
			}
			else if(!flag){
				placeGrave(player, x, y, z);
			}
		}
	}


	private void placeGrave(EntityPlayer player, int x, int y, int z){

		player.worldObj.setBlock(x, y+1, z, GraveStones.graveStone);

		TileEntityGravestone te = new TileEntityGravestone();
		InventoryPlayer inv = player.inventory;

		int graveID = PlayerGraveData.get(player).getGraveModel();
		int max = 9;

		if(!ConfigHandler.enableGravesTroughKey)
			graveID = ConfigHandler.graveOrder[Math.min((player.experienceLevel / ConfigHandler.graveLevel), max)];

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


	private void addOtherInventory(TileEntityGravestone te, EntityPlayer player){

		if(GraveStones.hasRpgI){
			IInventory inv = accesInventoryContents(player, "get", "rpgInventory.gui.rpginv.PlayerRpgInventory", "Rpg Inventory");

			if(inv != null)
				for(int i = 0; i < 7; i ++){
					ItemStack is = inv.getStackInSlot(i);
					te.list[i + 40] = is;
					inv.setInventorySlotContents(i, null);
				}
			else
				GraveStones.printDebugMessage("GraveStones Mod couldn't connect to Rpg Inventory. Have these classes been modified ? Report to mod Author pleases.");

		}

		if(GraveStones.hasTiCo){
			IInventory sack = accesInventoryContents(player, "getKnapsackInventory ",  "tconstruct.armor.player.TPlayerStats", "Tinkers Construct");
			IInventory inv = accesInventoryContents(player, "getAccessoryInventory ",  "tconstruct.armor.player.TPlayerStats", "Tinkers Construct");

			if(sack != null)
				for(int i = 0; i < 27; i ++){
					ItemStack is = sack.getStackInSlot(i);
					te.list[i + 47] = is;
					sack.setInventorySlotContents(i, null);
				}
			else
				GraveStones.printDebugMessage("GraveStones Mod couldn't connect to Tinkers Construct's Knapsack. Have these classes been modified ? Report to mod Author pleases.");

			if(inv != null)
				for(int i = 0; i < 7; i ++){ //needs to be 7
					ItemStack is = inv.getStackInSlot(i);
					te.list[i + 74] = is;
					inv.setInventorySlotContents(i, null);
				}
			else
				GraveStones.printDebugMessage("GraveStones Mod couldn't connect to Tinkers Construct's Armor. Have these classes been modified ? Report to mod Author pleases.");

		}

		if(GraveStones.hasBaub){
			IInventory inv = accesInventoryContents(player, "getPlayerBaubles",  "baubles.common.lib.PlayerHandler", "Baubles");

			if(inv != null)
				for(int i = 0; i < 4; i ++){
					ItemStack is = inv.getStackInSlot(i);
					te.list[i + 81] = is;
					inv.setInventorySlotContents(i, null);
				}
			else
				GraveStones.printDebugMessage("GraveStones Mod couldn't connect to Baubles. Have these classes been modified ? Report to mod Author pleases.");

			//sync to client
			try{
				Class<?> clazz = Class.forName("baubles.common.container.InventoryBaubles");
				Method m = clazz.getDeclaredMethod("syncSlotToClients", Integer.class);
				for(int i = 0; i < 7 ; i++)
					m.invoke(null, i);
			}catch(Exception e){
				GraveStones.printDebugMessage("Gravestones was not able to acces Baubles' save mechanicism. Has their class layout changed ? Report to mod author please.");
			}
		}

		if(GraveStones.hasGal_Craft){
			ItemStack[] inv = accesInventoryContentsStacks(player, "getExtendedInventory", "micdoodle8.mods.galacticraft.core.inventory.InventoryExtended", "GalactiCraft");

			for(int i = 0; i < 10; i ++){
				ItemStack is = inv[i];
				te.list[i + 85] = is;
				inv[i] = null;
			}
		}

		if(GraveStones.hasMari_Cul){
			ItemStack[] inv = accesInventoryContentsStacks(player, "getInventory", "mariculture.magic.MirrorHelper", "Mariculture");
			for(int i = 0; i < 3; i ++){
				ItemStack is = inv[i];
				te.list[i + 95] = is;
			}

			//the save method is static
			//the save method only has to be called to save the new (empty) itemstack
			try {
				ItemStack[] newstack = new ItemStack[4];
				Class<?> clazz = Class.forName("mariculture.magic.MirrorHelper");
				Method m2 = clazz.getDeclaredMethod("save", EntityPlayer.class, ItemStack[].class);
				Object saveEmptyArray = m2.invoke(null, player, newstack);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private IInventory accesInventoryContents(EntityPlayer player, String methodName, String path, String modName){

		try {

			Class<?> clazz = Class.forName(path);
			Method m = clazz.getDeclaredMethod(methodName, EntityPlayer.class);
			Object result = m.invoke(null, player);
			GraveStones.printDebugMessage("Dumping all " + modName + " content into grave");
			return (IInventory)result;

		} catch (Exception e){
			GraveStones.printDebugMessage("Error Encountered trying to acces " + modName +"  Inventory Content. Please report to mod author");
		}

		return null;
	}

	private ItemStack[] accesInventoryContentsStacks(EntityPlayer player, String methodName, String path, String modName){

		try {

			Class<?> clazz = Class.forName(path);
			Method m = clazz.getDeclaredMethod(methodName, EntityPlayer.class);
			Object result = m.invoke(null, player);
			GraveStones.printDebugMessage("Dumping all " + modName + " content into grave");
			return (ItemStack[])result;

		} catch (Exception e){
			GraveStones.printDebugMessage("Error Encountered trying to acces " + modName +"  Inventory Content. Please report to mod author");
		}

		return null;
	}

	private IInventory accesInventoryContents(EntityPlayer player, String methodName, String path, String declaredField , String modName){

		try {

			Class<?> clazz = Class.forName(path);
			Method m = clazz.getDeclaredMethod(methodName, EntityPlayer.class);
			Object result = m.invoke(null, player);

			Field f = clazz.getDeclaredField(declaredField);

			IInventory inv = (IInventory)f.get(result);

			GraveStones.printDebugMessage("Dumping all " + modName + " content into grave");
			return inv;

		} catch (Exception e){
			GraveStones.printDebugMessage("Error Encountered trying to acces " + modName +"  Inventory Content. Please report to mod author");
		}

		return null;
	}
}
