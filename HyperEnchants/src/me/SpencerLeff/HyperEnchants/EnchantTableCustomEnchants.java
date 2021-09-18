package me.SpencerLeff.HyperEnchants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantTableCustomEnchants implements Listener {
	@EventHandler
	public void itemBecomesEnchanted(EnchantItemEvent e) throws InterruptedException {
		List<String> swordEnchants = new ArrayList<String>();
		swordEnchants.add("Guillotine");
		swordEnchants.add("Venom");
		swordEnchants.add("Executioner");
		swordEnchants.add("Magnetize");
		swordEnchants.add("Thwomp");
		swordEnchants.add("Fury");
		swordEnchants.add("Intellect");
		swordEnchants.add("Lifesteal");
		swordEnchants.add("Rage");
		List<Integer> maxSwordLevels = new ArrayList<Integer>();
		maxSwordLevels.add(1);
		maxSwordLevels.add(2);
		maxSwordLevels.add(6);
		maxSwordLevels.add(3);
		maxSwordLevels.add(3);
		maxSwordLevels.add(6);
		maxSwordLevels.add(4);
		maxSwordLevels.add(3);
		maxSwordLevels.add(2);
		
		List<String> axeEnchants = new ArrayList<String>();
		axeEnchants.add("Blessed");
		axeEnchants.add("Confusion");
		axeEnchants.add("Guillotine");
		axeEnchants.add("Cleave");
		axeEnchants.add("Thwomp");
		axeEnchants.add("Wisdom");
		axeEnchants.add("Enrage");
		axeEnchants.add("Silence");
		axeEnchants.add("Rage");
		List<Integer> maxAxeLevels = new ArrayList<Integer>();
		maxAxeLevels.add(1);
		maxAxeLevels.add(3);
		maxAxeLevels.add(1);
		maxAxeLevels.add(5);
		maxAxeLevels.add(3);
		maxAxeLevels.add(1);
		maxAxeLevels.add(4);
		maxAxeLevels.add(7);
		maxAxeLevels.add(2);
		
		List<String> bowEnchants = new ArrayList<String>();
		bowEnchants.add("Starve");
		bowEnchants.add("Thunderbolt");
		bowEnchants.add("Devastate");
		bowEnchants.add("Nimble");
		bowEnchants.add("Bloom");
		bowEnchants.add("Meteor");
		List<Integer> maxBowLevels = new ArrayList<Integer>();
		maxBowLevels.add(2);
		maxBowLevels.add(7);
		maxBowLevels.add(3);
		maxBowLevels.add(3);
		maxBowLevels.add(5);
		maxBowLevels.add(4);
		
		List<String> crossbowEnchants = new ArrayList<String>();
		crossbowEnchants.add("Pillage");
		crossbowEnchants.add("Starve");
		crossbowEnchants.add("Thunderbolt");
		crossbowEnchants.add("Devastate");
		crossbowEnchants.add("Meteor");
		crossbowEnchants.add("Puncture");
		List<Integer> maxCrossbowLevels = new ArrayList<Integer>();
		maxCrossbowLevels.add(2);
		maxCrossbowLevels.add(7);
		maxCrossbowLevels.add(2);
		maxCrossbowLevels.add(3);
		maxCrossbowLevels.add(4);
		maxCrossbowLevels.add(1);
		
		List<String> tridentEnchants = new ArrayList<String>();
		tridentEnchants.add("Plunge");
		tridentEnchants.add("Poseidon");
		tridentEnchants.add("Shroud");
		tridentEnchants.add("Diminish");
		tridentEnchants.add("Pinpoint");
		tridentEnchants.add("Acrobatics");
		tridentEnchants.add("Aerodynamics");
		tridentEnchants.add("Demise");
		List<Integer> maxTridentLevels = new ArrayList<Integer>();
		maxTridentLevels.add(1);
		maxTridentLevels.add(1);
		maxTridentLevels.add(4);
		maxTridentLevels.add(1);
		maxTridentLevels.add(2);
		maxTridentLevels.add(5);
		maxTridentLevels.add(2);
		maxTridentLevels.add(7);
		
		List<String> pickaxeEnchants = new ArrayList<String>();
		pickaxeEnchants.add("Geomancy");
		pickaxeEnchants.add("Telepathy");
		pickaxeEnchants.add("Turbo");
		pickaxeEnchants.add("Fate");
		pickaxeEnchants.add("Vanquish");
		List<Integer> maxPickaxeLevels = new ArrayList<Integer>();
		maxPickaxeLevels.add(5);
		maxPickaxeLevels.add(1);
		maxPickaxeLevels.add(5);
		maxPickaxeLevels.add(3);
		maxPickaxeLevels.add(2);
		
		List<String> helmetEnchants = new ArrayList<String>();
		helmetEnchants.add("Aquatic");
		helmetEnchants.add("Crystallize");
		helmetEnchants.add("Nocturnal");
		helmetEnchants.add("Rested");
		helmetEnchants.add("Frozen");
		helmetEnchants.add("Gorge");
		helmetEnchants.add("Drunk");
		helmetEnchants.add("Lucky");
		helmetEnchants.add("Voidwalker");
		List<Integer> maxHelmetLevels = new ArrayList<Integer>();
		maxHelmetLevels.add(1);
		maxHelmetLevels.add(4);
		maxHelmetLevels.add(1);
		maxHelmetLevels.add(1);
		maxHelmetLevels.add(3);
		maxHelmetLevels.add(2);
		maxHelmetLevels.add(2);
		maxHelmetLevels.add(10);
		maxHelmetLevels.add(5);
		
		List<String> chestplateEnchants = new ArrayList<String>();
		chestplateEnchants.add("Crystallize");
		chestplateEnchants.add("Sow");
		chestplateEnchants.add("Pokey");
		chestplateEnchants.add("Retaliate");
		chestplateEnchants.add("Annihilation");
		chestplateEnchants.add("Lucky");
		chestplateEnchants.add("Stalwart");
		chestplateEnchants.add("Stockpile");
		List<Integer> maxChestplateLevels = new ArrayList<Integer>();
		maxChestplateLevels.add(4);
		maxChestplateLevels.add(1);
		maxChestplateLevels.add(4);
		maxChestplateLevels.add(5);
		maxChestplateLevels.add(3);
		maxChestplateLevels.add(10);
		maxChestplateLevels.add(3);
		maxChestplateLevels.add(3);
		
		List<String> leggingsEnchants = new ArrayList<String>();
		leggingsEnchants.add("Crystallize");
		leggingsEnchants.add("Shroomy");
		leggingsEnchants.add("Angelic");
		leggingsEnchants.add("Guardians");
		leggingsEnchants.add("Obsidianshield");
		leggingsEnchants.add("Dodge");
		leggingsEnchants.add("Lucky");
		leggingsEnchants.add("Whirlpool");
		List<Integer> maxLeggingsLevels = new ArrayList<Integer>();
		maxLeggingsLevels.add(4);
		maxLeggingsLevels.add(4);
		maxLeggingsLevels.add(6);
		maxLeggingsLevels.add(9);
		maxLeggingsLevels.add(1);
		maxLeggingsLevels.add(4);
		maxLeggingsLevels.add(10);
		maxLeggingsLevels.add(1);
		
		List<String> bootsEnchants = new ArrayList<String>();
		bootsEnchants.add("Crystallize");
		bootsEnchants.add("Goomba");
		bootsEnchants.add("Shift");
		bootsEnchants.add("Jump");
		bootsEnchants.add("Skyrocket");
		bootsEnchants.add("Agility");
		bootsEnchants.add("Blossom");
		bootsEnchants.add("Lucky");
		List<Integer> maxBootsLevels = new ArrayList<Integer>();
		maxBootsLevels.add(4);
		maxBootsLevels.add(3);
		maxBootsLevels.add(1);
		maxBootsLevels.add(4);
		maxBootsLevels.add(3);
		maxBootsLevels.add(3);
		maxBootsLevels.add(6);
		maxBootsLevels.add(10);

		Player player = e.getEnchanter();
		ItemStack item = e.getItem();
		int expCost = e.getExpLevelCost();
		
		Random rand = new Random();
		int upperBound = 4;
		int indexValue = rand.nextInt(upperBound);
		List<Integer> numberOfCustomEnchants = new ArrayList<Integer>();
		if (expCost <= 10) {
			numberOfCustomEnchants.add(0);
			numberOfCustomEnchants.add(0);
			numberOfCustomEnchants.add(0);
			numberOfCustomEnchants.add(1);
		}
		else if (expCost > 10 && expCost <= 20) {
			numberOfCustomEnchants.add(0);
			numberOfCustomEnchants.add(0);
			numberOfCustomEnchants.add(1);
			numberOfCustomEnchants.add(2);
		}
		else if (expCost > 20 && expCost <= 29) {
			numberOfCustomEnchants.add(0);
			numberOfCustomEnchants.add(1);
			numberOfCustomEnchants.add(2);
			numberOfCustomEnchants.add(2);
		}
		else if (expCost == 30) {
			numberOfCustomEnchants.add(1);
			numberOfCustomEnchants.add(2);
			numberOfCustomEnchants.add(2);
			numberOfCustomEnchants.add(3);
		}
		
		int numEnchants = numberOfCustomEnchants.get(indexValue);
		
		if (e.getItem().toString().contains("_SWORD")) {
			for (int k = 0; k < numEnchants; k++) {
				Random randomEnchant = new Random();
				int eArrayLength = swordEnchants.size();
				int randomValue = randomEnchant.nextInt(eArrayLength);
					
				String selectedEnchantment = swordEnchants.get(randomValue);
				int maxLevelOfEnchant = maxSwordLevels.get(randomValue);
				swordEnchants.remove(randomValue);
				maxSwordLevels.remove(randomValue);
					
				Random randomLevel = new Random();
				int selectedLevel = randomLevel.nextInt(maxLevelOfEnchant) + 1;
				
				if (selectedEnchantment == "Guillotine") {
					applyTheEnchantment(item, CustomEnchants.GUILLOTINE, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				if (selectedEnchantment == "Venom") {
					applyTheEnchantment(item, CustomEnchants.VENOM, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				if (selectedEnchantment == "Executioner") {
					applyTheEnchantment(item, CustomEnchants.EXECUTIONER, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Magnetize") {
					applyTheEnchantment(item, CustomEnchants.MAGNETIZE, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Thwomp") {
					applyTheEnchantment(item, CustomEnchants.THWOMP, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Fury") {
					applyTheEnchantment(item, CustomEnchants.FURY, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Intellect") {
					applyTheEnchantment(item, CustomEnchants.INTELLECT, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Lifesteal") {
					applyTheEnchantment(item, CustomEnchants.LIFESTEAL, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Rage") {
					applyTheEnchantment(item, CustomEnchants.RAGE, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
			}
		}
		if (e.getItem().toString().contains("_AXE")) {
			for (int k = 0; k < numEnchants; k++) {
				Random randomEnchant = new Random();
				int eArrayLength = axeEnchants.size();
				int randomValue = randomEnchant.nextInt(eArrayLength);
					
				String selectedEnchantment = axeEnchants.get(randomValue);
				int maxLevelOfEnchant = maxAxeLevels.get(randomValue);
				axeEnchants.remove(randomValue);
				maxAxeLevels.remove(randomValue);
					
				Random randomLevel = new Random();
				int selectedLevel = randomLevel.nextInt(maxLevelOfEnchant) + 1;
				
				if (selectedEnchantment == "Blessed") {
					applyTheEnchantment(item, CustomEnchants.BLESSED, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Confusion") {
					applyTheEnchantment(item, CustomEnchants.CONFUSION, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Guillotine") {
					applyTheEnchantment(item, CustomEnchants.GUILLOTINE, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Cleave") {
					applyTheEnchantment(item, CustomEnchants.CLEAVE, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Thwomp") {
					applyTheEnchantment(item, CustomEnchants.THWOMP, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Wisdom") {
					applyTheEnchantment(item, CustomEnchants.THWOMP, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Enrage") {
					applyTheEnchantment(item, CustomEnchants.ENRAGE, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Silence") {
					applyTheEnchantment(item, CustomEnchants.SILENCE, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Rage") {
					applyTheEnchantment(item, CustomEnchants.RAGE, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
			}
		}
		if (e.getItem().toString().contains("{BOW")) {
			for (int k = 0; k < numEnchants; k++) {
				Random randomEnchant = new Random();
				int eArrayLength = bowEnchants.size();
				int randomValue = randomEnchant.nextInt(eArrayLength);
					
				String selectedEnchantment = bowEnchants.get(randomValue);
				int maxLevelOfEnchant = maxBowLevels.get(randomValue);
				bowEnchants.remove(randomValue);
				maxBowLevels.remove(randomValue);
					
				Random randomLevel = new Random();
				int selectedLevel = randomLevel.nextInt(maxLevelOfEnchant) + 1;
				
				if (selectedEnchantment == "Starve") {
					applyTheEnchantment(item, CustomEnchants.STARVE, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Thunderbolt") {
					applyTheEnchantment(item, CustomEnchants.THUNDERBOLT, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Devastate") {
					applyTheEnchantment(item, CustomEnchants.DEVASTATE, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Nimble") {
					applyTheEnchantment(item, CustomEnchants.NIMBLE, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Bloom") {
					applyTheEnchantment(item, CustomEnchants.BLOOM, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Meteor") {
					applyTheEnchantment(item, CustomEnchants.METEOR, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
			}
		}
		if (e.getItem().toString().contains("CROSSBOW")) {
			for (int k = 0; k < numEnchants; k++) {
				Random randomEnchant = new Random();
				int eArrayLength = crossbowEnchants.size();
				int randomValue = randomEnchant.nextInt(eArrayLength);
					
				String selectedEnchantment = crossbowEnchants.get(randomValue);
				int maxLevelOfEnchant = maxCrossbowLevels.get(randomValue);
				crossbowEnchants.remove(randomValue);
				maxCrossbowLevels.remove(randomValue);
					
				Random randomLevel = new Random();
				int selectedLevel = randomLevel.nextInt(maxLevelOfEnchant) + 1;
				
				if (selectedEnchantment == "Pillage") {
					applyTheEnchantment(item, CustomEnchants.PILLAGE, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Starve") {
					applyTheEnchantment(item, CustomEnchants.STARVE, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Thunderbolt") {
					applyTheEnchantment(item, CustomEnchants.THUNDERBOLT, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Devastate") {
					applyTheEnchantment(item, CustomEnchants.DEVASTATE, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Meteor") {
					applyTheEnchantment(item, CustomEnchants.METEOR, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Puncture") {
					applyTheEnchantment(item, CustomEnchants.PUNCTURE, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
			}
		}
		if (e.getItem().toString().contains("TRIDENT")) {
			for (int k = 0; k < numEnchants; k++) {
				Random randomEnchant = new Random();
				int eArrayLength = tridentEnchants.size();
				int randomValue = randomEnchant.nextInt(eArrayLength);
					
				String selectedEnchantment = tridentEnchants.get(randomValue);
				int maxLevelOfEnchant = maxTridentLevels.get(randomValue);
				tridentEnchants.remove(randomValue);
				maxTridentLevels.remove(randomValue);
					
				Random randomLevel = new Random();
				int selectedLevel = randomLevel.nextInt(maxLevelOfEnchant) + 1;
				
				if (selectedEnchantment == "Plunge") {
					applyTheEnchantment(item, CustomEnchants.PLUNGE, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Poseidon") {
					applyTheEnchantment(item, CustomEnchants.POSEIDON, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Shroud") {
					applyTheEnchantment(item, CustomEnchants.SHROUD, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Diminish") {
					applyTheEnchantment(item, CustomEnchants.DIMINISH, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Pinpoint") {
					applyTheEnchantment(item, CustomEnchants.PINPOINT, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Acrobatics") {
					applyTheEnchantment(item, CustomEnchants.ACROBATICS, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Aerodynamics") {
					applyTheEnchantment(item, CustomEnchants.AERODYNAMICS, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Demise") {
					applyTheEnchantment(item, CustomEnchants.DEMISE, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
			}
		}
		if (e.getItem().toString().contains("_PICKAXE")) {
			for (int k = 0; k < numEnchants; k++) {
				Random randomEnchant = new Random();
				int eArrayLength = pickaxeEnchants.size();
				int randomValue = randomEnchant.nextInt(eArrayLength);
					
				String selectedEnchantment = pickaxeEnchants.get(randomValue);
				int maxLevelOfEnchant = maxPickaxeLevels.get(randomValue);
				pickaxeEnchants.remove(randomValue);
				maxPickaxeLevels.remove(randomValue);
					
				Random randomLevel = new Random();
				int selectedLevel = randomLevel.nextInt(maxLevelOfEnchant) + 1;
				
				if (selectedEnchantment == "Geomancy") {
					applyTheEnchantment(item, CustomEnchants.GEOMANCY, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Telepathy") {
					applyTheEnchantment(item, CustomEnchants.TELEPATHY, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Turbo") {
					applyTheEnchantment(item, CustomEnchants.TURBO, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Vanquish") {
					applyTheEnchantment(item, CustomEnchants.VANQUISH, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Fate") {
					applyTheEnchantment(item, CustomEnchants.FATE, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
			}
		}
		if (e.getItem().toString().contains("_HELMET")) {
			for (int k = 0; k < numEnchants; k++) {
				Random randomEnchant = new Random();
				int eArrayLength = helmetEnchants.size();
				int randomValue = randomEnchant.nextInt(eArrayLength);
					
				String selectedEnchantment = helmetEnchants.get(randomValue);
				int maxLevelOfEnchant = maxHelmetLevels.get(randomValue);
				helmetEnchants.remove(randomValue);
				maxHelmetLevels.remove(randomValue);
					
				Random randomLevel = new Random();
				int selectedLevel = randomLevel.nextInt(maxLevelOfEnchant) + 1;
					
				if (selectedEnchantment == "Aquatic") {
					applyTheEnchantment(item, CustomEnchants.AQUATIC, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Crystallize") {
					applyTheEnchantment(item, CustomEnchants.CRYSTALLIZE, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Nocturnal") {
					applyTheEnchantment(item, CustomEnchants.NOCTURNAL, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Rested") {
					applyTheEnchantment(item, CustomEnchants.RESTED, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Frozen") {
					applyTheEnchantment(item, CustomEnchants.FROZEN, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Gorge") {
					applyTheEnchantment(item, CustomEnchants.GORGE, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Drunk") {
					applyTheEnchantment(item, CustomEnchants.DRUNK, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Lucky") {
					applyTheEnchantment(item, CustomEnchants.LUCKY, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Voidwalker") {
					applyTheEnchantment(item, CustomEnchants.VOIDWALKER, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
			}
		}
		if (e.getItem().toString().contains("_CHESTPLATE")) {
			for (int k = 0; k < numEnchants; k++) {
				Random randomEnchant = new Random();
				int eArrayLength = chestplateEnchants.size();
				int randomValue = randomEnchant.nextInt(eArrayLength);
					
				String selectedEnchantment = chestplateEnchants.get(randomValue);
				int maxLevelOfEnchant = maxChestplateLevels.get(randomValue);
				chestplateEnchants.remove(randomValue);
				maxChestplateLevels.remove(randomValue);
				
				Random randomLevel = new Random();
				int selectedLevel = randomLevel.nextInt(maxLevelOfEnchant) + 1;
				
				if (selectedEnchantment == "Crystallize") {
					applyTheEnchantment(item, CustomEnchants.CRYSTALLIZE, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Sow") {
					applyTheEnchantment(item, CustomEnchants.SOW, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Pokey") {
					applyTheEnchantment(item, CustomEnchants.POKEY, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Retaliate") {
					applyTheEnchantment(item, CustomEnchants.RETALIATE, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Annihilation") {
					applyTheEnchantment(item, CustomEnchants.ANNIHILATION, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Lucky") {
					applyTheEnchantment(item, CustomEnchants.LUCKY, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Stalwart") {
					applyTheEnchantment(item, CustomEnchants.STALWART, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Stockpile") {
					applyTheEnchantment(item, CustomEnchants.STOCKPILE, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
			}
		}
		if (e.getItem().toString().contains("_LEGGINGS")) {
			for (int k = 0; k < numEnchants; k++) {
				Random randomEnchant = new Random();
				int eArrayLength = leggingsEnchants.size();
				int randomValue = randomEnchant.nextInt(eArrayLength);
					
				String selectedEnchantment = leggingsEnchants.get(randomValue);
				int maxLevelOfEnchant = maxLeggingsLevels.get(randomValue);
				leggingsEnchants.remove(randomValue);
				maxLeggingsLevels.remove(randomValue);
					
				Random randomLevel = new Random();
				int selectedLevel = randomLevel.nextInt(maxLevelOfEnchant) + 1;
				
				if (selectedEnchantment == "Crystallize") {
					applyTheEnchantment(item, CustomEnchants.CRYSTALLIZE, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Shroomy") {
					applyTheEnchantment(item, CustomEnchants.SHROOMY, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Angelic") {
					applyTheEnchantment(item, CustomEnchants.ANGELIC, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Guardians") {
					applyTheEnchantment(item, CustomEnchants.GUARDIANS, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Obsidianshield") {
					applyTheEnchantment(item, CustomEnchants.OBSIDIANSHIELD, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Dodge") {
					applyTheEnchantment(item, CustomEnchants.DODGE, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Lucky") {
					applyTheEnchantment(item, CustomEnchants.LUCKY, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Whirlpool") {
					applyTheEnchantment(item, CustomEnchants.WHIRLPOOL, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
			}
		}
		if (e.getItem().toString().contains("_BOOTS")) {
			for (int k = 0; k < numEnchants; k++) {
				Random randomEnchant = new Random();
				int eArrayLength = bootsEnchants.size();
				int randomValue = randomEnchant.nextInt(eArrayLength);
					
				String selectedEnchantment = bootsEnchants.get(randomValue);
				int maxLevelOfEnchant = maxBootsLevels.get(randomValue);
				bootsEnchants.remove(randomValue);
				maxBootsLevels.remove(randomValue);
					
				Random randomLevel = new Random();
				int selectedLevel = randomLevel.nextInt(maxLevelOfEnchant) + 1;
				
				if (selectedEnchantment == "Crystallize") {
					applyTheEnchantment(item, CustomEnchants.CRYSTALLIZE, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Goomba") {
					applyTheEnchantment(item, CustomEnchants.GOOMBA, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Shift") {
					applyTheEnchantment(item, CustomEnchants.SHIFT, selectedEnchantment, selectedLevel, ChatColor.WHITE, player);
				}
				else if (selectedEnchantment == "Jump") {
					applyTheEnchantment(item, CustomEnchants.JUMP, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Skyrocket") {
					applyTheEnchantment(item, CustomEnchants.SKYROCKET, selectedEnchantment, selectedLevel, ChatColor.AQUA, player);
				}
				else if (selectedEnchantment == "Agility") {
					applyTheEnchantment(item, CustomEnchants.AGILITY, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Blossom") {
					applyTheEnchantment(item, CustomEnchants.BLOSSOM, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
				else if (selectedEnchantment == "Lucky") {
					applyTheEnchantment(item, CustomEnchants.LUCKY, selectedEnchantment, selectedLevel, ChatColor.GOLD, player);
				}
			}	
		}
	}
	
	public void applyTheEnchantment(ItemStack item, Enchantment enchantment, String selectedEnchantment, int selectedLevel, ChatColor color, Player player) {
		ItemMeta currentMeta = item.getItemMeta();
		List<String> currentLore = new ArrayList<String>();
		String selectedLevelString = getRomanNumeral(selectedLevel);
		currentLore.add(color + selectedEnchantment + " " + selectedLevelString);
		if (currentMeta.hasLore()) {
			for (String l : currentMeta.getLore()) {
				currentLore.add(l);
			}
		}
		
		currentMeta.setLore(currentLore);
		item.setItemMeta(currentMeta);
		player.updateInventory();
		item.addUnsafeEnchantment(enchantment, selectedLevel);
	}
	
	public String getRomanNumeral(int num) {
		String n = "0";
		if (num == 1) {
			n = "I";
		}
		else if (num == 2) {
			n = "II";
		}
		else if (num == 3) {
			n = "III";
		}
		else if (num == 4) {
			n = "IV";
		}
		else if (num == 5) {
			n = "V";
		}
		else if (num == 6) {
			n = "VI";
		}
		else if (num == 7) {
			n = "VII";
		}
		else if (num == 8) {
			n = "VIII";
		}
		else if (num == 9) {
			n = "IX";
		}
		else if (num == 10) {
			n = "X";
		}
		else if (num == 11) {
			n = "XI";
		}
		else if (num == 12) {
			n = "XII";
		}
		else if (num == 13) {
			n = "XIII";
		}
		else if (num == 14) {
			n = "XIV";
		}
		else if (num == 15) {
			n = "XV";
		}
		else if (num == 16) {
			n = "XVI";
		}
		else if (num == 17) {
			n = "XVII";
		}
		else if (num == 18) {
			n = "XVIII";
		}
		else if (num == 19) {
			n = "XIX";
		}
		else if (num == 20) {
			n = "XX";
		}
		else {
			n = "I";
		}
		return n;
	}
}