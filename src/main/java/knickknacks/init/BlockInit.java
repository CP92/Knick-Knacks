package knickknacks.init;

import java.util.ArrayList;
import java.util.List;

import knickknacks.objects.blocks.BlockBase;
import knickknacks.objects.blocks.BlockSaltySand;
import knickknacks.objects.blocks.functionals.BlockSaltingBarrel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockInit {
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block SALTING_BARREL = new BlockSaltingBarrel("salting_barrel", CreativeTabs.MISC, Material.WOOD);
	
	public static final Block SALTY_SAND = new BlockSaltySand("salty_sand", CreativeTabs.MATERIALS);
	
	public static final Block SALT_BLOCK = new BlockBase("salt_block", CreativeTabs.MATERIALS, Material.SAND);
}



