package io.github.exmserver.mol.nms.v1_19_R1.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 *
 */
@SuppressWarnings("unused")
public class MolComposter implements io.github.exmserver.mol.block.MolComposter {
  public void addCompost(ItemStack itemStack, float chance) {
    Item item = CraftItemStack.asNMSCopy(itemStack).getItem();
    ComposterBlock.COMPOSTABLES.put(item, chance);
  }
}
