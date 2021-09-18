package me.SpencerLeff.HyperEnchants;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public final class PlayerArmorEquipEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private ItemStack itemStack;

    public PlayerArmorEquipEvent(Player player, ItemStack itemStack) {
        this.player = player;
        this.setItemStack(itemStack);
    }

    @Override
    public HandlerList getHandlers() {
        return PlayerArmorEquipEvent.HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return PlayerArmorEquipEvent.HANDLER_LIST;
    }

	public Player getPlayer() {
		return player;
	}

	public ItemStack getItemStack() {
		return itemStack;
	}

	public void setItemStack(ItemStack itemStack) {
		this.itemStack = itemStack;
	}
}