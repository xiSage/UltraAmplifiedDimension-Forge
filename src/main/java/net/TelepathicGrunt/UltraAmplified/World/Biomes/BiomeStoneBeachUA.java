package net.TelepathicGrunt.UltraAmplified.World.Biomes;

import java.util.Random;

import net.TelepathicGrunt.UltraAmplified.World.Biome.BiomeExtendedUA;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeStoneBeachUA extends BiomeExtendedUA
{
	
    public BiomeStoneBeachUA(Biome.BiomeProperties properties)
    {
        super(properties);
        
        this.topBlock = Blocks.STONE.getDefaultState();
        this.fillerBlock = Blocks.STONE.getDefaultState();
        
        this.decorator.treesPerChunk = -999;
        this.decorator.deadBushPerChunk = 0;
        this.decorator.reedsPerChunk = 0;
        this.decorator.cactiPerChunk = 0;
    }
    
    //heavily increased the spawnrate of mobs in this biome because this biome is high in resources.
    public float getSpawningChance()
    {
        return 0.15F;
    }
    
    //we use the decorator specifically for Stone Beach instead of BiomeDecoratorUA so we can generate additional ores and stone variants in this biome. 
    @Override
    public BiomeDecorator createBiomeDecorator()
    {
        return getModdedBiomeDecorator(new BiomeStoneBeachDecoratorUA());
    }
    
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
        this.generateBiomeTerrainUA(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }
}
