package net.TelepathicGrunt.UltraAmplified.World.gen.feature.placement;

import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class BlockConfig implements IFeatureConfig {
   public final Block block;

   public BlockConfig(Block blockIn) {
      this.block = blockIn;
   }
}