package me.SpencerLeff.HyperEnchants;

import java.util.List;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RenameCheck implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void renameCheck(InventoryClickEvent e){
       
    	HumanEntity entity = e.getWhoClicked();
    	if (entity instanceof Player) {
    		Player player = (Player) e.getWhoClicked();
    		Inventory inventory = e.getInventory();
    		if (inventory instanceof AnvilInventory) {
    			int rawSlot = e.getRawSlot();
    			if (rawSlot == 2) {
    				AnvilInventory anvil = (AnvilInventory) e.getInventory();
    				int requiredXPLevel = anvil.getRepairCost();
    				int playerXPLevel = player.getLevel();
    				if (playerXPLevel >= requiredXPLevel) {
    					ItemStack item = anvil.getItem(2);
    					List<String> lore = item.getItemMeta().getLore();
    					for (int i = 0; i < lore.size(); i++) {
    						String loreLine = lore.get(i);
    						String[] splitLoreLine = loreLine.split(" ");
    						if (loreLine.contains("aquatic")) {
    							item.addUnsafeEnchantment(BookOpening.AQUATIC, 1);
    						}
    						else if (loreLine.contains("blessed")) {
    							item.addUnsafeEnchantment(BookOpening.BLESSED, 1);
    						}
    						else if (loreLine.contains("confusion")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = Integer.parseInt(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(BookOpening.CONFUSION, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(BookOpening.CONFUSION, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(BookOpening.CONFUSION, 3);
        						}
    						}
    						else if (loreLine.contains("guillotine")) {
    							item.addUnsafeEnchantment(BookOpening.GUILLOTINE, 1);
    						}
    						else if (loreLine.contains("nocturnal")) {
    							item.addUnsafeEnchantment(BookOpening.NOCTURNAL, 1);
    						}
    						else if (loreLine.contains("telepathy")) {
        						item.addUnsafeEnchantment(BookOpening.TELEPATHY, 1);
    						}
    						else if (loreLine.contains("cleave")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = Integer.parseInt(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(BookOpening.CLEAVE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(BookOpening.CLEAVE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(BookOpening.CLEAVE, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(BookOpening.CLEAVE, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(BookOpening.CLEAVE, 5);
        						}
    						}
    						else if (loreLine.contains("jump")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = Integer.parseInt(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(BookOpening.JUMP, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(BookOpening.JUMP, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(BookOpening.JUMP, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(BookOpening.JUMP, 4);
        						}
    						}
    						else if (loreLine.contains("magnetize")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = Integer.parseInt(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(BookOpening.MAGNETIZE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(BookOpening.MAGNETIZE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(BookOpening.MAGNETIZE, 3);
        						}
    						}
    						else if (loreLine.contains("obsidianshield")) {
        						item.addUnsafeEnchantment(BookOpening.OBSIDIANSHIELD, 1);
    						}
    						else if (loreLine.contains("retribution")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = Integer.parseInt(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(BookOpening.RETRIBUTION, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(BookOpening.RETRIBUTION, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(BookOpening.RETRIBUTION, 3);
        						}
    						}
    						else if (loreLine.contains("skyrocket")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = Integer.parseInt(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(BookOpening.SKYROCKET, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(BookOpening.SKYROCKET, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(BookOpening.SKYROCKET, 3);
        						}
    						}
    						else if (loreLine.contains("thwomp")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = Integer.parseInt(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(BookOpening.THWOMP, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(BookOpening.THWOMP, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(BookOpening.THWOMP, 3);
        						}
    						}
    						else if (loreLine.contains("agility")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = Integer.parseInt(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(BookOpening.AGILITY, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(BookOpening.AGILITY, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(BookOpening.AGILITY, 3);
        						}
    						}
    						else if (loreLine.contains("drunk")) {
        						item.addUnsafeEnchantment(BookOpening.DRUNK, 1);
    						}
    						else if (loreLine.contains("lifesteal")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = Integer.parseInt(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(BookOpening.LIFESTEAL, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(BookOpening.LIFESTEAL, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(BookOpening.LIFESTEAL, 3);
        						}
    						}
    						else if (loreLine.contains("fury")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = Integer.parseInt(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(BookOpening.FURY, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(BookOpening.FURY, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(BookOpening.FURY, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(BookOpening.FURY, 3);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(BookOpening.FURY, 3);
        						}
        						else if (enchantmentLvl == 6) {
        							item.addUnsafeEnchantment(BookOpening.FURY, 3);
        						}
    						}
    						else if (loreLine.contains("rage")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = Integer.parseInt(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(BookOpening.RAGE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(BookOpening.RAGE, 2);
        						}
    						}
    						else if (loreLine.contains("stockpile")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = Integer.parseInt(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(BookOpening.STOCKPILE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(BookOpening.STOCKPILE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(BookOpening.STOCKPILE, 3);
        						}
    						}
    					}
    				}
    			}
    		}
    	}
    }
}