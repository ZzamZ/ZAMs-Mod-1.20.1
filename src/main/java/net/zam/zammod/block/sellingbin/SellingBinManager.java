package net.zam.zammod.block.sellingbin;

import net.zam.zammod.ZAMMod;

import java.util.TimerTask;

class SellingBinManager extends TimerTask {
    private final ZAMMod mod;

    SellingBinManager(ZAMMod mod) {
        this.mod = mod;
    }

    public void run() {
        ZAMMod.inventoryManager.save(ZAMMod.inventoryFile);
    }
}
