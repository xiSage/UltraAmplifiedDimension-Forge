package com.telepathicgrunt.ultraamplifieddimension.mixin.structures;

import com.telepathicgrunt.ultraamplifieddimension.dimension.UADChunkGenerator;
import com.telepathicgrunt.ultraamplifieddimension.world.structures.markerpieces.MarkerPiece;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.EndCityStructure;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@Mixin(EndCityStructure.Start.class)
public abstract class EndCityStructureStartMixin {

//    @Unique
//    private boolean markerAdded = false;

    /**
     * @author TelepathicGrunt
     * @reason Make End Cities not be placed so high in Ultra Amplified Dimension's dimension and cause the game to die due to out of bounds world deadlock.
     */
    @ModifyVariable(
            method = "func_230364_a_(Lnet/minecraft/util/registry/DynamicRegistries;Lnet/minecraft/world/gen/ChunkGenerator;Lnet/minecraft/world/gen/feature/template/TemplateManager;IILnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/feature/NoFeatureConfig;)V",
            at = @At(value = "STORE", ordinal = 0), ordinal = 2
    )
    private int fixedYHeightForUAD(int y, DynamicRegistries dynamicRegistries, ChunkGenerator chunkGenerator) {
        if(chunkGenerator instanceof UADChunkGenerator){
            //((EndCityStructure.Start)(Object)this).getComponents().add(new MarkerPiece(IStructurePieceType.BTP, 0));
            //markerAdded = true;
            return 105;
        }
        return y;
    }

//    /**
//     * @author TelepathicGrunt
//     * @reason Remove marker piece added to components which we used to know when to make End Cities large than usual.
//     */
//    @Inject(
//            method = "func_230364_a_(Lnet/minecraft/util/registry/DynamicRegistries;Lnet/minecraft/world/gen/ChunkGenerator;Lnet/minecraft/world/gen/feature/template/TemplateManager;IILnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/feature/NoFeatureConfig;)V",
//            at = @At(value = "TAIL")
//    )
//    private void removeMarker(DynamicRegistries dynamicRegistries, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig config, CallbackInfo ci) {
//        if(markerAdded && chunkGenerator instanceof UADChunkGenerator){
//            ((EndCityStructure.Start)(Object)this).getComponents().removeIf(piece -> piece instanceof MarkerPiece);
//            markerAdded = false;
//            ((StructureStartAccessor)this).callRecalculateStructureSize(); // Recalculate size without our marker.
//        }
//    }
}