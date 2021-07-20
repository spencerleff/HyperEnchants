package me.SpencerLeff.HyperEnchants;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

public class applyCustomEnchantments implements Listener {
	@EventHandler
	public void applyEnchant(InventoryClickEvent e) throws InterruptedException {
		
		if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
		
		//get player, item to get enchant (current), and book to apply (cursor)
        final Player player = (Player) e.getWhoClicked();
        final ItemStack current = e.getCurrentItem();
        final ItemStack cursor = e.getCursor();
        
        //player swaps item in inventory with cursor
        if (!current.getType().equals(Material.AIR) && !cursor.getType().equals(Material.AIR)) {
        	//if the held item (cursor item) is a book
        	if (cursor.getType().equals(Material.BOOK)) {
        		//Get book information
				String bookDisplayName = cursor.getItemMeta().getDisplayName();
				String[] splitBookName = bookDisplayName.split(" ");
				String bookName = splitBookName[0];
				String bookLvlStr = splitBookName[1];
				int bookLvl = Integer.parseInt(bookLvlStr);
				ChatColor enchantColor = ChatColor.GRAY;
				
				//APPLY ENCHANT BASED ON WHAT THE BOOK's NAME IS
				//Aquatic book
				if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Aquatic")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, "aquatic", "Aquatic", "HELMET", enchantColor);
				}
				//Blessed book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Blessed")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, "blessed", "Blessed", "AXE", enchantColor);
				}
				//Confusion book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Confusion")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, "confusion", "Confusion", "AXE", enchantColor);
				}
				//Guillotine book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Guillotine")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, "guillotine", "Guillotine", "MELEE_WEAPON", enchantColor);
				}
				//Nocturnal book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Nocturnal")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, "nocturnal", "Nocturnal", "HELMET", enchantColor);
				}
				//Telepathy book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Telepathy")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, "telepathy", "Telepathy", "PICKAXE", enchantColor);
				}
				//Cleave book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Cleave")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, "cleave", "Cleave", "AXE", enchantColor);
				}
				//Jump book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Jump")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, "jump", "Jump", "BOOTS", enchantColor);
				}
				//Magnetize book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Magnetize")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, "magnetize", "Magnetize", "SWORD", enchantColor);
				}
				//Obsidianshield book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Obsidianshield")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, "obsidianshield", "Obsidianshield", "LEGGINGS", enchantColor);
				}
				//Retribution book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Retribution")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, "retribution", "Retribution", "SHIELD", enchantColor);
				}
				//Skyrocket book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Skyrocket")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, "skyrocket", "Skyrocket", "BOOTS", enchantColor);
				}
				//Thwomp book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Thwomp")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, "thwomp", "Thwomp", "MELEE_WEAPON", enchantColor);
				}
				//Agility book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Agility")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, "agility", "Agility", "BOOTS", enchantColor);
				}
				//Drunk book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Drunk")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, "drunk", "Drunk", "HELMET", enchantColor);
				}
				//Fury book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Fury")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, "fury", "Fury", "SWORD", enchantColor);
				}
				//Lifesteal book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Lifesteal")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, "lifesteal", "Lifesteal", "SWORD", enchantColor);
				}
				//Rage book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Rage")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, "rage", "Rage", "MELEE_WEAPON", enchantColor);
				}
				//Stockpile book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Stockpile")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, "stockpile", "Stockpile", "CHESTPLATE", enchantColor);
				}
        	}
            return;
        }
	}
	
	//Main enchantment apply logic
	public void applyEnchantLogic(Player player, ItemStack current, String bookName, int bookLvl, String enchantment,
								  String enchantmentFirstCAPS, String gearType, ChatColor enchantColor) {
		//verify the gear is the correct item
		boolean isVerified = verifyGear(player, current, gearType);
			if (isVerified == true) {
				//check if the enchant already exists on the gear
				boolean isAlreadyApplied = doesEnchantAlreadyExist(player, current, enchantment);
				if (isAlreadyApplied == true) {
					//enchant level was already GT/EQ the book's level. Don't apply
					int currentEnchantLevel = findCurrentEnchantmentLevel(player, current, enchantment);
					if (currentEnchantLevel >= bookLvl) {
						player.sendMessage(ChatColor.DARK_RED + "Enchant is already on this item!");
						return;
					}
					//enchant level was LT the book's level. remove old lore and reapply new leveled enchant
					else if (currentEnchantLevel < bookLvl) {
						replaceEnchantmentLore(current, player, enchantment, bookLvl, enchantColor);
						player.setItemOnCursor(null);
						player.updateInventory();
						player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
						//add the enchant based on the name
						addTheUnsafeEnchantment(enchantment, current, bookLvl);
						return;
					}
				}
				//enchant was not already applied
				else if (isAlreadyApplied == false) {
					//add lore line, remove book from hand, update player's inventory, play a success sound, and add the enchant
					addEnchantmentLore(current, player, enchantment, bookLvl, enchantColor);	
					player.setItemOnCursor(null);
					player.updateInventory();
					player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
					//add the enchant based on the name
					addTheUnsafeEnchantment(enchantment, current, bookLvl);
					return;
				}
			}
	}
	
	//add the enchantment to the item based on the name
	public void addTheUnsafeEnchantment(String enchantment, ItemStack current, int bookLvl) {
		switch (enchantment) {
		case "aquatic":
			current.addUnsafeEnchantment(BookOpening.AQUATIC, bookLvl);
		case "blessed":
			current.addUnsafeEnchantment(BookOpening.BLESSED, bookLvl);
		case "confusion":
			current.addUnsafeEnchantment(BookOpening.CONFUSION, bookLvl);
		case "guillotine":
			current.addUnsafeEnchantment(BookOpening.GUILLOTINE, bookLvl);
		case "nocturnal":
			current.addUnsafeEnchantment(BookOpening.NOCTURNAL, bookLvl);
		case "telepathy":
			current.addUnsafeEnchantment(BookOpening.TELEPATHY, bookLvl);
		case "cleave":
			current.addUnsafeEnchantment(BookOpening.CLEAVE, bookLvl);
		case "jump":
			current.addUnsafeEnchantment(BookOpening.JUMP, bookLvl);
		case "magnetize":
			current.addUnsafeEnchantment(BookOpening.MAGNETIZE, bookLvl);
		case "obsidianshield":
			current.addUnsafeEnchantment(BookOpening.OBSIDIANSHIELD, 1);
		case "skyrocket":
			current.addUnsafeEnchantment(BookOpening.SKYROCKET, bookLvl);
		case "thwomp":
			current.addUnsafeEnchantment(BookOpening.THWOMP, bookLvl);
		case "retribution":
			current.addUnsafeEnchantment(BookOpening.RETRIBUTION, bookLvl);
		case "agility":
			current.addUnsafeEnchantment(BookOpening.AGILITY, bookLvl);
		case "drunk":
			current.addUnsafeEnchantment(BookOpening.DRUNK, bookLvl);
		case "fury":
			current.addUnsafeEnchantment(BookOpening.FURY, bookLvl);
		case "lifesteal":
			current.addUnsafeEnchantment(BookOpening.LIFESTEAL, bookLvl);
		case "rage":
			current.addUnsafeEnchantment(BookOpening.RAGE, bookLvl);
		case "stockpile":
			current.addUnsafeEnchantment(BookOpening.STOCKPILE, bookLvl);
		}
	}
	
	//Ensure that book was placed on correct gear-type
	public boolean verifyGear(Player player, ItemStack current, String gear) {
		if (gear == "AXE") {
			if (current.getType().equals(Material.WOODEN_AXE) || current.getType().equals(Material.STONE_AXE) || current.getType().equals(Material.GOLDEN_AXE) ||
				current.getType().equals(Material.IRON_AXE) || current.getType().equals(Material.DIAMOND_AXE) || current.getType().equals(Material.NETHERITE_AXE)) {
				return true;
			}
		}
		else if (gear == "SWORD") {
			if (current.getType().equals(Material.WOODEN_SWORD) || current.getType().equals(Material.STONE_SWORD) || current.getType().equals(Material.GOLDEN_SWORD) ||
				current.getType().equals(Material.IRON_SWORD) || current.getType().equals(Material.DIAMOND_SWORD) || current.getType().equals(Material.NETHERITE_SWORD)) {
				return true;
			}
		}
		else if (gear == "MELEE_WEAPON") {
			if (current.getType().equals(Material.WOODEN_AXE) || current.getType().equals(Material.STONE_AXE) || current.getType().equals(Material.GOLDEN_AXE) ||
				current.getType().equals(Material.IRON_AXE) || current.getType().equals(Material.DIAMOND_AXE) || current.getType().equals(Material.NETHERITE_AXE) ||
				current.getType().equals(Material.WOODEN_SWORD) || current.getType().equals(Material.STONE_SWORD) || current.getType().equals(Material.GOLDEN_SWORD) ||
				current.getType().equals(Material.IRON_SWORD) || current.getType().equals(Material.DIAMOND_SWORD) || current.getType().equals(Material.NETHERITE_SWORD)) {
				return true;
			}
		}
		else if (gear == "BOW") {
			if (current.getType().equals(Material.BOW)) {
				return true;
			}
		}
		else if (gear == "CROSSBOW") {
			if (current.getType().equals(Material.CROSSBOW)) {
				return true;
			}
		}
		else if (gear == "PICKAXE") {
			if (current.getType().equals(Material.WOODEN_PICKAXE) || current.getType().equals(Material.STONE_PICKAXE) || current.getType().equals(Material.GOLDEN_PICKAXE) || 
				current.getType().equals(Material.IRON_PICKAXE) || current.getType().equals(Material.DIAMOND_PICKAXE) || current.getType().equals(Material.NETHERITE_PICKAXE)) {
				return true;
			}
		}
		else if (gear == "ARMOR") {
			if (current.getType().equals(Material.LEATHER_HELMET) || current.getType().equals(Material.CHAINMAIL_HELMET) || current.getType().equals(Material.GOLDEN_HELMET) ||
				current.getType().equals(Material.IRON_HELMET) || current.getType().equals(Material.DIAMOND_HELMET) || current.getType().equals(Material.NETHERITE_HELMET) ||
				current.getType().equals(Material.LEATHER_CHESTPLATE) || current.getType().equals(Material.CHAINMAIL_CHESTPLATE) || current.getType().equals(Material.GOLDEN_CHESTPLATE) ||
				current.getType().equals(Material.IRON_CHESTPLATE) || current.getType().equals(Material.DIAMOND_CHESTPLATE) || current.getType().equals(Material.NETHERITE_CHESTPLATE) ||
				current.getType().equals(Material.LEATHER_LEGGINGS) || current.getType().equals(Material.CHAINMAIL_LEGGINGS) || current.getType().equals(Material.GOLDEN_LEGGINGS) ||
				current.getType().equals(Material.IRON_LEGGINGS) || current.getType().equals(Material.DIAMOND_LEGGINGS) || current.getType().equals(Material.NETHERITE_LEGGINGS) ||
				current.getType().equals(Material.LEATHER_BOOTS) || current.getType().equals(Material.CHAINMAIL_BOOTS) || current.getType().equals(Material.GOLDEN_BOOTS) ||
				current.getType().equals(Material.IRON_BOOTS) || current.getType().equals(Material.DIAMOND_BOOTS) || current.getType().equals(Material.NETHERITE_BOOTS)) {
				return true;
			}
		}
		else if (gear == "HELMET") {
			if (current.getType().equals(Material.LEATHER_HELMET) || current.getType().equals(Material.CHAINMAIL_HELMET) || current.getType().equals(Material.GOLDEN_HELMET) ||
				current.getType().equals(Material.IRON_HELMET) || current.getType().equals(Material.DIAMOND_HELMET) || current.getType().equals(Material.NETHERITE_HELMET)) {
				return true;
			}
		}
		else if (gear == "CHESTPLATE") {
			if (current.getType().equals(Material.LEATHER_CHESTPLATE) || current.getType().equals(Material.CHAINMAIL_CHESTPLATE) || current.getType().equals(Material.GOLDEN_CHESTPLATE) ||
				current.getType().equals(Material.IRON_CHESTPLATE) || current.getType().equals(Material.DIAMOND_CHESTPLATE) || current.getType().equals(Material.NETHERITE_CHESTPLATE)) {
				return true;
			}
		}
		else if (gear == "LEGGINGS") {
			if (current.getType().equals(Material.LEATHER_LEGGINGS) || current.getType().equals(Material.CHAINMAIL_LEGGINGS) || current.getType().equals(Material.GOLDEN_LEGGINGS) ||
				current.getType().equals(Material.IRON_LEGGINGS) || current.getType().equals(Material.DIAMOND_LEGGINGS) || current.getType().equals(Material.NETHERITE_LEGGINGS)) {
				return true;
			}
		}
		else if (gear == "BOOTS") {
			if (current.getType().equals(Material.LEATHER_BOOTS) || current.getType().equals(Material.CHAINMAIL_BOOTS) || current.getType().equals(Material.GOLDEN_BOOTS) ||
					current.getType().equals(Material.IRON_BOOTS) || current.getType().equals(Material.DIAMOND_BOOTS) || current.getType().equals(Material.NETHERITE_BOOTS)) {
					return true;
				}
		}
		else if (gear == "SHIELD") {
			if (current.getType().equals(Material.SHIELD)) {
				return true;
			}
		}
		player.sendMessage(ChatColor.DARK_RED + "Invalid gear type!");
		return false;
	}
	
	//Check if an enchantment already exists on a piece of gear
	public boolean doesEnchantAlreadyExist(Player player, ItemStack current, String enchantment) {
		if (!current.getItemMeta().hasLore()) {
			return false;
		}
		else {
			List<String> currentLore = current.getItemMeta().getLore();
			for (int i = 0; i < currentLore.size(); i++) {
				String loreLine = currentLore.get(i);
				if (loreLine.contains(enchantment)) {
					return true;
				}
			}
		}
		return false;
	}
	
	//Find the level of an enchantment on an item
	public int findCurrentEnchantmentLevel(Player player, ItemStack current, String enchantment) {
		int eLevel = 0;
		List<String> currentLore = current.getItemMeta().getLore();
		for (int i = 0; i < currentLore.size(); i++) {
			String loreLine = currentLore.get(i);
			if (loreLine.contains(enchantment)) {
				String[] splitLoreLine = loreLine.split(" ");
				String eLevelStr = splitLoreLine[1];
				eLevel = Integer.parseInt(eLevelStr);
			}
		}
		return eLevel;
	}
	
	//Delete the lore line (applying a book of an enchant with a higher level than what's currently on gear)
	public void replaceEnchantmentLore(ItemStack current, Player player, String enchantment, int eLevel, ChatColor enchantColor) {
		ItemMeta currentMeta = current.getItemMeta();
		List<String> currentLore = current.getItemMeta().getLore();
		for (int i = 0; i < currentLore.size(); i++) {
			String loreLine = currentLore.get(i);
			if (loreLine.contains(enchantment)) {
				String[] splitLoreLine = loreLine.split(" ");
				splitLoreLine[1] = String.valueOf(eLevel);
				loreLine = (splitLoreLine[0] + " " + splitLoreLine[1]);
				currentLore.remove(i);
				currentLore.add(enchantColor + loreLine);
			}
		}
		
		currentMeta.setLore(currentLore);
		current.setItemMeta(currentMeta);
		player.sendMessage(ChatColor.GREEN + "Successfully added " + enchantment + " " + eLevel);
	}
	
	//Add lore of passed in enchantment to the item
	public void addEnchantmentLore(ItemStack current, Player player, String enchantment, int eLevel, ChatColor enchantColor) {
		ItemMeta currentMeta = current.getItemMeta();
		List<String> currentLore = new ArrayList<String>();
		currentLore.add(enchantColor + enchantment + " " + eLevel);
		if (currentMeta.hasLore()) {
			for (String l : currentMeta.getLore()) {
				currentLore.add(l);
			}
		}
		
		currentMeta.setLore(currentLore);
		current.setItemMeta(currentMeta);
		player.sendMessage(ChatColor.GREEN + "Successfully added " + enchantment + " " + eLevel);
	}
}
