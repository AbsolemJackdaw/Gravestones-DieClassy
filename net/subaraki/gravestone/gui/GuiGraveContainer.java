package net.subaraki.gravestone.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.ModelTable;
import net.subaraki.gravestone.StringTable;
import net.subaraki.gravestone.TextureTable;
import net.subaraki.gravestone.block.inventory.ContainerGrave;
import net.subaraki.gravestone.block.inventory.TileEntityGrave;
import net.subaraki.gravestone.block.model.ModelAngel;
import net.subaraki.gravestone.block.model.ModelGraveSkeleton;
import net.subaraki.gravestone.block.model.ModelGraveStone;
import net.subaraki.gravestone.block.model.ModelHead;
import net.subaraki.gravestone.block.model.ModelKnight;
import net.subaraki.gravestone.block.model.ModelPillar;
import net.subaraki.gravestone.block.model.ModelStoneCross;
import net.subaraki.gravestone.block.model.ModelTomb;
import net.subaraki.gravestone.block.model.ModelWoodenGrave;

import org.lwjgl.opengl.GL11;

public class GuiGraveContainer extends GuiContainer{

	private short rotationCounter = 0;

	public String gravetext = "" ;

	public EntityPlayer deathPlayer;
	public EntityPlayer playerOpenGui;

	public String nameOfDeathPlayer;
	public String nameOfPlayerOpeningGui;

	private TileEntityGrave te;


	public static final ModelAngel angel = new ModelAngel();
	public static final ModelGraveSkeleton skeleton = new ModelGraveSkeleton();
	public static final ModelGraveStone gravestone = new ModelGraveStone();
	public static final ModelKnight knight = new ModelKnight();
	public static final ModelPillar pillar = new ModelPillar();
	public static final ModelStoneCross cross = new ModelStoneCross();
	public static final ModelTomb tomb = new ModelTomb();
	public static final ModelWoodenGrave wood = new ModelWoodenGrave();

	private static final ResourceLocation graveGui = new ResourceLocation("subaraki:grave/grave_chest.png");

	private ModelHead modelhead = new ModelHead();


	public GuiGraveContainer(EntityPlayer player, TileEntityGrave grave ) {
		super(new ContainerGrave(player.inventory, grave, player));

		deathPlayer = player.worldObj.getPlayerEntityByName(grave.playername);
		playerOpenGui = player;

		nameOfDeathPlayer = grave.playername;
		nameOfPlayerOpeningGui = player.getCommandSenderName();

		te = grave;

		this.xSize = 198;
		this.ySize = 186;

		if(grave != null) {
			if(grave.message1.length() <= 0)
			{
				GraveStones.proxy.setCustomNameBoolean(grave,false);

				if(nameOfDeathPlayer.equals("!Empty!")){
					gravetext = "The Grave is empty !";
					GraveStones.proxy.setCustomNameBoolean(grave,true);
				}

				else{
					gravetext = StringTable.getStringFromMeta(nameOfDeathPlayer, te.modelType);
				}


			}else{
				gravetext = grave.message1.replace("_"," ") + " "+grave.playername + " "+ grave.message2.replace("_", " ");
				GraveStones.proxy.setCustomNameBoolean(grave,true);
			}
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
		fontRendererObj.drawString(StatCollector.translateToLocal("grave.container.name"), 8, (ySize - 96) + 2, 0xffffff);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;

		mc.renderEngine.bindTexture(graveGui);
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);

		fontRendererObj.drawSplitString(gravetext, (this.width / 2)+109, (this.height / 2)-89, 100 ,0x000000);
		fontRendererObj.drawSplitString(gravetext, (this.width / 2)+110, (this.height / 2)-90, 100 ,0xffffff);

		if(te.locked.length() > 0){
			fontRendererObj.drawSplitString(te.locked, (this.width / 2)+79, (this.height / 2)+39, 150 ,0x000000);
			fontRendererObj.drawSplitString(te.locked, (this.width / 2)+80, (this.height / 2)+40, 150 ,0xffffff);
		}

		int render = te.modelType;

		GL11.glPushMatrix();
		mc.renderEngine.bindTexture(TextureTable.getTextureFromMeta(render));

		float scale = 75f;
		int height = 80;

		if((render == 4) || (render == 5)|| (render == 7) || (render == 8)|| (render == 9)){
			height = 40;
		}

		if((render == 8)|| (render == 9)){
			scale = 60f;
		}

		GL11.glTranslatef((this.width / 2) - 150, (this.height / 2) - height, 40);
		GL11.glScaled(scale, scale, -scale);

		GL11.glRotatef(5, 1f, 0f, 0f);
		GL11.glRotatef(rotationCounter++, 0, 1, 0);

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
		if(playerOpenGui != null)
		{
			try{
				if ((nameOfDeathPlayer != null) && (nameOfDeathPlayer.length() > 0))
				{
					resourcelocation = AbstractClientPlayer.getLocationSkin(nameOfDeathPlayer);
					AbstractClientPlayer.getDownloadImageSkin(resourcelocation, nameOfDeathPlayer);

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
}
