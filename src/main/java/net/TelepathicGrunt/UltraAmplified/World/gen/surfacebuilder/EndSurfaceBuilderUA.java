package net.TelepathicGrunt.UltraAmplified.World.gen.surfacebuilder;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class EndSurfaceBuilderUA implements ISurfaceBuilder<SurfaceBuilderConfig> {
   private static final IBlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
   private static final IBlockState ENDSTONE = Blocks.END_STONE.getDefaultState();

   public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, IBlockState defaultBlock, IBlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
	  int i = seaLevel + 1;
      int j = x & 15;
      int k = z & 15;
      int l = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
      int i1 = -1;
      IBlockState iblockstate = ENDSTONE;
      IBlockState iblockstate1 = ENDSTONE;

      for(int j1 = 255; j1 >= 0; --j1) {
         blockpos$mutableblockpos.setPos(j, j1, k);
         IBlockState iblockstate2 = chunkIn.getBlockState(blockpos$mutableblockpos);
         if (iblockstate2.getBlock() != null && iblockstate2.getMaterial() != Material.AIR) {
        	 if (iblockstate2 == ENDSTONE) {
        		
    	       if (i1 == -1) {
                  if (l <= 0) {
                     iblockstate = CAVE_AIR;
                     iblockstate1 = ENDSTONE;
                  } else if (j1 >= i - 4) {
                     iblockstate = ENDSTONE;
                     iblockstate1 = ENDSTONE;
                     
                  }
                  
                  i1 = l;
                  if (j1 >= i - 1) {
                     chunkIn.setBlockState(blockpos$mutableblockpos, iblockstate, false);
                  } else {
                     chunkIn.setBlockState(blockpos$mutableblockpos, iblockstate1, false);
                  }
               } else if (i1 > 0) {
                  --i1;
                  chunkIn.setBlockState(blockpos$mutableblockpos, iblockstate1, false);
               }
        	 }
            
         } else {
            i1 = -1;
         }
      }

   }
}