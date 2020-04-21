package knickknacks.utils.handlers;

import knickknacks.objects.blocks.functionals.tileentities.TileEntitySaltingBarrel;
import knickknacks.utils.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities()
	{
		
		GameRegistry.registerTileEntity(TileEntitySaltingBarrel.class, new ResourceLocation(Reference.MODID + ":salting_barrel"));

	}
}
