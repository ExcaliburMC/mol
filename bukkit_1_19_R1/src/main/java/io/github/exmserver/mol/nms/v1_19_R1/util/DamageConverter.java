package io.github.exmserver.mol.nms.v1_19_R1.util;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.Explosion;
import org.bukkit.craftbukkit.v1_19_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Lazy wrapper of Bukkit's {@link DamageCause DamageCause} enum.
 * <p>
 * This will provide methods that converts Bukkit's {@link DamageCause DamageCause} to NMS' {@link DamageSource DamageSource} or vice versa.
 * <p>
 * This wrapper will be deprecated when <a href="https://github.com/PaperMC/Paper/pull/8058">PaperMC/Paper#8058</a> is merged.
 * @see <a href="https://github.com/PaperMC/Paper/pull/8058">PaperMC/Paper#8058</a>
 */
@SuppressWarnings("unused")
public final class DamageConverter {
  /**
   * Method to convert Bukkit's {@link DamageCause DamageCause} to NMS' {@link DamageSource DamageSource}.
   * <p>
   * This method won't work with DamageSources 100%.
   * <br>
   * For example, Bukkit/CraftBukkit does not distinguish between {@link DamageSource#CACTUS} and {@link DamageSource#SWEET_BERRY_BUSH} and treat them as {@link DamageCause#CONTACT DamageCause#CONTACT}.
   * <br>
   * Another example can be {@link DamageCause#SUICIDE DamageCause#SUICIDE}. It is purely Bukkit/CraftBukkit's implementation detail, and has no counterpart within {@link DamageSource}
   *
   * @param cause {@link DamageCause DamageCause} to be converted to {@link DamageSource DamageSource}
   * @param attacker Entity which damaged other entity
   * @param damaged Entity which is damaged
   * @return {@link DamageSource DamageSource}
   */
  public static DamageSource toDamageSource(DamageCause cause, @Nullable Entity attacker, Entity damaged) {
    switch (cause) {
      case CONTACT -> {
        // This is for convenience
        CraftEventFactory.blockDamage = damaged.getBukkitEntity().getWorld().getBlockAt(0, 0, 0);
        return DamageSource.CACTUS;
      }
      case ENTITY_ATTACK -> {
        if (attacker instanceof Player) {
          return DamageSource.playerAttack((Player) attacker);
        }
        if (attacker instanceof LivingEntity) {
          return DamageSource.mobAttack((LivingEntity) attacker);
        }
        return DamageSource.GENERIC;
      }
      case ENTITY_SWEEP_ATTACK -> {
        if (attacker instanceof Player) {
          return DamageSource.playerAttack((Player) attacker).sweep();
        }
        if (attacker instanceof LivingEntity) {
          return DamageSource.mobAttack((LivingEntity) attacker).sweep();
        }
        return DamageSource.GENERIC;
      }
      case PROJECTILE -> {
        if (attacker instanceof Projectile projectile) {
          EntityType<?> entityType = projectile.getType();
          // switch is not available in here
          if (entityType == EntityType.TRIDENT) {
            return DamageSource.trident(attacker, projectile);
          } else if (entityType == EntityType.SNOWBALL || entityType == EntityType.EGG ||
            entityType == EntityType.ENDER_PEARL || entityType == EntityType.POTION) {
            return DamageSource.thrown(projectile, attacker);
          } else if (entityType == EntityType.FIREWORK_ROCKET) {
            return DamageSource.fireworks((FireworkRocketEntity) projectile, attacker);
          } else if (entityType == EntityType.WITHER_SKULL) {
            return DamageSource.witherSkull((WitherSkull) projectile, attacker);
          }
          return DamageSource.thrown(projectile, attacker);
        }
        return DamageSource.GENERIC;
      }
      case SUFFOCATION -> {
        return DamageSource.IN_WALL;
      }
      case FALL -> {
        return DamageSource.FALL;
      }
      case FIRE -> {
        return DamageSource.IN_FIRE;
      }
      case FIRE_TICK -> {
        return DamageSource.ON_FIRE;
      }
      case MELTING -> {
        return CraftEventFactory.MELTING;
      }
      case LAVA -> {
        return DamageSource.LAVA;
      }
      case DROWNING -> {
        return DamageSource.DROWN;
      }
      case BLOCK_EXPLOSION -> {
        // This is for convenience
        return DamageSource.explosion((Explosion) null);
      }
      case ENTITY_EXPLOSION -> {
        if (attacker == null) {
          return DamageSource.GENERIC;
        } else {
          return DamageSource.explosion((LivingEntity) attacker);
        }
      }
      case VOID, SUICIDE -> {
        return DamageSource.OUT_OF_WORLD;
      }
      case LIGHTNING -> {
        return DamageSource.LIGHTNING_BOLT;
      }
      case STARVATION -> {
        return DamageSource.STARVE;
      }
      case POISON -> {
        return CraftEventFactory.POISON;
      }
      case MAGIC -> {
        return DamageSource.MAGIC;
      }
      case WITHER -> {
        return DamageSource.WITHER;
      }
      case FALLING_BLOCK -> {
        return DamageSource.FALLING_BLOCK;
      }
      case THORNS -> {
        if (attacker == null) {
          return DamageSource.GENERIC;
        } else {
          return DamageSource.thorns(attacker);
        }
      }
      case DRAGON_BREATH -> {
        return DamageSource.DRAGON_BREATH;
      }
      case CUSTOM -> {
        return DamageSource.GENERIC;
      }
      case FLY_INTO_WALL -> {
        return DamageSource.FLY_INTO_WALL;
      }
      case HOT_FLOOR -> {
        return DamageSource.HOT_FLOOR;
      }
      case CRAMMING -> {
        return DamageSource.CRAMMING;
      }
      case DRYOUT -> {
        return DamageSource.DRY_OUT;
      }
      case FREEZE -> {
        return DamageSource.FREEZE;
      }
      case SONIC_BOOM -> {
        if (attacker == null) {
          return DamageSource.GENERIC;
        } else {
          return DamageSource.sonicBoom(attacker);
        }
      }
    }
    return DamageSource.GENERIC;
  }

  /**
   * Method to convert NMS' {@link DamageSource DamageSource} to Bukkit's {@link DamageCause DamageCause}.
   * <p>
   * This method won't work with DamageCause 100%.
   * @see DamageConverter#toDamageSource(DamageCause, Entity, Entity) toDamageSource
   *
   * @param source {@link DamageSource DamageSource} to be converted to {@link DamageCause DamageCause}
   * @param attacker Entity which damaged other entity
   * @param attacked Entity which is damaged
   * @return {@link DamageCause DamageCause}
   */
  public static DamageCause toDamageCause(DamageSource source, @Nullable Entity attacker, Entity attacked) {
    // switch is not available in here
    if (source == DamageSource.ANVIL || source == DamageSource.FALLING_BLOCK ||
    source == DamageSource.FALLING_STALACTITE) {
      return DamageCause.FALLING_BLOCK;
    } else if (source == DamageSource.CACTUS || source == DamageSource.SWEET_BERRY_BUSH) {
      return DamageCause.CONTACT;
    } else if (source == DamageSource.CRAMMING) {
      return DamageCause.CRAMMING;
    } else if (source == DamageSource.DRAGON_BREATH) {
      return DamageCause.DRAGON_BREATH;
    } else if (source == DamageSource.DROWN) {
      return DamageCause.DROWNING;
    } else if (source == DamageSource.DRY_OUT) {
      return DamageCause.DRYOUT;
    } else if (source == DamageSource.FALL || source == DamageSource.STALAGMITE) {
      return DamageCause.FALL;
    } else if (source == DamageSource.FLY_INTO_WALL) {
      return DamageCause.FLY_INTO_WALL;
    } else if (source == DamageSource.FREEZE) {
      return DamageCause.FREEZE;
    } else if (source == DamageSource.GENERIC) {
      return DamageCause.CUSTOM;
    } else if (source == DamageSource.HOT_FLOOR) {
      return DamageCause.HOT_FLOOR;
    } else if (source == DamageSource.IN_FIRE) {
      return DamageCause.FIRE;
    } else if (source == DamageSource.IN_WALL) {
      return DamageCause.SUFFOCATION;
    } else if (source == DamageSource.LAVA) {
      return DamageCause.LAVA;
    } else if (source == DamageSource.LIGHTNING_BOLT) {
      return DamageCause.LIGHTNING;
    } else if (source == DamageSource.MAGIC || ((attacker != null) &&
      (Objects.equals(source, DamageSource.indirectMagic(attacker, attacked))))) {
      return DamageCause.MAGIC;
    } else if (source == CraftEventFactory.MELTING) {
      return DamageCause.MELTING;
    } else if (source == DamageSource.ON_FIRE) {
      return DamageCause.FIRE_TICK;
    } else if (source == DamageSource.OUT_OF_WORLD) {
      return DamageCause.VOID;
    } else if (source == CraftEventFactory.POISON) {
      return DamageCause.POISON;
    } else if ((attacker != null) && (source == DamageSource.sonicBoom(attacker))) {
      return DamageCause.SONIC_BOOM;
    } else if (source == DamageSource.STARVE) {
      return DamageCause.STARVATION;
    } else if (source == DamageSource.WITHER) {
      return DamageCause.WITHER;
    } else if (source == DamageSource.badRespawnPointExplosion()) {
      return DamageCause.BLOCK_EXPLOSION;
    } else {
      // do entity attack thing
      if (attacker instanceof LivingEntity) {
        if (Objects.equals(source, DamageSource.sting((LivingEntity) attacker)) ||
          Objects.equals(source, DamageSource.mobAttack((LivingEntity) attacker)) ||
          Objects.equals(source, DamageSource.indirectMobAttack(attacker, (LivingEntity) attacked)) ||
          Objects.equals(source, DamageSource.playerAttack((Player) attacker))) {
          return DamageCause.ENTITY_ATTACK;
        } else if (source == DamageSource.explosion((LivingEntity) attacker)) {
          return DamageCause.ENTITY_EXPLOSION;
        }
      }
      // do projectile thing
      if (attacker instanceof FireworkRocketEntity) {
        if (source == DamageSource.fireworks((FireworkRocketEntity) attacker, attacked)) {
          return DamageCause.PROJECTILE;
        } else {
          return DamageCause.CUSTOM;
        }
      } else if (attacker instanceof Fireball) {
        if (source == DamageSource.fireball((Fireball) attacker, attacked)) {
          return DamageCause.ENTITY_EXPLOSION;
        } else {
          return DamageCause.CUSTOM;
        }
      } else if (attacker instanceof WitherSkull) {
        if (source == DamageSource.witherSkull((WitherSkull) attacker, attacker)) {
          return DamageCause.ENTITY_EXPLOSION;
        }
      } else if (attacker instanceof Projectile) {
        if (source == DamageSource.arrow((AbstractArrow) attacker, attacked) ||
          source == DamageSource.trident(attacker, attacked) ||
          source == DamageSource.thrown(attacker, attacked)) {
          return DamageCause.PROJECTILE;
        }
        return DamageCause.CUSTOM;
      }
      if ((attacker != null) && (source == DamageSource.thorns(attacker))) {
        return DamageCause.THORNS;
      }
      throw new IllegalStateException(String.format("mol: Unhandled damage of %s by %s from %s", attacked, (attacker == null ? "null" : attacker), source.getMsgId()));
    }
  }
}
