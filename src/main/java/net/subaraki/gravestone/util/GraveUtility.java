package net.subaraki.gravestone.util;

import net.minecraft.block.material.Material;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.block.BlockGrave;
import net.subaraki.gravestone.handler.ConfigHandler;
import net.subaraki.gravestone.handler.PlayerGraveData;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GraveUtility {

	public static GraveUtility instance;

	public GraveUtility() {
		instance = this;
	}


	public ResourceLocation SKIN_ABSTRACT_PLAYER = null;
	public ResourceLocation SKIN_STEVE = new ResourceLocation("textures/entity/steve.png");

	@SideOnly(Side.CLIENT)
	public ResourceLocation processPlayerTexture(EntityPlayer player){
		if(SKIN_ABSTRACT_PLAYER == null)
			SKIN_ABSTRACT_PLAYER = AbstractClientPlayer.locationStevePng;

		try{
			if ((player != null) && (player.getCommandSenderName().length() > 0)){

				SKIN_ABSTRACT_PLAYER = AbstractClientPlayer.getLocationSkin(player.getCommandSenderName());
				AbstractClientPlayer.getDownloadImageSkin(SKIN_ABSTRACT_PLAYER, player.getCommandSenderName());

			}else
				SKIN_ABSTRACT_PLAYER = SKIN_STEVE;
		}catch(Exception e){

		}
		return SKIN_ABSTRACT_PLAYER;

	}

	@SideOnly(Side.CLIENT)
	public ResourceLocation processPlayerTexture(String playername){
		if(SKIN_ABSTRACT_PLAYER == null)
			SKIN_ABSTRACT_PLAYER = AbstractClientPlayer.locationStevePng;

		try{		
			if ((playername != null) && (playername.length() > 1)){
				SKIN_ABSTRACT_PLAYER = AbstractClientPlayer.getLocationSkin(playername);
				AbstractClientPlayer.getDownloadImageSkin(SKIN_ABSTRACT_PLAYER, playername);
			}else
				SKIN_ABSTRACT_PLAYER = SKIN_STEVE;
			return SKIN_ABSTRACT_PLAYER;
		}catch(Exception e){

		}

		return SKIN_ABSTRACT_PLAYER;
	}

	public void buildGrave(PlayerDropsEvent event){

		EntityPlayer player = (EntityPlayer)event.entityPlayer;

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

						placeGrave(player, x+x2, y+y2, z+z2, event);

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

			int y2 = 0;
			while(!player.worldObj.getBlock(x, y+1 +y2, z).getMaterial().equals(Material.air)){
				y2++;
			}

			if(player.worldObj.getBlock(x, y+y2, z).getMaterial().isLiquid()){
				if(player.worldObj.getBlock(x, y+1+y2, z).getMaterial().equals(Material.air))
					player.worldObj.setBlock(x, y+y2, z, Blocks.cobblestone);

				placeGrave(player, x, y+y2+1, z, event);
			}
		}
		else if(!flag)
			placeGrave(player, x, y, z, event);

	}

	private void placeGrave(EntityPlayer player, int x, int y, int z, PlayerDropsEvent event){

		TileEntityGravestone te = new TileEntityGravestone();
		InventoryPlayer inv = player.inventory;

		int graveID = PlayerGraveData.get(player).getGraveModel();
		int max = 9;

		if(!ConfigHandler.enableGravesTroughKey)
			graveID = ConfigHandler.graveOrder[Math.min((player.experienceLevel / ConfigHandler.graveLevel), max)];

		te.setGraveData(player.getCommandSenderName(), graveID);

		int slot = 0;
		if(!player.worldObj.isRemote){

			ItemStack[] armor = Constants.armorFromPlayer.get(player.getUniqueID());

			for(int i = 9; i < 40 ; i+=10){
				te.tab = 0;
				te.list[i] = armor[i==9 ?  3 : i==19 ? 2 : i== 29 ? 1 : 0];
			}

			for(EntityItem ei : event.drops){
				ItemStack stack = ei.getEntityItem();

				if(stack != null && slot < 200 && slot != 9 && slot != 19 && slot != 29 && slot != 39){

					if(te.list[slot] == null){
						te.tab = slot / 40;
						te.list[slot] = stack;
						slot++;
					}else{
						while(te.list[slot] != null)
							slot++;
						te.tab = slot / 40;
						te.list[slot] = stack;
						slot++;
					}
				}
			}
		}

		player.worldObj.setBlock(x, y+1, z, GraveStones.graveStone);
		player.worldObj.setTileEntity(x, y+1, z, te);
		player.worldObj.markBlockForUpdate(x, y+1, z);
		te.markDirty();
	}
}
