package net.subaraki.gravestone.block.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.subaraki.gravestone.ModelTable;
import net.subaraki.gravestone.TextureTable;
import net.subaraki.gravestone.block.inventory.TileEntityGrave;

import org.lwjgl.opengl.GL11;


public class TileEntitySpecialRendererGrave extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity var1, double d, double d1,
			double d2, float var8) {

		TileEntityGrave tile = (TileEntityGrave)var1;
		float rot = tile.ModelRotation;
		int modeltype = tile.modelType;

		renderBeam(tile, d, d1, d2);

		GL11.glPushMatrix();
		this.bindTexture(TextureTable.getTextureFromMeta(modeltype));

		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);


		if( (modeltype == 2) || (modeltype == 3) || (modeltype == 6)){
			rot -= 90;
		}

		if(modeltype == 4){
			rot =+ 90;
		}

		GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);

		ModelTable.renderModelFromType(modeltype);
		GL11.glPopMatrix();

		GL11.glPushMatrix();

		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		float sc = 0.75f;
		GL11.glScalef(sc, sc, sc);

		if(modeltype == 5){
			ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;
			if ((tile.playername != null) && (tile.playername.length() > 0))
			{
				resourcelocation = AbstractClientPlayer.getLocationSkin(tile.playername);
				AbstractClientPlayer.getDownloadImageSkin(resourcelocation, tile.playername);

			}else{
				resourcelocation = new ResourceLocation("textures/entity/steve.png");
			}
			Minecraft.getMinecraft().renderEngine.bindTexture(resourcelocation);

			ModelTable.modelhead.renderHead(0.0625f);

			if(tile.getStackInSlot(tile.getSizeInventory()-1) != null){
				if(tile.getStackInSlot(tile.getSizeInventory()-1).getItem() instanceof ItemArmor){
					GL11.glPushMatrix();
					float f2 = 1.2f;
					GL11.glScalef(f2,f2,f2);
					GL11.glTranslatef(0f, 0.05f, 0f);
					ItemStack item = tile.getStackInSlot(tile.getSizeInventory()-1);
					ModelTable.helper.setArmorModel(ModelTable.modelarmorhead, item,
							((ItemArmor)item.getItem()).armorType, RenderBiped.bipedArmorFilenamePrefix[((ItemArmor)item.getItem()).renderIndex]);
					ModelTable.modelarmorhead.renderHead(0.0625f);
					GL11.glPopMatrix();
				}
			}

			if(tile.getStackInSlot(tile.getSizeInventory()-2) != null){
				if(tile.getStackInSlot(tile.getSizeInventory()-2).getItem() instanceof ItemArmor){

					GL11.glPushMatrix();
					float f2 = 1.1f;
					GL11.glScalef(f2,f2,f2);
					GL11.glTranslatef(0f, -0.02f, 0f);
					ItemStack item = tile.getStackInSlot(tile.getSizeInventory()-2);
					ModelTable.helper.setArmorModel(ModelTable.modelarmorchest, item,
							((ItemArmor)item.getItem()).armorType, RenderBiped.bipedArmorFilenamePrefix[((ItemArmor)item.getItem()).renderIndex]);
					ModelTable.modelarmorchest.renderHead(0.0625f);
					GL11.glPopMatrix();
				}
			}
		}
		GL11.glPopMatrix();
	}

	private void renderBeam(TileEntityGrave tileentity, double d, double d1, double d2/*x,y,z*/){

		if(tileentity.hasItems){
			Tessellator tesselator = Tessellator.instance;
			GL11.glDisable(3553 /*GL_TEXTURE_2D */);
			GL11.glDisable(2896/*GL_LIGHTING */);
			GL11.glDisable(2912/*GL_FOG */);
			GL11.glDepthMask(false);
			GL11.glDisable(GL11.GL_CULL_FACE);

			GL11.glEnable(3042/*GL_BLEND */);
			// GL11.glBlendFunc(770/* GL_SRC_ALPHA */, 1/*GL_LINES */);
			GL11.glBlendFunc(770/* GL_SRC_ALPHA */, GL11.GL_LINES/*GL_LINES */);

			char var5 = 0x000F0;
			int var6 = var5 % 65536;
			int var7 = var5 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6 / 1.0F, var7 / 1.0F);

			int height = 256;
			double topWidthFactor = 0.5D;
			double bottomWidthFactor = 0.5D;
			// true when player is someone else.
			boolean other = tileentity.playername.equals(Minecraft.getMinecraft().thePlayer.getCommandSenderName()) ? false: true;
			float otherFloat = 0f; //other ? 0.6f : 0f;
			float color = other && !Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode ? 0f : 0.7f;

			for (int width = 0; width < (other ? 2: 5); width++)
			{
				tesselator.startDrawing(5);
				tesselator.setColorRGBA_F(color-otherFloat,color-otherFloat,color, 0.11f);
				double var32 = 0.1D + (width * 0.2D);
				var32 *= topWidthFactor;

				double var34 = 0.1D + (width * 0.2D);
				var34 *= bottomWidthFactor;

				for (int side = 0; side < 5; side++)
				{
					double vertX2 = (d + 0.5D) - var32;
					double vertZ2 = (d2 + 0.5D) - var32;

					if ((side == 1) || (side == 2))
					{
						vertX2 += var32 * 2.0D;
					}

					if ((side == 2) || (side == 3))
					{
						vertZ2 += var32 * 2.0D;
					}

					double vertX1 = (d + 0.5D) - var34;
					double vertZ1 = (d2 + 0.5D) - var34;

					if ((side == 1) || (side == 2))
					{
						vertX1 += var34 * 2.0D;
					}

					if ((side == 2) || (side == 3))
					{
						vertZ1 += var34 * 2.0D;
					}

					tesselator.addVertex(vertX1, d1 + 0.0D, vertZ1);
					tesselator.addVertex(vertX2, d1 + height, vertZ2);
				}

				tesselator.draw();
			}
			GL11.glDisable(3042/*GL_BLEND */);
			GL11.glEnable(2912/*GL_FOG */);
			GL11.glEnable(2896/*GL_LIGHTING */);
			GL11.glEnable(3553/*GL_TEXTURE_2D */);
			GL11.glDepthMask(true);
		}
	}

}
