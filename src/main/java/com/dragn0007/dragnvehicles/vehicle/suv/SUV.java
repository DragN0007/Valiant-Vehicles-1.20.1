package com.dragn0007.dragnvehicles.vehicle.suv;

import com.dragn0007.dragnvehicles.ValiantVehiclesMain;
import com.dragn0007.dragnvehicles.registry.ItemRegistry;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static com.dragn0007.dragnvehicles.ValiantVehiclesMain.mod;

public class SUV extends Entity implements ContainerListener {

    private static final EntityDataAccessor<ResourceLocation> TEXTURE = SynchedEntityData.defineId(SUV.class, ValiantVehiclesMain.RESOURCE_SERIALIZER);

    private static final EntityDataAccessor<Float> HEALTH = SynchedEntityData.defineId(SUV.class, EntityDataSerializers.FLOAT);

    private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/white.png");

    private static final Map<DyeItem, ResourceLocation> COLOR_MAP = new HashMap<>() {{
        put(DyeItem.byColor(DyeColor.BLACK), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/black.png"));
        put(DyeItem.byColor(DyeColor.BLUE), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/blue.png"));
        put(DyeItem.byColor(DyeColor.BROWN), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/white.png"));
        put(DyeItem.byColor(DyeColor.CYAN), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/white.png"));
        put(DyeItem.byColor(DyeColor.GRAY), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/white.png"));
        put(DyeItem.byColor(DyeColor.LIGHT_BLUE), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/light_blue.png"));
        put(DyeItem.byColor(DyeColor.LIGHT_GRAY), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/white.png"));
        put(DyeItem.byColor(DyeColor.LIME), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/white.png"));
        put(DyeItem.byColor(DyeColor.MAGENTA), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/white.png"));
        put(DyeItem.byColor(DyeColor.ORANGE), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/white.png"));
        put(DyeItem.byColor(DyeColor.PINK), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/pink.png"));
        put(DyeItem.byColor(DyeColor.PURPLE), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/purple.png"));
        put(DyeItem.byColor(DyeColor.RED), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/red.png"));
        put(DyeItem.byColor(DyeColor.WHITE), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/white.png"));
        put(DyeItem.byColor(DyeColor.GREEN), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/green.png"));
        put(DyeItem.byColor(DyeColor.YELLOW), new ResourceLocation(ValiantVehiclesMain.MODID, "textures/entity/suv/yellow.png"));
    }};

    private static final float MAX_HEALTH = 25f;
    private static final float SPEED = 0.15f;
    private static final float TURN_SPEED = 1f;
    private static final float MAX_TURN = 6f;
    private static final float FRICTION = 0.7f;
    private static final float GRAVITY = 0.08f;


    private float targetRotation = 0;
    private float currentRotation = 0;

    public int forwardMotion = 1;

    public int driveTick = 0;
    public float lastDrivePartialTick = 0;
    public Vec3 lastPos = Vec3.ZERO;

    public SimpleContainer inventory;
    private LazyOptional<?> itemHandler;

    private int lerpSteps;
    private double targetX;
    private double targetY;
    private double targetZ;
    private float targetYRot;

    public SUV(EntityType < ? > entityType, Level level){
        super(entityType, level);
        this.createInventory();
    }

    private void createInventory() {
        this.inventory = new SimpleContainer(36);
        this.inventory.addListener(this);
        this.itemHandler = LazyOptional.of(() -> new InvWrapper(this.inventory));
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(this.isAlive() && cap == ForgeCapabilities.ITEM_HANDLER && this.itemHandler != null && this.isAlive()) {
            return itemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps () {
        super.invalidateCaps();
        if (this.itemHandler != null) {
            LazyOptional<?> oldHandler = this.itemHandler;
            this.itemHandler = null;
            oldHandler.invalidate();
        }
    }

    @Override
    public boolean canBeCollidedWith () {
        return true;
    }

    @Override
    public boolean isPickable () {
        return true;
    }

    private Vec3 calcOffset ( double x, double y, double z){
        double rad = this.getYRot() * Math.PI / 180;

        double xOffset = this.position().x + (x * Math.cos(rad) - z * Math.sin(rad));
        double yOffset = this.position().y + y;
        double zOffset = this.position().z + (x * Math.sin(rad) + z * Math.cos(rad));

        return new Vec3(xOffset, yOffset, zOffset);
    }

    public void updateLastDrivePartialTick ( float partialTick){
        double xStep = this.position().x - this.lastPos.x;
        double zStep = this.position().z - this.lastPos.z;

        if (xStep * xStep + zStep * zStep != 0) {
            this.lastDrivePartialTick = partialTick;
        }
    }

    public void calcAnimStep () {
        double xStep = this.position().x - this.lastPos.x;
        double zStep = this.position().z - this.lastPos.z;
        float deg = (float) (Math.atan2(xStep, zStep) * 180 / Math.PI);

        if (xStep * xStep + zStep * zStep != 0) {
            this.driveTick = (this.driveTick + 1) % 30; // dragoon this 30 number is hardcoded I will come up with something better when I work on ME or something idk who cares it took like 2 seconds to figure out  // thanks tenks dont worry about it
            this.forwardMotion = (Math.round(mod(this.getYRot(), 360) + deg) == 180) ? -1 : 1;
        }
    }

    @Override
    protected boolean canAddPassenger(Entity entity) {
        return this.getPassengers().size() < 4;
    }

    @Override
    public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
        int i = this.getPassengers().indexOf(entity);
        switch (i) {
            case 0:
                entity.setPos(this.calcOffset(0.6, 0.3, -0.2));
                break;
            case 1:
                entity.setPos(this.calcOffset(-0.6, 0.3, -0.2));
                break;
            case 2:
                entity.setPos(this.calcOffset(0.6, 0.3, -2.0));
                break;
            case 3:
                entity.setPos(this.calcOffset(-0.6, 0.3, -2.0));
                break;
        }
    }
    // 0 = Driver

    // 0 | 1
    // 2 | 3

    @Override
    public boolean hurt(DamageSource damageSource, float damage) {
        if(!this.level().isClientSide && !this.isRemoved()) {
            this.markHurt();
            this.gameEvent(GameEvent.ENTITY_DAMAGE);
            float health = this.entityData.get(HEALTH) - damage;
            this.entityData.set(HEALTH, health);

            if(health < 0) {
                Containers.dropContents(this.level(), this, this.inventory);
                this.spawnAtLocation(ItemRegistry.CAR_SPAWN_EGG.get());
                this.kill();
            }
        }
        return true;
    }

    @Override
    public LivingEntity getControllingPassenger() {
        return (LivingEntity) this.getFirstPassenger();
    }

    @Override
    public void lerpTo ( double x, double y, double z, float yRot, float xRot, int lerpSteps, boolean p_19902_){
        this.targetX = x;
        this.targetY = y;
        this.targetZ = z;
        this.targetYRot = yRot;

        this.lerpSteps = lerpSteps;
    }

    @Override
    public float getStepHeight () {
        return 1;
    }

    private void handleInput (Input input){
        float forward = 0;
        float turn = 0;
        int turnMod = 1;

        if (input.up) {
            forward = SPEED;
        }

        if (input.down) {
            forward = -SPEED;
            turnMod = -1;
        }

        if (input.left) {
            turn = -TURN_SPEED * turnMod;
        }

        if (input.right) {
            turn = TURN_SPEED * turnMod;
        }

        this.currentRotation = this.targetRotation;
        if (Math.abs(this.targetRotation + turn) <= MAX_TURN) {
            this.targetRotation += turn;
        }

        if (forward != 0 && turn == 0) {
            this.targetRotation = 0;
        }

        float deg = this.currentRotation + this.getYRot();
        float rad = deg * (float) Math.PI / 180;

        if (forward != 0 && deg != this.getYRot()) {
            this.setYRot(deg);
        }

        this.setDeltaMovement(this.getDeltaMovement().add(-Math.sin(rad) * forward, 0, Math.cos(rad) * forward));
    }

    @Override
    public void tick() {
        super.tick();

        if(this.isControlledByLocalInstance()) {
            this.lerpSteps = 0;

            Vec3 velocity = this.getDeltaMovement();
            double dx = velocity.x * FRICTION;
            if(Math.abs(dx) < 0.001) dx = 0;

            double dz = velocity.z * FRICTION;
            if(Math.abs(dz) < 0.001) dz = 0;

            double dy = velocity.y + (this.onGround() ? 0 : -GRAVITY);

            this.setDeltaMovement(dx, dy, dz);

            if(this.getControllingPassenger() instanceof LocalPlayer player) {
                this.handleInput(player.input);
            }
            this.move(MoverType.SELF, this.getDeltaMovement());

        } else {
            this.setDeltaMovement(0, 0, 0);
        }

        if (this.lerpSteps > 0) {
            double x = this.getX() + (this.targetX - this.getX()) / this.lerpSteps;
            double y = this.getY() + (this.targetY - this.getY()) / this.lerpSteps;
            double z = this.getZ() + (this.targetZ - this.getZ()) / this.lerpSteps;

            float yRot = this.getYRot() + (this.targetYRot - this.getYRot()) / this.lerpSteps;

            this.setPos(x, y, z);
            this.setYRot(yRot);
            this.lerpSteps--;
        }

        this.calcAnimStep();
    }

    public float getFrontWheelRotation(float time) {
        return (this.currentRotation + (this.targetRotation - this.currentRotation) * time) * (float)Math.PI / 180;
    }

    @Override
    @NotNull
    public InteractionResult interact(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if(player.isShiftKeyDown()) {
            if(itemStack.getItem() instanceof DyeItem dyeItem) {
                this.level().playSound(player, this, SoundEvents.DYE_USE, SoundSource.PLAYERS, 1f, 1f);

                if (!this.level().isClientSide) {
                    this.entityData.set(TEXTURE, COLOR_MAP.get(dyeItem));
                    itemStack.shrink(1);
                }

                return InteractionResult.sidedSuccess(this.level().isClientSide);
            } else {
                if(!this.level().isClientSide) {
                    NetworkHooks.openScreen((ServerPlayer) player, new SimpleMenuProvider((containerId, inventory, serverPlayer) -> {
                        return ChestMenu.threeRows(containerId, inventory, this.inventory);
                    }, this.getDisplayName()));
                }
            }
        } else if(!this.level().isClientSide){
            return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
        }
        return super.interact(player, hand);
    }

    public ResourceLocation getTextureLocation () {
        return this.entityData.get(TEXTURE);
    }

    @Override
    protected void defineSynchedData () {
        this.entityData.define(TEXTURE, DEFAULT_TEXTURE);
        this.entityData.define(HEALTH, MAX_HEALTH);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        ResourceLocation texture = ResourceLocation.tryParse(compoundTag.getString("Texture"));
        this.entityData.set(TEXTURE, texture == null ? DEFAULT_TEXTURE : texture);
        this.entityData.set(HEALTH, compoundTag.getFloat("Health"));

        this.createInventory();
        ListTag listTag = compoundTag.getList("Items", Tag.TAG_COMPOUND);
        for(int i = 0; i < listTag.size(); i++) {
            CompoundTag tag = listTag.getCompound(i);
            int j = tag.getByte("Slot") & 255;
            if(j < this.inventory.getContainerSize()) {
                this.inventory.setItem(j, ItemStack.of(tag));
            }
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putString("Texture", this.entityData.get(TEXTURE).toString());
        compoundTag.putFloat("Health", this.entityData.get(HEALTH));

        ListTag listTag = new ListTag();
        for(int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack itemStack = this.inventory.getItem(i);
            if(!itemStack.isEmpty()) {
                CompoundTag tag = new CompoundTag();
                tag.putByte("Slot", (byte)i);
                itemStack.save(tag);
                listTag.add(tag);
            }
        }
        compoundTag.put("Items", listTag);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public void containerChanged (Container container){

    }
}