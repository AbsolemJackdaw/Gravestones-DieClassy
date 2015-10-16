package net.subaraki.gravestone.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.subaraki.gravestone.handler.ModelHandler;
import net.subaraki.gravestone.handler.TextureHandler;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import net.subaraki.gravestone.util.GraveUtility;

import org.lwjgl.opengl.GL11;


public class TileEntitySpecialRendererGrave extends TileEntitySpecialRenderer {

	ResourceLocation texture = null;

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {

		TileEntityGravestone tile = (TileEntityGravestone)te;
		float rot = tile.ModelRotation;
		int modeltype = tile.modelType;

		renderBeam(tile, x, y, z);

		GL11.glPushMatrix();
		this.bindTexture(TextureHandler.getTextureFromMeta(modeltype));

		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);


		if( (modeltype == 2) || (modeltype == 3) || (modeltype == 6) || modeltype == 8 || modeltype == 9|| modeltype == 5){
			rot -= 90;
		}

		if(modeltype == 4 ){
			rot =+ 90;
		}

		float s = -0.75f;
		float s2 = -0.4f;

		if(modeltype == 8){
			GL11.glScalef(0.75f, 0.75f, 0.75f);
			GL11.glTranslatef(-0.75f, 2f, 0.75f);
			GL11.glTranslatef(-s, 0f, s);

			GL11.glRotatef(180, 1, 0, 0);
			GL11.glRotatef(180, 0, 1, 0);
		}

		if(modeltype == 10){
			GL11.glScalef(1.0f, 1.0f, 1.0f);
			GL11.glTranslatef(-0.4f, 1.5f, 0.4f);
			GL11.glTranslatef(-s2, 0f, s2);

			GL11.glRotatef(180, 1, 0, 0);
			GL11.glRotatef(180, 0, 1, 0);
		}

		GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);

		if(modeltype == 8) {
			GL11.glTranslatef(s, 0, -s);
		}
		if(modeltype == 10) {
			GL11.glTranslatef(s2, 0, -s2);
		}

		ModelHandler.renderModelFromType(modeltype);
		GL11.glPopMatrix();

		GL11.glPushMatrix();

		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		float sc = 0.75f;
		GL11.glScalef(sc, sc, sc);

		if(modeltype == 5){

			bindTexture(GraveUtility.instance.processPlayerTexture(tile.playername));

			GL11.glRotatef(rot, 0, 1, 0);
			ModelHandler.modelhead.renderHead(0.0625f);

			if(tile.getStackInSlot(tile.getSizeInventory()-1) != null){
				if(tile.getStackInSlot(tile.getSizeInventory()-1).getItem() instanceof ItemArmor){
					GL11.glPushMatrix();
					float f2 = 1.2f;
					GL11.glScalef(f2,f2,f2);
					GL11.glTranslatef(0f, 0.05f, 0f);
					GL11.glRotatef(rot, 0, 1, 0);
					ItemStack item = tile.getStackInSlot(tile.getSizeInventory()-1);
					ModelHandler.helper.setArmorModel(ModelHandler.modelarmorhead, item,((ItemArmor)item.getItem()).armorType, RenderBiped.bipedArmorFilenamePrefix[((ItemArmor)item.getItem()).renderIndex]);
					ModelHandler.modelarmorhead.renderHead(0.0625f);
					GL11.glPopMatrix();
				}
			}

			if(tile.getStackInSlot(tile.getSizeInventory()-2) != null){
				if(tile.getStackInSlot(tile.getSizeInventory()-2).getItem() instanceof ItemArmor){

					GL11.glPushMatrix();
					float f2 = 1.1f;
					GL11.glScalef(f2,f2,f2);
					GL11.glTranslatef(0f, -0.02f, 0f);
					GL11.glRotatef(rot, 0, 1, 0);
					ItemStack item = tile.getStackInSlot(tile.getSizeInventory()-2);
					ModelHandler.helper.setArmorModel(ModelHandler.modelarmorchest, item,((ItemArmor)item.getItem()).armorType, RenderBiped.bipedArmorFilenamePrefix[((ItemArmor)item.getItem()).renderIndex]);
					ModelHandler.modelarmorchest.renderHead(0.0625f);
					GL11.glPopMatrix();
				}
			}
		}
		GL11.glPopMatrix();
	}

	private void renderBeam(TileEntityGravestone tileentity, double d, double d1, double d2/*x,y,z*/){

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
