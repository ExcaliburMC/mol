package io.github.exmserver.mol.nms.v1_19_R1.entity;

import io.github.exmserver.mol.nms.v1_19_R1.util.DamageConverter;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftLivingEntity;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

/**
 *
 */
@SuppressWarnings("unused")
public class MolLivingEntity extends CraftLivingEntity implements io.github.exmserver.mol.entity.MolLivingEntity {
  CraftServer craftServer;
  LivingEntity livingEntity;

  /**
   * @param server CraftServer instance
   * @param entity NMS LivingEntity
   */
  public MolLivingEntity(CraftServer server, LivingEntity entity) {
    super(server, entity);
    this.craftServer = server;
    this.livingEntity = entity;
  }

  /**
   * @param livingEntity Bukkit LivingEntity
   */
  public MolLivingEntity(org.bukkit.entity.LivingEntity livingEntity) {
    super((CraftServer) livingEntity.getServer(), ((CraftLivingEntity) livingEntity).getHandle());
    this.craftServer = (CraftServer) livingEntity.getServer();
    this.livingEntity = ((CraftLivingEntity) livingEntity).getHandle();
  }

  public void hurt(DamageCause cause, float amount) {
    Entity entity = this.getHandle();
    DamageSource source = DamageConverter.toDamageSource(cause, null, entity);
    entity.hurt(source, amount);
  }
}
