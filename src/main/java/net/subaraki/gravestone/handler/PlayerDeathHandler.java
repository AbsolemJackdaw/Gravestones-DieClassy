package net.subaraki.gravestone.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.util.Constants;
import net.subaraki.gravestone.util.GraveUtility;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerDeathHandler {

	public PlayerDeathHandler() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent(priority = EventPriority.LOW, receiveCanceled = true)
	public void onDropsEvent(PlayerDropsEvent event){

		GraveStones.printDebugMessage(event.entityPlayer.capturedDrops.toString());
		GraveStones.printDebugMessage(event.drops.toString());

		if (!event.entityPlayer.worldObj.isRemote)
			GraveUtility.instance.buildGrave(event);

		if(Constants.armorFromPlayer.containsKey(event.entityPlayer.getUniqueID()))
			Constants.armorFromPlayer.remove(event.entityPlayer.getUniqueID());

		event.setCanceled(true);

	}

	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent event){

		if(event.entityLiving instanceof EntityPlayer){

			EntityPlayer player = (EntityPlayer) event.entityLiving;

			if(!Constants.armorFromPlayer.containsKey(player.getUniqueID())){
				Constants.armorFromPlayer.put(player.getUniqueID(), player.inventory.armorInventory.clone());
				player.inventory.armorInventory = new ItemStack[4];
			}
		}

	}
}
