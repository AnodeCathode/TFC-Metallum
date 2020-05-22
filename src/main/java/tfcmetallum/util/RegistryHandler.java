package tfcmetallum.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import net.dries007.tfc.api.capability.forge.CapabilityForgeable;
import net.dries007.tfc.api.capability.forge.IForgeable;
import net.dries007.tfc.api.capability.forge.IForgeableMeasurableMetal;
import net.dries007.tfc.api.recipes.AlloyRecipe;
import net.dries007.tfc.api.recipes.BlastFurnaceRecipe;
import net.dries007.tfc.api.recipes.BloomeryRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.registries.TFCRegistryEvent;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.ItemsTFC;
import net.dries007.tfc.objects.items.metal.ItemMetal;
import net.dries007.tfc.util.fuel.FuelManager;
import tfcmetallum.ConfigTFCM;
import tfcmetallum.TFCMetallum;
import tfcmetallum.objects.ArmorMaterialsTFCM;
import tfcmetallum.objects.ToolMaterialsTFCM;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;
import static net.dries007.tfc.api.types.Metal.ItemType.INGOT;
import static net.dries007.tfc.types.DefaultMetals.*;
import static net.dries007.tfc.util.forge.ForgeRule.*;

@SuppressWarnings({"unused", "WeakerAccess"})
@Mod.EventBusSubscriber(modid = TFCMetallum.MODID)
public final class RegistryHandler
{
    //Metals
    public static final ResourceLocation ALUMINUM = new ResourceLocation(MOD_ID, "aluminum");
    public static final ResourceLocation ALUMINUM_BRASS = new ResourceLocation(MOD_ID, "aluminum_brass");
    public static final ResourceLocation ANTIMONY = new ResourceLocation(MOD_ID, "antimony");
    public static final ResourceLocation ARDITE = new ResourceLocation(MOD_ID, "ardite");
    public static final ResourceLocation COBALT = new ResourceLocation(MOD_ID, "cobalt");
    public static final ResourceLocation CONSTANTAN = new ResourceLocation(MOD_ID, "constantan");
    public static final ResourceLocation ELECTRUM = new ResourceLocation(MOD_ID, "electrum");
    public static final ResourceLocation INVAR = new ResourceLocation(MOD_ID, "invar");
    public static final ResourceLocation MANYULLYN = new ResourceLocation(MOD_ID, "manyullyn");
    public static final ResourceLocation MITHRIL = new ResourceLocation(MOD_ID, "mithril");
    public static final ResourceLocation OSMIUM = new ResourceLocation(MOD_ID, "osmium");
    public static final ResourceLocation TITANIUM = new ResourceLocation(MOD_ID, "titanium");
    public static final ResourceLocation TUNGSTEN = new ResourceLocation(MOD_ID, "tungsten");
    public static final ResourceLocation TUNGSTEN_STEEL = new ResourceLocation(MOD_ID, "tungsten_steel");
    public static final ResourceLocation NICKEL_SILVER = new ResourceLocation(MOD_ID, "nickel_silver"); // Copper + zinc + nickel
    public static final ResourceLocation RED_ALLOY = new ResourceLocation(MOD_ID, "red_alloy"); // Copper + redstone (although not obtainable with just TFC + metallum

    //Ores
    public static final ResourceLocation NATIVE_ARDITE = new ResourceLocation(MOD_ID, "native_ardite");
    public static final ResourceLocation NATIVE_OSMIUM = new ResourceLocation(MOD_ID, "native_osmium");
    public static final ResourceLocation BAUXITE = new ResourceLocation(MOD_ID, "bauxite"); // aluminum / titanium
    public static final ResourceLocation WOLFRAMITE = new ResourceLocation(MOD_ID, "wolframite"); // tungsten
    public static final ResourceLocation COBALTITE = new ResourceLocation(MOD_ID, "cobaltite"); // cobalt
    public static final ResourceLocation STIBNITE = new ResourceLocation(MOD_ID, "stibnite"); // antimony
    public static final ResourceLocation RUTILE = new ResourceLocation(MOD_ID, "rutile"); // titanium

    //Ore without TFC-M metals
    public static final ResourceLocation THORIANITE = new ResourceLocation(MOD_ID, "thorianite"); // thorium
    public static final ResourceLocation CHROMITE = new ResourceLocation(MOD_ID, "chromite"); // chrome
    public static final ResourceLocation PYROLUSITE = new ResourceLocation(MOD_ID, "pyrolusite"); // manganese
    public static final ResourceLocation MAGNESITE = new ResourceLocation(MOD_ID, "magnesite"); //magnesium
    public static final ResourceLocation BORON = new ResourceLocation(MOD_ID, "boron"); // boron
    public static final ResourceLocation SPODUMENE = new ResourceLocation(MOD_ID, "spodumene"); // lithium


    @SubscribeEvent
    public static void onPreRegisterMetal(TFCRegistryEvent.RegisterPreBlock<Metal> event)
    {
        IForgeRegistry<Metal> r = event.getRegistry();
        r.register(new Metal(ANTIMONY, Metal.Tier.TIER_I, true, 0.25f, 630, 0xFFE7E7F5, null, null));
        r.register(new Metal(CONSTANTAN, Metal.Tier.TIER_II, true, 0.5f, 1200, 0xFFD28874, null, null));
        r.register(new Metal(ELECTRUM, Metal.Tier.TIER_II, true, 0.5f, 1200, 0xFFDFB950, null, null));
        r.register(new Metal(NICKEL_SILVER, Metal.Tier.TIER_II, true, 0.35f, 1450, 0xFFA4A4A5, ToolMaterialsTFCM.NICKEL_SILVER, ArmorMaterialsTFCM.NICKEL_SILVER));
        r.register(new Metal(RED_ALLOY, Metal.Tier.TIER_II, true, 0.35f, 1080, 0xFFDA6E6E, null, null));
        r.register(new Metal(MITHRIL, Metal.Tier.TIER_II, true, 0.35f, 940, 0xFF8ADAF6, ToolMaterialsTFCM.MITHRIL, ArmorMaterialsTFCM.MITHRIL));
        r.register(new Metal(INVAR, Metal.Tier.TIER_III, true, 0.35f, 1450, 0xFF40444A, ToolMaterialsTFCM.INVAR, ArmorMaterialsTFCM.INVAR));
        r.register(new Metal(ALUMINUM, Metal.Tier.TIER_III, true, 0.3f, 660, 0xFFD9FBFC, ToolMaterialsTFCM.ALUMINUM, ArmorMaterialsTFCM.ALUMINUM));
        r.register(new Metal(ALUMINUM_BRASS, Metal.Tier.TIER_III, true, 0.3f, 630, 0xFFDCDABE, null, null));
        r.register(new Metal(ARDITE, Metal.Tier.TIER_III, true, 0.3f, 1050, 0xFF40444A, null, null));
        r.register(new Metal(COBALT, Metal.Tier.TIER_III, true, 0.3f, 1495, 0xFF6CA6E5, ToolMaterialsTFCM.COBALT, ArmorMaterialsTFCM.COBALT));
        r.register(new Metal(MANYULLYN, Metal.Tier.TIER_IV, true, 0.3f, 1550, 0xFF40444A, ToolMaterialsTFCM.MANYULLYN, ArmorMaterialsTFCM.MANYULLYN));
        r.register(new Metal(OSMIUM, Metal.Tier.TIER_VI, true, 0.35f, 3025, 0xFFB8D8DE, ToolMaterialsTFCM.OSMIUM, ArmorMaterialsTFCM.OSMIUM));
        r.register(new Metal(TITANIUM, Metal.Tier.TIER_VI, true, 0.3f, 1700, 0xFFC2C4CC, ToolMaterialsTFCM.TITANIUM, ArmorMaterialsTFCM.TITANIUM));
        r.register(new Metal(TUNGSTEN, Metal.Tier.TIER_VI, true, 0.2f, 3400, 0xFF40444A, ToolMaterialsTFCM.TUNGSTEN, ArmorMaterialsTFCM.TUNGSTEN));
        r.register(new Metal(TUNGSTEN_STEEL, Metal.Tier.TIER_VI, true, 0.2f, 3695, 0xFF565F6E, ToolMaterialsTFCM.TUNGSTEN_STEEL, ArmorMaterialsTFCM.TUNGSTEN_STEEL));
    }


    @SubscribeEvent
    public static void onPreRegisterOre(TFCRegistryEvent.RegisterPreBlock<Ore> event)
    {
        IForgeRegistry<Ore> r = event.getRegistry();
        //  Ores which *could* be melted directly if it's temperature is met
        r.register(new Ore(STIBNITE, ANTIMONY, true));

        // Ores which we add tools, armor and textures inside TFC realm, but can't be melted directly (processing by other mods required)
        r.register(new Ore(NATIVE_ARDITE, ARDITE, false));
        r.register(new Ore(NATIVE_OSMIUM, OSMIUM, false));
        r.register(new Ore(BAUXITE, ALUMINUM, false));
        r.register(new Ore(WOLFRAMITE, TUNGSTEN, false));
        r.register(new Ore(COBALTITE, COBALT, false));
        r.register(new Ore(RUTILE, TITANIUM, false));

        // Ores without metals registered inside TFC
        r.register(new Ore(THORIANITE));
        r.register(new Ore(CHROMITE));
        r.register(new Ore(PYROLUSITE));
        r.register(new Ore(MAGNESITE));
        r.register(new Ore(BORON));
        r.register(new Ore(SPODUMENE));
    }

    @SubscribeEvent
    public static void onRegisterAlloyRecipe(RegistryEvent.Register<AlloyRecipe> event)
    {
        IForgeRegistry<AlloyRecipe> r = event.getRegistry();
        r.register(new AlloyRecipe.Builder(CONSTANTAN).add(COPPER, 0.4, 0.6).add(NICKEL, 0.4, 0.6).build());
        r.register(new AlloyRecipe.Builder(ELECTRUM).add(GOLD, 0.4, 0.6).add(SILVER, 0.4, 0.6).build());
        r.register(new AlloyRecipe.Builder(MITHRIL).add(COPPER, 0.88, 0.92).add(ANTIMONY, 0.08, 0.12).build());
        r.register(new AlloyRecipe.Builder(INVAR).add(WROUGHT_IRON, 0.6, 0.7).add(NICKEL, 0.3, 0.4).build());
        r.register(new AlloyRecipe.Builder(ALUMINUM_BRASS).add(ALUMINUM, 0.65, 0.85).add(COPPER, 0.15, 0.35).build());
        r.register(new AlloyRecipe.Builder(MANYULLYN).add(COBALT, 0.4, 0.6).add(ARDITE, 0.4, 0.6).build());
        r.register(new AlloyRecipe.Builder(TUNGSTEN_STEEL).add(TUNGSTEN, 0.02, 0.18).add(STEEL, 0.72, 0.98).build());
        r.register(new AlloyRecipe.Builder(NICKEL_SILVER).add(COPPER, 0.50, 0.65).add(ZINC, 0.1, 0.3).add(NICKEL, 0.1, 0.3).build());
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void onRegisterBloomeryRecipeEvent(RegistryEvent.Register<BloomeryRecipe> event)
    {
        IForgeRegistry<BloomeryRecipe> registry = event.getRegistry();
        if (ConfigTFCM.RECIPES.aluminum)
        {
            registry.register(new BloomeryRecipe(TFCRegistries.METALS.getValue(ALUMINUM), FuelManager::isItemBloomeryFuel));
        }
        if (ConfigTFCM.RECIPES.ardite)
        {
            registry.register(new BloomeryRecipe(TFCRegistries.METALS.getValue(ARDITE), FuelManager::isItemBloomeryFuel));
        }
        if (ConfigTFCM.RECIPES.cobalt)
        {
            registry.register(new BloomeryRecipe(TFCRegistries.METALS.getValue(COBALT), FuelManager::isItemBloomeryFuel));
        }
    }

    @SubscribeEvent
    public static void onRegisterBlastFurnaceRecipeEvent(RegistryEvent.Register<BlastFurnaceRecipe> event)
    {
        IForgeRegistry<BlastFurnaceRecipe> registry = event.getRegistry();
        if (ConfigTFCM.RECIPES.osmium)
        {
            Metal osmium = TFCRegistries.METALS.getValue(OSMIUM);
            if (osmium != null)
            {
                registry.register(new BlastFurnaceRecipe(osmium, osmium, IIngredient.of("dustFlux")));
            }
        }
        if (ConfigTFCM.RECIPES.titanium)
        {
            Metal titanium = TFCRegistries.METALS.getValue(TITANIUM);
            if (titanium != null)
            {
                registry.register(new BlastFurnaceRecipe(titanium, titanium, IIngredient.of("dustFlux")));
            }
        }
        if (ConfigTFCM.RECIPES.tungsten)
        {
            Metal tungsten = TFCRegistries.METALS.getValue(TUNGSTEN);
            if (tungsten != null)
            {
                registry.register(new BlastFurnaceRecipe(tungsten, tungsten, IIngredient.of("dustFlux")));
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterAnvilRecipeEvent(RegistryEvent.Register<AnvilRecipe> event)
    {
        IForgeRegistry<AnvilRecipe> r = event.getRegistry();
        if (ConfigTFCM.RECIPES.aluminum)
        {
            Metal aluminium = TFCRegistries.METALS.getValue(ALUMINUM);
            if (aluminium != null)
            {
                r.register(new AnvilRecipe(new ResourceLocation(TFCMetallum.MODID, "aluminium_bloom"), x ->
                {
                    if (x.getItem() == ItemsTFC.REFINED_BLOOM)
                    {
                        IForgeable cap = x.getCapability(CapabilityForgeable.FORGEABLE_CAPABILITY, null);
                        if (cap instanceof IForgeableMeasurableMetal)
                        {
                            return ((IForgeableMeasurableMetal) cap).getMetal() == aluminium && ((IForgeableMeasurableMetal) cap).getMetalAmount() == 100;
                        }
                    }
                    return false;
                }, new ItemStack(ItemMetal.get(aluminium, INGOT)), Metal.Tier.TIER_II, null, HIT_LAST, HIT_SECOND_LAST, HIT_THIRD_LAST));
            }
        }
        if (ConfigTFCM.RECIPES.ardite)
        {
            Metal ardite = TFCRegistries.METALS.getValue(ARDITE);
            if (ardite != null)
            {
                r.register(new AnvilRecipe(new ResourceLocation(TFCMetallum.MODID, "ardite_bloom"), x ->
                {
                    if (x.getItem() == ItemsTFC.REFINED_BLOOM)
                    {
                        IForgeable cap = x.getCapability(CapabilityForgeable.FORGEABLE_CAPABILITY, null);
                        if (cap instanceof IForgeableMeasurableMetal)
                        {
                            return ((IForgeableMeasurableMetal) cap).getMetal() == ardite && ((IForgeableMeasurableMetal) cap).getMetalAmount() == 100;
                        }
                    }
                    return false;
                }, new ItemStack(ItemMetal.get(ardite, INGOT)), Metal.Tier.TIER_II, null, HIT_LAST, HIT_SECOND_LAST, HIT_THIRD_LAST));
            }
        }
        if (ConfigTFCM.RECIPES.cobalt)
        {
            Metal cobalt = TFCRegistries.METALS.getValue(COBALT);
            if (cobalt != null)
            {
                r.register(new AnvilRecipe(new ResourceLocation(TFCMetallum.MODID, "cobalt_bloom"), x ->
                {
                    if (x.getItem() == ItemsTFC.REFINED_BLOOM)
                    {
                        IForgeable cap = x.getCapability(CapabilityForgeable.FORGEABLE_CAPABILITY, null);
                        if (cap instanceof IForgeableMeasurableMetal)
                        {
                            return ((IForgeableMeasurableMetal) cap).getMetal() == cobalt && ((IForgeableMeasurableMetal) cap).getMetalAmount() == 100;
                        }
                    }
                    return false;
                }, new ItemStack(ItemMetal.get(cobalt, INGOT)), Metal.Tier.TIER_II, null, HIT_LAST, HIT_SECOND_LAST, HIT_THIRD_LAST));
            }
        }
    }
}
