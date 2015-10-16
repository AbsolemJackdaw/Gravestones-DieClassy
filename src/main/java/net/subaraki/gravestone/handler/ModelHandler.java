package net.subaraki.gravestone.handler;

import net.subaraki.gravestone.client.model.ModelAngel;
import net.subaraki.gravestone.client.model.ModelGraveSkeleton;
import net.subaraki.gravestone.client.model.ModelGraveStone;
import net.subaraki.gravestone.client.model.ModelHead;
import net.subaraki.gravestone.client.model.ModelKnight;
import net.subaraki.gravestone.client.model.ModelPillar;
import net.subaraki.gravestone.client.model.ModelStoneCross;
import net.subaraki.gravestone.client.model.ModelTomb;
import net.subaraki.gravestone.client.model.ModelWoodenGrave;
import net.subaraki.gravestone.util.Constants;

public class ModelHandler {

	public static final ModelAngel angel = new ModelAngel();
	public static final ModelGraveSkeleton skeleton = new ModelGraveSkeleton();
	public static final ModelGraveStone gravestone = new ModelGraveStone();
	public static final ModelKnight knight = new ModelKnight();
	public static final ModelPillar pillar = new ModelPillar();
	public static final ModelStoneCross cross = new ModelStoneCross();
	public static final ModelTomb tomb = new ModelTomb();
	public static final ModelWoodenGrave wood = new ModelWoodenGrave();

	public static ModelHead modelhead = new ModelHead();
	public static ModelHead modelarmorhead = new ModelHead();
	public static ModelHead modelarmorchest = new ModelHead();

	public static final GraveArmorHandler helper = new GraveArmorHandler();

	public static void renderModelFromType(int modelType){

		switch (modelType) {
		case 1:
			cross.render(0.0625f);
			break;
		case 2:
			gravestone.render(0.0625f);
			break;
		case 3 :
			tomb.render(0.0625f);
			break;
		case 4:
			pillar.render(0.0625f);
			skeleton.render(0.0625f);
			break;
		case 5:
			pillar.render(0.0625f);
			break;
		case 6:
			wood.render(0.0625f);
		case 7:
			pillar.render(0.0625f);
			break;
		case 8:
			Constants.angelStatue.render();
			break;
		case 9:
			knight.render(0.0625f);
			break;
		case 10:
			Constants.barrel.render();
			break;

		default :
			cross.render(0.0625f);
			break;
		}
	}
}
