package net.telepathicgrunt.ultraamplified.world.biomes;

import com.google.common.collect.Lists;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureRadiusConfig;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.CountConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.DungeonRoomConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.telepathicgrunt.ultraamplified.config.ConfigUA;
import net.telepathicgrunt.ultraamplified.world.biome.BiomeUA;
import net.telepathicgrunt.ultraamplified.world.feature.FeatureUA;
import net.telepathicgrunt.ultraamplified.world.feature.config.ColumnBlocksConfig;
import net.telepathicgrunt.ultraamplified.world.feature.config.CountRangeAndTypeConfig;
import net.telepathicgrunt.ultraamplified.world.feature.config.PercentageAndFrequencyConfig;
import net.telepathicgrunt.ultraamplified.world.feature.structure.FortressConfigUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.MineshaftConfigUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.MineshaftUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.VillagePiecesUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.VillageUAConfig;

public class IceSpikesBiomeUA extends BiomeUA {
	public IceSpikesBiomeUA() {
		super((new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState()))).precipitation(Biome.RainType.SNOW).category(Biome.Category.ICY).depth(0.425F).scale(0.45F).temperature(0.0F).downfall(0.5F).waterColor(13172735).waterFogColor(13172735).parent("snowy_tundra"));
		this.addStructure(FeatureUA.MINESHAFT_UA, new MineshaftConfigUA((double) ConfigUA.mineshaftSpawnrate, MineshaftUA.Type.ICEY));
		this.addStructure(FeatureUA.STRONGHOLD_UA, IFeatureConfig.NO_FEATURE_CONFIG);
		this.addStructure(FeatureUA.FORTRESS_UA, new FortressConfigUA(false));
		this.addStructure(FeatureUA.IGLOO_UA, IFeatureConfig.NO_FEATURE_CONFIG);

		this.addStructure(FeatureUA.VILLAGE_UA, new VillageUAConfig(0, VillagePiecesUA.Type.ICY));

		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(CAVE_CAVITY_CARVER, new ProbabilityConfig((float) (ConfigUA.caveCavitySpawnrate) / 1000)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(RAVINE_CARVER, new ProbabilityConfig((float) (ConfigUA.ravineSpawnrate) / 100)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(LONG_RAVINE_CARVER, new ProbabilityConfig((float) (ConfigUA.ravineSpawnrate) / 850)));
		this.addStructureFeaturesUA();
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(FeatureUA.COLUMN, new ColumnBlocksConfig(Blocks.SNOW_BLOCK.getDefaultState(), Blocks.SNOW_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState()), Placement.COUNT_RANGE, new CountRangeConfig(2, 70, 0, 220)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(FeatureUA.RAMP, new ColumnBlocksConfig(Blocks.SNOW_BLOCK.getDefaultState(), Blocks.SNOW_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState()), Placement.COUNT_RANGE, new CountRangeConfig(2, 70, 0, 220)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(FeatureUA.GLOWPATCH, new CountConfig(100), Placement.CHANCE_RANGE, new ChanceRangeConfig((ConfigUA.glowstoneVariantsSpawnrate / 100f) * 0.4f, 45, 45, 60)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(FeatureUA.CONTAIN_LIQUID, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.LAKE, new LakesConfig(Blocks.WATER.getDefaultState()), LAKE_PLACEMENT, new CountRangeAndTypeConfig(4, CountRangeAndTypeConfig.Type.WATER)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.LAKE, new LakesConfig(Blocks.LAVA.getDefaultState()), LAKE_PLACEMENT, new CountRangeAndTypeConfig(80, CountRangeAndTypeConfig.Type.LAVA)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(FeatureUA.SLIME_AND_ICE_LAKE, new LakesConfig(Blocks.SLIME_BLOCK.getDefaultState()), LAKE_PLACEMENT, new CountRangeAndTypeConfig(7, CountRangeAndTypeConfig.Type.SLIME)));
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(FeatureUA.GIANT_ICE_SPIKE, IFeatureConfig.NO_FEATURE_CONFIG, AT_SURFACE_THROUGH_WATER_WITH_EXTRA_UA, new AtSurfaceWithExtraConfig(3, 0.2F, 1)));
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.ICE_PATCH, new FeatureRadiusConfig(2), Placement.COUNT_TOP_SOLID, new FrequencyConfig(2)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, Biome.createDecoratedFeature(FeatureUA.SNOW_DUNGEONS, IFeatureConfig.NO_FEATURE_CONFIG, DUNGEON_PLACEMENT, new DungeonRoomConfig(ConfigUA.dungeonSpawnrate)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIRT.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 175)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GRAVEL.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 256)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GRANITE.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(17, 0, 0, 100)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIORITE.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(14, 0, 0, 150)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.ANDESITE.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 200)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.COAL_ORE.getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(ConfigUA.coalOreSpawnrate, 0, 0, 240)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(ConfigUA.ironOreSpawnrate, 0, 0, 200)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GOLD_ORE.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(ConfigUA.goldOreSpawnrate, 0, 0, 50)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.REDSTONE_ORE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(ConfigUA.redstoneOreSpawnrate, 0, 0, 25)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIAMOND_ORE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(ConfigUA.diamondOreSpawnrate, 0, 0, 25)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.LAPIS_ORE.getDefaultState(), 7), Placement.COUNT_DEPTH_AVERAGE, new DepthAverageConfig(ConfigUA.lapisOreSpawnrate, 20, 20)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GOLD_ORE.getDefaultState(), 7), Placement.COUNT_RANGE, new CountRangeConfig(ConfigUA.goldOreSpawnrate, ConfigUA.seaLevel - 18, 0, 15)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.REDSTONE_ORE.getDefaultState(), 7), Placement.COUNT_RANGE, new CountRangeConfig(ConfigUA.redstoneOreSpawnrate / 2, ConfigUA.seaLevel - 15, 0, 10)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.LAPIS_ORE.getDefaultState(), 6), Placement.COUNT_DEPTH_AVERAGE, new DepthAverageConfig(ConfigUA.lapisOreSpawnrate / 2, ConfigUA.seaLevel - 10, 8)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(Blocks.COARSE_DIRT.getDefaultState(), 7, 2, Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.STONE.getDefaultState())), CHANCE_ON_ALL_WATER_BOTTOMS_UA, new PercentageAndFrequencyConfig(0.9F, 3)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(Blocks.CLAY.getDefaultState(), 4, 1, Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.CLAY.getDefaultState(), Blocks.STONE.getDefaultState())), CHANCE_ON_ALL_WATER_BOTTOMS_UA, new PercentageAndFrequencyConfig(1F, 4)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(Blocks.GRAVEL.getDefaultState(), 6, 2, Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.STONE.getDefaultState())), CHANCE_ON_ALL_WATER_BOTTOMS_UA, new PercentageAndFrequencyConfig(1F, 2)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SPRUCE_TREE, IFeatureConfig.NO_FEATURE_CONFIG, AT_SURFACE_WITH_EXTRA_UA, new AtSurfaceWithExtraConfig(0, 0.1F, 1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.DEFAULT_FLOWER, IFeatureConfig.NO_FEATURE_CONFIG, CHANCE_ON_ALL_SURFACES_UA, new PercentageAndFrequencyConfig(0.20F, 2)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(Blocks.GRASS.getDefaultState()), CHANCE_ON_ALL_SURFACES_UA, new PercentageAndFrequencyConfig(0.25F, 3)));
		DefaultBiomeFeatures.addMushrooms(this);
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.REED, IFeatureConfig.NO_FEATURE_CONFIG, CHANCE_ON_ALL_SURFACES_UA, new PercentageAndFrequencyConfig(0.80F, 6)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.PUMPKIN, IFeatureConfig.NO_FEATURE_CONFIG, TWICE_SURFACE_WITH_CHANCE_UA, new ChanceConfig(32)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SPRING_FEATURE, new LiquidsConfig(Fluids.WATER.getDefaultState()), Placement.COUNT_BIASED_RANGE, new CountRangeConfig(ConfigUA.waterfallSpawnrate / 34, 8, 8, 256)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SPRING_FEATURE, new LiquidsConfig(Fluids.WATER.getDefaultState()), Placement.COUNT_BIASED_RANGE, new CountRangeConfig(ConfigUA.waterfallSpawnrate, 75, 8, 175)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SPRING_FEATURE, new LiquidsConfig(Fluids.LAVA.getDefaultState()), Placement.COUNT_VERY_BIASED_RANGE, new CountRangeConfig(ConfigUA.lavafallSpawnrate, 75, 16, 175)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SPRING_FEATURE, new LiquidsConfig(Fluids.LAVA.getDefaultState()), Placement.COUNT_VERY_BIASED_RANGE, new CountRangeConfig(ConfigUA.lavafallSpawnrate, 8, 16, 70)));
		this.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, Biome.createDecoratedFeature(FeatureUA.ICE_AND_SNOW_UNDER_LEDGES, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.RABBIT, 10, 2, 3));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.POLAR_BEAR, 1, 1, 2));
		this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SQUID, 2, 1, 4));
		this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SALMON, 5, 1, 5));
		this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.DROWNED, 5, 1, 5));
		this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 20, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.STRAY, 80, 4, 4));
	}

	/**
	 * returns the chance a creature has to spawn.
	 */
	public float getSpawningChance() {
		return 0.07F;
	}
}