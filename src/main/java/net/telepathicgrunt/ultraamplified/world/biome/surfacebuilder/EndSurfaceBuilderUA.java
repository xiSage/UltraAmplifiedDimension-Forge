package net.telepathicgrunt.ultraamplified.world.biome.surfacebuilder;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;


public class EndSurfaceBuilderUA extends SurfaceBuilder<SurfaceBuilderConfig>
{
	public EndSurfaceBuilderUA(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> p_i51310_1_)
	{
		super(p_i51310_1_);
	}

	private static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
	private static final BlockState STONE = Blocks.STONE.getDefaultState();
	private static final BlockState ENDSTONE = Blocks.END_STONE.getDefaultState();


	@Override
	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
	{
		//creates grass surface normally
		SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, config);

		int xpos = x & 15;
		int zpos = z & 15;
		int l = (int) (noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
		BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable();
		int i1 = -1;
		BlockState iblockstate = ENDSTONE;
		BlockState iblockstate1 = ENDSTONE;

		//makes stone below sea level into end stone
		for (int ypos = seaLevel - 2; ypos >= 0; --ypos)
		{
			blockpos$Mutable.setPos(xpos, ypos, zpos);
			BlockState iblockstate2 = chunkIn.getBlockState(blockpos$Mutable);

			if (iblockstate2.getBlock() != null && iblockstate2.getMaterial() != Material.AIR)
			{
				if (iblockstate2 == STONE)
				{

					if (i1 == -1)
					{
						if (l <= 0)
						{
							iblockstate = CAVE_AIR;
							iblockstate1 = ENDSTONE;
						}
						else if (ypos >= seaLevel - 3)
						{
							iblockstate = ENDSTONE;
							iblockstate1 = ENDSTONE;

						}

						i1 = l;
						if (ypos >= seaLevel)
						{
							chunkIn.setBlockState(blockpos$Mutable, iblockstate, false);
						}
						else
						{
							chunkIn.setBlockState(blockpos$Mutable, iblockstate1, false);
						}
					}
					else if (i1 > 0)
					{
						--i1;
						chunkIn.setBlockState(blockpos$Mutable, iblockstate1, false);
					}
					else
					{
						chunkIn.setBlockState(blockpos$Mutable, ENDSTONE, false);
					}
				}

			}
			else
			{
				i1 = -1;
			}
		}

	}
}