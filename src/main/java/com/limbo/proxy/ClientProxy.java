package com.limbo.proxy;

import net.minecraft.client.model.ModelBiped;

import com.limbo.entity.EntityCursed;
import com.limbo.entity.RenderCursed;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCursed.class, new RenderCursed(new ModelBiped(), 0.5F));
		RenderingRegistry.addNewArmourRendererPrefix("whitesoulArmor");

	}
}
