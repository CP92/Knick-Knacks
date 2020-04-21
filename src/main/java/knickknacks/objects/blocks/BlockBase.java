package knickknacks.objects.blocks;

import knickknacks.KnickKnacks;
import knickknacks.init.BlockInit;
import knickknacks.init.ItemInit;
import knickknacks.utils.Modellable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements Modellable{
	public BlockBase(String name, CreativeTabs creativeTab, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(creativeTab);
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		KnickKnacks.proxy.registerItemRender(Item.getItemFromBlock(this), 0, "inventory");;
	}
}
