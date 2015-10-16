package net.subaraki.gravestone.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiTabButton extends GuiButton{

    protected static RenderItem itemRender = new RenderItem();

	protected static final ResourceLocation button = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");

	private boolean page;
	private final ItemStack stack;
	private final FontRenderer fontRendererObj;
	
	public GuiTabButton(int par1, int par2, int par3, int par4, int par5,
			String par6Str , boolean page, ItemStack stack, FontRenderer font) {
		super(par1, par2, par3, par4, par5, par6Str);
		this.page = page;
		this.stack = stack;
		this.fontRendererObj = font;
	}

	/**
	 * Draws this button to the screen.
	 */
	@Override
	public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_)
	{
		if (this.visible){
			
			FontRenderer fontrenderer = p_146112_1_.fontRenderer;
			p_146112_1_.getTextureManager().bindTexture(button);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = (p_146112_2_ >= this.xPosition) && (p_146112_3_ >= this.yPosition) && (p_146112_2_ < (this.xPosition + this.width)) && (p_146112_3_ < (this.yPosition + this.height));
			int k = page ? 1 : 2; // this.getHoverState(this.field_146123_n);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
			//left
			this.drawTexturedModalRect(this.xPosition, this.yPosition - 9, 28, (32 - (k*32)) + 32, 28, page ? 32 : 28);
			//right
			this.drawTexturedModalRect(this.xPosition + 9, this.yPosition - 9, 28 + 4, (32 - (k*32)) + 32, 24, page ? 32 : 28);

			this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
			int l = 14737632;

			if (packedFGColour != 0)
			{
				l = packedFGColour;
			}
			else if (!this.enabled)
			{
				l = 10526880;
			}
			else if (this.field_146123_n)
			{
				l = 16777120;
			}

			this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + (this.width / 2), this.yPosition + ((this.height - 8) / 2), l);
			
			GL11.glDisable(GL11.GL_LIGHTING);
	        GL11.glColor3f(1F, 1F, 1F);
	        this.zLevel = 100.0F;
	        itemRender.zLevel = 100.0F;
	        GL11.glEnable(GL11.GL_LIGHTING);
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        ItemStack itemstack = stack;
	        itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, Minecraft.getMinecraft().getTextureManager(), itemstack, xPosition+9, yPosition);
	        itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, Minecraft.getMinecraft().getTextureManager(), itemstack, xPosition, yPosition);
	        GL11.glDisable(GL11.GL_LIGHTING);
	        itemRender.zLevel = 0.0F;
		}
	}

}
