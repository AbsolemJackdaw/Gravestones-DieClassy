package net.subaraki.gravestone.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemNameTag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockGrave extends Block{

	Random rand = new Random();

	public BlockGrave(Material mat) {
		super(mat);
		this.setBlockBounds(0.4f, 0.0F, 0.4F, 0.6f, 1.0f, 0.6f);
		this.setBlockUnbreakable();
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("stonebrick");
	}

	@Override
	public int quantityDropped(Random par1Random){
		return -1;
	}

	@Override
	public boolean onBlockActivated(World world, int x,
			int y, int z, EntityPlayer player,
			int p_149727_6_, float p_149727_7_, float p_149727_8_,
			float p_149727_9_) {

		TileEntityGravestone te = (TileEntityGravestone) world.getTileEntity(x, y, z);

		if(player.getCurrentEquippedItem() != null)
			if(player.getCurrentEquippedItem().getItem() instanceof ItemNameTag){
				if(te.isDecorativeGrave){
					String s = player.getCurrentEquippedItem().getDisplayName();
					te.setName(s);
					te.setDeathMessage(StatCollector.translateToLocal("is.Honored.To"));
					te.setDeathMessage2(".");
					return true;
				}else{
					if(!world.isRemote)
						player.addChatComponentMessage(new ChatComponentText("This grave belongs to someone. I should not do that."));

					return true;
				}
			}

		if(!player.isSneaking()) {
			player.openGui(GraveStones.instance, 0, world, x, y, z);
		} else {
			te.ModelRotation += 15f;
		}

		return true;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		return getExplosionResistance(par1Entity);
	}

	@Override
	public float getExplosionResistance(Entity par1Entity) {
		return 18000000F;
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x,
			int y, int z, Explosion p_149723_5_) {

		world.removeTileEntity(x, y, z);
	}

	@Override
	public void onBlockClicked(World world, int x,
			int y, int z, EntityPlayer player) {

		if(((TileEntityGravestone)world.getTileEntity(x, y, z)).hasItems) {
			setBlockUnbreakable();
		} else {
			setHardness(5.0f);
		}

		System.out.println(blockHardness);
	}

	@Override
	public void breakBlock(World world, int x, int y,
			int z, Block block, int a) {

		TileEntityGravestone te = (TileEntityGravestone)world.getTileEntity(x,y,z);

		if (te != null)
		{
			for (ItemStack element : te.list) {
				ItemStack itemstack = element;

				if (itemstack != null)
				{
					float f = (this.rand.nextFloat() * 0.8F) + 0.1F;
					float f1 = (this.rand.nextFloat() * 0.8F) + 0.1F;
					float f2 = (this.rand.nextFloat() * 0.8F) + 0.1F;

					while (itemstack.stackSize > 0)
					{
						int k1 = this.rand.nextInt(21) + 10;

						if (k1 > itemstack.stackSize)
						{
							k1 = itemstack.stackSize;
						}

						itemstack.stackSize -= k1;
						EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound())
						{
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (float)this.rand.nextGaussian() * f3;
						entityitem.motionY = ((float)this.rand.nextGaussian() * f3) + 0.2F;
						entityitem.motionZ = (float)this.rand.nextGaussian() * f3;
						if(!world.isRemote) {
							world.spawnEntityInWorld(entityitem);
						}
					}
				}
			}
		}
		world.removeTileEntity(x, y, z);

		super.breakBlock(world, x,y,z, block, a);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z) {
		TileEntityGravestone te = (TileEntityGravestone)par1IBlockAccess.getTileEntity(x,y,z);
		int meta = te.modelType;
		switch (meta) {
		case 1:
			this.setBlockBounds(0.4f, 0.0F, 0.4F, 0.6f, 1.0f, 0.6f);
			break;
		case 2:
			this.setBlockBounds(0.43f, 0.0F, 0.14F, 0.57f, 1.05f, 0.86f);
			break;
		case 3:
			this.setBlockBounds(0.33f, 0.0F, 0.25F, 0.67f, 0.95f, 0.75f);
			break;
		case 4:
			this.setBlockBounds(0.2f, 0.0F, 0.2F, 0.8f, 1.25f, 0.8f);
			break;
		case 5:
			this.setBlockBounds(0.2f, 0.0F, 0.2F, 0.8f, 1.25f, 0.8f);
			break;
		case 7:
			this.setBlockBounds(0.2f, 0.0F, 0.2F, 0.8f, 1.25f, 0.8f);
			break;
		case 6:
			this.setBlockBounds(0.4f, 0.0F, 0.4F, 0.6f, 1.0f, 0.6f);
			break;
		case 8:
			this.setBlockBounds(0.2f, 0.0F, 0.2F, 0.8f, 1.5f, 0.8f);
			break;
		case 9:
			this.setBlockBounds(0.2f, 0.0F, 0.2F, 0.8f, 1.5f, 0.8f);
			break;
		case 10:
			this.setBlockBounds(0.2f, 0.0F, 0.2F, 0.8f, 0.85f, 0.8f);
			break;
		default:
			this.setBlockBounds(0.4f, 0.0F, 0.4F, 0.6f, 1.0f, 0.6f);
			break;
		}
	}


	@Override
	public int getRenderType(){
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityGravestone();
	}
}
