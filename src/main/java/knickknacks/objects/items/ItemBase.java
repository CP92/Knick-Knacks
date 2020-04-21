package knickknacks.objects.items;

import knickknacks.KnickKnacks;
import knickknacks.init.ItemInit;
import knickknacks.utils.Modellable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements Modellable{

	public ItemBase(String name, CreativeTabs tab) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		KnickKnacks.proxy.registerItemRender(this, 0, "inventory");		
	}

}

