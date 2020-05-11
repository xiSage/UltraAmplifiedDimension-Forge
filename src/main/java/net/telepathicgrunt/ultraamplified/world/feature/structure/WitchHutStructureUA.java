package net.telepathicgrunt.ultraamplified.world.feature.structure;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;

import net.minecraft.entity.EntityType;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.telepathicgrunt.ultraamplified.UltraAmplified;


public class WitchHutStructureUA extends Structure<NoFeatureConfig>
{
    /**
     * --------------------------------------------------------------------------
     * |									|
     * |	HELLO READERS! IF YOU'RE HERE, YOU'RE PROBABLY			|
     * |	LOOKING FOR A TUTORIAL ON HOW TO DO STRUCTURES			|
     * |									|
     * -------------------------------------------------------------------------
     * 
     * Don't worry, I actually have a structure tutorial
     * mod already setup for you to check out! It's full
     * of comments on what does what and how to make structures.
     * 
     * Here's the link! https://github.com/TelepathicGrunt/StructureTutorialMod
     * 
     * Good luck and have fun modding!
     */
	private static final List<Biome.SpawnListEntry> WITCH_HUT_ENEMIES = Lists.newArrayList(new Biome.SpawnListEntry(EntityType.WITCH, 1, 1, 1));
	private static final List<Biome.SpawnListEntry> WITCH_HUT_PASSIVE = Lists.newArrayList(new Biome.SpawnListEntry(EntityType.CAT, 1, 1, 1));


	public WitchHutStructureUA(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51427_1_)
	{
		super(p_i51427_1_);
	}


	@Override
	public Structure.IStartFactory getStartFactory()
	{
		return WitchHutStructureUA.Start::new;
	}


	@Override
	protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ)
	{
		int maxDistance = UltraAmplified.UAConfig.witchHutSpawnrate.get();
		int minDistance = 8;
		if (maxDistance < 9)
		{
			minDistance = maxDistance - 1;
		}
		int k = x + maxDistance * spacingOffsetsX;
		int l = z + maxDistance * spacingOffsetsZ;
		int i1 = k < 0 ? k - maxDistance + 1 : k;
		int j1 = l < 0 ? l - maxDistance + 1 : l;
		int k1 = i1 / maxDistance;
		int l1 = j1 / maxDistance;
		((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), k1, l1, this.getSeedModifier());
		k1 = k1 * maxDistance;
		l1 = l1 * maxDistance;
		k1 = k1 + random.nextInt(maxDistance - minDistance);
		l1 = l1 + random.nextInt(maxDistance - minDistance);
		return new ChunkPos(k1, l1);
	}


	@Override
	public String getStructureName()
	{
		return UltraAmplified.MODID + ":witch_hut";
	}


	@Override
	public int getSize()
	{
		return 3;
	}


	protected int getSeedModifier()
	{
		return 14357620;
	}


	@Override
	public List<Biome.SpawnListEntry> getSpawnList()
	{
		return WITCH_HUT_ENEMIES;
	}


	@Override
	public List<Biome.SpawnListEntry> getCreatureSpawnList()
	{
		return WITCH_HUT_PASSIVE;
	}


	public boolean func_202383_b(IWorld p_202383_1_, BlockPos p_202383_2_)
	{
		StructureStart structurestart = this.getStart(p_202383_1_, p_202383_2_, true);
		if (structurestart != StructureStart.DUMMY && structurestart instanceof WitchHutStructureUA.Start && !structurestart.getComponents().isEmpty())
		{
			StructurePiece structurepiece = structurestart.getComponents().get(0);
			return structurepiece instanceof WitchHutPiecesUA;
		}
		else
		{
			return false;
		}
	}


	@Override
	public boolean func_225558_a_(BiomeManager p_225558_1_, ChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ, Biome biome)
	{
		ChunkPos chunkpos = this.getStartPositionForPosition(chunkGen, rand, chunkPosX, chunkPosZ, 0, 0);
		if (chunkPosX == chunkpos.x && chunkPosZ == chunkpos.z)
		{
			if (UltraAmplified.UAConfig.witchHutSpawnrate.get() != 101 && chunkGen.hasStructure(biome, this))
			{
				return true;
			}
		}
		return false;
	}

	public static class Start extends StructureStart
	{
		public Start(Structure<?> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn)
		{
			super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
		}


		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int x, int z, Biome biomeIn)
		{

			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
			int i = 5;
			int j = 5;
			if (rotation == Rotation.CLOCKWISE_90)
			{
				i = -5;
			}
			else if (rotation == Rotation.CLOCKWISE_180)
			{
				i = -5;
				j = -5;
			}
			else if (rotation == Rotation.COUNTERCLOCKWISE_90)
			{
				j = -5;
			}

			int k = (x << 4) + 7;
			int l = (z << 4) + 7;
			int i1 = generator.func_222531_c(k, l, Heightmap.Type.WORLD_SURFACE_WG);
			int j1 = generator.func_222531_c(k, l + j, Heightmap.Type.WORLD_SURFACE_WG);
			int k1 = generator.func_222531_c(k + i, l, Heightmap.Type.WORLD_SURFACE_WG);
			int l1 = generator.func_222531_c(k + i, l + j, Heightmap.Type.WORLD_SURFACE_WG);
			int y = Math.min(Math.min(i1, j1), Math.min(k1, l1));
			y = Math.min(y, 244);

			if (y >= 70)
			{
				WitchHutPiecesUA swamphutpiece = new WitchHutPiecesUA(this.rand, x * 16, y, z * 16);
				this.components.add(swamphutpiece);
				this.recalculateStructureSize();
				// UltraAmplified.LOGGER.log(Level.DEBUG, "Witch Hut | "+(x*16)+" "+(z*16));
			}
		}
	}
}