package me.SpencerLeff.HyperEnchants;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.enchantments.Enchantment;

public class CustomEnchants {
	//Common
	public static final Enchantment AQUATIC = new EnchantmentWrapper("aquatic", "Aquatic", 1);
	public static final Enchantment BLESSED = new EnchantmentWrapper("blessed", "Blessed", 1);
	public static final Enchantment CONFUSION = new EnchantmentWrapper("confusion", "Confusion", 3);
	public static final Enchantment CRYSTALLIZE = new EnchantmentWrapper("crystallize", "Crystallize", 4);
	public static final Enchantment GEOMANCY = new EnchantmentWrapper("geomancy", "Geomancy", 5);
	public static final Enchantment GOOMBA = new EnchantmentWrapper("goomba", "Goomba", 3);
	public static final Enchantment GUILLOTINE = new EnchantmentWrapper("guillotine", "Guillotine", 1);
	public static final Enchantment NOCTURNAL = new EnchantmentWrapper("nocturnal", "Nocturnal", 1);
	public static final Enchantment PILLAGE = new EnchantmentWrapper("pillage", "Pillage", 2);
	public static final Enchantment PLUNGE = new EnchantmentWrapper("plunge", "Plunge", 1);
	public static final Enchantment POSEIDON = new EnchantmentWrapper("poseidon", "Poseidon", 1);
	public static final Enchantment REFLECT = new EnchantmentWrapper("reflect", "Reflect", 1);
	public static final Enchantment RESTED = new EnchantmentWrapper("rested", "Rested", 1);
	public static final Enchantment SHIFT = new EnchantmentWrapper("shift", "Shift", 1);
	public static final Enchantment SHROOMY = new EnchantmentWrapper("shroomy", "Shroomy", 4);
	public static final Enchantment SHROUD = new EnchantmentWrapper("shroud", "Shroud", 4);
	public static final Enchantment SOW = new EnchantmentWrapper("sow", "Sow", 1);
	public static final Enchantment STARVE = new EnchantmentWrapper("starve", "Starve", 7);
	public static final Enchantment TELEPATHY = new EnchantmentWrapper("telepathy", "Telepathy", 1);
	public static final Enchantment THUNDERBOLT = new EnchantmentWrapper("thunderbolt", "Thunderbolt", 2);
	public static final Enchantment VENOM = new EnchantmentWrapper("venom", "Venom", 2);
	//Rare
	public static final Enchantment ABSORB = new EnchantmentWrapper("absorb", "Absorb", 2);
	public static final Enchantment ANGELIC = new EnchantmentWrapper("angelic", "Angelic", 6);
	public static final Enchantment CLEAVE = new EnchantmentWrapper("cleave", "Cleave", 5);
	public static final Enchantment DEVASTATE = new EnchantmentWrapper("devastate", "Devastate", 3);
	public static final Enchantment DIMINISH = new EnchantmentWrapper("diminish", "Diminish", 1);
	public static final Enchantment EXECUTIONER = new EnchantmentWrapper("executioner", "Executioner", 6);
	public static final Enchantment FROZEN = new EnchantmentWrapper("frozen", "Frozen", 3);
	public static final Enchantment GORGE = new EnchantmentWrapper("gorge", "Gorge", 2);
	public static final Enchantment GUARDIANS = new EnchantmentWrapper("guardians", "Guardians", 9);
	public static final Enchantment JUMP = new EnchantmentWrapper("jump", "Jump", 4);
	public static final Enchantment MAGNETIZE = new EnchantmentWrapper("magnetize", "Magnetize", 3);
	public static final Enchantment NIMBLE = new EnchantmentWrapper("nimble", "Nimble", 3);
	public static final Enchantment OBSIDIANSHIELD = new EnchantmentWrapper("obsidianshield", "Obsidianshield", 1);
	public static final Enchantment PINPOINT = new EnchantmentWrapper("pinpoint", "Pinpoint", 2);
	public static final Enchantment POKEY = new EnchantmentWrapper("pokey", "Pokey", 4);
	public static final Enchantment RETALIATE = new EnchantmentWrapper("retaliate", "Retaliate", 5);
	public static final Enchantment RETRIBUTION = new EnchantmentWrapper("retribution", "Retribution", 3);
	public static final Enchantment SKYROCKET = new EnchantmentWrapper("skyrocket", "Skyrocket", 3);
	public static final Enchantment THWOMP = new EnchantmentWrapper("thwomp", "Thwomp", 3);
	public static final Enchantment TURBO = new EnchantmentWrapper("turbo", "Turbo", 5);
	public static final Enchantment VANQUISH = new EnchantmentWrapper("vanquish", "Vanquish", 2);
	public static final Enchantment WISDOM = new EnchantmentWrapper("wisdom", "Wisdom", 1);
	//Legendary
	public static final Enchantment ACROBATICS = new EnchantmentWrapper("acrobatics", "Acrobatics", 5);
	public static final Enchantment AERODYNAMICS = new EnchantmentWrapper("aerodynamics", "Aerodynamics", 2);
	public static final Enchantment AGILITY = new EnchantmentWrapper("agility", "Agility", 3);
	public static final Enchantment ANNIHILATION = new EnchantmentWrapper("annihilation", "Annihilation", 3);
	public static final Enchantment BLOOM = new EnchantmentWrapper("bloom", "Bloom", 5);
	public static final Enchantment BLOSSOM = new EnchantmentWrapper("blossom", "Blossom", 6);
	public static final Enchantment DEMISE = new EnchantmentWrapper("demise", "Demise", 7);
	public static final Enchantment DRUNK = new EnchantmentWrapper("drunk", "Drunk", 2);
	public static final Enchantment DODGE = new EnchantmentWrapper("dodge", "Dodge", 4);
	public static final Enchantment ENRAGE = new EnchantmentWrapper("enrage", "Enrage", 4);
	public static final Enchantment FATE = new EnchantmentWrapper("fate", "Fate", 3);
	public static final Enchantment FURY = new EnchantmentWrapper("fury", "Fury", 6);
	public static final Enchantment GLORY = new EnchantmentWrapper("glory", "Glory", 3);
	public static final Enchantment INTELLECT = new EnchantmentWrapper("intellect", "Intellect", 4);
	public static final Enchantment LIFESTEAL = new EnchantmentWrapper("lifesteal", "Lifesteal", 3);
	public static final Enchantment LUCKY = new EnchantmentWrapper("lucky", "Lucky", 10);
	public static final Enchantment METEOR = new EnchantmentWrapper("meteor", "Meteor", 4);
	public static final Enchantment PUNCTURE = new EnchantmentWrapper("puncture", "Puncture", 1);
	public static final Enchantment RAGE = new EnchantmentWrapper("rage", "Rage", 2);
	public static final Enchantment SAFEGUARD = new EnchantmentWrapper("safeguard", "Safeguard", 1);
	public static final Enchantment SILENCE = new EnchantmentWrapper("silence", "Silence", 7);
	public static final Enchantment STALWART = new EnchantmentWrapper("stalwart", "Stalwart", 3);
	public static final Enchantment STOCKPILE = new EnchantmentWrapper("stockpile", "Stockpile", 3);
	public static final Enchantment VOIDWALKER = new EnchantmentWrapper("voidwalker", "Voidwalker", 5);
	public static final Enchantment WHIRLPOOL = new EnchantmentWrapper("whirlpool", "Whirlpool", 1);
	//Hyper
	public static final Enchantment BLINK = new EnchantmentWrapper("blink", "Blink", 1);
	public static final Enchantment OVERDRIVE = new EnchantmentWrapper("overdrive", "Overdrive", 1);
	public static final Enchantment TITAN = new EnchantmentWrapper("titan", "Titan", 1);
	
	public static void register() {
		
		//COMMON ENCHANTS
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.AQUATIC)) {
			registerEnchantment(AQUATIC);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.BLESSED)) {
			registerEnchantment(BLESSED);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.CONFUSION)) {
			registerEnchantment(CONFUSION);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.CRYSTALLIZE)) {
			registerEnchantment(CRYSTALLIZE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.GEOMANCY)) {
			registerEnchantment(GEOMANCY);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.GOOMBA)) {
			registerEnchantment(GOOMBA);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.GUILLOTINE)) {
			registerEnchantment(GUILLOTINE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.NOCTURNAL)) {
			registerEnchantment(NOCTURNAL);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.PILLAGE)) {
			registerEnchantment(PILLAGE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.PLUNGE)) {
			registerEnchantment(PLUNGE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.POSEIDON)) {
			registerEnchantment(POSEIDON);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.REFLECT)) {
			registerEnchantment(REFLECT);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.RESTED)) {
			registerEnchantment(RESTED);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.SHIFT)) {
			registerEnchantment(SHIFT);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.SHROOMY)) {
			registerEnchantment(SHROOMY);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.SHROUD)) {
			registerEnchantment(SHROUD);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.SOW)) {
			registerEnchantment(SOW);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.STARVE)) {
			registerEnchantment(STARVE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.TELEPATHY)) {
			registerEnchantment(TELEPATHY);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.THUNDERBOLT)) {
			registerEnchantment(THUNDERBOLT);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.VENOM)) {
			registerEnchantment(VENOM);
		}
		
		//RARE ENCHANTS
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.ABSORB)) {
			registerEnchantment(ABSORB);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.ANGELIC)) {
			registerEnchantment(ANGELIC);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.CLEAVE)) {
			registerEnchantment(CLEAVE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.DEVASTATE)) {
			registerEnchantment(DEVASTATE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.DIMINISH)) {
			registerEnchantment(DIMINISH);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.EXECUTIONER)) {
			registerEnchantment(EXECUTIONER);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.FROZEN)) {
			registerEnchantment(FROZEN);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.GORGE)) {
			registerEnchantment(GORGE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.GUARDIANS)) {
			registerEnchantment(GUARDIANS);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.JUMP)) {
			registerEnchantment(JUMP);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.MAGNETIZE)) {
			registerEnchantment(MAGNETIZE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.NIMBLE)) {
			registerEnchantment(NIMBLE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.OBSIDIANSHIELD)) {
			registerEnchantment(OBSIDIANSHIELD);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.PINPOINT)) {
			registerEnchantment(PINPOINT);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.POKEY)) {
			registerEnchantment(POKEY);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.RETALIATE)) {
			registerEnchantment(RETALIATE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.RETRIBUTION)) {
			registerEnchantment(RETRIBUTION);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.SKYROCKET)) {
			registerEnchantment(SKYROCKET);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.THWOMP)) {
			registerEnchantment(THWOMP);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.TURBO)) {
			registerEnchantment(TURBO);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.VANQUISH)) {
			registerEnchantment(VANQUISH);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.WISDOM)) {
			registerEnchantment(WISDOM);
		}
		
		//LEGENDARY ENCHANTS
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.ACROBATICS)) {
			registerEnchantment(ACROBATICS);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.AERODYNAMICS)) {
			registerEnchantment(AERODYNAMICS);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.AGILITY)) {
			registerEnchantment(AGILITY);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.ANNIHILATION)) {
			registerEnchantment(ANNIHILATION);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.BLOOM)) {
			registerEnchantment(BLOOM);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.BLOSSOM)) {
			registerEnchantment(BLOSSOM);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.DEMISE)) {
			registerEnchantment(DEMISE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.DRUNK)) {
			registerEnchantment(DRUNK);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.DODGE)) {
			registerEnchantment(DODGE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.ENRAGE)) {
			registerEnchantment(ENRAGE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.FATE)) {
			registerEnchantment(FATE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.FURY)) {
			registerEnchantment(FURY);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.GLORY)) {
			registerEnchantment(GLORY);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.INTELLECT)) {
			registerEnchantment(INTELLECT);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.LIFESTEAL)) {
			registerEnchantment(LIFESTEAL);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.LUCKY)) {
			registerEnchantment(LUCKY);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.METEOR)) {
			registerEnchantment(METEOR);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.PUNCTURE)) {
			registerEnchantment(PUNCTURE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.RAGE)) {
			registerEnchantment(RAGE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.SAFEGUARD)) {
			registerEnchantment(SAFEGUARD);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.SILENCE)) {
			registerEnchantment(SILENCE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.STALWART)) {
			registerEnchantment(STALWART);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.STOCKPILE)) {
			registerEnchantment(STOCKPILE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.VOIDWALKER)) {
			registerEnchantment(VOIDWALKER);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.WHIRLPOOL)) {
			registerEnchantment(WHIRLPOOL);
		}
		
		//HYPER ENCHANTS
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.BLINK)) {
			registerEnchantment(BLINK);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.OVERDRIVE)) {
			registerEnchantment(OVERDRIVE);
		}
		if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.TITAN)) {
			registerEnchantment(TITAN);
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
		catch (Exception e) {
			registered = false;
			e.printStackTrace();
		}
		if (registered) {
			//send message to console
		}
	}
}
