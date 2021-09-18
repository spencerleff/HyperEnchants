package me.SpencerLeff.HyperEnchants;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TinkererMenuClickEvent implements Listener {
	public static HashMap<String, Long> tinkererMenuClickCooldown = new HashMap<String, Long>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@EventHandler
	public void tinkererMenuClickEvent(InventoryClickEvent e) throws InterruptedException {
		ItemStack enchantPane = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
		ItemMeta enchantMeta = enchantPane.getItemMeta();
		enchantMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Awaiting custom enchantment book...");
		ArrayList<String> enchantLore = new ArrayList<>();
		enchantLore.add(ChatColor.AQUA + "Add a custom enchantment to this item slot");
		enchantLore.add(ChatColor.AQUA + "and receive some " + ChatColor.GREEN + "EXP " + ChatColor.AQUA + "in return!");
		enchantMeta.setLore(enchantLore);
		enchantPane.setItemMeta(enchantMeta);
		
		ItemStack experiencePane = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta experienceMeta = experiencePane.getItemMeta();
		experienceMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "0 EXP");
		experiencePane.setItemMeta(experienceMeta);
		
		Player player = (Player) e.getWhoClicked();
		if (!e.getView().getTitle().equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Tinkerer")) {
			return;
		}
		
		long currentTime = System.currentTimeMillis();
		long lastMenuClickTime = tinkererMenuClickCooldown.get(player.getName());
		
		if (currentTime < lastMenuClickTime + 200) {
			e.setCancelled(true);
			player.sendMessage(ChatColor.RED + "Please wait longer between clicks!");
			return;
		}
		
		tinkererMenuClickCooldown.put(player.getName(), currentTime);

		e.setCancelled(true);
		if (e.getCurrentItem() == null) {
			return;
		}
		//click in player inventory
		if (e.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
			ItemStack current = e.getCurrentItem();
			if (current.getType().equals(Material.BOOK)) {
				//check for book already in menu
				ItemStack menuItem = e.getInventory().getItem(12);
				if (menuItem.getItemMeta().equals(enchantPane.getItemMeta())) {
					boolean isCustomEnchant = checkIfCustomEnchant(current);
					if (isCustomEnchant) {
						player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.85f, 1);
						
						//Move book to slot 12
						e.getInventory().setItem(12, current);
						e.setCurrentItem(new ItemStack(Material.AIR));
						
						//Set exp bottle amount
						int expAmount = getExpAmount(current);
						ItemStack expBottle = new ItemStack(Material.EXPERIENCE_BOTTLE);
						ItemMeta expBottleMeta = expBottle.getItemMeta();
						expBottleMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + expAmount + " EXP");
						expBottle.setItemMeta(expBottleMeta);
						e.getInventory().setItem(14, expBottle);
					}
					else {
						player.sendMessage(ChatColor.RED + "Item is not a custom enchantment");
						player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 0.7f, 1);
					}
				}
				else {
					player.sendMessage(ChatColor.RED + "Item already in menu");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 0.7f, 1);
				}
			}
			else {
				player.sendMessage(ChatColor.RED + "Item is not a custom enchantment");
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 0.7f, 1);
			}
		}
		//click in tinkerer inventory
		else if (e.getClickedInventory().getType().equals(InventoryType.CHEST)) {
			ItemStack current = e.getCurrentItem();
			if (current.getType().equals(Material.BOOK)) {
				if (player.getInventory().firstEmpty() != -1) {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.85f, 1);
					player.getInventory().addItem(current);
					e.getInventory().setItem(12, enchantPane);
					e.getInventory().setItem(14, experiencePane);
				}
				else {
					player.sendMessage(ChatColor.RED + "Inventory full! dropping item on ground");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 0.85f, 1);
					player.getWorld().dropItem(player.getLocation(), current);
					e.getInventory().setItem(12, enchantPane);
					e.getInventory().setItem(14, experiencePane);
				}
			}
			else if (current.getType().equals(Material.WHITE_STAINED_GLASS_PANE)) {
				//Accept trade
				player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
				String splitExpAmountSpace[] = e.getInventory().getItem(14).getItemMeta().getDisplayName().split(" ");
				if (splitExpAmountSpace.length != 2) {
					return;
				}
				String splitExpAmountColor[] = splitExpAmountSpace[0].split("§");
				if (splitExpAmountColor.length != 3) {
					return;
				}
				String actualExpAmount[] = splitExpAmountColor[2].split("l");
				if (actualExpAmount.length != 2) {
					return;
				}
				int expAmount = Integer.parseInt(actualExpAmount[1]);
				player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "+" + expAmount + " EXP");
				((ExperienceOrb) player.getWorld().spawn(player.getLocation(), (Class)ExperienceOrb.class)).setExperience(expAmount);
				
				e.getInventory().setItem(12, enchantPane);
				e.getInventory().setItem(14, experiencePane);
				//player.closeInventory();
			}
		}
	}
	
	@EventHandler
	public void tinkererCloseMenu(InventoryCloseEvent e) {
		Player player = (Player) e.getPlayer();
		if (!e.getView().getTitle().equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Tinkerer")) {
			return;
		}
		if (e.getInventory().getItem(12).getType().equals(Material.BOOK)) {
			if (player.getInventory().firstEmpty() != -1) {
				player.getInventory().addItem(e.getInventory().getItem(12));
				player.sendMessage(ChatColor.RED + "Returning item");
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 0.7f, 1);
			}
			else {
				player.sendMessage(ChatColor.RED + "Inventory full! dropping item on ground");
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 0.7f, 1);
				player.getWorld().dropItem(player.getLocation(), e.getInventory().getItem(12));
			}
		}
	}
	
	public int getExpAmount(ItemStack item) {
		int exp = 0;
		
		String bookDisplayName = item.getItemMeta().getDisplayName();
		String[] splitBookName = bookDisplayName.split(" ");
		if (splitBookName.length == 2) {
			String bookName = splitBookName[0];
			String bookLvl = splitBookName[1];
			int eLvl = getLevelFromNumeral(bookLvl);
			
			//COMMON ENCHANTS
			//Aquatic book
			if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Aquatic")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Blessed book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Blessed")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Confusion book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Confusion")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Crystallize book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Crystallize")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Geomancy book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Geomancy")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Goomba book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Goomba")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Guillotine book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Guillotine")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Nocturnal book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Nocturnal")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Pillage book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Pillage")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Plunge book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Plunge")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Poseidon book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Poseidon")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Reflect book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Reflect")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Rested book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Rested")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Shift book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Shift")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Shroomy book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Shroomy")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Shroud book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Shroud")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Sow book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Sow")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Starve book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Starve")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Telepathy book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Telepathy")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Telepathy book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Thunderbolt")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			//Venom book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Venom")) {
				exp += 150;
				exp += (eLvl * 25);
			}
			
			//RARE ENCHANTS
			//Absorb book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Absorb")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Angelic book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Angelic")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Cleave book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Cleave")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Devastate book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Devastate")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Diminish book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Diminish")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Executioner book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Executioner")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Frozen book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Frozen")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Gorge book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Gorge")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Guardians book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Guardians")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Jump book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Jump")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Magnetize book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Magnetize")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Nimble book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Nimble")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Obsidianshield book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Obsidianshield")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Pinpoint book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Pinpoint")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Pokey book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Pokey")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Retaliate book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Retaliate")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Retribution book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Retribution")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Skyrocket book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Skyrocket")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Thwomp book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Thwomp")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Turbo book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Turbo")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Vanquish book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Vanquish")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			//Wisdom book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Wisdom")) {
				exp += 450;
				exp += (eLvl * 50);
			}
			
			//LEGENDARY ENCHANTS
			//ACROBATICS book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Acrobatics")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//AERODYNAMICS book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Aerodynamics")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Agility book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Agility")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Annihilation book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Annihilation")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Bloom book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Bloom")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Blossom book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Blossom")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Demise book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Demise")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Drunk book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Drunk")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Dodge book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Dodge")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Enrage book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Enrage")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Fate book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Fate")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Fury book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Fury")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Glory book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Glory")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Intellect book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Intellect")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Lifesteal book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Lifesteal")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Lucky book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Lucky")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Meteor book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Meteor")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Puncture book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Puncture")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Rage book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Rage")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Safeguard book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Safeguard")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Silence book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Silence")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Stalwart book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Stalwart")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Stockpile book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Stockpile")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Voidwalker book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Voidwalker")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			//Whirlpool book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Whirlpool")) {
				exp += 800;
				exp += (eLvl * 75);
			}
			
			//HYPER ENCHANTS
			//Blink book
			else if (bookName.equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Blink")) {
				exp += 4800;
				exp += (eLvl * 200);
			}
			//Overdrive book
			else if (bookName.equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Overdrive")) {
				exp += 4800;
				exp += (eLvl * 200);
			}
			//Titan book
			else if (bookName.equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Titan")) {
				exp += 4800;
				exp += (eLvl * 200);
			}
		}
		
		return exp;
	}
	
	public boolean checkIfCustomEnchant(ItemStack item) {
		boolean isCustomEnchant = false;
		
		String bookDisplayName = item.getItemMeta().getDisplayName();
		String[] splitBookName = bookDisplayName.split(" ");
		if (splitBookName.length == 2) {
			String bookName = splitBookName[0];
			
			//COMMON ENCHANTS
			//Aquatic book
			if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Aquatic")) {
				isCustomEnchant = true;
			}
			//Blessed book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Blessed")) {
				isCustomEnchant = true;
			}
			//Confusion book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Confusion")) {
				isCustomEnchant = true;
			}
			//Crystallize book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Crystallize")) {
				isCustomEnchant = true;
			}
			//Geomancy book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Geomancy")) {
				isCustomEnchant = true;
			}
			//Goomba book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Goomba")) {
				isCustomEnchant = true;
			}
			//Guillotine book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Guillotine")) {
				isCustomEnchant = true;
			}
			//Nocturnal book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Nocturnal")) {
				isCustomEnchant = true;
			}
			//Pillage book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Pillage")) {
				isCustomEnchant = true;
			}
			//Plunge book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Plunge")) {
				isCustomEnchant = true;
			}
			//Poseidon book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Poseidon")) {
				isCustomEnchant = true;
			}
			//Reflect book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Reflect")) {
				isCustomEnchant = true;
			}
			//Rested book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Rested")) {
				isCustomEnchant = true;
			}
			//Shift book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Shift")) {
				isCustomEnchant = true;
			}
			//Shroomy book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Shroomy")) {
				isCustomEnchant = true;
			}
			//Shroud book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Shroud")) {
				isCustomEnchant = true;
			}
			//Sow book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Sow")) {
				isCustomEnchant = true;
			}
			//Starve book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Starve")) {
				isCustomEnchant = true;
			}
			//Telepathy book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Telepathy")) {
				isCustomEnchant = true;
			}
			//Telepathy book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Thunderbolt")) {
				isCustomEnchant = true;
			}
			//Venom book
			else if (bookName.equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Venom")) {
				isCustomEnchant = true;
			}
			
			//RARE ENCHANTS
			//Absorb book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Absorb")) {
				isCustomEnchant = true;
			}
			//Angelic book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Angelic")) {
				isCustomEnchant = true;
			}
			//Cleave book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Cleave")) {
				isCustomEnchant = true;
			}
			//Devastate book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Devastate")) {
				isCustomEnchant = true;
			}
			//Diminish book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Diminish")) {
				isCustomEnchant = true;
			}
			//Executioner book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Executioner")) {
				isCustomEnchant = true;
			}
			//Frozen book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Frozen")) {
				isCustomEnchant = true;
			}
			//Gorge book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Gorge")) {
				isCustomEnchant = true;
			}
			//Guardians book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Guardians")) {
				isCustomEnchant = true;
			}
			//Jump book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Jump")) {
				isCustomEnchant = true;
			}
			//Magnetize book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Magnetize")) {
				isCustomEnchant = true;
			}
			//Nimble book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Nimble")) {
				isCustomEnchant = true;
			}
			//Obsidianshield book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Obsidianshield")) {
				isCustomEnchant = true;
			}
			//Pinpoint book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Pinpoint")) {
				isCustomEnchant = true;
			}
			//Pokey book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Pokey")) {
				isCustomEnchant = true;
			}
			//Retaliate book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Retaliate")) {
				isCustomEnchant = true;
			}
			//Retribution book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Retribution")) {
				isCustomEnchant = true;
			}
			//Skyrocket book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Skyrocket")) {
				isCustomEnchant = true;
			}
			//Thwomp book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Thwomp")) {
				isCustomEnchant = true;
			}
			//Turbo book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Turbo")) {
				isCustomEnchant = true;
			}
			//Vanquish book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Vanquish")) {
				isCustomEnchant = true;
			}
			//Wisdom book
			else if (bookName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Wisdom")) {
				isCustomEnchant = true;
			}
			
			//LEGENDARY ENCHANTS
			//ACROBATICS book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Acrobatics")) {
				isCustomEnchant = true;
			}
			//AERODYNAMICS book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Aerodynamics")) {
				isCustomEnchant = true;
			}
			//Agility book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Agility")) {
				isCustomEnchant = true;
			}
			//Annihilation book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Annihilation")) {
				isCustomEnchant = true;
			}
			//Bloom book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Bloom")) {
				isCustomEnchant = true;
			}
			//Blossom book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Blossom")) {
				isCustomEnchant = true;
			}
			//Demise book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Demise")) {
				isCustomEnchant = true;
			}
			//Drunk book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Drunk")) {
				isCustomEnchant = true;
			}
			//Dodge book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Dodge")) {
				isCustomEnchant = true;
			}
			//Enrage book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Enrage")) {
				isCustomEnchant = true;
			}
			//Fate book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Fate")) {
				isCustomEnchant = true;
			}
			//Fury book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Fury")) {
				isCustomEnchant = true;
			}
			//Glory book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Glory")) {
				isCustomEnchant = true;
			}
			//Intellect book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Intellect")) {
				isCustomEnchant = true;
			}
			//Lifesteal book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Lifesteal")) {
				isCustomEnchant = true;
			}
			//Lucky book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Lucky")) {
				isCustomEnchant = true;
			}
			//Meteor book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Meteor")) {
				isCustomEnchant = true;
			}
			//Puncture book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Puncture")) {
				isCustomEnchant = true;
			}
			//Rage book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Rage")) {
				isCustomEnchant = true;
			}
			//Safeguard book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Safeguard")) {
				isCustomEnchant = true;
			}
			//Silence book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Silence")) {
				isCustomEnchant = true;
			}
			//Stalwart book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Stalwart")) {
				isCustomEnchant = true;
			}
			//Stockpile book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Stockpile")) {
				isCustomEnchant = true;
			}
			//Voidwalker book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Voidwalker")) {
				isCustomEnchant = true;
			}
			//Whirlpool book
			else if (bookName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Whirlpool")) {
				isCustomEnchant = true;
			}
			
			//HYPER ENCHANTS
			//Blink book
			else if (bookName.equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Blink")) {
				isCustomEnchant = true;
			}
			//Overdrive book
			else if (bookName.equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Overdrive")) {
				isCustomEnchant = true;
			}
			//Titan book
			else if (bookName.equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Titan")) {
				isCustomEnchant = true;
			}
		}
		
		return isCustomEnchant;
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
	
	@EventHandler 
	public void setBaseLineMenuClickCooldown(PlayerJoinEvent e) {
		long currentTime = System.currentTimeMillis();
		tinkererMenuClickCooldown.put(e.getPlayer().getName(), currentTime);
	}
}
