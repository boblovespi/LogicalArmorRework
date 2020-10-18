package boblovespi.logicalarmorrework;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ZenRegister
@ZenClass("logicalarmorrework.WeaponModifier")
public class WeaponModifier
{
    public static final List<ActionModifyWeapon> actions = new ArrayList<>();
    public static final Map<ItemSword, Double> attackSpeedMap = new HashMap<>();

    @ZenMethod
    public static void changeAttackDamage(IItemStack item, float damage)
    {
        ItemStack itemInternal = (ItemStack) item.getInternal();
        if (itemInternal.getItem() instanceof ItemSword)
        {
            ActionModifyWeapon action = new ActionModifyWeapon();
            action.weapon = (ItemSword) itemInternal.getItem();
            action.newAttackDamage = damage;
            actions.add(action);
        } else if (itemInternal.getItem() instanceof ItemTool)
        {
            ActionModifyWeapon action = new ActionModifyWeapon();
            action.tool = (ItemTool) itemInternal.getItem();
            action.newAttackDamage = damage;
            actions.add(action);
        } else
        {
            CraftTweakerAPI.logWarning("tried to modify non-weapon: " + itemInternal.getItem().getRegistryName());
        }
    }

    @ZenMethod
    public static void changeAttackSpeed(IItemStack item, float attackSpeed)
    {
        ItemStack itemInternal = (ItemStack) item.getInternal();
        if (itemInternal.getItem() instanceof ItemSword)
        {
            ActionModifyWeapon action = new ActionModifyWeapon();
            action.weapon = (ItemSword) itemInternal.getItem();
            action.newAttackSpeed = attackSpeed;
            actions.add(action);
        } else if (itemInternal.getItem() instanceof ItemTool)
        {
            ActionModifyWeapon action = new ActionModifyWeapon();
            action.tool = (ItemTool) itemInternal.getItem();
            action.newAttackSpeed = attackSpeed;
            actions.add(action);
        } else
        {
            CraftTweakerAPI.logWarning("tried to modify non-weapon: " + itemInternal.getItem().getRegistryName());
        }
    }

    @ZenMethod
    public static void changeWeapon(IItemStack item, float damage, float attackSpeed)
    {
        ItemStack itemInternal = (ItemStack) item.getInternal();
        if (itemInternal.getItem() instanceof ItemSword)
        {
            ActionModifyWeapon action = new ActionModifyWeapon();
            action.weapon = (ItemSword) itemInternal.getItem();
            action.newAttackDamage = damage;
            action.newAttackSpeed = attackSpeed;
            actions.add(action);
        } else if (itemInternal.getItem() instanceof ItemTool)
        {
            ActionModifyWeapon action = new ActionModifyWeapon();
            action.tool = (ItemTool) itemInternal.getItem();
            action.newAttackDamage = damage;
            action.newAttackSpeed = attackSpeed;
            actions.add(action);
        } else
        {
            CraftTweakerAPI.logWarning("tried to modify non-weapon: " + itemInternal.getItem().getRegistryName());
        }
    }

    public static class ActionModifyWeapon implements IAction
    {
        ItemSword weapon = null;
        ItemTool tool = null;
        float newAttackDamage = -1;
        float newAttackSpeed = -10;

        @Override
        public void apply()
        {
            if (weapon != null)
            {
                if (newAttackDamage >= 0)
                    weapon.attackDamage = newAttackDamage;
                if (newAttackSpeed > -4)
                    attackSpeedMap.put(weapon, (double) newAttackSpeed);
            }
            if (tool != null)
            {
                if (newAttackDamage >= 0)
                    tool.attackDamage = newAttackDamage;
                if (newAttackSpeed > -4)
                    tool.attackSpeed = newAttackSpeed;
            }
        }

        @Override
        public String describe()
        {
            if (weapon != null)
                return "modifying " + weapon.getRegistryName() + " to have " + String.format("%f damage and %f attack speed", newAttackDamage, newAttackSpeed);
            return "modifying " + tool.getRegistryName() + " to have " + String.format("%f damage and %f attack speed", newAttackDamage, newAttackSpeed);
        }
    }
}
