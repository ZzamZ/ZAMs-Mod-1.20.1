package net.zam.zammod.util;

import net.minecraft.nbt.NbtAccounter;

public interface IEntityDataSaver {
    NbtAccounter getPersistentData();
}