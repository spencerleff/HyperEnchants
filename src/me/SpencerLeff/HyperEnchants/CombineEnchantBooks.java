package me.SpencerLeff.HyperEnchants;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.Sound;

public class CombineEnchantBooks implements Listener {
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
        		//if the current item (inventory item) is a book
        		if (current.getType().equals(Material.BOOK)) {
        			if (!cursor.hasItemMeta()) {
        				return;
        			}
        			if (!current.hasItemMeta()) {
        				return;
        			}
        			String cursorBookDisplayName = cursor.getItemMeta().getDisplayName();
        			String[] cursorSplitBookName = cursorBookDisplayName.split(" ");
    				if (cursorSplitBookName.length != 2) {
    					return;
    				}
    				String cursorBookName = cursorSplitBookName[0];
    				String cursorBookLvlStr = cursorSplitBookName[1];
    				int cursorBookLvl = getLevelFromNumeral(cursorBookLvlStr);
    				
        			String currentBookDisplayName = current.getItemMeta().getDisplayName();
        			String[] currentSplitBookName = currentBookDisplayName.split(" ");
    				if (currentSplitBookName.length != 2) {
    					return;
    				}
    				String currentBookName = currentSplitBookName[0];
    				String currentBookLvlStr = currentSplitBookName[1];
    				int currentBookLvl = getLevelFromNumeral(currentBookLvlStr);
    				
    				if (cursorBookName.equals(currentBookName) && cursorBookLvl == currentBookLvl) {
    					boolean isMaxLevel = checkMaxLevel(cursorBookName, cursorBookLvl);
    					if (!isMaxLevel) {
    						int newLevel = currentBookLvl + 1;
    						String rNumeral = getRomanNumeral(newLevel);
    						
    						ItemMeta currentMeta = current.getItemMeta();
    						currentMeta.setDisplayName(currentBookName + " " + rNumeral);
    						current.setItemMeta(currentMeta);
    						
    						player.getOpenInventory().setItem(rawSlot, new ItemStack(Material.AIR));
    						player.setItemOnCursor(current);
    						
    						player.updateInventory();
    						player.sendMessage(ChatColor.GREEN + "Successfully combined into " + currentBookName + " " + rNumeral);
    						player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
    					}
    					else {
    						player.sendMessage(ChatColor.RED + "Enchantment is already the maximum level");
    						player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
    					}
    				}
        		}
        	}
        }
	}
	
	public boolean checkMaxLevel(String bookName, int bookLvl) {
		boolean isMaxLevel = true;
		if (bookName.contains("Aquatic")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Blessed")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Confusion")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Crystallize")) {
			if (bookLvl >= 4) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Geomancy")) {
			if (bookLvl >= 5) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Goomba")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Guillotine")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Nocturnal")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Pillage")) {
			if (bookLvl >= 2) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Plunge")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Poseidon")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Reflect")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Rested")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Shift")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Shroomy")) {
			if (bookLvl >= 4) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Shroud")) {
			if (bookLvl >= 4) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Sow")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Starve")) {
			if (bookLvl >= 7) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Telepathy")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Thunderbolt")) {
			if (bookLvl >= 2) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Venom")) {
			if (bookLvl >= 2) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Absorb")) {
			if (bookLvl >= 2) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Angelic")) {
			if (bookLvl >= 6) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Cleave")) {
			if (bookLvl >= 5) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Devastate")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Diminish")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Executioner")) {
			if (bookLvl >= 6) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Frozen")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Gorge")) {
			if (bookLvl >= 2) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}	
		}
		else if (bookName.contains("Guardians")) {
			if (bookLvl >= 9) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Jump")) {
			if (bookLvl >= 4) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Magnetize")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Nimble")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Obsidianshield")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Pinpoint")) {
			if (bookLvl >= 2) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Pokey")) {
			if (bookLvl >= 4) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Retaliate")) {
			if (bookLvl >= 5) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Retribution")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Skyrocket")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Thwomp")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Turbo")) {
			if (bookLvl >= 5) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Vanquish")) {
			if (bookLvl >= 2) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Wisdom")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Acrobatics")) {
			if (bookLvl >= 5) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Aerodynamics")) {
			if (bookLvl >= 2) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Agility")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Annihilation")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Bloom")) {
			if (bookLvl >= 5) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Blossom")) {
			if (bookLvl >= 6) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Demise")) {
			if (bookLvl >= 7) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Drunk")) {
			if (bookLvl >= 2) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Dodge")) {
			if (bookLvl >= 4) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Enrage")) {
			if (bookLvl >= 4) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Fate")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Fury")) {
			if (bookLvl >= 6) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Glory")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Intellect")) {
			if (bookLvl >= 4) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Lifesteal")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Lucky")) {
			if (bookLvl >= 10) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Meteor")) {
			if (bookLvl >= 4) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Puncture")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Rage")) {
			if (bookLvl >= 2) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Safeguard")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Silence")) {
			if (bookLvl >= 7) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Stalwart")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Stockpile")) {
			if (bookLvl >= 3) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Voidwalker")) {
			if (bookLvl >= 5) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Whirlpool")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Blink")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Overdrive")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		else if (bookName.contains("Titan")) {
			if (bookLvl >= 1) {
				isMaxLevel = true;
			}
			else {
				isMaxLevel = false;
			}
		}
		return isMaxLevel;
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