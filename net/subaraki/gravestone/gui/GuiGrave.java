package net.subaraki.gravestone.gui;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.ModelTable;
import net.subaraki.gravestone.PlayerData;
import net.subaraki.gravestone.TextureTable;
import net.subaraki.gravestone.packets.ServerPacket;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class GuiGrave extends GuiScreen{

	private int xSize = 0;
	private int ySize = 0;
	private short rotationCounter = 0;
	private EntityPlayer player;
	public int render;
	public String price ="";

	public GuiGrave(EntityPlayer player) {
		super();
		this.player = player;
		render = PlayerData.get(player).getGraveModel();
	}

	@Override
	public void initGui() {
		this.buttonList.clear();
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		this.buttonList.add(new GuiButton(0, posX+150 , posY-100 , 20, 20, "X"));
		this.buttonList.add(new GuiButton(1, posX+90 , posY-70 , 80, 20, "Cross"));
		this.buttonList.add(new GuiButton(2, posX+90 , posY-40 , 80, 20, "Classic Grave"));
		this.buttonList.add(new GuiButton(3, posX+90 , posY-10 , 80, 20, "Tomb"));
		this.buttonList.add(new GuiButton(4, posX+90 , posY+20 , 80, 20, "Pillar"));
		this.buttonList.add(new GuiButton(5, posX+90 , posY+50 , 80, 20, "Bust"));
		this.buttonList.add(new GuiButton(6, posX , posY-70 , 80, 20, "Cheap Cross"));
		this.buttonList.add(new GuiButton(7, posX , posY-40 , 80, 20, "Stele"));
		//		this.buttonList.add(new GuiButton(10, posX-180 , posY+80 , 80, 20, "Set Grave"));
		// this.buttonList.add(new GuiButton(11, posX-100 , posY , 80 ,20, "clear"));
		this.buttonList.add(new GuiButton(8, posX , posY-10 , 80, 20, "Angel"));
		this.buttonList.add(new GuiButton(9, posX , posY+20 , 80, 20, "Knight"));
		this.buttonList.add(new GuiButton(10, posX , posY+50 , 80, 20, "Some Barrel"));

	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {

		super.drawScreen(par1, par2, par3);

		GL11.glColor4f(0.0F, 0.0F, 0.0F, 5.0F);
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		fontRendererObj.drawSplitString("Choose the Grave you want to spawn when you die.", (this.width / 2)-84, (this.height / 2)-100, 150 ,0xffffff);
		fontRendererObj.drawSplitString(""+render, (this.width / 2) +140, (this.height / 2)-95, 150 ,0xffffff);

		fontRendererObj.drawSplitString(price, (this.width / 2) -94, (this.height / 2)+86, 150 ,0x444444);
		fontRendererObj.drawSplitString(price, (this.width / 2) -95, (this.height / 2)+85, 150 ,0xffffff);

		GL11.glPushMatrix();
		mc.renderEngine.bindTexture(TextureTable.getTextureFromMeta(render));

		float scale = 75f;
		int height = 80;

		if((render == 4) || (render == 5)|| (render == 7) || (render == 8)|| (render == 9)){
			height = 40;
		}

		if((render == 9)){
			scale = 60f;
		}

		if(render == 8) {
			scale = 50f;
		}

		GL11.glTranslatef((this.width / 2) - 150, (this.height / 2) - height, 40);
		GL11.glScaled(scale, scale, -scale);

		float s = -0.65f;
		float s2 = -0.4f;
		if(render == 8){

			GL11.glScalef(1, -1, 1);
			GL11.glRotatef(-10, 1f, 0f, 0f);
			GL11.glTranslatef(-0.5f, -2.4f, 0f);
			GL11.glTranslatef(-s, 0f, s);

		}

		if(render == 10){

			GL11.glScalef(1, -1, 1);
			GL11.glTranslatef(-0.5f, -1.5f, 0f);
			GL11.glRotatef(-10, 1f, 0f, 0f);
			GL11.glTranslatef(-s2, 0f, s2);

		}

		GL11.glRotatef(5, 1f, 0f, 0f);
		GL11.glRotatef(rotationCounter++, 0, 1, 0);

		if(render == 8) {
			GL11.glTranslatef(s, 0, -s);
		}

		if(render == 10){
			GL11.glTranslatef(s2, 0, -s2);
		}
		ModelTable.renderModelFromType(render);

		GL11.glPopMatrix();

		if(render == 5){
			renderBust();
		}

	}

	ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;
	ResourceLocation steve = new ResourceLocation("textures/entity/steve.png");

	private void renderBust()
	{
		GL11.glPushMatrix();
		if(player != null)
		{
			try{
				if ((player != null) && (player.getCommandSenderName().length() > 0))
				{
					resourcelocation = AbstractClientPlayer.getLocationSkin(player.getCommandSenderName());
					AbstractClientPlayer.getDownloadImageSkin(resourcelocation, player.getCommandSenderName());

				}else{
					resourcelocation = steve;
				}
				Minecraft.getMinecraft().renderEngine.bindTexture(resourcelocation);
			}catch(Throwable e){}

			GL11.glTranslatef((this.width / 2)-150, (this.height / 2)-40, 40);
			GL11.glScaled(50, 50, -50);
			GL11.glRotatef(5, 1f, 0f, 0f);
			GL11.glRotatef(rotationCounter, 0, 1, 0);

			ModelTable.modelhead.renderHead(0.0625f);
		}
		GL11.glPopMatrix();

	}

	@Override
	protected void actionPerformed(GuiButton b) {
		super.actionPerformed(b);

		if(b.id == 0) {
			player.closeScreen();
		} else {
			sendPacket(b.id);
		}
	}

	private void sendPacket(int id) {
		render = id;

		PlayerData.get(player).setGraveModel(id);

		ByteBuf buf = Unpooled.buffer();
		ByteBufOutputStream out = new ByteBufOutputStream(buf);

		try {
			out.writeInt(ServerPacket.SET_GRAVE_MODEL);
			out.writeInt(render);
			GraveStones.channel.sendToServer(new FMLProxyPacket(buf,"gravestone"));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}


}
