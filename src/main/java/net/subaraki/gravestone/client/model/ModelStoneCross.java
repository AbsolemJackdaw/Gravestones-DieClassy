package net.subaraki.gravestone.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelStoneCross extends ModelBase{

	public ModelRenderer Shape1;
	public ModelRenderer Shape2;
	public ModelRenderer Shape3;
	public ModelRenderer Shape4;


	public ModelStoneCross() {

		textureHeight = 64;
		textureWidth = 64;

		Shape1 = new ModelRenderer(this, 0, 0+32);
		Shape1.addBox(-2F, 0F, -2F, 4, 4, 4);
		Shape1.setRotationPoint(0F, 19F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 8+32);
		Shape2.addBox(-3F, 0F, -3F, 6, 1, 6);
		Shape2.setRotationPoint(0F, 23F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 25, 0+32);
		Shape3.addBox(-1F, 0F, -1F, 2, 14, 2);
		Shape3.setRotationPoint(0F, 5F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 33, 0+32);
		Shape4.addBox(-0.5F, -5F, -2F, 1, 10, 2);
		Shape4.setRotationPoint(0F, 10F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, -1.570796F, 0F, 0F);

	}

	public void render(float f5){
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
