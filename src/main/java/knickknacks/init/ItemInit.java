package knickknacks.init;

import java.util.ArrayList;
import java.util.List;

import knickknacks.objects.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemInit {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item PESTLE_AND_MORTAR = new ItemBase("pestle_and_mortar", CreativeTabs.MISC);
	
	public static final Item SALT_PILE = new ItemBase("salt_pile", CreativeTabs.MISC);
}
