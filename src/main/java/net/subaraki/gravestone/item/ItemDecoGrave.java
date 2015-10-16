package net.subaraki.gravestone.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDecoGrave extends ItemBlock {

	public ItemDecoGrave(Block p_i45328_1_) {
		super(p_i45328_1_);
		setHasSubtypes(true);
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs tab, List par3List)
	{
		for(int i = 1; i <= 10 ; i++)
			par3List.add(new ItemStack(par1, 1, i));
	}

	@Override
	public int getMetadata(int par1) {
		return super.getMetadata(par1);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par7 == 0)
		{
			return false;
		}
		else if (!par3World.getBlock(par4, par5, par6).getMaterial().isSolid())
		{
			return false;
		}
		else
		{
			if (par7 == 1)
			{
				++par5;
			}

			if (par7 == 2)
			{
				--par6;
			}

			if (par7 == 3)
			{
				++par6;
			}

			if (par7 == 4)
			{
				--par4;
			}

			if (par7 == 5)
			{
				++par4;
			}

			if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
			{
				return false;
			}
			else
			{
				par3World.setBlock(par4, par5, par6, GraveStones.graveStone, par7, 2);
				int i1 = 0;

				TileEntityGravestone grave = new TileEntityGravestone();
				grave.setDeathMessage("Decorative grave.");
				grave.setDeathMessage2("");
				grave.isDecorativeGrave = true;
				grave.modelType = par1ItemStack.getItemDamage();
				par3World.setTileEntity(par4, par5, par6, grave);

				--par1ItemStack.stackSize;
				return true;
			}
		}
	}
}
