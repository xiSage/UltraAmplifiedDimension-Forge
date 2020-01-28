package net.telepathicgrunt.ultraamplified.world.generation.layers;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset1Transformer;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.telepathicgrunt.ultraamplified.config.ConfigUA;
import net.telepathicgrunt.ultraamplified.world.biome.BiomeInit;
import net.telepathicgrunt.ultraamplified.world.generation.BiomeGenHelper;


public enum HillsAndAmplifiedLayerUA implements IAreaTransformer2, IDimOffset1Transformer
{
	INSTANCE;
	/*
	 * This class generates M variants and Hills variants of biomes if they exist for that biome
	 */

	private static ForgeRegistry<Biome> BiomeRegistry = ((ForgeRegistry<Biome>) ForgeRegistries.BIOMES);


	public int apply(INoiseRandom context, IArea area1, IArea area2, int x, int z)
	{
		int biomeId1 = area1.getValue(x + 1, z + 1);
		int biomeId2 = area2.getValue(x + 1, z + 1);

		//creates the noise (gennerally in the range of -0.5 to 0.5 but not always)
		double noise = context.getNoiseGenerator().func_215456_a((double) (x) / 8.0D, (double) (z) / 8.0D, 0.0D, 0.0D, 0.0D);
		boolean allowMForm = ConfigUA.mutatedBiomeSpawnrate != 0 && 
							(noise < (ConfigUA.mutatedBiomeSpawnrate / 10D) - 0.5D || ConfigUA.mutatedBiomeSpawnrate == 10);
		boolean allowHills = (biomeId2 - 2) % 29 == 0 || context.random(3) == 0;

		//debugging
		//System.out.println("Noise: "+noise+"  |  M form? " + allowMForm);

		//first way to create m variant biomes of base biomes
		if (!BiomeGenHelper.isShallowOcean(biomeId1) && allowMForm && !allowHills)
		{
			Biome biome = BiomeRegistry.getValue(biomeId1);
			if (biome == null || !biome.isMutation())
			{
				Biome biome2 = BiomeInit.BASE_TO_MUTATION_MAP.get(biome);
				return biome2 == null ? biomeId1 : BiomeRegistry.getID(biome2);
			}
		}

		//first way of making m biomes failed. Now we try to make hills/hills m biomes

		//creates hills variants of biomes
		if (allowHills)
		{
			int biomeIdToReturn = biomeId1;

			if (BiomeInit.BASE_TO_HILLS_MAP.containsKey(biomeId1))
			{
				biomeIdToReturn = BiomeInit.BASE_TO_HILLS_MAP.get(biomeId1);
			}
			else if (biomeId1 == BiomeGenHelper.PLAINS && ConfigUA.forest)
			{
				//makes sure forest is selected in the config setting before allowing it to spawn through here
				biomeIdToReturn = context.random(3) == 0 ? BiomeGenHelper.RELIC_FOREST : BiomeGenHelper.FOREST;
			}
			else if (biomeId1 == BiomeGenHelper.SNOWY_TUNDRA && ConfigUA.icedTerrain)
			{
				biomeIdToReturn = BiomeGenHelper.ICED_TERRAIN;
			}
			else if (BiomeGenHelper.areUABiomesSimilar(biomeId1, BiomeGenHelper.WOODED_BADLANDS))
			{
				biomeIdToReturn = BiomeGenHelper.BADLANDS;
			}

			//second way to create m variant biomes of both base and hills biomes
			if (allowMForm)
			{
				Biome biomeTemp = BiomeInit.BASE_TO_MUTATION_MAP.get(BiomeRegistry.getValue(biomeIdToReturn));

				if(biomeTemp != null) //hill form does have mutated form
				{
					biomeIdToReturn = BiomeRegistry.getID(biomeTemp);
				}
				else
				{
					if(ConfigUA.mutatedBiomeSpawnrate == 10) //if we must generate m form, do so of the base biome instead of hills. Otherwise, return base form itself.
					{
						biomeTemp = BiomeInit.BASE_TO_MUTATION_MAP.get(BiomeRegistry.getValue(biomeId1));
						
						if(biomeTemp == null) //base form does not have mutated form
						{
							biomeIdToReturn = biomeId1;
						}
						else //mutated form of base form
						{
							biomeIdToReturn = BiomeRegistry.getID(biomeTemp);
						}
					}
					else //return base form instead of mutated form
					{
						biomeIdToReturn = biomeId1;
					}
				}
			}

			//returns hills and m variant biome if it is surrounded by similar biomes on atleast 3 sides
			if (biomeIdToReturn != biomeId1)
			{
				int i1 = 0;
				if (BiomeGenHelper.areUABiomesSimilar(area1.getValue(x + 1, z + 0), biomeId1))
				{
					++i1;
				}

				if (BiomeGenHelper.areUABiomesSimilar(area1.getValue(x + 2, z + 1), biomeId1))
				{
					++i1;
				}

				if (BiomeGenHelper.areUABiomesSimilar(area1.getValue(x + 0, z + 1), biomeId1))
				{
					++i1;
				}

				if (BiomeGenHelper.areUABiomesSimilar(area1.getValue(x + 1, z + 2), biomeId1))
				{
					++i1;
				}

				if (i1 >= 3)
				{
					return biomeIdToReturn;
				}
			}
		}
		
		if(ConfigUA.mutatedBiomeSpawnrate == 10) {
			Biome biome = BiomeRegistry.getValue(biomeId1);
			if (biome == null || !biome.isMutation())
			{
				Biome biome2 = BiomeInit.BASE_TO_MUTATION_MAP.get(biome);
				return biome2 == null ? biomeId1 : BiomeRegistry.getID(biome2);
			}
		}
		
		return biomeId1;
	}

}