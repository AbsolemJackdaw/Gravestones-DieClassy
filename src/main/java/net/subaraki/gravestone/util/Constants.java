package net.subaraki.gravestone.util;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.block.BlockSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.client.model.ModelAngel;
import net.subaraki.gravestone.client.model.ModelCubeWorld;
import net.subaraki.gravestone.client.model.ModelGraveSkeleton;
import net.subaraki.gravestone.client.model.ModelGraveStone;
import net.subaraki.gravestone.client.model.ModelKnight;
import net.subaraki.gravestone.client.model.ModelPillar;
import net.subaraki.gravestone.client.model.ModelStoneCross;
import net.subaraki.gravestone.client.model.ModelTomb;
import net.subaraki.gravestone.client.model.ModelWoodenGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {

	 public static final String MODID = "gravestonemod";
	    public static final String MOD_NAME = "Gravestones";
	    public static final String VERSION = "1.7.10";
	    public static final String SERVER = "net.subaraki.gravestone.common.CommonProxy";
	    public static final String CLIENT = "net.subaraki.gravestone.client.ClientProxy";
	    public static final String FACTORY = "net.subaraki.gravestone.client.gui.GraveGuiFactory";
	    public static final String DEPENDENCY = "";
	    public static final Logger LOG = LogManager.getLogger(MOD_NAME);

	    public static final ModelAngel MODEL_ANGEL = new ModelAngel();
		public static final ModelGraveSkeleton MODEL_SKELETON = new ModelGraveSkeleton();
		public static final ModelGraveStone MODEL_GRAVESTONE = new ModelGraveStone();
		public static final ModelKnight MODEL_KNIGHT = new ModelKnight();
		public static final ModelPillar MODEL_PILLAR = new ModelPillar();
		public static final ModelStoneCross MODEL_CROSS = new ModelStoneCross();
		public static final ModelTomb MODEL_TOMB = new ModelTomb();
		public static final ModelWoodenGrave MODEL_WOOD = new ModelWoodenGrave();
		public static ModelCubeWorld angelStatue;
		public static ModelCubeWorld barrel;
		
		public static final ItemStack ICON_SKULL_0 = new ItemStack(Items.skull, 1, 0);
		public static final ItemStack ICON_SKULL_1 = new ItemStack(Items.skull, 1, 1);
		public static final ItemStack ICON_SKULL_2 = new ItemStack(Items.skull, 1, 2);
		public static final ItemStack ICON_SKULL_3 = new ItemStack(Items.skull, 1, 3);
		public static final ItemStack ICON_SKULL_4 = new ItemStack(Items.skull, 1, 4);
		
		public static final TileEntityGravestone GRAVE_PLACEHOLDER = new TileEntityGravestone();
		
		public static HashMap<UUID, ItemStack[]> armorFromPlayer = new HashMap<UUID, ItemStack[]>();
}
