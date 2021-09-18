package me.SpencerLeff.HyperEnchants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.PufferFish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Stray;
import org.bukkit.entity.Trident;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.world.level.Explosion;

public class Main extends JavaPlugin implements Listener {

	public int retaliateCounter = 0;

	public static HashMap<String, Boolean> goombaActive = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> shroomyActive = new HashMap<String, Boolean>();
	public static HashMap<String, Integer> shroomyCounter = new HashMap<String, Integer>();
	public static HashMap<String, Integer> gorgeHungerLevel = new HashMap<String, Integer>();
	public static HashMap<String, Integer> guardiansCounter = new HashMap<String, Integer>();
	public static HashMap<String, Integer> pokeyCounter = new HashMap<String, Integer>();
	public static HashMap<String, Double> getLastFuryDamage = new HashMap<String, Double>();
	public static HashMap<String, Double> currentFuryMultiplier = new HashMap<String, Double>();
	public static HashMap<String, Double> currentRageMultiplier = new HashMap<String, Double>();
	public static HashMap<String, Boolean> retributionActive = new HashMap<String, Boolean>();
	public static HashMap<String, Long> retributionCooldown = new HashMap<String, Long>();
	public static HashMap<String, Boolean> thwompActive = new HashMap<String, Boolean>();
	public static HashMap<String, Integer> demiseCounter = new HashMap<String, Integer>();
	public static HashMap<String, Boolean> demiseActive = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> silenceActive = new HashMap<String, Boolean>();
	public static HashMap<String, Integer> luckyValue = new HashMap<String, Integer>();
	public static HashMap<String, String> hyperActive = new HashMap<String, String>();
	public static HashMap<String, Long> hyperMenuClickCooldown = new HashMap<String, Long>();
	
	public static HashMap<String, Integer> blinkCharges = new HashMap<String, Integer>();
	public static HashMap<String, Integer> blinkFloatCounter = new HashMap<String, Integer>();
	public static HashMap<String, Long> blinkLastChargeGain = new HashMap<String, Long>();
	public static HashMap<String, Long> overdriveCooldown = new HashMap<String, Long>();
	public static HashMap<String, Integer> overdriveAnimationRadius = new HashMap<String, Integer>();
	public static HashMap<String, Boolean> overdriveChargingBow = new HashMap<String, Boolean>();
	public static HashMap<String, Integer> overdriveChargeCounter = new HashMap<String, Integer>();
	public static HashMap<String, Long> overdriveLastUse = new HashMap<String, Long>();
	public static HashMap<String, Long> titanCooldown = new HashMap<String, Long>();
	public static HashMap<String, Boolean> titanReady = new HashMap<String, Boolean>();
	public static HashMap<String, Integer> titanAnimationRadius = new HashMap<String, Integer>();
	public static HashMap<String, Integer> titanBubbleAnimationCounter = new HashMap<String, Integer>();
	public static HashMap<String, Boolean> titanAlreadyAddedAbsorption = new HashMap<String, Boolean>();

	@Override
	public void onEnable() {
		// register all enchants using the register function in CustomEnchants class
		CustomEnchants.register();

		// catch all events in the main function (enchantment procs)
		this.getServer().getPluginManager().registerEvents(this, this);

		// custom enchantment book opening event
		getServer().getPluginManager().registerEvents(new BookOpening(), this);

		// Enchanter menu click event
		getServer().getPluginManager().registerEvents(new EnchanterMenuClickEvent(), this);

		// Tinkerer menu click event
		getServer().getPluginManager().registerEvents(new TinkererMenuClickEvent(), this);

		// applyCustomEnchantments click event
		getServer().getPluginManager().registerEvents(new ApplyCustomEnchantments(), this);

		// enchantment table event (some custom enchants)
		getServer().getPluginManager().registerEvents(new EnchantTableCustomEnchants(), this);

		// grindstone event
		getServer().getPluginManager().registerEvents(new RemoveEnchantsGrindstone(), this);

		// Check if item is renamed - if it has been, then reapply all the current
		// enchants on the item
		getServer().getPluginManager().registerEvents(new RenameCheck(), this);

		// Combine two custom enchant books to raise the level
		getServer().getPluginManager().registerEvents(new CombineEnchantBooks(), this);

		// armor equip/unequip events
		this.getServer().getPluginManager().registerEvents(new EventAnalyser(this), this);
		
		blinkGainCharges();
	}

	@Override
	public void onDisable() {

	}

	// all commands for the plugin
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Enchanter command
		if (label.equalsIgnoreCase("enchanter")) {
			if (!(sender instanceof Player)) {
				return true;
			}
			Player player = (Player) sender;

			Inventory enchanterMenu = Bukkit.createInventory(player, 9,
					ChatColor.GREEN + "" + ChatColor.BOLD + "Enchanter");

			// Fill first three and last three gui slots with grey stained class
			ItemStack fillerPane = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
			ItemMeta fillerMeta = fillerPane.getItemMeta();
			fillerMeta.setDisplayName(ChatColor.BLACK + "" + ChatColor.BOLD + "" + ChatColor.MAGIC + "~");
			fillerPane.setItemMeta(fillerMeta);

			// Common enchantment pane
			ItemStack commonPane = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
			ItemMeta commonMeta = commonPane.getItemMeta();
			commonMeta.setDisplayName(ChatColor.WHITE + "Common Enchantment - 20 Levels");
			ArrayList<String> commonLore = new ArrayList<>();
			commonLore.add(ChatColor.RED + "Ensure you have placed any extra experience in a /xpbottle");
			commonMeta.setLore(commonLore);
			commonPane.setItemMeta(commonMeta);

			// Uncommon enchantment pane
			ItemStack rarePane = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
			ItemMeta rareMeta = rarePane.getItemMeta();
			rareMeta.setDisplayName(ChatColor.AQUA + "Rare Enchantment - 35 Levels");
			ArrayList<String> rareLore = new ArrayList<>();
			rareLore.add(ChatColor.RED + "Ensure you have placed any extra experience in a /xpbottle");
			rareMeta.setLore(rareLore);
			rarePane.setItemMeta(rareMeta);

			// Legendary enchantment pane
			ItemStack legendaryPane = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
			ItemMeta legendaryMeta = legendaryPane.getItemMeta();
			legendaryMeta.setDisplayName(ChatColor.GOLD + "Legendary Enchantment - 60 Levels");
			ArrayList<String> legendaryLore = new ArrayList<>();
			legendaryLore.add(ChatColor.RED + "Ensure you have placed any extra experience in a /xpbottle");
			legendaryMeta.setLore(legendaryLore);
			legendaryPane.setItemMeta(legendaryMeta);

			ItemStack[] enchanterPanes = { fillerPane, fillerPane, fillerPane, commonPane, rarePane, legendaryPane,
					fillerPane, fillerPane, fillerPane };
			enchanterMenu.setContents(enchanterPanes);
			player.openInventory(enchanterMenu);
		}

		// Tinkerer command
		if (label.equalsIgnoreCase("tinkerer")) {
			if (!(sender instanceof Player)) {
				return true;
			}
			Player player = (Player) sender;

			Inventory tinkerer = Bukkit.createInventory(player, 36, ChatColor.AQUA + "" + ChatColor.BOLD + "Tinkerer");

			ItemStack fillerPane = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
			ItemMeta fillerMeta = fillerPane.getItemMeta();
			fillerMeta.setDisplayName(ChatColor.BLACK + "" + ChatColor.BOLD + "" + ChatColor.MAGIC + "~");
			fillerPane.setItemMeta(fillerMeta);

			ItemStack enchantPane = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
			ItemMeta enchantMeta = enchantPane.getItemMeta();
			enchantMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Awaiting custom enchantment book...");
			ArrayList<String> enchantLore = new ArrayList<>();
			enchantLore.add(ChatColor.AQUA + "Add a custom enchantment to this item slot");
			enchantLore.add(
					ChatColor.AQUA + "and receive some " + ChatColor.GREEN + "EXP " + ChatColor.AQUA + "in return!");
			enchantMeta.setLore(enchantLore);
			enchantPane.setItemMeta(enchantMeta);

			ItemStack experiencePane = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
			ItemMeta experienceMeta = experiencePane.getItemMeta();
			experienceMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "0 EXP");
			experiencePane.setItemMeta(experienceMeta);

			ItemStack combinePane = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
			ItemMeta combineMeta = combinePane.getItemMeta();
			combineMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.MAGIC + "~" + ChatColor.GREEN + ""
					+ ChatColor.BOLD + " ACCEPT " + ChatColor.DARK_GREEN + "" + ChatColor.MAGIC + "~");
			combinePane.setItemMeta(combineMeta);

			ItemStack[] tinkererPanes = { fillerPane, fillerPane, fillerPane, fillerPane, fillerPane, fillerPane,
					fillerPane, fillerPane, fillerPane, fillerPane, fillerPane, fillerPane, enchantPane, fillerPane,
					experiencePane, fillerPane, fillerPane, fillerPane, fillerPane, fillerPane, fillerPane, fillerPane,
					combinePane, fillerPane, fillerPane, fillerPane, fillerPane, fillerPane, fillerPane, fillerPane,
					fillerPane, fillerPane, fillerPane, fillerPane, fillerPane, fillerPane };
			tinkerer.setContents(tinkererPanes);
			player.openInventory(tinkerer);
		}

		// Hyper command
		if (label.equalsIgnoreCase("hyper")) {
			if (!(sender instanceof Player)) {
				return true;
			}
			Player player = (Player) sender;

			Inventory hyper = Bukkit.createInventory(player, 36,
					ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Hyper");

			ItemStack fillerPane = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
			ItemMeta fillerMeta = fillerPane.getItemMeta();
			fillerMeta.setDisplayName(ChatColor.BLACK + "" + ChatColor.BOLD + "" + ChatColor.MAGIC + "~");
			fillerPane.setItemMeta(fillerMeta);

			ArrayList<String> activeItemLore = new ArrayList<>();
			activeItemLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "ACTIVE");
			activeItemLore.add(ChatColor.DARK_PURPLE + "Click to deactivate");

			ArrayList<String> inactiveItemLore = new ArrayList<>();
			inactiveItemLore.add(ChatColor.RED + "" + ChatColor.BOLD + "INACTIVE");
			inactiveItemLore.add(ChatColor.DARK_PURPLE + "Click to activate");

			ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
			if (hyperActive.get(player.getName()) == "RECALL") {
				ItemMeta swordMeta = sword.getItemMeta();
				swordMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Recall");
				swordMeta.setLore(activeItemLore);
				sword.setItemMeta(swordMeta);
				sword.addUnsafeEnchantment(CustomEnchants.GOOMBA, 1);
			} else {
				ItemMeta swordMeta = sword.getItemMeta();
				swordMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Recall");
				swordMeta.setLore(inactiveItemLore);
				sword.setItemMeta(swordMeta);
			}

			ItemStack axe = new ItemStack(Material.NETHERITE_AXE);
			if (hyperActive.get(player.getName()) == "ABYSS") {
				ItemMeta axeMeta = axe.getItemMeta();
				axeMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Abyss");
				axeMeta.setLore(activeItemLore);
				axe.setItemMeta(axeMeta);
				axe.addUnsafeEnchantment(CustomEnchants.GOOMBA, 1);
			} else {
				ItemMeta axeMeta = axe.getItemMeta();
				axeMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Abyss");
				axeMeta.setLore(inactiveItemLore);
				axe.setItemMeta(axeMeta);
			}

			ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET);
			if (hyperActive.get(player.getName()) == "THERMAL") {
				ItemMeta helmetMeta = helmet.getItemMeta();
				helmetMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Thermal");
				helmetMeta.setLore(activeItemLore);
				helmet.setItemMeta(helmetMeta);
				helmet.addUnsafeEnchantment(CustomEnchants.GOOMBA, 1);
			} else {
				ItemMeta helmetMeta = helmet.getItemMeta();
				helmetMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Thermal");
				helmetMeta.setLore(inactiveItemLore);
				helmet.setItemMeta(helmetMeta);
			}

			ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
			if (hyperActive.get(player.getName()) == "TITAN") {
				ItemMeta chestplateMeta = chestplate.getItemMeta();
				chestplateMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Titan");
				chestplateMeta.setLore(activeItemLore);
				chestplate.setItemMeta(chestplateMeta);
				chestplate.addUnsafeEnchantment(CustomEnchants.GOOMBA, 1);
			} else {
				ItemMeta chestplateMeta = chestplate.getItemMeta();
				chestplateMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Titan");
				chestplateMeta.setLore(inactiveItemLore);
				chestplate.setItemMeta(chestplateMeta);
			}

			ItemStack leggings = new ItemStack(Material.NETHERITE_LEGGINGS);
			if (hyperActive.get(player.getName()) == "INFERNO") {
				ItemMeta leggingsMeta = leggings.getItemMeta();
				leggingsMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Inferno");
				leggingsMeta.setLore(activeItemLore);
				leggings.setItemMeta(leggingsMeta);
				leggings.addUnsafeEnchantment(CustomEnchants.GOOMBA, 1);
			} else {
				ItemMeta leggingsMeta = leggings.getItemMeta();
				leggingsMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Inferno");
				leggingsMeta.setLore(inactiveItemLore);
				leggings.setItemMeta(leggingsMeta);
			}

			ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
			if (hyperActive.get(player.getName()) == "BLINK") {
				ItemMeta bootsMeta = boots.getItemMeta();
				bootsMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Blink");
				bootsMeta.setLore(activeItemLore);
				boots.setItemMeta(bootsMeta);
				boots.addUnsafeEnchantment(CustomEnchants.GOOMBA, 1);
			} else {
				ItemMeta bootsMeta = boots.getItemMeta();
				bootsMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Blink");
				bootsMeta.setLore(inactiveItemLore);
				boots.setItemMeta(bootsMeta);
			}

			ItemStack shield = new ItemStack(Material.SHIELD);
			if (hyperActive.get(player.getName()) == "PUMMEL") {
				ItemMeta shieldMeta = shield.getItemMeta();
				shieldMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Pummel");
				shieldMeta.setLore(activeItemLore);
				shield.setItemMeta(shieldMeta);
				shield.addUnsafeEnchantment(CustomEnchants.GOOMBA, 1);
			} else {
				ItemMeta shieldMeta = shield.getItemMeta();
				shieldMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Pummel");
				shieldMeta.setLore(inactiveItemLore);
				shield.setItemMeta(shieldMeta);
			}

			ItemStack bow = new ItemStack(Material.BOW);
			if (hyperActive.get(player.getName()) == "OVERDRIVE") {
				ItemMeta bowMeta = bow.getItemMeta();
				bowMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Overdrive");
				bowMeta.setLore(activeItemLore);
				bow.setItemMeta(bowMeta);
				bow.addUnsafeEnchantment(CustomEnchants.GOOMBA, 1);
			} else {
				ItemMeta bowMeta = bow.getItemMeta();
				bowMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Overdrive");
				bowMeta.setLore(inactiveItemLore);
				bow.setItemMeta(bowMeta);
			}

			ItemStack crossbow = new ItemStack(Material.CROSSBOW);
			if (hyperActive.get(player.getName()) == "MARKSMAN") {
				ItemMeta crossbowMeta = crossbow.getItemMeta();
				crossbowMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Marksman");
				crossbowMeta.setLore(activeItemLore);
				crossbow.setItemMeta(crossbowMeta);
				crossbow.addUnsafeEnchantment(CustomEnchants.GOOMBA, 1);
			} else {
				ItemMeta crossbowMeta = crossbow.getItemMeta();
				crossbowMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Marksman");
				crossbowMeta.setLore(inactiveItemLore);
				crossbow.setItemMeta(crossbowMeta);
			}

			ItemStack trident = new ItemStack(Material.TRIDENT);
			if (hyperActive.get(player.getName()) == "GEYSER") {
				ItemMeta tridentMeta = trident.getItemMeta();
				tridentMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Geyser");
				tridentMeta.setLore(activeItemLore);
				trident.setItemMeta(tridentMeta);
				trident.addUnsafeEnchantment(CustomEnchants.GOOMBA, 1);
			} else {
				ItemMeta tridentMeta = trident.getItemMeta();
				tridentMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Geyser");
				tridentMeta.setLore(inactiveItemLore);
				trident.setItemMeta(tridentMeta);
			}

			ItemStack[] hyperPanes = { fillerPane, fillerPane, fillerPane, fillerPane, fillerPane, fillerPane,
					fillerPane, fillerPane, fillerPane, fillerPane, sword, axe, helmet, chestplate, leggings, boots,
					shield, fillerPane, fillerPane, fillerPane, fillerPane, bow, crossbow, trident, fillerPane,
					fillerPane, fillerPane, fillerPane, fillerPane, fillerPane, fillerPane, fillerPane, fillerPane,
					fillerPane, fillerPane, fillerPane };
			hyper.setContents(hyperPanes);
			player.openInventory(hyper);
		}

		// Bless command
		if (label.equalsIgnoreCase("bless")) {
			if (!(sender instanceof Player)) {
				return true;
			}
			Player player = (Player) sender;
			if (!player.isOp()) {
				return true;
			}

			player.removePotionEffect(PotionEffectType.BLINDNESS);
			player.removePotionEffect(PotionEffectType.CONFUSION);
			player.removePotionEffect(PotionEffectType.HUNGER);
			player.removePotionEffect(PotionEffectType.POISON);
			player.removePotionEffect(PotionEffectType.SLOW);
			player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
			player.removePotionEffect(PotionEffectType.WEAKNESS);
			player.removePotionEffect(PotionEffectType.WITHER);

			player.sendMessage(ChatColor.WHITE + "You have been " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Blessed"
					+ ChatColor.RESET + ChatColor.WHITE + "!");
		}

		return true;
	}

	@EventHandler
	public void hyperMenuClickEvent(InventoryClickEvent e) throws InterruptedException {
		Player player = (Player) e.getWhoClicked();
		if (!e.getView().getTitle().equals(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Hyper")) {
			return;
		}

		long currentTime = System.currentTimeMillis();
		long lastMenuClickTime = hyperMenuClickCooldown.get(player.getName());

		if (currentTime < lastMenuClickTime + 200) {
			e.setCancelled(true);
			player.sendMessage(ChatColor.RED + "Please wait longer between clicks!");
			return;
		}

		hyperMenuClickCooldown.put(player.getName(), currentTime);

		e.setCancelled(true);
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getClickedInventory().getType().equals(InventoryType.CHEST)) {
			// RECALL
			if (e.getCurrentItem().getType().equals(Material.NETHERITE_SWORD)) {
				// ACTIVATE
				if (hyperActive.get(player.getName()) == "NONE") {
					hyperActive.put(player.getName(), "RECALL");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_SWORD, "Activate", "Recall");
					e.getInventory().setItem(10, item);
				}
				// DEACTIVATE
				else if (hyperActive.get(player.getName()) == "RECALL") {
					deactivateOldHyperEnchant(player);
					hyperActive.put(player.getName(), "NONE");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.6f, 0.5f);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_SWORD, "Deactivate", "Recall");
					e.getInventory().setItem(10, item);
				}
				// ACTIVATE + DEACTIVATE OLD ENCHANT
				else {
					deactivateOldHyperEnchant(player);
					Material deactivateType = hyperFindOldMaterial(player);
					String deactivateEnchant = hyperFindOldEnchant(player);
					int deactivateSlot = hyperFindOldSlot(player);
					ItemStack deactivateItem = hyperGenerateNewItem(deactivateType, "Deactivate", deactivateEnchant);
					e.getInventory().setItem(deactivateSlot, deactivateItem);

					hyperActive.put(player.getName(), "RECALL");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_SWORD, "Activate", "Recall");
					e.getInventory().setItem(10, item);
				}
			}
			// ABYSS
			else if (e.getCurrentItem().getType().equals(Material.NETHERITE_AXE)) {
				// ACTIVATE
				if (hyperActive.get(player.getName()) == "NONE") {
					hyperActive.put(player.getName(), "ABYSS");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_AXE, "Activate", "Abyss");
					e.getInventory().setItem(11, item);
				}
				// DEACTIVATE
				else if (hyperActive.get(player.getName()) == "ABYSS") {
					deactivateOldHyperEnchant(player);
					hyperActive.put(player.getName(), "NONE");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.6f, 0.5f);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_AXE, "Deactivate", "Abyss");
					e.getInventory().setItem(11, item);
				}
				// ACTIVATE + DEACTIVATE OLD ENCHANT
				else {
					deactivateOldHyperEnchant(player);
					Material deactivateType = hyperFindOldMaterial(player);
					String deactivateEnchant = hyperFindOldEnchant(player);
					int deactivateSlot = hyperFindOldSlot(player);
					ItemStack deactivateItem = hyperGenerateNewItem(deactivateType, "Deactivate", deactivateEnchant);
					e.getInventory().setItem(deactivateSlot, deactivateItem);

					hyperActive.put(player.getName(), "ABYSS");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_AXE, "Activate", "Abyss");
					e.getInventory().setItem(11, item);
				}
			}
			// THERMAL
			else if (e.getCurrentItem().getType().equals(Material.NETHERITE_HELMET)) {
				// ACTIVATE
				if (hyperActive.get(player.getName()) == "NONE") {
					hyperActive.put(player.getName(), "THERMAL");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_HELMET, "Activate", "Thermal");
					e.getInventory().setItem(12, item);
				}
				// DEACTIVATE
				else if (hyperActive.get(player.getName()) == "THERMAL") {
					deactivateOldHyperEnchant(player);
					hyperActive.put(player.getName(), "NONE");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.6f, 0.5f);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_HELMET, "Deactivate", "Thermal");
					e.getInventory().setItem(12, item);
				}
				// ACTIVATE + DEACTIVATE OLD ENCHANT
				else {
					deactivateOldHyperEnchant(player);
					Material deactivateType = hyperFindOldMaterial(player);
					String deactivateEnchant = hyperFindOldEnchant(player);
					int deactivateSlot = hyperFindOldSlot(player);
					ItemStack deactivateItem = hyperGenerateNewItem(deactivateType, "Deactivate", deactivateEnchant);
					e.getInventory().setItem(deactivateSlot, deactivateItem);

					hyperActive.put(player.getName(), "THERMAL");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_HELMET, "Activate", "Thermal");
					e.getInventory().setItem(12, item);
				}
			}
			// TITAN
			else if (e.getCurrentItem().getType().equals(Material.NETHERITE_CHESTPLATE)) {
				// ACTIVATE
				if (hyperActive.get(player.getName()) == "NONE") {
					hyperActive.put(player.getName(), "TITAN");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_CHESTPLATE, "Activate", "Titan");
					e.getInventory().setItem(13, item);
				}
				// DEACTIVATE
				else if (hyperActive.get(player.getName()) == "TITAN") {
					deactivateOldHyperEnchant(player);
					hyperActive.put(player.getName(), "NONE");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.6f, 0.5f);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_CHESTPLATE, "Deactivate", "Titan");
					e.getInventory().setItem(13, item);
				}
				// ACTIVATE + DEACTIVATE OLD ENCHANT
				else {
					deactivateOldHyperEnchant(player);
					Material deactivateType = hyperFindOldMaterial(player);
					String deactivateEnchant = hyperFindOldEnchant(player);
					int deactivateSlot = hyperFindOldSlot(player);
					ItemStack deactivateItem = hyperGenerateNewItem(deactivateType, "Deactivate", deactivateEnchant);
					e.getInventory().setItem(deactivateSlot, deactivateItem);

					hyperActive.put(player.getName(), "TITAN");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_CHESTPLATE, "Activate", "Titan");
					e.getInventory().setItem(13, item);
				}
			}
			// INFERNO
			else if (e.getCurrentItem().getType().equals(Material.NETHERITE_LEGGINGS)) {
				// ACTIVATE
				if (hyperActive.get(player.getName()) == "NONE") {
					hyperActive.put(player.getName(), "INFERNO");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_LEGGINGS, "Activate", "Inferno");
					e.getInventory().setItem(14, item);
				}
				// DEACTIVATE
				else if (hyperActive.get(player.getName()) == "INFERNO") {
					deactivateOldHyperEnchant(player);
					hyperActive.put(player.getName(), "NONE");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.6f, 0.5f);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_LEGGINGS, "Deactivate", "Inferno");
					e.getInventory().setItem(14, item);
				}
				// ACTIVATE + DEACTIVATE OLD ENCHANT
				else {
					deactivateOldHyperEnchant(player);
					Material deactivateType = hyperFindOldMaterial(player);
					String deactivateEnchant = hyperFindOldEnchant(player);
					int deactivateSlot = hyperFindOldSlot(player);
					ItemStack deactivateItem = hyperGenerateNewItem(deactivateType, "Deactivate", deactivateEnchant);
					e.getInventory().setItem(deactivateSlot, deactivateItem);

					hyperActive.put(player.getName(), "INFERNO");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_LEGGINGS, "Activate", "Inferno");
					e.getInventory().setItem(14, item);
				}
			}
			// BLINK
			else if (e.getCurrentItem().getType().equals(Material.NETHERITE_BOOTS)) {
				// ACTIVATE
				if (hyperActive.get(player.getName()) == "NONE") {
					hyperActive.put(player.getName(), "BLINK");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_BOOTS, "Activate", "Blink");
					e.getInventory().setItem(15, item);
				}
				// DEACTIVATE
				else if (hyperActive.get(player.getName()) == "BLINK") {
					deactivateOldHyperEnchant(player);
					hyperActive.put(player.getName(), "NONE");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.6f, 0.5f);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_BOOTS, "Deactivate", "Blink");
					e.getInventory().setItem(15, item);
				}
				// ACTIVATE + DEACTIVATE OLD ENCHANT
				else {
					deactivateOldHyperEnchant(player);
					Material deactivateType = hyperFindOldMaterial(player);
					String deactivateEnchant = hyperFindOldEnchant(player);
					int deactivateSlot = hyperFindOldSlot(player);
					ItemStack deactivateItem = hyperGenerateNewItem(deactivateType, "Deactivate", deactivateEnchant);
					e.getInventory().setItem(deactivateSlot, deactivateItem);

					hyperActive.put(player.getName(), "BLINK");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.NETHERITE_BOOTS, "Activate", "Blink");
					e.getInventory().setItem(15, item);
				}
			}
			// PUMMEL
			else if (e.getCurrentItem().getType().equals(Material.SHIELD)) {
				// ACTIVATE
				if (hyperActive.get(player.getName()) == "NONE") {
					hyperActive.put(player.getName(), "PUMMEL");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.SHIELD, "Activate", "Pummel");
					e.getInventory().setItem(16, item);
				}
				// DEACTIVATE
				else if (hyperActive.get(player.getName()) == "PUMMEL") {
					deactivateOldHyperEnchant(player);
					hyperActive.put(player.getName(), "NONE");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.6f, 0.5f);
					ItemStack item = hyperGenerateNewItem(Material.SHIELD, "Deactivate", "Pummel");
					e.getInventory().setItem(16, item);
				}
				// ACTIVATE + DEACTIVATE OLD ENCHANT
				else {
					deactivateOldHyperEnchant(player);
					Material deactivateType = hyperFindOldMaterial(player);
					String deactivateEnchant = hyperFindOldEnchant(player);
					int deactivateSlot = hyperFindOldSlot(player);
					ItemStack deactivateItem = hyperGenerateNewItem(deactivateType, "Deactivate", deactivateEnchant);
					e.getInventory().setItem(deactivateSlot, deactivateItem);

					hyperActive.put(player.getName(), "PUMMEL");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.SHIELD, "Activate", "Pummel");
					e.getInventory().setItem(16, item);
				}
			}
			// OVERDRIVE
			else if (e.getCurrentItem().getType().equals(Material.BOW)) {
				// ACTIVATE
				if (hyperActive.get(player.getName()) == "NONE") {
					hyperActive.put(player.getName(), "OVERDRIVE");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.BOW, "Activate", "Overdrive");
					e.getInventory().setItem(21, item);
				}
				// DEACTIVATE
				else if (hyperActive.get(player.getName()) == "OVERDRIVE") {
					deactivateOldHyperEnchant(player);
					hyperActive.put(player.getName(), "NONE");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.6f, 0.5f);
					ItemStack item = hyperGenerateNewItem(Material.BOW, "Deactivate", "Overdrive");
					e.getInventory().setItem(21, item);
				}
				// ACTIVATE + DEACTIVATE OLD ENCHANT
				else {
					deactivateOldHyperEnchant(player);
					Material deactivateType = hyperFindOldMaterial(player);
					String deactivateEnchant = hyperFindOldEnchant(player);
					int deactivateSlot = hyperFindOldSlot(player);
					ItemStack deactivateItem = hyperGenerateNewItem(deactivateType, "Deactivate", deactivateEnchant);
					e.getInventory().setItem(deactivateSlot, deactivateItem);

					hyperActive.put(player.getName(), "OVERDRIVE");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.BOW, "Activate", "Overdrive");
					e.getInventory().setItem(21, item);
				}
			}
			// MARKSMAN
			else if (e.getCurrentItem().getType().equals(Material.CROSSBOW)) {
				// ACTIVATE
				if (hyperActive.get(player.getName()) == "NONE") {
					hyperActive.put(player.getName(), "MARKSMAN");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.CROSSBOW, "Activate", "Marksman");
					e.getInventory().setItem(22, item);
				}
				// DEACTIVATE
				else if (hyperActive.get(player.getName()) == "MARKSMAN") {
					deactivateOldHyperEnchant(player);
					hyperActive.put(player.getName(), "NONE");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.6f, 0.5f);
					ItemStack item = hyperGenerateNewItem(Material.CROSSBOW, "Deactivate", "Marksman");
					e.getInventory().setItem(22, item);
				}
				// ACTIVATE + DEACTIVATE OLD ENCHANT
				else {
					deactivateOldHyperEnchant(player);
					Material deactivateType = hyperFindOldMaterial(player);
					String deactivateEnchant = hyperFindOldEnchant(player);
					int deactivateSlot = hyperFindOldSlot(player);
					ItemStack deactivateItem = hyperGenerateNewItem(deactivateType, "Deactivate", deactivateEnchant);
					e.getInventory().setItem(deactivateSlot, deactivateItem);

					hyperActive.put(player.getName(), "MARKSMAN");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.CROSSBOW, "Activate", "Marksman");
					e.getInventory().setItem(22, item);
				}
			}
			// GEYSER
			else if (e.getCurrentItem().getType().equals(Material.TRIDENT)) {
				// ACTIVATE
				if (hyperActive.get(player.getName()) == "NONE") {
					hyperActive.put(player.getName(), "GEYSER");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.TRIDENT, "Activate", "Geyser");
					e.getInventory().setItem(23, item);
				}
				// DEACTIVATE
				else if (hyperActive.get(player.getName()) == "GEYSER") {
					deactivateOldHyperEnchant(player);
					hyperActive.put(player.getName(), "NONE");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.6f, 0.5f);
					ItemStack item = hyperGenerateNewItem(Material.TRIDENT, "Deactivate", "Geyser");
					e.getInventory().setItem(23, item);
				}
				// ACTIVATE + DEACTIVATE OLD ENCHANT
				else {
					deactivateOldHyperEnchant(player);
					Material deactivateType = hyperFindOldMaterial(player);
					String deactivateEnchant = hyperFindOldEnchant(player);
					int deactivateSlot = hyperFindOldSlot(player);
					ItemStack deactivateItem = hyperGenerateNewItem(deactivateType, "Deactivate", deactivateEnchant);
					e.getInventory().setItem(deactivateSlot, deactivateItem);

					hyperActive.put(player.getName(), "GEYSER");
					activateNewHyperEnchant(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 1);
					ItemStack item = hyperGenerateNewItem(Material.TRIDENT, "Activate", "Geyser");
					e.getInventory().setItem(23, item);
				}
			}
		}
	}

	public void activateNewHyperEnchant(Player player) {
		if (hyperActive.get(player.getName()) == "BLINK") {
			if (player.getInventory().getBoots() == null) {
				return;
			}
			ItemStack boots = player.getInventory().getBoots();
			if (!boots.hasItemMeta()) {
				return;
			}
			if (!boots.getItemMeta().hasEnchant(CustomEnchants.BLINK)) {
				return;
			}
			player.setAllowFlight(true);
			blinkCharges.put(player.getName(), 0);
			blinkLastChargeGain.put(player.getName(), (System.currentTimeMillis() / 1000) + 20);
		}
		else if (hyperActive.get(player.getName()) == "OVERDRIVE") {
			overdriveCooldown.put(player.getName(), (System.currentTimeMillis() / 1000) + 60);
		}
		else if (hyperActive.get(player.getName()) == "TITAN") {
			if (player.getInventory().getChestplate() == null) {
				return;
			}
			ItemStack chestplate = player.getInventory().getChestplate();
			if (!chestplate.hasItemMeta()) {
				return;
			}
			if (!chestplate.getItemMeta().hasEnchant(CustomEnchants.TITAN)) {
				return;
			}
			if (!player.hasPotionEffect(PotionEffectType.HEALTH_BOOST)) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 1));
			} 
			else {
				int potionLevel = player.getPotionEffect(PotionEffectType.HEALTH_BOOST).getAmplifier();
				potionLevel += 2;
				player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, potionLevel));
			}
			titanCooldown.put(player.getName(), (System.currentTimeMillis() / 1000) + 240);
		}
	}

	public void deactivateOldHyperEnchant(Player player) {
		if (hyperActive.get(player.getName()) == "BLINK") {
			player.setAllowFlight(false);
		}
		else if (hyperActive.get(player.getName()) == "OVERDRIVE") {
			//nothing to do here
		}
		if (hyperActive.get(player.getName()) == "TITAN") {
			if (player.getInventory().getChestplate() == null) {
				return;
			}
			ItemStack chestplate = player.getInventory().getChestplate();
			if (!chestplate.hasItemMeta()) {
				return;
			}
			if (!chestplate.getItemMeta().hasEnchant(CustomEnchants.TITAN)) {
				return;
			}
			if (!player.hasPotionEffect(PotionEffectType.HEALTH_BOOST)) {
				return;
			}
			int potionLevel = player.getPotionEffect(PotionEffectType.HEALTH_BOOST).getAmplifier();
			if (potionLevel <= 1) {
				player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
			} else {
				potionLevel -= 2;
				player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
				player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, potionLevel));
			}
		}
	}

	public ItemStack hyperGenerateNewItem(Material material, String type, String enchantment) {
		ArrayList<String> activeItemLore = new ArrayList<>();
		activeItemLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "ACTIVE");
		activeItemLore.add(ChatColor.DARK_PURPLE + "Click to deactivate");

		ArrayList<String> inactiveItemLore = new ArrayList<>();
		inactiveItemLore.add(ChatColor.RED + "" + ChatColor.BOLD + "INACTIVE");
		inactiveItemLore.add(ChatColor.DARK_PURPLE + "Click to activate");

		ItemStack item = new ItemStack(Material.AIR);
		if (type == "Activate") {
			item = new ItemStack(material);
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(
					ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + enchantment);
			itemMeta.setLore(activeItemLore);
			item.setItemMeta(itemMeta);
			item.addUnsafeEnchantment(CustomEnchants.GOOMBA, 1);
		} else {
			item = new ItemStack(material);
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(
					ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + enchantment);
			itemMeta.setLore(inactiveItemLore);
			item.setItemMeta(itemMeta);
		}
		return item;
	}

	public Material hyperFindOldMaterial(Player player) {
		Material material = Material.AIR;
		if (hyperActive.get(player.getName()) == "RECALL") {
			material = Material.NETHERITE_SWORD;
		} else if (hyperActive.get(player.getName()) == "ABYSS") {
			material = Material.NETHERITE_AXE;
		} else if (hyperActive.get(player.getName()) == "THERMAL") {
			material = Material.NETHERITE_HELMET;
		} else if (hyperActive.get(player.getName()) == "TITAN") {
			material = Material.NETHERITE_CHESTPLATE;
		} else if (hyperActive.get(player.getName()) == "INFERNO") {
			material = Material.NETHERITE_LEGGINGS;
		} else if (hyperActive.get(player.getName()) == "BLINK") {
			material = Material.NETHERITE_BOOTS;
		} else if (hyperActive.get(player.getName()) == "PUMMEL") {
			material = Material.SHIELD;
		} else if (hyperActive.get(player.getName()) == "OVERDRIVE") {
			material = Material.BOW;
		} else if (hyperActive.get(player.getName()) == "MARKSMAN") {
			material = Material.CROSSBOW;
		} else if (hyperActive.get(player.getName()) == "GEYSER") {
			material = Material.TRIDENT;
		}
		return material;
	}

	public String hyperFindOldEnchant(Player player) {
		String enchant = "NONE";
		if (hyperActive.get(player.getName()) == "RECALL") {
			enchant = "Recall";
		} else if (hyperActive.get(player.getName()) == "ABYSS") {
			enchant = "Abyss";
		} else if (hyperActive.get(player.getName()) == "THERMAL") {
			enchant = "Thermal";
		} else if (hyperActive.get(player.getName()) == "TITAN") {
			enchant = "Titan";
		} else if (hyperActive.get(player.getName()) == "INFERNO") {
			enchant = "Inferno";
		} else if (hyperActive.get(player.getName()) == "BLINK") {
			enchant = "Blink";
		} else if (hyperActive.get(player.getName()) == "PUMMEL") {
			enchant = "Pummel";
		} else if (hyperActive.get(player.getName()) == "OVERDRIVE") {
			enchant = "Overdrive";
		} else if (hyperActive.get(player.getName()) == "MARKSMAN") {
			enchant = "Marksman";
		} else if (hyperActive.get(player.getName()) == "GEYSER") {
			enchant = "Geyser";
		}
		return enchant;
	}

	public int hyperFindOldSlot(Player player) {
		int slot = 0;
		if (hyperActive.get(player.getName()) == "RECALL") {
			slot = 10;
		} else if (hyperActive.get(player.getName()) == "ABYSS") {
			slot = 11;
		} else if (hyperActive.get(player.getName()) == "THERMAL") {
			slot = 12;
		} else if (hyperActive.get(player.getName()) == "TITAN") {
			slot = 13;
		} else if (hyperActive.get(player.getName()) == "INFERNO") {
			slot = 14;
		} else if (hyperActive.get(player.getName()) == "BLINK") {
			slot = 15;
		} else if (hyperActive.get(player.getName()) == "PUMMEL") {
			slot = 16;
		} else if (hyperActive.get(player.getName()) == "OVERDRIVE") {
			slot = 21;
		} else if (hyperActive.get(player.getName()) == "MARKSMAN") {
			slot = 22;
		} else if (hyperActive.get(player.getName()) == "GEYSER") {
			slot = 23;
		}
		return slot;
	}

	@EventHandler
	public void setBaseLineMenuClickCooldown(PlayerJoinEvent e) {
		long currentTime = System.currentTimeMillis();
		hyperMenuClickCooldown.put(e.getPlayer().getName(), currentTime);
	}

	@EventHandler
	public void setActiveHyperEnchant(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (!player.hasPlayedBefore()) {
			hyperActive.put(player.getName(), "NONE");
		} else if (hyperActive.get(player.getName()) == null) {
			hyperActive.put(player.getName(), "NONE");
		}
	}

	// for grindstone - to delay the lore remove on success item
	@SuppressWarnings("deprecation")
	@EventHandler
	public void removeResultLore(InventoryClickEvent e) {
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getInventory().getType() == InventoryType.GRINDSTONE) {
			Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
				public void run() {
					if (e.getInventory().getItem(2) == null) {
						return;
					}
					ItemStack item = e.getInventory().getItem(2);
					if (!item.hasItemMeta()) {
						return;
					}
					ItemMeta itemMeta = item.getItemMeta();
					if (!itemMeta.hasLore()) {
						return;
					}
					List<String> currentLore = item.getItemMeta().getLore();
					int loreSize = currentLore.size();
					for (int i = loreSize - 1; i >= 0; i--) {
						currentLore.remove(i);
					}
					itemMeta.setLore(currentLore);
					item.setItemMeta(itemMeta);
					e.getInventory().setItem(2, item);
				}
			}, 1);
		}
	}

	// AQUATIC
	@EventHandler
	public void aquaticEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() != null) {
			ItemStack item = e.getItemStack();
			if (!item.hasItemMeta()) {
				return;
			}
			if (!item.getItemMeta().hasEnchant(CustomEnchants.AQUATIC)) {
				return;
			}

			player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 0));
		}
	}

	@EventHandler
	public void aquaticUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() == null) {
			return;
		}
		ItemStack playerArmor = e.getItemStack();
		if (!playerArmor.hasItemMeta()) {
			return;
		}
		if (!playerArmor.getItemMeta().hasEnchant(CustomEnchants.AQUATIC)) {
			return;
		}
		player.removePotionEffect(PotionEffectType.WATER_BREATHING);
	}

	// BLESSED
	@EventHandler
	public void blessed(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();
			if (attackingPlayer.getInventory().getItemInMainHand() == null) {
				return;
			}
			if (!attackingPlayer.getInventory().getItemInMainHand().hasItemMeta()) {
				return;
			}
			if (!attackingPlayer.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.BLESSED)) {
				return;
			}

			if (silenceActive.get(attackingPlayer.getName()).equals(true)) {
				return;
			}

			Random rand = new Random();
			int upperBound = 2000;
			int procValue = rand.nextInt(upperBound);

			if (e.getEntity() instanceof Player && procValue <= (100 + luckyValue.get(attackingPlayer.getName()))) {
				attackingPlayer.removePotionEffect(PotionEffectType.BLINDNESS);
				attackingPlayer.removePotionEffect(PotionEffectType.CONFUSION);
				attackingPlayer.removePotionEffect(PotionEffectType.HUNGER);
				attackingPlayer.removePotionEffect(PotionEffectType.POISON);
				attackingPlayer.removePotionEffect(PotionEffectType.SLOW);
				attackingPlayer.removePotionEffect(PotionEffectType.SLOW_DIGGING);
				attackingPlayer.removePotionEffect(PotionEffectType.WEAKNESS);
				attackingPlayer.removePotionEffect(PotionEffectType.WITHER);
				attackingPlayer.getWorld().playSound(attackingPlayer.getLocation(), Sound.ITEM_BUCKET_EMPTY, 10, 1);
			} 
			else if (e.getEntity() instanceof Monster && procValue <= (20 + luckyValue.get(attackingPlayer.getName()))) {
				attackingPlayer.removePotionEffect(PotionEffectType.BLINDNESS);
				attackingPlayer.removePotionEffect(PotionEffectType.CONFUSION);
				attackingPlayer.removePotionEffect(PotionEffectType.HUNGER);
				attackingPlayer.removePotionEffect(PotionEffectType.POISON);
				attackingPlayer.removePotionEffect(PotionEffectType.SLOW);
				attackingPlayer.removePotionEffect(PotionEffectType.SLOW_DIGGING);
				attackingPlayer.removePotionEffect(PotionEffectType.WEAKNESS);
				attackingPlayer.removePotionEffect(PotionEffectType.WITHER);
				attackingPlayer.getWorld().playSound(attackingPlayer.getLocation(), Sound.ITEM_BUCKET_EMPTY, 10, 1);
			}
		}
	}

	// CONFUSION
	@EventHandler
	public void confusion(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player hitPlayer = (Player) e.getEntity();
			Player attackingPlayer = (Player) e.getDamager();
			if (attackingPlayer.getInventory().getItemInMainHand() == null) {
				return;
			}
			if (!attackingPlayer.getInventory().getItemInMainHand().hasItemMeta()) {
				return;
			}
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta()
					.hasEnchant(CustomEnchants.CONFUSION)) {
				return;
			}

			if (silenceActive.get(attackingPlayer.getName()).equals(true)) {
				return;
			}

			// find attacker weapon so i can grab the level from it
			ItemStack confusionWeapon = attackingPlayer.getInventory().getItemInMainHand();

			// use random number generator to choose whether or not to proc the enchant
			Random rand = new Random();
			int upperBound = 4000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = confusionWeapon.getItemMeta().getEnchantLevel(CustomEnchants.CONFUSION);
			if (eLvl == 1 && procValue <= (80 + luckyValue.get(attackingPlayer.getName()))) {
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 120, 2));
				Location hitLocation = hitPlayer.getLocation().add(0, 1, 0);
				hitPlayer.getWorld().spawnParticle(Particle.SNEEZE, hitLocation.getX(), hitLocation.getY(),
						hitLocation.getZ(), 1, 1, 1, (float) 0.2, 10);
			} 
			else if (eLvl == 2 && procValue <= (120 + luckyValue.get(attackingPlayer.getName()))) {
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 4));
				Location hitLocation = hitPlayer.getLocation().add(0, 1, 0);
				hitPlayer.getWorld().spawnParticle(Particle.SNEEZE, hitLocation.getX(), hitLocation.getY(),
						hitLocation.getZ(), 1, 1, 1, (float) 0.2, 10);
			} 
			else if (eLvl == 3 && procValue <= (160 + luckyValue.get(attackingPlayer.getName()))) {
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 6));
				Location hitLocation = hitPlayer.getLocation().add(0, 1, 0);
				hitPlayer.getWorld().spawnParticle(Particle.SNEEZE, hitLocation.getX(), hitLocation.getY(),
						hitLocation.getZ(), 1, 1, 1, (float) 0.2, 10);
			}
		}
	}

	// CRYSTALLIZE
	@EventHandler
	public void crystallize(PlayerItemDamageEvent e) {
		Random rand = new Random();
		int upperBound = 2000;
		int procValue = rand.nextInt(upperBound);

		Player player = (Player) e.getPlayer();
		if (player.getInventory().getHelmet() != null) {
			ItemStack helmet = player.getInventory().getHelmet();
			if (!helmet.hasItemMeta()) {
				return;
			}
			if (helmet.getItemMeta().hasEnchant(CustomEnchants.CRYSTALLIZE)) {
				if (silenceActive.get(player.getName()).equals(true)) {
					return;
				}

				int eLvl = helmet.getItemMeta().getEnchantLevel(CustomEnchants.CRYSTALLIZE);
				if (eLvl == 1 && (procValue >= 0 && procValue <= (120 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
				if (eLvl == 2 && (procValue >= 0 && procValue <= (240 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
				if (eLvl == 3 && (procValue >= 0 && procValue <= (360 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
				if (eLvl == 4 && (procValue >= 0 && procValue <= (500 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
			}
		}
		if (player.getInventory().getChestplate() != null) {
			ItemStack chestplate = player.getInventory().getChestplate();
			if (!chestplate.hasItemMeta()) {
				return;
			}
			if (chestplate.getItemMeta().hasEnchant(CustomEnchants.CRYSTALLIZE)) {
				if (silenceActive.get(player.getName()).equals(true)) {
					return;
				}

				int eLvl = chestplate.getItemMeta().getEnchantLevel(CustomEnchants.CRYSTALLIZE);
				if (eLvl == 1 && (procValue >= (501 + luckyValue.get(player.getName()))
						&& procValue <= (620 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
				if (eLvl == 2 && (procValue >= (501 + luckyValue.get(player.getName()))
						&& procValue <= (740 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
				if (eLvl == 3 && (procValue >= (501 + luckyValue.get(player.getName()))
						&& procValue <= (860 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
				if (eLvl == 4 && (procValue >= (501 + luckyValue.get(player.getName()))
						&& procValue <= (1000 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
			}
		}
		if (player.getInventory().getLeggings() != null) {
			ItemStack leggings = player.getInventory().getLeggings();
			if (!leggings.hasItemMeta()) {
				return;
			}
			if (leggings.getItemMeta().hasEnchant(CustomEnchants.CRYSTALLIZE)) {
				if (silenceActive.get(player.getName()).equals(true)) {
					return;
				}

				int eLvl = leggings.getItemMeta().getEnchantLevel(CustomEnchants.CRYSTALLIZE);
				if (eLvl == 1 && (procValue >= (1001 + luckyValue.get(player.getName()))
						&& procValue <= (1120 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
				if (eLvl == 2 && (procValue >= (1001 + luckyValue.get(player.getName()))
						&& procValue <= (1240 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
				if (eLvl == 3 && (procValue >= (1001 + luckyValue.get(player.getName()))
						&& procValue <= (1360 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
				if (eLvl == 4 && (procValue >= (1001 + luckyValue.get(player.getName()))
						&& procValue <= (1500 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
			}
		}

		if (player.getInventory().getBoots() != null) {
			ItemStack boots = player.getInventory().getBoots();
			if (!boots.hasItemMeta()) {
				return;
			}
			if (boots.getItemMeta().hasEnchant(CustomEnchants.CRYSTALLIZE)) {
				if (silenceActive.get(player.getName()).equals(true)) {
					return;
				}

				int eLvl = boots.getItemMeta().getEnchantLevel(CustomEnchants.CRYSTALLIZE);
				if (eLvl == 1 && (procValue >= (1501 + luckyValue.get(player.getName()))
						&& procValue <= (1620 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
				if (eLvl == 2 && (procValue >= (1501 + luckyValue.get(player.getName()))
						&& procValue <= (1740 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
				if (eLvl == 3 && (procValue >= (1501 + luckyValue.get(player.getName()))
						&& procValue <= (1860 + luckyValue.get(player.getName())))) {
					e.setCancelled(true);
					return;
				}
				if (eLvl == 4 && (procValue >= (1501 + luckyValue.get(player.getName())) && procValue <= 2000)) {
					e.setCancelled(true);
					return;
				}
			}
		}
	}

	// GEOMANCY
	@EventHandler
	public void geomancy(BlockBreakEvent e) {
		if (e.getPlayer().getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack geomancyItem = e.getPlayer().getInventory().getItemInMainHand();
		if (!geomancyItem.hasItemMeta()) {
			return;
		}
		if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.GEOMANCY)) {
			return;
		}
		if (e.getPlayer().getGameMode() == GameMode.CREATIVE || e.getPlayer().getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (e.getBlock().getState() instanceof Container) {
			return;
		}

		Player player = e.getPlayer();

		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}

		int eLvl = geomancyItem.getItemMeta().getEnchantLevel(CustomEnchants.GEOMANCY);
		if (eLvl == 1) {
			if (e.getExpToDrop() > 0) {
				e.setExpToDrop((int) (e.getExpToDrop() * 1.2));
			}
		} 
		else if (eLvl == 2) {
			if (e.getExpToDrop() > 0) {
				e.setExpToDrop((int) (e.getExpToDrop() * 1.35));
			}
		} 
		else if (eLvl == 3) {
			if (e.getExpToDrop() > 0) {
				e.setExpToDrop((int) (e.getExpToDrop() * 1.5));
			}
		} 
		else if (eLvl == 4) {
			if (e.getExpToDrop() > 0) {
				e.setExpToDrop((int) (e.getExpToDrop() * 1.625));
			}
		} 
		else if (eLvl == 5) {
			if (e.getExpToDrop() > 0) {
				e.setExpToDrop((int) (e.getExpToDrop() * 1.75));
			}
		}
	}

	// GOOMBA
	@EventHandler
	public void goomba(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if (player.getInventory().getBoots() != null) {
			ItemStack goombaBoots = player.getInventory().getBoots();
			if (!goombaBoots.hasItemMeta()) {
				return;
			}
			if (!goombaBoots.getItemMeta().hasEnchant(CustomEnchants.GOOMBA)) {
				return;
			}
			if (silenceActive.get(player.getName()).equals(true)) {
				return;
			}

			int eLvl = goombaBoots.getItemMeta().getEnchantLevel(CustomEnchants.GOOMBA);
			if (eLvl == 1) {
				goombaEffects(player, 2);
			} 
			else if (eLvl == 2) {
				goombaEffects(player, 3);
			} 
			else if (eLvl == 3) {
				goombaEffects(player, 4);
			}
		}
	}

	// goomba effects
	public void goombaEffects(Player player, double damage) {
		for (Entity nearbyEntity : player.getNearbyEntities(2, 2, 2)) {
			if (nearbyEntity instanceof Player) {
				double nearbyEntityXMin = nearbyEntity.getLocation().getX() - 0.75;
				double nearbyEntityXMax = nearbyEntity.getLocation().getX() + 0.75;
				double nearbyEntityYMin = nearbyEntity.getLocation().getY() + 2.25;
				double nearbyEntityYMax = nearbyEntity.getLocation().getY() + 2.7;
				double nearbyEntityZMin = nearbyEntity.getLocation().getZ() - 0.75;
				double nearbyEntityZMax = nearbyEntity.getLocation().getZ() + 0.75;

				double playerX = player.getLocation().getX();
				double playerY = player.getLocation().getY();
				double playerZ = player.getLocation().getZ();

				if (playerX >= nearbyEntityXMin && playerX <= nearbyEntityXMax && playerY >= nearbyEntityYMin
						&& playerY <= nearbyEntityYMax && playerZ >= nearbyEntityZMin && playerZ <= nearbyEntityZMax) {
					((Player) nearbyEntity).damage(damage);
					player.getWorld().playSound(player.getLocation(), Sound.ENTITY_SLIME_HURT, 1, 1);
					player.setVelocity(new Vector(0, 0.8, 0));
					goombaActive.put(player.getName(), true);

					// after 30 seconds of goomba, make the player take fall damage again
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
						public void run() {
							goombaActive.put(player.getName(), false);
						}
					}, 600);
				}
			}
		}
	}

	// goomba turn off fall damage
	@EventHandler
	public void goombaFallDamageOff(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			if (silenceActive.get(e.getEntity().getName()).equals(true)) {
				return;
			}
			if (e.getCause() == DamageCause.FALL) {
				if (goombaActive.get(e.getEntity().getName()) == true) {
					goombaActive.put(e.getEntity().getName(), false);
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void setGoombaVariable(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		goombaActive.put(player.getName(), false);
	}

	// GUILLOTINE
	@EventHandler
	public void guillotine(PlayerDeathEvent e) {
		Player deadPlayer = e.getEntity();
		if (!(deadPlayer.getKiller() instanceof Player)) {
			return;
		}
		Player killer = deadPlayer.getKiller();
		if (killer.getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack weapon = killer.getInventory().getItemInMainHand();
		if (!weapon.hasItemMeta()) {
			return;
		}
		if (!weapon.getItemMeta().hasEnchant(CustomEnchants.GUILLOTINE)) {
			return;
		}
		ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
		skullMeta.setOwningPlayer(e.getEntity());
		ArrayList<String> skullLore = new ArrayList<>();
		skullLore.add(ChatColor.WHITE + "Killed by: " + ChatColor.DARK_RED + "" + ChatColor.BOLD
				+ e.getEntity().getKiller().getName());
		skullMeta.setLore(skullLore);
		playerHead.setItemMeta(skullMeta);
		e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), playerHead);
		e.getEntity().getLocation().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_GHAST_SCREAM, 1,
				0.5f);
	}

	// NOCTURNAL
	@EventHandler
	public void nocturnalEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() != null) {
			ItemStack item = e.getItemStack();
			if (!item.hasItemMeta()) {
				return;
			}
			if (!item.getItemMeta().hasEnchant(CustomEnchants.NOCTURNAL)) {
				return;
			}
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
		}
	}

	@EventHandler
	public void nocturnalUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() == null) {
			return;
		}
		ItemStack playerArmor = e.getItemStack();
		if (!playerArmor.hasItemMeta()) {
			return;
		}
		if (!playerArmor.getItemMeta().hasEnchant(CustomEnchants.NOCTURNAL)) {
			return;
		}
		player.removePotionEffect(PotionEffectType.NIGHT_VISION);
	}

	//PILLAGE
	@EventHandler
	public void pillage(EntityDeathEvent e) {
		if (e.getEntity() == null) {
			return;
		}
		if (e.getEntity().getKiller() == null) {
			return;
		}
		Player player = e.getEntity().getKiller();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		
		if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta() &&
			player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.PILLAGE)) {
			int eLvl = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(CustomEnchants.PILLAGE);
			if (eLvl == 1) {
				if (e.getDrops() == null) {
					return;
				}
				List<ItemStack> drops = e.getDrops();
				for (ItemStack item : drops) {
					item.setAmount((int) (item.getAmount() * 1.5));
				}
				return;
			} 
			else if (eLvl == 2) {
				if (e.getDrops() == null) {
					return;
				}
				List<ItemStack> drops = e.getDrops();
				for (ItemStack item : drops) {
					item.setAmount((int) (item.getAmount() * 2));
				}
				return;
			}
		}
		else if (player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta() &&
				 player.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.PUNCTURE)) {
			int eLvl = player.getInventory().getItemInOffHand().getItemMeta().getEnchantLevel(CustomEnchants.PILLAGE);
			if (eLvl == 1) {
				if (e.getDrops() == null) {
					return;
				}
				List<ItemStack> drops = e.getDrops();
				for (ItemStack item : drops) {
					item.setAmount((int) (item.getAmount() * 1.5));
				}
				return;
			} 
			else if (eLvl == 2) {
				if (e.getDrops() == null) {
					return;
				}
				List<ItemStack> drops = e.getDrops();
				for (ItemStack item : drops) {
					item.setAmount((int) (item.getAmount() * 2));
				}
				return;
			}
		}
	}
	

	// PLUNGE
	@EventHandler
	public void plunge(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getDamager();
		if (!(e.getEntity() instanceof LivingEntity)) {
			return;
		}
		LivingEntity entity = (LivingEntity) e.getEntity();
		if (player.getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack item = player.getInventory().getItemInMainHand();
		if (!item.hasItemMeta()) {
			return;
		}
		if (!item.getItemMeta().hasEnchant(CustomEnchants.PLUNGE)) {
			return;
		}
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}

		Random rand = new Random();
		int upperBound = 4000;
		int procValue = rand.nextInt(upperBound);
		if (procValue <= (80 + luckyValue.get(player.getName()))) {
			if (entity.isDead()) {
				return;
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					entity.damage(4);
					entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_HURT_DROWN, 1, 1);
				}
			}, 20);
			if (entity.isDead()) {
				return;
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					entity.damage(4);
					entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_HURT_DROWN, 1, 1);
				}
			}, 40);
			if (entity.isDead()) {
				return;
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					entity.damage(4);
					entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_HURT_DROWN, 1, 1);
				}
			}, 60);
		}
	}

	@EventHandler
	public void plungeThrow(ProjectileHitEvent e) {
		if (!(e.getEntity() instanceof Trident)) {
			return;
		}
		Trident item = (Trident) e.getEntity();
		if (!(e.getHitEntity() instanceof LivingEntity)) {
			return;
		}
		LivingEntity entity = (LivingEntity) e.getHitEntity();
		if (!(item.getShooter() instanceof Player)) {
			return;
		}
		Player player = (Player) item.getShooter();
		if (player.getInventory().getItemInMainHand() == null) {
			return;
		}
		if (!item.getItem().hasItemMeta()) {
			return;
		}
		if (!item.getItem().getItemMeta().hasEnchant(CustomEnchants.PLUNGE)) {
			return;
		}
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}

		Random rand = new Random();
		int upperBound = 4000;
		int procValue = rand.nextInt(upperBound);
		if (procValue <= (80 + luckyValue.get(player.getName()))) {
			if (entity.isDead()) {
				return;
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					if (entity.isDead()) {
						return;
					} else {
						entity.damage(4);
						entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_HURT_DROWN, 1, 1);
					}
				}
			}, 20);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					if (entity.isDead()) {
						return;
					} else {
						entity.damage(4);
						entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_HURT_DROWN, 1, 1);
					}
				}
			}, 40);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					if (entity.isDead()) {
						return;
					} else {
						entity.damage(4);
						entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_HURT_DROWN, 1, 1);
					}
				}
			}, 60);
		}
	}

	// POSEIDON
	@EventHandler
	public void poseidon(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getDamager();
		if (!(e.getEntity() instanceof LivingEntity)) {
			return;
		}
		LivingEntity entity = (LivingEntity) e.getEntity();
		if (player.getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack item = player.getInventory().getItemInMainHand();
		if (!item.hasItemMeta()) {
			return;
		}
		if (!item.getItemMeta().hasEnchant(CustomEnchants.POSEIDON)) {
			return;
		}
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}

		Random rand = new Random();
		int upperBound = 4000;
		int procValue = rand.nextInt(upperBound);

		if (procValue <= 200 + luckyValue.get(player.getName())) {
			if (entity.isDead()) {
				return;
			}
			entity.setMaximumNoDamageTicks(0);
			entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_DEATH, 1, 0.5f);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					if (entity.isDead()) {
						return;
					} else {
						entity.damage(e.getDamage() / 2.5);
						entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_DEATH, 1, 0.5f);
						entity.setMaximumNoDamageTicks(0);
					}
				}
			}, 3);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					if (entity.isDead()) {
						return;
					} else {
						entity.damage(e.getDamage() / 4);
						entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_DEATH, 1, 0.5f);
						entity.setMaximumNoDamageTicks(0);
					}
				}
			}, 6);
		}
	}

	@EventHandler
	public void poseidonThrow(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Trident)) {
			return;
		}
		Trident item = (Trident) e.getDamager();
		if (!(e.getEntity() instanceof LivingEntity)) {
			return;
		}
		LivingEntity entity = (LivingEntity) e.getEntity();
		if (!(item.getShooter() instanceof Player)) {
			return;
		}
		Player player = (Player) item.getShooter();
		if (!item.getItem().hasItemMeta()) {
			return;
		}
		if (!item.getItem().getItemMeta().hasEnchant(CustomEnchants.POSEIDON)) {
			return;
		}
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}

		Random rand = new Random();
		int upperBound = 4000;
		int procValue = rand.nextInt(upperBound);

		if (procValue <= 200 + luckyValue.get(player.getName())) {
			if (entity.isDead()) {
				return;
			}
			entity.setMaximumNoDamageTicks(0);
			entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_DEATH, 1, 0.5f);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					if (entity.isDead()) {
						return;
					} else {
						entity.damage(e.getDamage() / 2.5);
						entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_DEATH, 1, 0.5f);
						entity.setMaximumNoDamageTicks(0);
					}
				}
			}, 3);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					if (entity.isDead()) {
						return;
					} else {
						entity.damage(e.getDamage() / 4);
						entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_DEATH, 1, 0.5f);
						entity.setMaximumNoDamageTicks(0);
					}
				}
			}, 6);
		}
	}

	// REFLECT main hand
	@EventHandler
	public void reflectMainHand(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player hit = (Player) e.getEntity();

		if (hit.getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack shield = hit.getInventory().getItemInMainHand();
		if (!shield.hasItemMeta()) {
			return;
		}
		if (!shield.getItemMeta().hasEnchant(CustomEnchants.REFLECT)) {
			return;
		}
		if (silenceActive.get(hit.getName()).equals(true)) {
			return;
		}
		if (!hit.isBlocking()) {
			return;
		}

		Random rand = new Random();
		int upperBound = 2000;
		int procValue = rand.nextInt(upperBound);

		if (procValue <= 260 + luckyValue.get(hit.getName())) {
			e.setCancelled(true);
			if (e.getDamager() instanceof LivingEntity) {
				LivingEntity damager = (LivingEntity) e.getDamager();
				damager.damage(e.getDamage() / 1.33);
				hit.getWorld().playSound(hit.getLocation(), Sound.ITEM_TRIDENT_RETURN, 3, (float) 0.5);
			}
		}
	}

	// REFLECT off hand
	@EventHandler
	public void reflectOffHand(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player hit = (Player) e.getEntity();

		if (hit.getInventory().getItemInOffHand() == null) {
			return;
		}
		ItemStack shield = hit.getInventory().getItemInOffHand();
		if (!shield.hasItemMeta()) {
			return;
		}
		if (!shield.getItemMeta().hasEnchant(CustomEnchants.REFLECT)) {
			return;
		}
		if (silenceActive.get(hit.getName()).equals(true)) {
			return;
		}
		if (!hit.isBlocking()) {
			return;
		}

		Random rand = new Random();
		int upperBound = 2000;
		int procValue = rand.nextInt(upperBound);

		if (procValue <= 260 + luckyValue.get(hit.getName())) {
			e.setCancelled(true);
			if (e.getDamager() instanceof LivingEntity) {
				LivingEntity damager = (LivingEntity) e.getDamager();
				damager.damage(e.getDamage() / 1.33);
				hit.getWorld().playSound(hit.getLocation(), Sound.ITEM_TRIDENT_RETURN, 3, (float) 0.5);
			}
		}
	}

	// RESTED
	@EventHandler
	public void rested(EntitySpawnEvent e) {
		if (!(e.getEntity() instanceof Phantom)) {
			return;
		}
		Phantom phantom = (Phantom) e.getEntity();
		LivingEntity target = phantom.getTarget();
		if (target instanceof Player) {
			Player player = (Player) phantom.getTarget();
			if (silenceActive.get(player.getName()).equals(true)) {
				return;
			}
			if (player.getInventory().getHelmet() == null) {
				return;
			}
			ItemStack helmet = player.getInventory().getHelmet();
			if (!helmet.hasItemMeta()) {
				return;
			}
			if (!helmet.getItemMeta().hasEnchant(CustomEnchants.RESTED)) {
				return;
			}
			e.setCancelled(true);
		}
	}

	// RESTED target event
	@EventHandler
	public void restedTarget(EntityTargetEvent e) {
		if (!(e.getEntity() instanceof Phantom)) {
			return;
		}
		Phantom phantom = (Phantom) e.getEntity();
		Entity target = e.getTarget();
		if (target instanceof Player) {
			Player player = (Player) e.getTarget();
			if (silenceActive.get(player.getName()).equals(true)) {
				return;
			}
			if (player.getInventory().getHelmet() == null) {
				return;
			}
			ItemStack helmet = player.getInventory().getHelmet();
			if (!helmet.hasItemMeta()) {
				return;
			}
			if (!helmet.getItemMeta().hasEnchant(CustomEnchants.RESTED)) {
				return;
			}
			phantom.remove();
		}
	}

	// SHIFT
	@EventHandler
	public void shift(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof EnderPearl && e.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}

		if (player.getInventory().getBoots() == null) {
			return;
		}
		ItemStack boots = player.getInventory().getBoots();

		if (!boots.hasItemMeta()) {
			return;
		}
		if (!boots.getItemMeta().hasEnchant(CustomEnchants.SHIFT)) {
			return;
		}

		e.setCancelled(true);
	}

	// SHROOMY
	@EventHandler
	public void shroomy(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}

		if (player.getInventory().getLeggings() == null) {
			return;
		}
		ItemStack leggings = player.getInventory().getLeggings();

		if (!leggings.hasItemMeta()) {
			return;
		}
		if (!leggings.getItemMeta().hasEnchant(CustomEnchants.SHROOMY)) {
			return;
		}
		if (player.getHealth() <= 8) {
			return;
		}
		if (!(e.getDamager() instanceof LivingEntity)) {
			return;
		}
		if (shroomyActive.get(player.getName()).equals(true)) {
			return;
		}
		LivingEntity damager = (LivingEntity) e.getDamager();

		Random r = new Random();
		int upper = 8000;
		int procValue = r.nextInt(upper);

		int eLvl = leggings.getItemMeta().getEnchantLevel(CustomEnchants.SHROOMY);
		if (eLvl == 1 && procValue <= (80 + luckyValue.get(player.getName()))) {
			damager.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 10));
			new BukkitRunnable() {
				public void run() {
					shroomyActive.put(player.getName(), true);
					int counter = shroomyCounter.get(player.getName());

					if (counter % 3 == 0) {
						damager.setNoDamageTicks(0);
						damager.damage(0.15);
					}

					shroomyCounter.put(player.getName(), shroomyCounter.get(player.getName()) + 1);
					if (shroomyCounter.get(player.getName()) == 40 || player.getHealth() <= 8) {
						shroomyCounter.put(player.getName(), 0);
						shroomyActive.put(player.getName(), false);
						cancel();
					}
				}
			}.runTaskTimer(this, 1, 1);
		} 
		else if (eLvl == 2 && procValue <= (90 + luckyValue.get(player.getName()))) {
			damager.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 10));
			new BukkitRunnable() {
				public void run() {
					shroomyActive.put(player.getName(), true);
					int counter = shroomyCounter.get(player.getName());

					if (counter % 3 == 0) {
						damager.setNoDamageTicks(0);
						damager.damage(0.2);
					}

					shroomyCounter.put(player.getName(), shroomyCounter.get(player.getName()) + 1);
					if (counter == 0 || counter == 30) {
						damager.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
					}
					if (shroomyCounter.get(player.getName()) == 60) {
						shroomyCounter.put(player.getName(), 0);
						shroomyActive.put(player.getName(), false);
						cancel();
					}
				}
			}.runTaskTimer(this, 1, 1);
		} 
		else if (eLvl == 3 && procValue <= (100 + luckyValue.get(player.getName()))) {
			damager.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 70, 15));
			new BukkitRunnable() {
				public void run() {
					shroomyActive.put(player.getName(), true);
					int counter = shroomyCounter.get(player.getName());

					if (counter % 3 == 0) {
						damager.setNoDamageTicks(0);
						damager.damage(0.25);
					}

					shroomyCounter.put(player.getName(), shroomyCounter.get(player.getName()) + 1);
					if (counter == 0 || counter == 35) {
						damager.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 25, 0));
					}
					if (shroomyCounter.get(player.getName()) == 70) {
						shroomyCounter.put(player.getName(), 0);
						shroomyActive.put(player.getName(), false);
						cancel();
					}
				}
			}.runTaskTimer(this, 1, 1);
		} 
		else if (eLvl == 4 && procValue <= (110 + luckyValue.get(player.getName()))) {
			damager.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 80, 20));
			new BukkitRunnable() {
				public void run() {
					shroomyActive.put(player.getName(), true);
					int counter = shroomyCounter.get(player.getName());

					if (counter % 3 == 0) {
						damager.setNoDamageTicks(0);
						damager.damage(0.3);
					}

					shroomyCounter.put(player.getName(), shroomyCounter.get(player.getName()) + 1);
					if (counter == 0 || counter == 40) {
						damager.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 0));
					}
					if (shroomyCounter.get(player.getName()) == 80) {
						shroomyCounter.put(player.getName(), 0);
						shroomyActive.put(player.getName(), false);
						cancel();
					}
				}
			}.runTaskTimer(this, 1, 1);
		}
	}

	// shroomy counter set
	@EventHandler
	public void shroomyVariables(PlayerJoinEvent e) {
		shroomyCounter.put(e.getPlayer().getName(), 0);
		shroomyActive.put(e.getPlayer().getName(), false);
	}

	// SHROUD
	@EventHandler
	public void shroud(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity && e.getDamager() instanceof Player) {
			LivingEntity hitPlayer = (LivingEntity) e.getEntity();
			Player attackingPlayer = (Player) e.getDamager();
			if (attackingPlayer.getInventory().getItemInMainHand() == null) {
				return;
			}
			if (!attackingPlayer.getInventory().getItemInMainHand().hasItemMeta()) {
				return;
			}
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta()
					.hasEnchant(CustomEnchants.SHROUD)) {
				return;
			}

			if (silenceActive.get(attackingPlayer.getName()).equals(true)) {
				return;
			}
			ItemStack shroudWeapon = attackingPlayer.getInventory().getItemInMainHand();

			Random rand = new Random();
			int upperBound = 3000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = shroudWeapon.getItemMeta().getEnchantLevel(CustomEnchants.SHROUD);
			if (eLvl == 1 && procValue <= (55 + luckyValue.get(attackingPlayer.getName()))) {
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 2));
			} else if (eLvl == 2 && procValue <= (70 + luckyValue.get(attackingPlayer.getName()))) {
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 75, 2));
			} else if (eLvl == 3 && procValue <= (85 + luckyValue.get(attackingPlayer.getName()))) {
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 90, 2));
			} else if (eLvl == 4 && procValue <= (100 + luckyValue.get(attackingPlayer.getName()))) {
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 105, 2));
			}
		}
	}

	@EventHandler
	public void shroudThrow(ProjectileHitEvent e) {
		if (e.getEntity() instanceof Trident && e.getHitEntity() instanceof LivingEntity) {
			Trident trident = (Trident) e.getEntity();
			LivingEntity hitPlayer = (LivingEntity) e.getHitEntity();
			if (!trident.getItem().hasItemMeta()) {
				return;
			}
			if (!trident.getItem().getItemMeta().hasEnchant(CustomEnchants.SHROUD)) {
				return;
			}
			if (!(trident.getShooter() instanceof Player)) {
				return;
			}
			Player thrower = (Player) trident.getShooter();
			if (silenceActive.get(thrower.getName()).equals(true)) {
				return;
			}

			Random rand = new Random();
			int upperBound = 3000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = trident.getItem().getItemMeta().getEnchantLevel(CustomEnchants.SHROUD);
			if (eLvl == 1 && procValue <= (55 + luckyValue.get(thrower.getName()))) {
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 2));
			} else if (eLvl == 2 && procValue <= (70 + luckyValue.get(thrower.getName()))) {
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 75, 2));
			} else if (eLvl == 3 && procValue <= (85 + luckyValue.get(thrower.getName()))) {
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 90, 2));
			} else if (eLvl == 4 && procValue <= (100 + luckyValue.get(thrower.getName()))) {
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 105, 2));
			}
		}
	}

	// SOW
	@EventHandler
	public void sow(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}

		if (player.getInventory().getChestplate() == null) {
			return;
		}
		ItemStack chestplate = player.getInventory().getChestplate();

		if (!chestplate.hasItemMeta()) {
			return;
		}
		if (!chestplate.getItemMeta().hasEnchant(CustomEnchants.SOW)) {
			return;
		}

		Block block = e.getBlock();
		World world = block.getWorld();
		Location l = block.getLocation();
		BlockData data = block.getBlockData();
		if (data instanceof Ageable) {
			Ageable age = (Ageable) data;
			if (age.getAge() == age.getMaximumAge()) {
				int minValue = 0;
				int maxValue = 0;
				Material type = null;
				if (block.getType().equals(Material.WHEAT)) {
					minValue = 1;
					maxValue = 2;
					type = Material.WHEAT;
				} else if (block.getType().equals(Material.CARROTS)) {
					minValue = 2;
					maxValue = 5;
					type = Material.CARROT;
				} else if (block.getType().equals(Material.POTATOES)) {
					minValue = 2;
					maxValue = 5;
					type = Material.POTATO;
				} else if (block.getType().equals(Material.BEETROOTS)) {
					minValue = 1;
					maxValue = 2;
					type = Material.BEETROOT;
				} else if (block.getType().equals(Material.NETHER_WART)) {
					minValue = 2;
					maxValue = 4;
					type = Material.NETHER_WART;
				} else if (block.getType().equals(Material.COCOA_BEANS)) {
					minValue = 2;
					maxValue = 3;
					type = Material.COCOA;
				} else {
					return;
				}
				e.setDropItems(false);
				Random rand = new Random();
				int r = rand.nextInt(maxValue + 1 - minValue) + minValue;
				for (int i = 0; i < r; i++) {
					world.dropItemNaturally(l, new ItemStack(type));
				}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						e.setCancelled(true);
						age.setAge(0);
						block.setBlockData(age);
					}
				}, 10);
			}
		}
	}

	
	//STARVE
	@EventHandler
	public void starve(ProjectileHitEvent e) {
		if (!(e.getEntity().getShooter() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity().getShooter();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}	
		if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta() &&
			player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.STARVE)) {
			Random rand = new Random();
			int upperBound = 2000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(CustomEnchants.STARVE);
			if (eLvl == 1 && procValue <= (100 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 80, 3));
					}
				}
			} 
			else if (eLvl == 2 && procValue <= (115 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 3));
					}
				}
			} 
			else if (eLvl == 3 && procValue <= (130 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 4));
					}
				}
			} 
			else if (eLvl == 4 && procValue <= (145 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 120, 4));
					}
				}
			} 
			else if (eLvl == 5 && procValue <= (160 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 120, 5));
					}
				}
			} 
			else if (eLvl == 6 && procValue <= (175 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 140, 5));
					}
				}
			} 
			else if (eLvl == 7 && procValue <= (190 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 140, 6));
					}
				}
			}
		}
		else if (player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta() &&
				 player.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.STARVE)) {
			Random rand = new Random();
			int upperBound = 2000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = player.getInventory().getItemInOffHand().getItemMeta().getEnchantLevel(CustomEnchants.STARVE);
			if (eLvl == 1 && procValue <= (100 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 80, 3));
					}
				}
			} 
			else if (eLvl == 2 && procValue <= (115 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 3));
					}
				}
			} 
			else if (eLvl == 3 && procValue <= (130 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 4));
					}
				}
			} 
			else if (eLvl == 4 && procValue <= (145 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 120, 4));
					}
				}
			} 
			else if (eLvl == 5 && procValue <= (160 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 120, 5));
					}
				}
			} 
			else if (eLvl == 6 && procValue <= (175 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 140, 5));
					}
				}
			} 
			else if (eLvl == 7 && procValue <= (190 + luckyValue.get(player.getName()))) {
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 140, 6));
					}
				}
			}
		}
	}
	

	// TELEPATHY
	@EventHandler
	public void telepathy(BlockBreakEvent e) {
		if (e.getPlayer().getInventory().getItemInMainHand() == null) {
			return;
		}
		if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
			return;
		}
		if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.TELEPATHY)) {
			return;
		}
		if (e.getPlayer().getGameMode() == GameMode.CREATIVE || e.getPlayer().getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (e.getPlayer().getInventory().firstEmpty() == -1) {
			return;
		}
		if (e.getBlock().getState() instanceof Container) {
			return;
		}
		if (silenceActive.get(e.getPlayer().getName()).equals(true)) {
			return;
		}

		e.setDropItems(false);
		Player player = e.getPlayer();
		Block block = e.getBlock();

		Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());
		if (drops.isEmpty()) {
			return;
		}
		player.getInventory().addItem(drops.iterator().next());
	}

	
	//THUNDERBOLT
	@EventHandler
	public void thunderbolt(ProjectileHitEvent e) {
		if (!(e.getEntity().getShooter() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity().getShooter();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}	
		if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta() &&
			player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.THUNDERBOLT)) {
			Random rand = new Random();
			int upperBound = 6000;
			if (player.getInventory().getItemInMainHand().getType().equals(Material.CROSSBOW)) {
				upperBound = 12000;
			}
			int procValue = rand.nextInt(upperBound);

			int eLvl = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(CustomEnchants.THUNDERBOLT);
			if (eLvl == 1 && procValue <= (120 + luckyValue.get(player.getName()))) {
				e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).damage(3);
					}
				}
			} 
			else if (eLvl == 2 && procValue <= (160 + luckyValue.get(player.getName()))) {
				e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).damage(5);
					}
				}
			}
		}
		else if (player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta() &&
				 player.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.THUNDERBOLT)) {
			Random rand = new Random();
			int upperBound = 6000;
			if (player.getInventory().getItemInOffHand().getType().equals(Material.CROSSBOW)) {
				upperBound = 12000;
			}
			int procValue = rand.nextInt(upperBound);

			int eLvl = player.getInventory().getItemInOffHand().getItemMeta().getEnchantLevel(CustomEnchants.THUNDERBOLT);
			if (eLvl == 1 && procValue <= (120 + luckyValue.get(player.getName()))) {
				e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
				for (Entity nearby : e.getEntity().getNearbyEntities(1, 2, 1)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).damage(3);
					}
				}
			} 
			else if (eLvl == 2 && procValue <= (160 + luckyValue.get(player.getName()))) {
				e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
				for (Entity nearby : e.getEntity().getNearbyEntities(2, 2, 2)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).damage(5);
					}
				}
			}
		}
	}
	

	// VENOM
	@EventHandler
	public void venom(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();
			if (attackingPlayer.getInventory().getItemInMainHand() == null) {
				return;
			}
			ItemStack venomWeapon = attackingPlayer.getInventory().getItemInMainHand();
			if (!venomWeapon.hasItemMeta()) {
				return;
			}
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta()
					.hasEnchant(CustomEnchants.VENOM)) {
				return;
			}
			if (silenceActive.get(attackingPlayer.getName()).equals(true)) {
				return;
			}

			Random rand = new Random();
			int upperBound = 6000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = venomWeapon.getItemMeta().getEnchantLevel(CustomEnchants.VENOM);
			if (eLvl == 1 && procValue <= (120 + luckyValue.get(attackingPlayer.getName()))) {
				if (e.getEntity() instanceof LivingEntity) {
					LivingEntity hitPlayer = (LivingEntity) e.getEntity();
					hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 6));
					hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ENTITY_BEE_STING, 0.1f, 1);
				}
			} 
			else if (eLvl == 2 && procValue <= (160 + luckyValue.get(attackingPlayer.getName()))) {
				if (e.getEntity() instanceof LivingEntity) {
					LivingEntity hitPlayer = (LivingEntity) e.getEntity();
					hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 9));
					hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ENTITY_BEE_STING, 0.1f, 1);
				}
			}
		}
	}

	// ABSORB main hand
	@EventHandler
	public void absorbMainHand(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player hit = (Player) e.getEntity();

		if (hit.getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack shield = hit.getInventory().getItemInMainHand();
		if (!shield.hasItemMeta()) {
			return;
		}
		if (!shield.getItemMeta().hasEnchant(CustomEnchants.ABSORB)) {
			return;
		}
		if (silenceActive.get(hit.getName()).equals(true)) {
			return;
		}
		if (!hit.isBlocking()) {
			return;
		}

		int eLvl = shield.getItemMeta().getEnchantLevel(CustomEnchants.ABSORB);
		if (eLvl == 1) {
			e.setDamage(e.getDamage() / 2);
		} 
		else if (eLvl == 2) {
			e.setDamage(e.getDamage() / 4);
		}
	}

	// ABSORB off hand
	@EventHandler
	public void absorbOffHand(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player hit = (Player) e.getEntity();

		if (hit.getInventory().getItemInOffHand() == null) {
			return;
		}
		ItemStack shield = hit.getInventory().getItemInOffHand();
		if (!shield.hasItemMeta()) {
			return;
		}
		if (!shield.getItemMeta().hasEnchant(CustomEnchants.ABSORB)) {
			return;
		}
		if (silenceActive.get(hit.getName()).equals(true)) {
			return;
		}
		if (!hit.isBlocking()) {
			return;
		}

		int eLvl = shield.getItemMeta().getEnchantLevel(CustomEnchants.ABSORB);
		if (eLvl == 1) {
			e.setDamage(e.getDamage() / 2);
		} 
		else if (eLvl == 2) {
			e.setDamage(e.getDamage() / 4);
		}
	}

	// CLEAVE
	@EventHandler
	public void cleave(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();

			if (attackingPlayer.getInventory().getItemInMainHand() == null) {
				return;
			}
			ItemStack cleaveWeapon = attackingPlayer.getInventory().getItemInMainHand();
			if (!cleaveWeapon.hasItemMeta()) {
				return;
			}
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta()
					.hasEnchant(CustomEnchants.CLEAVE)) {
				return;
			}
			if (silenceActive.get(attackingPlayer.getName()).equals(true)) {
				return;
			}

			int eLvl = cleaveWeapon.getItemMeta().getEnchantLevel(CustomEnchants.CLEAVE);
			int AOEdistance = 3;
			int damageValue = 1;
			Random rand = new Random();
			if (eLvl == 1) {
				// use random number generator to generate the damage dealt in AOE
				int upperBoundDamage = 1;
				damageValue = rand.nextInt(upperBoundDamage);
				AOEdistance = 3;
				if (e.getEntity() instanceof Player) {
					Player hitPlayer = (Player) e.getEntity();
					for (Entity nearbyEntity : attackingPlayer.getNearbyEntities(AOEdistance, AOEdistance,
							AOEdistance)) {
						if (nearbyEntity instanceof Player) {
							if (hitPlayer.getName() == nearbyEntity.getName()) {
								// do not damage the already hit player
							} else {
								((Damageable) nearbyEntity).damage(damageValue + 1);
							}
						}
					}
				}
			} else if (eLvl == 2) {
				int upperBoundDamage = 2;
				damageValue = rand.nextInt(upperBoundDamage);
				AOEdistance = 4;
				if (e.getEntity() instanceof Player) {
					Player hitPlayer = (Player) e.getEntity();
					for (Entity nearbyEntity : attackingPlayer.getNearbyEntities(AOEdistance, AOEdistance,
							AOEdistance)) {
						if (nearbyEntity instanceof Player) {
							if (hitPlayer.getName() == nearbyEntity.getName()) {
								// do not damage the already hit player
							} else {
								((Damageable) nearbyEntity).damage(damageValue + 1);
							}
						}
					}
				}
			} else if (eLvl == 3) {
				int upperBoundDamage = 3;
				damageValue = rand.nextInt(upperBoundDamage);
				AOEdistance = 4;
				if (e.getEntity() instanceof Player) {
					Player hitPlayer = (Player) e.getEntity();
					for (Entity nearbyEntity : attackingPlayer.getNearbyEntities(AOEdistance, AOEdistance,
							AOEdistance)) {
						if (nearbyEntity instanceof Player) {
							if (hitPlayer.getName() == nearbyEntity.getName()) {
								// do not damage the already hit player
							} else {
								((Damageable) nearbyEntity).damage(damageValue + 1);
							}
						}
					}
				}
			} else if (eLvl == 4) {
				int upperBoundDamage = 4;
				damageValue = rand.nextInt(upperBoundDamage);
				AOEdistance = 5;
				if (e.getEntity() instanceof Player) {
					Player hitPlayer = (Player) e.getEntity();
					for (Entity nearbyEntity : attackingPlayer.getNearbyEntities(AOEdistance, AOEdistance,
							AOEdistance)) {
						if (nearbyEntity instanceof Player) {
							if (hitPlayer.getName() == nearbyEntity.getName()) {
								// do not damage the already hit player
							} else {
								((Damageable) nearbyEntity).damage(damageValue + 1);
							}
						}
					}
				}
			} else if (eLvl == 5) {
				int upperBoundDamage = 5;
				damageValue = rand.nextInt(upperBoundDamage);
				AOEdistance = 7;
				if (e.getEntity() instanceof Player) {
					Player hitPlayer = (Player) e.getEntity();
					for (Entity nearbyEntity : attackingPlayer.getNearbyEntities(AOEdistance, AOEdistance,
							AOEdistance)) {
						if (nearbyEntity instanceof Player) {
							if (hitPlayer.getName() == nearbyEntity.getName()) {
								// do not damage the already hit player
							} else {
								((Damageable) nearbyEntity).damage(damageValue + 1);
							}
						}
					}
				}
			}
		}
	}

	
	//DEVASTATE
	@EventHandler
	public void devastate(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Projectile)) {
			return;
		}
		Projectile projectile = (Projectile) e.getDamager();
		if (!(projectile.getShooter() instanceof Player)) {
			return;
		}
		Player player = (Player) projectile.getShooter();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta() &&
			player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.DEVASTATE)) {
			if (e.getEntity() instanceof Player) {
				Player hit = (Player) e.getEntity();
				if (hit.getInventory().getItemInMainHand().toString().contains("_AXE")
						|| hit.getInventory().getItemInMainHand().toString().contains("_SWORD")) {
					int eLvl = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(CustomEnchants.DEVASTATE);
					if (eLvl == 1) {
						e.setDamage(e.getDamage() * 1.15);
					} else if (eLvl == 2) {
						e.setDamage(e.getDamage() * 1.2);
					} else if (eLvl == 3) {
						e.setDamage(e.getDamage() * 1.25);
					}
				}
			}
		}
		else if (player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta() &&
				 player.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.DEVASTATE)) {
			if (e.getEntity() instanceof Player) {
				Player hit = (Player) e.getEntity();
				if (hit.getInventory().getItemInMainHand().toString().contains("_AXE")
						|| hit.getInventory().getItemInMainHand().toString().contains("_SWORD")) {
					int eLvl = player.getInventory().getItemInOffHand().getItemMeta().getEnchantLevel(CustomEnchants.DEVASTATE);
					if (eLvl == 1) {
						e.setDamage(e.getDamage() * 1.15);
					} else if (eLvl == 2) {
						e.setDamage(e.getDamage() * 1.2);
					} else if (eLvl == 3) {
						e.setDamage(e.getDamage() * 1.25);
					}
				}
			}
		}
	}
	

	// DIMINISH
	@EventHandler
	public void diminish(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity && e.getDamager() instanceof Player) {
			LivingEntity hitPlayer = (LivingEntity) e.getEntity();
			Player attackingPlayer = (Player) e.getDamager();
			if (attackingPlayer.getInventory().getItemInMainHand() == null) {
				return;
			}
			if (!attackingPlayer.getInventory().getItemInMainHand().hasItemMeta()) {
				return;
			}
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta()
					.hasEnchant(CustomEnchants.DIMINISH)) {
				return;
			}
			if (silenceActive.get(attackingPlayer.getName()).equals(true)) {
				return;
			}

			Random rand = new Random();
			int upperBound = 3000;
			int procValue = rand.nextInt(upperBound);

			if (procValue <= (90 + luckyValue.get(attackingPlayer.getName()))) {
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 80, 1));
			}
		}
	}

	@EventHandler
	public void diminishThrow(ProjectileHitEvent e) {
		if (e.getEntity() instanceof Trident && e.getHitEntity() instanceof LivingEntity) {
			Trident trident = (Trident) e.getEntity();
			LivingEntity hitPlayer = (LivingEntity) e.getHitEntity();
			if (!trident.getItem().hasItemMeta()) {
				return;
			}
			if (!trident.getItem().getItemMeta().hasEnchant(CustomEnchants.DIMINISH)) {
				return;
			}
			if (!(trident.getShooter() instanceof Player)) {
				return;
			}
			Player thrower = (Player) trident.getShooter();
			if (silenceActive.get(thrower.getName()).equals(true)) {
				return;
			}

			Random rand = new Random();
			int upperBound = 3000;
			int procValue = rand.nextInt(upperBound);

			if (procValue <= (90 + luckyValue.get(thrower.getName()))) {
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 80, 1));
			}
		}
	}

	// EXECUTIONER
	@EventHandler
	public void executioner(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getDamager();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}

		if (!(e.getEntity() instanceof LivingEntity)) {
			return;
		}
		LivingEntity hit = (LivingEntity) e.getEntity();

		if (player.getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack executionerWeapon = player.getInventory().getItemInMainHand();
		if (!executionerWeapon.hasItemMeta()) {
			return;
		}
		if (!executionerWeapon.getItemMeta().hasEnchant(CustomEnchants.EXECUTIONER)) {
			return;
		}

		int eLvl = executionerWeapon.getItemMeta().getEnchantLevel(CustomEnchants.EXECUTIONER);
		if (eLvl == 1) {
			Vector pDir = player.getLocation().getDirection();
			Vector eDir = hit.getLocation().getDirection();
			double xv = pDir.getX() * eDir.getZ() - pDir.getZ() * eDir.getX();
			double zv = pDir.getX() * eDir.getX() + pDir.getZ() * eDir.getZ();
			double angle = Math.atan2(xv, zv);
			double angleInDegrees = (angle * 180) / Math.PI;
			if (angleInDegrees <= 40 && angleInDegrees >= -40) {
				player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, (float) 0.25, (float) 0.5);
				e.setDamage(e.getDamage() * 1.1);
			}
		} else if (eLvl == 2) {
			Vector pDir = player.getLocation().getDirection();
			Vector eDir = hit.getLocation().getDirection();
			double xv = pDir.getX() * eDir.getZ() - pDir.getZ() * eDir.getX();
			double zv = pDir.getX() * eDir.getX() + pDir.getZ() * eDir.getZ();
			double angle = Math.atan2(xv, zv);
			double angleInDegrees = (angle * 180) / Math.PI;
			if (angleInDegrees <= 40 && angleInDegrees >= -40) {
				player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, (float) 0.25, (float) 0.5);
				e.setDamage(e.getDamage() * 1.125);
			}
		} else if (eLvl == 3) {
			Vector pDir = player.getLocation().getDirection();
			Vector eDir = hit.getLocation().getDirection();
			double xv = pDir.getX() * eDir.getZ() - pDir.getZ() * eDir.getX();
			double zv = pDir.getX() * eDir.getX() + pDir.getZ() * eDir.getZ();
			double angle = Math.atan2(xv, zv);
			double angleInDegrees = (angle * 180) / Math.PI;
			if (angleInDegrees <= 40 && angleInDegrees >= -40) {
				player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, (float) 0.25, (float) 0.5);
				e.setDamage(e.getDamage() * 1.15);
			}
		} else if (eLvl == 4) {
			Vector pDir = player.getLocation().getDirection();
			Vector eDir = hit.getLocation().getDirection();
			double xv = pDir.getX() * eDir.getZ() - pDir.getZ() * eDir.getX();
			double zv = pDir.getX() * eDir.getX() + pDir.getZ() * eDir.getZ();
			double angle = Math.atan2(xv, zv);
			double angleInDegrees = (angle * 180) / Math.PI;
			if (angleInDegrees <= 40 && angleInDegrees >= -40) {
				player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, (float) 0.25, (float) 0.5);
				e.setDamage(e.getDamage() * 1.175);
			}
		} else if (eLvl == 5) {
			Vector pDir = player.getLocation().getDirection();
			Vector eDir = hit.getLocation().getDirection();
			double xv = pDir.getX() * eDir.getZ() - pDir.getZ() * eDir.getX();
			double zv = pDir.getX() * eDir.getX() + pDir.getZ() * eDir.getZ();
			double angle = Math.atan2(xv, zv);
			double angleInDegrees = (angle * 180) / Math.PI;
			if (angleInDegrees <= 40 && angleInDegrees >= -40) {
				player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, (float) 0.25, (float) 0.5);
				e.setDamage(e.getDamage() * 1.2);
			}
		} else if (eLvl == 6) {
			Vector pDir = player.getLocation().getDirection();
			Vector eDir = hit.getLocation().getDirection();
			double xv = pDir.getX() * eDir.getZ() - pDir.getZ() * eDir.getX();
			double zv = pDir.getX() * eDir.getX() + pDir.getZ() * eDir.getZ();
			double angle = Math.atan2(xv, zv);
			double angleInDegrees = (angle * 180) / Math.PI;
			if (angleInDegrees <= 40 && angleInDegrees >= -40) {
				player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, (float) 0.25, (float) 0.5);
				e.setDamage(e.getDamage() * 1.25);
			}
		}
	}

	// FROZEN
	@EventHandler
	public void frozen(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		if (player.getInventory().getHelmet() == null) {
			return;
		}
		ItemStack frozenHelmet = player.getInventory().getHelmet();
		if (!frozenHelmet.hasItemMeta()) {
			return;
		}
		if (!frozenHelmet.getItemMeta().hasEnchant(CustomEnchants.FROZEN)) {
			return;
		}

		if (!(e.getDamager() instanceof LivingEntity)) {
			return;
		}
		LivingEntity attacker = (LivingEntity) e.getDamager();

		Random rand = new Random();
		int upperBound = 3000;
		int procValue = rand.nextInt(upperBound);

		int eLvl = frozenHelmet.getItemMeta().getEnchantLevel(CustomEnchants.FROZEN);
		if (eLvl == 1 && procValue <= (60 + luckyValue.get(player.getName()))) {
			attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 4));
			player.getWorld().spawnParticle(Particle.SNOWFLAKE, attacker.getLocation().add(0, 1, 0), 30);
			attacker.getWorld().playSound(attacker.getLocation(), Sound.BLOCK_POWDER_SNOW_STEP, 5, 0.5f);
		} else if (eLvl == 2 && procValue <= (90 + luckyValue.get(player.getName()))) {
			attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 140, 5));
			player.getWorld().spawnParticle(Particle.SNOWFLAKE, attacker.getLocation().add(0, 1, 0), 30);
			attacker.getWorld().playSound(attacker.getLocation(), Sound.BLOCK_POWDER_SNOW_STEP, 5, 0.5f);
		} else if (eLvl == 3 && procValue <= (120 + luckyValue.get(player.getName()))) {
			attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 180, 6));
			player.getWorld().spawnParticle(Particle.SNOWFLAKE, attacker.getLocation().add(0, 1, 0), 30);
			attacker.getWorld().playSound(attacker.getLocation(), Sound.BLOCK_POWDER_SNOW_STEP, 5, 0.5f);
		}
	}

	// GORGE
	@EventHandler
	public void gorge(FoodLevelChangeEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			int newHunger = player.getFoodLevel();
			int oldHunger = gorgeHungerLevel.get(player.getName());

			// update the hunger level
			gorgeHungerLevel.put(player.getName(), newHunger);

			if (player.getInventory().getHelmet() == null) {
				return;
			}
			if (silenceActive.get(player.getName()).equals(true)) {
				return;
			}

			// return if helmet doesnt have gorge
			ItemStack gorgeItem = player.getInventory().getHelmet();
			if (!gorgeItem.hasItemMeta()) {
				return;
			}
			if (!gorgeItem.getItemMeta().hasEnchant(CustomEnchants.GORGE)) {
				return;
			}

			// return if newHunger is greater than oldHunger (the player ate food)
			if (newHunger > oldHunger) {
				return;
			}

			// return if hunger isnt half or below
			if (newHunger >= 10) {
				return;
			}

			// get the proc chance
			Random rand = new Random();
			int upperBound = 2000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = gorgeItem.getItemMeta().getEnchantLevel(CustomEnchants.GORGE);
			if (eLvl == 1 && procValue <= (250 + luckyValue.get(player.getName()))) {
				player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
				player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 0));
				gorgeHungerLevel.put(player.getName(), newHunger + 1);
			} else if (eLvl == 2 && procValue <= (500 + luckyValue.get(player.getName()))) {
				player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
				player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 1));
				gorgeHungerLevel.put(player.getName(), newHunger + 2);
			}
		}
	}

	// Get current hunger level on player join for gorge
	@EventHandler
	public void gorgeGetHunger(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		int playerHunger = player.getFoodLevel();
		gorgeHungerLevel.put(player.getName(), playerHunger);
	}

	// GUARDIANS
	@EventHandler
	public void guardians(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity();
		World world = player.getWorld();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		if (player.getInventory().getLeggings() == null) {
			return;
		}
		ItemStack guardiansLeggings = player.getInventory().getLeggings();
		if (!guardiansLeggings.hasItemMeta()) {
			return;
		}
		if (!guardiansLeggings.getItemMeta().hasEnchant(CustomEnchants.GUARDIANS)) {
			return;
		}

		if (!(e.getDamager() instanceof LivingEntity)) {
			return;
		}
		LivingEntity attacker = (LivingEntity) e.getDamager();

		Random rand = new Random();
		int upperBound = 10000;
		int procValue = rand.nextInt(upperBound);

		int eLvl = guardiansLeggings.getItemMeta().getEnchantLevel(CustomEnchants.GUARDIANS);
		if (eLvl == 1 && procValue <= (50 + luckyValue.get(player.getName()))) {
			spawnGuardian(player, attacker, world, 1, 120);
		}
		if (eLvl == 2 && procValue <= (60 + luckyValue.get(player.getName()))) {
			spawnGuardian(player, attacker, world, 2, 130);
		}
		if (eLvl == 3 && procValue <= (70 + luckyValue.get(player.getName()))) {
			spawnGuardian(player, attacker, world, 3, 140);
		}
		if (eLvl == 4 && procValue <= (80 + luckyValue.get(player.getName()))) {
			spawnGuardian(player, attacker, world, 4, 150);
		}
		if (eLvl == 5 && procValue <= (90 + luckyValue.get(player.getName()))) {
			spawnGuardian(player, attacker, world, 5, 160);
		}
		if (eLvl == 6 && procValue <= (100 + luckyValue.get(player.getName()))) {
			spawnGuardian(player, attacker, world, 6, 170);
		}
		if (eLvl == 7 && procValue <= (110 + luckyValue.get(player.getName()))) {
			spawnGuardian(player, attacker, world, 7, 180);
		}
		if (eLvl == 8 && procValue <= (120 + luckyValue.get(player.getName()))) {
			spawnGuardian(player, attacker, world, 8, 190);
		}
		if (eLvl == 9 && procValue <= (130 + luckyValue.get(player.getName()))) {
			spawnGuardian(player, attacker, world, 9, 200);
		}
	}

	@SuppressWarnings("deprecation")
	public void spawnGuardian(Player player, LivingEntity attacker, World world, int level, double health) {
		IronGolem guardian = (IronGolem) world.spawnEntity(player.getLocation(), EntityType.IRON_GOLEM);
		String name = ChatColor.WHITE + "" + ChatColor.BOLD + "Guardians";
		if (level == 1) {
			name = ChatColor.WHITE + "" + ChatColor.BOLD + "Guardians I";
		} else if (level == 2) {
			name = ChatColor.WHITE + "" + ChatColor.BOLD + "Guardians II";
		} else if (level == 3) {
			name = ChatColor.WHITE + "" + ChatColor.BOLD + "Guardians III";
		} else if (level == 4) {
			name = ChatColor.WHITE + "" + ChatColor.BOLD + "Guardians IV";
		} else if (level == 5) {
			name = ChatColor.WHITE + "" + ChatColor.BOLD + "Guardians V";
		} else if (level == 6) {
			name = ChatColor.WHITE + "" + ChatColor.BOLD + "Guardians VI";
		} else if (level == 7) {
			name = ChatColor.WHITE + "" + ChatColor.BOLD + "Guardians VII";
		} else if (level == 8) {
			name = ChatColor.WHITE + "" + ChatColor.BOLD + "Guardians VIII";
		} else if (level == 9) {
			name = ChatColor.WHITE + "" + ChatColor.BOLD + "Guardians IX";
		}
		guardian.setCustomName(name);
		guardian.setMaxHealth(health);
		guardian.setHealth(health);
		guardian.setTarget(attacker);

		new BukkitRunnable() {
			public void run() {
				guardiansCounter.put(player.getName(), guardiansCounter.get(player.getName()) + 20);
				if (!player.isOnline() || guardian.isDead() || player.isDead() || player.isBanned()
						|| guardiansCounter.get(player.getName()) == 600) {
					guardiansCounter.put(player.getName(), 0);
					guardian.remove();
					cancel();
				}
			}
		}.runTaskTimer(this, 0, 20);
	}

	// guardians variable reset
	@EventHandler
	public void guardiansResetVariable(PlayerJoinEvent e) {
		guardiansCounter.put(e.getPlayer().getName(), 0);
	}

	// guardians damage event
	@EventHandler
	public void guardiansDamage(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof IronGolem)) {
			return;
		}
		IronGolem possibleGuardian = (IronGolem) e.getDamager();
		if (!possibleGuardian.getCustomName().contains("Guardians")) {
			String customNameSplit[] = possibleGuardian.getCustomName().split(" ");
			String level = customNameSplit[1];
			if (level == "I") {
				e.setDamage(17);
				e.getEntity().setVelocity(e.getEntity().getVelocity().multiply(12));
			} else if (level == "II") {
				e.setDamage(18);
				e.getEntity().setVelocity(e.getEntity().getVelocity().multiply(13));
			} else if (level == "III") {
				e.setDamage(19);
				e.getEntity().setVelocity(e.getEntity().getVelocity().multiply(14));
			} else if (level == "IV") {
				e.setDamage(20);
				e.getEntity().setVelocity(e.getEntity().getVelocity().multiply(15));
			} else if (level == "V") {
				e.setDamage(21);
				e.getEntity().setVelocity(e.getEntity().getVelocity().multiply(16));
			} else if (level == "VI") {
				e.setDamage(22);
				e.getEntity().setVelocity(e.getEntity().getVelocity().multiply(17));
			} else if (level == "VII") {
				e.setDamage(23);
				e.getEntity().setVelocity(e.getEntity().getVelocity().multiply(18));
			} else if (level == "VIII") {
				e.setDamage(24);
				e.getEntity().setVelocity(e.getEntity().getVelocity().multiply(19));
			} else if (level == "IX") {
				e.setDamage(25);
				e.getEntity().setVelocity(e.getEntity().getVelocity().multiply(20));
			}
		}
	}

	// JUMP
	@EventHandler
	public void jumpEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() != null) {
			ItemStack item = e.getItemStack();
			if (!item.hasItemMeta()) {
				return;
			}
			if (!item.getItemMeta().hasEnchant(CustomEnchants.JUMP)) {
				return;
			}

			int eLvl = item.getItemMeta().getEnchantLevel(CustomEnchants.JUMP);
			if (eLvl == 1) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 0));
			} else if (eLvl == 2) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1));
			} else if (eLvl == 3) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2));
			} else if (eLvl == 4) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 3));
			}
		}
	}

	@EventHandler
	public void jumpUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() == null) {
			return;
		}
		ItemStack playerArmor = e.getItemStack();
		if (!playerArmor.hasItemMeta()) {
			return;
		}
		if (!playerArmor.getItemMeta().hasEnchant(CustomEnchants.JUMP)) {
			return;
		}
		player.removePotionEffect(PotionEffectType.JUMP);
	}

	// MAGNETIZE
	@EventHandler
	public void magnetize(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Monster && e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();
			Monster hitEntity = (Monster) e.getEntity();
			if (attackingPlayer.getInventory().getItemInMainHand() == null) {
				return;
			}
			ItemStack magnetizeWeapon = attackingPlayer.getInventory().getItemInMainHand();
			if (!magnetizeWeapon.hasItemMeta()) {
				return;
			}
			if (!magnetizeWeapon.getItemMeta().hasEnchant(CustomEnchants.MAGNETIZE)) {
				return;
			}
			if (silenceActive.get(attackingPlayer.getName()).equals(true)) {
				return;
			}

			int eLvl = magnetizeWeapon.getItemMeta().getEnchantLevel(CustomEnchants.MAGNETIZE);
			if (eLvl == 1) {
				hitEntity.setVelocity(attackingPlayer.getLocation().getDirection().setY(0).normalize().multiply(0.5));
			} 
			else if (eLvl == 2) {
				hitEntity.setVelocity(attackingPlayer.getLocation().getDirection().setY(0).normalize().multiply(0.3));
			} 
			else if (eLvl == 3) {
				hitEntity.setVelocity(attackingPlayer.getLocation().getDirection().setY(0).normalize().multiply(0.1));
			}
		}
	}

	
	//NIMBLE
	@SuppressWarnings("deprecation")
	@EventHandler
	public void nimble(ProjectileHitEvent e) {
		if (!(e.getEntity().getShooter() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity().getShooter();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		
		if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta() &&
			player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.NIMBLE)) {
			if (!player.isOnGround()) {
				int eLvl = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(CustomEnchants.NIMBLE);
				if (eLvl == 1) {
					if (e.getHitEntity() instanceof LivingEntity) {
						LivingEntity hit = (LivingEntity) e.getHitEntity();
						hit.damage(4);
					}
				}
				else if (eLvl == 2) {
					if (e.getHitEntity() instanceof LivingEntity) {
						LivingEntity hit = (LivingEntity) e.getHitEntity();
						hit.damage(6);
					}
				} 
				else if (eLvl == 3) {
					if (e.getHitEntity() instanceof LivingEntity) {
						LivingEntity hit = (LivingEntity) e.getHitEntity();
						hit.damage(8);
					}
				}
			}
		}
		else if (player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta() &&
				 player.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.NIMBLE)) {
			if (!player.isOnGround()) {
				int eLvl = player.getInventory().getItemInOffHand().getItemMeta().getEnchantLevel(CustomEnchants.NIMBLE);
				if (eLvl == 1) {
					if (e.getHitEntity() instanceof LivingEntity) {
						LivingEntity hit = (LivingEntity) e.getHitEntity();
						hit.damage(4);
					}
				}
				else if (eLvl == 2) {
					if (e.getHitEntity() instanceof LivingEntity) {
						LivingEntity hit = (LivingEntity) e.getHitEntity();
						hit.damage(6);
					}
				} 
				else if (eLvl == 3) {
					if (e.getHitEntity() instanceof LivingEntity) {
						LivingEntity hit = (LivingEntity) e.getHitEntity();
						hit.damage(8);
					}
				}
			}
		}
	}
	

	// OBSIDIANSHIELD
	@EventHandler
	public void obsidianShieldEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() != null) {
			ItemStack item = e.getItemStack();
			if (!item.hasItemMeta()) {
				return;
			}
			if (!item.getItemMeta().hasEnchant(CustomEnchants.OBSIDIANSHIELD)) {
				return;
			}
			player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
		}
	}

	@EventHandler
	public void obsidianShieldUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() == null) {
			return;
		}
		ItemStack playerArmor = e.getItemStack();
		if (!playerArmor.hasItemMeta()) {
			return;
		}
		if (!playerArmor.getItemMeta().hasEnchant(CustomEnchants.OBSIDIANSHIELD)) {
			return;
		}
		player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
	}

	// PINPOINT
	@EventHandler
	public void pinpoint(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Trident)) {
			return;
		}
		Trident trident = (Trident) e.getDamager();
		if (!(e.getEntity() instanceof LivingEntity)) {
			return;
		}
		LivingEntity entity = (LivingEntity) e.getEntity();
		if (!(trident.getShooter() instanceof Player)) {
			return;
		}
		Player player = (Player) trident.getShooter();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		if (!trident.getItem().hasItemMeta()) {
			return;
		}
		if (!trident.getItem().getItemMeta().hasEnchant(CustomEnchants.PINPOINT)) {
			return;
		}

		int eLvl = trident.getItem().getItemMeta().getEnchantLevel(CustomEnchants.PINPOINT);
		if (eLvl == 1) {
			if (entity instanceof Player || entity instanceof Creeper || entity instanceof Zombie
					|| entity instanceof Evoker || entity instanceof Villager || entity instanceof Husk
					|| entity instanceof Witch || entity instanceof Vindicator || entity instanceof Illusioner
					|| entity instanceof Drowned || entity instanceof Pillager || entity instanceof Skeleton
					|| entity instanceof Stray) {
				double xmin = entity.getLocation().getX() - 4;
				double xmax = entity.getLocation().getX() + 4;
				double ymin = entity.getLocation().getY() + 1.3;
				double ymax = entity.getLocation().getY() + 2.5;
				double zmin = entity.getLocation().getZ() - 4;
				double zmax = entity.getLocation().getZ() + 4;

				double tx = trident.getLocation().getX();
				double ty = trident.getLocation().getY();
				double tz = trident.getLocation().getZ();

				if (tx >= xmin && tx <= xmax) {
					if (ty >= ymin && ty <= ymax) {
						if (tz >= zmin && tz <= zmax) {
							entity.getWorld().spawnParticle(Particle.CRIT, entity.getLocation(), 30, 0.5, 1.1, 0.5);
							player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "- " + ChatColor.RED + ""
									+ ChatColor.BOLD + "Headshot" + ChatColor.DARK_RED + "" + ChatColor.BOLD + " -");
							e.setDamage(e.getDamage() * 1.3);
						}
					}
				}
			} else if (entity instanceof WitherSkeleton) {
				double xmin = entity.getLocation().getX() - 4;
				double xmax = entity.getLocation().getX() + 4;
				double ymin = entity.getLocation().getY() + 1.6;
				double ymax = entity.getLocation().getY() + 3.5;
				double zmin = entity.getLocation().getZ() - 4;
				double zmax = entity.getLocation().getZ() + 4;

				double tx = trident.getLocation().getX();
				double ty = trident.getLocation().getY();
				double tz = trident.getLocation().getZ();

				if (tx >= xmin && tx <= xmax) {
					if (ty >= ymin && ty <= ymax) {
						if (tz >= zmin && tz <= zmax) {
							entity.getWorld().spawnParticle(Particle.CRIT, entity.getLocation(), 30, 0.5, 1.1, 0.5);
							player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "- " + ChatColor.RED + ""
									+ ChatColor.BOLD + "Headshot" + ChatColor.DARK_RED + "" + ChatColor.BOLD + " -");
							e.setDamage(e.getDamage() * 1.3);
						}
					}
				}
			} else if (entity instanceof Wither) {
				double xmin = entity.getLocation().getX() - 4;
				double xmax = entity.getLocation().getX() + 4;
				double ymin = entity.getLocation().getY() + 2.25;
				double ymax = entity.getLocation().getY() + 4;
				double zmin = entity.getLocation().getZ() - 4;
				double zmax = entity.getLocation().getZ() + 4;

				double tx = trident.getLocation().getX();
				double ty = trident.getLocation().getY();
				double tz = trident.getLocation().getZ();

				if (tx >= xmin && tx <= xmax) {
					if (ty >= ymin && ty <= ymax) {
						if (tz >= zmin && tz <= zmax) {
							entity.getWorld().spawnParticle(Particle.CRIT, entity.getLocation(), 30, 0.5, 1.1, 0.5);
							player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "- " + ChatColor.RED + ""
									+ ChatColor.BOLD + "Headshot" + ChatColor.DARK_RED + "" + ChatColor.BOLD + " -");
							e.setDamage(e.getDamage() * 1.3);
						}
					}
				}
			}
		} else if (eLvl == 2) {
			if (entity instanceof Player || entity instanceof Creeper || entity instanceof Zombie
					|| entity instanceof Evoker || entity instanceof Villager || entity instanceof Husk
					|| entity instanceof Witch || entity instanceof Vindicator || entity instanceof Illusioner
					|| entity instanceof Drowned || entity instanceof Pillager || entity instanceof Skeleton
					|| entity instanceof Stray) {
				double xmin = entity.getLocation().getX() - 4;
				double xmax = entity.getLocation().getX() + 4;
				double ymin = entity.getLocation().getY() + 1.3;
				double ymax = entity.getLocation().getY() + 2.5;
				double zmin = entity.getLocation().getZ() - 4;
				double zmax = entity.getLocation().getZ() + 4;

				double tx = trident.getLocation().getX();
				double ty = trident.getLocation().getY();
				double tz = trident.getLocation().getZ();

				if (tx >= xmin && tx <= xmax) {
					if (ty >= ymin && ty <= ymax) {
						if (tz >= zmin && tz <= zmax) {
							entity.getWorld().spawnParticle(Particle.CRIT, entity.getLocation(), 30, 0.5, 1.1, 0.5);
							player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "- " + ChatColor.RED + ""
									+ ChatColor.BOLD + "Headshot" + ChatColor.DARK_RED + "" + ChatColor.BOLD + " -");
							e.setDamage(e.getDamage() * 1.6);
						}
					}
				}
			} else if (entity instanceof WitherSkeleton || entity instanceof Enderman) {
				double xmin = entity.getLocation().getX() - 4;
				double xmax = entity.getLocation().getX() + 4;
				double ymin = entity.getLocation().getY() + 1.6;
				double ymax = entity.getLocation().getY() + 3.5;
				double zmin = entity.getLocation().getZ() - 4;
				double zmax = entity.getLocation().getZ() + 4;

				double tx = trident.getLocation().getX();
				double ty = trident.getLocation().getY();
				double tz = trident.getLocation().getZ();

				if (tx >= xmin && tx <= xmax) {
					if (ty >= ymin && ty <= ymax) {
						if (tz >= zmin && tz <= zmax) {
							entity.getWorld().spawnParticle(Particle.CRIT, entity.getLocation(), 30, 0.5, 1.1, 0.5);
							player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "- " + ChatColor.RED + ""
									+ ChatColor.BOLD + "Headshot" + ChatColor.DARK_RED + "" + ChatColor.BOLD + " -");
							e.setDamage(e.getDamage() * 1.6);
						}
					}
				}
			} else if (entity instanceof Wither) {
				double xmin = entity.getLocation().getX() - 4;
				double xmax = entity.getLocation().getX() + 4;
				double ymin = entity.getLocation().getY() + 2.25;
				double ymax = entity.getLocation().getY() + 4;
				double zmin = entity.getLocation().getZ() - 4;
				double zmax = entity.getLocation().getZ() + 4;

				double tx = trident.getLocation().getX();
				double ty = trident.getLocation().getY();
				double tz = trident.getLocation().getZ();

				if (tx >= xmin && tx <= xmax) {
					if (ty >= ymin && ty <= ymax) {
						if (tz >= zmin && tz <= zmax) {
							entity.getWorld().spawnParticle(Particle.CRIT, entity.getLocation(), 30, 0.5, 1.1, 0.5);
							player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "- " + ChatColor.RED + ""
									+ ChatColor.BOLD + "Headshot" + ChatColor.DARK_RED + "" + ChatColor.BOLD + " -");
							e.setDamage(e.getDamage() * 1.6);
						}
					}
				}
			}
		}
	}

	// POKEY
	@EventHandler
	public void pokey(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity();
		if (!(e.getDamager() instanceof LivingEntity)) {
			return;
		}
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		if (player.getInventory().getChestplate() == null) {
			return;
		}
		ItemStack pokeyChestplate = player.getInventory().getChestplate();
		if (!pokeyChestplate.hasItemMeta()) {
			return;
		}
		if (!pokeyChestplate.getItemMeta().hasEnchant(CustomEnchants.POKEY)) {
			return;
		}

		Random rand = new Random();
		int upperBound = 6000;
		int procValue = rand.nextInt(upperBound);

		int eLvl = pokeyChestplate.getItemMeta().getEnchantLevel(CustomEnchants.POKEY);
		if (eLvl == 1 && procValue <= (75 + luckyValue.get(player.getName()))) {
			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PUFFER_FISH_BLOW_OUT, 1, 1);
			pufferfishAnimation(player, player.getFacing(), 6);
		} else if (eLvl == 2 && procValue <= (115 + luckyValue.get(player.getName()))) {
			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PUFFER_FISH_BLOW_OUT, 1, 1);
			pufferfishAnimation(player, player.getFacing(), 9);
		} else if (eLvl == 3 && procValue <= (155 + luckyValue.get(player.getName()))) {
			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PUFFER_FISH_BLOW_OUT, 1, 1);
			pufferfishAnimation(player, player.getFacing(), 12);
		} else if (eLvl == 4 && procValue <= (195 + luckyValue.get(player.getName()))) {
			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PUFFER_FISH_BLOW_OUT, 1, 1);
			pufferfishAnimation(player, player.getFacing(), 15);
		}
	}

	public void pufferfishAnimation(Player player, BlockFace dir, double damage) {
		if (dir.equals(BlockFace.NORTH)) {
			PufferFish p = (PufferFish) player.getWorld().spawnEntity(player.getLocation().add(-2, 1, 0),
					EntityType.PUFFERFISH);
			p.setPuffState(2);
			p.setAI(false);
			p.setInvulnerable(true);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, -0.5);
				}
			}, 1);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, -1);
				}
			}, 2);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, -1.5);
				}
			}, 3);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -1.5, 1, -2);
				}
			}, 4);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -1, 1, -2);
				}
			}, 5);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -0.5, 1, -2);
				}
			}, 6);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 0, 1, -2);
				}
			}, 7);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 0.5, 1, -2);
					for (Entity entity : p.getNearbyEntities(7, 7, 7)) {
						if (entity.getName() == player.getName()) {
							// do nothing if its the player who summoned
						} else if (entity instanceof LivingEntity) {
							((LivingEntity) entity).damage(damage);
							((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 4));
						}
					}
				}
			}, 8);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 1, 1, -2);
				}
			}, 9);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 1.5, 1, -2);
				}
			}, 10);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, -1.5);
				}
			}, 11);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, -1);
				}
			}, 12);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, -0.5);
				}
			}, 13);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, 0);
				}
			}, 14);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					p.remove();
				}
			}, 16);
		}

		else if (dir.equals(BlockFace.SOUTH)) {
			PufferFish p = (PufferFish) player.getWorld().spawnEntity(player.getLocation().add(2, 1, 0),
					EntityType.PUFFERFISH);
			p.setPuffState(2);
			p.setAI(false);
			p.setInvulnerable(true);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, 0.5);
				}
			}, 1);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, 1);
				}
			}, 2);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, 1.5);
				}
			}, 3);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 1.5, 1, 2);
				}
			}, 4);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 1, 1, 2);
				}
			}, 5);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 0.5, 1, 2);
				}
			}, 6);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 0, 1, 2);
				}
			}, 7);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -0.5, 1, 2);
					for (Entity entity : p.getNearbyEntities(7, 7, 7)) {
						if (entity.getName() == player.getName()) {
							// do nothing if its the player who summoned
						} else if (entity instanceof LivingEntity) {
							((LivingEntity) entity).damage(damage);
							((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 4));
						}
					}
				}
			}, 8);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -1, 1, 2);
				}
			}, 9);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -1.5, 1, 2);
				}
			}, 10);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, 1.5);
				}
			}, 11);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, 1);
				}
			}, 12);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, 0.5);
				}
			}, 13);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, 0);
				}
			}, 14);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					p.remove();
				}
			}, 16);
		}

		else if (dir.equals(BlockFace.EAST)) {
			PufferFish p = (PufferFish) player.getWorld().spawnEntity(player.getLocation().add(0, 1, -2),
					EntityType.PUFFERFISH);
			p.setPuffState(2);
			p.setAI(false);
			p.setInvulnerable(true);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 0.5, 1, -2);
				}
			}, 1);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 1, 1, -2);
				}
			}, 2);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 1.5, 1, -2);
				}
			}, 3);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, -1.5);
				}
			}, 4);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, -1);
				}
			}, 5);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, -0.5);
				}
			}, 6);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, 0);
				}
			}, 7);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, 0.5);
					for (Entity entity : p.getNearbyEntities(7, 7, 7)) {
						if (entity.getName() == player.getName()) {
							// do nothing if its the player who summoned
						} else if (entity instanceof LivingEntity) {
							((LivingEntity) entity).damage(damage);
							((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 4));
						}
					}
				}
			}, 8);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, 1);
				}
			}, 9);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 2, 1, 1.5);
				}
			}, 10);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 1.5, 1, 2);
				}
			}, 11);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 1, 1, 2);
				}
			}, 12);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 0.5, 1, 2);
				}
			}, 13);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 0, 1, 2);
				}
			}, 14);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					p.remove();
				}
			}, 16);
		}

		else if (dir.equals(BlockFace.WEST)) {
			PufferFish p = (PufferFish) player.getWorld().spawnEntity(player.getLocation().add(0, 1, 2),
					EntityType.PUFFERFISH);
			p.setPuffState(2);
			p.setAI(false);
			p.setInvulnerable(true);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -0.5, 1, 2);
				}
			}, 1);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -1, 1, 2);
				}
			}, 2);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -1.5, 1, 2);
				}
			}, 3);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, 1.5);
				}
			}, 4);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, 1);
				}
			}, 5);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, 0.5);
				}
			}, 6);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, 0);
				}
			}, 7);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, -0.5);
					for (Entity entity : p.getNearbyEntities(7, 7, 7)) {
						if (entity.getName() == player.getName()) {
							// do nothing if its the player who summoned
						} else if (entity instanceof LivingEntity) {
							((LivingEntity) entity).damage(damage);
							((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 4));
						}
					}
				}
			}, 8);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, -1);
				}
			}, 9);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -2, 1, -1.5);
				}
			}, 10);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -1.5, 1, -2);
				}
			}, 11);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -1, 1, -2);
				}
			}, 12);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, -0.5, 1, -2);
				}
			}, 13);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					teleportPufferfish(player, p, 0, 1, -2);
				}
			}, 14);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					p.remove();
				}
			}, 16);
		}
	}

	public void teleportPufferfish(Player player, PufferFish p, double x, double y, double z) {
		Location location = player.getLocation().add(x, y, z);
		p.teleport(location);
	}

	// RETALIATE
	@EventHandler
	public void retaliate(EntityDamageByEntityEvent e) {
		Player player;
		Player attacker;
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			player = (Player) e.getEntity();
			attacker = (Player) e.getDamager();
		} else {
			return;
		}
		if (player.getInventory().getChestplate() == null) {
			return;
		}
		ItemStack chestplate = player.getInventory().getChestplate();
		if (!chestplate.hasItemMeta()) {
			return;
		}
		if (!chestplate.getItemMeta().hasEnchant(CustomEnchants.RETALIATE)) {
			return;
		}
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}

		Random rand = new Random();
		int upperBound = 2000;
		int procValue = rand.nextInt(upperBound);

		int eLvl = chestplate.getItemMeta().getEnchantLevel(CustomEnchants.RETALIATE);
		if (eLvl == 1 && procValue <= (20 + luckyValue.get(player.getName()))) {
			attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 25, 9));
			EvokerFangs fang = (EvokerFangs) attacker.getWorld().spawnEntity(attacker.getLocation(),
					EntityType.EVOKER_FANGS);
			fang.setCustomName("Retaliate Fang 1");
			fang.setInvulnerable(true);
		} else if (eLvl == 2 && procValue <= (25 + luckyValue.get(player.getName()))) {
			attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 25, 9));
			EvokerFangs fang = (EvokerFangs) attacker.getWorld().spawnEntity(attacker.getLocation(),
					EntityType.EVOKER_FANGS);
			fang.setCustomName("Retaliate Fang 2");
			fang.setInvulnerable(true);
		} else if (eLvl == 3 && procValue <= (30 + luckyValue.get(player.getName()))) {
			attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 25, 9));
			EvokerFangs fang = (EvokerFangs) attacker.getWorld().spawnEntity(attacker.getLocation(),
					EntityType.EVOKER_FANGS);
			fang.setCustomName("Retaliate Fang 3");
			fang.setInvulnerable(true);
		} else if (eLvl == 4 && procValue <= (35 + luckyValue.get(player.getName()))) {
			attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 25, 9));
			EvokerFangs fang = (EvokerFangs) attacker.getWorld().spawnEntity(attacker.getLocation(),
					EntityType.EVOKER_FANGS);
			fang.setCustomName("Retaliate Fang 4");
			fang.setInvulnerable(true);
		} else if (eLvl == 5 && procValue <= (40 + luckyValue.get(player.getName()))) {
			attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 25, 9));
			EvokerFangs fang = (EvokerFangs) attacker.getWorld().spawnEntity(attacker.getLocation(),
					EntityType.EVOKER_FANGS);
			fang.setCustomName("Retaliate Fang 5");
			fang.setInvulnerable(true);
		}
	}

	@EventHandler
	public void retaliateHitEffects(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof EvokerFangs) {
			EvokerFangs possibleFang = (EvokerFangs) e.getDamager();
			if (possibleFang.getCustomName().equals("Retaliate Fang 1")) {
				Player hitPlayer = (Player) e.getEntity();
				hitPlayer.damage(8);
			}
			if (possibleFang.getCustomName().equals("Retaliate Fang 2")) {
				Player hitPlayer = (Player) e.getEntity();
				hitPlayer.damage(10);
			}
			if (possibleFang.getCustomName().equals("Retaliate Fang 3")) {
				Player hitPlayer = (Player) e.getEntity();
				hitPlayer.damage(12);
			}
			if (possibleFang.getCustomName().equals("Retaliate Fang 4")) {
				Player hitPlayer = (Player) e.getEntity();
				hitPlayer.damage(14);
			}
			if (possibleFang.getCustomName().equals("Retaliate Fang 5")) {
				Player hitPlayer = (Player) e.getEntity();
				hitPlayer.damage(18);
			}
		} else {
			return;
		}
	}

	//RETRIBUTION
	@EventHandler
	public void retribution(PlayerSwapHandItemsEvent e) throws InterruptedException {
		Player player = e.getPlayer();
		if (player.getInventory().getItemInOffHand() == null) {
			return;
		}
		ItemStack offHandItem = player.getInventory().getItemInOffHand();
		if (!offHandItem.hasItemMeta()) {
			return;
		}
		if (offHandItem.getItemMeta().hasEnchant(CustomEnchants.RETRIBUTION)) {
			if (player.isSneaking()) {
				e.setCancelled(true);
				// off cooldown
				int currentTime = (int) (System.currentTimeMillis() / 1000);
				if (currentTime >= retributionCooldown.get(player.getName())) {
					// if not already active, make it active
					if (!retributionActive.get(player.getName())) {
						retributionActive.put(player.getName(), true);
						retributionCooldownTimerSet(player, 3);
						player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Retribution "
								+ ChatColor.RESET + "" + ChatColor.DARK_PURPLE + "active!");
						// particle effect
						for (double phi = 0; phi <= Math.PI; phi += Math.PI / 15) {
							for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 30) {
								double r = 1.3;
								double x = r * Math.cos(theta) * Math.sin(phi);
								double y = r * Math.cos(phi) + 1.25;
								double z = r * Math.sin(theta) * Math.sin(phi);

								Location location = player.getLocation();
								location.add(x, y, z);
								player.getWorld().spawnParticle(Particle.CLOUD, location, 1, 0F, 0F, 0F, 0.1);
								location.subtract(x, y, z);
							}
						}
					}
					// if active already, turn it off
					else if (retributionActive.get(player.getName())) {
						retributionActive.put(player.getName(), false);
						retributionCooldownTimerSet(player, 3);
						player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Retribution "
								+ ChatColor.RESET + "" + ChatColor.WHITE + "inactive.");
					}
				}
				// on cooldown
				else {
					int timeLeft = (int) (currentTime - retributionCooldown.get(player.getName()));
					if (player.isSneaking()) {
						player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Retribution" + ChatColor.RED + " is still on cooldown! (" +
						ChatColor.BLUE + "" + ChatColor.BOLD + (-1 * timeLeft) + "s" + ChatColor.RED + ")");
					}
				}
			}
		}
	}

	// RETRIBUTION EFFECTS
	@EventHandler
	public void retributionEffects(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof LivingEntity) {
			Player hitPlayer = (Player) e.getEntity();
			LivingEntity attackingPlayer = (LivingEntity) e.getDamager();
			if (hitPlayer.getInventory().getItemInOffHand() == null) {
				return;
			}
			ItemStack retributionShield = hitPlayer.getInventory().getItemInOffHand();
			if (!retributionShield.hasItemMeta()) {
				return;
			}
			if (!hitPlayer.getPlayer().getInventory().getItemInOffHand().getItemMeta()
					.hasEnchant(CustomEnchants.RETRIBUTION)) {
				return;
			}
			if (silenceActive.get(hitPlayer.getName()).equals(true)) {
				return;
			}

			if (retributionActive.get(hitPlayer.getName())) {
				if (hitPlayer.isBlocking()) {
					retributionActive.put(hitPlayer.getName(), false);
					retributionCooldownTimerSet(hitPlayer, 90);

					int eLvl = retributionShield.getItemMeta().getEnchantLevel(CustomEnchants.RETRIBUTION);
					if (eLvl == 1) {
						hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
						hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 0));
						attackingPlayer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 0));
						attackingPlayer.setVelocity(new Vector(0, 0.7, 0));
					} else if (eLvl == 2) {
						hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
						hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 150, 1));
						attackingPlayer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 150, 1));
						attackingPlayer.setVelocity(new Vector(0, 0.85, 0));
					} else if (eLvl == 3) {
						hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
						hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 2));
						attackingPlayer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 2));
						attackingPlayer.setVelocity(new Vector(0, 1, 0));
					}
				}
			}
		}
	}

	// RETRIBUTION_RESET_ON_SERVER_JOIN
	@EventHandler
	public void retributionOnServerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		retributionActive.put(player.getName(), false);
		retributionCooldownTimerSet(player, 90);
	}

	// RETRIBUTION_COOLDOWN_TIMER_SET
	public void retributionCooldownTimerSet(Player player, int cooldownAdded) {
		retributionCooldown.put(player.getName(), (System.currentTimeMillis() / 1000) + cooldownAdded);
	}

	// SKYROCKET
	@EventHandler
	public void skyrocket(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if (player.getInventory().getBoots() == null) {
				return;
			}
			if (!player.getInventory().getBoots().hasItemMeta()) {
				return;
			}
			if (silenceActive.get(player.getName()).equals(true)) {
				return;
			}
			if (player.getInventory().getBoots().getItemMeta().hasEnchant(CustomEnchants.SKYROCKET)) {
				Random rand = new Random();
				int upperBound = 2000;
				int procValue = rand.nextInt(upperBound);

				int eLvl = player.getInventory().getBoots().getItemMeta().getEnchantLevel(CustomEnchants.SKYROCKET);
				if (eLvl == 1 && player.getHealth() <= 2 && procValue <= (200 + luckyValue.get(player.getName()))) {
					player.setVelocity(new Vector(0, 18, 0));
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 130, 2));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 205, 0));
					player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Skyrocket" + ChatColor.RESET
							+ " blasted you into the air!");
				} 
				else if (eLvl == 2 && player.getHealth() <= 3 && procValue <= (250 + luckyValue.get(player.getName()))) {
					player.setVelocity(new Vector(0, 20, 0));
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 180, 2));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 210, 0));
					player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Skyrocket" + ChatColor.RESET
							+ " blasted you into the air!");
				} 
				else if (eLvl == 3 && player.getHealth() <= 4 && procValue <= (300 + luckyValue.get(player.getName()))) {
					player.setVelocity(new Vector(0, 22, 0));
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 215, 3));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 215, 0));
					player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Skyrocket" + ChatColor.RESET
							+ " blasted you into the air!");
				}
			}
		}
	}

	// THWOMP
	@EventHandler
	public void thwomp(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();
			Entity hitEntity = e.getEntity();
			if (attackingPlayer.getInventory().getItemInMainHand() == null) {
				return;
			}
			ItemStack thwompWeapon = attackingPlayer.getInventory().getItemInMainHand();
			if (!thwompWeapon.hasItemMeta()) {
				return;
			}
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta()
					.hasEnchant(CustomEnchants.THWOMP)) {
				return;
			}
			if (silenceActive.get(attackingPlayer.getName()).equals(true)) {
				return;
			}

			Random rand = new Random();
			int upperBound = 3000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = thwompWeapon.getItemMeta().getEnchantLevel(CustomEnchants.THWOMP);
			if (eLvl == 1 && procValue <= (45 + luckyValue.get(attackingPlayer.getName()))) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						hitEntity.setGravity(false);
						hitEntity.setVelocity(new Vector(0, 0.7, 0));
					}
				}, 2);

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						hitEntity.getWorld().playSound(hitEntity.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
						hitEntity.setGravity(true);
						hitEntity.setVelocity(new Vector(0, -1.6, 0));
					}
				}, 20);
			}

			else if (eLvl == 2 && procValue <= (70 + luckyValue.get(attackingPlayer.getName()))) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						hitEntity.setGravity(false);
						hitEntity.setVelocity(new Vector(0, 0.75, 0));
					}
				}, 2);

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						hitEntity.getWorld().playSound(hitEntity.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
						hitEntity.setGravity(true);
						hitEntity.setVelocity(new Vector(0, -2, 0));
					}
				}, 20);
			}

			else if (eLvl == 3 && procValue <= (90 + luckyValue.get(attackingPlayer.getName()))) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						hitEntity.setGravity(false);
						hitEntity.setVelocity(new Vector(0, 0.8, 0));
					}
				}, 2);

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						hitEntity.getWorld().playSound(hitEntity.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
						hitEntity.setGravity(true);
						hitEntity.setVelocity(new Vector(0, -3, 0));
					}
				}, 20);
			}
		}
	}

	// TURBO
	@EventHandler
	public void turbo(BlockBreakEvent e) {
		if (e.getPlayer().getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack turboItem = e.getPlayer().getInventory().getItemInMainHand();
		if (!turboItem.hasItemMeta()) {
			return;
		}
		if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.TURBO)) {
			return;
		}
		if (e.getPlayer().getGameMode() == GameMode.CREATIVE || e.getPlayer().getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (e.getBlock().getState() instanceof Container) {
			return;
		}
		if (silenceActive.get(e.getPlayer().getName()).equals(true)) {
			return;
		}

		Player player = e.getPlayer();
		Random rand = new Random();
		int upperBound = 2000;
		int procValue = rand.nextInt(upperBound);

		int eLvl = turboItem.getItemMeta().getEnchantLevel(CustomEnchants.TURBO);
		if (eLvl == 1 && procValue <= (80 + luckyValue.get(player.getName()))) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 60, 0));
		} else if (eLvl == 2 && procValue <= (100 + luckyValue.get(player.getName()))) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 80, 0));
		} else if (eLvl == 3 && procValue <= (120 + luckyValue.get(player.getName()))) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 80, 1));
		} else if (eLvl == 4 && procValue <= (140 + luckyValue.get(player.getName()))) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 1));
		} else if (eLvl == 5 && procValue <= (160 + luckyValue.get(player.getName()))) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 2));
		}
	}

	// VANQUISH
	@EventHandler
	public void vanquish(BlockBreakEvent e) {
		if (e.getPlayer().getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack vanquishItem = e.getPlayer().getInventory().getItemInMainHand();
		if (!vanquishItem.hasItemMeta()) {
			return;
		}
		if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.VANQUISH)) {
			return;
		}
		if (e.getPlayer().getGameMode() == GameMode.CREATIVE || e.getPlayer().getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (e.getBlock().getState() instanceof Container) {
			return;
		}
		if (silenceActive.get(e.getPlayer().getName()).equals(true)) {
			return;
		}

		Player player = e.getPlayer();
		if (player.isSneaking()) {
			return;
		}

		int eLvl = vanquishItem.getItemMeta().getEnchantLevel(CustomEnchants.VANQUISH);
		if (eLvl == 1) {
			Block belowBlock = e.getBlock().getRelative(BlockFace.DOWN);
			Block aboveBlock = e.getBlock().getRelative(BlockFace.UP);
			possiblyBreakBlock(player, vanquishItem, belowBlock);
			possiblyBreakBlock(player, vanquishItem, aboveBlock);
		} 
		else if (eLvl == 2) {
			Block belowBlock = e.getBlock().getRelative(BlockFace.DOWN);
			Block aboveBlock = e.getBlock().getRelative(BlockFace.UP);
			possiblyBreakBlock(player, vanquishItem, belowBlock);
			possiblyBreakBlock(player, vanquishItem, aboveBlock);
			if (player.getFacing().getDirection().getX() == 1.0 || player.getFacing().getDirection().getX() == -1.0) {
				Block leftBlock = e.getBlock().getRelative(BlockFace.SOUTH);
				Block rightBlock = e.getBlock().getRelative(BlockFace.NORTH);
				possiblyBreakBlock(player, vanquishItem, leftBlock);
				possiblyBreakBlock(player, vanquishItem, rightBlock);
			} 
			else {
				Block leftBlock = e.getBlock().getRelative(BlockFace.WEST);
				Block rightBlock = e.getBlock().getRelative(BlockFace.EAST);
				possiblyBreakBlock(player, vanquishItem, leftBlock);
				possiblyBreakBlock(player, vanquishItem, rightBlock);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void possiblyBreakBlock(Player player, ItemStack item, Block block) {
		ArrayList<Material> unbreakable = new ArrayList<>();
		unbreakable.add(Material.AIR);
		unbreakable.add(Material.OBSIDIAN);
		unbreakable.add(Material.BEDROCK);
		unbreakable.add(Material.BEACON);
		unbreakable.add(Material.WATER);
		unbreakable.add(Material.LEGACY_WATER);
		unbreakable.add(Material.LAVA);
		unbreakable.add(Material.LEGACY_LAVA);
		unbreakable.add(Material.GRASS);
		unbreakable.add(Material.LEGACY_GRASS);
		unbreakable.add(Material.TALL_GRASS);
		unbreakable.add(Material.LEGACY_LONG_GRASS);
		unbreakable.add(Material.COAL_ORE);
		unbreakable.add(Material.DEEPSLATE_COAL_ORE);
		unbreakable.add(Material.COPPER_ORE);
		unbreakable.add(Material.DEEPSLATE_COPPER_ORE);
		unbreakable.add(Material.IRON_ORE);
		unbreakable.add(Material.DEEPSLATE_IRON_ORE);
		unbreakable.add(Material.GOLD_ORE);
		unbreakable.add(Material.DEEPSLATE_GOLD_ORE);
		unbreakable.add(Material.REDSTONE_ORE);
		unbreakable.add(Material.DEEPSLATE_REDSTONE_ORE);
		unbreakable.add(Material.LAPIS_ORE);
		unbreakable.add(Material.DEEPSLATE_LAPIS_ORE);
		unbreakable.add(Material.DIAMOND_ORE);
		unbreakable.add(Material.DEEPSLATE_DIAMOND_ORE);
		unbreakable.add(Material.EMERALD_ORE);
		unbreakable.add(Material.DEEPSLATE_EMERALD_ORE);

		if (!(block.getState() instanceof Container)) {
			for (int k = 0; k < unbreakable.size(); k++) {
				Material uMat = unbreakable.get(k);
				if (block.getType().equals(uMat)) {
					return;
				}
			}
		}

		// if item has telepathy
		if (item.getItemMeta().hasEnchant(CustomEnchants.TELEPATHY)) {
			if (player.getInventory().firstEmpty() == -1) {
				block.breakNaturally();
				return;
			} else {
				Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());
				if (drops.isEmpty()) {
					return;
				}
				player.getInventory().addItem(drops.iterator().next());
				block.setType(Material.AIR);
				return;
			}
		} else {
			block.breakNaturally();
		}
	}

	// WISDOM
	@EventHandler
	public void wisdom(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();

			if (attackingPlayer.getInventory().getItemInMainHand() == null) {
				return;
			}
			ItemStack wisdomWeapon = attackingPlayer.getInventory().getItemInMainHand();
			if (!wisdomWeapon.hasItemMeta()) {
				return;
			}
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.WISDOM)) {
				return;
			}
			if (silenceActive.get(attackingPlayer.getName()).equals(true)) {
				return;
			}
			if (e.getEntity() instanceof LivingEntity) {
				int playerExpLevel = attackingPlayer.getLevel();
				if (playerExpLevel >= 100) {
					e.setDamage(e.getDamage() * 1.125);
				} 
				else {
					double damageMultiplier = ((playerExpLevel * 0.01) / 10) + 1;
					e.setDamage(e.getDamage() * damageMultiplier);
				}
			}
		}
	}

	// ACROBATICS
	@SuppressWarnings("deprecation")
	@EventHandler
	public void acrobatics(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity && e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();
			if (attackingPlayer.getInventory().getItemInMainHand() == null) {
				return;
			}
			if (!attackingPlayer.getInventory().getItemInMainHand().hasItemMeta()) {
				return;
			}
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.ACROBATICS)) {
				return;
			}
			if (attackingPlayer.isOnGround() == true) {
				return;
			}
			if (silenceActive.get(attackingPlayer.getName()).equals(true)) {
				return;
			}
			ItemStack acroWeapon = attackingPlayer.getInventory().getItemInMainHand();

			int eLvl = acroWeapon.getItemMeta().getEnchantLevel(CustomEnchants.ACROBATICS);
			if (eLvl == 1) {
				e.setDamage(e.getDamage() * 1.175);
			} else if (eLvl == 2) {
				e.setDamage(e.getDamage() * 1.2);
			} else if (eLvl == 3) {
				e.setDamage(e.getDamage() * 1.225);
			} else if (eLvl == 4) {
				e.setDamage(e.getDamage() * 1.25);
			} else if (eLvl == 5) {
				e.setDamage(e.getDamage() * 1.3);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void acrobaticsThrow(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Trident)) {
			return;
		}
		Trident acroWeapon = (Trident) e.getDamager();
		if (!(acroWeapon.getShooter() instanceof Player)) {
			return;
		}
		Player attackingPlayer = (Player) acroWeapon.getShooter();
		if (!(e.getEntity() instanceof LivingEntity)) {
			return;
		}
		if (!acroWeapon.getItem().hasItemMeta()) {
			return;
		}
		if (!acroWeapon.getItem().getItemMeta().hasEnchant(CustomEnchants.ACROBATICS)) {
			return;
		}
		if (attackingPlayer.isOnGround() == true) {
			return;
		}
		if (silenceActive.get(attackingPlayer.getName()).equals(true)) {
			return;
		}
		int eLvl = acroWeapon.getItem().getItemMeta().getEnchantLevel(CustomEnchants.ACROBATICS);
		if (eLvl == 1) {
			e.setDamage(e.getDamage() * 1.4);
		} else if (eLvl == 2) {
			e.setDamage(e.getDamage() * 1.45);
		} else if (eLvl == 3) {
			e.setDamage(e.getDamage() * 1.5);
		} else if (eLvl == 4) {
			e.setDamage(e.getDamage() * 1.55);
		} else if (eLvl == 5) {
			e.setDamage(e.getDamage() * 1.6);
		}
	}

	// AERODYNAMICS
	@EventHandler
	public void aerodynamics(PlayerRiptideEvent e) {
		Player player = e.getPlayer();
		ItemStack item = e.getItem();
		if (!item.hasItemMeta()) {
			return;
		}
		if (!item.getItemMeta().hasEnchant(CustomEnchants.AERODYNAMICS)) {
			return;
		}
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}

		int eLvl = item.getItemMeta().getEnchantLevel(CustomEnchants.AERODYNAMICS);
		if (eLvl == 1) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					player.setVelocity(player.getVelocity().multiply(1.1));
				}
			}, 1);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					player.setVelocity(player.getVelocity().multiply(1.1));
				}
			}, 2);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					player.setVelocity(player.getVelocity().multiply(1.1));
				}
			}, 3);
		} 
		else if (eLvl == 2) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					player.setVelocity(player.getVelocity().multiply(1.1));
				}
			}, 1);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					player.setVelocity(player.getVelocity().multiply(1.1));
				}
			}, 2);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					player.setVelocity(player.getVelocity().multiply(1.1));
				}
			}, 3);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					player.setVelocity(player.getVelocity().multiply(1.1));
				}
			}, 4);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					player.setVelocity(player.getVelocity().multiply(1.1));
				}
			}, 5);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					player.setVelocity(player.getVelocity().multiply(1.1));
				}
			}, 6);
		}
	}

	// AGILITY
	@EventHandler
	public void agilityEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() != null) {
			ItemStack item = e.getItemStack();
			if (!item.hasItemMeta()) {
				return;
			}
			if (!item.getItemMeta().hasEnchant(CustomEnchants.AGILITY)) {
				return;
			}

			int eLvl = item.getItemMeta().getEnchantLevel(CustomEnchants.AGILITY);
			if (eLvl == 1) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
			} 
			else if (eLvl == 2) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
			} 
			else if (eLvl == 3) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
			}
		}
	}

	@EventHandler
	public void agilityUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() == null) {
			return;
		}
		ItemStack playerArmor = e.getItemStack();
		if (!playerArmor.hasItemMeta()) {
			return;
		}
		if (!playerArmor.getItemMeta().hasEnchant(CustomEnchants.AGILITY)) {
			return;
		}
		player.removePotionEffect(PotionEffectType.SPEED);
	}

	// ANGELIC
	@EventHandler
	public void angelic(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		if (player.getInventory().getLeggings() == null) {
			return;
		}
		ItemStack angelicLeggings = player.getInventory().getLeggings();
		if (!angelicLeggings.hasItemMeta()) {
			return;
		}
		if (!angelicLeggings.getItemMeta().hasEnchant(CustomEnchants.ANGELIC)) {
			return;
		}

		Random rand = new Random();
		int upperBound = 4000;
		int procValue = rand.nextInt(upperBound);

		int eLvl = angelicLeggings.getItemMeta().getEnchantLevel(CustomEnchants.ANGELIC);
		if (eLvl == 1 && procValue <= (110 + luckyValue.get(player.getName()))) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 0));
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_LARGE_AMETHYST_BUD_PLACE, 3, 1);
		} 
		else if (eLvl == 2 && procValue <= (120 + luckyValue.get(player.getName()))) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 0));
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_LARGE_AMETHYST_BUD_PLACE, 3, 1);
		} 
		else if (eLvl == 3 && procValue <= (130 + luckyValue.get(player.getName()))) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 1));
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_LARGE_AMETHYST_BUD_PLACE, 3, 1);
		} 
		else if (eLvl == 4 && procValue <= (140 + luckyValue.get(player.getName()))) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 70, 1));
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_LARGE_AMETHYST_BUD_PLACE, 3, 1);
		} 
		else if (eLvl == 5 && procValue <= (150 + luckyValue.get(player.getName()))) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 70, 2));
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_LARGE_AMETHYST_BUD_PLACE, 3, 1);
		} 
		else if (eLvl == 6 && procValue <= (160 + luckyValue.get(player.getName()))) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 2));
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_LARGE_AMETHYST_BUD_PLACE, 3, 1);
		}
	}

	// ANNIHILATION
	@EventHandler
	public void annihilation(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getDamager();
		if (player.getInventory().getChestplate() == null) {
			return;
		}
		ItemStack chestplate = player.getInventory().getChestplate();
		if (!chestplate.hasItemMeta()) {
			return;
		}
		if (!chestplate.getItemMeta().hasEnchant(CustomEnchants.ANNIHILATION)) {
			return;
		}
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}

		Random rand = new Random();
		int upperBound = 6000;
		int procValue = rand.nextInt(upperBound);

		int eLvl = chestplate.getItemMeta().getEnchantLevel(CustomEnchants.ANNIHILATION);
		if (eLvl == 1 && procValue <= (180 + luckyValue.get(player.getName()))) {
			e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_WITHER_HURT, 0.7f, 0.5f);
			e.setDamage(e.getDamage() * 2);
		} 
		else if (eLvl == 2 && procValue <= (240 + luckyValue.get(player.getName()))) {
			e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_WITHER_HURT, 0.7f, 0.5f);
			e.setDamage(e.getDamage() * 2);
		} 
		else if (eLvl == 3 && procValue <= (300 + luckyValue.get(player.getName()))) {
			e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_WITHER_HURT, 0.7f, 0.5f);
			e.setDamage(e.getDamage() * 2);
		}
	}

	
	//BLOOM
	@SuppressWarnings("deprecation")
	@EventHandler
	public void bloom(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Projectile)) {
			return;
		}
		Projectile projectile = (Projectile) e.getDamager();
		if (!(projectile.getShooter() instanceof Player)) {
			return;
		}
		Player player = (Player) projectile.getShooter();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta() &&
			player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.BLOOM)) {
			Random rand = new Random();
			int upperBound = 4000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(CustomEnchants.BLOOM);
			if (eLvl == 1 && procValue <= (160 + luckyValue.get(player.getName()))) {
				double maxHealth = player.getMaxHealth();
				double currentHealth = player.getHealth();
				if (e.getEntity() instanceof Player) {
					currentHealth += 1;
				} else if (e.getEntity() instanceof Monster) {
					currentHealth += 1;
				}

				if (currentHealth > maxHealth) {
					player.setHealth(maxHealth);
				} else {
					player.setHealth(currentHealth);
				}
				player.getWorld().playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 2, (float) 1.5);
			}
			else if (eLvl == 2 && procValue <= (220 + luckyValue.get(player.getName()))) {
				double maxHealth = player.getMaxHealth();
				double currentHealth = player.getHealth();
				if (e.getEntity() instanceof Player) {
					currentHealth += 2;
				} else if (e.getEntity() instanceof Monster) {
					currentHealth += 1;
				}

				if (currentHealth > maxHealth) {
					player.setHealth(maxHealth);
				} else {
					player.setHealth(currentHealth);
				}
				player.getWorld().playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 2, (float) 1.5);
			}
			else if (eLvl == 3 && procValue <= (280 + luckyValue.get(player.getName()))) {
				double maxHealth = player.getMaxHealth();
				double currentHealth = player.getHealth();
				if (e.getEntity() instanceof Player) {
					currentHealth += 3;
				} else if (e.getEntity() instanceof Monster) {
					currentHealth += 1;
				}

				if (currentHealth > maxHealth) {
					player.setHealth(maxHealth);
				} else {
					player.setHealth(currentHealth);
					player.getWorld().playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 2,
							(float) 1.5);
				}
			}
			else if (eLvl == 4 && procValue <= (340 + luckyValue.get(player.getName()))) {
				double maxHealth = player.getMaxHealth();
				double currentHealth = player.getHealth();
				if (e.getEntity() instanceof Player) {
					currentHealth += 4;
				} else if (e.getEntity() instanceof Monster) {
					currentHealth += 1;
				}

				if (currentHealth > maxHealth) {
					player.setHealth(maxHealth);
				} else {
					player.setHealth(currentHealth);
					player.getWorld().playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 2,
							(float) 1.5);
				}
			}
			else if (eLvl == 5 && procValue <= (400 + luckyValue.get(player.getName()))) {
				double maxHealth = player.getMaxHealth();
				double currentHealth = player.getHealth();
				if (e.getEntity() instanceof Player) {
					currentHealth += 5;
				} else if (e.getEntity() instanceof Monster) {
					currentHealth += 1;
				}

				if (currentHealth > maxHealth) {
					player.setHealth(maxHealth);
				} else {
					player.setHealth(currentHealth);
					player.getWorld().playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 2,
							(float) 1.5);
				}
			}
		}
		else if (player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta() &&
				 player.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.BLOOM)) {
			Random rand = new Random();
			int upperBound = 4000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = player.getInventory().getItemInOffHand().getItemMeta().getEnchantLevel(CustomEnchants.BLOOM);
			if (eLvl == 1 && procValue <= (160 + luckyValue.get(player.getName()))) {
				double maxHealth = player.getMaxHealth();
				double currentHealth = player.getHealth();
				if (e.getEntity() instanceof Player) {
					currentHealth += 1;
				} else if (e.getEntity() instanceof Monster) {
					currentHealth += 1;
				}

				if (currentHealth > maxHealth) {
					player.setHealth(maxHealth);
				} else {
					player.setHealth(currentHealth);
				}
				player.getWorld().playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 2, (float) 1.5);
			}
			else if (eLvl == 2 && procValue <= (220 + luckyValue.get(player.getName()))) {
				double maxHealth = player.getMaxHealth();
				double currentHealth = player.getHealth();
				if (e.getEntity() instanceof Player) {
					currentHealth += 2;
				} else if (e.getEntity() instanceof Monster) {
					currentHealth += 1;
				}

				if (currentHealth > maxHealth) {
					player.setHealth(maxHealth);
				} else {
					player.setHealth(currentHealth);
				}
				player.getWorld().playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 2, (float) 1.5);
			}
			else if (eLvl == 3 && procValue <= (280 + luckyValue.get(player.getName()))) {
				double maxHealth = player.getMaxHealth();
				double currentHealth = player.getHealth();
				if (e.getEntity() instanceof Player) {
					currentHealth += 3;
				} else if (e.getEntity() instanceof Monster) {
					currentHealth += 1;
				}

				if (currentHealth > maxHealth) {
					player.setHealth(maxHealth);
				} else {
					player.setHealth(currentHealth);
					player.getWorld().playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 2,
							(float) 1.5);
				}
			}
			else if (eLvl == 4 && procValue <= (340 + luckyValue.get(player.getName()))) {
				double maxHealth = player.getMaxHealth();
				double currentHealth = player.getHealth();
				if (e.getEntity() instanceof Player) {
					currentHealth += 4;
				} else if (e.getEntity() instanceof Monster) {
					currentHealth += 1;
				}

				if (currentHealth > maxHealth) {
					player.setHealth(maxHealth);
				} else {
					player.setHealth(currentHealth);
					player.getWorld().playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 2,
							(float) 1.5);
				}
			}
			else if (eLvl == 5 && procValue <= (400 + luckyValue.get(player.getName()))) {
				double maxHealth = player.getMaxHealth();
				double currentHealth = player.getHealth();
				if (e.getEntity() instanceof Player) {
					currentHealth += 5;
				} else if (e.getEntity() instanceof Monster) {
					currentHealth += 1;
				}

				if (currentHealth > maxHealth) {
					player.setHealth(maxHealth);
				} else {
					player.setHealth(currentHealth);
					player.getWorld().playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 2,
							(float) 1.5);
				}
			}
		}
	}
	

	// BLOSSOM
	@SuppressWarnings("deprecation")
	@EventHandler
	public void blossom(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity();
		if (player.getInventory().getBoots() == null) {
			return;
		}
		ItemStack boots = player.getInventory().getBoots();
		if (!boots.hasItemMeta()) {
			return;
		}
		if (!boots.getItemMeta().hasEnchant(CustomEnchants.BLOSSOM)) {
			return;
		}
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}

		Random rand = new Random();
		int upperBound = 4000;
		int procValue = rand.nextInt(upperBound);

		int rr = 255;
		int rg = 102;
		int rb = 99;

		int or = 254;
		int og = 177;
		int ob = 68;

		int yr = 253;
		int yg = 253;
		int yb = 151;

		int gr = 158;
		int gg = 224;
		int gb = 158;

		int br = 158;
		int bg = 193;
		int bb = 207;

		int pr = 204;
		int pg = 153;
		int pb = 201;

		int eLvl = boots.getItemMeta().getEnchantLevel(CustomEnchants.BLOSSOM);
		if (eLvl == 1 && procValue <= (100 + luckyValue.get(player.getName()))) {
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(0, 1, -1.5), 6,
					new Particle.DustOptions(Color.fromBGR(rb, rg, rr), 1));
			double maxHealth = player.getMaxHealth();
			double currentHealth = player.getHealth();
			if (e.getEntity() instanceof Player) {
				currentHealth += 1;
			} else if (e.getEntity() instanceof Monster) {
				currentHealth += 1;
			}

			if (currentHealth > maxHealth) {
				player.setHealth(maxHealth);
			} else {
				player.setHealth(currentHealth);
			}
			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0.5f);
		} 
		else if (eLvl == 2 && procValue <= (130 + luckyValue.get(player.getName()))) {
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(0, 1, -1.5), 6,
					new Particle.DustOptions(Color.fromBGR(rb, rg, rr), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(1.25, 1, -0.75),
					6, new Particle.DustOptions(Color.fromBGR(ob, og, or), 1));
			double maxHealth = player.getMaxHealth();
			double currentHealth = player.getHealth();
			if (e.getEntity() instanceof Player) {
				currentHealth += 1;
			} else if (e.getEntity() instanceof Monster) {
				currentHealth += 1;
			}

			if (currentHealth > maxHealth) {
				player.setHealth(maxHealth);
			} else {
				player.setHealth(currentHealth);
			}
			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0.5f);
		} 
		else if (eLvl == 3 && procValue <= (160 + luckyValue.get(player.getName()))) {
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(0, 1, -1.5), 6,
					new Particle.DustOptions(Color.fromBGR(rb, rg, rr), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(1.25, 1, -0.75),
					6, new Particle.DustOptions(Color.fromBGR(ob, og, or), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(1.25, 1, 0.75), 6,
					new Particle.DustOptions(Color.fromBGR(yb, yg, yr), 1));
			double maxHealth = player.getMaxHealth();
			double currentHealth = player.getHealth();
			if (e.getEntity() instanceof Player) {
				currentHealth += 1;
			} else if (e.getEntity() instanceof Monster) {
				currentHealth += 1;
			}

			if (currentHealth > maxHealth) {
				player.setHealth(maxHealth);
			} else {
				player.setHealth(currentHealth);
			}
			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0.5f);
		} 
		else if (eLvl == 4 && procValue <= (190 + luckyValue.get(player.getName()))) {
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(0, 1, -1.5), 6,
					new Particle.DustOptions(Color.fromBGR(rb, rg, rr), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(1.25, 1, -0.75),
					6, new Particle.DustOptions(Color.fromBGR(ob, og, or), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(1.25, 1, 0.75), 6,
					new Particle.DustOptions(Color.fromBGR(yb, yg, yr), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(0, 1, 1.5), 6,
					new Particle.DustOptions(Color.fromBGR(gb, gg, gr), 1));
			double maxHealth = player.getMaxHealth();
			double currentHealth = player.getHealth();
			if (e.getEntity() instanceof Player) {
				currentHealth += 2;
			} else if (e.getEntity() instanceof Monster) {
				currentHealth += 1;
			}

			if (currentHealth > maxHealth) {
				player.setHealth(maxHealth);
			} else {
				player.setHealth(currentHealth);
			}
			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0.5f);
		} 
		else if (eLvl == 5 && procValue <= (220 + luckyValue.get(player.getName()))) {
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(0, 1, -1.5), 6,
					new Particle.DustOptions(Color.fromBGR(rb, rg, rr), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(1.25, 1, -0.75),
					6, new Particle.DustOptions(Color.fromBGR(ob, og, or), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(1.25, 1, 0.75), 6,
					new Particle.DustOptions(Color.fromBGR(yb, yg, yr), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(0, 1, 1.5), 6,
					new Particle.DustOptions(Color.fromBGR(gb, gg, gr), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(-1.25, 1, 0.75),
					6, new Particle.DustOptions(Color.fromBGR(bb, bg, br), 1));
			double maxHealth = player.getMaxHealth();
			double currentHealth = player.getHealth();
			if (e.getEntity() instanceof Player) {
				currentHealth += 2;
			} else if (e.getEntity() instanceof Monster) {
				currentHealth += 1;
			}

			if (currentHealth > maxHealth) {
				player.setHealth(maxHealth);
			} else {
				player.setHealth(currentHealth);
			}
			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0.5f);
		} 
		else if (eLvl == 6 && procValue <= (250 + luckyValue.get(player.getName()))) {
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(0, 1, -1.5), 6,
					new Particle.DustOptions(Color.fromBGR(rb, rg, rr), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(1.25, 1, -0.75),
					6, new Particle.DustOptions(Color.fromBGR(ob, og, or), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(1.25, 1, 0.75), 6,
					new Particle.DustOptions(Color.fromBGR(yb, yg, yr), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(0, 1, 1.5), 6,
					new Particle.DustOptions(Color.fromBGR(gb, gg, gr), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(-1.25, 1, 0.75),
					6, new Particle.DustOptions(Color.fromBGR(bb, bg, br), 1));
			player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(-1.25, 1, -0.75),
					6, new Particle.DustOptions(Color.fromBGR(pb, pg, pr), 1));
			double maxHealth = player.getMaxHealth();
			double currentHealth = player.getHealth();
			if (e.getEntity() instanceof Player) {
				currentHealth += 2;
			} else if (e.getEntity() instanceof Monster) {
				currentHealth += 1;
			}

			if (currentHealth > maxHealth) {
				player.setHealth(maxHealth);
			} else {
				player.setHealth(currentHealth);
			}
			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0.5f);
		}
	}

	// DEMISE
	@EventHandler
	public void demiseAdd(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getDamager();
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player hitPlayer = (Player) e.getEntity();
		demiseCounter.put(hitPlayer.getName(), 0);
		if (demiseActive.get(hitPlayer.getName()).equals(true)) {
			return;
		}
		if (player.getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack item = player.getInventory().getItemInMainHand();
		if (!item.hasItemMeta()) {
			return;
		}
		if (!item.getItemMeta().hasEnchant(CustomEnchants.DEMISE)) {
			return;
		}
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		int dCtr = demiseCounter.get(player.getName());
		if (dCtr == 0) {
			dCtr = 1;
			demiseCounter.put(player.getName(), dCtr);
			return;
		} else if (dCtr == 1) {
			dCtr = 2;
			demiseCounter.put(player.getName(), dCtr);
			return;
		} else if (dCtr == 2) {
			dCtr = 3;
			demiseCounter.put(player.getName(), dCtr);
			return;
		} else if (dCtr == 3) {
			dCtr = 4;
			demiseCounter.put(player.getName(), dCtr);
			return;
		} else if (dCtr == 4) {
			dCtr = 0;
			demiseCounter.put(player.getName(), dCtr);
			demiseActive.put(hitPlayer.getName(), true);
			// do particles here
			int eLvl = item.getItemMeta().getEnchantLevel(CustomEnchants.DEMISE);
			if (eLvl == 1) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 3, 1.5f);
				for (int i = 0; i <= 80; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 80);
			} else if (eLvl == 2) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 3, 1.5f);
				for (int i = 0; i <= 100; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 100);
			} else if (eLvl == 3) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 3, 1.5f);
				for (int i = 0; i <= 120; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 120);
			} else if (eLvl == 4) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 3, 1.5f);
				for (int i = 0; i <= 140; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 140);
			} else if (eLvl == 5) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 3, 1.5f);
				for (int i = 0; i <= 160; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 160);
			} else if (eLvl == 6) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 3, 1.5f);
				for (int i = 0; i <= 180; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 180);
			} else if (eLvl == 7) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 3, 1.5f);
				for (int i = 0; i <= 200; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 200);
			}
		}
	}

	@EventHandler
	public void demiseThrowAdd(ProjectileHitEvent e) {
		if (!(e.getEntity() instanceof Trident)) {
			return;
		}
		Trident item = (Trident) e.getEntity();
		if (!(item.getShooter() instanceof Player)) {
			return;
		}
		Player player = (Player) item.getShooter();
		if (!(e.getHitEntity() instanceof Player)) {
			return;
		}
		Player hitPlayer = (Player) e.getHitEntity();
		demiseCounter.put(hitPlayer.getName(), 0);
		if (demiseActive.get(hitPlayer.getName()).equals(true)) {
			return;
		}
		if (!item.getItem().hasItemMeta()) {
			return;
		}
		if (!item.getItem().getItemMeta().hasEnchant(CustomEnchants.DEMISE)) {
			return;
		}
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		int dCtr = demiseCounter.get(player.getName());
		if (dCtr == 0) {
			dCtr = 1;
			demiseCounter.put(player.getName(), dCtr);
			return;
		} else if (dCtr == 1) {
			dCtr = 2;
			demiseCounter.put(player.getName(), dCtr);
			return;
		} else if (dCtr == 2) {
			dCtr = 3;
			demiseCounter.put(player.getName(), dCtr);
			return;
		} else if (dCtr == 3) {
			dCtr = 4;
			demiseCounter.put(player.getName(), dCtr);
			return;
		} else if (dCtr == 4) {
			dCtr = 0;
			demiseCounter.put(player.getName(), dCtr);
			demiseActive.put(hitPlayer.getName(), true);
			// do particles here
			int eLvl = item.getItem().getItemMeta().getEnchantLevel(CustomEnchants.DEMISE);
			if (eLvl == 1) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ITEM_HOE_TILL, 5, 0.2f);
				for (int i = 0; i <= 80; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 80);
			} else if (eLvl == 2) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ITEM_HOE_TILL, 5, 0.2f);
				for (int i = 0; i <= 100; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 100);
			} else if (eLvl == 3) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ITEM_HOE_TILL, 5, 0.2f);
				for (int i = 0; i <= 120; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 120);
			} else if (eLvl == 4) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ITEM_HOE_TILL, 5, 0.2f);
				for (int i = 0; i <= 140; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 140);
			} else if (eLvl == 5) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ITEM_HOE_TILL, 5, 0.2f);
				for (int i = 0; i <= 160; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 160);
			} else if (eLvl == 6) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ITEM_HOE_TILL, 5, 0.2f);
				for (int i = 0; i <= 180; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 180);
			} else if (eLvl == 7) {
				hitPlayer.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "You've met your "
						+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demise");
				hitPlayer.getWorld().playSound(hitPlayer.getLocation(), Sound.ITEM_HOE_TILL, 5, 0.2f);
				for (int i = 0; i <= 200; i++) {
					if (i % 5 == 0) {
						demiseParticle(hitPlayer, i);
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						demiseActive.put(hitPlayer.getName(), false);
					}
				}, 200);
			}
		}
	}

	@EventHandler
	public void demiseDamage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if (demiseActive.get(player.getName()).equals(true)) {
				e.setDamage(e.getDamage() * 1.2);
			}
		}
	}

	public void demiseParticle(Player hitPlayer, int delay) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				hitPlayer.getLocation().getWorld().spawnParticle(Particle.REDSTONE,
						hitPlayer.getLocation().add(0, 2.25, -0.75), 6,
						new Particle.DustOptions(Color.fromBGR(25, 25, 25), 1));
				hitPlayer.getLocation().getWorld().spawnParticle(Particle.REDSTONE,
						hitPlayer.getLocation().add(0.75, 2.25, -0.375), 6,
						new Particle.DustOptions(Color.fromBGR(25, 25, 25), 1));
				hitPlayer.getLocation().getWorld().spawnParticle(Particle.REDSTONE,
						hitPlayer.getLocation().add(0.75, 2.25, 0.375), 6,
						new Particle.DustOptions(Color.fromBGR(25, 25, 25), 1));
				hitPlayer.getLocation().getWorld().spawnParticle(Particle.REDSTONE,
						hitPlayer.getLocation().add(0, 2.25, 0.75), 6,
						new Particle.DustOptions(Color.fromBGR(25, 25, 25), 1));
				hitPlayer.getLocation().getWorld().spawnParticle(Particle.REDSTONE,
						hitPlayer.getLocation().add(-0.75, 2.25, 0.375), 6,
						new Particle.DustOptions(Color.fromBGR(25, 25, 25), 1));
				hitPlayer.getLocation().getWorld().spawnParticle(Particle.REDSTONE,
						hitPlayer.getLocation().add(-0.75, 2.25, -0.375), 6,
						new Particle.DustOptions(Color.fromBGR(25, 25, 25), 1));
			}
		}, delay);
	}

	@EventHandler
	public void demiseReset(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		demiseActive.put(player.getName(), false);
		demiseCounter.put(player.getName(), 0);
	}

	// DRUNK
	@EventHandler
	public void drunkEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() != null) {
			ItemStack item = e.getItemStack();
			if (!item.hasItemMeta()) {
				return;
			}
			if (!item.getItemMeta().hasEnchant(CustomEnchants.DRUNK)) {
				return;
			}
			int eLvl = item.getItemMeta().getEnchantLevel(CustomEnchants.DRUNK);
			if (eLvl == 1) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1));
			} 
			else if (eLvl == 2) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 3));
			}
		}
	}

	@EventHandler
	public void drunkUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() == null) {
			return;
		}
		ItemStack playerArmor = e.getItemStack();
		if (!playerArmor.hasItemMeta()) {
			return;
		}
		if (!playerArmor.getItemMeta().hasEnchant(CustomEnchants.DRUNK)) {
			return;
		}
		player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
		player.removePotionEffect(PotionEffectType.SLOW);
	}

	// DODGE
	@EventHandler
	public void dodge(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if (player.getInventory().getLeggings() == null) {
				return;
			}
			ItemStack dodgeItem = player.getInventory().getLeggings();
			if (!dodgeItem.hasItemMeta()) {
				return;
			}
			if (!player.getInventory().getLeggings().getItemMeta().hasEnchant(CustomEnchants.DODGE)) {
				return;
			}
			if (silenceActive.get(player.getName()).equals(true)) {
				return;
			}

			Random rand = new Random();
			int upperBound = 6000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = dodgeItem.getItemMeta().getEnchantLevel(CustomEnchants.DODGE);
			if (eLvl == 1 && procValue <= (150 + luckyValue.get(player.getName()))) {
				player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 8, 0.75, 0.75, 0.75, 0.1);
				player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 0.5f, 0.8f);
				e.setCancelled(true);
			} 
			else if (eLvl == 2 && procValue <= (210 + luckyValue.get(player.getName()))) {
				player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 8, 0.75, 0.75, 0.75, 0.1);
				player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 0.5f, 0.8f);
				e.setCancelled(true);
			} 
			else if (eLvl == 3 && procValue <= (270 + luckyValue.get(player.getName()))) {
				player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 8, 0.75, 0.75, 0.75, 0.1);
				player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 0.5f, 0.8f);
				e.setCancelled(true);
			} 
			else if (eLvl == 4 && procValue <= (330 + luckyValue.get(player.getName()))) {
				player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 8, 0.75, 0.75, 0.75, 0.1);
				player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 0.5f, 0.8f);
				e.setCancelled(true);
			}
		}
	}

	// ENRAGE
	@SuppressWarnings("deprecation")
	@EventHandler
	public void enrage(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player attacker = (Player) e.getDamager();
			if (silenceActive.get(attacker.getName()).equals(true)) {
				return;
			}

			if (attacker.getInventory().getItemInMainHand() == null) {
				return;
			}
			ItemStack weapon = attacker.getInventory().getItemInMainHand();
			if (!weapon.hasItemMeta()) {
				return;
			}
			if (!attacker.getPlayer().getInventory().getItemInMainHand().getItemMeta()
					.hasEnchant(CustomEnchants.ENRAGE)) {
				return;
			}
			if (attacker.isOnGround()) {
				return;
			}
			if (attacker.getVelocity().getY() > 0) {
				return;
			}

			int eLvl = weapon.getItemMeta().getEnchantLevel(CustomEnchants.ENRAGE);
			if (eLvl == 1) {
				e.setDamage(e.getDamage() * 1.08);
				if (e.getEntity() instanceof LivingEntity) {
					e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1,
							(float) 0.8);
				}
			} else if (eLvl == 2) {
				e.setDamage(e.getDamage() * 1.12);
				if (e.getEntity() instanceof LivingEntity) {
					e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1,
							(float) 0.8);
				}
			} else if (eLvl == 3) {
				e.setDamage(e.getDamage() * 1.16);
				if (e.getEntity() instanceof LivingEntity) {
					e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1,
							(float) 0.8);
				}
			} else if (eLvl == 4) {
				e.setDamage(e.getDamage() * 1.2);
				if (e.getEntity() instanceof LivingEntity) {
					e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1,
							(float) 0.8);
				}
			}
		}
	}

	// FATE
	@EventHandler
	public void fate(BlockBreakEvent e) {
		if (e.getPlayer().getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack fateItem = e.getPlayer().getInventory().getItemInMainHand();
		if (!fateItem.hasItemMeta()) {
			return;
		}
		if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.FATE)) {
			return;
		}
		if (e.getPlayer().getGameMode() == GameMode.CREATIVE || e.getPlayer().getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (e.getBlock().getState() instanceof Container) {
			return;
		}
		if (silenceActive.get(e.getPlayer().getName()).equals(true)) {
			return;
		}

		Player player = e.getPlayer();
		Random rand = new Random();
		int upperBound = 2000;
		int procValue = rand.nextInt(upperBound);

		int eLvl = fateItem.getItemMeta().getEnchantLevel(CustomEnchants.FATE);
		if (eLvl == 1 && procValue <= (100 + luckyValue.get(player.getName()))) {
			for (ItemStack item : e.getBlock().getDrops()) {
				if (item.getType().equals(Material.COAL) || item.getType().equals(Material.RAW_COPPER)
						|| item.getType().equals(Material.RAW_IRON) || item.getType().equals(Material.RAW_GOLD)
						|| item.getType().equals(Material.REDSTONE) || item.getType().equals(Material.LAPIS_LAZULI)
						|| item.getType().equals(Material.DIAMOND) || item.getType().equals(Material.EMERALD)) {
					e.setCancelled(true);
					if (fateItem.getItemMeta().hasEnchant(CustomEnchants.TELEPATHY)) {
						player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
						return;
					}
					if (!fateItem.getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
						e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), item);
					} else {
						int fortuneLevel = fateItem.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1;
						Random r = new Random();
						int proc = r.nextInt(fortuneLevel);
						for (int k = 0; k < proc; k++) {
							e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), item);
						}
					}
					player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
				}
			}
		} else if (eLvl == 2 && procValue <= (200 + luckyValue.get(player.getName()))) {
			for (ItemStack item : e.getBlock().getDrops()) {
				if (item.getType().equals(Material.COAL) || item.getType().equals(Material.RAW_COPPER)
						|| item.getType().equals(Material.RAW_IRON) || item.getType().equals(Material.RAW_GOLD)
						|| item.getType().equals(Material.REDSTONE) || item.getType().equals(Material.LAPIS_LAZULI)
						|| item.getType().equals(Material.DIAMOND) || item.getType().equals(Material.EMERALD)) {
					e.setCancelled(true);
					if (fateItem.getItemMeta().hasEnchant(CustomEnchants.TELEPATHY)) {
						player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
						return;
					}
					if (!fateItem.getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
						e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), item);
					} else {
						int fortuneLevel = fateItem.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1;
						Random r = new Random();
						int proc = r.nextInt(fortuneLevel);
						for (int k = 0; k < proc; k++) {
							e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), item);
						}
					}
					player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
				}
			}
		} else if (eLvl == 3 && procValue <= (300 + luckyValue.get(player.getName()))) {
			for (ItemStack item : e.getBlock().getDrops()) {
				if (item.getType().equals(Material.COAL) || item.getType().equals(Material.RAW_COPPER)
						|| item.getType().equals(Material.RAW_IRON) || item.getType().equals(Material.RAW_GOLD)
						|| item.getType().equals(Material.REDSTONE) || item.getType().equals(Material.LAPIS_LAZULI)
						|| item.getType().equals(Material.DIAMOND) || item.getType().equals(Material.EMERALD)) {
					e.setCancelled(true);
					if (fateItem.getItemMeta().hasEnchant(CustomEnchants.TELEPATHY)) {
						player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
						return;
					}
					if (!fateItem.getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
						e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), item);
					} else {
						int fortuneLevel = fateItem.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1;
						Random r = new Random();
						int proc = r.nextInt(fortuneLevel);
						for (int k = 0; k < proc; k++) {
							e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), item);
						}
					}
					player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
				}
			}
		}
	}

	// FURY
	@EventHandler
	public void fury(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Monster && e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();
			Monster hitEntity = (Monster) e.getEntity();
			if (attackingPlayer.getInventory().getItemInMainHand() == null) {
				return;
			}
			ItemStack furyWeapon = attackingPlayer.getInventory().getItemInMainHand();
			if (!furyWeapon.hasItemMeta()) {
				return;
			}
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta()
					.hasEnchant(CustomEnchants.FURY)) {
				return;
			}
			if (silenceActive.get(attackingPlayer.getName()).equals(true)) {
				return;
			}

			double damageDealt = e.getDamage();
			if (damageDealt > 1) {
				getLastFuryDamage.put(attackingPlayer.getName(), damageDealt);
			}
			if (getLastFuryDamage.get(attackingPlayer.getName()) == null) {
				return;
			}
			double trueLastDamage = getLastFuryDamage.get(attackingPlayer.getName());

			int eLvl = furyWeapon.getItemMeta().getEnchantLevel(CustomEnchants.FURY);
			double furyMultiplier = 0;
			if (eLvl == 1) {
				furyMultiplier = currentFuryMultiplier.get(attackingPlayer.getName());
				if (furyMultiplier >= 0.25) {
					Location hitLocation = hitEntity.getLocation();
					hitLocation.setY(hitLocation.getY() + 1);
					hitEntity.getWorld().spawnParticle(Particle.CRIMSON_SPORE, hitLocation, 25, 0.5, 0.5, 0.5, 0.1);
				} else if (furyMultiplier < 0.35) {
					furyMultiplier += 0.035;
				}
			} else if (eLvl == 2) {
				furyMultiplier = currentFuryMultiplier.get(attackingPlayer.getName());
				if (furyMultiplier >= 0.5) {
					Location hitLocation = hitEntity.getLocation();
					hitLocation.setY(hitLocation.getY() + 1);
					hitEntity.getWorld().spawnParticle(Particle.CRIMSON_SPORE, hitLocation, 25, 0.5, 0.5, 0.5, 0.1);
				} else if (furyMultiplier < 0.5) {
					furyMultiplier += 0.05;
				}
			} else if (eLvl == 3) {
				furyMultiplier = currentFuryMultiplier.get(attackingPlayer.getName());
				if (furyMultiplier >= 0.65) {
					Location hitLocation = hitEntity.getLocation();
					hitLocation.setY(hitLocation.getY() + 1);
					hitEntity.getWorld().spawnParticle(Particle.CRIMSON_SPORE, hitLocation, 25, 0.5, 0.5, 0.5, 0.1);
				} else if (furyMultiplier < 0.65) {
					furyMultiplier += 0.065;
				}
			} else if (eLvl == 4) {
				furyMultiplier = currentFuryMultiplier.get(attackingPlayer.getName());
				if (furyMultiplier >= 0.8) {
					Location hitLocation = hitEntity.getLocation();
					hitLocation.setY(hitLocation.getY() + 1);
					hitEntity.getWorld().spawnParticle(Particle.CRIMSON_SPORE, hitLocation, 25, 0.5, 0.5, 0.5, 0.1);
				} else if (furyMultiplier < 8) {
					furyMultiplier += 0.08;
				}
			} else if (eLvl == 5) {
				furyMultiplier = currentFuryMultiplier.get(attackingPlayer.getName());
				if (furyMultiplier >= 1) {
					Location hitLocation = hitEntity.getLocation();
					hitLocation.setY(hitLocation.getY() + 1);
					hitEntity.getWorld().spawnParticle(Particle.CRIMSON_SPORE, hitLocation, 25, 0.5, 0.5, 0.5, 0.1);
				} else if (furyMultiplier < 1) {
					furyMultiplier += 0.1;
				}
			} else if (eLvl == 6) {
				furyMultiplier = currentFuryMultiplier.get(attackingPlayer.getName());
				if (furyMultiplier >= 1.25) {
					Location hitLocation = hitEntity.getLocation();
					hitLocation.setY(hitLocation.getY() + 1);
					hitEntity.getWorld().spawnParticle(Particle.CRIMSON_SPORE, hitLocation, 25, 0.5, 0.5, 0.5, 0.1);
				} else if (furyMultiplier < 1.25) {
					furyMultiplier += 0.125;
				}
			}
			currentFuryMultiplier.put(attackingPlayer.getName(), furyMultiplier);
			double extraDamage = furyMultiplier * trueLastDamage;
			hitEntity.damage(extraDamage);
		}
	}

	// FURY MULTIPLIER REMOVAL
	@EventHandler
	public void furyRemove(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player hitPlayer = (Player) e.getEntity();
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Monster) {
			currentFuryMultiplier.put(hitPlayer.getName(), 0.0);
		} 
		else if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) {
			currentFuryMultiplier.put(hitPlayer.getName(), 0.0);
		} 
		else if (e.getEntity() instanceof Player && e.getDamager() instanceof Explosion) {
			currentFuryMultiplier.put(hitPlayer.getName(), 0.0);
		}
		else if (e.getEntity() instanceof Player && e.getDamager() instanceof WitherSkull) {
			currentFuryMultiplier.put(hitPlayer.getName(), 0.0);
		}
	}

	// FURY MULTIPLIER REMOVAL ON SERVER JOIN
	@EventHandler
	public void furyRemovePlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		currentFuryMultiplier.put(player.getName(), 0.0);
	}

	// GLORY Main Hand
	@EventHandler
	public void gloryMainHand(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player hit = (Player) e.getEntity();

		if (hit.getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack shield = hit.getInventory().getItemInMainHand();
		if (!shield.hasItemMeta()) {
			return;
		}
		if (!shield.getItemMeta().hasEnchant(CustomEnchants.GLORY)) {
			return;
		}
		if (silenceActive.get(hit.getName()).equals(true)) {
			return;
		}

		int eLvl = shield.getItemMeta().getEnchantLevel(CustomEnchants.GLORY);
		if (eLvl == 1) {
			e.setDamage(e.getDamage() / 1.06);
		} 
		else if (eLvl == 2) {
			e.setDamage(e.getDamage() / 1.09);
		} 
		else if (eLvl == 3) {
			e.setDamage(e.getDamage() / 1.12);
		}
	}

	// GLORY off hand
	@EventHandler
	public void gloryOffHand(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player hit = (Player) e.getEntity();

		if (hit.getInventory().getItemInOffHand() == null) {
			return;
		}
		ItemStack shield = hit.getInventory().getItemInOffHand();
		if (!shield.hasItemMeta()) {
			return;
		}
		if (!shield.getItemMeta().hasEnchant(CustomEnchants.GLORY)) {
			return;
		}
		if (silenceActive.get(hit.getName()).equals(true)) {
			return;
		}

		int eLvl = shield.getItemMeta().getEnchantLevel(CustomEnchants.GLORY);
		if (eLvl == 1) {
			e.setDamage(e.getDamage() / 1.06);
		} 
		else if (eLvl == 2) {
			e.setDamage(e.getDamage() / 1.09);
		} 
		else if (eLvl == 3) {
			e.setDamage(e.getDamage() / 1.12);
		}
	}

	// INTELLECT
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@EventHandler
	public void intellect(EntityDeathEvent e) {
		if (e.getEntity() instanceof Monster) {
			if (e.getEntity().getKiller() == null) {
				return;
			}
			Player attackingPlayer = e.getEntity().getKiller();

			if (attackingPlayer.getInventory().getItemInMainHand() == null) {
				return;
			}
			ItemStack intellectWeapon = attackingPlayer.getInventory().getItemInMainHand();
			if (!intellectWeapon.hasItemMeta()) {
				return;
			}
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta()
					.hasEnchant(CustomEnchants.INTELLECT)) {
				return;
			}
			if (silenceActive.get(attackingPlayer.getName()).equals(true)) {
				return;
			}

			// use random number generator to choose whether or not to proc the enchant
			Random rand = new Random();
			int upperBound = 1000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = intellectWeapon.getItemMeta().getEnchantLevel(CustomEnchants.INTELLECT);
			if (eLvl == 1 && procValue <= (150 + luckyValue.get(attackingPlayer.getName()))) {
				((ExperienceOrb) attackingPlayer.getWorld().spawn(e.getEntity().getLocation(),
						(Class) ExperienceOrb.class)).setExperience((int) (e.getDroppedExp() / 2));
			} 
			else if (eLvl == 2 && procValue <= (225 + luckyValue.get(attackingPlayer.getName()))) {
				((ExperienceOrb) attackingPlayer.getWorld().spawn(e.getEntity().getLocation(),
						(Class) ExperienceOrb.class)).setExperience((int) (e.getDroppedExp() / 1.5));
			} 
			else if (eLvl == 3 && procValue <= (300 + luckyValue.get(attackingPlayer.getName()))) {
				((ExperienceOrb) attackingPlayer.getWorld().spawn(e.getEntity().getLocation(),
						(Class) ExperienceOrb.class)).setExperience((int) (e.getDroppedExp()));
			} 
			else if (eLvl == 4 && procValue <= (375 + luckyValue.get(attackingPlayer.getName()))) {
				((ExperienceOrb) attackingPlayer.getWorld().spawn(e.getEntity().getLocation(),
						(Class) ExperienceOrb.class)).setExperience((int) (e.getDroppedExp() * 1.5));
			}
		}
	}

	// LIFESTEAL
	@SuppressWarnings("deprecation")
	@EventHandler
	public void lifesteal(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player attacker = (Player) e.getDamager();

			if (attacker.getInventory().getItemInMainHand() == null) {
				return;
			}
			ItemStack weapon = attacker.getInventory().getItemInMainHand();
			if (!weapon.hasItemMeta()) {
				return;
			}
			if (!attacker.getPlayer().getInventory().getItemInMainHand().getItemMeta()
					.hasEnchant(CustomEnchants.LIFESTEAL)) {
				return;
			}
			if (silenceActive.get(attacker.getName()).equals(true)) {
				return;
			}

			Random rand = new Random();
			int upperBound = 4000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = weapon.getItemMeta().getEnchantLevel(CustomEnchants.LIFESTEAL);
			if (eLvl == 1 && procValue <= (140 + luckyValue.get(attacker.getName()))) {
				double maxHealth = attacker.getMaxHealth();
				double currentHealth = attacker.getHealth();
				if (e.getEntity() instanceof Player) {
					currentHealth += 2;
				} else if (e.getEntity() instanceof Monster) {
					currentHealth += 1;
				}

				if (currentHealth > maxHealth) {
					attacker.setHealth(maxHealth);
				} else {
					attacker.setHealth(currentHealth);
				}
				attacker.getWorld().playSound(attacker.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 2,
						(float) 1.5);
			} 
			else if (eLvl == 2 && procValue <= (220 + luckyValue.get(attacker.getName()))) {
				double maxHealth = attacker.getMaxHealth();
				double currentHealth = attacker.getHealth();
				if (e.getEntity() instanceof Player) {
					currentHealth += 4;
				} else if (e.getEntity() instanceof Monster) {
					currentHealth += 1;
				}

				if (currentHealth > maxHealth) {
					attacker.setHealth(maxHealth);
				} else {
					attacker.setHealth(currentHealth);
				}
				attacker.getWorld().playSound(attacker.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 2,
						(float) 1.5);
			} 
			else if (eLvl == 3 && procValue <= (300 + luckyValue.get(attacker.getName()))) {
				double maxHealth = attacker.getMaxHealth();
				double currentHealth = attacker.getHealth();
				if (e.getEntity() instanceof Player) {
					currentHealth += 6;
				} else if (e.getEntity() instanceof Monster) {
					currentHealth += 1;
				}

				if (currentHealth > maxHealth) {
					attacker.setHealth(maxHealth);
				} else {
					attacker.setHealth(currentHealth);
					attacker.getWorld().playSound(attacker.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 2,
							(float) 1.5);
				}
			}
		}
	}

	// LUCKY EQUIP
	@EventHandler
	public void luckyEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() != null) {
			ItemStack item = e.getItemStack();
			if (!item.hasItemMeta()) {
				return;
			}
			if (!item.getItemMeta().hasEnchant(CustomEnchants.LUCKY)) {
				return;
			}
			int eLvl = item.getItemMeta().getEnchantLevel(CustomEnchants.LUCKY);
			if (eLvl == 1) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) + 1);
			} else if (eLvl == 2) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) + 2);
			} else if (eLvl == 3) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) + 3);
			} else if (eLvl == 4) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) + 4);
			} else if (eLvl == 5) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) + 5);
			} else if (eLvl == 6) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) + 6);
			} else if (eLvl == 7) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) + 7);
			} else if (eLvl == 8) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) + 8);
			} else if (eLvl == 9) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) + 9);
			} else if (eLvl == 10) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) + 10);
			}
		}
	}

	// LUCKY UNEQUIP
	@EventHandler
	public void luckyUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() != null) {
			ItemStack item = e.getItemStack();
			if (!item.hasItemMeta()) {
				return;
			}
			if (!item.getItemMeta().hasEnchant(CustomEnchants.LUCKY)) {
				return;
			}
			int eLvl = item.getItemMeta().getEnchantLevel(CustomEnchants.LUCKY);
			if (eLvl == 1) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) - 1);
			} else if (eLvl == 2) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) - 2);
			} else if (eLvl == 3) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) - 3);
			} else if (eLvl == 4) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) - 4);
			} else if (eLvl == 5) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) - 5);
			} else if (eLvl == 6) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) - 6);
			} else if (eLvl == 7) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) - 7);
			} else if (eLvl == 8) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) - 8);
			} else if (eLvl == 9) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) - 9);
			} else if (eLvl == 10) {
				luckyValue.put(player.getName(), luckyValue.get(player.getName()) - 10);
			}
		}
	}

	@EventHandler
	public void luckyValueReset(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		luckyValue.put(player.getName(), 0);
	}

	
	//METEOR
	@EventHandler
	public void meteor(ProjectileHitEvent e) {
		ProjectileSource shooter = e.getEntity().getShooter();
		if (!(shooter instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity().getShooter();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		
		if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta() &&
			player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.METEOR)) {
			Random rand = new Random();
			int upperBound = 10000;
			if (player.getInventory().getItemInMainHand().getType().equals(Material.CROSSBOW)) {
				upperBound = 18000;
			}
			int procValue = rand.nextInt(upperBound);

			int eLvl = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(CustomEnchants.METEOR);
			if (eLvl == 1 && procValue <= (250 + luckyValue.get(player.getName()))) {
				meteorExplosion(e.getEntity(), 2, 9);
			} 
			else if (eLvl == 2 && procValue <= (300 + luckyValue.get(player.getName()))) {
				meteorExplosion(e.getEntity(), 2, 12);
			} 
			else if (eLvl == 3 && procValue <= (350 + luckyValue.get(player.getName()))) {
				meteorExplosion(e.getEntity(), 3, 15);
			} 
			else if (eLvl == 4 && procValue <= (400 + luckyValue.get(player.getName()))) {
				meteorExplosion(e.getEntity(), 3, 18);
			}
		}
		else if (player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta() &&
				 player.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.METEOR)) {
			Random rand = new Random();
			int upperBound = 10000;
			if (player.getInventory().getItemInOffHand().getType().equals(Material.CROSSBOW)) {
				upperBound = 18000;
			}
			int procValue = rand.nextInt(upperBound);

			int eLvl = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(CustomEnchants.METEOR);
			if (eLvl == 1 && procValue <= (250 + luckyValue.get(player.getName()))) {
				meteorExplosion(e.getEntity(), 2, 9);
			} 
			else if (eLvl == 2 && procValue <= (300 + luckyValue.get(player.getName()))) {
				meteorExplosion(e.getEntity(), 2, 12);
			} 
			else if (eLvl == 3 && procValue <= (350 + luckyValue.get(player.getName()))) {
				meteorExplosion(e.getEntity(), 3, 15);
			} 
			else if (eLvl == 4 && procValue <= (400 + luckyValue.get(player.getName()))) {
				meteorExplosion(e.getEntity(), 3, 18);
			}
		}
	}
	// Meteor explosion
	public void meteorExplosion(Projectile projectile, int size, double damage) {
		Location location1 = projectile.getLocation().add(0, 9, 0);
		Location location2 = projectile.getLocation().add(0, 6, 0);
		Location location3 = projectile.getLocation().add(0, 3, 0);
		Location location4 = projectile.getLocation();

		// particle 1
		projectile.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, location1.getX(), location1.getY(),
				location1.getZ(), 15, 1, 1, 1, 0.35);
		projectile.getWorld().spawnParticle(Particle.SMALL_FLAME, location1.getX(), location1.getY(), location1.getZ(),
				20, 1, 1, 1, 0.25);
		projectile.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location1.getX(), location1.getY(), location1.getZ(),
				20, 1, 1, 1, 0.25);

		// particle 2
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				projectile.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, location2.getX(), location2.getY(),
						location2.getZ(), 15, 1, 1, 1, 0.35);
				projectile.getWorld().spawnParticle(Particle.SMALL_FLAME, location2.getX(), location2.getY(),
						location2.getZ(), 20, 1, 1, 1, 0.25);
				projectile.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location2.getX(), location2.getY(),
						location2.getZ(), 20, 1, 1, 1, 0.25);
			}
		}, 5);

		// particle 3
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				projectile.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, location3.getX(), location3.getY(),
						location3.getZ(), 15, 1, 1, 1, 0.35);
				projectile.getWorld().spawnParticle(Particle.SMALL_FLAME, location3.getX(), location3.getY(),
						location3.getZ(), 20, 1, 1, 1, 0.25);
				projectile.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location3.getX(), location3.getY(),
						location3.getZ(), 20, 1, 1, 1, 0.25);
			}
		}, 10);

		// particle 4
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				projectile.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, location4.getX(), location4.getY(),
						location4.getZ(), 2, 1, 1, 1, 0.9);
				projectile.getWorld().spawnParticle(Particle.FLAME, location4.getX(), location4.getY(),
						location4.getZ(), 25, 2, 2, 2, 0.5);
				projectile.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location4.getX(), location4.getY(),
						location4.getZ(), 25, 2, 2, 2, 0.5);
				projectile.getWorld().createExplosion(location4.getX(), location4.getY() + 1, location4.getZ(), size,
						false, false);
				for (Entity nearby : projectile.getNearbyEntities(4, 5, 4)) {
					if (nearby instanceof LivingEntity) {
						((LivingEntity) nearby).damage(damage);
					}
				}
			}
		}, 15);
	}

	//PUNCTURE
	@EventHandler
	public void puncture(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Projectile)) {
			return;
		}
		Projectile projectile = (Projectile) e.getDamager();
		if (!(projectile.getShooter() instanceof Player)) {
			return;
		}
		Player player = (Player) projectile.getShooter();
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta() &&
			player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.PUNCTURE)) {
			Entity entity = e.getEntity();
			if (!(entity instanceof Player)) {
				return;
			}
			Player hitEntity = (Player) e.getEntity();
			if (hitEntity.getInventory().getHelmet() != null && hitEntity.getInventory().getChestplate() != null
					&& hitEntity.getInventory().getLeggings() != null && hitEntity.getInventory().getBoots() != null) {
				e.setDamage(e.getDamage() * 1.25);
			}
		}
		else if (player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta() &&
				 player.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.PUNCTURE)) {
			Entity entity = e.getEntity();
			if (!(entity instanceof Player)) {
				return;
			}
			Player hitEntity = (Player) e.getEntity();
			if (hitEntity.getInventory().getHelmet() != null && hitEntity.getInventory().getChestplate() != null
					&& hitEntity.getInventory().getLeggings() != null && hitEntity.getInventory().getBoots() != null) {
				e.setDamage(e.getDamage() * 1.25);
			}
		}
	}


	// RAGE
	@EventHandler
	public void rage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player attacker = (Player) e.getDamager();
			Player hit = (Player) e.getEntity();

			if (attacker.getInventory().getItemInMainHand() == null) {
				return;
			}
			ItemStack weapon = attacker.getInventory().getItemInMainHand();
			if (!weapon.hasItemMeta()) {
				return;
			}
			if (!attacker.getPlayer().getInventory().getItemInMainHand().getItemMeta()
					.hasEnchant(CustomEnchants.RAGE)) {
				return;
			}
			if (silenceActive.get(attacker.getName()).equals(true)) {
				return;
			}

			double damageDealt = e.getDamage();
			int eLvl = weapon.getItemMeta().getEnchantLevel(CustomEnchants.RAGE);
			if (eLvl == 1) {
				double rageMultiplier = currentRageMultiplier.get(attacker.getName());
				if (rageMultiplier >= 0.125) {
					rageMultiplier = 0.125;
					Location hitLocation = hit.getLocation();
					hitLocation.setY(hitLocation.getY() + 1);
					hit.getWorld().spawnParticle(Particle.SMOKE_NORMAL, hitLocation, 25, 0.75, 0.75, 0.75, 0.1);
				} else if (rageMultiplier < 0.125) {
					rageMultiplier += 0.0125;
				}
				currentRageMultiplier.put(attacker.getName(), rageMultiplier);
				double extraDamage = rageMultiplier * damageDealt;
				hit.damage(extraDamage);
			}
			if (eLvl == 2) {
				double rageMultiplier = currentRageMultiplier.get(attacker.getName());
				if (rageMultiplier >= 0.25) {
					rageMultiplier = 0.25;
					Location hitLocation = hit.getLocation();
					hitLocation.setY(hitLocation.getY() + 1);
					hit.getWorld().spawnParticle(Particle.SMOKE_NORMAL, hitLocation, 25, 0.75, 0.75, 0.75, 0.1);
				} else if (rageMultiplier < 0.25) {
					rageMultiplier += 0.025;
				}
				currentRageMultiplier.put(attacker.getName(), rageMultiplier);
				double extraDamage = rageMultiplier * damageDealt;
				hit.damage(extraDamage);
			}
		}
	}

	// RAGE MULTIPLIER REMOVAL
	@EventHandler
	public void rageRemove(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player hitPlayer = (Player) e.getEntity();
			currentRageMultiplier.put(hitPlayer.getName(), 0.0);
		}
	}

	// RAGE MULTIPLIER REMOVAL ON SERVER JOIN
	@EventHandler
	public void rageRemovePlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		currentRageMultiplier.put(player.getName(), 0.0);
	}

	// SAFEGUARD Equip Event
	@EventHandler
	public void safeguard(PlayerSwapHandItemsEvent e) {
		Player player = e.getPlayer();

		// safeguard equip
		if (player.getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack MainHandItem = player.getInventory().getItemInMainHand();
		if (!MainHandItem.hasItemMeta()) {
			return;
		}
		if (MainHandItem.getItemMeta().hasEnchant(CustomEnchants.SAFEGUARD)) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
			return;
		}
	}

	// SAFEGUARD Unequip Event
	@EventHandler
	public void safeguardUnequip(PlayerSwapHandItemsEvent e) {
		Player player = e.getPlayer();

		if (player.getInventory().getItemInOffHand() == null) {
			return;
		}
		ItemStack offHandItem = player.getInventory().getItemInOffHand();
		if (!offHandItem.hasItemMeta()) {
			return;
		}
		if (offHandItem.getItemMeta().hasEnchant(CustomEnchants.SAFEGUARD)) {
			player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			return;
		}
	}

	// SAFEGUARD Inventory change
	@EventHandler
	public void safeguardInventory(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		Player player = (Player) e.getWhoClicked();
		if (e.getInventory().getType().equals(InventoryType.CRAFTING)) {
			if (e.getRawSlot() == 45) {
				if (e.getCursor().getType().equals(Material.SHIELD)) {
					ItemStack item = e.getCursor();
					if (!item.hasItemMeta()) {
						return;
					}
					if (item.getItemMeta().hasEnchant(CustomEnchants.SAFEGUARD)) {
						player.addPotionEffect(
								new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
						return;
					}
				} else {
					return;
				}
			} else if (e.getCurrentItem().getType().equals(Material.SHIELD)
					&& (e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.SHIFT_RIGHT))) {
				if (player.getInventory().getItemInOffHand().getType().equals(Material.AIR)) {
					ItemStack item = e.getCurrentItem();
					if (!item.hasItemMeta()) {
						return;
					}
					if (item.getItemMeta().hasEnchant(CustomEnchants.SAFEGUARD)) {
						player.addPotionEffect(
								new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
						return;
					}
				}
			}
		}
	}

	// SAFEGUARD Inventory unequip
	@EventHandler
	public void safeguardInventoryUnequip(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getWhoClicked();
		if (e.getInventory().getType().equals(InventoryType.CRAFTING)) {
			if (e.getRawSlot() == 45) {
				if (e.getCurrentItem().getType().equals(Material.SHIELD)) {
					ItemStack item = e.getCurrentItem();
					if (!item.hasItemMeta()) {
						return;
					}
					if (item.getItemMeta().hasEnchant(CustomEnchants.SAFEGUARD)) {
						player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
						return;
					}
				} else {
					return;
				}
			}
		}
	}

	// SILENCE
	@EventHandler
	public void silence(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player attacker = (Player) e.getDamager();

			if (attacker.getInventory().getItemInMainHand() == null) {
				return;
			}
			ItemStack weapon = attacker.getInventory().getItemInMainHand();
			if (!weapon.hasItemMeta()) {
				return;
			}
			if (!attacker.getPlayer().getInventory().getItemInMainHand().getItemMeta()
					.hasEnchant(CustomEnchants.SILENCE)) {
				return;
			}
			if (silenceActive.get(attacker.getName()).equals(true)) {
				return;
			}

			Random rand = new Random();
			int upperBound = 6000;
			int procValue = rand.nextInt(upperBound);

			int eLvl = weapon.getItemMeta().getEnchantLevel(CustomEnchants.SILENCE);
			if (eLvl == 1 && procValue <= (120 + luckyValue.get(attacker.getName()))) {
				if (e.getEntity() instanceof Player) {
					Player hit = (Player) e.getEntity();
					silenceActive.put(hit.getName(), true);
					hit.getWorld().playSound(hit.getLocation(), Sound.ITEM_FIRECHARGE_USE, 3, 0.6f);
					hit.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "- " + ChatColor.DARK_GRAY + ""
							+ ChatColor.BOLD + "Silenced" + ChatColor.GRAY + "" + ChatColor.BOLD + "- ");
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
						public void run() {
							silenceActive.put(hit.getName(), false);
						}
					}, 40);
				}
			} else if (eLvl == 2 && procValue <= (150 + luckyValue.get(attacker.getName()))) {
				if (e.getEntity() instanceof Player) {
					Player hit = (Player) e.getEntity();
					silenceActive.put(hit.getName(), true);
					hit.getWorld().playSound(hit.getLocation(), Sound.ITEM_FIRECHARGE_USE, 3, 0.6f);
					hit.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "- " + ChatColor.DARK_GRAY + ""
							+ ChatColor.BOLD + "Silenced" + ChatColor.GRAY + "" + ChatColor.BOLD + "- ");
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
						public void run() {
							silenceActive.put(hit.getName(), false);
						}
					}, 50);
				}
			} else if (eLvl == 3 && procValue <= (180 + luckyValue.get(attacker.getName()))) {
				if (e.getEntity() instanceof Player) {
					Player hit = (Player) e.getEntity();
					silenceActive.put(hit.getName(), true);
					hit.getWorld().playSound(hit.getLocation(), Sound.ITEM_FIRECHARGE_USE, 3, 0.6f);
					hit.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "- " + ChatColor.DARK_GRAY + ""
							+ ChatColor.BOLD + "Silenced" + ChatColor.GRAY + "" + ChatColor.BOLD + "- ");
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
						public void run() {
							silenceActive.put(hit.getName(), false);
						}
					}, 60);
				}
			} else if (eLvl == 4 && procValue <= (210 + luckyValue.get(attacker.getName()))) {
				if (e.getEntity() instanceof Player) {
					Player hit = (Player) e.getEntity();
					silenceActive.put(hit.getName(), true);
					hit.getWorld().playSound(hit.getLocation(), Sound.ITEM_FIRECHARGE_USE, 3, 0.6f);
					hit.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "- " + ChatColor.DARK_GRAY + ""
							+ ChatColor.BOLD + "Silenced" + ChatColor.GRAY + "" + ChatColor.BOLD + "- ");
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
						public void run() {
							silenceActive.put(hit.getName(), false);
						}
					}, 70);
				}
			} else if (eLvl == 5 && procValue <= (240 + luckyValue.get(attacker.getName()))) {
				if (e.getEntity() instanceof Player) {
					Player hit = (Player) e.getEntity();
					silenceActive.put(hit.getName(), true);
					hit.getWorld().playSound(hit.getLocation(), Sound.ITEM_FIRECHARGE_USE, 3, 0.6f);
					hit.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "- " + ChatColor.DARK_GRAY + ""
							+ ChatColor.BOLD + "Silenced" + ChatColor.GRAY + "" + ChatColor.BOLD + "- ");
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
						public void run() {
							silenceActive.put(hit.getName(), false);
						}
					}, 80);
				}
			} else if (eLvl == 6 && procValue <= (270 + luckyValue.get(attacker.getName()))) {
				if (e.getEntity() instanceof Player) {
					Player hit = (Player) e.getEntity();
					silenceActive.put(hit.getName(), true);
					hit.getWorld().playSound(hit.getLocation(), Sound.ITEM_FIRECHARGE_USE, 3, 0.6f);
					hit.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "- " + ChatColor.DARK_GRAY + ""
							+ ChatColor.BOLD + "Silenced" + ChatColor.GRAY + "" + ChatColor.BOLD + "- ");
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
						public void run() {
							silenceActive.put(hit.getName(), false);
						}
					}, 90);
				}
			} else if (eLvl == 7 && procValue <= (300 + luckyValue.get(attacker.getName()))) {
				if (e.getEntity() instanceof Player) {
					Player hit = (Player) e.getEntity();
					silenceActive.put(hit.getName(), true);
					hit.getWorld().playSound(hit.getLocation(), Sound.ITEM_FIRECHARGE_USE, 3, 0.6f);
					hit.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "- " + ChatColor.DARK_GRAY + ""
							+ ChatColor.BOLD + "Silenced" + ChatColor.GRAY + "" + ChatColor.BOLD + "- ");
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
						public void run() {
							silenceActive.put(hit.getName(), false);
						}
					}, 100);
				}
			}
		}
	}

	// Silence Death Event
	@EventHandler
	public void resetSilenceDeath(PlayerDeathEvent e) {
		Player player = e.getEntity();
		silenceActive.put(player.getName(), false);
	}

	// Silence Join Event
	@EventHandler
	public void resetSilencejoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		silenceActive.put(player.getName(), false);
	}

	// STALWART
	@EventHandler
	public void stalwartEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() == null) {
			return;
		}
		ItemStack item = e.getItemStack();
		if (!item.hasItemMeta()) {
			return;
		}
		if (!item.getItemMeta().hasEnchant(CustomEnchants.STALWART)) {
			return;
		}

		int eLvl = item.getItemMeta().getEnchantLevel(CustomEnchants.STALWART);
		if (eLvl == 1) {
			player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(1);
			player.sendTitle(ChatColor.BLUE + "" + ChatColor.BOLD + "Armor Toughness " + ChatColor.AQUA + ""
					+ ChatColor.BOLD + "+1", "", 3, 25, 3);
		} 
		else if (eLvl == 2) {
			player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(2);
			player.sendTitle(ChatColor.BLUE + "" + ChatColor.BOLD + "Armor Toughness " + ChatColor.AQUA + ""
					+ ChatColor.BOLD + "+2", "", 3, 25, 3);
		} 
		else if (eLvl == 3) {
			player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(3);
			player.sendTitle(ChatColor.BLUE + "" + ChatColor.BOLD + "Armor Toughness " + ChatColor.AQUA + ""
					+ ChatColor.BOLD + "+3", "", 3, 25, 3);
		}
	}

	@EventHandler
	public void stalwartUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() == null) {
			return;
		}
		ItemStack playerArmor = e.getItemStack();
		if (!playerArmor.hasItemMeta()) {
			return;
		}
		if (!playerArmor.getItemMeta().hasEnchant(CustomEnchants.STALWART)) {
			return;
		}
		player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0);
	}

	// STOCKPILE
	@EventHandler
	public void stockpileEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() != null) {
			ItemStack item = e.getItemStack();
			if (!item.hasItemMeta()) {
				return;
			}
			if (!item.getItemMeta().hasEnchant(CustomEnchants.STOCKPILE)) {
				return;
			}

			if (player.hasPotionEffect(PotionEffectType.HEALTH_BOOST)) {
				int currentLevel = player.getPotionEffect(PotionEffectType.HEALTH_BOOST).getAmplifier();
				int eLvl = item.getItemMeta().getEnchantLevel(CustomEnchants.STOCKPILE);
				if (eLvl == 1) {
					player.addPotionEffect(
							new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, currentLevel + 1));
				} else if (eLvl == 2) {
					player.addPotionEffect(
							new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, currentLevel + 2));
				} else if (eLvl == 3) {
					player.addPotionEffect(
							new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, currentLevel + 3));
				}
			} else {
				int eLvl = item.getItemMeta().getEnchantLevel(CustomEnchants.STOCKPILE);
				if (eLvl == 1) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 0));
				} else if (eLvl == 2) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 1));
				} else if (eLvl == 3) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 2));
				}
			}
		}
	}

	@EventHandler
	public void stockpileUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		if (e.getItemStack() == null) {
			return;
		}
		ItemStack playerArmor = e.getItemStack();
		if (!playerArmor.hasItemMeta()) {
			return;
		}
		if (!playerArmor.getItemMeta().hasEnchant(CustomEnchants.STOCKPILE)) {
			return;
		}
		player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
	}

	// VOIDWALKER
	@EventHandler
	public void voidwalker(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity();
		if (player.getInventory().getHelmet() == null) {
			return;
		}
		ItemStack helmet = player.getInventory().getHelmet();
		if (!helmet.hasItemMeta()) {
			return;
		}
		if (!helmet.getItemMeta().hasEnchant(CustomEnchants.VOIDWALKER)) {
			return;
		}
		if (silenceActive.get(player.getName()).equals(true)) {
			return;
		}
		if (e.getDamager() instanceof Enderman || e.getDamager() instanceof EnderCrystal
				|| e.getDamager() instanceof EnderDragon || e.getDamager() instanceof Endermite
				|| e.getDamager() instanceof EnderPearl) {
			int eLvl = helmet.getItemMeta().getEnchantLevel(CustomEnchants.VOIDWALKER);
			if (eLvl == 1) {
				e.setDamage(e.getDamage() / 1.05);
			} else if (eLvl == 2) {
				e.setDamage(e.getDamage() / 1.075);
			} else if (eLvl == 3) {
				e.setDamage(e.getDamage() / 1.1);
			} else if (eLvl == 4) {
				e.setDamage(e.getDamage() / 1.125);
			} else if (eLvl == 5) {
				e.setDamage(e.getDamage() / 1.15);
			}
		}

		else if (e.getDamager() instanceof EnderCrystal) {
			int eLvl = helmet.getItemMeta().getEnchantLevel(CustomEnchants.VOIDWALKER);
			if (eLvl == 1) {
				e.setDamage(e.getDamage() / 1.5);
			} else if (eLvl == 2) {
				e.setDamage(e.getDamage() / 2);
			} else if (eLvl == 3) {
				e.setDamage(e.getDamage() / 2.5);
			} else if (eLvl == 4) {
				e.setDamage(e.getDamage() / 3);
			} else if (eLvl == 5) {
				e.setDamage(e.getDamage() / 4);
			}
		}
	}

	// WHIRLPOOL
	@EventHandler
	public void whirlpool(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player hitPlayer = (Player) e.getEntity();
			if (hitPlayer.getInventory().getLeggings() == null) {
				return;
			}
			if (!hitPlayer.getInventory().getLeggings().hasItemMeta()) {
				return;
			}
			if (!hitPlayer.getInventory().getLeggings().getItemMeta().hasEnchant(CustomEnchants.WHIRLPOOL)) {
				return;
			}
			if (silenceActive.get(hitPlayer.getName()).equals(true)) {
				return;
			}
			if (!(e.getDamager() instanceof LivingEntity)) {
				return;
			}
			LivingEntity damager = (LivingEntity) e.getDamager();

			Random rand = new Random();
			int upperBound = 4000;
			int procValue = rand.nextInt(upperBound);

			if (procValue <= (100 + luckyValue.get(hitPlayer.getName()))) {
				Location whirlpoolLocation = damager.getLocation();
				damager.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Caught in a whirlpool!");
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						for (double phi = 0; phi <= Math.PI; phi += Math.PI / 15) {
							for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 30) {
								double r = 1.25;
								double x = r * Math.cos(theta) * Math.sin(phi);
								double y = r * Math.cos(phi) + 1.25;
								double z = r * Math.sin(theta) * Math.sin(phi);

								Location location = whirlpoolLocation;
								location.add(x, y, z);
								damager.getWorld().spawnParticle(Particle.WATER_SPLASH, location, 1, 0F, 0F, 0F, 0.1);
								location.subtract(x, y, z);
							}
						}
						damager.damage(5);
						damager.getWorld().playSound(damager.getLocation(), Sound.AMBIENT_UNDERWATER_ENTER, 0.25f,
								0.75f);
						Vector direction = damager.getLocation().toVector().subtract(whirlpoolLocation.toVector())
								.normalize().multiply(-0.5);
						damager.setVelocity(direction);
					}
				}, 0);

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						for (double phi = 0; phi <= Math.PI; phi += Math.PI / 15) {
							for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 30) {
								double r = 1.25;
								double x = r * Math.cos(theta) * Math.sin(phi);
								double y = r * Math.cos(phi) + 1.25;
								double z = r * Math.sin(theta) * Math.sin(phi);

								Location location = whirlpoolLocation;
								location.add(x, y, z);
								damager.getWorld().spawnParticle(Particle.WATER_SPLASH, location, 1, 0F, 0F, 0F, 0.1);
								location.subtract(x, y, z);
							}
						}
						damager.damage(5);
						damager.getWorld().playSound(damager.getLocation(), Sound.AMBIENT_UNDERWATER_ENTER, 0.25f,
								0.75f);
						Vector direction = damager.getLocation().toVector().subtract(whirlpoolLocation.toVector())
								.normalize().multiply(-0.5);
						damager.setVelocity(direction);
					}
				}, 20);

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						for (double phi = 0; phi <= Math.PI; phi += Math.PI / 15) {
							for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 30) {
								double r = 1.25;
								double x = r * Math.cos(theta) * Math.sin(phi);
								double y = r * Math.cos(phi) + 1.25;
								double z = r * Math.sin(theta) * Math.sin(phi);

								Location location = whirlpoolLocation;
								location.add(x, y, z);
								damager.getWorld().spawnParticle(Particle.WATER_SPLASH, location, 1, 0F, 0F, 0F, 0.1);
								location.subtract(x, y, z);
							}
						}
						damager.damage(5);
						damager.getWorld().playSound(damager.getLocation(), Sound.AMBIENT_UNDERWATER_ENTER, 0.25f,
								0.75f);
						Vector direction = damager.getLocation().toVector().subtract(whirlpoolLocation.toVector())
								.normalize().multiply(-0.5);
						damager.setVelocity(direction);
					}
				}, 40);

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						for (double phi = 0; phi <= Math.PI; phi += Math.PI / 15) {
							for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 30) {
								double r = 1.25;
								double x = r * Math.cos(theta) * Math.sin(phi);
								double y = r * Math.cos(phi) + 1.25;
								double z = r * Math.sin(theta) * Math.sin(phi);

								Location location = whirlpoolLocation;
								location.add(x, y, z);
								damager.getWorld().spawnParticle(Particle.WATER_SPLASH, location, 1, 0F, 0F, 0F, 0.1);
								location.subtract(x, y, z);
							}
						}
						damager.damage(5);
						damager.getWorld().playSound(damager.getLocation(), Sound.AMBIENT_UNDERWATER_ENTER, 0.25f,
								0.75f);
						Vector direction = damager.getLocation().toVector().subtract(whirlpoolLocation.toVector())
								.normalize().multiply(-0.5);
						damager.setVelocity(direction);
					}
				}, 60);
			}
		}
	}
	
	
	//BLINK
	@SuppressWarnings("deprecation")
	@EventHandler
	public void blinkFloat(PlayerToggleSneakEvent e) {
		Player player = e.getPlayer();
		if (hyperActive.get(player.getName()) != "BLINK") {
			return;
		}
		if (player.getInventory().getChestplate() == null) {
			return;
		}
		ItemStack boots = player.getInventory().getBoots();
		if (!boots.hasItemMeta()) {
			return;
		}
		if (!boots.getItemMeta().hasEnchant(CustomEnchants.BLINK)) {
			return;
		}
		if (player.isOnGround()) {
			return;
		}
		
		blinkFloatCounter.put(player.getName(), 0);
		
		new BukkitRunnable() {
			public void run() {
				if (player.isOnGround()) {
					blinkFloatCounter.put(player.getName(), 0);
					this.cancel();
				}
				if (blinkFloatCounter.get(player.getName()) == 100) {
					blinkFloatCounter.put(player.getName(), 0);
					this.cancel();
				}
				if (player.isSneaking()) {
					Location l = player.getLocation();
					player.setVelocity(l.getDirection().multiply(0.05).setY(-0.0000001));
					if (blinkFloatCounter.get(player.getName()) % 5 == 0) {
						player.getWorld().spawnParticle(Particle.CLOUD, l.getX(), l.getY(), l.getZ(), 1, 0.5, 0.35, 0.5, 0.01);	
					}
				}
				else {
					blinkFloatCounter.put(player.getName(), 0);
					this.cancel();
				}
				blinkFloatCounter.put(player.getName(), blinkFloatCounter.get(player.getName() + 1));
			}
		}.runTaskTimer(this, 0, 1);
	}
	@EventHandler
	public void blinkDodge(PlayerToggleFlightEvent e) {
		if (e.getPlayer().getGameMode() == GameMode.CREATIVE || e.getPlayer().getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		Player player = e.getPlayer();
		if (blinkCharges.get(player.getName()) == 0) {
			e.setCancelled(true);
			return;
		}
		if (hyperActive.get(player.getName()) != "BLINK") {
			return;
		}
		if (player.getInventory().getChestplate() == null) {
			return;
		}
		ItemStack boots = player.getInventory().getBoots();
		if (!boots.hasItemMeta()) {
			return;
		}
		if (!boots.getItemMeta().hasEnchant(CustomEnchants.BLINK)) {
			return;
		}
		
		e.setCancelled(true);
		Location l = player.getLocation();
		player.setVelocity(l.getDirection().multiply(1.175).setY(0.15));
		player.getWorld().spawnParticle(Particle.CLOUD, l.getX(), l.getY(), l.getZ(), 6, 0.5, 0.35, 0.5, 0.01);
		player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.5f, 1.5f);
		blinkCharges.put(player.getName(), blinkCharges.get(player.getName()) - 1);
	}
	public void blinkGainCharges() {
		try {
			new BukkitRunnable() {
				public void run() {
					ArrayList<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
					for (Player player : playerList) {
						if (hyperActive.get(player.getName()) != "BLINK") {
							return;
						}
						if (player.getInventory().getBoots() == null) {
							return;
						}
						ItemStack boots = player.getInventory().getBoots();
						if (!boots.hasItemMeta()) {
							return;
						}
						if (!boots.getItemMeta().hasEnchant(CustomEnchants.BLINK)) {
							return;
						}
						int currentTime = (int) (System.currentTimeMillis() / 1000);
						if (currentTime >= blinkLastChargeGain.get(player.getName())) {
							if (blinkCharges.get(player.getName()) == 0) {
								blinkCharges.put(player.getName(), 1);
								player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Blink " + ChatColor.DARK_PURPLE + "charges: " + ChatColor.LIGHT_PURPLE + "1");
								blinkLastChargeGain.put(player.getName(), (System.currentTimeMillis() / 1000) + 20);
							}
							else if (blinkCharges.get(player.getName()) == 1) {
								blinkCharges.put(player.getName(), 2);
								player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Blink " + ChatColor.DARK_PURPLE + "charges: " + ChatColor.LIGHT_PURPLE + "2");
								blinkLastChargeGain.put(player.getName(), (System.currentTimeMillis() / 1000) + 20);
							}
							else if (blinkCharges.get(player.getName()) == 2) {
								blinkCharges.put(player.getName(), 3);
								player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Blink " + ChatColor.DARK_PURPLE + "charges: " + ChatColor.LIGHT_PURPLE + "3");
								blinkLastChargeGain.put(player.getName(), (System.currentTimeMillis() / 1000) + 20);
							}
						}
					}
				}
			}.runTaskTimer(this, 0, 20);
		}
		catch (Exception e) {
		}
	}
	@EventHandler
	public void blinkEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		if (hyperActive.get(player.getName()) != "BLINK") {
			return;
		}
		if (e.getItemStack() != null) {
			ItemStack item = e.getItemStack();
			if (!item.hasItemMeta()) {
				return;
			}
			if (!item.getItemMeta().hasEnchant(CustomEnchants.BLINK)) {
				return;
			}
			
			player.setAllowFlight(true);
			blinkCharges.put(player.getName(), 0);
			blinkLastChargeGain.put(player.getName(), (System.currentTimeMillis() / 1000) + 20);
		}
	}
	@EventHandler
	public void blinkUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		if (hyperActive.get(player.getName()) != "BLINK") {
			return;
		}
		if (e.getItemStack() == null) {
			return;
		}
		ItemStack playerArmor = e.getItemStack();
		if (!playerArmor.hasItemMeta()) {
			return;
		}
		if (!playerArmor.getItemMeta().hasEnchant(CustomEnchants.BLINK)) {
			return;
		}

		player.setAllowFlight(false);
	}
	@EventHandler
	public void blinkJoinVariables(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		player.setAllowFlight(false);
		blinkCharges.put(player.getName(), 0);
		blinkLastChargeGain.put(player.getName(), (System.currentTimeMillis() / 1000) + 20);
		blinkFloatCounter.put(player.getName(), 0);
	}
	
	
	//OVERDRIVE
	@EventHandler
	public void overdriveLongshot(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Projectile)) {
			return;
		}
		Projectile projectile = (Projectile) e.getDamager();
		if (!(projectile.getShooter() instanceof Player)) {
			return;
		}
		Player player = (Player) projectile.getShooter();
		if (hyperActive.get(player.getName()) != "OVERDRIVE") {
			return;
		}
		if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta() &&
			player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.OVERDRIVE)) {
			Entity entity = e.getEntity();
			if (!(entity instanceof LivingEntity)) {
				return;
			}
			LivingEntity hitEntity = (LivingEntity) e.getEntity();
			
			double distance = player.getLocation().distance(hitEntity.getLocation());
			double multiplier = 1 + (distance / 100);
			e.setDamage(e.getDamage() * multiplier);
		}
		else if (player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta() &&
				 player.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.OVERDRIVE)) {
			Entity entity = e.getEntity();
			if (!(entity instanceof LivingEntity)) {
				return;
			}
			LivingEntity hitEntity = (LivingEntity) e.getEntity();
			
			double distance = player.getLocation().distance(hitEntity.getLocation());
			double multiplier = 1 + (distance / 100);
			e.setDamage(e.getDamage() * multiplier);
		}
	}
	@EventHandler
	public void overdriveAbilityChargeMainHand(PlayerToggleSneakEvent e) {
		Player player = e.getPlayer();
		if (hyperActive.get(player.getName()) != "OVERDRIVE") {
			return;
		}
		if (player.getInventory().getItemInMainHand() == null) {
			return;
		}
		ItemStack bow = player.getInventory().getItemInMainHand();
		if (!bow.hasItemMeta()) {
			return;
		}
		if (!bow.getItemMeta().hasEnchant(CustomEnchants.OVERDRIVE)) {
			return;
		}
		
		int currentTime = (int) (System.currentTimeMillis() / 1000);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				if (e.isSneaking()) {
					if (currentTime < overdriveCooldown.get(player.getName()) && player.isSneaking()) {
						int timeLeft = (int) (currentTime - overdriveCooldown.get(player.getName()));
						player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Overdrive " + ChatColor.RED + "is still on cooldown! (" + ChatColor.DARK_PURPLE +
						"" + ChatColor.BOLD + (-1 * timeLeft) + "s" + ChatColor.RED + ")");
						return;
					}
					else if (player.isSneaking()) {
						overdriveCooldown.put(player.getName(), (System.currentTimeMillis() / 1000) + 60);
						overdriveAnimation(player, player.getLocation());
					}
				}
				else {
					return;
				}
			}
		}, 20);
	}
	@EventHandler
	public void overdriveAbilityChargeOffHand(PlayerToggleSneakEvent e) {
		Player player = e.getPlayer();
		if (hyperActive.get(player.getName()) != "OVERDRIVE") {
			return;
		}
		if (player.getInventory().getItemInOffHand() == null) {
			return;
		}
		ItemStack bow = player.getInventory().getItemInOffHand();
		if (!bow.hasItemMeta()) {
			return;
		}
		if (!bow.getItemMeta().hasEnchant(CustomEnchants.OVERDRIVE)) {
			return;
		}
		
		int currentTime = (int) (System.currentTimeMillis() / 1000);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				if (e.isSneaking()) {
					if (currentTime < overdriveCooldown.get(player.getName()) && player.isSneaking()) {
						int timeLeft = (int) (currentTime - overdriveCooldown.get(player.getName()));
						player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Overdrive " + ChatColor.RED + "is still on cooldown! (" + ChatColor.DARK_PURPLE +
						"" + ChatColor.BOLD + (-1 * timeLeft) + "s" + ChatColor.RED + ")");
						return;
					}
					else if (player.isSneaking()) {
						overdriveCooldown.put(player.getName(), (System.currentTimeMillis() / 1000) + 60);
						overdriveAnimation(player, player.getLocation());
					}
				}
				else {
					return;
				}
			}
		}, 20);
	}
	public void overdriveAnimation(Player player, Location loc) {
		new BukkitRunnable() {
			public void run() {
				int radius = overdriveAnimationRadius.get(player.getName());
				if (radius >= 4) {
					overdriveAnimationRadius.put(player.getName(), 1);
					player.setVelocity(new Vector(0, 22, 0));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 200, 2));
					ArmorStand tempStand = (ArmorStand) player.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
					tempStand.setInvisible(true);
					for (Entity entity : tempStand.getNearbyEntities(4, 4, 4)) {
						if (entity instanceof LivingEntity) {
							LivingEntity lEntity = (LivingEntity) entity;
							if (lEntity.getName() != player.getName()) {
								lEntity.damage(5);
								lEntity.setVelocity(new Vector(0, 3, 0));
							}
						}
					}
					tempStand.remove();
					this.cancel();
				}
				
			    for (double angle = 0; angle < 2 * Math.PI; angle += Math.PI / 36) {
			        final double x = radius * Math.cos(angle);
			        final double z = radius * Math.sin(angle);
			 
			        loc.add(x, 0, z);
			        player.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, loc.getX(), loc.getY(), loc.getZ(), 5, 0.4, 0, 0.4, 0.02);
			        loc.subtract(x, 0, z);
			    }
				
			    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANCIENT_DEBRIS_STEP, 1, 1);
				overdriveAnimationRadius.put(player.getName(), overdriveAnimationRadius.get(player.getName()) + 1);
			}
		}.runTaskTimer(this, 0, 2);
	}
	@EventHandler
	public void overdriveVariableReset(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		overdriveCooldown.put(player.getName(), (System.currentTimeMillis() / 1000) + 60);
		overdriveAnimationRadius.put(player.getName(), 1);
		overdriveChargingBow.put(player.getName(), false);
		overdriveChargeCounter.put(player.getName(), 0);
		overdriveLastUse.put(player.getName(), (System.currentTimeMillis() / 1000) + 5);
	}
	
	
	//TITAN
	@EventHandler
	public void titanEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		if (hyperActive.get(player.getName()) != "TITAN") {
			return;
		}
		if (e.getItemStack() != null) {
			ItemStack item = e.getItemStack();
			if (!item.hasItemMeta()) {
				return;
			}
			if (!item.getItemMeta().hasEnchant(CustomEnchants.TITAN)) {
				return;
			}

			if (player.hasPotionEffect(PotionEffectType.HEALTH_BOOST)) {
				int currentLevel = player.getPotionEffect(PotionEffectType.HEALTH_BOOST).getAmplifier();
				currentLevel += 2;
				player.addPotionEffect(
						new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, currentLevel));
			} 
			else {
				player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 1));
			}
			
			titanCooldown.put(player.getName(), (System.currentTimeMillis() / 1000) + 240);
		}
	}

	@EventHandler
	public void titanUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		if (hyperActive.get(player.getName()) != "TITAN") {
			return;
		}
		if (e.getItemStack() == null) {
			return;
		}
		ItemStack playerArmor = e.getItemStack();
		if (!playerArmor.hasItemMeta()) {
			return;
		}
		if (!playerArmor.getItemMeta().hasEnchant(CustomEnchants.TITAN)) {
			return;
		}

		player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
	}

	@EventHandler
	public void titanAbilityCharge(PlayerToggleSneakEvent e) {
		Player player = e.getPlayer();
		if (hyperActive.get(player.getName()) != "TITAN") {
			return;
		}
		if (player.getInventory().getChestplate() == null) {
			return;
		}
		ItemStack chestplate = player.getInventory().getChestplate();
		if (!chestplate.hasItemMeta()) {
			return;
		}
		if (!chestplate.getItemMeta().hasEnchant(CustomEnchants.TITAN)) {
			return;
		}
		
		int currentTime = (int) (System.currentTimeMillis() / 1000);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				if (e.isSneaking()) {
					if (currentTime < titanCooldown.get(player.getName()) && player.isSneaking()) {
						int timeLeft = (int) (currentTime - titanCooldown.get(player.getName()));
						player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Titan " + ChatColor.RED + "is still on cooldown! (" + ChatColor.DARK_PURPLE +
						"" + ChatColor.BOLD + (-1 * timeLeft) + "s" + ChatColor.RED + ")");
						return;
					}
					else if (player.isSneaking()) {
						titanCooldown.put(player.getName(), (System.currentTimeMillis() / 1000) + 240);
						titanAnimation(player);
					}
				}
				else {
					return;
				}
			}
		}, 20);
	}
	public void titanAnimation(Player player) {
		Location loc = player.getLocation();
		
		new BukkitRunnable() {
			public void run() {
				int radius = titanAnimationRadius.get(player.getName());
				if (radius >= 8) {
					titanAnimationRadius.put(player.getName(), 1);
					titanAlreadyAddedAbsorption.put(player.getName(), false);
					summonTitanBubble(player, loc);
					this.cancel();
				}
				
			    for (double angle = 0; angle < 2 * Math.PI; angle += Math.PI / 36) {
			        final double x = radius * Math.cos(angle);
			        final double z = radius * Math.sin(angle);
			 
			        loc.add(x, 0, z);
			        player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, loc.getX(), loc.getY(), loc.getZ(), 1, 0.2, 0, 0.2, 0.02);
			        loc.subtract(x, 0, z);
			    }
				
			    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANCIENT_DEBRIS_STEP, 1, 1);
				titanAnimationRadius.put(player.getName(), titanAnimationRadius.get(player.getName()) + 1);
			}
		}.runTaskTimer(this, 0, 2);
	}
	public void summonTitanBubble(Player player, Location loc) {
		new BukkitRunnable() {
			public void run() {
				int counter = titanBubbleAnimationCounter.get(player.getName());
				if (counter == 390 || player.isDead() || player.isBanned() || !player.isOnline()) {
					titanBubbleAnimationCounter.put(player.getName(), 0);
					this.cancel();
				}
				
				for (double phi = 0; phi <= Math.PI; phi += Math.PI / 25) {
					for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 25) {
						double r = 8;
						double x = r * Math.cos(theta) * Math.sin(phi);
						double y = r * Math.cos(phi) + 1.25;
						double z = r * Math.sin(theta) * Math.sin(phi);

						loc.add(x, y, z);
						loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.fromBGR(255, 40, 160), 1));
						loc.subtract(x, y, z);
					}
				}
				if (player.getLocation().getX() >= loc.getX() - 8 && player.getLocation().getX() <= loc.getX() + 8) {
					if (player.getLocation().getY() >= loc.getY() - 8 && player.getLocation().getY() <= loc.getY() + 8) {
						if (player.getLocation().getZ() >= loc.getZ() - 8 && player.getLocation().getZ() <= loc.getZ() + 8) {
							ArmorStand tempStand = (ArmorStand) player.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
							tempStand.setInvisible(true);
							for (Entity entity : tempStand.getNearbyEntities(9, 9, 9)) {
								if (entity instanceof LivingEntity) {
									LivingEntity lEntity = (LivingEntity) entity;
									
									if (lEntity.getName() == player.getName()) {
										int currentAbsorptionLevel = -1;
										if (player.hasPotionEffect(PotionEffectType.ABSORPTION)) {
											currentAbsorptionLevel = player.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier();
										}
										if (titanAlreadyAddedAbsorption.get(player.getName()).equals(false)) {
											currentAbsorptionLevel += 3;
											player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 400, currentAbsorptionLevel));
											titanAlreadyAddedAbsorption.put(player.getName(), true);
										}
										
										player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 25, 1));
									}
									else {
										lEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0));	
									}
								}
							}
							tempStand.remove();
						}
					}
				}
				titanBubbleAnimationCounter.put(player.getName(), titanBubbleAnimationCounter.get(player.getName()) + 15);
				loc.getWorld().playSound(loc, Sound.BLOCK_BEACON_AMBIENT, 10f, 1f);
			}
		}.runTaskTimer(this, 0, 15);
	}
	@EventHandler
	public void titanVariableReset(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		titanReady.put(player.getName(), false);
		titanCooldown.put(player.getName(), (System.currentTimeMillis() / 1000) + 240);
		titanAnimationRadius.put(player.getName(), 1);
		titanBubbleAnimationCounter.put(player.getName(), 0);
		titanAlreadyAddedAbsorption.put(player.getName(), false);
	}

	// remove potion effects on leave
	@EventHandler
	public void removePotionEffectsQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
		player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
		player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
		player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
		player.removePotionEffect(PotionEffectType.JUMP);
		player.removePotionEffect(PotionEffectType.NIGHT_VISION);
		player.removePotionEffect(PotionEffectType.SPEED);
		player.removePotionEffect(PotionEffectType.WATER_BREATHING);
	}

	// remove potion effects on join
	@EventHandler
	public void removePotionEffectsJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
		player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
		player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
		player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
		player.removePotionEffect(PotionEffectType.JUMP);
		player.removePotionEffect(PotionEffectType.NIGHT_VISION);
		player.removePotionEffect(PotionEffectType.SPEED);
		player.removePotionEffect(PotionEffectType.WATER_BREATHING);
	}
}