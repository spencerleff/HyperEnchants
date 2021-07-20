package me.SpencerLeff.HyperEnchants;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BookOpening implements Listener {

	//Enchant variables
	public static final Enchantment AQUATIC = new EnchantmentWrapper("aquatic", "Aquatic", 1);
	public static final Enchantment BLESSED = new EnchantmentWrapper("blessed", "Blessed", 1);
	public static final Enchantment CONFUSION = new EnchantmentWrapper("confusion", "Confusion", 3);
	public static final Enchantment GUILLOTINE = new EnchantmentWrapper("guillotine", "Guillotine", 1);
	public static final Enchantment NOCTURNAL = new EnchantmentWrapper("nocturnal", "Nocturnal", 1);
	public static final Enchantment TELEPATHY = new EnchantmentWrapper("telepathy", "Telepathy", 1);
	public static final Enchantment CLEAVE = new EnchantmentWrapper("cleave", "Cleave", 5);
	public static final Enchantment JUMP = new EnchantmentWrapper("jump", "Jump", 4);
	public static final Enchantment MAGNETIZE = new EnchantmentWrapper("magnetize", "Magnetize", 3);
	public static final Enchantment OBSIDIANSHIELD = new EnchantmentWrapper("obsidianshield", "Obsidianshield", 1);
	public static final Enchantment RETRIBUTION = new EnchantmentWrapper("retribution", "RETRIBUTION", 3);
	public static final Enchantment SKYROCKET = new EnchantmentWrapper("skyrocket", "Skyrocket", 1);
	public static final Enchantment THWOMP = new EnchantmentWrapper("thwomp", "Thwomp", 3);
	public static final Enchantment AGILITY = new EnchantmentWrapper("agility", "Agility", 3);
	public static final Enchantment DRUNK = new EnchantmentWrapper("drunk", "Drunk", 1);
	public static final Enchantment FURY = new EnchantmentWrapper("fury", "Fury", 6);
	public static final Enchantment LIFESTEAL = new EnchantmentWrapper("lifesteal", "Lifesteal", 3);
	public static final Enchantment RAGE = new EnchantmentWrapper("rage", "Rage", 2);
	public static final Enchantment STOCKPILE = new EnchantmentWrapper("stockpile", "Stockpile", 3);
	
	//register custom enchants
	public static void register() {
		boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(TELEPATHY);
		if (!registered) {
			registerEnchantment(AQUATIC);
			registerEnchantment(BLESSED);
			registerEnchantment(CONFUSION);
			registerEnchantment(GUILLOTINE);
			registerEnchantment(NOCTURNAL);
			registerEnchantment(TELEPATHY);
			registerEnchantment(CLEAVE);
			registerEnchantment(JUMP);
			registerEnchantment(MAGNETIZE);
			registerEnchantment(OBSIDIANSHIELD);
			registerEnchantment(RETRIBUTION);
			registerEnchantment(SKYROCKET);
			registerEnchantment(THWOMP);
			registerEnchantment(AGILITY);
			registerEnchantment(DRUNK);
			registerEnchantment(FURY);
			registerEnchantment(LIFESTEAL);
			registerEnchantment(RAGE);
			registerEnchantment(STOCKPILE);
		}
	}
	public static void registerEnchantment(Enchantment enchantment) {
		boolean registered = true;
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(enchantment);
			
		} 
		catch(Exception e) {
			registered = false;
			e.printStackTrace();
		}
		
		if (registered) {
			// Send message to console
		}
	}
	
	
	@EventHandler(priority=EventPriority.HIGH)
	public void bookOpening(PlayerInteractEvent e) {
		
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
		
		Player player = e.getPlayer();
		
		//right click air or block
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
				}
				else {
					player.sendMessage(ChatColor.DARK_RED + "Inventory is full! try again later");
				}
			}
		}	
	}
	
	//give random common enchantment to player
	public void generateCommonEnchantment(Player player) {
		//select the enchantment randomly
		String[] commonEnchantments = {"Aquatic", "Blessed", "Confusion", "Guillotine", "Nocturnal", "Telepathy"};
		int[] commonEnchantmentsMaxLvl = {1, 1, 3, 1, 1, 1};
		int arraySize = commonEnchantments.length;
		Random randomEnchStr = new Random();
		int selectedIndex = randomEnchStr.nextInt(arraySize);
		String selectedEnchantment = commonEnchantments[selectedIndex];
				
		//based on the selected enchantment, generate the level of the book
		int selectedEnchantmentMaxLvl = commonEnchantmentsMaxLvl[selectedIndex];
		Random randomEnchLvl = new Random();
		int selectedLevel = randomEnchLvl.nextInt(selectedEnchantmentMaxLvl) + 1;
		player.sendMessage(ChatColor.LIGHT_PURPLE + "HyperEnchants: " + ChatColor.RESET + "" +
						   ChatColor.WHITE + "Received " + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
				
		//turn the randomly generated things into a real enchantment book 
		turnIntoCommonBook(player, selectedEnchantment, selectedLevel);
	}
			
	//turn the random enchant into an actual book
	public void turnIntoCommonBook(Player player, String selectedEnchantment, int selectedLevel) {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta itemMeta = item.getItemMeta();
		
		//AQUATIC
		if (selectedEnchantment == "Aquatic") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.GOLD + "Helmet " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Gain permanent water breathing");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.DARK_AQUA + "Axe " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Chance to remove all debuffs after hitting an opponent");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.DARK_AQUA + "Axe " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Chance to give the opponent nausea upon impact");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.DARK_PURPLE + "Melee Weapon " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Chance to drop opponents head after killing them");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.GOLD + "Helmet " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Gain permanent night vision");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.YELLOW + "Pickaxe " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "When breaking blocks with this enchantment,");
			itemLore.add(ChatColor.WHITE + "they will be placed directly into your inventory");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
	
	//give random rare enchantment to player
	public void generateRareEnchantment(Player player) {
		//select the enchantment randomly
		String[] rareEnchantments = {"Cleave", "Jump", "Magnetize", "Obsidianshield", "Retribution", "Skyrocket", "Thwomp"};
		int[] rareEnchantmentsMaxLvl = {5, 4, 3, 1, 3, 3, 3};
		int arraySize = rareEnchantments.length;
		Random randomEnchStr = new Random();
		int selectedIndex = randomEnchStr.nextInt(arraySize);
		String selectedEnchantment = rareEnchantments[selectedIndex];
		
		//based on the selected enchantment, generate the level of the book
		int selectedEnchantmentMaxLvl = rareEnchantmentsMaxLvl[selectedIndex];
		Random randomEnchLvl = new Random();
		int selectedLevel = randomEnchLvl.nextInt(selectedEnchantmentMaxLvl) + 1;
		player.sendMessage(ChatColor.LIGHT_PURPLE + "HyperEnchants: " + ChatColor.RESET + "" +
				   ChatColor.WHITE + "Received " + ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
		
		//turn the randomly generated things into a real enchantment book 
		turnIntoRareBook(player, selectedEnchantment, selectedLevel);
	}
	
	//turn the random enchant into an actual book
	public void turnIntoRareBook(Player player, String selectedEnchantment, int selectedLevel) {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta itemMeta = item.getItemMeta();
		
		//CLEAVE
		if (selectedEnchantment == "Cleave") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.DARK_AQUA + "Axe " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Deal an extra percentage of damage to surrounding enemies in an AOE");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.BLUE + "Boots " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Gain permanent jump boost");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.DARK_RED + "Sword " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Mobs take reduced knockback");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.RED + "Leggings " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Gain permanent fire resistance");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.LIGHT_PURPLE + "Shield " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Press shift with this enchantment in your off-hand to activate (cooldown 90 seconds).");
			itemLore.add(ChatColor.WHITE + "Upon blocking a hit from your enemy, gain resistance and absorption for a short period of time!");
			itemLore.add(ChatColor.WHITE + "This will also knock your opponents away!");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.BLUE + "Boots " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Chance to be shot into the air when at very low hp");
			itemLore.add(ChatColor.WHITE + "and gain regeneration for a short period of time");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.DARK_PURPLE + "Melee Weapon " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "On proc, send the hit mob/entity into the heavens for judgement");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
		String[] legendaryEnchantments = {"Agility", "Drunk", "Fury", "Lifesteal", "Rage", "Stockpile"};
		int[] legendaryEnchantmentsMaxLvl = {2, 1, 6, 3, 2, 3};
		int arraySize = legendaryEnchantments.length;
		Random randomEnchStr = new Random();
		int selectedIndex = randomEnchStr.nextInt(arraySize);
		String selectedEnchantment = legendaryEnchantments[selectedIndex];
		
		//based on the selected enchantment, generate the level of the book
		int selectedEnchantmentMaxLvl = legendaryEnchantmentsMaxLvl[selectedIndex];
		Random randomEnchLvl = new Random();
		int selectedLevel = randomEnchLvl.nextInt(selectedEnchantmentMaxLvl) + 1;
		player.sendMessage(ChatColor.LIGHT_PURPLE + "HyperEnchants: " + ChatColor.RESET + "" +
				   ChatColor.WHITE + "Received " + ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
		
		//turn the randomly generated things into a real enchantment book 
		turnIntoLegendaryBook(player, selectedEnchantment, selectedLevel);
	}
	
	//turn the random enchant into an actual book
	public void turnIntoLegendaryBook(Player player, String selectedEnchantment, int selectedLevel) {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta itemMeta = item.getItemMeta();
		
		//Drunk
		if (selectedEnchantment == "Agility") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + selectedEnchantment + " " + selectedLevel);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.BLUE + "Boots " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Gain permanent speed boost");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.GOLD + "Helmet " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Gain permanent strength at the cost of slowness");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.DARK_RED + "Sword " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Gain a damage multiplier for each time you hit a mob without taking damage");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.DARK_RED + "Sword " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Upon hitting an enemy, chance to replenish some health");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.DARK_PURPLE + "Melee Weapon " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Gain a damage multiplier for hitting an opponent without taking a hit");
			itemLore.add(ChatColor.WHITE + "Dealing damage before your attack cooldown is up reduces the rage multiplier");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
			itemLore.add(ChatColor.DARK_BLUE + "Chestplate " + ChatColor.WHITE + "Enchantment");
			itemLore.add(ChatColor.WHITE + "Gain a permanent health increase");
			itemLore.add(ChatColor.RED + "Place this enchantment on the correct item type to apply it!");
			Random rand = new Random();
			int upperBound = 99999;
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
}
