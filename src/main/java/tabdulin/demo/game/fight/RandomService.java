package tabdulin.demo.game.fight;

import java.util.Random;

/**
 * Service to randomize selection
 */
class RandomService {
    private Random random = new Random();

    public <ITEM> ITEM random(ITEM[] items) {
        return items[random.nextInt(items.length)];
    }
}
