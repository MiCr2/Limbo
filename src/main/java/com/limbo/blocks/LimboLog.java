package com.limbo.blocks;

import java.util.Random;

import com.limbo.Limbo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class LimboLog extends Block 
{
	protected IIcon blockIcon;
	protected IIcon blockIconTop;
	protected IIcon blockIconBottom;
  
	public LimboLog()
	{
		super(Material.wood);
		this.setTickRandomly(true);
		setHardness(2.0F);
		setResistance(5.0F);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
	blockIcon = p_149651_1_.registerIcon(Limbo.MODID + ":" + this.getUnlocalizedName().substring(5) + "LimboLog");
	blockIconTop = p_149651_1_.registerIcon(Limbo.MODID + ":" + this.getUnlocalizedName().substring(5) + "LimboLog2");
	blockIconBottom = p_149651_1_.registerIcon(Limbo.MODID + ":" + this.getUnlocalizedName().substring(5) + "LimboLog2");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata)
	{
	if (side == 1) {
		return blockIconTop;
	} else if (side == 0){
		return blockIconBottom;
	} else {
		return blockIcon;
	}
	}

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(Limbo.limboLog);
    }
}