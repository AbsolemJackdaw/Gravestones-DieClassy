package net.subaraki.gravestone.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.client.model.ModelCubeWorld;
import net.subaraki.gravestone.client.renderer.RenderGrave;
import net.subaraki.gravestone.client.renderer.TileEntitySpecialRendererGrave;
import net.subaraki.gravestone.common.CommonProxy;
import net.subaraki.gravestone.handler.ConfigHandler;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import net.subaraki.gravestone.util.Constants;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	public static KeyBinding keyGui = new KeyBinding("Pick Grave Gui", Keyboard.KEY_M, "gravestonemod");

	@Override
	public void preInit() {

		if(ConfigHandler.enableGravesTroughKey)
			ClientRegistry.registerKeyBinding(keyGui);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGravestone.class, new TileEntitySpecialRendererGrave());

		Constants.angelStatue = new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/grave/models/angelStatue.cub"));
		Constants.barrel = new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/grave/models/barrel.cub"));

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GraveStones.graveStone), new RenderGrave());
	}
}
