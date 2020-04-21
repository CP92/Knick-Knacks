package knickknacks.objects.blocks.functionals.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class SaltingBarrelRecipes {	
	private static final SaltingBarrelRecipes INSTANCE = new SaltingBarrelRecipes();
	private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static SaltingBarrelRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private SaltingBarrelRecipes() 
	{
		addSaltingRecipe(new ItemStack(Items.ROTTEN_FLESH), new ItemStack(Items.LEATHER), 5.0F);
	}

	
	public void addSaltingRecipe(ItemStack input1, ItemStack result, float experience) 
	{
		if(getSaltingResult(input1) != ItemStack.EMPTY) return;
		this.smeltingList.put(input1, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getSaltingResult(ItemStack input1) 
	{
		for(Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet()) 
		{
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) 
			{
				return (ItemStack)entry.getValue();	
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Map<ItemStack, ItemStack> getSaltingList() 
	{
		return this.smeltingList;
	}
	
	public float getSaltingExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) 
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) 
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}