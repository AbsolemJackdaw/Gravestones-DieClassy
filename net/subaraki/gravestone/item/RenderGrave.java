package net.subaraki.gravestone.item;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.subaraki.gravestone.block.inventory.TileEntityGrave;

public class RenderGrave implements IItemRenderer {

	TileEntityGrave grave = new TileEntityGrave();
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
		
		grave.modelType = item.getItemDamage();
		
		TileEntityRendererDispatcher.instance.renderTileEntityAt(grave, 0,0,0,0.0625f);
	}

}
