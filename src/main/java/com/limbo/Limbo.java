package com.limbo;
 
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import scala.util.Random;

import com.limbo.blocks.LimboCobble;
import com.limbo.blocks.LimboDirt;
import com.limbo.blocks.LimboDust;
import com.limbo.blocks.LimboGrass;
import com.limbo.blocks.LimboLeaf;
import com.limbo.blocks.LimboLog;
import com.limbo.blocks.LimboPlanks;
import com.limbo.blocks.LimboStone;
import com.limbo.blocks.MysticBlock;
import com.limbo.blocks.MysticOre;
import com.limbo.blocks.SoulOre;
import com.limbo.dimension.BiomeLimbo;
import com.limbo.dimension.BlockPortalLimbo;
import com.limbo.dimension.WorldProviderLimbo;
import com.limbo.entity.EntityCursed;
import com.limbo.items.BlackSoul;
import com.limbo.items.CursedApple;
import com.limbo.items.Forgotten;
import com.limbo.items.ForgottenMetal;
import com.limbo.items.LimboAxe;
import com.limbo.items.LimboClaw;
import com.limbo.items.LimboHoe;
import com.limbo.items.LimboPick;
import com.limbo.items.LimboSpade;
import com.limbo.items.LimboSword;
import com.limbo.items.LimboTorch;
import com.limbo.items.Mystical;
import com.limbo.items.Scythe;
import com.limbo.items.WhilteSoul1;
import com.limbo.items.WhiteSoul;
import com.limbo.items.WhiteSoulArmor;
import com.limbo.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
 
@Mod(modid = Limbo.MODID, version = "Alpha 1.0")
public class Limbo extends Recipes
{
    public static final String MODID = "limbo";
    public static final String VERSION = "2.0";
    
    @SidedProxy(clientSide="com.limbo.proxy.ClientProxy", serverSide="com.limbo.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    @Instance(MODID)
    public static Limbo instance;
    
    public Fluid limboWaterFluid = new Fluid("limboWater");
    
    //Dimension
    public static int dimensionId = 8;
    public static final BiomeGenBase limboBiome = new BiomeLimbo();
    
    //Blocks
    public static Block limboStone;
    public static Block limboCobble;
    public static Block limboLog;
    public static Block limboGrass;
    public static Block limboDirt;
    public static Block limboPlanks;
    public static Block limboTorch;
    public static Block limboLeaf;
    public static Block mysticBlock;
    public static Block soulOre;
    public static Block limboWater;
    public static Block portalLimboBlock;
    public static Block mysticOre;
    
    //Items
    public static Item mystical;
    public static Item cursedApple;
    public static Item forgotten;
    public static Item forgottenMetal;
    public static Item limboDust;
    public static Item whitesoul;
    public static Item whiltesoul1;
    public static Item blacksoul;
    
    //Tools and Weapons
    public static Item limboClaw;
    public static Item scythe;
    public static Item limboWoodPick;
    public static Item limboWoodAxe;
    public static Item limboWoodSpade;
    public static Item limboWoodHoe;
    public static Item limboWoodSword;
    public static Item limboForgottenPick;
    public static Item limboForgottenAxe;
    public static Item limboForgottenSpade;
    public static Item limboForgottenHoe;
    public static Item limboForgottenSword;
    
    //Armor
    public static Item whitesoulHelmet;
    public static Item whitesoulChest;
    public static Item whitesoulLegs;
    public static Item whitesoulBoots;
    
    //Materials
    static ToolMaterial forgottenMaterial =  EnumHelper.addToolMaterial("forgottenMaterial", 2, 250, 6.0F, 2.0F, 14);
    static ToolMaterial limboWoodMaterial = EnumHelper.addToolMaterial("limboWoodMaterial", 0, 59, 2.0F, 0.0F, 15);
    static ArmorMaterial whitesoulMaterial = EnumHelper.addArmorMaterial("whitesoulMaterial", 15, new int[]{2, 6, 5, 2}, 15);
    
    //TabItemIcon
    public static Item cursedAppleTab;
    public static Item mysticBlockTab;
    
    //Tabs    
	public static CreativeTabs limboTab = new CreativeTabs("limboTab")
	{
		@Override
		public Item getTabIconItem() 
		{
			return Limbo.mysticBlockTab;
		}	
	};
	
	public static CreativeTabs limboTabItems = new CreativeTabs("limboTabItems")
	{
		@Override
		public Item getTabIconItem() 
		{
			return Limbo.cursedAppleTab;
		}	
	};
	
	public static CreativeTabs limboTabTW = new CreativeTabs("limboTabTW")
	{
		@Override
		public Item getTabIconItem()
		{
			return Limbo.limboForgottenPick;
		}
	};
	
	//Mob
	public static void registerEntity(Class entityClass, String name)
	{
	int entityID = EntityRegistry.findGlobalUniqueEntityId();
	long seed = name.hashCode();
	Random rand = new Random(seed);
	int primaryColor = rand.nextInt() * 16777215;
	int secondaryColor = rand.nextInt() * 16777215;

	EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
	EntityRegistry.registerModEntity(entityClass, name, entityID, instance, 64, 1, true);
	EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, primaryColor, secondaryColor));
	}

	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {    	
    	//FluidRegistry.registerFluid(limboWaterFluid);
    	//limboWaterFluid = new LimboWater(limboWater).setCreativeTab(limboTab).setBlockName("limbowater");
    	//GameRegistry.registerBlock(limboWaterFluid, "limboWaterFluid");
    	//limboWaterFluid.setUnlocalizedName(limboWaterFluid.getUnlocalizedName());
    	
    	BiomeDictionary.registerBiomeType(limboBiome, Type.FOREST);
    	BiomeManager.addSpawnBiome(limboBiome);
    	DimensionManager.registerProviderType(Limbo.dimensionId, WorldProviderLimbo.class, false);
    	DimensionManager.registerDimension(Limbo.dimensionId, Limbo.dimensionId);
    	portalLimboBlock = new BlockPortalLimbo().setCreativeTab(limboTab);
    	GameRegistry.registerBlock(portalLimboBlock, "portalLimboBlock");
    	registerEntity(EntityCursed.class, "entityCursed");
    	limboStone = new LimboStone().setBlockName("limboStone").setCreativeTab(limboTab).setBlockTextureName(MODID + ":" + "LimboStone");
    	GameRegistry.registerBlock(limboStone, "limboStone");
    	limboCobble = new LimboCobble().setBlockName("limboCobble").setCreativeTab(limboTab).setBlockTextureName(MODID + ":" + "LimboCobble");
    	GameRegistry.registerBlock(limboCobble, "limboCobble");
    	limboLog = new LimboLog().setBlockName("limboLog").setCreativeTab(limboTab).setBlockTextureName(MODID + ":" + "LimboLog");
    	GameRegistry.registerBlock(limboLog, "limboLog");
    	limboGrass = new LimboGrass().setBlockName("limboGrass").setCreativeTab(limboTab).setBlockTextureName(MODID + ":" + "LimboGrass");
    	GameRegistry.registerBlock(limboGrass, "limboGrass");
    	limboPlanks = new LimboPlanks().setBlockName("limboPlanks").setCreativeTab(limboTab).setBlockTextureName(MODID + ":" + "LimboPlanks");
    	GameRegistry.registerBlock(limboPlanks, "limboPlanks");
    	limboTorch = new LimboTorch().setBlockName("limboTorch").setCreativeTab(limboTab).setTickRandomly(true).setBlockTextureName(MODID + ":" + "LimboTorch");
    	GameRegistry.registerBlock(limboTorch, "limboTorch");
    	limboLeaf = new LimboLeaf().setBlockName("limboLeaf").setCreativeTab(limboTab).setTickRandomly(true).setBlockTextureName(MODID + ":" + "LimboLeaf");
    	GameRegistry.registerBlock(limboLeaf, "limboLeaf");
    	mysticBlock = new MysticBlock().setBlockName("mysticBlock").setCreativeTab(limboTab).setBlockTextureName(MODID + ":" + "MysticBlock");
    	GameRegistry.registerBlock(mysticBlock, "mysticBlock");
    	soulOre = new SoulOre().setBlockName("soulOre").setCreativeTab(limboTab).setBlockTextureName(MODID + ":" + "SoulOre");
    	GameRegistry.registerBlock(soulOre, "soulOre");
    	limboDirt = new LimboDirt().setBlockName("limboDirt").setCreativeTab(limboTab).setBlockTextureName(MODID + ":" + "LimboDirt");
    	GameRegistry.registerBlock(limboDirt, "limboDirt");
    	mysticOre = new MysticOre().setBlockName("mysticOre").setCreativeTab(limboTab).setBlockTextureName(MODID + ":" + "MysticalStone");
    	GameRegistry.registerBlock(mysticOre, "mysticOre");
    	
    	//Items pt.2
    	mystical = new Mystical().setUnlocalizedName("mystical").setCreativeTab(limboTabItems).setTextureName(MODID + ":" + "Mystical");
    	GameRegistry.registerItem(mystical, "mystical");    	
    	cursedApple = new CursedApple().setUnlocalizedName("cursedApple").setCreativeTab(limboTabItems).setTextureName(MODID + ":" + "CursedApple");
    	GameRegistry.registerItem(cursedApple, "cursedApple");    	
    	forgotten = new Forgotten().setUnlocalizedName("forgotten").setCreativeTab(limboTabItems).setTextureName(MODID + ":" + "Forgotten");
    	GameRegistry.registerItem(forgotten, "forgotten");    	
    	forgottenMetal = new ForgottenMetal().setUnlocalizedName("forgottenMetal").setCreativeTab(limboTabItems).setTextureName(MODID + ":" + "ForgottenMetal");
    	GameRegistry.registerItem(forgottenMetal, "forgottenMetal");    	
    	limboDust = new LimboDust().setUnlocalizedName("limboDust").setCreativeTab(limboTabItems).setTextureName(MODID + ":" + "LimboDust");
    	GameRegistry.registerItem(limboDust, "limboDust");    	   	
    	whitesoul = new WhiteSoul().setUnlocalizedName("whitesoul").setCreativeTab(limboTabItems).setTextureName(MODID + ":" + "WhiteSoul");
    	GameRegistry.registerItem(whitesoul, "whitesoul");    	
    	whiltesoul1 = new WhilteSoul1().setUnlocalizedName("whiltesoul1").setCreativeTab(limboTabItems).setTextureName(MODID + ":" + "WhiteSoul1");
    	GameRegistry.registerItem(whiltesoul1, "whiltesoul1");    	
    	blacksoul = new BlackSoul().setUnlocalizedName("blacksoul").setCreativeTab(limboTabItems).setTextureName(MODID + ":" + "BlackSoul");
    	GameRegistry.registerItem(blacksoul, "blacksoul");
    	
    	//Tools and Weapons
    	limboForgottenPick = new LimboPick(forgottenMaterial).setUnlocalizedName("limboForgottenPick").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "LimboForgottenPick");
    	GameRegistry.registerItem(limboForgottenPick, "limboForgottenPick");
    	limboForgottenAxe = new LimboAxe(forgottenMaterial).setUnlocalizedName("limboForgottenAxe").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "LimboForgottenAxe");
    	GameRegistry.registerItem(limboForgottenAxe, "limboForgottenAxe");
    	limboForgottenSpade = new LimboSpade(forgottenMaterial).setUnlocalizedName("limboForgottenSpade").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "LimboForgottenSpade");
    	GameRegistry.registerItem(limboForgottenSpade, "limboForgottenSpade");
    	limboForgottenHoe = new LimboHoe(forgottenMaterial).setUnlocalizedName("limboForgottenHoe").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "LimboForgottenHoe");
    	GameRegistry.registerItem(limboForgottenHoe, "limboForgottenHoe");
    	limboForgottenSword = new LimboSword(forgottenMaterial).setUnlocalizedName("limboForgottenSword").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "LimboForgottenSword");
    	GameRegistry.registerItem(limboForgottenSword, "limboForgottenSword");    	
    	limboWoodPick = new LimboPick(limboWoodMaterial).setUnlocalizedName("limboWoodPick").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "LimboWoodPick");
    	GameRegistry.registerItem(limboWoodPick, "limboWoodPick");
    	limboWoodAxe = new LimboAxe(limboWoodMaterial).setUnlocalizedName("limboWoodAxe").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "LimboWoodAxe");
    	GameRegistry.registerItem(limboWoodAxe, "limboWoodAxe");
    	limboWoodSpade = new LimboSpade(limboWoodMaterial).setUnlocalizedName("limboWoodSpade").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "LimboWoodSpade");
    	GameRegistry.registerItem(limboWoodSpade, "limboWoodSpade");
    	limboWoodHoe = new LimboHoe(limboWoodMaterial).setUnlocalizedName("limboWoodHoe").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "LimboWoodHoe");
    	GameRegistry.registerItem(limboWoodHoe, "limboWoodHoe");
    	limboWoodSword = new LimboSword(limboWoodMaterial).setUnlocalizedName("limboWoodSword").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "LimboWoodSword");
    	GameRegistry.registerItem(limboWoodSword, "limboWoodSword");
    	scythe = new Scythe(forgottenMaterial).setUnlocalizedName("scythe").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "Scythe");
    	GameRegistry.registerItem(scythe, "scythe"); 
    	limboClaw = new LimboClaw(forgottenMaterial).setUnlocalizedName("limboClaw").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "LimboClaw");
    	GameRegistry.registerItem(limboClaw, "limboClaw");
    	
    	//Armor
    	whitesoulHelmet = new WhiteSoulArmor(whitesoulMaterial, 0).setUnlocalizedName("whitesoulHelmet").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "whitesoulHelmet");
    	GameRegistry.registerItem(whitesoulHelmet, "whitesoulHelmet");
    	whitesoulChest = new WhiteSoulArmor(whitesoulMaterial, 1).setUnlocalizedName("whitesoulChest").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "whitesoulChest");
    	GameRegistry.registerItem(whitesoulChest, "whitesoulChest");
    	whitesoulLegs = new WhiteSoulArmor(whitesoulMaterial, 2).setUnlocalizedName("whitesoulLegs").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "whitesoulLegs");
    	GameRegistry.registerItem(whitesoulLegs, "whitesoulLegs");
    	whitesoulBoots = new WhiteSoulArmor(whitesoulMaterial, 3).setUnlocalizedName("whitesoulBoots").setCreativeTab(limboTabTW).setTextureName(MODID + ":" + "whitesoulBoots");
    	GameRegistry.registerItem(whitesoulBoots, "whitesoulBoots");
    	
    	cursedAppleTab = new TabItems().setUnlocalizedName("cursedAppleTab").setTextureName(MODID + ":" + "CursedApple");
    	GameRegistry.registerItem(cursedAppleTab, "cursedAppleTab");
    	mysticBlockTab = new TabItems().setUnlocalizedName("mysticBlockTab").setTextureName(MODID + ":" + "MysticBlock");
    	GameRegistry.registerItem(mysticBlockTab, "mysticBlockTab");    
    	
    	//Crafting
    	GameRegistry.addRecipe(new ItemStack(Items.stick, 4), new Object[]{
            "   ",
            "A  ",
            "A  ",
            'A', Limbo.limboPlanks
    });
    	GameRegistry.addRecipe(new ItemStack(Limbo.scythe), new Object[]{
    		"AAB",
    		" B ",
    		"B  ",
    		'A', Limbo.forgottenMetal, 'B', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(Limbo.limboClaw), new Object[]{
    		"AAA",
    		"BBB",
    		" B ",
    		'A', Limbo.forgottenMetal, 'B', Items.leather
    	});
    	GameRegistry.addRecipe(new ItemStack(Limbo.limboForgottenPick), new Object[]{
    		"AAA",
    		" B ",
    		" B ",
    		'A', Limbo.forgottenMetal, 'B', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(Limbo.limboForgottenAxe), new Object[]{
    		"AA ",
    		"AB ",
    		" B ",
    		'A', Limbo.forgottenMetal, 'B', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(Limbo.limboForgottenSpade), new Object[]{
    		" A ",
    		" B ",
    		" B ",
    		'A', Limbo.forgottenMetal, 'B', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(Limbo.limboForgottenHoe), new Object[]{
    		"AA ",
    		" B ",
    		" B ",
    		'A', Limbo.forgottenMetal, 'B', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(Limbo.limboForgottenSword), new Object[]{
    		" A ",
    		" A ",
    		" B ",
    		'A', Limbo.forgottenMetal, 'B', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(Limbo.limboWoodPick), new Object[]{
    		"AAA",
    		" B ",
    		" B ",
    		'A', Limbo.forgottenMetal, 'B', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(Limbo.limboWoodAxe), new Object[]{
    		"AA ",
    		"AB ",
    		" B ",
    		'A', Limbo.forgottenMetal, 'B', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(Limbo.limboWoodSpade), new Object[]{
    		" A ",
    		" B ",
    		" B ",
    		'A', Limbo.forgottenMetal, 'B', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(Limbo.limboWoodHoe), new Object[]{
    		"AA ",
    		" B ",
    		" B ",
    		'A', Limbo.forgottenMetal, 'B', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(Limbo.limboWoodSword), new Object[]{
    		" A ",
    		" A ",
    		" B ",
    		'A', Limbo.forgottenMetal, 'B', Items.stick
    	});
    	GameRegistry.addShapelessRecipe(new ItemStack(Limbo.limboPlanks, 4), new Object[]{
            Limbo.limboLog
    });
 
    	
        //Smelting
        GameRegistry.addSmelting(new ItemStack(Limbo.limboCobble), new ItemStack(Limbo.limboStone, 1), 0.1F);
        GameRegistry.addSmelting(new ItemStack(Limbo.soulOre), new ItemStack(Limbo.whitesoul), 0.3F);
        GameRegistry.addSmelting(new ItemStack(Limbo.mysticBlock), new ItemStack(Limbo.mystical), 0.4F);
    	
    	proxy.registerRenderers();
    }
}