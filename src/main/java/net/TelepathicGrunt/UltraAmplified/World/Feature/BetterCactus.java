package net.telepathicgrunt.ultraamplified.world.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class BetterCactus extends Feature<NoFeatureConfig> {
	   
	public BetterCactus(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
		// TODO Auto-generated constructor stub
	}

	private final int height = 8; 
	
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random rand, BlockPos position, NoFeatureConfig p_212245_5_)
    {
    	//randomly set this cactus to a random spot. (thus passed in position must be the corner of the 4 loaded chunks)
        BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

        if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.down()) == Blocks.SAND.getDefaultState())
        {
            int maxHeight = height + rand.nextInt(2);

            //creates main body of cactus
            for (int currentHeight = 0; currentHeight < maxHeight; ++currentHeight)
            {
            	if(!worldIn.isAirBlock(blockpos.up(currentHeight))) {
            		break;
            	}
                worldIn.setBlockState(blockpos.up(currentHeight), Blocks.CACTUS.getDefaultState(), 18);
            }
            
            
            int facingOffset = rand.nextBoolean() ? 2 : 4;
            
            for(int i = 0; i < 2; i++) {
            	//used to determine what arms will spawn for this cactus
            	if(rand.nextInt(3) < 2) {
	            int ArmHeight = rand.nextInt(maxHeight-5)+2;
	            int ArmTallness = ArmHeight + rand.nextInt(maxHeight - ArmHeight - 2) + 2;
	            
	            if(worldIn.isAirBlock(blockpos.offset(Direction.byIndex(facingOffset+i)).offset(Direction.byIndex(facingOffset+i))) && 
	               worldIn.getBlockState(blockpos.down().offset(Direction.byIndex(facingOffset+i)).offset(Direction.byIndex(facingOffset+i))) == Blocks.SAND.getDefaultState()) 
	            {
	            	for (int k = 0; k < ArmTallness; ++k)
	            	{
		            	if(!worldIn.isAirBlock(blockpos.up(k).offset(Direction.byIndex(facingOffset+i)).offset(Direction.byIndex(facingOffset+i)))
		                ){
		            		break;
		            	}
		            	
		                worldIn.setBlockState(blockpos.up(k).offset(Direction.byIndex(facingOffset+i)).offset(Direction.byIndex(facingOffset+i)), 
		                					  Blocks.CACTUS.getDefaultState(), 
	                					  18);
	            	}
	            }
        		 
         	}
            
            	
            
            /*
            //below code will generate cactus facing north/south 50% of the time and east/west the other times.
            //it will also generate just left arm 1/3rd of time, just right arm 1/3rd of time, and both arms the other 1/3rd of times.
            
            int facingOffset = rand.nextBoolean() ? 2 : 4;
            
            for(int i = 0; i < 2; i++) {
            	//used to determine what arms will spawn for this cactus
            	if(rand.nextInt(3) < 2) {
            		
            		//trying to make sure arm does not equal or exceed main body height.
	            	int ArmHeight = rand.nextInt(maxHeight-5)+2;
	            	int ArmTallness = ArmHeight + rand.nextInt(maxHeight - ArmHeight - 2) + 2;
	            	
	            	if(worldIn.isAirBlock(blockpos.up(ArmHeight).offset(Direction.byIndex(facingOffset+i)))) 
	            	{
	            		worldIn.setBlockState(blockpos.up(ArmHeight).offset(Direction.byIndex(facingOffset+i)), 
	            							Blocks.CACTUS.getDefaultState(), 
	            							18);
	            	}
	            	
		            for (int k = ArmHeight; k < ArmTallness; ++k)
		            {
		            	if(!worldIn.isAirBlock(blockpos.up(k).offset(Direction.byIndex(facingOffset+i)).offset(Direction.byIndex(facingOffset+i)))
		                ){
		            		break;
		            	}
		            	
		                worldIn.setBlockState(blockpos.up(k).offset(Direction.byIndex(facingOffset+i)).offset(Direction.byIndex(facingOffset+i)), 
		                					  Blocks.CACTUS.getDefaultState(), 
		                					  18);
		            }
            	}*/
            }
            
        }
        
        //cactus finished generating
        return true;
    }
}
