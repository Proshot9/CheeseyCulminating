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

	public int power(int level, String rarity) {
		Random rand = new Random();
		int power = 0; 
		
		if (level > 2) {
			int min = 100 * level - 1;
			power = rand.nextInt(min, 100 * level + 1);
			return power;
		} else if (level == 1) {
			power = rand.nextInt(50, 100);
			return power;
		}
		return 0;
	}

}