package net.gensir.cobgyms.world;

import net.gensir.cobgyms.CobGyms;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.Objects;
import java.util.Optional;

public class StructurePlacer {
    public static void placeStructure(StructureWorldAccess world, BlockPos pos, Identifier structureIdentifier) {

        Objects.requireNonNull(world.getServer()).execute(() -> {

            StructureTemplateManager structureTemplateManager = world.getServer().getStructureTemplateManager();
            Optional<StructureTemplate> structureTemplate = structureTemplateManager.getTemplate(structureIdentifier);

            if (structureTemplate.isPresent()) {
                StructurePlacementData structurePlacementData = new StructurePlacementData()
                        .setIgnoreEntities(true)
                        .setMirror(BlockMirror.NONE)
                        .setRotation(BlockRotation.NONE)
                        .setUpdateNeighbors(true);


                structureTemplate.get().place(world, pos, pos, structurePlacementData, null, 18);

                structureTemplateManager.unloadTemplate(structureIdentifier);
                CobGyms.LOGGER.info("Successfully placed structure: " + structureIdentifier);

            } else {
                CobGyms.LOGGER.info("Failed to load structure: " + structureIdentifier);
            }

        });
    }
}
