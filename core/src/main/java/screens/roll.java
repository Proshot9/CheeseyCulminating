package screens;

import java.util.Random;

public class roll {
	private int Max;
	private int rarity;

	roll() {
		Max = 100;
		rarity = buy();
	}

	roll(int n) {
		Max = n;
		rarity = buy();

	}

	public int buy() {
		Random rand = new Random();
		rarity = rand.nextInt(1, Max + 1);
		return rarity;
	}

	public String setRarity(int rarity) {

		String israrity = null;
		if (rarity <= 60) {
			israrity = "common";
		} else if (rarity <= 80) {
			israrity = "uncommon";
		} else if (rarity < 90) {
			israrity = "rare";
		} else if (rarity <= 95) {
			israrity = "epic";
		} else if (rarity <= 100) {
			israrity = "legendary";
		}
		return israrity;
	}

}