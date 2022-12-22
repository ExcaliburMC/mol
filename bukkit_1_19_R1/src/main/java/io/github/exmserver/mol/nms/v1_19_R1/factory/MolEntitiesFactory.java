package io.github.exmserver.mol.nms.v1_19_R1.factory;

import io.github.exmserver.mol.nms.v1_19_R1.entity.MolLivingEntity;

/**
 *
 */
@SuppressWarnings("unused")
public class MolEntitiesFactory extends io.github.exmserver.mol.factory.MolEntitiesFactory {
  @Override
  public io.github.exmserver.mol.entity.MolLivingEntity livingEntity(org.bukkit.entity.LivingEntity livingEntity) {
    return new MolLivingEntity(livingEntity);
  }
}
