package knickknacks.objects.blocks;

import java.util.Random;

import knickknacks.init.ItemInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BlockSaltySand extends FallingBlockBase{

	public BlockSaltySand(String name, CreativeTabs creativeTab) {
		super(name, creativeTab);
		this.setHardness((float) 0.5);
		this.setHarvestLevel("shovel", 0);
	}

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ItemInit.SALT_PILE;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 4;
    }
}
