package knickknacks.objects.blocks.functionals.slots;

import knickknacks.objects.blocks.functionals.tileentities.TileEntitySaltingBarrel;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSaltingBarrelInput extends Slot {

	public SlotSaltingBarrelInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}

//	public SlotSaltingBarrelFuel(IInventory inventoryIn, int index, int xPosition, int yPosition) {
//		super(inventoryIn, index, xPosition, yPosition);
//		
//	}
//	
//	@Override
//	public boolean isItemValid(ItemStack stack){
//		return TileEntitySaltingBarrel.isItemFuel(stack);
//	}
//	
//	@Override
//	public int getItemStackLimit(ItemStack stack) {
//		return super.getItemStackLimit(stack);
//	}

}
