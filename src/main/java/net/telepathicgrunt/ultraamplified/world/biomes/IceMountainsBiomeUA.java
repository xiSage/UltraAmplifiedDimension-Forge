package net.telepathicgrunt.ultraamplified.world.biomes;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.telepathicgrunt.ultraamplified.world.biome.BiomeUA;
import net.telepathicgrunt.ultraamplified.world.feature.FeatureUA;
import net.telepathicgrunt.ultraamplified.world.feature.config.ColumnBlocksConfig;
import net.telepathicgrunt.ultraamplified.world.feature.structure.FortressConfigUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.MineshaftConfigUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.MineshaftUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.VillageConfigUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.VillagePiecesUA;

public class IceMountainsBiomeUA extends BiomeUA {
	public IceMountainsBiomeUA() {
		super((new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder<>(ICE_MOUNTAIN_SURFACE_BUILDER, SNOWBLOCK_ICE_ICE_SURFACE)).precipitation(Biome.RainType.SNOW).category(Biome.Category.ICY).depth(0.45F).scale(0.3F).temperature(0.0F).downfall(0.5F).waterColor(13172735).waterFogColor(13172735).parent((String) null));
		this.addStructure(FeatureUA.MINESHAFT_UA, new MineshaftConfigUA(MineshaftUA.Type.ICEY));
		this.addStructure(FeatureUA.STRONGHOLD_UA, IFeatureConfig.NO_FEATURE_CONFIG);
		this.addStructure(FeatureUA.FORTRESS_UA, new FortressConfigUA(false));
		this.addStructure(FeatureUA.IGLOO_UA, IFeatureConfig.NO_FEATURE_CONFIG);

		this.addStructure(FeatureUA.VILLAGE_UA, new VillageConfigUA(0, VillagePiecesUA.Type.ICY));

		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(CAVE_CAVITY_CARVER, new ProbabilityConfig(0f)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(RAVINE_CARVER, new ProbabilityConfig(0f)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(LONG_RAVINE_CARVER, new ProbabilityConfig(0f)));
		this.addFeature(GenerationStage.Decoration.RAW_GENERATION, Biome.createDecoratedFeature(FeatureUA.CONTAIN_UNDERGROUND_LIQUID, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		this.addStructureFeaturesUA();
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(FeatureUA.COLUMN, new ColumnBlocksConfig(Blocks.SNOW_BLOCK.getDefaultState(), Blocks.ICE.getDefaultState(), Blocks.ICE.getDefaultState()), Placement.COUNT_RANGE, new CountRangeConfig(2, 70, 0, 220)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(FeatureUA.CONTAIN_LIQUID, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(FeatureUA.SLIME_AND_ICE_LAKE, new LakesConfig(Blocks.PACKED_ICE.getDefaultState()), Placement.COUNT_RANGE, new CountRangeConfig(10, 30, 0, 250)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(FeatureUA.SLIME_AND_ICE_LAKE, new LakesConfig(Blocks.PACKED_ICE.getDefaultState()), Placement.COUNT_RANGE, new CountRangeConfig(8, 17, 0, 100)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(FeatureUA.SLIME_AND_ICE_LAKE, new LakesConfig(Blocks.BLUE_ICE.getDefaultState()), Placement.COUNT_RANGE, new CountRangeConfig(1, 10, 0, 40)));

		this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, Biome.createDecoratedFeature(FeatureUA.SNOW_DUNGEONS, IFeatureConfig.NO_FEATURE_CONFIG, DUNGEON_PLACEMENT, IPlacementConfig.NO_PLACEMENT_CONFIG));
		DefaultBiomeFeatures.addMushrooms(this);
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(FeatureUA.BLUE_ICE_WATERFALL, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_BIASED_RANGE, new CountRangeConfig(1, 8, 8, 256)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(FeatureUA.BLUE_ICE_WATERFALL, IFeatureConfig.NO_FEATURE_CONFIG, HEIGHT_BIASED_RANGE_UA, new CountRangeConfig(1 * 2, 75, 8, 175)));
		this.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, Biome.createDecoratedFeature(FeatureUA.ICE_AND_SNOW_UNDER_LEDGES, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.POLAR_BEAR, 1, 1, 2));
		this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SQUID, 2, 1, 4));
		this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SALMON, 5, 1, 5));
		this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.DROWNED, 5, 1, 5));
		this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 20, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.STRAY, 80, 4, 4));
	}

	/**
	 * returns the chance a creature has to spawn.
	 */
	public float getSpawningChance() {
		return 0.04F;
	}
}