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
    					if (!item.hasItemMeta()) {
    						return;
    					}
    					if (!item.getItemMeta().hasLore()) {
    						return;
    					}
    					List<String> lore = item.getItemMeta().getLore();
    					for (int i = 0; i < lore.size(); i++) {
    						String loreLine = lore.get(i);
    						String[] splitLoreLine = loreLine.split(" ");
    						if (loreLine.contains("Aquatic")) {
    							item.addUnsafeEnchantment(CustomEnchants.AQUATIC, 1);
    						}
    						else if (loreLine.contains("Blessed")) {
    							item.addUnsafeEnchantment(CustomEnchants.BLESSED, 1);
    						}
    						else if (loreLine.contains("Confusion")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.CONFUSION, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.CONFUSION, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.CONFUSION, 3);
        						}
    						}
    						else if (loreLine.contains("Crystallize")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.CRYSTALLIZE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.CRYSTALLIZE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.CRYSTALLIZE, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.CRYSTALLIZE, 4);
        						}
    						}
    						else if (loreLine.contains("Geomancy")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.GEOMANCY, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.GEOMANCY, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.GEOMANCY, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.GEOMANCY, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.GEOMANCY, 5);
        						}
    						}
    						else if (loreLine.contains("Goomba")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.GOOMBA, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.GOOMBA, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.GOOMBA, 3);
        						}
    						}
    						else if (loreLine.contains("Guillotine")) {
    							item.addUnsafeEnchantment(CustomEnchants.GUILLOTINE, 1);
    						}
    						else if (loreLine.contains("Nocturnal")) {
    							item.addUnsafeEnchantment(CustomEnchants.NOCTURNAL, 1);
    						}
    						else if (loreLine.contains("Pillage")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.PILLAGE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.PILLAGE, 2);
        						}
    						}
    						else if (loreLine.contains("Plunge")) {
    							item.addUnsafeEnchantment(CustomEnchants.PLUNGE, 1);
    						}
    						else if (loreLine.contains("Poseidon")) {
    							item.addUnsafeEnchantment(CustomEnchants.POSEIDON, 1);
    						}
    						else if (loreLine.contains("Reflect")) {
        						item.addUnsafeEnchantment(CustomEnchants.REFLECT, 1);
    						}
    						else if (loreLine.contains("Rested")) {
        						item.addUnsafeEnchantment(CustomEnchants.RESTED, 1);
    						}
    						else if (loreLine.contains("Shift")) {
    							item.addUnsafeEnchantment(CustomEnchants.SHIFT, 1);
    						}
    						else if (loreLine.contains("Shroomy")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.SHROOMY, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.SHROOMY, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.SHROOMY, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.SHROOMY, 4);
        						}
    						}
    						else if (loreLine.contains("Shroud")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.SHROUD, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.SHROUD, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.SHROUD, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.SHROUD, 4);
        						}
    						}
    						else if (loreLine.contains("Sow")) {
        						item.addUnsafeEnchantment(CustomEnchants.SOW, 1);
    						}
    						else if (loreLine.contains("Starve")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.STARVE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.STARVE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.STARVE, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.STARVE, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.STARVE, 5);
        						}
        						else if (enchantmentLvl == 6) {
        							item.addUnsafeEnchantment(CustomEnchants.STARVE, 6);
        						}
        						else if (enchantmentLvl == 7) {
        							item.addUnsafeEnchantment(CustomEnchants.STARVE, 7);
        						}
    						}
    						else if (loreLine.contains("Telepathy")) {
        						item.addUnsafeEnchantment(CustomEnchants.TELEPATHY, 1);
    						}
    						else if (loreLine.contains("Thunderbolt")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.THUNDERBOLT, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.THUNDERBOLT, 2);
        						}
    						}
    						else if (loreLine.contains("Venom")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.VENOM, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.VENOM, 2);
        						}
    						}
    						else if (loreLine.contains("Absorb")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.ABSORB, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.ABSORB, 2);
        						}
    						}
    						else if (loreLine.contains("Angelic")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.ANGELIC, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.ANGELIC, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.ANGELIC, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.ANGELIC, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.ANGELIC, 5);
        						}
        						else if (enchantmentLvl == 6) {
        							item.addUnsafeEnchantment(CustomEnchants.ANGELIC, 6);
        						}
    						}
    						else if (loreLine.contains("Cleave")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.CLEAVE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.CLEAVE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.CLEAVE, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.CLEAVE, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.CLEAVE, 5);
        						}
    						}
    						else if (loreLine.contains("Devastate")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.DEVASTATE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.DEVASTATE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.DEVASTATE, 3);
        						}
    						}
    						else if (loreLine.contains("Diminish")) {
        						item.addUnsafeEnchantment(CustomEnchants.DIMINISH, 1);
    						}
    						else if (loreLine.contains("Executioner")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.EXECUTIONER, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.EXECUTIONER, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.EXECUTIONER, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.EXECUTIONER, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.EXECUTIONER, 5);
        						}
        						else if (enchantmentLvl == 6) {
        							item.addUnsafeEnchantment(CustomEnchants.EXECUTIONER, 6);
        						}
    						}
    						else if (loreLine.contains("Frozen")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.FROZEN, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.FROZEN, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.FROZEN, 3);
        						}
    						}
    						else if (loreLine.contains("Gorge")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.GORGE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.GORGE, 2);
        						}
    						}
    						else if (loreLine.contains("Guardians")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.GUARDIANS, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.GUARDIANS, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.GUARDIANS, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.GUARDIANS, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.GUARDIANS, 5);
        						}
        						else if (enchantmentLvl == 6) {
        							item.addUnsafeEnchantment(CustomEnchants.GUARDIANS, 6);
        						}
        						else if (enchantmentLvl == 7) {
        							item.addUnsafeEnchantment(CustomEnchants.GUARDIANS, 7);
        						}
        						else if (enchantmentLvl == 8) {
        							item.addUnsafeEnchantment(CustomEnchants.GUARDIANS, 8);
        						}
        						else if (enchantmentLvl == 9) {
        							item.addUnsafeEnchantment(CustomEnchants.GUARDIANS, 9);
        						}
    						}
    						else if (loreLine.contains("Jump")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.JUMP, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.JUMP, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.JUMP, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.JUMP, 4);
        						}
    						}
    						else if (loreLine.contains("Magnetize")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.MAGNETIZE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.MAGNETIZE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.MAGNETIZE, 3);
        						}
    						}
    						else if (loreLine.contains("Nimble")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.NIMBLE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.NIMBLE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.NIMBLE, 3);
        						}
    						}
    						else if (loreLine.contains("Obsidianshield")) {
        						item.addUnsafeEnchantment(CustomEnchants.OBSIDIANSHIELD, 1);
    						}
    						else if (loreLine.contains("Pinpoint")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.PINPOINT, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.PINPOINT, 2);
        						}
    						}
    						else if (loreLine.contains("Pokey")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.POKEY, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.POKEY, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.POKEY, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.POKEY, 4);
        						}
    						}
    						else if (loreLine.contains("Retaliate")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.RETALIATE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.RETALIATE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.RETALIATE, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.RETALIATE, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.RETALIATE, 5);
        						}
    						}
    						else if (loreLine.contains("Retribution")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.RETRIBUTION, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.RETRIBUTION, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.RETRIBUTION, 3);
        						}
    						}
    						else if (loreLine.contains("Skyrocket")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.SKYROCKET, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.SKYROCKET, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.SKYROCKET, 3);
        						}
    						}
    						else if (loreLine.contains("Thwomp")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.THWOMP, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.THWOMP, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.THWOMP, 3);
        						}
    						}
    						else if (loreLine.contains("Turbo")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.TURBO, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.TURBO, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.TURBO, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.TURBO, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.TURBO, 5);
        						}
    						}
    						else if (loreLine.contains("Vanquish")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.VANQUISH, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.VANQUISH, 2);
        						}
    						}
    						else if (loreLine.contains("Wisdom")) {
        						item.addUnsafeEnchantment(CustomEnchants.WISDOM, 1);
    						}
    						else if (loreLine.contains("Acrobatics")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.ACROBATICS, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.ACROBATICS, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.ACROBATICS, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.ACROBATICS, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.ACROBATICS, 5);
        						}
    						}
    						else if (loreLine.contains("Aerodynamics")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.AERODYNAMICS, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.AERODYNAMICS, 2);
        						}
    						}
    						else if (loreLine.contains("Agility")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.AGILITY, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.AGILITY, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.AGILITY, 3);
        						}
    						}
    						else if (loreLine.contains("Annihilation")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.ANNIHILATION, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.ANNIHILATION, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.ANNIHILATION, 3);
        						}
    						}
    						else if (loreLine.contains("Bloom")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.BLOOM, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.BLOOM, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.BLOOM, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.BLOOM, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.BLOOM, 5);
        						}
    						}
    						else if (loreLine.contains("Blossom")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.BLOSSOM, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.BLOSSOM, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.BLOSSOM, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.BLOSSOM, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.BLOSSOM, 5);
        						}
        						else if (enchantmentLvl == 6) {
        							item.addUnsafeEnchantment(CustomEnchants.BLOSSOM, 6);
        						}
    						}
    						else if (loreLine.contains("Demise")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.DEMISE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.DEMISE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.DEMISE, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.DEMISE, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.DEMISE, 5);
        						}
        						else if (enchantmentLvl == 6) {
        							item.addUnsafeEnchantment(CustomEnchants.DEMISE, 6);
        						}
        						else if (enchantmentLvl == 7) {
        							item.addUnsafeEnchantment(CustomEnchants.DEMISE, 7);
        						}
    						}
    						else if (loreLine.contains("Drunk")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
            						item.addUnsafeEnchantment(CustomEnchants.DRUNK, 1);
        						}
        						else if (enchantmentLvl == 2) {
            						item.addUnsafeEnchantment(CustomEnchants.DRUNK, 2);
        						}
    						}
    						else if (loreLine.contains("Dodge")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.DODGE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.DODGE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.DODGE, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.DODGE, 4);
        						}
    						}
    						else if (loreLine.contains("Enrage")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.ENRAGE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.ENRAGE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.ENRAGE, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.ENRAGE, 4);
        						}
    						}
    						else if (loreLine.contains("Fate")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.FATE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.FATE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.FATE, 3);
        						}
    						}
    						else if (loreLine.contains("Fury")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.FURY, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.FURY, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.FURY, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.FURY, 3);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.FURY, 3);
        						}
        						else if (enchantmentLvl == 6) {
        							item.addUnsafeEnchantment(CustomEnchants.FURY, 3);
        						}
    						}
    						else if (loreLine.contains("Glory")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.GLORY, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.GLORY, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.GLORY, 3);
        						}
    						}
    						else if (loreLine.contains("Intellect")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.INTELLECT, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.INTELLECT, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.INTELLECT, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.INTELLECT, 4);
        						}
    						}
    						else if (loreLine.contains("Lifesteal")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.LIFESTEAL, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.LIFESTEAL, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.LIFESTEAL, 3);
        						}
    						}
    						else if (loreLine.contains("Lucky")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.LUCKY, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.LUCKY, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.LUCKY, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.LUCKY, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.LUCKY, 5);
        						}
        						else if (enchantmentLvl == 6) {
        							item.addUnsafeEnchantment(CustomEnchants.LUCKY, 6);
        						}
        						else if (enchantmentLvl == 7) {
        							item.addUnsafeEnchantment(CustomEnchants.LUCKY, 7);
        						}
        						else if (enchantmentLvl == 8) {
        							item.addUnsafeEnchantment(CustomEnchants.LUCKY, 8);
        						}
        						else if (enchantmentLvl == 9) {
        							item.addUnsafeEnchantment(CustomEnchants.LUCKY, 9);
        						}
        						else if (enchantmentLvl == 10) {
        							item.addUnsafeEnchantment(CustomEnchants.LUCKY, 10);
        						}
    						}
    						else if (loreLine.contains("Meteor")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.METEOR, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.METEOR, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.METEOR, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.METEOR, 4);
        						}
    						}
    						else if (loreLine.contains("Puncture")) {
        						item.addUnsafeEnchantment(CustomEnchants.PUNCTURE, 1);
    						}
    						else if (loreLine.contains("Rage")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.RAGE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.RAGE, 2);
        						}
    						}
    						else if (loreLine.contains("Safeguard")) {
    							item.addUnsafeEnchantment(CustomEnchants.SAFEGUARD, 1);
    						}
    						else if (loreLine.contains("Silence")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.SILENCE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.SILENCE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.SILENCE, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.SILENCE, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.SILENCE, 5);
        						}
        						else if (enchantmentLvl == 6) {
        							item.addUnsafeEnchantment(CustomEnchants.SILENCE, 6);
        						}
        						else if (enchantmentLvl == 7) {
        							item.addUnsafeEnchantment(CustomEnchants.SILENCE, 7);
        						}
    						}
    						else if (loreLine.contains("Stalwart")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.STALWART, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.STALWART, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.STALWART, 3);
        						}
    						}
    						else if (loreLine.contains("Stockpile")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.STOCKPILE, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.STOCKPILE, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.STOCKPILE, 3);
        						}
    						}
    						else if (loreLine.contains("Voidwalker")) {
        						String enchantmentLvlString = splitLoreLine[1];
        						int enchantmentLvl = getLevelFromNumeral(enchantmentLvlString);
        						if (enchantmentLvl == 1) {
        							item.addUnsafeEnchantment(CustomEnchants.VOIDWALKER, 1);
        						}
        						else if (enchantmentLvl == 2) {
        							item.addUnsafeEnchantment(CustomEnchants.VOIDWALKER, 2);
        						}
        						else if (enchantmentLvl == 3) {
        							item.addUnsafeEnchantment(CustomEnchants.VOIDWALKER, 3);
        						}
        						else if (enchantmentLvl == 4) {
        							item.addUnsafeEnchantment(CustomEnchants.VOIDWALKER, 4);
        						}
        						else if (enchantmentLvl == 5) {
        							item.addUnsafeEnchantment(CustomEnchants.VOIDWALKER, 5);
        						}
    						}
    						else if (loreLine.contains("Whirlpool")) {
        						item.addUnsafeEnchantment(CustomEnchants.WHIRLPOOL, 1);
    						}
    						else if (loreLine.contains("Blink")) {
        						item.addUnsafeEnchantment(CustomEnchants.BLINK, 1);
    						}
    						else if (loreLine.contains("Overdrive")) {
        						item.addUnsafeEnchantment(CustomEnchants.OVERDRIVE, 1);
    						}
    						else if (loreLine.contains("Titan")) {
        						item.addUnsafeEnchantment(CustomEnchants.TITAN, 1);
    						}
    					}
    				}
    			}
    		}
    	}
    }
    public int getLevelFromNumeral(String n) {
		int num = 0;
		if (n.equals("I")) {
			num = 1;
		}
		else if (n.equals("II")) {
			num = 2;
		}
		else if (n.equals("III")) {
			num = 3;
		}
		else if (n.equals("IV")) {
			num = 4;
		}
		else if (n.equals("V")) {
			num = 5;
		}
		else if (n.equals("VI")) {
			num = 6;
		}
		else if (n.equals("VII")) {
			num = 7;
		}
		else if (n.equals("VIII")) {
			num = 8;
		}
		else if (n.equals("IX")) {
			num = 9;
		}
		else if (n.equals("X")) {
			num = 10;
		}
		else if (n.equals("XI")) {
			num = 11;
		}
		else if (n.equals("XII")) {
			num = 12;
		}
		else if (n.equals("XIII")) {
			num = 13;
		}
		else if (n.equals("XIV")) {
			num = 14;
		}
		else if (n.equals("XV")) {
			num = 15;
		}
		else if (n.equals("XVI")) {
			num = 16;
		}
		else if (n.equals("XVII")) {
			num = 17;
		}
		else if (n.equals("XVIII")) {
			num = 18;
		}
		else if (n.equals("XIX")) {
			num = 19;
		}
		else if (n.equals("XX")) {
			num = 20;
		}
		else {
			num = 1;
		}
		return num;
	}
}