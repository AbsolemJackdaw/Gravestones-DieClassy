package net.subaraki.gravestone.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelKnight extends ModelBase  {

	ModelRenderer knight1;
	ModelRenderer knight2;
	ModelRenderer knight3;
	ModelRenderer knight4;
	ModelRenderer knight5;
	ModelRenderer knight6;
	ModelRenderer knight7;
	ModelRenderer knight8;
	ModelRenderer knight9;
	ModelRenderer knight10;
	ModelRenderer knight11;
	ModelRenderer knight12;
	ModelRenderer knight13;
	ModelRenderer knight14;
	ModelRenderer knight15;
	ModelRenderer knight17;
	ModelRenderer knight16;


	public ModelKnight() {

		textureHeight = 64;
		textureWidth = 64;

		knight1 = new ModelRenderer(this, 0, 0);
		knight1.addBox(-4F, -8F, -4F, 8, 8, 8);
		knight1.setRotationPoint(0F, 0F, 0F);
		knight1.setTextureSize(64, 64);
		knight1.mirror = true;
		setRotation(knight1, 0F, 0F, 0F);
		knight2 = new ModelRenderer(this, 16, 16);
		knight2.addBox(-4F, 0F, -2F, 8, 12, 4);
		knight2.setRotationPoint(0F, 0F, 0F);
		knight2.setTextureSize(64, 64);
		knight2.mirror = true;
		setRotation(knight2, 0F, 0F, 0F);
		knight3 = new ModelRenderer(this, 40, 16);
		knight3.addBox(-3F, -2F, -2F, 4, 12, 4);
		knight3.setRotationPoint(-5F, 2F, 0F);
		knight3.setTextureSize(64, 64);
		knight3.mirror = true;
		setRotation(knight3, -0.8726646F, -0.3490659F, -0.3665191F);
		knight4 = new ModelRenderer(this, 40, 16);
		knight4.addBox(-1F, -2F, -2F, 4, 12, 4);
		knight4.setRotationPoint(5F, 2F, 0F);
		knight4.setTextureSize(64, 64);
		knight4.mirror = true;
		setRotation(knight4, -0.8726646F, 0.3490659F, 0.3665191F);
		knight5 = new ModelRenderer(this, 0, 16);
		knight5.addBox(-2F, 0F, -2F, 4, 12, 4);
		knight5.setRotationPoint(-2F, 12F, 0F);
		knight5.setTextureSize(64, 64);
		knight5.mirror = true;
		setRotation(knight5, 0F, 0F, 0F);
		knight6 = new ModelRenderer(this, 0, 16);
		knight6.addBox(-2F, 0F, -2F, 4, 12, 4);
		knight6.setRotationPoint(2F, 12F, 0F);
		knight6.setTextureSize(64, 64);
		knight6.mirror = true;
		setRotation(knight6, 0F, 0F, 0F);
		knight7 = new ModelRenderer(this, 0, 32);
		knight7.addBox(0F, 4F, -8F, 1, 4, 1);
		knight7.setRotationPoint(0F, 0F, 0F);
		knight7.setTextureSize(64, 64);
		knight7.mirror = true;
		setRotation(knight7, 0F, 0F, 0F);
		knight8 = new ModelRenderer(this, 12, 32);
		knight8.addBox(-2F, 8F, -8F, 5, 1, 1);
		knight8.setRotationPoint(0F, 0F, 0F);
		knight8.setTextureSize(64, 64);
		knight8.mirror = true;
		setRotation(knight8, 0F, 0F, 0F);
		knight9 = new ModelRenderer(this, 4, 32);
		knight9.addBox(-1F, 9F, -8F, 3, 13, 1);
		knight9.setRotationPoint(0F, 0F, 0F);
		knight9.setTextureSize(64, 64);
		knight9.mirror = true;
		setRotation(knight9, 0F, 0F, 0F);
		knight10 = new ModelRenderer(this, 12, 34);
		knight10.addBox(-0.5F, 22F, -8F, 2, 1, 1);
		knight10.setRotationPoint(0F, 0F, 0F);
		knight10.setTextureSize(64, 64);
		knight10.mirror = true;
		setRotation(knight10, 0F, 0F, 0F);
		knight11 = new ModelRenderer(this, 12, 36);
		knight11.addBox(0F, 23F, -8F, 1, 1, 1);
		knight11.setRotationPoint(0F, 0F, 0F);
		knight11.setTextureSize(64, 64);
		knight11.mirror = true;
		setRotation(knight11, 0F, 0F, 0F);
		knight12 = new ModelRenderer(this, 0, 46);
		knight12.addBox(-4.5F, -6F, -5F, 9, 3, 1);
		knight12.setRotationPoint(0F, 0F, 0F);
		knight12.setTextureSize(64, 64);
		knight12.mirror = true;
		setRotation(knight12, -0.122173F, 0F, 0F);
		knight13 = new ModelRenderer(this, 0, 46);
		knight13.addBox(-4.5F, -4F, -4F, 9, 3, 1);
		knight13.setRotationPoint(0F, 0F, 0F);
		knight13.setTextureSize(64, 64);
		knight13.mirror = true;
		setRotation(knight13, 0.122173F, 0F, 0F);
		knight14 = new ModelRenderer(this, 0, 50);
		knight14.addBox(-4.6F, -5F, -3F, 1, 3, 4);
		knight14.setRotationPoint(0F, 0F, 0F);
		knight14.setTextureSize(64, 64);
		knight14.mirror = true;
		setRotation(knight14, 0.3490659F, 0F, 0F);
		knight15 = new ModelRenderer(this, 0, 50);
		knight15.addBox(-4.7F, -7F, -3F, 1, 3, 3);
		knight15.setRotationPoint(0F, 0F, 0F);
		knight15.setTextureSize(64, 64);
		knight15.mirror = true;
		setRotation(knight15, 0.122173F, 0F, 0F);
		knight17 = new ModelRenderer(this, 0, 50);
		knight17.addBox(3.7F, -7F, -3F, 1, 3, 3);
		knight17.setRotationPoint(0F, 0F, 0F);
		knight17.setTextureSize(64, 64);
		knight17.mirror = true;
		setRotation(knight17, 0.122173F, 0F, 0F);
		knight16 = new ModelRenderer(this, 0, 50);
		knight16.addBox(3.6F, -5F, -3F, 1, 3, 4);
		knight16.setRotationPoint(0F, 0F, 0F);
		knight16.setTextureSize(64, 64);
		knight16.mirror = true;
		setRotation(knight16, 0.3490659F, 0F, 0F);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void render(float f5){
		knight1.render(f5);
		knight2.render(f5);
		knight3.render(f5);
		knight4.render(f5);
		knight5.render(f5);
		knight6.render(f5);
		knight7.render(f5);
		knight8.render(f5);
		knight9.render(f5);
		knight10.render(f5);
		knight11.render(f5);
		knight12.render(f5);
		knight13.render(f5);
		knight14.render(f5);
		knight15.render(f5);
		knight17.render(f5);
	}
}
