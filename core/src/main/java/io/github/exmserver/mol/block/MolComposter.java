package io.github.exmserver.mol.block;

import org.bukkit.inventory.ItemStack;

/**
 * Provides methods about Composter
 */
@SuppressWarnings("unused")
public interface MolComposter {
  /**
   * Adds {@code ItemStack} to Composter as compost.
   *
   * @param itemStack ItemStack to add as compost
   * @param chance Chance of the ItemStack to increase Composter's level
   */
  void addCompost(ItemStack itemStack, float chance);
}
