package me.SpencerLeff.HyperEnchants;

import java.util.List;

import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RemoveEnchantsGrindstone implements Listener {	
	@EventHandler
	public void removeEnchantments(InventoryClickEvent e) {
		if (e.getClickedInventory() == null) {
			return;
		}
		if (e.getClickedInventory().getType() == InventoryType.GRINDSTONE && e.getSlotType() == InventoryType.SlotType.RESULT) {
        	Player player = (Player) e.getWhoClicked();
        	ItemStack currentItem = e.getCurrentItem();
        	if (currentItem.toString().contains("AIR")) {
        		return;
        	}
        	else {
        		Result result = e.getResult();
        		if (result.toString().contains("ALLOW")) {
        			int extraExp = removeCustomEnchantsFromItem(currentItem, player);
        			player.getWorld().spawn(player.getLocation(), ExperienceOrb.class).setExperience(extraExp);
        		}
        	}
        }
	}
	
	public int removeCustomEnchantsFromItem(ItemStack item, Player player) {
		int extraExp = 0;
		if (!item.hasItemMeta()) {
			return 0;
		}
		if (!item.getItemMeta().hasLore()) {
			return 0;
		}
		ItemMeta itemMeta = item.getItemMeta();
		List<String> currentLore = item.getItemMeta().getLore();
		int loreSize = currentLore.size();
		for (int i = loreSize - 1; i >= 0; i--) {
			String loreLine = currentLore.get(i);
			if (loreLine.contains("Aquatic")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.AQUATIC);
			}
			else if (loreLine.contains("Blessed")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.BLESSED);
			}
			else if (loreLine.contains("Confusion")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.CONFUSION);
			}
			else if (loreLine.contains("Crystallize")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.CRYSTALLIZE);
			}
			else if (loreLine.contains("Geomancy")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.GEOMANCY);
			}
			else if (loreLine.contains("Goomba")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.GOOMBA);
			}
			else if (loreLine.contains("Guillotine")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.GUILLOTINE);
			}
			else if (loreLine.contains("Nocturnal")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.NOCTURNAL);
			}
			else if (loreLine.contains("Pillage")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.PILLAGE);
			}
			else if (loreLine.contains("Plunge")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.PLUNGE);
			}
			else if (loreLine.contains("Poseidon")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.POSEIDON);
			}
			else if (loreLine.contains("Reflect")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.REFLECT);
			}
			else if (loreLine.contains("Rested")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.RESTED);
			}
			else if (loreLine.contains("Shift")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.SHIFT);
			}
			else if (loreLine.contains("Shroomy")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.SHROOMY);
			}
			else if (loreLine.contains("Shroud")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.SHROUD);
			}
			else if (loreLine.contains("Sow")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.SOW);
			}
			else if (loreLine.contains("Starve")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.STARVE);
			}
			else if (loreLine.contains("Telepathy")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.TELEPATHY);
			}
			else if (loreLine.contains("Thunderbolt")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.THUNDERBOLT);
			}
			else if (loreLine.contains("Venom")) {
				extraExp += 8;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.VENOM);
			}
			else if (loreLine.contains("Absorb")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.ABSORB);
			}
			else if (loreLine.contains("Angelic")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.ANGELIC);
			}
			else if (loreLine.contains("Cleave")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.CLEAVE);
			}
			else if (loreLine.contains("Devastate")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.DEVASTATE);
			}
			else if (loreLine.contains("Diminish")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.DIMINISH);
			}
			else if (loreLine.contains("Executioner")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.EXECUTIONER);
			}
			else if (loreLine.contains("Frozen")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.FROZEN);
			}
			else if (loreLine.contains("Gorge")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.GORGE);
			}
			else if (loreLine.contains("Guardians")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.GUARDIANS);
			}
			else if (loreLine.contains("Jump")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.JUMP);
			}
			else if (loreLine.contains("Magnetize")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.MAGNETIZE);
			}
			else if (loreLine.contains("Nimble")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.NIMBLE);
			}
			else if (loreLine.contains("Obsidianshield")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.OBSIDIANSHIELD);
			}
			else if (loreLine.contains("Pinpoint")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.PINPOINT);
			}
			else if (loreLine.contains("Pokey")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.POKEY);
			}
			else if (loreLine.contains("Retaliate")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.RETALIATE);
			}
			else if (loreLine.contains("Retribution")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.RETRIBUTION);
			}
			else if (loreLine.contains("Skyrocket")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.SKYROCKET);
			}
			else if (loreLine.contains("Thwomp")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.THWOMP);
			}
			else if (loreLine.contains("Turbo")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.TURBO);
			}
			else if (loreLine.contains("Vanquish")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.VANQUISH);
			}
			else if (loreLine.contains("Wisdom")) {
				extraExp += 15;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.WISDOM);
			}
			else if (loreLine.contains("Acrobatics")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.ACROBATICS);
			}
			else if (loreLine.contains("Aerodynamics")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.AERODYNAMICS);
			}
			else if (loreLine.contains("Agility")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.AGILITY);
			}
			else if (loreLine.contains("Annihilation")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.ANNIHILATION);
			}
			else if (loreLine.contains("Bloom")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.BLOOM);
			}
			else if (loreLine.contains("Blossom")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.BLOSSOM);
			}
			else if (loreLine.contains("Demise")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.DEMISE);
			}
			else if (loreLine.contains("Dodge")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.DODGE);
			}
			else if (loreLine.contains("Drunk")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.DRUNK);
			}
			else if (loreLine.contains("Enrage")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.ENRAGE);
			}
			else if (loreLine.contains("Fate")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.FATE);
			}
			else if (loreLine.contains("Fury")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.FURY);
			}
			else if (loreLine.contains("Glory")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.GLORY);
			}
			else if (loreLine.contains("Intellect")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.INTELLECT);
			}
			else if (loreLine.contains("Lifesteal")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.LIFESTEAL);
			}
			else if (loreLine.contains("Lucky")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.LUCKY);
			}
			else if (loreLine.contains("Meteor")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.METEOR);
			}
			else if (loreLine.contains("Puncture")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.PUNCTURE);
			}
			else if (loreLine.contains("Rage")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.RAGE);
			}
			else if (loreLine.contains("Safeguard")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.SAFEGUARD);
			}
			else if (loreLine.contains("Silence")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.SILENCE);
			}
			else if (loreLine.contains("Stalwart")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.STALWART);
			}
			else if (loreLine.contains("Stockpile")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.STOCKPILE);
			}
			else if (loreLine.contains("Voidwalker")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.VOIDWALKER);
			}
			else if (loreLine.contains("Whirlpool")) {
				extraExp += 25;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.WHIRLPOOL);
			}
			else if (loreLine.contains("Blink")) {
				extraExp += 500;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.BLINK);
			}
			else if (loreLine.contains("Overdrive")) {
				extraExp += 500;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.OVERDRIVE);
			}
			else if (loreLine.contains("Titan")) {
				extraExp += 500;
				currentLore.remove(i);
				itemMeta.setLore(currentLore);
				item.setItemMeta(itemMeta);
				item.removeEnchantment(CustomEnchants.TITAN);
			}
		}
		
		return extraExp;
	}
}