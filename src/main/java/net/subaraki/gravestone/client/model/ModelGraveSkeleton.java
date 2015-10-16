package net.subaraki.gravestone.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelGraveSkeleton extends ModelBase {

	ModelRenderer Shape18;
	ModelRenderer Shape19;
	ModelRenderer Shape20;
	ModelRenderer Shape21;
	ModelRenderer Shape22;
	ModelRenderer Shape23;

	public ModelGraveSkeleton() {
		textureHeight = 64;
		textureWidth = 64;

		Shape18 = new ModelRenderer(this, 0, 0);
		Shape18.addBox(-2.5F, -4F, -2F, 5, 5, 5);
		Shape18.setRotationPoint(0F, -4F, 2F);
		Shape18.setTextureSize(64, 64);
		Shape18.mirror = true;
		setRotation(Shape18, -0.2268928F, 0F, 0F);
		Shape19 = new ModelRenderer(this, 0, 10);
		Shape19.addBox(-2.5F, -3F, -1F, 5, 7, 2);
		Shape19.setRotationPoint(0F, 0F, 0F);
		Shape19.setTextureSize(64, 64);
		Shape19.mirror = true;
		setRotation(Shape19, 0F, 0F, 0F);
		Shape20 = new ModelRenderer(this, 20, 0);
		Shape20.addBox(-1.5F, 0F, 0F, 1, 5, 1);
		Shape20.setRotationPoint(-2F, -2F, -1F);
		Shape20.setTextureSize(64, 64);
		Shape20.mirror = true;
		setRotation(Shape20, 0.5759587F, 0F, 0F);
		Shape21 = new ModelRenderer(this, 20, 0);
		Shape21.addBox(0.5F, 0F, 0F, 1, 5, 1);
		Shape21.setRotationPoint(2F, -2F, -1F);
		Shape21.setTextureSize(64, 64);
		Shape21.mirror = true;
		setRotation(Shape21, 0.5759587F, 0F, 0F);
		Shape22 = new ModelRenderer(this, 24, 0);
		Shape22.addBox(-3F, -0.3F, 0F, 4, 1, 1);
		Shape22.setRotationPoint(2.5F, 2F, 2F);
		Shape22.setTextureSize(64, 64);
		Shape22.mirror = true;
		setRotation(Shape22, 0F, 0F, 0.4363323F);
		Shape23 = new ModelRenderer(this, 24, 0);
		Shape23.addBox(-1F, -0.3F, 0F, 4, 1, 1);
		Shape23.setRotationPoint(-2.5F, 2F, 2F);
		Shape23.setTextureSize(64, 64);
		Shape23.mirror = true;
		setRotation(Shape23, 0F, 0F, -0.4363323F);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void render(float f5){
		Shape18.render(f5);
		Shape19.render(f5);
		Shape20.render(f5);
		Shape21.render(f5);
		Shape22.render(f5);
		Shape23.render(f5);
	}
}
