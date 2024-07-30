package net.zam.zammod.block.sellingbin;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public class PlayerInventoryManager {
    private HashMap<UUID, PlayerInventory> playerInventories = new HashMap<>();

    public PlayerInventoryManager() {
    }

    public void addPlayerInventory(UUID playerId, PlayerInventory inventory) {
        this.playerInventories.put(playerId, inventory);
    }

    public PlayerInventory getPlayerInventory(UUID playerId) {
        if (!this.playerInventories.containsKey(playerId)) {
            PlayerInventory playerInventory = new PlayerInventory();
            this.addPlayerInventory(playerId, playerInventory);
            return playerInventory;
        } else {
            return this.playerInventories.get(playerId);
        }
    }

    public void removePlayerInventory(UUID playerId) {
        this.playerInventories.remove(playerId);
    }

    public void save(File file) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(this.playerInventories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            this.playerInventories = (HashMap<UUID, PlayerInventory>) inputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}