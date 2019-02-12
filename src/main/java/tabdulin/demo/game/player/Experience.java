package tabdulin.demo.game.player;

import java.io.Serializable;

/**
 * Experience of player.
 * Value is represented as a non-negative integer.
 */
public class Experience implements Serializable {
    /**
     * Value of the experience
     */
    private int value = 0;

    public void gain() {
        value++;
    }

    public void loose() {
        if (value > 0) value--;
    }

    public int getValue() {
        return value;
    }
}
