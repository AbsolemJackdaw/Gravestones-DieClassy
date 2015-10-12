package net.subaraki.gravestone.client.model;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGraveStone extends ModelBase
{
	//fields

	public ModelRenderer Shape14;
	public ModelRenderer Shape15;
	public ModelRenderer Shape16;
	public ModelRenderer Shape17;
	public ModelRenderer Base1;
	public ModelRenderer Base2;
	public ModelRenderer Base3;


	public ModelGraveStone()
	{

		textureWidth = 64;
		textureHeight = 64;

		Base1 = new ModelRenderer(this, 0, 55);
		Base1.addBox(-8F, 0F, -4F, 16, 1, 8);
		Base1.setRotationPoint(0F, 23F, 0F);
		Base1.setTextureSize(64, 64);
		Base1.mirror = true;
		setRotation(Base1, 0F, 0F, 0F);
		Base2 = new ModelRenderer(this, 0, 47);
		Base2.addBox(-7.5F, 0F, -3.5F, 15, 1, 7);
		Base2.setRotationPoint(0F, 22F, 0F);
		Base2.setTextureSize(64, 64);
		Base2.mirror = true;
		setRotation(Base2, 0F, 0F, 0F);
		Base3 = new ModelRenderer(this, 0, 40);
		Base3.addBox(-7F, 0F, -3F, 14, 1, 6);
		Base3.setRotationPoint(0F, 21F, 0F);
		Base3.setTextureSize(64, 64);
		Base3.mirror = true;
		setRotation(Base3, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 0, 24);
		Shape14.addBox(-6F, 0F, -1F, 12, 14, 2);
		Shape14.setRotationPoint(0F, 7F, 0F);
		Shape14.setTextureSize(64, 64);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		Shape15 = new ModelRenderer(this, 0, 21);
		Shape15.addBox(-5.5F, 0F, 0F, 1, 1, 2);
		Shape15.setRotationPoint(0F, 6.5F, -1F);
		Shape15.setTextureSize(64, 64);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
		Shape16 = new ModelRenderer(this, 0, 18);
		Shape16.addBox(-5F, -2F, 0F, 10, 1, 2);
		Shape16.setRotationPoint(0F, 8F, -1F);
		Shape16.setTextureSize(64, 64);
		Shape16.mirror = true;
		setRotation(Shape16, 0F, 0F, 0F);
		Shape17 = new ModelRenderer(this, 0, 21);
		Shape17.addBox(4.5F, 0F, 0F, 1, 1, 2);
		Shape17.setRotationPoint(0F, 6.5F, -1F);
		Shape17.setTextureSize(64, 64);
		Shape17.mirror = true;
		setRotation(Shape17, 0F, 0F, 0F);


	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void showZerk(boolean b)
	{
		Shape14.showModel =Shape15.showModel =Shape16.showModel =Shape17.showModel =
				Base1.showModel =Base2.showModel =Base3.showModel =b;
	}

	public void render(float f5)
	{
		Shape14.render(f5);
		Shape15.render(f5);
		Shape16.render(f5);
		Shape17.render(f5);
		Base1.render(f5);
		Base2.render(f5);
		Base3.render(f5);

	}
}