package com.limbo.blocks;

import java.util.Random;

import com.limbo.Limbo;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class SoulOre extends Block 
{
	public SoulOre()
	{
		super(Material.rock);
		setHardness(3.0F);
		setResistance(5.0F);
	}

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(Limbo.soulOre);
    }
}