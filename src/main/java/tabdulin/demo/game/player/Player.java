package tabdulin.demo.game.player;

import java.io.Serializable;

/**
 * Player of the game
 */
public class Player implements Serializable {
    private final String name;
    private final Experience experience;

    public Player(String name) {
        this.name = name;
        this.experience = new Experience();
    }

    public String getName() {
        return name;
    }

    public Experience getExperience() {
        return experience;
    }
}
