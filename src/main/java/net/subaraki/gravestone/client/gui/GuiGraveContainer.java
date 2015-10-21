package net.subaraki.gravestone.client.gui;

import static net.subaraki.gravestone.util.Constants.ICON_SKULL_0;
import static net.subaraki.gravestone.util.Constants.ICON_SKULL_1;
import static net.subaraki.gravestone.util.Constants.ICON_SKULL_2;
import static net.subaraki.gravestone.util.Constants.ICON_SKULL_3;
import static net.subaraki.gravestone.util.Constants.ICON_SKULL_4;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.client.model.ModelHead;
import net.subaraki.gravestone.common.network.PacketSwitchSlotLayout;
import net.subaraki.gravestone.handler.GraveTextHandler;
import net.subaraki.gravestone.handler.ModelHandler;
import net.subaraki.gravestone.handler.TextureHandler;
import net.subaraki.gravestone.inventory.ContainerGrave;
import net.subaraki.gravestone.inventory.slot.SlotArmorGrave;
import net.subaraki.gravestone.inventory.slot.SlotGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import net.subaraki.gravestone.util.GraveUtility;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiGraveContainer extends GuiContainer{

	private short rotationCounter = 0;

	public String gravetext = "" ;

	public EntityPlayer deathPlayer;
	public EntityPlayer playerOpenGui;

	public String nameOfDeathPlayer;

	private TileEntityGravestone te;

	private String tabText = "MineCraft";

	private static final ResourceLocation graveGui = new ResourceLocation("grave:textures/entity/tile/grave_chest.png");
	private static final ResourceLocation boots = new ResourceLocation("minecraft:textures/items/empty_armor_slot_boots.png");
	private static final ResourceLocation legs = new ResourceLocation("minecraft:textures/items/empty_armor_slot_leggings.png");
	private static final ResourceLocation chest = new ResourceLocation("minecraft:textures/items/empty_armor_slot_chestplate.png");
	private static final ResourceLocation helm = new ResourceLocation("minecraft:textures/items/empty_armor_slot_helmet.png");

	private ModelHead modelhead = new ModelHead();

	private ResourceLocation texture;

	public GuiGraveContainer(EntityPlayer player, TileEntityGravestone grave ) {
		super(new ContainerGrave(player.inventory, grave, player));

		deathPlayer = player.worldObj.getPlayerEntityByName(grave.playername);
		playerOpenGui = player;

		nameOfDeathPlayer = grave.playername;

		te = grave;
		tabText = "Tab " + (te.tab + 1);

		updateLayout(te.tab);
		updateInventory(te.tab);
		
		this.xSize = 197;
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

		if(te.tab == 0){
			for(int offset = 0; offset <9; offset++)
				drawTexturedModalRect(posX+7 + offset*18, posY+14, 200, 0, 18, 72);
			//			
			drawTexturedModalRect(posX+173, posY+14, 200, 0, 18, 72);
				
			for(int id = 0; id < 4; id++){
				 IIcon iicon = ItemArmor.func_94602_b(i);
		            if (iicon != null)
		            {
		                GL11.glDisable(GL11.GL_LIGHTING);
		                GL11.glEnable(GL11.GL_BLEND); // Forge: Blending needs to be enabled for this.
		                this.mc.getTextureManager().bindTexture(TextureMap.locationItemsTexture);
		                this.drawTexturedModelRectFromIcon(posX, posY, iicon, 16, 16);
		                GL11.glDisable(GL11.GL_BLEND); // Forge: And clean that up
		                GL11.glEnable(GL11.GL_LIGHTING);
		            }
			}

		}else{
			for(int offset = 0; offset <10; offset++)
				drawTexturedModalRect(posX+9 + offset*18, posY+14, 200, 0, 18, 72);
		}

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

		offsetX += offsetSize;

		for(int i = 0 ; i < 5 ;  i ++){
			buttonList.add(new GuiTabButton(i, x - 33 + offsetX , y, 35 , 20, "", te.tab == i, getIconForTab(i), fontRendererObj));
			offsetX += offsetSize;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		super.actionPerformed(button);

		updateLayout(button.id);

		updateInventory(button.id);

		tabText = "Tab " + (button.id + 1);

		initGui();
	}

	private void updateInventory(int i){

		GraveStones.instance.network.sendToServer(new PacketSwitchSlotLayout(te.xCoord, te.yCoord, te.zCoord, i));

		te.updateSlotContents(i);

		te.tab = i;
	}

	private ItemStack getIconForTab(int b){
		switch (b) {
		case 0:
			return ICON_SKULL_0;
		case 1 :
			return ICON_SKULL_1;
		case 2 :
			return ICON_SKULL_2;
		case 3 : 
			return ICON_SKULL_3;
		case 4 : 
			return ICON_SKULL_4;
		default : 
			return ICON_SKULL_0;
		}
	}

	private void updateLayout(int buttonID){

		if(buttonID == 0){
			for(int i = 0; i < inventorySlots.inventorySlots.size(); i++){
				Slot slot = (Slot)((ContainerGrave)inventorySlots).inventorySlots.get(i);
				if(slot.inventory instanceof TileEntityGravestone){

					int slotIndex = i;
					if((i == (10 * ((slotIndex+1)/10)) -1))
						slot.xDisplayPosition = 12 + ((slotIndex - ((slotIndex/10)*10)) * 18);
					else
						slot.xDisplayPosition = 8 + ((slotIndex - ((slotIndex/10)*10)) * 18);
					slotIndex++;
				}

			}
		}else{
			for(int i = 0; i < inventorySlots.inventorySlots.size(); i++){
				Slot slot = (Slot)((ContainerGrave)inventorySlots).inventorySlots.get(i);
				if(slot.inventory instanceof TileEntityGravestone){
					int slotIndex = i;

					if((i == (10 * ((slotIndex+1)/10)) -1))
						slot.xDisplayPosition = 10 + ((slotIndex - ((slotIndex/10)*10)) * 18);
					else
						slot.xDisplayPosition = 10 + ((slotIndex - ((slotIndex/10)*10)) * 18);

					slotIndex++;
				}
			}
		}
	}

	private Slot changeSlot(int id, int x, int y, final int armorIdentifier, boolean creative, boolean armor){
		return null;
	}
}
