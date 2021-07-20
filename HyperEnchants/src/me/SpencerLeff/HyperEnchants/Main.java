package me.SpencerLeff.HyperEnchants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {
	
	public static HashMap<String, Long> thwompTimer = new HashMap<String, Long>();
	public static HashMap<String, Double> getLastFuryDamage = new HashMap<String, Double>();
	public static HashMap<String, Double> currentFuryMultiplier = new HashMap<String, Double>();
	public static HashMap<String, Double> currentRageMultiplier = new HashMap<String, Double>();
	public static HashMap<String, Boolean> retributionActive = new HashMap<String, Boolean>();
	public static HashMap<String, Long> retributionCooldown = new HashMap<String, Long>();
	
	@Override
	public void onEnable() {
		//Register custom enchants
		BookOpening.register();
		
		//Register a custom enchant proc
		this.getServer().getPluginManager().registerEvents(this,  this);
		
		//Armor equip event
	    this.getServer().getPluginManager().registerEvents(new EventAnalyser(this), this);
		
		//Enchanter menu click event
		getServer().getPluginManager().registerEvents(new EnchanterMenuClickEvent(), this);
		
		//applyCustomEnchantments click event
		getServer().getPluginManager().registerEvents(new applyCustomEnchantments(), this);
		
		//Custom enchantment book opening event
		getServer().getPluginManager().registerEvents(new BookOpening(), this);
		
		//Check if item is renamed - if it has been, then reapply all the current enchants on the item
		getServer().getPluginManager().registerEvents(new RenameCheck(), this); 
	}
	
	@Override
	public void onDisable() {
		
	}
	
	//all commands for the plugin
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {		
		//Enchanter command
		if (label.equalsIgnoreCase("enchanter")) {
			if (!(sender instanceof Player)) {
				return true;
			}
			Player player = (Player) sender;
			
			Inventory gui = Bukkit.createInventory(player,  9, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Enchanter");
			
			//Fill first three and last three gui slots with grey stained class
			ItemStack fillerPane = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
			
			//Common enchantment pane
			ItemStack commonPane = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
			ItemMeta commonMeta = commonPane.getItemMeta();
			commonMeta.setDisplayName(ChatColor.WHITE + "Common Enchantment - 20 Levels");
			ArrayList<String> commonLore = new ArrayList<>();
			commonLore.add(ChatColor.RED + "Ensure you have placed any extra experience in a /xpbottle");
			commonMeta.setLore(commonLore);
			commonPane.setItemMeta(commonMeta);
			
			//Uncommon enchantment pane
			ItemStack rarePane = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
			ItemMeta rareMeta = rarePane.getItemMeta();
			rareMeta.setDisplayName(ChatColor.AQUA + "Rare Enchantment - 35 Levels");
			ArrayList<String> rareLore = new ArrayList<>();
			rareLore.add(ChatColor.RED + "Ensure you have placed any extra experience in a /xpbottle");
			rareMeta.setLore(rareLore);
			rarePane.setItemMeta(rareMeta);
			
			//Legendary enchantment pane
			ItemStack legendaryPane = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
			ItemMeta legendaryMeta = legendaryPane.getItemMeta();
			legendaryMeta.setDisplayName(ChatColor.GOLD + "Legendary Enchantment - 60 Levels");
			ArrayList<String> legendaryLore = new ArrayList<>();
			legendaryLore.add(ChatColor.RED + "Ensure you have placed any extra experience in a /xpbottle");
			legendaryMeta.setLore(legendaryLore);
			legendaryPane.setItemMeta(legendaryMeta);
			
			ItemStack[] enchanterPanes = {fillerPane, fillerPane, fillerPane, commonPane, rarePane, legendaryPane, fillerPane, fillerPane, fillerPane};
			gui.setContents(enchanterPanes);
			player.openInventory(gui);
		}
		
		
		//Bless command
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
			
			player.sendMessage(ChatColor.WHITE + "You have been " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Blessed" + ChatColor.RESET + ChatColor.WHITE + "!");
		}
		
		return true;
	}
	
	
	//AQUATIC
	@EventHandler()
	public void aquaticEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		if (player.getInventory().getHelmet() != null) {
			if (!player.getPlayer().getInventory().getHelmet().getItemMeta().hasEnchant(BookOpening.AQUATIC)) {
				return;
			}
			List<String> aquaticLore = player.getInventory().getHelmet().getItemMeta().getLore();
			for (int i = 0; i < aquaticLore.size(); i++) {
				String loreLine = aquaticLore.get(i);
				if (loreLine.contains("aquatic")) {
					//add the aquatic enchant effects
					player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 0));
				}
			}
		}
	}
	@EventHandler
	public void aquaticUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		ItemStack playerArmor = e.getItemStack();
		List<String> aquaticLore = playerArmor.getItemMeta().getLore();
		for (int i = 0; i < aquaticLore.size(); i++) {
			String loreLine = aquaticLore.get(i);
			if (loreLine.contains("aquatic")) {
				player.removePotionEffect(PotionEffectType.WATER_BREATHING);
			}
		}
	}
	
	
	//BLESS
	@EventHandler
	public void blessed(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(BookOpening.BLESSED)) {
				return;
			}
			
			//use random number generator to choose whether or not to proc the enchant
			Random rand = new Random();
			int upperBound = 100;
			int procValue = rand.nextInt(upperBound);
			
			if (procValue <= 10) {	
				//remove the debuff(s) and send a chat message
				attackingPlayer.removePotionEffect(PotionEffectType.BLINDNESS);
				attackingPlayer.removePotionEffect(PotionEffectType.CONFUSION);
				attackingPlayer.removePotionEffect(PotionEffectType.HUNGER);
				attackingPlayer.removePotionEffect(PotionEffectType.POISON);
				attackingPlayer.removePotionEffect(PotionEffectType.SLOW);
				attackingPlayer.removePotionEffect(PotionEffectType.SLOW_DIGGING);
				attackingPlayer.removePotionEffect(PotionEffectType.WEAKNESS);
				attackingPlayer.removePotionEffect(PotionEffectType.WITHER);
				attackingPlayer.sendMessage(ChatColor.WHITE + "You have been " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Blessed" + ChatColor.RESET + ChatColor.WHITE + "!");
			}
		}
	}
	
	
	//CONFUSION
	@EventHandler()
	public void confusion(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player hitPlayer = (Player) e.getEntity();
			Player attackingPlayer = (Player) e.getDamager();
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(BookOpening.CONFUSION)) {
				return;
			}
			
			//find attacker weapon so i can grab the level from it
			ItemStack confusionWeapon = attackingPlayer.getInventory().getItemInMainHand();
			
			//use random number generator to choose whether or not to proc the enchant
			Random rand = new Random();
			int upperBound = 100;
			int procValue = rand.nextInt(upperBound);
			
			
			//find the level of confusion enchant
			List<String> confusionLore = confusionWeapon.getItemMeta().getLore();
			for (int i = 0; i < confusionLore.size(); i++) {
				String loreLine = confusionLore.get(i);
				if (loreLine.contains("confusion")) {
					String[] splitLoreLine = loreLine.split(" ");
					String eLvlString = splitLoreLine[1];
					int eLvl = Integer.parseInt(eLvlString);
					if (eLvl == 1 && procValue <= 5) {
						hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 110, 1));
						hitPlayer.sendMessage(ChatColor.WHITE + "You are becoming " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "confused" + ChatColor.RESET + "...");
					}
					else if (eLvl == 2 && procValue <= 8) {
						hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 130, 2));
						hitPlayer.sendMessage(ChatColor.WHITE + "You are becoming " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "confused" + ChatColor.RESET + "...");
					}
					else if (eLvl == 3 && procValue <= 11) {
						hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 3));
						hitPlayer.sendMessage(ChatColor.WHITE + "You are becoming " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "confused" + ChatColor.RESET + "...");
					}
				}
			}
		}
	}
	
	
	//GUILLOTINE
	@EventHandler()
	public void guillotine(PlayerDeathEvent e) {
		if (!e.getEntity().getKiller().getInventory().getItemInMainHand().getItemMeta().hasEnchant(BookOpening.GUILLOTINE)) {
			return;
		}
		ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
		skullMeta.setOwningPlayer(e.getEntity());
		ArrayList<String> skullLore = new ArrayList<>();
		skullLore.add(ChatColor.WHITE + "Killed by: " + ChatColor.DARK_RED + "" + ChatColor.BOLD + e.getEntity().getKiller().getName());
		skullMeta.setLore(skullLore);
		playerHead.setItemMeta(skullMeta);
		e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), playerHead);
	}
	
	
	//NOCTURNAL
	@EventHandler()
	public void nocturnalEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		if (player.getInventory().getHelmet() != null) {
			if (!player.getPlayer().getInventory().getHelmet().getItemMeta().hasEnchant(BookOpening.NOCTURNAL)) {
				return;
			}
			List<String> nocturnalLore = player.getInventory().getHelmet().getItemMeta().getLore();
			for (int i = 0; i < nocturnalLore.size(); i++) {
				String loreLine = nocturnalLore.get(i);
				if (loreLine.contains("nocturnal")) {
					//add the drunk enchant effects
					player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
				}
			}
		}
	}
	@EventHandler
	public void nocturnalUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		ItemStack playerArmor = e.getItemStack();
		List<String> nocturnalLore = playerArmor.getItemMeta().getLore();
		for (int i = 0; i < nocturnalLore.size(); i++) {
			String loreLine = nocturnalLore.get(i);
			if (loreLine.contains("nocturnal")) {
				player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			}
		}
	}
	
	
	//TELEPATHY
	@EventHandler()
	public void telepathy(BlockBreakEvent event) {
		if (!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(BookOpening.TELEPATHY)) {
			return;
		}
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (event.getPlayer().getInventory().firstEmpty() == -1) {
			return;
		}
		if (event.getBlock().getState() instanceof Container) {
			return;
		}
		
		event.setDropItems(false);
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());
		if (drops.isEmpty()) {
			return;
		}
		player.getInventory().addItem(drops.iterator().next());
	}
	
	
	//CLEAVE
	@EventHandler()
	public void cleave(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(BookOpening.CLEAVE)) {
				return;
			}
			
			//find attacker weapon so i can grab the level from it
			ItemStack cleaveWeapon = attackingPlayer.getInventory().getItemInMainHand();
			
			//find the level of confusion enchant
			List<String> cleaveLore = cleaveWeapon.getItemMeta().getLore();
			for (int i = 0; i < cleaveLore.size(); i++) {
				String loreLine = cleaveLore.get(i);
				if (loreLine.contains("cleave")) {
					String[] splitLoreLine = loreLine.split(" ");
					String eLvlString = splitLoreLine[1];
					int eLvl = Integer.parseInt(eLvlString);
					Random rand = new Random();
					int AOEdistance = 3;
					int damageValue = 1;

					if (eLvl == 1) {
						//use random number generator to generate the damage dealt in AOE
						int upperBoundDamage = 1;
						damageValue = rand.nextInt(upperBoundDamage);
						AOEdistance = 3;
						Player hitPlayer = (Player) e.getEntity();
						for (Entity nearbyEntity : attackingPlayer.getNearbyEntities(AOEdistance, AOEdistance, AOEdistance)) {
							if (nearbyEntity instanceof Player) {
								if (hitPlayer.getName() == nearbyEntity.getName()) {
									//do not damage the already hit player
								}
								else {
									((Damageable) nearbyEntity).damage(damageValue + 1);
								}
							}
						}
					}
					else if (eLvl == 2) {
						int upperBoundDamage = 1;
						damageValue = rand.nextInt(upperBoundDamage);
						AOEdistance = 3;
						Player hitPlayer = (Player) e.getEntity();
						for (Entity nearbyEntity : attackingPlayer.getNearbyEntities(AOEdistance, AOEdistance, AOEdistance)) {
							if (nearbyEntity instanceof Player) {
								if (hitPlayer.getName() == nearbyEntity.getName()) {
									//do not damage the already hit player
								}
								else {
									((Damageable) nearbyEntity).damage(damageValue + 1);
								}
							}
						}
					}
					else if (eLvl == 3) {
						int upperBoundDamage = 2;
						damageValue = rand.nextInt(upperBoundDamage);
						AOEdistance = 4;
						Player hitPlayer = (Player) e.getEntity();
						for (Entity nearbyEntity : attackingPlayer.getNearbyEntities(AOEdistance, AOEdistance, AOEdistance)) {
							if (nearbyEntity instanceof Player) {
								if (hitPlayer.getName() == nearbyEntity.getName()) {
									//do not damage the already hit player
								}
								else {
									((Damageable) nearbyEntity).damage(damageValue + 1);
								}
							}
						}
					}
					else if (eLvl == 4) {
						int upperBoundDamage = 3;
						damageValue = rand.nextInt(upperBoundDamage);
						AOEdistance = 4;
						Player hitPlayer = (Player) e.getEntity();
						for (Entity nearbyEntity : attackingPlayer.getNearbyEntities(AOEdistance, AOEdistance, AOEdistance)) {
							if (nearbyEntity instanceof Player) {
								if (hitPlayer.getName() == nearbyEntity.getName()) {
									//do not damage the already hit player
								}
								else {
									((Damageable) nearbyEntity).damage(damageValue + 1);
								}
							}
						}
					}
					else if (eLvl == 5) {
						int upperBoundDamage = 4;
						damageValue = rand.nextInt(upperBoundDamage);
						AOEdistance = 5;
						Player hitPlayer = (Player) e.getEntity();
						for (Entity nearbyEntity : attackingPlayer.getNearbyEntities(AOEdistance, AOEdistance, AOEdistance)) {
							if (nearbyEntity instanceof Player) {
								if (hitPlayer.getName() == nearbyEntity.getName()) {
									//do not damage the already hit player
								}
								else {
									((Damageable) nearbyEntity).damage(damageValue + 1);
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	//JUMP
	@EventHandler
	public void jumpEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		//ItemStack playerArmor = e.getItemStack();
		if (player.getInventory().getBoots() != null) {
			if (!player.getPlayer().getInventory().getBoots().getItemMeta().hasEnchant(BookOpening.JUMP)) {
				return;
			}
			//find the level of jump enchant
			List<String> jumpLore = player.getInventory().getBoots().getItemMeta().getLore();
			for (int i = 0; i < jumpLore.size(); i++) {
				String loreLine = jumpLore.get(i);
				if (loreLine.contains("jump")) {
					String[] splitLoreLine = loreLine.split(" ");
					String eLvlString = splitLoreLine[1];
					int eLvl = Integer.parseInt(eLvlString);
					//give jump boost based on enchantment level
					if (eLvl == 1) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 0));
					}
					else if (eLvl == 2) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1));
					}
					else if (eLvl == 3) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2));
					}
					else if (eLvl == 4) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 3));
					}
				}
			}
		}
	}
	@EventHandler
	public void jumpUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		ItemStack playerArmor = e.getItemStack();
		List<String> jumpLore = playerArmor.getItemMeta().getLore();
		for (int i = 0; i < jumpLore.size(); i++) {
			String loreLine = jumpLore.get(i);
			if (loreLine.contains("jump")) {
				player.removePotionEffect(PotionEffectType.JUMP);
			}
		}
	}
	
	
	//MAGNETIZE
	@EventHandler
	public void magnetize(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Monster && e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();
			Monster hitEntity = (Monster) e.getEntity();
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(BookOpening.MAGNETIZE)) {
				return;
			}
			
			//how much damage did the last hit do
			ItemStack magnetizeWeapon = attackingPlayer.getInventory().getItemInMainHand();
			
			//find the level of rage enchant
			List<String> magnetizeLore = magnetizeWeapon.getItemMeta().getLore();
			for (int i = 0; i < magnetizeLore.size(); i++) {
				String loreLine = magnetizeLore.get(i);
				if (loreLine.contains("magnetize")) {
					String[] splitLoreLine = loreLine.split(" ");
					String eLvlString = splitLoreLine[1];
					int eLvl = Integer.parseInt(eLvlString);
					if (eLvl == 1) {
						hitEntity.setVelocity(attackingPlayer.getLocation().getDirection().setY(0).normalize().multiply(0.5));
						//hitEntity.setGravity(false);
					}
					else if (eLvl == 2) {
						hitEntity.setVelocity(attackingPlayer.getLocation().getDirection().setY(0).normalize().multiply(0.33));
						//hitEntity.setGravity(false);
					}
					else if (eLvl == 3) {
						hitEntity.setVelocity(attackingPlayer.getLocation().getDirection().setY(0).normalize().multiply(0.25));
						//hitEntity.setGravity(false);
					}
				}
			}
		}
	}
	
	
	//OBSIDIANSHIELD
	@EventHandler
	public void obsidianShieldEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		//ItemStack playerArmor = e.getItemStack();
		if (player.getInventory().getLeggings() != null) {
			if (!player.getPlayer().getInventory().getLeggings().getItemMeta().hasEnchant(BookOpening.OBSIDIANSHIELD)) {
				return;
			}
			List<String> obsidianshieldLore = player.getInventory().getLeggings().getItemMeta().getLore();
			for (int i = 0; i < obsidianshieldLore.size(); i++) {
				String loreLine = obsidianshieldLore.get(i);
				if (loreLine.contains("obsidianshield")) {
					//add the drunk enchant effects
					player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
				}
			}
		}
	}
	@EventHandler
	public void obsidianShieldUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		ItemStack playerArmor = e.getItemStack();
		List<String> obsidianshieldLore = playerArmor.getItemMeta().getLore();
		for (int i = 0; i < obsidianshieldLore.size(); i++) {
			String loreLine = obsidianshieldLore.get(i);
			if (loreLine.contains("obsidianshield")) {
				player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
			}
		}
	}
	
	
	//RETRIBUTION
	@EventHandler
	public void retribution(PlayerToggleSneakEvent e) throws InterruptedException {
		Player player = e.getPlayer();
		ItemStack offHandItem = player.getInventory().getItemInOffHand();
		if (offHandItem.getItemMeta().hasEnchant(BookOpening.RETRIBUTION)) {
			if (e.isSneaking()) {
				//off cooldown
				int currentTime = (int)(System.currentTimeMillis() / 1000);
				if (currentTime >= retributionCooldown.get(player.getName())) {
					//if not already active, make it active
					if (!retributionActive.get(player.getName())) {
						retributionActive.put(player.getName(), true);
						retributionCooldownTimerSet(player, 10);
						player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Retribution " + ChatColor.RESET + "" + ChatColor.DARK_PURPLE + "active!");
						//particle effect
                        for(double phi=0; phi<=Math.PI; phi+=Math.PI/15) {
                            for(double theta=0; theta<=2*Math.PI; theta+=Math.PI/30) {
                              double r = 1.85;
                              double x = r*Math.cos(theta)*Math.sin(phi);
                              double y = r*Math.cos(phi) + 1.25;
                              double z = r*Math.sin(theta)*Math.sin(phi);
                              
                              Location location = player.getLocation();
                              location.add(x,y,z);
                              player.getWorld().spawnParticle(Particle.CLOUD, location, 1, 0F, 0F, 0F, 0.1);
                              location.subtract(x, y, z);
                          }
                        }
					}
					//if active already, turn it off
					else if (retributionActive.get(player.getName())) {
						retributionActive.put(player.getName(), false);
						retributionCooldownTimerSet(player, 10);
						player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Retribution " + ChatColor.RESET + "" + ChatColor.WHITE + "inactive.");
					}
				}
				//on cooldown
				else {
					int timeLeft = (int) (currentTime - retributionCooldown.get(player.getName()));
					if (e.isSneaking()) {
						player.sendMessage(ChatColor.DARK_RED + "Retribution is still on cooldown for " + ChatColor.BOLD + (-1 * timeLeft) + "" + ChatColor.RESET + "" + ChatColor.DARK_RED + " seconds.");
					}
				}
			}
		}
	}
	//RETRIBUTION EFFECTS
	@EventHandler
	public void retributionEffects(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player hitPlayer = (Player) e.getEntity();
			Player attackingPlayer = (Player) e.getDamager();
			if (!hitPlayer.getPlayer().getInventory().getItemInOffHand().getItemMeta().hasEnchant(BookOpening.RETRIBUTION)) {
				return;
			}
			
			//find hit player's shield so i can grab the level from it
			ItemStack retributionShield = hitPlayer.getInventory().getItemInOffHand();
						
			if (retributionActive.get(hitPlayer.getName())) {
				if (hitPlayer.isBlocking()) {
					retributionActive.put(hitPlayer.getName(), false);
					retributionCooldownTimerSet(hitPlayer, 90);
					//find the level of retribution enchant
					List<String> retributionLore = retributionShield.getItemMeta().getLore();
					for (int i = 0; i < retributionLore.size(); i++) {
						String loreLine = retributionLore.get(i);
						if (loreLine.contains("retribution")) {
							String[] splitLoreLine = loreLine.split(" ");
							String eLvlString = splitLoreLine[1];
							int eLvl = Integer.parseInt(eLvlString);
							if (eLvl == 1) {
								hitPlayer.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Retribution brings vengeance upon the enemy...");
								attackingPlayer.sendMessage(ChatColor.WHITE + "Struck by " + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Retribution!");
								hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0));
								hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 0));
								attackingPlayer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 0));
								attackingPlayer.setVelocity(new Vector(0, 0.8, 0));
							}
							else if (eLvl == 2) {
								hitPlayer.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Retribution brings vengeance upon the enemy...");
								attackingPlayer.sendMessage(ChatColor.WHITE + "Struck by " + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Retribution!");
								hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 125, 1));
								hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 125, 1));
								attackingPlayer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 125, 0));
								attackingPlayer.setVelocity(new Vector(0, 0.9, 0));
							}
							else if (eLvl == 3) {
								hitPlayer.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Retribution brings vengeance upon the enemy...");
								attackingPlayer.sendMessage(ChatColor.WHITE + "Struck by " + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Retribution!");
								hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 150, 2));
								hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 150, 2));
								attackingPlayer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 150, 1));
								attackingPlayer.setVelocity(new Vector(0, 1, 0));
							}
						}
					}
				}
			}
		}
	}
	//RETRIBUTION_RESET_ON_SERVER_JOIN
	@EventHandler
	public void retributionOnServerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		retributionActive.put(player.getName(), false);
		retributionCooldownTimerSet(player, 90);
	}
	//RETRIBUTION_COOLDOWN_TIMER_SET
	public void retributionCooldownTimerSet(Player player, int cooldownAdded) {
		retributionCooldown.put(player.getName(), (System.currentTimeMillis() / 1000) + cooldownAdded);
	}
	
	
	//SKYROCKET
	@EventHandler
	public void skyrocket(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			//does the player's boots have skyrocket?
			if (player.getInventory().getBoots().getItemMeta().hasEnchant(BookOpening.SKYROCKET)) {
				//use random number generator to choose whether or not to proc the enchant
				Random rand = new Random();
				int upperBound = 100;
				int procValue = rand.nextInt(upperBound);
				
				//find the level of skyrocket enchant
				List<String> skyrocketLore = player.getInventory().getBoots().getItemMeta().getLore();
				for (int i = 0; i < skyrocketLore.size(); i++) {
					String loreLine = skyrocketLore.get(i);
					if (loreLine.contains("skyrocket")) {
						String[] splitLoreLine = loreLine.split(" ");
						String eLvlString = splitLoreLine[1];
						int eLvl = Integer.parseInt(eLvlString);
						
						//based on the level and proc chance, proc skyrocket for the player
						if (eLvl == 1 && player.getHealth() <= 2 && procValue <= 12) {
							player.setVelocity(new Vector(0, 18, 0));
							player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 130, 2));
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 205, 0));
							player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Skyrocket" + ChatColor.RESET + " blasted you into the air!");
						}
						else if (eLvl == 2 && player.getHealth() <= 2 && procValue <= 16) {
							player.setVelocity(new Vector(0, 20, 0));
							player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 180, 2));
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 210, 0));
							player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Skyrocket" + ChatColor.RESET + " blasted you into the air!");
						}
						else if (eLvl == 3 && player.getHealth() <= 3 && procValue <= 20) {
							player.setVelocity(new Vector(0, 22, 0));
							player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 215, 3));
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 215, 0));
							player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Skyrocket" + ChatColor.RESET + " blasted you into the air!");
						}
					}
				}
			}
		}
	}
	
	
	//THWOMP
	@EventHandler()
	public void thwomp(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();
			Entity hitEntity = e.getEntity();
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(BookOpening.THWOMP)) {
				return;
			}
			
			//find attacker weapon so i can grab the level from it
			ItemStack thwompWeapon = attackingPlayer.getInventory().getItemInMainHand();
			
			//use random number generator to choose whether or not to proc the enchant
			Random rand = new Random();
			int upperBound = 100;
			int procValue = rand.nextInt(upperBound);
			
			//find the level of confusion enchant
			List<String> thwompLore = thwompWeapon.getItemMeta().getLore();
			for (int i = 0; i < thwompLore.size(); i++) {
				String loreLine = thwompLore.get(i);
				if (loreLine.contains("thwomp")) {
					String[] splitLoreLine = loreLine.split(" ");
					String eLvlString = splitLoreLine[1];
					int eLvl = Integer.parseInt(eLvlString);
					if (eLvl == 1 && procValue <= 3) {
						
					}
					else if (eLvl == 2 && procValue <= 4) {
						
					}
					else if (eLvl == 3 && procValue <= 100) {
						hitEntity.setGravity(false);
						hitEntity.setVelocity(new Vector(0, 1, 0));
						attackingPlayer.sendMessage(ChatColor.RED + "Enemy was " + ChatColor.BOLD + "THWOMPED");
						retributionCooldown.put(attackingPlayer.getName(), System.currentTimeMillis() / 1000);

						//wait 2 seconds or however long here - bukkit runnable task scheduler
						
						hitEntity.setGravity(true);
						hitEntity.setVelocity(new Vector(0, 0, 0));
						Location entityLocation = hitEntity.getLocation();
						double currentX = entityLocation.getX();
						double currentY = entityLocation.getY();
						double currentZ = entityLocation.getZ();
						double newY = currentY - 1;
						Location blockLocation = new Location(hitEntity.getWorld(), currentX, newY, currentZ);
						blockLocation.getBlock().setType(Material.BARRIER);
						
						
						//after damage
						blockLocation.getBlock().setType(Material.AIR);
					}
				}
			}
		}
	}
	
	
	
	//AGILITY
	@EventHandler
	public void agilityEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		//ItemStack playerArmor = e.getItemStack();
		if (player.getInventory().getBoots() != null) {
			if (!player.getPlayer().getInventory().getBoots().getItemMeta().hasEnchant(BookOpening.AGILITY)) {
				return;
			}
			//find the level of agility enchant
			List<String> agilityLore = player.getInventory().getBoots().getItemMeta().getLore();
			for (int i = 0; i < agilityLore.size(); i++) {
				String loreLine = agilityLore.get(i);
				if (loreLine.contains("agility")) {
					String[] splitLoreLine = loreLine.split(" ");
					String eLvlString = splitLoreLine[1];
					int eLvl = Integer.parseInt(eLvlString);
					//give speed boost based on enchantment level
					if (eLvl == 1) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
					}
					else if (eLvl == 2) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
					}
				}
			}
		}
	}
	@EventHandler
	public void agilityUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		ItemStack playerArmor = e.getItemStack();
		List<String> agilityLore = playerArmor.getItemMeta().getLore();
		for (int i = 0; i < agilityLore.size(); i++) {
			String loreLine = agilityLore.get(i);
			if (loreLine.contains("agility")) {
				player.removePotionEffect(PotionEffectType.SPEED);
			}
		}
	}
	
	
	//DRUNK
	@EventHandler
	public void drunkEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		//ItemStack playerArmor = e.getItemStack();
		if (player.getInventory().getHelmet() != null) {
			if (!player.getPlayer().getInventory().getHelmet().getItemMeta().hasEnchant(BookOpening.DRUNK)) {
				return;
			}
			List<String> drunkLore = player.getInventory().getHelmet().getItemMeta().getLore();
			for (int i = 0; i < drunkLore.size(); i++) {
				String loreLine = drunkLore.get(i);
				if (loreLine.contains("drunk")) {
					//add the drunk enchant effects
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1));
				}
			}
		}
	}
	@EventHandler
	public void drunkUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		ItemStack playerArmor = e.getItemStack();
		List<String> drunkLore = playerArmor.getItemMeta().getLore();
		for (int i = 0; i < drunkLore.size(); i++) {
			String loreLine = drunkLore.get(i);
			if (loreLine.contains("drunk")) {
				player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
				player.removePotionEffect(PotionEffectType.SLOW);
			}
		}
	}
	
	
	//RAGE
	@EventHandler
	public void fury(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Monster && e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();
			Monster hitEntity = (Monster) e.getEntity();
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(BookOpening.FURY)) {
				return;
			}
			
			//how much damage did the last hit do
			ItemStack furyWeapon = attackingPlayer.getInventory().getItemInMainHand();
			double damageDealt = hitEntity.getLastDamage();
			if (damageDealt > 1 && attackingPlayer.getAttackCooldown() > 0.85) {
				getLastFuryDamage.put(attackingPlayer.getName(), damageDealt);
			}
			double trueLastDamage = getLastFuryDamage.get(attackingPlayer.getName());
			
			//find the level of rage enchant
			List<String> furyLore = furyWeapon.getItemMeta().getLore();
			for (int i = 0; i < furyLore.size(); i++) {
				String loreLine = furyLore.get(i);
				if (loreLine.contains("fury")) {
					String[] splitLoreLine = loreLine.split(" ");
					String eLvlString = splitLoreLine[1];
					int eLvl = Integer.parseInt(eLvlString);
					double furyMultiplier = 0;
					if (eLvl == 1) {
						furyMultiplier = currentFuryMultiplier.get(attackingPlayer.getName());
						if (furyMultiplier < 0.7) {
							furyMultiplier += 0.04;
						}
					}
					else if (eLvl == 2) {
						furyMultiplier = currentFuryMultiplier.get(attackingPlayer.getName());
						if (furyMultiplier < 0.8) {
							furyMultiplier += 0.04;
						}
					}
					else if (eLvl == 3) {
						furyMultiplier = currentFuryMultiplier.get(attackingPlayer.getName());
						if (furyMultiplier < 0.9) {
							furyMultiplier += 0.045;
						}
					}
					else if (eLvl == 4) {
						furyMultiplier = currentFuryMultiplier.get(attackingPlayer.getName());
						if (furyMultiplier < 1) {
							furyMultiplier += 0.05;
						}
					}
					else if (eLvl == 5) {
						furyMultiplier = currentFuryMultiplier.get(attackingPlayer.getName());
						if (furyMultiplier < 1.1) {
							furyMultiplier += 0.055;
						}
					}
					else if (eLvl == 6) {
						furyMultiplier = currentFuryMultiplier.get(attackingPlayer.getName());
						if (furyMultiplier < 1.2) {
							furyMultiplier += 0.06;
						}
					}
					currentFuryMultiplier.put(attackingPlayer.getName(), furyMultiplier);
					double extraDamage = furyMultiplier * trueLastDamage;
					hitEntity.damage(extraDamage);
				}
			}
		}
	}
	//RAGE MULTIPLIER REMOVAL
	@EventHandler
	public void furyRemove(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Monster) {
			Player hitPlayer = (Player) e.getEntity();
			currentFuryMultiplier.put(hitPlayer.getName(), 0.0);
		}
		else if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) {
			Player hitPlayer = (Player) e.getEntity();
			currentFuryMultiplier.put(hitPlayer.getName(), 0.0);
		}
	}
	//RAGE MULTIPLIER REMOVAL ON SERVER JOIN
	@EventHandler
	public void furyRemovePlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		currentFuryMultiplier.put(player.getName(), 0.0);
	}
	
	
	//LIFESTEAL
	@EventHandler
	public void lifesteal(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(BookOpening.LIFESTEAL)) {
				return;
			}
			if (attackingPlayer.getAttackCooldown() < 0.8) {
				return;
			}
			
			//find attacker weapon so i can grab the level from it
			ItemStack lifestealWeapon = attackingPlayer.getInventory().getItemInMainHand();
			
			//use random number generator to choose whether or not to proc the enchant
			Random rand = new Random();
			int upperBound = 100;
			int procValue = rand.nextInt(upperBound);
			
			//find the level of lifesteal enchant
			List<String> lifestealLore = lifestealWeapon.getItemMeta().getLore();
			for (int i = 0; i < lifestealLore.size(); i++) {
				String loreLine = lifestealLore.get(i);
				if (loreLine.contains("lifesteal")) {
					String[] splitLoreLine = loreLine.split(" ");
					String eLvlString = splitLoreLine[1];
					int eLvl = Integer.parseInt(eLvlString);
					if (eLvl == 1 && procValue <= 12) {
						double currentHealth = attackingPlayer.getHealth();
						currentHealth += 4;
						attackingPlayer.setHealth(currentHealth);
					}
					else if (eLvl == 2 && procValue <= 12) {
						double currentHealth = attackingPlayer.getHealth();
						currentHealth += 5;
						attackingPlayer.setHealth(currentHealth);
					}
					else if (eLvl == 3 && procValue <= 12) {
						double currentHealth = attackingPlayer.getHealth();
						currentHealth += 6;
						attackingPlayer.setHealth(currentHealth);
					}
				}
			}
		}
	}
	
	
	//RAGE
	@EventHandler
	public void rage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player attackingPlayer = (Player) e.getDamager();
			Player hitPlayer = (Player) e.getEntity();
			if (!attackingPlayer.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(BookOpening.RAGE)) {
				return;
			}
			
			//how much damage did the last hit do
			ItemStack rageWeapon = attackingPlayer.getInventory().getItemInMainHand();
			double damageDealt = hitPlayer.getLastDamage();
			
			//find the level of rage enchant
			List<String> rageLore = rageWeapon.getItemMeta().getLore();
			for (int i = 0; i < rageLore.size(); i++) {
				String loreLine = rageLore.get(i);
				if (loreLine.contains("rage")) {
					String[] splitLoreLine = loreLine.split(" ");
					String eLvlString = splitLoreLine[1];
					int eLvl = Integer.parseInt(eLvlString);
					if (eLvl == 1) {
						double rageMultiplier = currentRageMultiplier.get(attackingPlayer.getName());
						if (rageMultiplier >= 0.3) {
							rageMultiplier = 0.3;
							attackingPlayer.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "ENRAGED " + ChatColor.RESET + "" + ChatColor.WHITE + "" + ChatColor.BOLD + "hit");
						}
						else if (rageMultiplier < 0.3) {
							rageMultiplier += 0.0375;
						}
						currentRageMultiplier.put(attackingPlayer.getName(), rageMultiplier);
						double extraDamage = rageMultiplier * damageDealt;
						hitPlayer.damage(extraDamage);
					}
					if (eLvl == 2) {
						double rageMultiplier = currentRageMultiplier.get(attackingPlayer.getName());
						if (rageMultiplier >= 0.5) {
							rageMultiplier = 0.5;
							attackingPlayer.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "ENRAGED " + ChatColor.RESET + "" + ChatColor.WHITE + "" + ChatColor.BOLD + "hit");
						}
						else if (rageMultiplier < 0.5) {
							rageMultiplier += 0.0625;
						}
						currentRageMultiplier.put(attackingPlayer.getName(), rageMultiplier);
						double extraDamage = rageMultiplier * damageDealt;
						hitPlayer.damage(extraDamage);
					}
				}
			}
		}
	}
	//RAGE MULTIPLIER REMOVAL
	@EventHandler
	public void rageRemove(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player hitPlayer = (Player) e.getEntity();
			currentRageMultiplier.put(hitPlayer.getName(), 0.0);
		}
	}
	//RAGE MULTIPLIER REMOVAL ON SERVER JOIN
	@EventHandler
	public void rageRemovePlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		currentRageMultiplier.put(player.getName(), 0.0);
	}
	
	
	//STOCKPILE
	@EventHandler
	public void stockpileEquip(PlayerArmorEquipEvent e) {
		Player player = e.getPlayer();
		//ItemStack playerArmor = e.getItemStack();
		if (player.getInventory().getChestplate() != null) {
			if (!player.getPlayer().getInventory().getChestplate().getItemMeta().hasEnchant(BookOpening.STOCKPILE)) {
				return;
			}
			//find the level of agility enchant
			List<String> stockpileLore = player.getInventory().getChestplate().getItemMeta().getLore();
			for (int i = 0; i < stockpileLore.size(); i++) {
				String loreLine = stockpileLore.get(i);
				if (loreLine.contains("stockpile")) {
					String[] splitLoreLine = loreLine.split(" ");
					String eLvlString = splitLoreLine[1];
					int eLvl = Integer.parseInt(eLvlString);
					//give speed boost based on enchantment level
					if (eLvl == 1) {
						player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(24);
					}
					else if (eLvl == 2) {
						player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(28);
					}
					else if (eLvl == 3) {
						player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(32);
					}
				}
			}
		}
	}
	@EventHandler
	public void stockpileUnequip(PlayerArmorUnequipEvent e) {
		Player player = e.getPlayer();
		ItemStack playerArmor = e.getItemStack();
		List<String> stockpileLore = playerArmor.getItemMeta().getLore();
		for (int i = 0; i < stockpileLore.size(); i++) {
			String loreLine = stockpileLore.get(i);
			if (loreLine.contains("stockpile")) {
				player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
			}
		}
	}
}
