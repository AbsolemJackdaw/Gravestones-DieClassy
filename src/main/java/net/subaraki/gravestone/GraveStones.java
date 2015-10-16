package net.subaraki.gravestone;

import static net.subaraki.gravestone.util.Constants.CLIENT;
import static net.subaraki.gravestone.util.Constants.MODID;
import static net.subaraki.gravestone.util.Constants.MOD_NAME;
import static net.subaraki.gravestone.util.Constants.SERVER;
import static net.subaraki.gravestone.util.Constants.VERSION;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.subaraki.gravestone.block.BlockGrave;
import net.subaraki.gravestone.common.CommonProxy;
import net.subaraki.gravestone.common.network.PacketSwitchSlotLayout;
import net.subaraki.gravestone.common.network.PacketSwitchSlotLayout.PacketSwitchSlotLayoutHandler;
import net.subaraki.gravestone.common.network.PacketSyncGraveModel;
import net.subaraki.gravestone.common.network.PacketSyncGraveModel.PacketSyncGraveModelHandler;
import net.subaraki.gravestone.common.network.PacketSyncModelToClient;
import net.subaraki.gravestone.common.network.PacketSyncModelToClient.PacketSyncModelToClientHandler;
import net.subaraki.gravestone.handler.ConfigHandler;
import net.subaraki.gravestone.handler.GravestoneEventHandler;
import net.subaraki.gravestone.handler.GuiHandler;
import net.subaraki.gravestone.item.ItemDecoGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import net.subaraki.gravestone.util.Constants;
import net.subaraki.gravestone.util.GraveUtility;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = MODID, name = MOD_NAME, version = VERSION)
public class GraveStones {


	@SidedProxy(serverSide = SERVER, clientSide = CLIENT)
	public static CommonProxy proxy;

	@Mod.Instance(MODID)
	public static GraveStones instance;

	/**
	 * A network channel to be used to handle packets specific to the Telepads mod.
	 */
	public SimpleNetworkWrapper network;

	public static Block graveStone;

	public static boolean hasTiCo = false;
	public static boolean hasRpgI = false;
	public static boolean hasBaub = false;
	public static boolean hasGal_Craft = false;
	public static boolean hasMari_Cul = false;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		new GraveUtility(); //init instance
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("gravestones");
		network.registerMessage(PacketSyncGraveModelHandler.class, PacketSyncGraveModel.class, 0, Side.SERVER);
		network.registerMessage(PacketSwitchSlotLayoutHandler.class, PacketSwitchSlotLayout.class, 1, Side.SERVER);
		network.registerMessage(PacketSyncModelToClientHandler.class, PacketSyncModelToClient.class, 2, Side.CLIENT);

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		ConfigHandler.instance.loadConfig(event.getSuggestedConfigurationFile());

		graveStone = new BlockGrave(Material.rock).setBlockName("gravestone").setCreativeTab(CreativeTabs.tabDecorations);

		GameRegistry.registerTileEntity(TileEntityGravestone.class, "TileEntityGraveStone");
		GameRegistry.registerBlock(graveStone, ItemDecoGrave.class, "graveStone");

		new GravestoneEventHandler();

		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event){

		hasRpgI = GraveUtility.findClass("rpgInventory.RpgInventoryMod", "Rpg Inventory");
		hasTiCo = GraveUtility.findClass("tconstruct.TConstruct", "Tinkers Construct");
		hasBaub = GraveUtility.findClass("baubles.Baubles", "Baubel Inventory");
		hasGal_Craft = GraveUtility.findClass("micdoodle8.mods.galacticraft.core.GalacticraftCore", "GalacticCraft");
		hasMari_Cul = GraveUtility.findClass("mariculture.Mariculture" , "Mariculture");

	}

	/**
	 * Prints a specified message to the console. If the user has disabled debug messages,
	 * messages sent through this method will not be logged.
	 * 
	 * @param message : The message to send to the console.
	 */
	public static void printDebugMessage (String message) {

		if (ConfigHandler.allowDebug)
			Constants.LOG.info(message);
	}

}
