package net.subaraki.gravestone;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.subaraki.gravestone.block.inventory.ContainerGrave;
import net.subaraki.gravestone.block.inventory.TileEntityGrave;
import net.subaraki.gravestone.gui.GuiGrave;
import net.subaraki.gravestone.gui.GuiGraveContainer;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		return ID == 0 ? new ContainerGrave(player.inventory, (TileEntityGrave) te, player) : null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {

		TileEntity te = world.getTileEntity(x, y, z);

		switch (ID) {
		case 0 :
			return new GuiGraveContainer(player, (TileEntityGrave) te);
		case 1:
			return new GuiGrave(player);
		}

		return new GuiGraveContainer(player, (TileEntityGrave) te);

	}
}
