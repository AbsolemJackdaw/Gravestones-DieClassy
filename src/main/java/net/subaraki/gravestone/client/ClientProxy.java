package net.subaraki.gravestone.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.client.model.ModelCubeWorld;
import net.subaraki.gravestone.client.renderer.TileEntitySpecialRendererGrave;
import net.subaraki.gravestone.common.ServerProxy;
import net.subaraki.gravestone.item.RenderGrave;
import net.subaraki.gravestone.packets.ClientPacket;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends ServerProxy {

	public static KeyBinding keyGui = new KeyBinding(
			"Grave Gui", Keyboard.KEY_M, "gravestonemod");

	public static ModelCubeWorld angelStatue;
	public static ModelCubeWorld barrel;

	public ClientProxy() {
	}

	@Override
	public void registerRendering() {
		ClientRegistry.registerKeyBinding(keyGui);
		GraveStones.channel.register(new ClientPacket());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGravestone.class, new TileEntitySpecialRendererGrave());

		angelStatue = new ModelCubeWorld( ModelCubeWorld.class.getResourceAsStream("/assets/gravestone/models/angelStatue.cub"));
		barrel = new ModelCubeWorld( ModelCubeWorld.class.getResourceAsStream("/assets/gravestone/models/barrel.cub"));

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GraveStones.graveStone), new RenderGrave());
	}


	@Override
	public void setCustomNameBoolean(TileEntityGravestone te, boolean b) {
		te.isDecorativeGrave = b;
	}
}
