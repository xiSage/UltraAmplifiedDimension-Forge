package com.TelepathicGrunt.UltraAmplified.LootTable;

import org.apache.logging.log4j.Level;

import com.TelepathicGrunt.UltraAmplified.UltraAmplified;

import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UltraAmplified.modid)
public class JungleTempleLootInjection {
	
	//Will work only on 1.14.3+ due to a bug introduced in forge in 1.13
	
//	@SubscribeEvent
//	public static void lootLoad(LootTableLoadEvent event)
//	{
//		UltraAmplified.Logger.log(Level.DEBUG, "CHECKING CHEST");
//		
//		if (event.getName().toString().equals("minecraft:chests/jungle_temple")){
//		
//			LootEntry diamondHorseArmorEntry = new LootEntry(Items.DIAMOND_HORSE_ARMOR, 10, 80, new LootFunction[0], new LootCondition[0], "ultraamplified:diamond_horse_armor");
//			LootEntry goldHorseArmorEntry = new LootEntry(Items.GOLDEN_HORSE_ARMOR, 20, 40, new LootFunction[0], new LootCondition[0], "ultraamplified:gold_horse_armor");
//			LootEntry cobwebEntry = new LootEntry(Blocks.COBWEB.asItem(), 50, -20, new LootFunction[0], new LootCondition[0], "ultraamplified:cobweb");
//			LootEntry goldenCarrotEntry = new LootEntry(Items.GOLDEN_CARROT, 10, 80, new LootFunction[0], new LootCondition[0], "ultraamplified:golden_carrot");
//			LootEntry poisonousPotatoEntry = new LootEntry(Items.POISONOUS_POTATO, 30, -10, new LootFunction[0], new LootCondition[0], "ultraamplified:poisonous_potato");
//		
//			LootPool newPool1 = new LootPool(new LootEntry[]{diamondHorseArmorEntry, goldHorseArmorEntry, cobwebEntry}, new ILootCondition[0], new ILootFunction[0],  new RandomValueRange(1), new RandomValueRange(0,1), "ultraamplified_pool_inject1");
//			LootPool newPool2 = new LootPool(new LootEntry[]{goldenCarrotEntry, poisonousPotatoEntry, cobwebEntry}, new ILootCondition[0], new ILootFunction[0],  new RandomValueRange(2), new RandomValueRange(1,3), "ultraamplified_pool_inject2");
//		
//			event.getTable().addPool(newPool1);
//			event.getTable().addPool(newPool2);
//	
//		}
//	}
}