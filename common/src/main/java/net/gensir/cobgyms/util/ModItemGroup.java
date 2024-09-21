package net.gensir.cobgyms.util;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.gensir.cobgyms.CobGyms;
import net.gensir.cobgyms.registry.ModItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.gensir.cobgyms.CobGyms.MOD_ID;

public class ModItemGroup {

    static Registrar<ItemGroup> tabs = CobGyms.MANAGER.get().get(RegistryKeys.ITEM_GROUP);
    public static RegistrySupplier<ItemGroup> COBGYMS_TAB = tabs.register(new Identifier(MOD_ID, "cobgyms_tab"),() -> CreativeTabRegistry.create(
            Text.translatable("cobgyms.lang.item_group"),
            () -> new ItemStack(ModItemRegistry.GYM_KEY.get())
    ));

    public static void initialize() {
    }
}
