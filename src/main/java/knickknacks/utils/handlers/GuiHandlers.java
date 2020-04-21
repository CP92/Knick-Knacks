package knickknacks.utils.handlers;

import knickknacks.objects.blocks.functionals.containers.ContainerSaltingBarrel;
import knickknacks.objects.blocks.functionals.guis.GuiSaltingBarrel;
import knickknacks.objects.blocks.functionals.tileentities.TileEntitySaltingBarrel;
import knickknacks.utils.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlers implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_SALTING_BARREL) return new ContainerSaltingBarrel(player.inventory, (TileEntitySaltingBarrel)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_SALTING_BARREL) return new GuiSaltingBarrel(player.inventory, (TileEntitySaltingBarrel)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

}
