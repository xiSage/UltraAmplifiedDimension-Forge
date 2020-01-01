package net.telepathicgrunt.ultraamplified.world.biomes;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.BuriedTreasureConfig;
import net.minecraft.world.gen.feature.structure.OceanRuinConfig;
import net.minecraft.world.gen.feature.structure.OceanRuinStructure;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.telepathicgrunt.ultraamplified.world.biome.BiomeUA;
import net.telepathicgrunt.ultraamplified.world.feature.FeatureUA;
import net.telepathicgrunt.ultraamplified.world.feature.config.CountRangeAndTypeConfig;
import net.telepathicgrunt.ultraamplified.world.feature.config.LapisCountRangeConfig;
import net.telepathicgrunt.ultraamplified.world.feature.config.PercentageAndFrequencyConfig;
import net.telepathicgrunt.ultraamplified.world.feature.config.PercentageAndHeightConfig;
import net.telepathicgrunt.ultraamplified.world.feature.structure.FortressConfigUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.MineshaftConfigUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.MineshaftUA;


public class OceanFrozenBiomeUA extends BiomeUA
{
	protected static final PerlinNoiseGenerator field_205163_aV = new PerlinNoiseGenerator(new SharedSeedRandom(3456L), 2, 0);


	public OceanFrozenBiomeUA()
	{
		super((new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder<>(OCEAN_SURFACE_BUILDER_UA, ICE_GRAVEL_STONE_SURFACE)).precipitation(Biome.RainType.SNOW).category(Biome.Category.OCEAN).depth(-1.0F).scale(0.1F).temperature(0.0F).downfall(0.5F).waterColor(3750089).waterFogColor(329011).parent((String) null));

		this.func_226711_a_(FeatureUA.MINESHAFT_UA.func_225566_b_(new MineshaftConfigUA(MineshaftUA.Type.OCEAN)));
		this.func_226711_a_(FeatureUA.STRONGHOLD_UA.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG));
		this.func_226711_a_(FeatureUA.FORTRESS_UA.func_225566_b_(new FortressConfigUA(false)));
		this.func_226711_a_(FeatureUA.OCEAN_RUIN_UA.func_225566_b_(new OceanRuinConfig(OceanRuinStructure.Type.COLD, 0.3F, 0.9F)));
		this.func_226711_a_(FeatureUA.OCEAN_MONUMENT_UA.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG));
		this.func_226711_a_(Feature.BURIED_TREASURE.func_225566_b_(new BuriedTreasureConfig(0.05F)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureUA.MARKED_TREASURE_CHEST_UA.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(FIXED_HEIGHT_WITH_CHANCE.func_227446_a_(new PercentageAndHeightConfig(0.025f, 64))));

		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(CAVE_CAVITY_CARVER, new ProbabilityConfig(0f)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(RAVINE_CARVER, new ProbabilityConfig(0f)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(LONG_RAVINE_CARVER, new ProbabilityConfig(0f)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(UNDERWATER_CAVE_CARVER, new ProbabilityConfig(0f)));

		this.addFeature(GenerationStage.Decoration.RAW_GENERATION, FeatureUA.CONTAIN_UNDERGROUND_LIQUID.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		this.addStructureFeaturesUA();
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, FeatureUA.GLOWPATCH.func_225566_b_(new CountConfig(100)).func_227228_a_(GENERAL_PLACEMENT.func_227446_a_(new CountRangeAndTypeConfig(0.005f, 45, 45, 60, false, CountRangeAndTypeConfig.Type.GLOWSTONE_VARIANT_PATCH))));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, FeatureUA.CONTAIN_LIQUID.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));

		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, FeatureUA.ICEBERG_UA.func_225566_b_(new BlockStateFeatureConfig(Blocks.PACKED_ICE.getDefaultState())).func_227228_a_(CHANCE_ON_ALL_WATER_SURFACES_UA.func_227446_a_(new PercentageAndFrequencyConfig(0.35F, 3))));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, FeatureUA.ICEBERG_UA.func_225566_b_(new BlockStateFeatureConfig(Blocks.BLUE_ICE.getDefaultState())).func_227228_a_(CHANCE_ON_ALL_WATER_SURFACES_UA.func_227446_a_(new PercentageAndFrequencyConfig(0.07F, 1))));

		this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, FeatureUA.SNOW_DUNGEONS.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(DUNGEON_PLACEMENT.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));

		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Feature.BLUE_ICE.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.RANDOM_COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 30, 32, 64))));
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Feature.BLUE_ICE.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.RANDOM_COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 40, 32, 160))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIRT.getDefaultState(), 33)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(6, 0, 0, 175))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GRAVEL.getDefaultState(), 33)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(8, 0, 0, 256))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GRANITE.getDefaultState(), 33)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(10, 0, 0, 100))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIORITE.getDefaultState(), 33)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(6, 0, 0, 150))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.ANDESITE.getDefaultState(), 33)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(13, 0, 0, 200))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.COAL_ORE.getDefaultState(), 17)).func_227228_a_(GENERAL_PLACEMENT.func_227446_a_(new CountRangeAndTypeConfig(1f, 0, 0, 240, false, CountRangeAndTypeConfig.Type.COAL))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.getDefaultState(), 9)).func_227228_a_(GENERAL_PLACEMENT.func_227446_a_(new CountRangeAndTypeConfig(1f, 0, 0, 200, false, CountRangeAndTypeConfig.Type.IRON))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GOLD_ORE.getDefaultState(), 9)).func_227228_a_(GENERAL_PLACEMENT.func_227446_a_(new CountRangeAndTypeConfig(1f, 0, 0, 50, false, CountRangeAndTypeConfig.Type.GOLD))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.REDSTONE_ORE.getDefaultState(), 8)).func_227228_a_(GENERAL_PLACEMENT.func_227446_a_(new CountRangeAndTypeConfig(1f, 0, 0, 25, false, CountRangeAndTypeConfig.Type.REDSTONE))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIAMOND_ORE.getDefaultState(), 8)).func_227228_a_(GENERAL_PLACEMENT.func_227446_a_(new CountRangeAndTypeConfig(1f, 0, 0, 25, false, CountRangeAndTypeConfig.Type.DIAMOND))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.LAPIS_ORE.getDefaultState(), 7)).func_227228_a_(LAPIS_PLACEMENT.func_227446_a_(new LapisCountRangeConfig(1.5f, 20, 20, false))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GOLD_ORE.getDefaultState(), 7)).func_227228_a_(GENERAL_PLACEMENT.func_227446_a_(new CountRangeAndTypeConfig(1f, 18, 0, 15, true, CountRangeAndTypeConfig.Type.GOLD))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.REDSTONE_ORE.getDefaultState(), 7)).func_227228_a_(GENERAL_PLACEMENT.func_227446_a_(new CountRangeAndTypeConfig(0.5f, 15, 0, 10, true, CountRangeAndTypeConfig.Type.REDSTONE))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.LAPIS_ORE.getDefaultState(), 6)).func_227228_a_(LAPIS_PLACEMENT.func_227446_a_(new LapisCountRangeConfig(1f, 10, 8, true))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227247_y_.func_225566_b_(FeatureUA.getConfiguredClusterConfig(DefaultBiomeFeatures.field_226831_z_)).func_227228_a_(CHANCE_ON_ALL_SURFACES_UA.func_227446_a_(new PercentageAndFrequencyConfig(0.2F, 2))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(FeatureUA.getConfiguredClusterConfig(DefaultBiomeFeatures.field_226826_u_)).func_227228_a_(CHANCE_ON_ALL_SURFACES_UA.func_227446_a_(new PercentageAndFrequencyConfig(0.3F, 2))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(DefaultBiomeFeatures.field_226722_J_).func_227228_a_(RANDOM_CHANCE_UNDER_SURFACE.func_227446_a_(new ChanceConfig(1))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(DefaultBiomeFeatures.field_226721_I_).func_227228_a_(RANDOM_CHANCE_UNDER_SURFACE.func_227446_a_(new ChanceConfig(2))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(FeatureUA.getConfiguredClusterConfig(DefaultBiomeFeatures.field_226730_R_)).func_227228_a_(CHANCE_ON_ALL_SURFACES_UA.func_227446_a_(new PercentageAndFrequencyConfig(0.2F, 4))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(DefaultBiomeFeatures.field_226717_E_).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(32))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureUA.KELP_UA.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(CHANCE_ON_ALL_WATER_BOTTOMS_UA.func_227446_a_(new PercentageAndFrequencyConfig(0.1F, 2))));

		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.SPRING_FEATURE.func_225566_b_(new LiquidsConfig(Fluids.WATER.getDefaultState(), true, 4, 1, ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE))).func_227228_a_(WATERFALL_RANGE.func_227446_a_(new CountRangeConfig(30, 8, 8, 256))));

		this.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, FeatureUA.SNOW_AND_ICE_LAYERER.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SQUID, 3, 1, 4));
		this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SALMON, 40, 1, 5));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.POLAR_BEAR, 8, 1, 2));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.TURTLE, 5, 2, 5));
		this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.DROWNED, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
	}


	/**
	 * Gets the current temperature at the given location, based off of the default for this biome, the elevation of the
	 * position, and {@linkplain #TEMPERATURE_NOISE} some random perlin noise.
	 */
	public float getTemperature(BlockPos pos)
	{
		float f = this.getDefaultTemperature();
		double d0 = field_205163_aV.noiseAt((double) pos.getX() * 0.05D, (double) pos.getZ() * 0.05D, false) * 7.0D;
		double d1 = INFO_NOISE.noiseAt((double) pos.getX() * 0.2D, (double) pos.getZ() * 0.2D, false);
		double d2 = d0 + d1;
		if (d2 < 0.3D)
		{
			double d3 = INFO_NOISE.noiseAt((double) pos.getX() * 0.09D, (double) pos.getZ() * 0.09D, false);
			if (d3 < 0.8D)
			{
				f = 0.2F;
			}
		}

		if (pos.getY() > 64)
		{
			float f1 = (float) (TEMPERATURE_NOISE.noiseAt((double) ((float) pos.getX() / 8.0F), (double) ((float) pos.getZ() / 8.0F), false) * 4.0D);
			return f - (f1 + (float) pos.getY() - 64.0F) * 0.05F / 30.0F;
		}
		else
		{
			return f;
		}
	}


	/*
	 * set grass color
	 */
	@OnlyIn(Dist.CLIENT)
	public int func_225528_a_(double p_225528_1_, double p_225528_3_)
	{
		return 37488;
	}


	/*
	 * set foliage/plant color
	 */
	@OnlyIn(Dist.CLIENT)
	public int func_225527_a_()
	{
		return 36456;
	}
}