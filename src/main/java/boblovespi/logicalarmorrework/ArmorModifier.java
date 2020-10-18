package boblovespi.logicalarmorrework;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenClass("logicalarmorrework.ArmorModifier")
public class ArmorModifier
{
    public static final List<ActionModifyArmor> actions = new ArrayList<>();

    @ZenMethod
    public static void changeArmorPoints(IItemStack item, int armorPoints)
    {
        ItemStack itemInternal = (ItemStack) item.getInternal();
        if (itemInternal.getItem() instanceof ItemArmor)
        {
            ActionModifyArmor action = new ActionModifyArmor();
            action.armor = (ItemArmor) itemInternal.getItem();
            action.newArmorPoints = armorPoints;
            actions.add(action);
        }
        else
        {
            CraftTweakerAPI.logWarning("tried to modify non-armor: " + itemInternal.getItem().getRegistryName());
        }
    }

    @ZenMethod
    public static void changeToughness(IItemStack item, float toughness)
    {
        ItemStack itemInternal = (ItemStack) item.getInternal();
        if (itemInternal.getItem() instanceof ItemArmor)
        {
            ActionModifyArmor action = new ActionModifyArmor();
            action.armor = (ItemArmor) itemInternal.getItem();
            action.newToughness = toughness;
            actions.add(action);
        }
        else
        {
            CraftTweakerAPI.logWarning("tried to modify non-armor: " + itemInternal.getItem().getRegistryName());
        }
    }

    @ZenMethod
    public static void changeArmor(IItemStack item, int armorPoints, float toughness)
    {
        ItemStack itemInternal = (ItemStack) item.getInternal();
        if (itemInternal.getItem() instanceof ItemArmor)
        {
            ActionModifyArmor action = new ActionModifyArmor();
            action.armor = (ItemArmor) itemInternal.getItem();
            action.newArmorPoints = armorPoints;
            action.newToughness = toughness;
            actions.add(action);
        }
        else
        {
            CraftTweakerAPI.logWarning("tried to modify non-armor: " + itemInternal.getItem().getRegistryName());
        }
    }

    public static class ActionModifyArmor implements IAction
    {
        ItemArmor armor;
        int newArmorPoints = -1;
        float newToughness = -1;

        @Override
        public void apply()
        {
            if (newArmorPoints >= 0)
                armor.damageReduceAmount = newArmorPoints;
            if (newToughness >= 0)
                armor.toughness = newToughness;
        }

        @Override
        public String describe()
        {
            return "modifying " + armor.getRegistryName() + " to have " + String.format("%d armor points and %f toughness", newArmorPoints, newToughness);
        }
    }
}
