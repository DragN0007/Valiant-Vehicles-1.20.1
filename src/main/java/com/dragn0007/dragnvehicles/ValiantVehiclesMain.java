package com.dragn0007.dragnvehicles;

import com.dragn0007.dragnvehicles.registry.ItemRegistry;
import com.dragn0007.dragnvehicles.registry.VehicleRegistry;
import com.mojang.logging.LogUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static com.dragn0007.dragnvehicles.ValiantVehiclesMain.MODID;

@Mod(MODID)
public class ValiantVehiclesMain
{
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "dragnvehicles";

    public static final EntityDataSerializer<ResourceLocation> RESOURCE_SERIALIZER = EntityDataSerializer.simple(FriendlyByteBuf::writeResourceLocation, FriendlyByteBuf::readResourceLocation);

    public ValiantVehiclesMain()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EntityDataSerializers.registerSerializer(RESOURCE_SERIALIZER);
        ItemRegistry.ITEMS.register(eventBus);
        VehicleRegistry.ENTITY_TYPES.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(FMLClientSetupEvent event) {
    }

    public static float mod(float n, float m) {
        while(n < 0) {
            n += m;
        }
        return n % m;
    }
}