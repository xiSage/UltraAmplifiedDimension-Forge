package com.telepathicgrunt.ultraamplified;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.telepathicgrunt.ultraamplified.blocks.BlockColorManager;
import net.telepathicgrunt.ultraamplified.blocks.BlocksInit;
import net.telepathicgrunt.ultraamplified.config.ConfigUA;
import net.telepathicgrunt.ultraamplified.world.biome.BiomeInit;
import net.telepathicgrunt.ultraamplified.world.feature.FeatureUA;
import net.telepathicgrunt.ultraamplified.world.feature.structure.StructureInit;
import net.telepathicgrunt.ultraamplified.world.worldtypes.WorldTypeUA;

/*
 * Mod for making insane landscape and a more challenging world to survive on!
 * If you have any questions, advice, or suggestions for this mod, reach out to me on Reddit! (my name is TelepathicGrunt on there as well) 
 * 
 * @author TelepathicGrunt
 */
		//workaround because the events for colors is firing before the blocks are registered which is causing a crash
		//BlockColorManager.onBlockColorsInit(Minecraft.getInstance().getBlockColors());
		//BlockColorManager.onItemColorsInit(Minecraft.getInstance().getItemColors(), Minecraft.getInstance().getBlockColors());
@Mod(UltraAmplified.MODID)
public class UltraAmplified {
	
	public static UltraAmplified instance;
	public static final String MODID = "ultra_amplified_mod";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	//worldTypes
	public static WorldType UltraAmplified;
	
	public UltraAmplified() {
		instance = this;
		
        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::modConfig);
        modEventBus.register(new BlockColorManager());

		//generates config
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigUA.SERVER_SPEC);

	}
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerBiomes(RegistryEvent.Register<Biome> event){
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
	

	
	
	private void setup(final FMLCommonSetupEvent event) 
	{
		//registers the worldtype used for this mod so we can select that worldtype
		UltraAmplified = new WorldTypeUA();
		StructureInit.registerStructurePieces();
	}
	
	public void modConfig(ModConfig.ModConfigEvent event)
    {
        ModConfig config = event.getConfig();
        if (config.getSpec() == ConfigUA.SERVER_SPEC)
            ConfigUA.refreshServer();
    }
	
}