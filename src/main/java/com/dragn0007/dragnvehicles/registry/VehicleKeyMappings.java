package com.dragn0007.dragnvehicles.registry;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

public class VehicleKeyMappings {
    public static final KeyMapping DRIFT_KEY = new KeyMapping(
            "key.dragnvehicles.drift",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_SPACE,
            "key.categories.movement"
    );

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(DRIFT_KEY);
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
    }

    public static class KeyInputHandler {
        @SubscribeEvent
        public void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                if (DRIFT_KEY.isDown()) {
                }
            }
        }
    }
}
