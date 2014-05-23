package net.subaraki.gravestone.block.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTomb extends ModelBase {

	public ModelRenderer Shape5;
	public ModelRenderer Shape6;
	public ModelRenderer Shape7;
	public ModelRenderer Shape8;
	public ModelRenderer Shape9;
	public ModelRenderer Shape10;
	public ModelRenderer Shape11;
	public ModelRenderer Shape12;
	public ModelRenderer Shape13;

	public ModelTomb() {

		textureHeight = 64;
		textureWidth = 64;

		Shape10 = new ModelRenderer(this, 24, 0);
		Shape10.addBox(-1F, -5F, -1F, 2, 3, 2);
		Shape10.setRotationPoint(7F, 26F, 7F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 0, 0);
		Shape11.addBox(-1F, -2F, -1F, 2, 3, 2);
		Shape11.setRotationPoint(-7F, 23F, 7F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 16, 0);
		Shape12.addBox(-1F, -2F, -1F, 2, 3, 2);
		Shape12.setRotationPoint(7F, 23F, -7F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 8, 0);
		Shape13.addBox(-1F, -2F, -1F, 2, 3, 2);
		Shape13.setRotationPoint(-7F, 23F, -7F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 17);
		Shape5.addBox(-7F, 0F, -7F, 14, 1, 14);
		Shape5.setRotationPoint(0F, 23F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 32, 0);
		Shape6.addBox(-4F, -2F, -4F, 8, 4, 8);
		Shape6.setRotationPoint(0F, 20F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 7, 21);
		Shape7.addBox(-5F, 0F, -5F, 10, 1, 10);
		Shape7.setRotationPoint(0F, 17F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 0, 5);
		Shape8.addBox(-4F, -5F, -2F, 8, 8, 4);
		Shape8.setRotationPoint(0F, 14F, 0F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 4, 19);
		Shape9.addBox(-6F, 0F, -6F, 12, 1, 12);
		Shape9.setRotationPoint(0F, 22F, 0F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void render(float f5){
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
	}
}
