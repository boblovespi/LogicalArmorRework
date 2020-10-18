package boblovespi.logicalarmorrework;

import crafttweaker.CraftTweakerAPI;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@net.minecraftforge.fml.common.Mod(modid = Mod.MODID, name = Mod.NAME, version = Mod.VERSION)
public class Mod
{
    public static final String MODID = "logicalarmorrework";
    public static final String NAME = "Logical Armor Rework";
    public static final String VERSION = "1.0";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // logger.info("hi");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        logger.info("modifying armors!");
        ArmorModifier.actions.forEach(CraftTweakerAPI::apply);
        logger.info("modifying weapons!");
        WeaponModifier.actions.forEach(CraftTweakerAPI::apply);
    }
}
