package me.SpencerLeff.HyperEnchants;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchanterMenuClickEvent implements Listener {
	@EventHandler
	public void enchanterMenuClickEvent(InventoryClickEvent e) throws InterruptedException {
		Player player = (Player) e.getWhoClicked();
		switch(e.getCurrentItem().getType()) {
		//common book
		case WHITE_STAINED_GLASS_PANE:
			if (player.getInventory().firstEmpty() == -1) {
				player.closeInventory();
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
				player.sendMessage(ChatColor.DARK_RED + "Inventory is full! try again later");
				break;
			}
			else {
				e.setCancelled(true);
				if (player.getLevel() >= 20) {
					player.closeInventory();
					player.setLevel(player.getLevel() - 20);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1f, 1f);
					player.sendMessage(ChatColor.GREEN + "Purchase successful! (1x common enchantment)");
					giveEnchantmentBook(player, "common");
					break;
				}
				else {
					player.closeInventory();
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
					break;
				}
			}
		//rare book
		case LIGHT_BLUE_STAINED_GLASS_PANE:
			if (player.getInventory().firstEmpty() == -1) {
				player.closeInventory();
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
				player.sendMessage(ChatColor.DARK_RED + "Inventory is full! try again later");
				break;
			}
			else {
				e.setCancelled(true);
				if (player.getLevel() >= 35) {
					player.closeInventory();
					player.setLevel(player.getLevel() - 35);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1f, 1f);
					player.sendMessage(ChatColor.GREEN + "Purchase successful! (1x rare enchantment)");
					giveEnchantmentBook(player, "rare");
					break;
				}
				else {
					player.closeInventory();
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
					break;
				}
			}
		//legendary book
		case ORANGE_STAINED_GLASS_PANE:
			if (player.getInventory().firstEmpty() == -1) {
				player.closeInventory();
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
				player.sendMessage(ChatColor.DARK_RED + "Inventory is full! try again later");
				break;
			}
			else {
				e.setCancelled(true);
				if (player.getLevel() >= 60) {
					player.closeInventory();
					player.setLevel(player.getLevel() - 60);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1f, 1f);
					player.sendMessage(ChatColor.GREEN + "Purchase successful! (1x legendary enchantment)");
					giveEnchantmentBook(player, "legendary");
					break;
				}
				else {
					player.closeInventory();
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
					break;
				}
			}
		//surrounding panes
		case LIGHT_GRAY_STAINED_GLASS_PANE:
			e.setCancelled(true);
			player.closeInventory();
		default:
			//Nothing to do here
		}
		//after enchanter gui is closed, allow user to move things in inventory again
		e.setCancelled(false);
	}
	
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
}
