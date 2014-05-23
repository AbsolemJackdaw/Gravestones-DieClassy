package net.subaraki.gravestone;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.subaraki.gravestone.block.inventory.TileEntityGrave;
import net.subaraki.gravestone.proxy.ClientProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
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

		if(ClientProxy.keyGui.isPressed()){
			if(Minecraft.getMinecraft().currentScreen == null){
				EntityPlayer p = Minecraft.getMinecraft().thePlayer;

				p.openGui(GraveStones.instance, 1, Minecraft.getMinecraft().theWorld,
						(int)p.posX, (int)p.posY, (int)p.posZ);
			}
		}
	}

	@SubscribeEvent
	public void onDeathEvent(LivingDeathEvent evt){

		if(evt.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)evt.entityLiving;

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
			}

		}
	}


	private void placeGrave(EntityPlayer player, int x, int y, int z){
		player.worldObj.setBlock(x, y+1, z, GraveStones.graveStone);


		TileEntityGrave te = new TileEntityGrave();
		InventoryPlayer inv = player.inventory;

		te.setGraveData(player.getCommandSenderName(), PlayerData.get(player).getGraveModel());


		for(int id = 0; id <inv.getSizeInventory(); id++)
		{
			ItemStack is = inv.getStackInSlot(id);
			if((is != null) && (id < te.getSizeInventory()))
			{
				te.setInventorySlotContents(id, is);
				inv.setInventorySlotContents(id, null);
			}
		}

		player.worldObj.setTileEntity(x, y+1, z, te);
		player.worldObj.markBlockForUpdate(x, y+1, z);
		te.markDirty();

	}
}
