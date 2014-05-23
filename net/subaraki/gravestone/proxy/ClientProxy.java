package net.subaraki.gravestone.proxy;

import net.minecraft.client.settings.KeyBinding;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.block.inventory.TileEntityGrave;
import net.subaraki.gravestone.block.render.TileEntitySpecialRendererGrave;
import net.subaraki.gravestone.packets.ClientPacket;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends ServerProxy {

	public static KeyBinding keyGui = new KeyBinding(
			"Grave Gui", Keyboard.KEY_M, "gravestonemod");


	public ClientProxy() {
	}

	@Override
	public void registerRendering() {
		ClientRegistry.registerKeyBinding(keyGui);
		GraveStones.channel.register(new ClientPacket());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrave.class, new TileEntitySpecialRendererGrave());

	}

	@Override
	public void setCustomNameBoolean(TileEntityGrave te, boolean b) {
		te.customName = b;
	}
}
