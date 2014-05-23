package net.subaraki.gravestone;

import net.minecraft.util.StatCollector;

public class StringTable {


	public static String getStringFromMeta(String nameOfDeathPlayer, int meta){

		String rest1 = StatCollector.translateToLocal("grave.rest.1");
		String rest2 = StatCollector.translateToLocal("grave.rest.2");

		String friend1 = StatCollector.translateToLocal("grave.friend.1");
		String friend2 = StatCollector.translateToLocal("grave.friend.2");

		String hero1 = StatCollector.translateToLocal("grave.hero.1");
		String hero2 = StatCollector.translateToLocal("grave.hero.2");

		String glory1 = StatCollector.translateToLocal("grave.glory.1");
		String glory2 = StatCollector.translateToLocal("grave.glory.2");

		String passed1 = StatCollector.translateToLocal("grave.passed.1");
		String passed2 = StatCollector.translateToLocal("grave.passed.2");

		String silence1 = StatCollector.translateToLocal("grave.silence.1");
		String silence2 = StatCollector.translateToLocal("grave.silence.2");

		String memory1 = StatCollector.translateToLocal("grave.memory.1");
		String memory2 = StatCollector.translateToLocal("grave.memory.2");

		String angle1 = StatCollector.translateToLocal("grave.angle.1");
		String angle2 = StatCollector.translateToLocal("grave.angle.2");

		String knight1 = StatCollector.translateToLocal("grave.knight.1");
		String knight2 = StatCollector.translateToLocal("grave.knight.2");

		if(meta == 1) {
			return rest1 + nameOfDeathPlayer + rest2;
		} else if(meta == 2) {
			return friend1 + nameOfDeathPlayer + friend2;
		} else if(meta == 3) {
			return hero1 + nameOfDeathPlayer + hero2;
		} else if(meta == 4) {
			return glory1 + nameOfDeathPlayer + glory2;
		} else if(meta == 5) {
			return passed1 + nameOfDeathPlayer + passed2 ;
		} else if(meta == 7) {
			return silence1 + nameOfDeathPlayer + silence2 ;
		} else if(meta == 6) {
			return memory1 + nameOfDeathPlayer + memory2 ;
		} else if(meta == 8) {
			return angle1 + nameOfDeathPlayer + angle2 ;
		} else if(meta == 9) {
			return knight1 + nameOfDeathPlayer+ knight2 ;
		}

		return "error encountered. index out of bounds : " + meta;

	}
}
