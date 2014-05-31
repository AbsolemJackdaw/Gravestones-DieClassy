package net.subaraki.gravestone;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.block.BlockGrave;
import net.subaraki.gravestone.block.inventory.TileEntityGrave;
import net.subaraki.gravestone.proxy.ServerProxy;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "gravestonemod", name = "GraveStone", version = "1.7.2")
public class GraveStones {

	@SidedProxy(serverSide = "net.subaraki.gravestone.proxy.ServerProxy", clientSide = "net.subaraki.gravestone.proxy.ClientProxy")
	public static ServerProxy proxy;

	public static Block graveStone;

	public static GraveStones instance;

	public static FMLEventChannel channel;

	public static boolean hasTC = false;
	public static boolean hasRpgI = false;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		instance = this;
		channel =  NetworkRegistry.INSTANCE.newEventDrivenChannel("gravestone");

		graveStone = new BlockGrave(Material.rock);
		GameRegistry.registerBlock(graveStone, "graveStone").setBlockName("gravestone");
		GameRegistry.registerTileEntity(TileEntityGrave.class, "TileEntityGraveStone");

	}

	@EventHandler
	public void init(FMLInitializationEvent event){

		proxy.registerRendering();
		new net.subaraki.gravestone.EventHandler();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		
		try {
			Class classRpgI = Class.forName("rpgInventory.RpgInventoryMod");

			if(classRpgI != null){
				FMLLog.getLogger().info("GraveStones Detected Rpg Inventory. Inventory Content will be dumped into grave");
				hasRpgI = true;
			}

		} catch (Exception e) {
		}

		try {
			Class classTC = Class.forName("tconstruct.TConstruct");

			if(classTC != null){
				FMLLog.getLogger().info("GraveStones Detected Tinkers Construct. Inventory Content will be dumped into grave");
				hasTC = true;
			}
			
		} catch (Exception e){
		}
	}

}
