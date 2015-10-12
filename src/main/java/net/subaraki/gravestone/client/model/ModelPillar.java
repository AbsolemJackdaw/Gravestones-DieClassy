package net.subaraki.gravestone.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelPillar extends ModelBase {

	ModelRenderer Base4;
	ModelRenderer Base5;
	ModelRenderer Base6;
	ModelRenderer Base7;
	ModelRenderer Base8;
	ModelRenderer Pillar1;
	ModelRenderer Base9;
	ModelRenderer Base10;
	ModelRenderer Pillar2;
	ModelRenderer Pillar3;
	ModelRenderer Pillar4;
	ModelRenderer Pillar5;
	ModelRenderer Pillar6;
	ModelRenderer Shape18;
	ModelRenderer Shape19;
	ModelRenderer Shape20;
	ModelRenderer Shape21;
	ModelRenderer Shape22;
	ModelRenderer Shape23;

	public ModelPillar() {

		textureHeight = 64;
		textureWidth = 64;

		Base4 = new ModelRenderer(this, 0, 51);
		Base4.addBox(0F, 0F, 0F, 12, 1, 12);
		Base4.setRotationPoint(-6F, 23F, -6F);
		Base4.setTextureSize(64, 64);
		Base4.mirror = true;
		setRotation(Base4, 0F, 0F, 0F);
		Base5 = new ModelRenderer(this, 0, 52);
		Base5.addBox(0F, 0F, 0F, 11, 1, 11);
		Base5.setRotationPoint(-5.5F, 22.5F, -5.5F);
		Base5.setTextureSize(64, 64);
		Base5.mirror = true;
		setRotation(Base5, 0F, 0F, 0F);
		Base6 = new ModelRenderer(this, 0, 53);
		Base6.addBox(-5F, 0F, -5F, 10, 1, 10);
		Base6.setRotationPoint(0F, 22F, 0F);
		Base6.setTextureSize(64, 64);
		Base6.mirror = true;
		setRotation(Base6, 0F, 0F, 0F);
		Base7 = new ModelRenderer(this, 0, 54);
		Base7.addBox(-4.5F, 0F, -4.5F, 9, 1, 9);
		Base7.setRotationPoint(0F, 21.5F, 0F);
		Base7.setTextureSize(64, 64);
		Base7.mirror = true;
		setRotation(Base7, 0F, 0F, 0F);
		Base8 = new ModelRenderer(this, 0, 55);
		Base8.addBox(0F, 0F, 0F, 8, 1, 8);
		Base8.setRotationPoint(-4F, 21F, -4F);
		Base8.setTextureSize(64, 64);
		Base8.mirror = true;
		setRotation(Base8, 0F, 0F, 0F);
		Pillar1 = new ModelRenderer(this, 0, 20);
		Pillar1.addBox(0F, 0F, 0F, 7, 16, 2);
		Pillar1.setRotationPoint(-3.5F, 5F, -1F);
		Pillar1.setTextureSize(64, 64);
		Pillar1.mirror = true;
		setRotation(Pillar1, 0F, 0F, 0F);
		Base9 = new ModelRenderer(this, 0, 55);
		Base9.addBox(0F, 0F, 0F, 8, 1, 8);
		Base9.setRotationPoint(-4F, 4.5F, -4F);
		Base9.setTextureSize(64, 64);
		Base9.mirror = true;
		setRotation(Base9, 0F, 0F, 0F);
		Base10 = new ModelRenderer(this, 0, 54);
		Base10.addBox(0F, 0F, 0F, 9, 1, 9);
		Base10.setRotationPoint(-4.5F, 4F, -4.5F);
		Base10.setTextureSize(64, 64);
		Base10.mirror = true;
		setRotation(Base10, 0F, 0F, 0F);
		Pillar2 = new ModelRenderer(this, 18, 20);
		Pillar2.addBox(-1F, 5F, -3.5F, 2, 16, 7);
		Pillar2.setRotationPoint(0F, 0F, 0F);
		Pillar2.setTextureSize(64, 64);
		Pillar2.mirror = true;
		setRotation(Pillar2, 0F, 0F, 0F);
		Pillar3 = new ModelRenderer(this, 47, 20);
		Pillar3.addBox(-3F, 5F, -2F, 1, 16, 4);
		Pillar3.setRotationPoint(0F, 0F, 0F);
		Pillar3.setTextureSize(64, 64);
		Pillar3.mirror = true;
		setRotation(Pillar3, 0F, 0F, 0F);
		Pillar4 = new ModelRenderer(this, 47, 20);
		Pillar4.addBox(2F, 5F, -2F, 1, 16, 4);
		Pillar4.setRotationPoint(0F, 0F, 0F);
		Pillar4.setTextureSize(64, 64);
		Pillar4.mirror = true;
		setRotation(Pillar4, 0F, 0F, 0F);
		Pillar5 = new ModelRenderer(this, 37, 20);
		Pillar5.addBox(-2F, 5F, 2F, 4, 16, 1);
		Pillar5.setRotationPoint(0F, 0F, 0F);
		Pillar5.setTextureSize(64, 64);
		Pillar5.mirror = true;
		setRotation(Pillar5, 0F, 0F, 0F);
		Pillar6 = new ModelRenderer(this, 37, 20);
		Pillar6.addBox(-2F, 5F, -3F, 4, 16, 1);
		Pillar6.setRotationPoint(0F, 0F, 0F);
		Pillar6.setTextureSize(64, 64);
		Pillar6.mirror = true;
		setRotation(Pillar6, 0F, 0F, 0F);
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

	public void render(float f5){
		Base4.render(f5);
		Base5.render(f5);
		Base6.render(f5);
		Base7.render(f5);
		Base8.render(f5);
		Base9.render(f5);
		Base10.render(f5);
		Pillar1.render(f5);
		Pillar2.render(f5);
		Pillar3.render(f5);
		Pillar4.render(f5);
		Pillar5.render(f5);
		Pillar6.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
