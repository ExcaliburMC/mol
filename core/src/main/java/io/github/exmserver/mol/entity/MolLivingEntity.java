package io.github.exmserver.mol.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.jetbrains.annotations.ApiStatus;

/**
 * mol variant of LivingEntity. You can access some NMS methods without implementing NMS.
 * <p>
 * Do not directly use this interface. Use this interface only with
 * {@code MolEntitiesFactory.init().livingEntity(livingEntity)} or you'll get error!
 */
@SuppressWarnings("unused")
public interface MolLivingEntity extends LivingEntity {
  /**
   * Give damage to the entity with specific {@link org.bukkit.event.entity.EntityDamageEvent.DamageCause DamageCause}
   * and damage value.
   * <p>
   * This method will be deprecated when <a href="https://github.com/PaperMC/Paper/pull/8058">PaperMC/Paper#8058</a>
   * is merged.
   *
   * @param cause {@code DamageCause} of the damage
   * @param amount amount of damage
   * @see <a href="https://github.com/PaperMC/Paper/pull/8058">PaperMC/Paper#8058</a>
   */
  @ApiStatus.Experimental
  void hurt(DamageCause cause, float amount);
}
