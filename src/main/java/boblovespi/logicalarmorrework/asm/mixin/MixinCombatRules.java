package boblovespi.logicalarmorrework.asm.mixin;

import boblovespi.logicalarmorrework.Mod;
import net.minecraft.util.CombatRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CombatRules.class)
public class MixinCombatRules
{
    @Inject(method = "getDamageAfterAbsorb(FFF)F", at = @At("HEAD"), cancellable = true)
    private static void getDamageAfterAbsorb(float damage, float ar, float to, CallbackInfoReturnable<Float> cr)
    {
        float c = 6;
        float a = 50;
        float m = 5;
        float plus5 = ar + 5;

        float max = Math.max(ar / plus5 - damage / (c * to + a), ar / (m * plus5));
        // Mod.logger.info("dmg: {}, armor: {}, toughness: {}, reduction: {}", damage, ar, to, max);
        cr.setReturnValue(damage * (1 - max));
    }
}
