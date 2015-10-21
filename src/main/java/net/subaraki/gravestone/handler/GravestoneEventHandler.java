package net.subaraki.gravestone.handler;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.client.ClientProxy;
import cpw.mods.fml.common.FMLCommonHandler;
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
}
