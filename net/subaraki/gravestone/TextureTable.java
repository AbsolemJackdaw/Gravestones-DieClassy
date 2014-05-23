package net.subaraki.gravestone;

import net.minecraft.util.ResourceLocation;

public class TextureTable {


	private static final ResourceLocation graveTexture = new ResourceLocation("subaraki:grave/gravestone.png");
	private static final ResourceLocation tombTexture = new ResourceLocation("subaraki:grave/gravezerk.png");
	private static final ResourceLocation pillarTexture = new ResourceLocation("subaraki:grave/gravepillar.png");
	private static final ResourceLocation angelTexture = new ResourceLocation("subaraki:grave/Angel.png");
	private static final ResourceLocation woodTexture = new ResourceLocation("subaraki:grave/gravewood.png");
	private static final ResourceLocation knightTexture = new ResourceLocation("subaraki:grave/knight.png");

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
