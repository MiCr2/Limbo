package com.limbo.entity;

import com.limbo.Limbo;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCursed extends RenderBiped 
{

	private static final ResourceLocation textureLocation = new ResourceLocation(Limbo.MODID + ":" + "textures/models/TheCursed.png");

	public RenderCursed(ModelBiped model, float shadowSize) 
	{
		super(model, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return textureLocation;
	}
}
