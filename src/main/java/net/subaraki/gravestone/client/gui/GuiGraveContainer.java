package net.subaraki.gravestone.client.gui;

import static net.subaraki.gravestone.util.Constants.BAUBEL;
import static net.subaraki.gravestone.util.Constants.GALACTICRAFT;
import static net.subaraki.gravestone.util.Constants.ICON_BAUBLES;
import static net.subaraki.gravestone.util.Constants.ICON_GALACTICRAFT;
import static net.subaraki.gravestone.util.Constants.ICON_MARICULTURE;
import static net.subaraki.gravestone.util.Constants.ICON_RPGI;
import static net.subaraki.gravestone.util.Constants.ICON_TCON;
import static net.subaraki.gravestone.util.Constants.ICON_VANILLA;
import static net.subaraki.gravestone.util.Constants.MARICULTURE;
import static net.subaraki.gravestone.util.Constants.RPGI;
import static net.subaraki.gravestone.util.Constants.TC;
import static net.subaraki.gravestone.util.Constants.VANILLA;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.client.model.ModelHead;
import net.subaraki.gravestone.common.network.PacketSwitchSlotLayout;
import net.subaraki.gravestone.handler.GraveTextHandler;
import net.subaraki.gravestone.handler.ModelHandler;
import net.subaraki.gravestone.handler.TextureHandler;
import net.subaraki.gravestone.inventory.ContainerGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import net.subaraki.gravestone.util.GraveUtility;

import org.lwjgl.opengl.GL11;

public class GuiGraveContainer extends GuiContainer{

	private short rotationCounter = 0;

	public String gravetext = "" ;

	public EntityPlayer deathPlayer;
	public EntityPlayer playerOpenGui;

	public String nameOfDeathPlayer;

	private TileEntityGravestone te;

	private String tabText = "MineCraft";

	private static final ResourceLocation graveGui = new ResourceLocation("grave:textures/entity/tile/grave_chest.png");

	private ModelHead modelhead = new ModelHead();

	private ResourceLocation texture;

	public GuiGraveContainer(EntityPlayer player, TileEntityGravestone grave ) {
		super(new ContainerGrave(player.inventory, grave, player));

		deathPlayer = player.worldObj.getPlayerEntityByName(grave.playername);
		playerOpenGui = player;

		nameOfDeathPlayer = grave.playername;

		te = grave;

		this.xSize = 198;
		this.ySize = 186;

		if(grave != null) {
			if(grave.message1.length() <= 0)
			{
				grave.isDecorativeGrave = false;

				if(nameOfDeathPlayer.equals("!Empty!"))
					gravetext = "The Grave is empty !";
				else
					gravetext = GraveTextHandler.getStringFromMeta(nameOfDeathPlayer, te.modelType);
			}else{
				gravetext = grave.message1+grave.playername + grave.message2;
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

		fontRendererObj.drawString(tabText, ((this.width/2)-(xSize/2)) + 5, ((this.height/2)-(ySize/2)) + 5, 0xffffff);

		int render = te.modelType;

		GL11.glPushMatrix();
		mc.renderEngine.bindTexture(TextureHandler.getTextureFromMeta(render));

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
		if(render == 8 ){
			GL11.glScalef(1, -1, 1);
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

		ModelHandler.renderModelFromType(render);
		GL11.glPopMatrix();

		if(render == 5){
			renderBust();
		}
	}

	private void renderBust()
	{
		GL11.glPushMatrix();

		mc.renderEngine.bindTexture( GraveUtility.instance.processPlayerTexture(nameOfDeathPlayer));

		GL11.glTranslatef((this.width / 2)-150, (this.height / 2)-40, 40);
		GL11.glScaled(50, 50, -50);
		GL11.glRotatef(5, 1f, 0f, 0f);
		GL11.glRotatef(rotationCounter, 0, 1, 0);

		ModelHandler.modelhead.renderHead(0.0625f);
		GL11.glPopMatrix();

	}


	@Override
	public void initGui() {
		super.initGui();

		this.buttonList.clear();
		int offsetX = 0;
		int offsetSize = 33;
		int x = ((this.width/2) - (xSize/2)) + 4;
		int y = ((this.height/2) - (ySize/2)) - 19;

		buttonList.add(new GuiTabButton(0, x     , y, 35 , 20, "", te.tab == 0, ICON_VANILLA, fontRendererObj));
		offsetX += offsetSize;
		if(GraveStones.hasRpgI){
			buttonList.add(new GuiTabButton(1, x + offsetX , y, 35 , 20, "", te.tab == 1, ICON_RPGI, fontRendererObj));
			offsetX += offsetSize;
		}
		if(GraveStones.hasTiCo){
			buttonList.add(new GuiTabButton(2, x + offsetX , y, 35 , 20, "", te.tab == 2, ICON_TCON,fontRendererObj));
			offsetX += offsetSize;
		}

		if(GraveStones.hasBaub){
			buttonList.add(new GuiTabButton(3, x + offsetX , y, 35 , 20, "", te.tab == 3, ICON_BAUBLES,fontRendererObj));
			offsetX += offsetSize;
		}
		if(GraveStones.hasGal_Craft){
			buttonList.add(new GuiTabButton(4, x + offsetX , y, 35 , 20, "", te.tab == 4, ICON_GALACTICRAFT,fontRendererObj));
			offsetX +=offsetSize;
		}
		if(GraveStones.hasMari_Cul){
			buttonList.add(new GuiTabButton(5, x + offsetX , y, 35 , 20, "", te.tab == 5, ICON_MARICULTURE,fontRendererObj));
			offsetX +=offsetX;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		super.actionPerformed(button);

		updateInventory((byte)button.id);

		if(button.id == 0)
			tabText = "MineCraft";

		if(button.id == 1)
			tabText = "Rpg Inventory";

		if(button.id == 2)
			tabText = "Tinkers Construct";

		if(button.id == 3)
			tabText = "Baubel Inventory";

		if(button.id == 4)
			tabText = "Galacticraft";

		if(button.id == 5)
			tabText = "Mariculture";

		initGui();
	}

	private void updateInventory(byte i){

		GraveStones.instance.network.sendToServer(new PacketSwitchSlotLayout(te.xCoord, te.yCoord, te.zCoord, i));

		switch(i){
		case 0:
			te.changeSlotLayout(VANILLA);
			break;
		case 1:
			te.changeSlotLayout(RPGI);
			break;
		case 2:
			te.changeSlotLayout(TC);
			break;
		case 3:
			te.changeSlotLayout(BAUBEL);
			break;
		case 4:
			te.changeSlotLayout(GALACTICRAFT);
			break;
		case 5:
			te.changeSlotLayout(MARICULTURE);
			break;
		}

		te.tab = i;
	}
}
