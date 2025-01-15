package io.game;

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
			if (buy <= 80) {
				rarity = "common";
			} else if (buy <= 100) {
				rarity = "uncommon";
			}
		}
		if (level == 2) {
			if (buy <= 55) {
				rarity = "common";
			} else if (buy <= 85) {
				rarity = "uncommon";
			} else if (buy <= 100) {
				rarity = "rare";
			}
		}
		if (level == 3) {
			if (buy <= 40) {
				rarity = "common";
			} else if (buy <= 70) {
				rarity = "uncommon";
			} else if (buy <= 95) {
				rarity = "rare";
			} else if (buy <= 100) {
				rarity = "epic";
			}
		}
		if (level == 4) {
			if (buy <= 30) {
				rarity = "common";
			} else if (buy <= 70) {
				rarity = "uncommon";
			} else if (buy <= 95) {
				rarity = "rare";
			} else if (buy <= 100) {
				rarity = "epic";
			}
		}
		if (level == 5) {
			if (buy <= 18) {
				rarity = "common";
			} else if (buy <= 43) {
				rarity = "uncommon";
			} else if (buy <= 75) {
				rarity = "rare";
			} else if (buy <= 97) {
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

	public static int upgrade(int gold, int level) {

		if (gold >= 500 && level == 1) {
			return 500;
		}
		if (gold >= 1000 && level == 2) {
			return 1000;
		}
		if (gold >= 1500 && level == 3) {
			return 1500;
		}
		if (gold >= 500 && level == 4) {
			return 2000;
		}
		return 0;
	}
	public static int level(int upgrade) {
		
		if (upgrade == 500) {
			return 1;
		}
		if (upgrade == 1000) {
			return 2;
		}
		if (upgrade == 1500) {
			return 3;
		}
		if (upgrade == 2000) {
			return 4;
		}
		return 0;
	}

}
