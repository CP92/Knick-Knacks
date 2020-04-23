package knickknacks.objects.blocks.functionals.tileentities;

import knickknacks.init.BlockInit;
import knickknacks.init.ItemInit;
import knickknacks.objects.blocks.functionals.BlockSaltingBarrel;
import knickknacks.objects.blocks.functionals.recipes.SaltingBarrelRecipes;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntitySaltingBarrel extends TileEntity implements ITickable
{
	private ItemStackHandler handler = new ItemStackHandler(3);
	private String customName;
	private ItemStack smelting = ItemStack.EMPTY;
	
	private int saltTime;
	private int currentSaltTime;
	private int dryTime;
	private int totalDryTime = 200;

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		else return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.salting_barrel");
	}
	
	
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.saltTime = compound.getInteger("saltTime");
		this.dryTime = compound.getInteger("CookTime");
		this.totalDryTime = compound.getInteger("CookTimeTotal");
		this.currentSaltTime = getItemsaltTime((ItemStack)this.handler.getStackInSlot(1));
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("saltTime", (short)this.saltTime);
		compound.setInteger("CookTime", (short)this.dryTime);
		compound.setInteger("CookTimeTotal", (short)this.totalDryTime);
		compound.setTag("Inventory", this.handler.serializeNBT());
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	public boolean isSalting() 
	{
		return this.saltTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(TileEntitySaltingBarrel te) 
	{
		return te.getField(0) > 0;
	}
	
	public void update() 
	{	
		if(this.isSalting())
		{
			--this.saltTime;
			BlockSaltingBarrel.setState(true, world, pos);
		}
		
		ItemStack[] inputs = new ItemStack[] {handler.getStackInSlot(0)};
		ItemStack fuel = this.handler.getStackInSlot(1);
		
		if(this.isSalting() || !fuel.isEmpty() && !this.handler.getStackInSlot(0).isEmpty())
		{

			if(!this.isSalting() && this.canSmelt())
			{

				this.saltTime = getItemsaltTime(fuel);
				this.currentSaltTime = saltTime;
				
				if(this.isSalting() && !fuel.isEmpty())
				{
					Item item = fuel.getItem();
					fuel.shrink(1);
					
					if(fuel.isEmpty())
					{
						ItemStack item1 = item.getContainerItem(fuel);
						this.handler.setStackInSlot(2, item1);
					}
				}
			}
		}
		
		if(this.isSalting() && this.canSmelt() && dryTime > 0)
		{
			dryTime++;
			if(dryTime == totalDryTime)
			{
				if(handler.getStackInSlot(2).getCount() > 0)
				{
					handler.getStackInSlot(2).grow(1);
				}
				else
				{
					handler.insertItem(2, smelting, false);
				}
				
				smelting = ItemStack.EMPTY;
				dryTime = 0;
				return;
			}
		}
		else
		{
			if(this.canSmelt() && this.isSalting())
			{
				ItemStack output = SaltingBarrelRecipes.getInstance().getSaltingResult(inputs[0]);
				if(!output.isEmpty())
				{
					smelting = output;
					dryTime++;
					inputs[0].shrink(1);
					handler.setStackInSlot(0, inputs[0]);
				}
			}
		}
		
		if(this.isSalting()) {
			this.markDirty();
		}
	}
	
	private boolean canSmelt() 
	{
		if(((ItemStack)this.handler.getStackInSlot(0)).isEmpty()) return false;
		else 
		{
			ItemStack result = SaltingBarrelRecipes.getInstance().getSaltingResult((ItemStack)this.handler.getStackInSlot(0));	
			if(result.isEmpty()) return false;
			else
			{
				ItemStack output = (ItemStack)this.handler.getStackInSlot(2);
				if(output.isEmpty()) return true;
				if(!output.isItemEqual(result)) return false;
				int res = output.getCount() + result.getCount();
				return res <= 64 && res <= output.getMaxStackSize();
			}
		}
	}
	
	public static int getItemsaltTime(ItemStack fuel) 
	{
		if(fuel.isEmpty()) return 0;
		else 
		{
			Item item = fuel.getItem();
			
			if (item == ItemInit.SALT_PILE) return 1600;

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) 
			{
				Block block = Block.getBlockFromItem(item);

				if (block == BlockInit.SALT_BLOCK) return 16000;

			}

			return GameRegistry.getFuelValue(fuel);
		}
	}
		
	public static boolean isItemFuel(ItemStack fuel)
	{
		return getItemsaltTime(fuel) > 0;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	public int getField(int id) 
	{
		switch(id) 
		{
		case 0:
			return this.saltTime;
		case 1:
			return this.currentSaltTime;
		case 2:
			return this.dryTime;
		case 3:
			return this.totalDryTime;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) 
	{
		switch(id) 
		{
		case 0:
			this.saltTime = value;
			break;
		case 1:
			this.currentSaltTime = value;
			break;
		case 2:
			this.dryTime = value;
			break;
		case 3:
			this.totalDryTime = value;
		}
	}
}
