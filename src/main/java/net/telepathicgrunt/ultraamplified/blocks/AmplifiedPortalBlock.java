package net.telepathicgrunt.ultraamplified.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.EndPortalTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.telepathicgrunt.ultraamplified.world.dimension.UltraAmplifiedDimension;


public class AmplifiedPortalBlock extends Block
{
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);


	public AmplifiedPortalBlock()
	{
		super(Block.Properties.create(Material.GLASS, MaterialColor.BLACK).lightValue(15).hardnessAndResistance(5.0F, 3600000.0F));

		setRegistryName("amplified_portal");
	}


	public TileEntity createNewTileEntity(IBlockReader worldIn)
	{
		return new EndPortalTileEntity();
	}


	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}

	/**
	 * mining portal block in ultra amplified dimension will be denied if it is the highest Amplified Portal Block at x=8,
	 * z=8
	 *
	 * :Forge notes: Called when a player removes a block. This is responsible for actually destroying the block, and the
	 * block is intact at time of call. This is called regardless of whether the player can harvest the block or not.
	 *
	 * Return true if the block is actually destroyed.
	 *
	 * Note: When used in multiplayer, this is called on both client and server sides!
	 *
	 * @param state       The current state.
	 * @param world       The current world
	 * @param player      The player damaging the block, may be null
	 * @param pos         Block position in world
	 * @param willHarvest True if Block.harvestBlock will be called after this, if the return in true. Can be useful to
	 *                    delay the destruction of tile entities till after harvestBlock
	 * @param fluid       The current fluid state at current position
	 * @return True if the block is actually destroyed.
	 */
	@Override
	public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluid)
	{

		// if player is in creative mode, just remove block completely
		if (player != null && player.isCreative())
		{
			getBlock().onBlockHarvested(world, pos, state, player);
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
			return true;
		}

		// otherwise, check to see if we are mining the highest portal block at world
		// origin in UA dimension
		else
		{
			// if we are in UA dimension
			if (world.getDimension().getType() == UltraAmplifiedDimension.ultraamplified())
			{

				// if we are at default portal coordinate
				if (pos.getX() == 8 && pos.getZ() == 8)
				{

					// finds the highest portal at world origin
					BlockPos posOfHighestPortal = new BlockPos(pos.getX(), 255, pos.getZ());
					while (posOfHighestPortal.getY() >= 0)
					{
						Block blockToCheck = world.getBlockState(posOfHighestPortal).getBlock();
						if (blockToCheck == BlocksInit.AMPLIFIEDPORTAL.get())
						{
							break;
						}

						posOfHighestPortal = posOfHighestPortal.down();
					}

					// if this block being broken is the highest portal, return false to end method
					// and not break the portal block
					if (posOfHighestPortal.getY() == pos.getY())
					{
						return false;
					}
				}
			}
		}

		// otherwise, allow the block to break
		getBlock().onBlockHarvested(world, pos, state, player);
		return world.removeBlock(pos, false);
	}



	/**
	 * faster particle movement than normal EndPortal block
	 */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		double d0 = (double) ((float) pos.getX() + (rand.nextFloat() * 3 - 1));
		double d1 = (double) ((float) pos.getY() + (rand.nextFloat() * 3 - 1));
		double d2 = (double) ((float) pos.getZ() + (rand.nextFloat() * 3 - 1));
		worldIn.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}


	// has no item form
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state)
	{
		return ItemStack.EMPTY;
	}

}