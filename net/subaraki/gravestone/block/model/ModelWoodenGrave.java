package net.subaraki.gravestone.block.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelWoodenGrave extends ModelBase {

	ModelRenderer Cross1;
	ModelRenderer Cross2;
	ModelRenderer Rope1;
	ModelRenderer Rope2;
	ModelRenderer Rope3;
	ModelRenderer Rope4;
	ModelRenderer ground1;
	ModelRenderer Ground2;
	ModelRenderer ground3;
	ModelRenderer ground4;
	ModelRenderer ground5;
	ModelRenderer ground6;
	ModelRenderer ground7;
	ModelRenderer ground8;

	public ModelWoodenGrave() {

		textureHeight = 64;
		textureWidth = 64;

		Cross1 = new ModelRenderer(this, 0, 0);
		Cross1.addBox(-1F, -4F, -1F, 2, 17, 2);
		Cross1.setRotationPoint(0F, 11F, 0F);
		Cross1.setTextureSize(64, 64);
		Cross1.mirror = true;
		setRotation(Cross1, 0F, 0F, 0.0523599F);
		Cross2 = new ModelRenderer(this, 8, 0);
		Cross2.addBox(-6F, 0F, -0.5F, 12, 1, 1);
		Cross2.setRotationPoint(0F, 12F, 0F);
		Cross2.setTextureSize(64, 64);
		Cross2.mirror = true;
		setRotation(Cross2, 0F, 0F, -0.1047198F);
		Rope1 = new ModelRenderer(this, 8, 2);
		Rope1.addBox(-1.3F, 1F, 0.5F, 3, 1, 1);
		Rope1.setRotationPoint(-1F, 11F, 0F);
		Rope1.setTextureSize(64, 64);
		Rope1.mirror = true;
		setRotation(Rope1, 0F, 0F, -0.5235988F);
		Rope2 = new ModelRenderer(this, 8, 2);
		Rope2.addBox(0F, 0F, 0.3F, 3, 1, 1);
		Rope2.setRotationPoint(-1F, 11F, 0F);
		Rope2.setTextureSize(64, 64);
		Rope2.mirror = true;
		setRotation(Rope2, 0F, 0F, 0.5759587F);
		Rope3 = new ModelRenderer(this, 8, 2);
		Rope3.addBox(0F, 0F, -1.4F, 3, 1, 1);
		Rope3.setRotationPoint(-1F, 11F, 0F);
		Rope3.setTextureSize(64, 64);
		Rope3.mirror = true;
		setRotation(Rope3, 0F, 0F, 0.5759587F);
		Rope4 = new ModelRenderer(this, 8, 2);
		Rope4.addBox(-1.3F, 1F, -1.2F, 3, 1, 1);
		Rope4.setRotationPoint(-1F, 11F, 0F);
		Rope4.setTextureSize(64, 64);
		Rope4.mirror = true;
		setRotation(Rope4, 0F, 0F, -0.5235988F);
		ground1 = new ModelRenderer(this, 46, 8);
		ground1.addBox(-3F, -1F, -1F, 5, 2, 4);
		ground1.setRotationPoint(0F, 23F, -1F);
		ground1.setTextureSize(64, 64);
		ground1.mirror = true;
		setRotation(ground1, 0F, 0F, 0F);
		Ground2 = new ModelRenderer(this, 34, 0);
		Ground2.addBox(-5F, 0F, -4.5F, 8, 1, 7);
		Ground2.setRotationPoint(0F, 23F, 0F);
		Ground2.setTextureSize(64, 64);
		Ground2.mirror = true;
		setRotation(Ground2, 0F, 0F, 0F);
		ground3 = new ModelRenderer(this, 41, 0);
		ground3.addBox(0F, 0F, 0F, 1, 1, 1);
		ground3.setRotationPoint(4F, 23F, 0F);
		ground3.setTextureSize(64, 64);
		ground3.mirror = true;
		setRotation(ground3, 0F, 0F, 0F);
		ground4 = new ModelRenderer(this, 41, 0);
		ground4.addBox(0F, 0F, 0F, 1, 1, 1);
		ground4.setRotationPoint(0F, 23F, -6F);
		ground4.setTextureSize(64, 64);
		ground4.mirror = true;
		setRotation(ground4, 0F, 0F, 0F);
		ground5 = new ModelRenderer(this, 41, 0);
		ground5.addBox(0F, 0F, 0F, 1, 1, 1);
		ground5.setRotationPoint(-7F, 23F, 5F);
		ground5.setTextureSize(64, 64);
		ground5.mirror = true;
		setRotation(ground5, 0F, 0F, 0F);
		ground6 = new ModelRenderer(this, 41, 0);
		ground6.addBox(-6F, 0F, -5F, 1, 1, 1);
		ground6.setRotationPoint(0F, 23F, 0F);
		ground6.setTextureSize(64, 64);
		ground6.mirror = true;
		setRotation(ground6, 0F, 0F, 0F);
		ground7 = new ModelRenderer(this, 41, 0);
		ground7.addBox(3F, 0F, 4F, 1, 1, 1);
		ground7.setRotationPoint(0F, 23F, 0F);
		ground7.setTextureSize(64, 64);
		ground7.mirror = true;
		setRotation(ground7, 0F, 0F, 0F);
		ground8 = new ModelRenderer(this, 41, 0);
		ground8.addBox(-2F, 0.5F, 2F, 3, 1, 1);
		ground8.setRotationPoint(0F, 23F, 0F);
		ground8.setTextureSize(64, 64);
		ground8.mirror = true;
		setRotation(ground8, 0F, 0F, 0F);

	}

	public void render(float f5){
		Cross1.render(f5);
		Cross2.render(f5);
		Rope1.render(f5);
		Rope2.render(f5);
		Rope3.render(f5);
		Rope4.render(f5);
		ground1.render(f5);
		Ground2.render(f5);
		ground3.render(f5);
		ground4.render(f5);
		ground5.render(f5);
		ground6.render(f5);
		ground7.render(f5);
		ground8.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
