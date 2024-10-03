package com.dragn0007.dragnvehicles;

import com.dragn0007.dragnvehicles.registry.ItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;

@Mod.EventBusSubscriber(modid = ValiantVehiclesMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CreativeTabModifier {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ValiantVehiclesMain.MODID);

       @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
           if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
               event.accept(ItemRegistry.CAR_SPAWN_EGG);
               event.accept(ItemRegistry.CLASSIC_SPAWN_EGG);
               event.accept(ItemRegistry.MOTORCYCLE_SPAWN_EGG);
               event.accept(ItemRegistry.SPORT_CAR_SPAWN_EGG);
               event.accept(ItemRegistry.SUV_SPAWN_EGG);
               event.accept(ItemRegistry.TRUCK_SPAWN_EGG);

               event.accept(ItemRegistry.CAR_BODY);
               event.accept(ItemRegistry.CLASSIC_BODY);
               event.accept(ItemRegistry.MOTORCYCLE_BODY);
               event.accept(ItemRegistry.SPORT_CAR_BODY);
               event.accept(ItemRegistry.SUV_BODY);
               event.accept(ItemRegistry.TRUCK_BODY);

               event.accept(ItemRegistry.WHEEL);
               event.accept(ItemRegistry.SPORTS_WHEEL);
               event.accept(ItemRegistry.ENGINE);
               event.accept(ItemRegistry.SPORTS_ENGINE);
           }
       }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
