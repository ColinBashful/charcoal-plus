package com.colinbashful.charcoalplus;

import com.google.common.base.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod("charcoalplus")
public class CharcoalPlus {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "charcoalplus");
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister
            .create(ForgeRegistries.BLOCKS, "charcoalplus");
    public static final RegistryObject<Item> COAL_PIECE = ITEMS.register("coal_piece",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)) {
                @Override
                public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
                    return 200;
                }
            });
    public static final RegistryObject<Item> CHARCOAL_PIECE = ITEMS.register("charcoal_piece",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)) {
                @Override
                public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
                    return 200;
                }
            });

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
            CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
            CreativeModeTab tab) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)) {
            @Override
            public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
                return 16000;
            }
        });
    }

    public static final RegistryObject<Block> CHARCOAL_BLOCK = registerBlock("charcoal_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).requiresCorrectToolForDrops()),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public CharcoalPlus() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
