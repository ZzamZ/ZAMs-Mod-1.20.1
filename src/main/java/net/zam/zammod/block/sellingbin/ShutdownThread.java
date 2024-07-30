package net.zam.zammod.block.sellingbin;

import net.zam.zammod.ZAMMod;

public class ShutdownThread extends Thread {
    public ShutdownThread() {
    }

    public void run() {
        ZAMMod.inventoryManager.save(ZAMMod.inventoryFile);
    }
}