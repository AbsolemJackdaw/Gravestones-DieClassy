package net.subaraki.gravestone.handler;

import net.minecraft.util.ResourceLocation;

public class TextureHandler {


	private static final String path = "grave:textures/entity/tile/";
	private static final ResourceLocation graveTexture = new ResourceLocation(path + "grave_stone.png");
	private static final ResourceLocation tombTexture = new ResourceLocation(path + "grave_zerk.png");
	private static final ResourceLocation pillarTexture = new ResourceLocation(path + "grave_pillar.png");
	private static final ResourceLocation angelTexture = new ResourceLocation(path + "grave_angel.png");
	private static final ResourceLocation woodTexture = new ResourceLocation(path + "grave_wood.png");
	private static final ResourceLocation knightTexture = new ResourceLocation(path + "grave_knight.png");

	public static ResourceLocation getTextureFromMeta(int meta) {

		ResourceLocation resourcelocation = null;

		switch(meta){
		case 1:
			resourcelocation = graveTexture;
			break;
		case 2:
			resourcelocation = tombTexture ;
			break;
		case 3 :
			resourcelocation = graveTexture;
			break;
		case 4:
			resourcelocation = pillarTexture;
			break;
		case 5:
			resourcelocation = pillarTexture;
			break;
		case 6:
			resourcelocation =  woodTexture;
			break;
		case 7:
			resourcelocation = pillarTexture;
			break;
		case 8:
			resourcelocation = angelTexture;
			break;
		case 9:
			resourcelocation = knightTexture;
			break;
		default:
			resourcelocation = graveTexture;
			break;
		}

		return resourcelocation;
	}

}
