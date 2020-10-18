package boblovespi.logicalarmorrework.asm.mixin;

import boblovespi.logicalarmorrework.WeaponModifier;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemSword;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.minecraft.item.Item.ATTACK_SPEED_MODIFIER;

@Mixin(ItemSword.class)
public class MixinItemSword
{
    @Redirect(method = "getItemAttributeModifiers",
            at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Multimap;put(Ljava/lang/Object;Ljava/lang/Object;)Z", ordinal = 1))
    private boolean put(Multimap multimap, Object name, Object modifier)
    {
        if (WeaponModifier.attackSpeedMap.containsKey(this))
            return multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", WeaponModifier.attackSpeedMap.get(this), 0));
        return multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
    }
}
