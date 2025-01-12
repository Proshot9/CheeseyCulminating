package screens;

public class Cost {
	public static int percent(int level) {
		if (level == 1) {
			return 10;
		} else if (level == 2) {
			return 20;
		} else if (level == 3) {
			return 30;
		} else if (level == 4) {
			return 40;
		} else {
			return 50;
		}
	}

	public static String rates(int level, int buy) {
		String rarity = "";
		if (level == 1) {
			if (buy <= 70) {
				rarity = "common";
			} else if (buy <= 90) {
				rarity = "uncommon";
			} else if (buy <= 98) {
				rarity = "rare";
			} else if (buy <= 99) {
				rarity = "epic";
			} else if (buy <= 100) {
				rarity = "legendary";
			}
		}
		return rarity;
	}

	public static int rarity(String israrity) {
		int sell = 0;
		if (israrity.equals("common")) {
			sell = 5;
		} else if (israrity.equals("uncommon")) {
			sell = 10;
		} else if (israrity.equals("rare")) {
			sell = 20;
		} else if (israrity.equals("epic")) {
			sell = 30;
		} else if (israrity.equals("legendary")) {
			sell = 50;
		}
		return sell;

	}
}
