package net.subaraki.gravestone;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.block.BlockGrave;
import net.subaraki.gravestone.common.ServerProxy;
import net.subaraki.gravestone.handler.ConfigHandler;
import net.subaraki.gravestone.handler.GuiHandler;
import net.subaraki.gravestone.item.ItemDecoGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
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
	public static boolean hasBaubel = false;
	public static boolean hasGalacti = false;
	public static boolean hasMariCulture = false;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		ConfigHandler.instance.loadConfig(event.getSuggestedConfigurationFile());

		instance = this;
		channel =  NetworkRegistry.INSTANCE.newEventDrivenChannel("gravestone");

		graveStone = new BlockGrave(Material.rock).setBlockName("gravestone").setCreativeTab(CreativeTabs.tabDecorations);
		
		GameRegistry.registerTileEntity(TileEntityGravestone.class, "TileEntityGraveStone");
		
		GameRegistry.registerBlock(graveStone, ItemDecoGrave.class, "graveStone");
		
		GameRegistry.addRecipe(new ItemStack(graveStone,1,1) , new Object[]{
			"www"," w "," w ",'w', Blocks.cobblestone
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(graveStone,1,2) , new Object[]{new ItemStack(graveStone,1,1)});
		GameRegistry.addShapelessRecipe(new ItemStack(graveStone,1,3) , new Object[]{new ItemStack(graveStone,1,2)});
		GameRegistry.addShapelessRecipe(new ItemStack(graveStone,1,4) , new Object[]{new ItemStack(graveStone,1,3)});
		GameRegistry.addShapelessRecipe(new ItemStack(graveStone,1,5) , new Object[]{new ItemStack(graveStone,1,4)});
		GameRegistry.addShapelessRecipe(new ItemStack(graveStone,1,6) , new Object[]{new ItemStack(graveStone,1,5)});
		GameRegistry.addShapelessRecipe(new ItemStack(graveStone,1,7) , new Object[]{new ItemStack(graveStone,1,6)});
		GameRegistry.addShapelessRecipe(new ItemStack(graveStone,1,8) , new Object[]{new ItemStack(graveStone,1,7)});
		GameRegistry.addShapelessRecipe(new ItemStack(graveStone,1,9) , new Object[]{new ItemStack(graveStone,1,8)});
		GameRegistry.addShapelessRecipe(new ItemStack(graveStone,1,10) , new Object[]{new ItemStack(graveStone,1,9)});
		GameRegistry.addShapelessRecipe(new ItemStack(graveStone,1,1) , new Object[]{new ItemStack(graveStone,1,10)});

	}

	@EventHandler
	public void init(FMLInitializationEvent event){

		proxy.registerRendering();
		new net.subaraki.gravestone.handler.EventHandler();
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


		try {
			Class classTC = Class.forName("baubles.Baubles");

			if(classTC != null){
				FMLLog.getLogger().info("GraveStones Detected Baubel inventory. Inventory Content will be dumped into grave");
				hasBaubel = true;
			}

		} catch (Exception e){
		}
		
		try {
			Class classTC = Class.forName("micdoodle8.mods.galacticraft.core.GalacticraftCore");

			if(classTC != null){
				FMLLog.getLogger().info("GraveStones Detected GalacticCraft. Inventory Content will be dumped into grave");
				hasGalacti = true;
			}

		} catch (Exception e){
		}
		
		//TODO wait for Galactic Craft to implement iextended properties
		hasGalacti = false;
		
		
		try {
			Class classTC = Class.forName("mariculture.Mariculture");

			if(classTC != null){
				FMLLog.getLogger().info("GraveStones Detected Mariculture. Inventory Content will be dumped into grave");
				hasMariCulture = true;
			}

		} catch (Exception e){
		}
	}

}
