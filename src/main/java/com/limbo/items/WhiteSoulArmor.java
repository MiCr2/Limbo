package com.limbo.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import com.limbo.Limbo;

public class WhiteSoulArmor extends ItemArmor
{
	public WhiteSoulArmor (ArmorMaterial material, int armorType)
	{
		super(material, 0, armorType);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if(stack.getItem() == Limbo.whitesoulHelmet)
		{
			return Limbo.MODID + "/textures/models/armor/white_1.png";
		}else if(stack.getItem() == Limbo.whitesoulChest) {
			return Limbo.MODID + "/textures/models/armor/white_1.png";
		}else if(stack.getItem() == Limbo.whitesoulLegs) {
			return Limbo.MODID + "/textures/models/armor/white_1.png";
		}else if(stack.getItem() == Limbo.whitesoulBoots) {
			return Limbo.MODID + "/textures/models/armor/white_1.png";
		}
		return null;
	}
}
