package net.subaraki.gravestone.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHead extends ModelBiped {

	public ModelHead()
	{
		textureWidth = 64;
		textureHeight = 32;

		this.bipedBody = new ModelRenderer(this, 16, 16);
		this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 6, 4);
		this.bipedBody.setRotationPoint(0.0F, 0.0F , 0.0F);
		this.bipedRightArm = new ModelRenderer(this, 40, 16);
		this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 6, 4);
		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F , 0.0F);
		this.bipedLeftArm = new ModelRenderer(this, 40, 16);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 6, 4);
		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F , 0.0F);

		this.bipedLeftLeg.showModel = false;
		this.bipedRightLeg.showModel = false;

	}

	public void renderHead(float f5)
	{
		bipedHead.render(f5);
		bipedHeadwear.render(f5);
		bipedBody.render(f5);
		bipedLeftArm.render(f5);
		bipedRightArm.render(f5);
	}

	public void showHead(boolean b)
	{
		bipedHead.showModel=bipedHeadwear.showModel=bipedBody.showModel=bipedLeftArm.showModel=bipedRightArm.showModel=b;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		renderHead(f5);
	}
}