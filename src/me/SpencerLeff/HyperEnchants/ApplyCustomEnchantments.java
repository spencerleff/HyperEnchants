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

public class ApplyCustomEnchantments implements Listener {
	@EventHandler
	public void applyEnchant(InventoryClickEvent e) throws InterruptedException {
		if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getCursor() == null) {
			return;
		}
		
		//get player, item to get enchant (current), and book to apply (cursor)
        final Player player = (Player) e.getWhoClicked();
        final ItemStack current = e.getCurrentItem();
        final ItemStack cursor = e.getCursor();
        int rawSlot = e.getRawSlot();
        
        //player swaps item in inventory with cursor
        if (!current.getType().equals(Material.AIR) && !cursor.getType().equals(Material.AIR)) {
        	//if the held item (cursor item) is a book
        	if (cursor.getType().equals(Material.BOOK)) {
        		//Get book information
				String bookDisplayName = cursor.getItemMeta().getDisplayName();
				String[] splitBookName = bookDisplayName.split(" ");
				if (splitBookName.length != 2) {
					return;
				}
				String bookName = splitBookName[0];
				String bookLvlStr = splitBookName[1];
				int bookLvl = getLevelFromNumeral(bookLvlStr);
				ChatColor enchantColor = ChatColor.GRAY;
				
				//COMMON ENCHANTS
				//Aquatic book
				if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Aquatic")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Aquatic", "HELMET", enchantColor, rawSlot);
				}
				//Blessed book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Blessed")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Blessed", "AXE", enchantColor, rawSlot);
				}
				//Confusion book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Confusion")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Confusion", "AXE", enchantColor, rawSlot);
				}
				//Crystallize book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Crystallize")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Crystallize", "ARMOR", enchantColor, rawSlot);
				}
				//Geomancy book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Geomancy")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Geomancy", "PICKAXE", enchantColor, rawSlot);
				}
				//Goomba book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Goomba")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Goomba", "BOOTS", enchantColor, rawSlot);
				}
				//Guillotine book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Guillotine")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Guillotine", "SwordAxeTrident", enchantColor, rawSlot);
				}
				//Nocturnal book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Nocturnal")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Nocturnal", "HELMET", enchantColor, rawSlot);
				}
				//Pillage book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Pillage")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Pillage", "CROSSBOW", enchantColor, rawSlot);
				}
				//Plunge book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Plunge")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Plunge", "TRIDENT", enchantColor, rawSlot);
				}
				//Poseidon book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Poseidon")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Poseidon", "TRIDENT", enchantColor, rawSlot);
				}
				//Reflect book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Reflect")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Reflect", "SHIELD", enchantColor, rawSlot);
				}
				//Rested book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Rested")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Rested", "HELMET", enchantColor, rawSlot);
				}
				//Shift book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Shift")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Shift", "BOOTS", enchantColor, rawSlot);
				}
				//Shroomy book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Shroomy")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Shroomy", "LEGGINGS", enchantColor, rawSlot);
				}
				//Shroud book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Shroud")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Shroud", "TRIDENT", enchantColor, rawSlot);
				}
				//Sow book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Sow")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Sow", "CHESTPLATE", enchantColor, rawSlot);
				}
				//Starve book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Starve")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Starve", "RANGED", enchantColor, rawSlot);
				}
				//Telepathy book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Telepathy")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Telepathy", "PICKAXE", enchantColor, rawSlot);
				}
				//Telepathy book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Thunderbolt")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Thunderbolt", "RANGED", enchantColor, rawSlot);
				}
				//Venom book
				else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Venom")) {
					enchantColor = ChatColor.WHITE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Venom", "SWORD", enchantColor, rawSlot);
				}
				
				//RARE ENCHANTS
				//Absorb book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Absorb")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Absorb", "SHIELD", enchantColor, rawSlot);
				}
				//Angelic book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Angelic")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Angelic", "LEGGINGS", enchantColor, rawSlot);
				}
				//Cleave book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Cleave")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Cleave", "AXE", enchantColor, rawSlot);
				}
				//Devastate book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Devastate")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Devastate", "RANGED", enchantColor, rawSlot);
				}
				//Diminish book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Diminish")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Diminish", "TRIDENT", enchantColor, rawSlot);
				}
				//Executioner book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Executioner")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Executioner", "SWORD", enchantColor, rawSlot);
				}
				//Frozen book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Frozen")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Frozen", "HELMET", enchantColor, rawSlot);
				}
				//Gorge book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Gorge")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Gorge", "HELMET", enchantColor, rawSlot);
				}
				//Guardians book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Guardians")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Guardians", "LEGGINGS", enchantColor, rawSlot);
				}
				//Jump book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Jump")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Jump", "BOOTS", enchantColor, rawSlot);
				}
				//Magnetize book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Magnetize")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Magnetize", "SWORD", enchantColor, rawSlot);
				}
				//Nimble book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Nimble")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Nimble", "BOW", enchantColor, rawSlot);
				}
				//Obsidianshield book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Obsidianshield")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Obsidianshield", "LEGGINGS", enchantColor, rawSlot);
				}
				//Pinpoint book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Pinpoint")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Pinpoint", "TRIDENT", enchantColor, rawSlot);
				}
				//Pokey book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Pokey")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Pokey", "CHESTPLATE", enchantColor, rawSlot);
				}
				//Retaliate book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Retaliate")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Retaliate", "CHESTPLATE", enchantColor, rawSlot);
				}
				//Retribution book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Retribution")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr,"Retribution", "SHIELD", enchantColor, rawSlot);
				}
				//Skyrocket book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Skyrocket")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Skyrocket", "BOOTS", enchantColor, rawSlot);
				}
				//Thwomp book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Thwomp")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Thwomp", "MELEE_WEAPON", enchantColor, rawSlot);
				}
				//Turbo book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Turbo")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Turbo", "PICKAXE", enchantColor, rawSlot);
				}
				//Vanquish book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Vanquish")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Vanquish", "PICKAXE", enchantColor, rawSlot);
				}
				//Wisdom book
				else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Wisdom")) {
					enchantColor = ChatColor.AQUA;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Wisdom", "AXE", enchantColor, rawSlot);
				}
				
				//LEGENDARY ENCHANTS
				//ACROBATICS book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Acrobatics")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Acrobatics", "TRIDENT", enchantColor, rawSlot);
				}
				//AERODYNAMICS book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Aerodynamics")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Aerodynamics", "TRIDENT", enchantColor, rawSlot);
				}
				//Agility book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Agility")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Agility", "BOOTS", enchantColor, rawSlot);
				}
				//Annihilation book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Annihilation")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Annihilation", "CHESTPLATE", enchantColor, rawSlot);
				}
				//Bloom book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Bloom")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Bloom", "BOW", enchantColor, rawSlot);
				}
				//Blossom book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Blossom")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Blossom", "BOOTS", enchantColor, rawSlot);
				}
				//Demise book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Demise")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Demise", "TRIDENT", enchantColor, rawSlot);
				}
				//Drunk book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Drunk")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Drunk", "HELMET", enchantColor, rawSlot);
				}
				//Dodge book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Dodge")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Dodge", "LEGGINGS", enchantColor, rawSlot);
				}
				//Enrage book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Enrage")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Enrage", "AXE", enchantColor, rawSlot);
				}
				//Fate book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Fate")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Fate", "PICKAXE", enchantColor, rawSlot);
				}
				//Fury book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Fury")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Fury", "SWORD", enchantColor, rawSlot);
				}
				//Glory book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Glory")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Glory", "SHIELD", enchantColor, rawSlot);
				}
				//Intellect book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Intellect")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Intellect", "SWORD", enchantColor, rawSlot);
				}
				//Lifesteal book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Lifesteal")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Lifesteal", "SWORD", enchantColor, rawSlot);
				}
				//Lucky book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Lucky")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Lucky", "ARMOR", enchantColor, rawSlot);
				}
				//Meteor book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Meteor")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Meteor", "RANGED", enchantColor, rawSlot);
				}
				//Puncture book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Puncture")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Puncture", "CROSSBOW", enchantColor, rawSlot);
				}
				//Rage book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Rage")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Rage", "MELEE_WEAPON", enchantColor, rawSlot);
				}
				//Safeguard book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Safeguard")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Safeguard", "SHIELD", enchantColor, rawSlot);
				}
				//Silence book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Silence")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Silence", "AXE", enchantColor, rawSlot);
				}
				//Stalwart book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Stalwart")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Stalwart", "CHESTPLATE", enchantColor, rawSlot);
				}
				//Stockpile book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Stockpile")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Stockpile", "CHESTPLATE", enchantColor, rawSlot);
				}
				//Voidwalker book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Voidwalker")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Voidwalker", "HELMET", enchantColor, rawSlot);
				}
				//Whirlpool book
				else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Whirlpool")) {
					enchantColor = ChatColor.GOLD;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Whirlpool", "LEGGINGS", enchantColor, rawSlot);
				}
				//Blink book
				else if (bookName.equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Blink")) {
					enchantColor = ChatColor.DARK_PURPLE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Blink", "BOOTS", enchantColor, rawSlot);
				}
				//Overdrive book
				else if (bookName.equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Overdrive")) {
					enchantColor = ChatColor.DARK_PURPLE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Overdrive", "BOW", enchantColor, rawSlot);
				}
				//Titan book
				else if (bookName.equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Titan")) {
					enchantColor = ChatColor.DARK_PURPLE;
					applyEnchantLogic(player, current, bookName, bookLvl, bookLvlStr, "Titan", "CHESTPLATE", enchantColor, rawSlot);
				}
        	}
            return;
        }
	}
	
	//Main enchantment apply logic
	public void applyEnchantLogic(Player player, ItemStack current, String bookName, int bookLvl, String bookLvlStr, 
								  String enchantment, String gearType, ChatColor enchantColor, int rawSlot) {
		//check if the item is another custom enchant book
		if (current.getType().equals(Material.BOOK)) {
			return;
		}
		//verify the gear is the correct item
		boolean isVerified = verifyGear(player, current, gearType);
		if (isVerified == true) {
			//check if the enchant already exists on the gear
			boolean isAlreadyApplied = doesEnchantAlreadyExist(player, current, enchantment);
			if (isAlreadyApplied == true) {
				//enchant level was already GT/EQ the book's level. Don't apply
				int currentEnchantLevel = findCurrentEnchantmentLevel(player, current, enchantment);
				if (currentEnchantLevel >= bookLvl) {
					player.sendMessage(ChatColor.RED + "Enchant is already on this item!");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
					return;
				}
				//enchant level was LT the book's level. remove old lore and reapply new leveled enchant
				else if (currentEnchantLevel < bookLvl) {
					replaceEnchantmentLore(current, player, enchantment, bookLvlStr, enchantColor);
					player.getOpenInventory().setItem(rawSlot, new ItemStack(Material.AIR));
					player.setItemOnCursor(current);
					player.updateInventory();
					ItemStack item = player.getItemOnCursor();
					player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
					addTheUnsafeEnchantment(enchantment, item, bookLvl);
					return;
				}
			}
			//enchant was not already applied
			else if (isAlreadyApplied == false) {
				//add lore line, remove book from hand, update player's inventory, play a success sound, and add the enchant
				addEnchantmentLore(current, player, enchantment, bookLvlStr, enchantColor);
				player.getOpenInventory().setItem(rawSlot, new ItemStack(Material.AIR));
				player.setItemOnCursor(current);
				player.updateInventory();
				ItemStack item = player.getItemOnCursor();
				player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
				addTheUnsafeEnchantment(enchantment, item, bookLvl);
				return;
			}
		}
	}
	
	//add the enchantment to the item based on the name
	public void addTheUnsafeEnchantment(String enchantment, ItemStack current, int bookLvl) {
		if (enchantment == "Aquatic") {
			current.addUnsafeEnchantment(CustomEnchants.AQUATIC, bookLvl);
		}
		else if (enchantment == "Blessed") {
			current.addUnsafeEnchantment(CustomEnchants.BLESSED, bookLvl);
		}
		else if (enchantment == "Confusion") {
			current.addUnsafeEnchantment(CustomEnchants.CONFUSION, bookLvl);
		}
		else if (enchantment == "Crystallize") {
			current.addUnsafeEnchantment(CustomEnchants.CRYSTALLIZE, bookLvl);
		}
		else if (enchantment == "Geomancy") {
			current.addUnsafeEnchantment(CustomEnchants.GEOMANCY, bookLvl);
		}
		else if (enchantment == "Goomba") {
			current.addUnsafeEnchantment(CustomEnchants.GOOMBA, bookLvl);
		}
		else if (enchantment == "Guillotine") {
			current.addUnsafeEnchantment(CustomEnchants.GUILLOTINE, bookLvl);
		}
		else if (enchantment == "Nocturnal") {
			current.addUnsafeEnchantment(CustomEnchants.NOCTURNAL, bookLvl);
		}
		else if (enchantment == "Pillage") {
			current.addUnsafeEnchantment(CustomEnchants.PILLAGE, bookLvl);
		}
		else if (enchantment == "Plunge") {
			current.addUnsafeEnchantment(CustomEnchants.PLUNGE, bookLvl);
		}
		else if (enchantment == "Poseidon") {
			current.addUnsafeEnchantment(CustomEnchants.POSEIDON, bookLvl);
		}
		else if (enchantment == "Reflect") {
			current.addUnsafeEnchantment(CustomEnchants.REFLECT, bookLvl);
		}
		else if (enchantment == "Rested") {
			current.addUnsafeEnchantment(CustomEnchants.RESTED, bookLvl);
		}
		else if (enchantment == "Shift") {
			current.addUnsafeEnchantment(CustomEnchants.SHIFT, bookLvl);
		}
		else if (enchantment == "Shroomy") {
			current.addUnsafeEnchantment(CustomEnchants.SHROOMY, bookLvl);
		}
		else if (enchantment == "Shroud") {
			current.addUnsafeEnchantment(CustomEnchants.SHROUD, bookLvl);
		}
		else if (enchantment == "Sow") {
			current.addUnsafeEnchantment(CustomEnchants.SOW, bookLvl);
		}
		else if (enchantment == "Starve") {
			current.addUnsafeEnchantment(CustomEnchants.STARVE, bookLvl);
		}
		else if (enchantment == "Telepathy") {
			current.addUnsafeEnchantment(CustomEnchants.TELEPATHY, bookLvl);
		}
		else if (enchantment == "Thunderbolt") {
			current.addUnsafeEnchantment(CustomEnchants.THUNDERBOLT, bookLvl);
		}
		else if (enchantment == "Venom") {
			current.addUnsafeEnchantment(CustomEnchants.VENOM, bookLvl);
		}
		else if (enchantment == "Absorb") {
			current.addUnsafeEnchantment(CustomEnchants.ABSORB, bookLvl);
		}
		else if (enchantment == "Angelic") {
			current.addUnsafeEnchantment(CustomEnchants.ANGELIC, bookLvl);
		}
		else if (enchantment == "Cleave") {
			current.addUnsafeEnchantment(CustomEnchants.CLEAVE, bookLvl);
		}
		else if (enchantment == "Devastate") {
			current.addUnsafeEnchantment(CustomEnchants.DEVASTATE, bookLvl);
		}
		else if (enchantment == "Diminish") {
			current.addUnsafeEnchantment(CustomEnchants.DIMINISH, bookLvl);
		}
		else if (enchantment == "Executioner") {
			current.addUnsafeEnchantment(CustomEnchants.EXECUTIONER, bookLvl);
		}
		else if (enchantment == "Frozen") {
			current.addUnsafeEnchantment(CustomEnchants.FROZEN, bookLvl);
		}
		else if (enchantment == "Gorge") {
			current.addUnsafeEnchantment(CustomEnchants.GORGE, bookLvl);
		}
		else if (enchantment == "Guardians") {
			current.addUnsafeEnchantment(CustomEnchants.GUARDIANS, bookLvl);
		}
		else if (enchantment == "Jump") {
			current.addUnsafeEnchantment(CustomEnchants.JUMP, bookLvl);
		}
		else if (enchantment == "Magnetize") {
			current.addUnsafeEnchantment(CustomEnchants.MAGNETIZE, bookLvl);
		}
		else if (enchantment == "Nimble") {
			current.addUnsafeEnchantment(CustomEnchants.NIMBLE, bookLvl);
		}
		else if (enchantment == "Obsidianshield") {
			current.addUnsafeEnchantment(CustomEnchants.OBSIDIANSHIELD, bookLvl);
		}
		else if (enchantment == "Pinpoint") {
			current.addUnsafeEnchantment(CustomEnchants.PINPOINT, bookLvl);
		}
		else if (enchantment == "Pokey") {
			current.addUnsafeEnchantment(CustomEnchants.POKEY, bookLvl);
		}
		else if (enchantment == "Retaliate") {
			current.addUnsafeEnchantment(CustomEnchants.RETALIATE, bookLvl);
		}
		else if (enchantment == "Retribution") {
			current.addUnsafeEnchantment(CustomEnchants.RETRIBUTION, bookLvl);
		}
		else if (enchantment == "Skyrocket") {
			current.addUnsafeEnchantment(CustomEnchants.SKYROCKET, bookLvl);
		}
		else if (enchantment == "Thwomp") {
			current.addUnsafeEnchantment(CustomEnchants.THWOMP, bookLvl);
		}
		else if (enchantment == "Turbo") {
			current.addUnsafeEnchantment(CustomEnchants.TURBO, bookLvl);
		}
		else if (enchantment == "Vanquish") {
			current.addUnsafeEnchantment(CustomEnchants.VANQUISH, bookLvl);
		}
		else if (enchantment == "Wisdom") {
			current.addUnsafeEnchantment(CustomEnchants.WISDOM, bookLvl);
		}
		else if (enchantment == "Acrobatics") {
			current.addUnsafeEnchantment(CustomEnchants.ACROBATICS, bookLvl);
		}
		else if (enchantment == "Aerodynamics") {
			current.addUnsafeEnchantment(CustomEnchants.AERODYNAMICS, bookLvl);
		}
		else if (enchantment == "Agility") {
			current.addUnsafeEnchantment(CustomEnchants.AGILITY, bookLvl);
		}
		else if (enchantment == "Annihilation") {
			current.addUnsafeEnchantment(CustomEnchants.ANNIHILATION, bookLvl);
		}
		else if (enchantment == "Bloom") {
			current.addUnsafeEnchantment(CustomEnchants.BLOOM, bookLvl);
		}
		else if (enchantment == "Blossom") {
			current.addUnsafeEnchantment(CustomEnchants.BLOSSOM, bookLvl);
		}
		else if (enchantment == "Demise") {
			current.addUnsafeEnchantment(CustomEnchants.DEMISE, bookLvl);
		}
		else if (enchantment == "Drunk") {
			current.addUnsafeEnchantment(CustomEnchants.DRUNK, bookLvl);
		}
		else if (enchantment == "Dodge") {
			current.addUnsafeEnchantment(CustomEnchants.DODGE, bookLvl);
		}
		else if (enchantment == "Enrage") {
			current.addUnsafeEnchantment(CustomEnchants.ENRAGE, bookLvl);
		}
		else if (enchantment == "Fate") {
			current.addUnsafeEnchantment(CustomEnchants.FATE, bookLvl);
		}
		else if (enchantment == "Fury") {
			current.addUnsafeEnchantment(CustomEnchants.FURY, bookLvl);
		}
		else if (enchantment == "Glory") {
			current.addUnsafeEnchantment(CustomEnchants.GLORY, bookLvl);
		}
		else if (enchantment == "Intellect") {
			current.addUnsafeEnchantment(CustomEnchants.INTELLECT, bookLvl);
		}
		else if (enchantment == "Lifesteal") {
			current.addUnsafeEnchantment(CustomEnchants.LIFESTEAL, bookLvl);
		}
		else if (enchantment == "Lucky") {
			current.addUnsafeEnchantment(CustomEnchants.LUCKY, bookLvl);
		}
		else if (enchantment == "Meteor") {
			current.addUnsafeEnchantment(CustomEnchants.METEOR, bookLvl);
		}
		else if (enchantment == "Puncture") {
			current.addUnsafeEnchantment(CustomEnchants.PUNCTURE, bookLvl);
		}
		else if (enchantment == "Rage") {
			current.addUnsafeEnchantment(CustomEnchants.RAGE, bookLvl);
		}
		else if (enchantment == "Safeguard") {
			current.addUnsafeEnchantment(CustomEnchants.SAFEGUARD, bookLvl);
		}
		else if (enchantment == "Silence") {
			current.addUnsafeEnchantment(CustomEnchants.SILENCE, bookLvl);
		}
		else if (enchantment == "Stalwart") {
			current.addUnsafeEnchantment(CustomEnchants.STALWART, bookLvl);
		}
		else if (enchantment == "Stockpile") {
			current.addUnsafeEnchantment(CustomEnchants.STOCKPILE, bookLvl);
		}
		else if (enchantment == "Voidwalker") {
			current.addUnsafeEnchantment(CustomEnchants.VOIDWALKER, bookLvl);
		}
		else if (enchantment == "Whirlpool") {
			current.addUnsafeEnchantment(CustomEnchants.WHIRLPOOL, bookLvl);
		}
		else if (enchantment == "Blink") {
			current.addUnsafeEnchantment(CustomEnchants.BLINK, bookLvl);
		}
		else if (enchantment == "Overdrive") {
			current.addUnsafeEnchantment(CustomEnchants.OVERDRIVE, bookLvl);
		}
		else if (enchantment == "Titan") {
			current.addUnsafeEnchantment(CustomEnchants.TITAN, bookLvl);
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
		else if (gear == "RANGED") {
			if (current.getType().equals(Material.BOW) || current.getType().equals(Material.CROSSBOW)) {
				return true;
			}
		}
		else if (gear == "TRIDENT") {
			if (current.getType().equals(Material.TRIDENT)) {
				return true;
			}
		}
		else if (gear == "SwordAxeTrident") {
			if (current.getType().equals(Material.WOODEN_AXE) || current.getType().equals(Material.STONE_AXE) || current.getType().equals(Material.GOLDEN_AXE) ||
					current.getType().equals(Material.IRON_AXE) || current.getType().equals(Material.DIAMOND_AXE) || current.getType().equals(Material.NETHERITE_AXE) ||
					current.getType().equals(Material.WOODEN_SWORD) || current.getType().equals(Material.STONE_SWORD) || current.getType().equals(Material.GOLDEN_SWORD) ||
					current.getType().equals(Material.IRON_SWORD) || current.getType().equals(Material.DIAMOND_SWORD) || current.getType().equals(Material.NETHERITE_SWORD) ||
					current.getType().equals(Material.TRIDENT)) {
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
		player.sendMessage(ChatColor.RED + "Invalid gear type!");
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
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
				eLevel = getLevelFromNumeral(eLevelStr);
			}
		}
		return eLevel;
	}
	
	//Delete the lore line (applying a book of an enchant with a higher level than what's currently on gear)
	public void replaceEnchantmentLore(ItemStack current, Player player, String enchantment, String eLevelStr, ChatColor enchantColor) {
		ItemMeta currentMeta = current.getItemMeta();
		List<String> currentLore = current.getItemMeta().getLore();
		for (int i = 0; i < currentLore.size(); i++) {
			String loreLine = currentLore.get(i);
			if (loreLine.contains(enchantment)) {
				String[] splitLoreLine = loreLine.split(" ");
				splitLoreLine[1] = String.valueOf(eLevelStr);
				loreLine = (splitLoreLine[0] + " " + splitLoreLine[1]);
				currentLore.remove(i);
				currentLore.add(enchantColor + loreLine);
			}
		}
		
		currentMeta.setLore(currentLore);
		current.setItemMeta(currentMeta);
		player.sendMessage(ChatColor.GREEN + "Applied " + enchantColor + ChatColor.BOLD + enchantment + " " + eLevelStr);
	}
	
	//Add lore of passed in enchantment to the item
	public void addEnchantmentLore(ItemStack current, Player player, String enchantment, String eLevelStr, ChatColor enchantColor) {
		ItemMeta currentMeta = current.getItemMeta();
		List<String> currentLore = new ArrayList<String>();
		currentLore.add(enchantColor + enchantment + " " + eLevelStr);
		if (currentMeta.hasLore()) {
			for (String l : currentMeta.getLore()) {
				currentLore.add(l);
			}
		}
		
		currentMeta.setLore(currentLore);
		current.setItemMeta(currentMeta);
		player.sendMessage(ChatColor.GREEN + "Applied " + enchantColor + ChatColor.BOLD + enchantment + " " + eLevelStr);
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