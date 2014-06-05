package net.subaraki.gravestone.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigGraveStones {

	public static ConfigGraveStones instance = new ConfigGraveStones();

	public static boolean enableGravesTroughKey = true;
	
	public static int graveLevel = 5;
	public static int[] graveOrder = new int[]{6,1,2,3,7,4,5,10,9,8};
	
	public void loadConfig(File file)
	{
		Configuration config = new Configuration(file);
		config.load();
		loadSettings(config);
		config.save();
	}

	private void loadSettings(Configuration config) {
		enableGravesTroughKey = config.get("GraveStones", "enable Graves Trough Key", true).getBoolean(true);
		graveLevel = config.get("GraveStones", "change graves every x level", 5).getInt(5);
		graveOrder = config.get("GraveStones", "grave orders", new int[]{6,1,2,3,7,4,5,10,9,8}).getIntList();
	}
}
