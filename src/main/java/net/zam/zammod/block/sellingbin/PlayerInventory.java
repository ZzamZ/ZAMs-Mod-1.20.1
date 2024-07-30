package net.zam.zammod.block.sellingbin;

public class PlayerInventory {
    private final ImplementedInventoryImpl shippingBin = new ImplementedInventoryImpl(27);


    public PlayerInventory() {
    }

    public ImplementedInventory getShippingBin() {
        return this.shippingBin;
    }
}