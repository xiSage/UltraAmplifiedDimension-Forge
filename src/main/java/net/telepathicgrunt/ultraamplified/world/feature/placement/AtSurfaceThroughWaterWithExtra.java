package net.telepathicgrunt.ultraamplified.world.feature.placement;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;


public class AtSurfaceThroughWaterWithExtra extends Placement<AtSurfaceWithExtraConfig>
{
	public AtSurfaceThroughWaterWithExtra(Function<Dynamic<?>, ? extends AtSurfaceWithExtraConfig> configFactory)
	{
		super(configFactory);
	}


	public Stream<BlockPos> getPositions(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, AtSurfaceWithExtraConfig chancesConfig, BlockPos pos)
	{
		int maxAttempts = chancesConfig.count;
		if (random.nextFloat() < chancesConfig.extraChance)
		{
			maxAttempts += chancesConfig.extraCount;
		}

		ArrayList<BlockPos> blockPosList = new ArrayList<BlockPos>();
		BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable(pos);

		for (int currentAttempt = 0; currentAttempt < maxAttempts; currentAttempt++)
		{
			int xOffset = random.nextInt(16);
			int zOffset = random.nextInt(16);
			blockpos$Mutable.setPos(pos.getX() + xOffset, 250, pos.getZ() + zOffset);

			while (blockpos$Mutable.getY() > 60)
			{
				int yPosOfSurface = PlacingUtils.topOfUnderwaterSurfaceBelowHeight(world, blockpos$Mutable.getY(), random, blockpos$Mutable);

				//gets difference and sets mutable height to yPosOfSurface
				blockPosList.add(blockpos$Mutable.move(0, yPosOfSurface - blockpos$Mutable.getY(), 0));

				//move down one for next surface searching to not get stuck at surface we are on now.
				blockpos$Mutable.move(Direction.DOWN);
			}

		}

		return IntStream.range(0, blockPosList.size()).mapToObj((p_215051_3_) ->
		{
			return blockPosList.remove(0);
		}).filter(Objects::nonNull);

	}
}