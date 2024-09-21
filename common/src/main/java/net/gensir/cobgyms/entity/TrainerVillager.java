package net.gensir.cobgyms.entity;

import com.google.common.base.Suppliers;
import com.selfdot.cobblemontrainers.trainer.Trainer;
import com.selfdot.cobblemontrainers.util.PokemonUtility;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class TrainerVillager extends VillagerEntity {
    public static final Supplier<EntityType<TrainerVillager>> TYPE = Suppliers.memoize(() -> EntityType.Builder.create(TrainerVillager::new, SpawnGroup.MISC).build("trainer_villager"));
    private final World entityWorld;
    private Trainer trainer = null;

    public TrainerVillager(EntityType<? extends VillagerEntity> entityType, World world) {
        super(entityType, world);
        this.entityWorld = world;
        this.setPersistent();
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }


    @Override
    public void tickMovement() {
        super.tickMovement();
        this.setVelocity(0, 0, 0);
    }

    @Override
    public boolean isSilent() {
        return true;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!this.entityWorld.isClient) {
            if (this.trainer != null){
                if (this.trainer.getBattleTeam().isEmpty()) {
                    player.sendMessage(Text.translatable("cobgyms.lang.npc.no_pokemon", trainer.getName()));
                }
                PokemonUtility.startTrainerBattle(
                        (ServerPlayerEntity) player, trainer,
                        this
                );
            }

            return ActionResult.SUCCESS;
        }

        return super.interactMob(player, hand);
    }

    public void setTrainer(Trainer trainer){
        this.trainer = trainer;

    }

    @Override
    public int getMaxLookYawChange() {
        return 360;
    }

    @Override
    public boolean isPushable() {
        return false;
    }


}
