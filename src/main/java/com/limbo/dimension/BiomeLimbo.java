package com.limbo.dimension;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.limbo.Limbo;

public class BiomeLimbo extends BiomeGenBase
{
	  public BiomeLimbo()
	  {
	    super(40);
	    setBiomeName("Limbo");
	    this.topBlock = Limbo.limboGrass;
	    this.fillerBlock = Limbo.limboDirt;
	    this.enableRain = false;
	    this.enableSnow = false;
	  }
}
