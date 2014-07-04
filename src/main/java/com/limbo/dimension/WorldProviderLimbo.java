package com.limbo.dimension;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

import com.limbo.Limbo;
 
public class WorldProviderLimbo extends WorldProvider
{
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerHell(Limbo.limboBiome, 0.1F);
        this.dimensionId = Limbo.dimensionId;
    }
    
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderLimbo(worldObj, worldObj.getSeed(), true);
    }
    
    @Override
    public String getDimensionName()
    {
        return "Limbo Realm";
    }
}