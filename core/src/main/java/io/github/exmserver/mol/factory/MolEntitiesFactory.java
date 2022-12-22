package io.github.exmserver.mol.factory;

import io.github.exmserver.mol.entity.MolLivingEntity;
import io.github.exmserver.mol.util.GetInformation;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.InvocationTargetException;

/**
 * Returns mol variation of (sub)interface of Entity
 */
@SuppressWarnings("unused")
public abstract class MolEntitiesFactory {

  /**
   * Get {@link MolLivingEntity MolLivingEntity} version of {@link LivingEntity}
   *
   * @param livingEntity {@link LivingEntity}
   * @return {@link MolLivingEntity MolLivingEntity}
   */
  public abstract MolLivingEntity livingEntity(LivingEntity livingEntity);

  /**
   * Initialize {@code MolEntitiesFactory}. If this library is used with unsupported Minecraft version, since library
   * cannot be initialized, server will automatically shutdown.
   * <p>
   * This must be done before using {@code MolEntitiesFactory}'s methods.
   * <br>
   * For instance, use it like: {@code MolEntitiesFactory.init().livingEntity(livingEntity);}
   *
   * @return Initialized {@code MolEntitiesFactory}
   */
  public static MolEntitiesFactory init() {
    Class<?> molEntitiesFactoryClass;

    try {
      molEntitiesFactoryClass = Class.forName(GetInformation.libraryPackage() + ".nms." + GetInformation.serverVersion() + ".factory.MolEntitiesFactory");
    } catch (ClassNotFoundException e) {
      Bukkit.getLogger().severe("This Bukkit API version is not supported by mol library!");
      Bukkit.getLogger().severe("Shutting down server...");
      e.printStackTrace();
      Bukkit.shutdown();
      return null;
    }
    try {
      if (MolEntitiesFactory.class.isAssignableFrom(molEntitiesFactoryClass)) {
        return (MolEntitiesFactory) molEntitiesFactoryClass.getConstructor().newInstance();
      }
    } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
      Bukkit.getLogger().severe("mol library failed to reflect NMS counterpart");
      Bukkit.getLogger().severe("Shutting down server...");
      e.printStackTrace();
      Bukkit.shutdown();
      return null;
    }
    Bukkit.getLogger().severe("mol library failed to reflect NMS counterpart");
    Bukkit.getLogger().severe("Shutting down server...");
    Bukkit.shutdown();
    return null;
  }
}
