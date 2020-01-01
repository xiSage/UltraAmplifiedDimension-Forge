package net.telepathicgrunt.ultraamplified;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Item;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.telepathicgrunt.ultraamplified.blocks.BlockColorManager;
import net.telepathicgrunt.ultraamplified.blocks.BlocksInit;
import net.telepathicgrunt.ultraamplified.capabilities.CapabilityPlayerPosAndDim;
import net.telepathicgrunt.ultraamplified.config.ConfigUA;
import net.telepathicgrunt.ultraamplified.world.biome.BiomeInit;
import net.telepathicgrunt.ultraamplified.world.feature.FeatureUA;
import net.telepathicgrunt.ultraamplified.world.feature.carver.CaveCavityCarver;
import net.telepathicgrunt.ultraamplified.world.feature.carver.RavineCarver;
import net.telepathicgrunt.ultraamplified.world.feature.carver.SuperLongRavineCarver;
import net.telepathicgrunt.ultraamplified.world.feature.carver.UnderwaterCaveCarver;
import net.telepathicgrunt.ultraamplified.world.feature.structure.StructureInitUA;
import net.telepathicgrunt.ultraamplified.world.worldtypes.WorldTypeUA;

/*
 * Mod for making insane landscape and a more challenging world to survive on!
 * If you have any questions, advice, or suggestions for this mod, reach out to me on Reddit! (my name is TelepathicGrunt on there as well) 
 * 
 * @author TelepathicGrunt
 */
@Mod(UltraAmplified.MODID)
public class UltraAmplified {
	
	public static UltraAmplified instance;
	public static final String MODID = "ultra_amplified_dimension";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	//worldTypes
	public static WorldType UltraAmplified;
	
	public UltraAmplified() {
		instance = this;
		
        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::modConfig);
        modEventBus.addListener(this::clientSetup);
        modEventBus.register(new BlockColorManager());

		//generates config
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigUA.SERVER_SPEC);
	}
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerBiomes(final RegistryEvent.Register<Biome> event){
			//registers all my modified biomes
			BiomeInit.registerBiomes(event);
			LOGGER.log(Level.INFO, "Biomes registered.");
		}
		
		/**
		 * This method will be called by Forge when it is time for the mod to register its Blocks.
		 * This method will always be called before the Item registry method.
		 */
		@SubscribeEvent
		public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
			BlocksInit.registerBlocks(event);
			LOGGER.log(Level.INFO, "Blocks registered.");
		}
		
		/**
		 * This method will be called by Forge when it is time for the mod to register its Items.
		 * This method will always be called after the Block registry method.
		 */
		@SubscribeEvent
		public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
			BlocksInit.registerItems(event);
			LOGGER.log(Level.INFO, "Items registered.");
		}

		/**
		 * This method will be called by Forge when it is time for the mod to register features.
		 */
		@SubscribeEvent
		public static void onRegisterFeatures(final RegistryEvent.Register<Feature<?>> event) {
			FeatureUA.registerFeatures(event);
			LOGGER.log(Level.INFO, "features registered.");
		}

	}
	
	@OnlyIn(Dist.CLIENT)
	public void clientSetup(final FMLClientSetupEvent event) 
	{
		RenderTypeLookup.setRenderLayer(BlocksInit.GLOWGRASS_BLOCK.get(), RenderType.func_228643_e_());
	}
	
	public void setup(final FMLCommonSetupEvent event) 
	{
		//registers the worldtype used for this mod so we can select that worldtype
		UltraAmplified = new WorldTypeUA();
		StructureInitUA.registerStructurePieces();
		CapabilityPlayerPosAndDim.register();
		RavineCarver.setFillerMap();
		SuperLongRavineCarver.setFillerMap();
		UnderwaterCaveCarver.setFillerMap();
		CaveCavityCarver.setFillerMap();
	}
	
	public void modConfig(final ModConfig.ModConfigEvent event)
    {
        ModConfig config = event.getConfig();
        if (config.getSpec() == ConfigUA.SERVER_SPEC)
            ConfigUA.refreshServer();
    }
	
}