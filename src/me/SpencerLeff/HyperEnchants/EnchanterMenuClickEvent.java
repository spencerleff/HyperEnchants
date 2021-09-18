package me.SpencerLeff.HyperEnchants;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchanterMenuClickEvent implements Listener {
	public static HashMap<String, Long> enchanterMenuClickCooldown = new HashMap<String, Long>();
	
	@EventHandler
	public void enchanterMenuClickEvent(InventoryClickEvent e) throws InterruptedException {
		Player player = (Player) e.getWhoClicked();
		if (!e.getView().getTitle().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "Enchanter")) {
			return;
		}
		
		long currentTime = System.currentTimeMillis();
		long lastMenuClickTime = enchanterMenuClickCooldown.get(player.getName());
		
		if (currentTime < lastMenuClickTime + 200) {
			e.setCancelled(true);
			player.sendMessage(ChatColor.RED + "Please wait longer between clicks!");
			return;
		}
		
		enchanterMenuClickCooldown.put(player.getName(), currentTime);
		
		e.setCancelled(true);
		if (e.getCurrentItem() == null) {
			return;
		}
		switch(e.getCurrentItem().getType()) {
		//common book
		case WHITE_STAINED_GLASS_PANE:
			if (player.getInventory().firstEmpty() == -1) {
				//player.closeInventory();
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
				player.sendMessage(ChatColor.RED + "Inventory is full!");
				break;
			}
			else {
				//e.setCancelled(true);
				if (player.getLevel() >= 20) {
					//player.closeInventory();
					player.setLevel(player.getLevel() - 20);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1f, 1f);
					player.sendMessage(ChatColor.GREEN + "Purchased " + ChatColor.WHITE + "Common enchantment" + ChatColor.WHITE + " (x1)");
					giveEnchantmentBook(player, "common");
					break;
				}
				else {
					//player.closeInventory();
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
					player.sendMessage(ChatColor.RED + "Not enough exp!");
					break;
				}
			}
		//rare book
		case LIGHT_BLUE_STAINED_GLASS_PANE:
			if (player.getInventory().firstEmpty() == -1) {
				//player.closeInventory();
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
				player.sendMessage(ChatColor.RED + "Inventory is full!");
				break;
			}
			else {
				//e.setCancelled(true);
				if (player.getLevel() >= 35) {
					//player.closeInventory();
					player.setLevel(player.getLevel() - 35);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1f, 1f);
					player.sendMessage(ChatColor.GREEN + "Purchased " + ChatColor.AQUA + "Rare enchantment" + ChatColor.WHITE + " (x1)");
					giveEnchantmentBook(player, "rare");
					break;
				}
				else {
					//player.closeInventory();
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
					player.sendMessage(ChatColor.RED + "Not enough exp!");
					break;
				}
			}
		//legendary book
		case ORANGE_STAINED_GLASS_PANE:
			if (player.getInventory().firstEmpty() == -1) {
				//player.closeInventory();
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
				player.sendMessage(ChatColor.RED + "Inventory is full!");
				break;
			}
			else {
				//e.setCancelled(true);
				if (player.getLevel() >= 60) {
					//player.closeInventory();
					player.setLevel(player.getLevel() - 60);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1f, 1f);
					player.sendMessage(ChatColor.GREEN + "Purchased " + ChatColor.GOLD + "legendary enchantment" + ChatColor.WHITE + " (x1)");
					giveEnchantmentBook(player, "legendary");
					break;
				}
				else {
					//player.closeInventory();
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
					player.sendMessage(ChatColor.RED + "Not enough exp!");
					break;
				}
			}
		//surrounding panes
		case LIGHT_GRAY_STAINED_GLASS_PANE:
			//e.setCancelled(true);
			//player.closeInventory();
		default:
			//Nothing to do here
		}
	}
	
	//give player enchantment book
	public void giveEnchantmentBook(Player player, String bookType) {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta itemMeta = item.getItemMeta();
		
		if (bookType == "common") {
			itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Random Common Enchantment " + ChatColor.RESET + "" + ChatColor.GRAY + "(right click)");
			itemMeta.setLore(Arrays.asList(ChatColor.WHITE + "right click to receive a random common enchantment book!"));
		}
		else if (bookType == "rare") {
			itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Random Rare Enchantment " + ChatColor.RESET + "" + ChatColor.GRAY + "(right click)");
			itemMeta.setLore(Arrays.asList(ChatColor.WHITE + "right click to receive a random rare enchantment book!"));
		}
		else if (bookType == "legendary") {
			itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Random Legendary Enchantment " + ChatColor.RESET + "" + ChatColor.GRAY + "(right click)");
			itemMeta.setLore(Arrays.asList(ChatColor.WHITE + "right click to receive a random legendary enchantment book!"));
		}
		
		item.setItemMeta(itemMeta);
		player.getInventory().addItem(item);
	}
	
	@EventHandler 
	public void setBaseLineMenuClickCooldown(PlayerJoinEvent e) {
		long currentTime = System.currentTimeMillis();
		enchanterMenuClickCooldown.put(e.getPlayer().getName(), currentTime);
	}
}
