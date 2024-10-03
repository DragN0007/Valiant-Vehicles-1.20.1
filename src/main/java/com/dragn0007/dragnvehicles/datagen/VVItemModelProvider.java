package com.dragn0007.dragnvehicles.datagen;

import com.dragn0007.dragnvehicles.ValiantVehiclesMain;
import com.dragn0007.dragnvehicles.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class VVItemModelProvider extends ItemModelProvider {
    public VVItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ValiantVehiclesMain.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ItemRegistry.CAR_SPAWN_EGG);
        simpleItem(ItemRegistry.CLASSIC_SPAWN_EGG);
        simpleItem(ItemRegistry.TRUCK_SPAWN_EGG);
        simpleItem(ItemRegistry.SUV_SPAWN_EGG);
        simpleItem(ItemRegistry.SPORT_CAR_SPAWN_EGG);
        simpleItem(ItemRegistry.MOTORCYCLE_SPAWN_EGG);

        simpleItem(ItemRegistry.CAR_BODY);
        simpleItem(ItemRegistry.CLASSIC_BODY);
        simpleItem(ItemRegistry.TRUCK_BODY);
        simpleItem(ItemRegistry.SUV_BODY);
        simpleItem(ItemRegistry.SPORT_CAR_BODY);
        simpleItem(ItemRegistry.MOTORCYCLE_BODY);
        simpleItem(ItemRegistry.WHEEL);
        simpleItem(ItemRegistry.SPORTS_WHEEL);
        simpleItem(ItemRegistry.ENGINE);
        simpleItem(ItemRegistry.SPORTS_ENGINE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ValiantVehiclesMain.MODID,"item/" + item.getId().getPath()));
    }
}