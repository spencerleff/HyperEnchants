package me.SpencerLeff.HyperEnchants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BookOpening implements Listener {
	@EventHandler(priority=EventPriority.HIGH)
	public void bookOpening(PlayerInteractEvent e) {
		//Get the player who triggered the event
		Player player = e.getPlayer();
		
		//Create common book item meta
		ItemStack commonBookItem = new ItemStack(Material.BOOK);
		ItemMeta commonBookMeta = commonBookItem.getItemMeta();
		commonBookMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Random Common Enchantment " + ChatColor.RESET + "" + ChatColor.GRAY + "(right click)");
		commonBookMeta.setLore(Arrays.asList(ChatColor.WHITE + "right click to receive a random common enchantment book!"));
		commonBookItem.setItemMeta(commonBookMeta);
		
		//Create rare book item meta
		ItemStack rareBookItem = new ItemStack(Material.BOOK);
		ItemMeta rareBookMeta = rareBookItem.getItemMeta();
		rareBookMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Random Rare Enchantment " + ChatColor.RESET + "" + ChatColor.GRAY + "(right click)");
		rareBookMeta.setLore(Arrays.asList(ChatColor.WHITE + "right click to receive a random rare enchantment book!"));
		rareBookItem.setItemMeta(rareBookMeta);

		//Create legendary book item meta
		ItemStack legendaryBookItem = new ItemStack(Material.BOOK);
		ItemMeta legendaryBookMeta = legendaryBookItem.getItemMeta();
		legendaryBookMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Random Legendary Enchantment " + ChatColor.RESET + "" + ChatColor.GRAY + "(right click)");
		legendaryBookMeta.setLore(Arrays.asList(ChatColor.WHITE + "right click to receive a random legendary enchantment book!"));
		legendaryBookItem.setItemMeta(legendaryBookMeta);
		
		//Create hyper book item meta
		ItemStack hyperBookItem = new ItemStack(Material.BOOK);
		ItemMeta hyperBookMeta = hyperBookItem.getItemMeta();
		hyperBookMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Random Hyper Enchantment " + ChatColor.RESET + "" + ChatColor.GRAY + "(right click)");
		hyperBookMeta.setLore(Arrays.asList(ChatColor.WHITE + "right click to receive a random hyper enchantment book!"));
		hyperBookItem.setItemMeta(hyperBookMeta);
		
		//right click air or block with custom book
		if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) || (e.getAction().equals(Action.RIGHT_CLICK_AIR))) {
			if (e.getItem() != null) {
				if (player.getInventory().firstEmpty() != -1) {
					
					//Common book right clicked
					if (e.getItem().getItemMeta().equals(commonBookMeta)) {
						//remove 1 book from hand slot
						player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
						
						//generate the enchantment, and play the particle effect/sound
						generateCommonEnchantment(player);
						player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 1, 1);
						player.spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 1, 1, 1, 0.25, 40);
					}
					
					//Rare book right clicked
					if (e.getItem().getItemMeta().equals(rareBookItem.getItemMeta())) {
						//remove 1 book from hand slot
						player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
						
						//generate the enchantment, and play the particle effect/sound
						generateRareEnchantment(player);
						player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 1, 1);
						player.spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 1, 1, 1, 0.25, 40);
					}
					
					//Legendary book right clicked
					if (e.getItem().getItemMeta().equals(legendaryBookItem.getItemMeta())) {
						//remove 1 book from hand slot
						player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
						
						//generate the enchantment, and play the particle effect/sound
						generateLegendaryEnchantment(player);
						player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 1, 1);
						player.spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 1, 1, 1, 0.25, 40);
					}
					
					//Hyper book right clicked
					if (e.getItem().getItemMeta().equals(hyperBookItem.getItemMeta())) {
						//remove 1 book from hand slot
						player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
						
						//generate the enchantment, and play the particle effect/sound
						generateHyperEnchantment(player);
						player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 1, 1);
						player.spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 1, 1, 1, 0.25, 40);
					}
				}
				else if (player.getInventory().firstEmpty() == -1 && (e.getItem().getItemMeta().equals(commonBookMeta) || e.getItem().getItemMeta().equals(rareBookMeta) || e.getItem().getItemMeta().equals(legendaryBookMeta) || e.getItem().getItemMeta().equals(hyperBookMeta))){
					player.sendMessage(ChatColor.DARK_RED + "Inventory is full! try again later");
				}
			}
		}
		
	}
	
	//generate the random common enchantment
	public void generateCommonEnchantment(Player player) {
		//select the enchantment randomly
		String[] commonEnchantments = {"Aquatic", "Blessed", "Confusion", "Crystallize", "Geomancy", "Goomba", "Guillotine",
									   "Nocturnal", "Pillage", "Plunge", "Poseidon", "Reflect", "Rested", "Shift", "Shroomy",
									   "Shroud", "Sow", "Starve", "Telepathy", "Thunderbolt", "Venom"};
		int[] commonEnchantmentsMaxLvl = {1, 1, 3, 4, 5, 3, 1, 1, 2, 1, 1, 1, 1, 1, 4, 4, 1, 7, 1, 2, 2};
		int arraySize = commonEnchantments.length;
		Random randomEnchStr = new Random();
		int selectedIndex = randomEnchStr.nextInt(arraySize);
		String selectedEnchantment = commonEnchantments[selectedIndex];
				
		//based on the selected enchantment, generate the level of the book
		int selectedEnchantmentMaxLvl = commonEnchantmentsMaxLvl[selectedIndex];
		Random randomEnchLvl = new Random();
		int selectedLevel = randomEnchLvl.nextInt(selectedEnchantmentMaxLvl) + 1;
		String bookLevel = getRomanNumeral(selectedLevel);
		player.sendMessage(ChatColor.WHITE + "Received " + ChatColor.BOLD + selectedEnchantment + " " + bookLevel);
				
		//turn the randomly generated things into a real enchantment book 
		turnIntoCommonBook(player, selectedEnchantment, bookLevel);
	}
	
	//turn the generated enchantment into a custom enchant book
	public void turnIntoCommonBook(Player player, String selectedEnchantment, String selectedLevel) {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta itemMeta = item.getItemMeta();
		
		//AQUATIC
		if (selectedEnchantment == "Aquatic") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Just keep swimming...");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Helmet");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Permanent water breathing");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//BLESSED
		if (selectedEnchantment == "Blessed") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Graced by the gods above");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Axe");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Remove all debuffs");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//CONFUSION
		if (selectedEnchantment == "Confusion") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Maybe they had too much to drink...");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Axe");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Imbue the opponent with nausea");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//CRYSTALLIZE
		if (selectedEnchantment == "Crystallize") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Durability loss? Couldn't be me");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Armor");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "IV");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Decreased overall durability loss");
			itemLore.add(ChatColor.AQUA + "Max level on full armor set provides immunity to durability loss");
			itemLore.add("");
			itemLore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "This enchantment is stackable");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//GEOMANCY
		if (selectedEnchantment == "Geomancy") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "I have 400 houses and 400 mouses");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Pickaxe");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "V");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Increased experience gained while mining");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//GOOMBA
		if (selectedEnchantment == "Goomba") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "We're gonna kick some koopa");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Boots");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Allows you to bouce off the opponent's head, dealing damage");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//GUILLOTINE
		if (selectedEnchantment == "Guillotine") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Fatality...");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Sword, Axe, Trident");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Killing an enemy player drops their head");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}	
		
		//NOCTURNAL
		if (selectedEnchantment == "Nocturnal") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "I'm Batman!");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Helmet");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Permanent night vision");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//PILLAGE
		if (selectedEnchantment == "Pillage") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Hog Riiiiider");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Crossbow");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "II");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Increased drops from slain mobs");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//PLUNGE
		if (selectedEnchantment == "Plunge") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Call an ambulance... but not for me!");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Trident");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Drown an enemy for a short time upon striking them");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//POSEIDON
		if (selectedEnchantment == "Poseidon") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Oh baby a triple!");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Trident");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Strike an enemy three times for only one hit");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//REFLECT
		if (selectedEnchantment == "Reflect") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "That's not my problem anymore");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Shield");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Return blocked damage to the enemy");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//RESTED
		if (selectedEnchantment == "Rested") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "The universe required correction");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Helmet");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Instantly kills any phantoms that target you");
			itemLore.add(ChatColor.AQUA + "Prevents new phantoms from spawning");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//SHIFT
		else if (selectedEnchantment == "Shift") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "King of the endermen");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Boots");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Immunity to ender pearl damage");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//SHROOMY
		else if (selectedEnchantment == "Shroomy") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Who needs Princess Peach anyways");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Leggings");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "IV");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Imbue the enemy with a poisonous D.O.T.");
			itemLore.add(ChatColor.AQUA + "Nauseates the enemy");
			itemLore.add(ChatColor.AQUA + "Blinds the enemy");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//SHROUD
		else if (selectedEnchantment == "Shroud") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "These glasses cured my color blindness! [gone wrong]");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Trident");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "IV");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Blinds the enemy");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//SOW
		else if (selectedEnchantment == "Sow") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Dogecoin to the moon!");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Chestplate");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Automatically replants farmed crops");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//STARVE
		else if (selectedEnchantment == "Starve") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Me so hungry");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Bow, Crossbow");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "VII");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Applies drastic hunger to the enemy on hit");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//TELEPATHY
		else if (selectedEnchantment == "Telepathy") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "You're a wizard Harry");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Pickaxe");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Broken blocks are placed directly in your inventory");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//THUNDERBOLT
		else if (selectedEnchantment == "Thunderbolt") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "I went for the head");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Bow, Crossbow");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "II");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Strike lightning upon the enemy");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//VENOM
		else if (selectedEnchantment == "Venom") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Snake? Snake!? Snaaaake!");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Sword");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "II");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Poison the enemy");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		item.setItemMeta(itemMeta);
		player.getInventory().addItem(item);
	} //END COMMON ENCHANTS
	
	//give random rare enchantment to player
	public void generateRareEnchantment(Player player) {
		//select the enchantment randomly
		String[] rareEnchantments = {"Absorb", "Angelic", "Cleave", "Devastate", "Diminish", "Executioner", "Frozen", "Gorge", "Guardians",
									 "Jump", "Magnetize", "Nimble", "Obsidianshield", "Pinpoint", "Pokey", "Retaliate", "Retribution", "Skyrocket",
									 "Thwomp", "Turbo", "Vanquish", "Wisdom"};
		int[] rareEnchantmentsMaxLvl = {2, 6, 5, 3, 1, 6, 3, 2, 9, 4, 3, 3, 1, 2, 4, 5, 3, 3, 3, 5, 2, 1};
		int arraySize = rareEnchantments.length;
		Random randomEnchStr = new Random();
		int selectedIndex = randomEnchStr.nextInt(arraySize);
		String selectedEnchantment = rareEnchantments[selectedIndex];
		
		//based on the selected enchantment, generate the level of the book
		int selectedEnchantmentMaxLvl = rareEnchantmentsMaxLvl[selectedIndex];
		Random randomEnchLvl = new Random();
		int selectedLevel = randomEnchLvl.nextInt(selectedEnchantmentMaxLvl) + 1;
		String bookLevel = getRomanNumeral(selectedLevel);
		player.sendMessage(ChatColor.WHITE + "Received " + ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + bookLevel);
		
		//turn the randomly generated things into a real enchantment book 
		turnIntoRareBook(player, selectedEnchantment, bookLevel);
	}
	
	//turn the random enchant into an actual book
	public void turnIntoRareBook(Player player, String selectedEnchantment, String selectedLevel) {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta itemMeta = item.getItemMeta();
		
		//ABSORB
		if (selectedEnchantment == "Absorb") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "I don't need it. I don't need it. I NEED IT");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Shield");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "II");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Drastically decreases incoming damage while blocking");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//ANGELIC
		if (selectedEnchantment == "Angelic") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "You should've picked Mercy");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Leggings");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "VI");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Gives a burst of regeneration");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//CLEAVE
		if (selectedEnchantment == "Cleave") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Not 1... not 2... not 3... not 4...");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Axe");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "V");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Striking a player also damages surrounding players");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//DEVASTATE
		if (selectedEnchantment == "Devastate") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "You brought a knife to a gunfight");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Bow, Crossbow");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Punishes enemies that are shot while wielding a sword/axe");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//DIMINISH
		if (selectedEnchantment == "Diminish") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "It's nerf or nothing");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Trident");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Give weakness to the enemy");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//EXECUTIONER
		if (selectedEnchantment == "Executioner") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "The f*ck did you say to me you little sh*t?!?");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Sword");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "VI");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Backstabbing the opponent deals increased damage");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//FROZEN
		if (selectedEnchantment == "Frozen") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Let it go!");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Helmet");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Enemy's movement speed drastically decreases");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//GORGE
		if (selectedEnchantment == "Gorge") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Nom nom nom nom nom BUUURP");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Helmet");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "II");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Gain a small amount of hunger back when critically low");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//GUARDIANS
		if (selectedEnchantment == "Guardians") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "I am Groot");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Leggings");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "IX");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Summon an iron golem ally to aid you in battle");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//JUMP
		if (selectedEnchantment == "Jump") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Jump up, jump up and get down!");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Boots");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "IV");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Permanent jump boots");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//MAGNETIZE
		if (selectedEnchantment == "Magnetize") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "They call me a chick magnet ^-^");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Sword");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Decreases the knockback taken by mobs");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//NIMBLE
		if (selectedEnchantment == "Nimble") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Jack be nimble, Jack be quick");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Bow");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Increased damage while airborne");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//OBSIDIANSHIELD
		if (selectedEnchantment == "Obsidianshield") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "This girl is on fireeeeee");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Leggings");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Permanent fire resistance");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//PINPOINT
		if (selectedEnchantment == "Pinpoint") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "360 no scope");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Trident");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "II");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Headshots from thrown tridents deal increased damage");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//POKEY
		if (selectedEnchantment == "Pokey") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Like the streamer");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Chestplate");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "IV");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Summon a pufferfish ally that damages and poisons the enemy");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//RETALIATE
		if (selectedEnchantment == "Retaliate") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Anger issues?");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Chestplate");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "V");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Trap the enemy within evoker fangs, dealing massive damage");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//RETRIBUTION
		if (selectedEnchantment == "Retribution") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Make them pay...");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Shield");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Blocking a hit from the enemy gives you absorption");
			itemLore.add(ChatColor.AQUA + "The enemy is also knocked away and  given weakness");
			itemLore.add(ChatColor.AQUA + "Activation: press f while sneaking");
			itemLore.add(ChatColor.GOLD + "This enchantment has a cooldown");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//SKYROCKET
		if (selectedEnchantment == "Skyrocket") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "We May Lose. But We Don't Die. We Survive");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Boots");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Blast into the sky while on critically low hp");
			itemLore.add(ChatColor.AQUA + "Gain slow falling and massive regeneration");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//THWOMP
		if (selectedEnchantment == "Thwomp") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "UNNGH");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Sword, Axe");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Launch the enemy up, and bring them down with force");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//TURBO
		if (selectedEnchantment == "Turbo") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Kerchow!");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Pickaxe");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "V");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Gain a burst of haste");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//VANQUISH
		if (selectedEnchantment == "Vanquish") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Miiiiiiine diamondsss");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Pickaxe");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "II");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Break surrounding blocks while mining");
			itemLore.add(ChatColor.AQUA + "Sneaking while mining deactivates the enchantment");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//WISDOM
		if (selectedEnchantment == "Wisdom") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "I have the high ground");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Axe");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Gain a damage multiplier based on your experience level");
			itemLore.add(ChatColor.AQUA + "Multiplier caps at level 100");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
				
		item.setItemMeta(itemMeta);
		player.getInventory().addItem(item);
	}
	
	
	//give random legendary enchantment to player
	public void generateLegendaryEnchantment(Player player) {
		//select the enchantment randomly
		String[] legendaryEnchantments = {"Acrobatics", "Aerodynamics", "Agility", "Annihilation", "Bloom", "Blossom", 
										  "Demise", "Drunk", "Dodge", "Enrage", "Fate", "Fury", "Glory", "Intellect", 
										  "Lifesteal", "Lucky", "Meteor", "Puncture", "Rage", "Safeguard", "Silence",
										  "Stalwart", "Stockpile", "Voidwalker", "Whirlpool"};
		int[] legendaryEnchantmentsMaxLvl = {5, 2, 3, 3, 5, 6, 7, 2, 4, 4, 3, 6, 3, 4, 3, 10, 4, 1, 2, 1, 7, 3, 3, 5, 1};
		int arraySize = legendaryEnchantments.length;
		Random randomEnchStr = new Random();
		int selectedIndex = randomEnchStr.nextInt(arraySize);
		String selectedEnchantment = legendaryEnchantments[selectedIndex];
		
		//based on the selected enchantment, generate the level of the book
		int selectedEnchantmentMaxLvl = legendaryEnchantmentsMaxLvl[selectedIndex];
		Random randomEnchLvl = new Random();
		int selectedLevel = randomEnchLvl.nextInt(selectedEnchantmentMaxLvl) + 1;
		String bookLevel = getRomanNumeral(selectedLevel);
		player.sendMessage(ChatColor.WHITE + "Received " + ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + bookLevel);
		
		//turn the randomly generated things into a real enchantment book 
		turnIntoLegendaryBook(player, selectedEnchantment, bookLevel);
	}
	
	//turn the random enchant into an actual book
	public void turnIntoLegendaryBook(Player player, String selectedEnchantment, String selectedLevel) {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta itemMeta = item.getItemMeta();
		
		//Acrobatics
		if (selectedEnchantment == "Acrobatics") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Unleash your inner Tarzan");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Trident");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "V");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Increases damage dealt while airborne");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Aerodynamics
		if (selectedEnchantment == "Aerodynamics") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Duct tape is always the answer");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Trident");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "II");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Increases the distance traveled via riptide");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Agility
		if (selectedEnchantment == "Agility") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Running in the 90's");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Boots");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Permanent speed boost");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Annihilation
		if (selectedEnchantment == "Annihilation") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Thanos snap the universe");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Chestplate");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Chance to deal double damage");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Bloom
		if (selectedEnchantment == "Bloom") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Console gamer lol");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Bow");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "V");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Regain some health after shooting an enemy");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Blossom
		if (selectedEnchantment == "Blossom") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "peepoHappy");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Boots");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "VI");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "You call upon the nearby flowers for healing");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Demise
		if (selectedEnchantment == "Demise") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "o7");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Trident");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "VII");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Landing five consecutive strikes marks the opponent's demise");
			itemLore.add(ChatColor.AQUA + "While marked, the enemy player will take increased damage");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Drunk
		if (selectedEnchantment == "Drunk") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Couple Claws down the hatch");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Helmet");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "II");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Permanent strength");
			itemLore.add(ChatColor.AQUA + "Permanent slowness");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Dodge
		if (selectedEnchantment == "Dodge") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Can't touch this");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Leggings");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "IV");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Completely avoid the damage taken when struck");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Enrage
		if (selectedEnchantment == "Enrage") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Spicy gamer moment");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Axe");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "IV");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Increases critical hit damage");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Fate
		if (selectedEnchantment == "Fate") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "I'm rich!");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Pickaxe");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Duplicate valuable ores once mined");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Fury
		if (selectedEnchantment == "Fury") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "I don't care that you broke your elbow");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Sword");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "VI");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Gain an increasing PVE damage multiplier for landing consecutive mob hits");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Glory
		if (selectedEnchantment == "Glory") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "This is Sparta!");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Shield");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Reduces incoming damage even while not blocking");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Intellect
		if (selectedEnchantment == "Intellect") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "9000 IQ");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Sword");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "IV");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Greatly increases experience gained from monster kills");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Lifesteal
		if (selectedEnchantment == "Lifesteal") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Basically cannibalism");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Sword");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Steals the enemies health, and replenishes your own");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Lucky
		if (selectedEnchantment == "Lucky") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Rig the system in your favor");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Armor");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "X");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Increases the proc chance of custom enchantments");
			itemLore.add("");
			itemLore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "This enchantment is stackable");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Meteor
		if (selectedEnchantment == "Meteor") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Bye bye dinos");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Bow, Crossbow");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "IV");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Summon a meteor strike at your opponent's location");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Puncture
		if (selectedEnchantment == "Puncture") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Need a Band-Aid?");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Crossbow");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Projectiles pierce the enemy's armor");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Rage
		if (selectedEnchantment == "Rage") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Typical gamer moment");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Sword, Axe");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "II");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Gain an increasing PVP damage multiplier for landing consecutive strikes");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Safeguard
		if (selectedEnchantment == "Safeguard") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "I'm the captain now");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Shield");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Permanent resistance");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Silence
		if (selectedEnchantment == "Silence") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "That movie with the bird and the box");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Axe");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "VII");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Disables the opponent's custom enchantments");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Stalwart
		if (selectedEnchantment == "Stalwart") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "You're such a worry-wart");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Shield");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Permanent increase in armor toughness");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Stockpile
		if (selectedEnchantment == "Stockpile") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Snorlax vibes");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Chestplate");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "III");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Permanent health increase");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Voidwalker
		if (selectedEnchantment == "Voidwalker") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "The moon landing was fake");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Helmet");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "V");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Decreases incoming damage from end-related sources");
			itemLore.add(ChatColor.AQUA + "Significantly decreases end crystal damage in particular");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Whirlpool
		if (selectedEnchantment == "Whirlpool") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Required to be a hot tub streamer");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Leggings");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On activation");
			itemLore.add(ChatColor.AQUA + "Summons a temporary whirlpool at the enemy's location");
			itemLore.add(ChatColor.AQUA + "The enemy is pulled in and dealt D.O.T.");
			itemLore.add("");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		item.setItemMeta(itemMeta);
		player.getInventory().addItem(item);
	} //END LEGENDARY ENCHANTS
	
	//give random hyper enchantment to player
	public void generateHyperEnchantment(Player player) {
		//select the enchantment randomly
		String[] hyperEnchantments = {"Blink", "Overdrive", "Titan"};
		int[] hyperEnchantmentsMaxLvl = {1, 1, 1};
		int arraySize = hyperEnchantments.length;
		Random randomEnchStr = new Random();
		int selectedIndex = randomEnchStr.nextInt(arraySize);
		String selectedEnchantment = hyperEnchantments[selectedIndex];
		
		//based on the selected enchantment, generate the level of the book
		int selectedEnchantmentMaxLvl = hyperEnchantmentsMaxLvl[selectedIndex];
		Random randomEnchLvl = new Random();
		int selectedLevel = randomEnchLvl.nextInt(selectedEnchantmentMaxLvl) + 1;
		String bookLevel = getRomanNumeral(selectedLevel);
		player.sendMessage(ChatColor.WHITE + "Received " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + selectedEnchantment + " " + bookLevel);
		
		//turn the randomly generated things into a real enchantment book 
		turnIntoHyperBook(player, selectedEnchantment, bookLevel);
	}
	
	//turn the random enchant into an actual book
	public void turnIntoHyperBook(Player player, String selectedEnchantment, String selectedLevel) {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta itemMeta = item.getItemMeta();
		
		//Blink
		if (selectedEnchantment == "Blink") {
			itemMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Watch this!");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Boots");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add("");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On usage");
			itemLore.add(ChatColor.AQUA + "Passively gain air dodge charges over time");
			itemLore.add(ChatColor.AQUA + "If a charge is available, double jump to use it");
			itemLore.add("");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Hold sneak while midair to hold your position for a short time");
			itemLore.add(ChatColor.AQUA + "Immune to fall damage");
			itemLore.add("");
			itemLore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "This enchantment is immune to silence");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Overdrive
		if (selectedEnchantment == "Overdrive") {
			itemMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Come and fly away with meeeee");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Bow");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add("");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On usage");
			itemLore.add(ChatColor.AQUA + "Hold shift to create a shockwave at your feet");
			itemLore.add(ChatColor.AQUA + "The shockwave launches you upwards, damaging nearby enemies");
			itemLore.add(ChatColor.AQUA + "While airborne, gain considerable slow falling");
			itemLore.add(ChatColor.LIGHT_PURPLE + "This ability has a cooldown");
			itemLore.add("");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Shooting enemies from further away deals extra damage");
			itemLore.add("");
			itemLore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "This enchantment is immune to silence");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		//Titan
		if (selectedEnchantment == "Titan") {
			itemMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Where did my shoulder charge go?");
			itemLore.add(ChatColor.BLUE + "Enchantment type: " + ChatColor.WHITE + "" + ChatColor.BOLD + "Chestplate");
			itemLore.add(ChatColor.BLUE + "Max level: " + ChatColor.WHITE + "" + ChatColor.BOLD + "I");
			itemLore.add(ChatColor.BLUE + "Usage: " + ChatColor.RED + "PVP " + ChatColor.WHITE + "+ " + ChatColor.DARK_GREEN + "PVE");
			itemLore.add("");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "On usage");
			itemLore.add(ChatColor.AQUA + "Summon a protective shield in a radius around you");
			itemLore.add(ChatColor.AQUA + "While inside, gain passive regeneration and absorption");
			itemLore.add(ChatColor.AQUA + "Enemies within your shield will be blinded");
			itemLore.add(ChatColor.LIGHT_PURPLE + "This ability has a cooldown");
			itemLore.add("");
			itemLore.add(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Passive effects");
			itemLore.add(ChatColor.AQUA + "Permanent health increase");
			itemLore.add("");
			itemLore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "This enchantment is immune to silence");
			itemLore.add(ChatColor.RED + "Apply to the correct item to add this enchantment");
			Random rand = new Random();
			int upperBound = 9999999;
			int uniqueTag = rand.nextInt(upperBound);
			itemLore.add(ChatColor.DARK_GRAY + "#" + uniqueTag);
			if (itemMeta.hasLore()) {
				for (String IL : itemMeta.getLore()) {
					itemLore.add(IL);
				}
			}
			itemMeta.setLore(itemLore);
		}
		
		item.setItemMeta(itemMeta);
		player.getInventory().addItem(item);
	} //END HYPER ENCHANTS
	
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
