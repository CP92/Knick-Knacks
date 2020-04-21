package knickknacks.utils.handlers;

import knickknacks.KnickKnacks;
import knickknacks.init.BlockInit;
import knickknacks.init.ItemInit;
import knickknacks.utils.Modellable;
import knickknacks.worldgen.WorldGenKnickKnacks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandlers {
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	};
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for (Item item : ItemInit.ITEMS) {
			if (item instanceof Modellable ) {
				((Modellable) item).registerModels(); 
			}
		}
		
		for (Block block : BlockInit.BLOCKS) {
			if (block instanceof Modellable) {
				((Modellable) block).registerModels();
			}
		}
	}
	
	public static void preInitRegistries(FMLPreInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(new WorldGenKnickKnacks(), 0);
		

	}
	
	public static void initRegistries(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(KnickKnacks.instance, new GuiHandlers());
	
	}
	
	public static void postInitRegistries(FMLPostInitializationEvent event)
	{

	}
}
