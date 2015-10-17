package net.subaraki.gravestone.util;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.subaraki.gravestone.GraveStones;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GraveUtility {

	public static GraveUtility instance;

	public GraveUtility() {
		instance = this;
	}

	/**
	 * @param classPath : the path to the class that has to be found
	 * @param modName : the mod's name for console print if not found
	 */
	public static boolean findClass(String classPath, String modName) {

		try {
			Class classToFind = Class.forName(classPath);

			if(classToFind != null){
				GraveStones.printDebugMessage("GraveStones Detected " + modName + ". Inventory Content will be dumped into grave");
				return true;
			}

		} catch (Exception e) {
		}

		return false;
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
}
