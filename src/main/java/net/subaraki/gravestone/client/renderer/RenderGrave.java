package net.subaraki.gravestone.client.renderer;

import static net.subaraki.gravestone.util.Constants.GRAVE_PLACEHOLDER;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RenderGrave implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		
		GRAVE_PLACEHOLDER.modelType = item.getItemDamage();
		
		TileEntitySpecialRenderer tesr = TileEntityRendererDispatcher.instance.getSpecialRenderer(GRAVE_PLACEHOLDER);
		tesr.renderTileEntityAt(GRAVE_PLACEHOLDER, 0, 0, 0, 0);
	}

}
