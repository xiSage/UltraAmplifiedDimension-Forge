package net.telepathicgrunt.ultraamplified.world.feature.structure;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.tileentity.BrewingStandTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.ScatteredStructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class WitchHutPiecesUA extends ScatteredStructurePiece
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
	private boolean witch;
	private boolean cat;


	public WitchHutPiecesUA(Random random, int x, int y, int z)
	{
		super(StructureInitUA.TESHUA, random, x, y, z, 7, 7, 9);
	}


	public WitchHutPiecesUA(TemplateManager templateManager, CompoundNBT data)
	{
		super(StructureInitUA.TESHUA, data);
		this.witch = data.getBoolean("Witch");
		this.cat = data.getBoolean("Cat");
	}


	/**
	 * (abstract) Helper method to read subclass data from NBT
	 */
	@Override
	protected void readAdditional(CompoundNBT data)
	{
		super.readAdditional(data);
		data.putBoolean("Witch", this.witch);
		data.putBoolean("Cat", this.cat);
	}


	@Override
	public boolean create(IWorld world, ChunkGenerator<?> chunkGenerator, Random random, MutableBoundingBox box, ChunkPos chunkPos)
	{
		if (!this.isInsideBounds(world, box, 0))
		{
			return false;
		}
		else
		{
			this.fillWithBlocks(world, box, 1, 1, 1, 5, 1, 7, Blocks.SPRUCE_PLANKS.getDefaultState(), Blocks.SPRUCE_PLANKS.getDefaultState(), false);
			this.fillWithBlocks(world, box, 1, 4, 2, 5, 4, 7, Blocks.SPRUCE_PLANKS.getDefaultState(), Blocks.SPRUCE_PLANKS.getDefaultState(), false);
			this.fillWithBlocks(world, box, 2, 1, 0, 4, 1, 0, Blocks.SPRUCE_PLANKS.getDefaultState(), Blocks.SPRUCE_PLANKS.getDefaultState(), false);
			this.fillWithBlocks(world, box, 2, 2, 2, 3, 3, 2, Blocks.SPRUCE_PLANKS.getDefaultState(), Blocks.SPRUCE_PLANKS.getDefaultState(), false);
			this.fillWithBlocks(world, box, 1, 2, 3, 1, 3, 6, Blocks.SPRUCE_PLANKS.getDefaultState(), Blocks.SPRUCE_PLANKS.getDefaultState(), false);
			this.fillWithBlocks(world, box, 5, 2, 3, 5, 3, 6, Blocks.SPRUCE_PLANKS.getDefaultState(), Blocks.SPRUCE_PLANKS.getDefaultState(), false);
			this.fillWithBlocks(world, box, 2, 2, 7, 4, 3, 7, Blocks.SPRUCE_PLANKS.getDefaultState(), Blocks.SPRUCE_PLANKS.getDefaultState(), false);
			this.fillWithBlocks(world, box, 1, 0, 2, 1, 3, 2, Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LOG.getDefaultState(), false);
			this.fillWithBlocks(world, box, 5, 0, 2, 5, 3, 2, Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LOG.getDefaultState(), false);
			this.fillWithBlocks(world, box, 1, 0, 7, 1, 3, 7, Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LOG.getDefaultState(), false);
			this.fillWithBlocks(world, box, 5, 0, 7, 5, 3, 7, Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LOG.getDefaultState(), false);
			this.setBlockState(world, Blocks.OAK_FENCE.getDefaultState(), 2, 3, 2, box);
			this.setBlockState(world, Blocks.OAK_FENCE.getDefaultState(), 3, 3, 7, box);
			this.setBlockState(world, Blocks.AIR.getDefaultState(), 1, 3, 4, box);
			this.setBlockState(world, Blocks.AIR.getDefaultState(), 5, 3, 4, box);
			this.setBlockState(world, Blocks.AIR.getDefaultState(), 5, 3, 5, box);
			this.setBlockState(world, Blocks.POTTED_RED_MUSHROOM.getDefaultState(), 1, 3, 5, box);
			this.setBlockState(world, Blocks.CRAFTING_TABLE.getDefaultState(), 2, 2, 6, box);
			this.setBlockState(world, Blocks.BREWING_STAND.getDefaultState(), 2, 3, 6, box);
			this.setBlockState(world, Blocks.CAULDRON.getDefaultState().with(CauldronBlock.LEVEL, 2), 4, 2, 6, box);
			this.setBlockState(world, Blocks.OAK_FENCE.getDefaultState(), 1, 2, 1, box);
			this.setBlockState(world, Blocks.OAK_FENCE.getDefaultState(), 5, 2, 1, box);
			BlockState iblockstate = Blocks.SPRUCE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
			BlockState iblockstate1 = Blocks.SPRUCE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.EAST);
			BlockState iblockstate2 = Blocks.SPRUCE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.WEST);
			BlockState iblockstate3 = Blocks.SPRUCE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH);
			this.fillWithBlocks(world, box, 0, 4, 1, 6, 4, 1, iblockstate, iblockstate, false);
			this.fillWithBlocks(world, box, 0, 4, 2, 0, 4, 7, iblockstate1, iblockstate1, false);
			this.fillWithBlocks(world, box, 6, 4, 2, 6, 4, 7, iblockstate2, iblockstate2, false);
			this.fillWithBlocks(world, box, 0, 4, 8, 6, 4, 8, iblockstate3, iblockstate3, false);
			this.setBlockState(world, iblockstate.with(StairsBlock.SHAPE, StairsShape.OUTER_RIGHT), 0, 4, 1, box);
			this.setBlockState(world, iblockstate.with(StairsBlock.SHAPE, StairsShape.OUTER_LEFT), 6, 4, 1, box);
			this.setBlockState(world, iblockstate3.with(StairsBlock.SHAPE, StairsShape.OUTER_LEFT), 0, 4, 8, box);
			this.setBlockState(world, iblockstate3.with(StairsBlock.SHAPE, StairsShape.OUTER_RIGHT), 6, 4, 8, box);

			// following code is a modified version from JavaMan7's youtube tutorial about
			// making structures (But went into detail about how to put potions in brewing
			// stands)
			TileEntity tileentity = world.getTileEntity(new BlockPos(this.getXWithOffset(2, 6), this.getYWithOffset(3), this.getZWithOffset(2, 6)));

			if (tileentity instanceof BrewingStandTileEntity)
			{
				int potionSlot = 1 + random.nextInt(3);

				for (int j = 0; j < potionSlot; j++)
				{

					int potionType = random.nextInt(9);

					ItemStack potion = null;

					// 5/9 chance
					if (potionType < 5)
					{
						potion = new ItemStack(Items.POTION);
						PotionUtils.addPotionToItemStack(potion, Potions.STRONG_LEAPING);
					}
					// 3/9 chance
					else if (potionType < 8)
					{
						potion = new ItemStack(Items.POTION);
						PotionUtils.addPotionToItemStack(potion, Potions.STRONG_POISON);
					}
					// 1/9 chance
					else
					{
						potion = new ItemStack(Items.POTION);
						PotionUtils.addPotionToItemStack(potion, Potions.LONG_NIGHT_VISION);
					}

					((BrewingStandTileEntity) tileentity).setInventorySlotContents(j, potion);
				}
			}

			for (int i = 2; i <= 7; i += 5)
			{
				for (int j = 1; j <= 5; j += 4)
				{
					this.replaceAirAndLiquidDownwards(world, Blocks.OAK_LOG.getDefaultState(), j, -1, i, box);
				}
			}

			if (!this.witch)
			{
				int l = this.getXWithOffset(2, 5);
				int i1 = this.getYWithOffset(2);
				int k = this.getZWithOffset(2, 5);
				if (box.isVecInside(new BlockPos(l, i1, k)))
				{
					this.witch = true;
					for (int i = 0; i < 2; i++)
					{
						WitchEntity entitywitch = EntityType.WITCH.create(world.getWorld());
						entitywitch.enablePersistence();
						entitywitch.setLocationAndAngles(l + 0.5D, i1, k + 0.5D, 0.0F, 0.0F);
						entitywitch.onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(l, i1, k)), SpawnReason.STRUCTURE, (ILivingEntityData) null, (CompoundNBT) null);
						world.addEntity(entitywitch);
					}
				}
			}

			this.spawnCat(world, box);
			return true;
		}
	}


	private void spawnCat(IWorld world, MutableBoundingBox box)
	{
		if (!this.cat)
		{
			int i = this.getXWithOffset(2, 5);
			int j = this.getYWithOffset(2);
			int k = this.getZWithOffset(2, 5);
			if (box.isVecInside(new BlockPos(i, j, k)))
			{
				this.cat = true;
				CatEntity catentity = EntityType.CAT.create(world.getWorld());
				catentity.enablePersistence();
				catentity.setLocationAndAngles(i + 0.5D, j, k + 0.5D, 0.0F, 0.0F);
				catentity.onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(i, j, k)), SpawnReason.STRUCTURE, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(catentity);
			}
		}

	}

}