package knickknacks.worldgen;

import java.util.Random;

import knickknacks.init.BlockInit;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeBeach;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenKnickKnacks implements IWorldGenerator {
	
	private WorldGenerator saltySand;

	public WorldGenKnickKnacks() {
		saltySand = new WorldGenMinable(BlockInit.SALTY_SAND.getDefaultState(), 9, BlockMatcher.forBlock(Blocks.SAND));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
		case 0:
			int chance = 25;
			Biome biome = world.provider.getBiomeProvider().getBiome(new BlockPos(chunkX, chunkZ, 0));
			if(biome instanceof BiomeOcean || biome instanceof BiomeBeach) {
				chance = 75;
			}
			runGenerator(saltySand, world, random, chunkX, chunkZ, chance, 0, 100, BlockInit.SALTY_SAND.getLocalizedName());
			break;
		}
		
	}
	
	public void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight, String blockName) {
		if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException(blockName + " generated out of bounds");
		
		int heightDiff = maxHeight - minHeight + 1;
		for(int i = 0; i < chance; i++)
		{
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			
			gen.generate(world, rand, new BlockPos(x,y,z));
		}
	}
	
	
}
