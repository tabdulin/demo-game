package tabdulin.demo.game.fight;

import java.io.Serializable;

/**
 * Immutable fight data of the player
 */
public class Fight implements Serializable {
    /**
     * Player's rival
     */
    private final Rival rival;
    /**
     * Player's hit
     */
    private final Hit playerHit;
    /**
     * Player rival's hit
     */
    private final Hit rivalHit;
    /**
     * Result of the fight
     */
    private final Result result;

    /**
     * Create a fight.
     * Use FightFactory whenever possible to exclude compromising fight result
     *
     * @param playerHit
     * @param rival
     * @param rivalHit
     * @param result
     *
     * @see FightFactory
     */
    Fight(Hit playerHit, Rival rival, Hit rivalHit, Result result) {
        this.playerHit = playerHit;
        this.rival = rival;
        this.rivalHit = rivalHit;
        this.result = result;
    }

    public Hit getPlayerHit() {
        return playerHit;
    }

    public Rival getRival() {
        return rival;
    }

    public Hit getRivalHit() {
        return rivalHit;
    }

    public Result getResult() {
        return result;
    }

    public enum Result {
        VICTORY,
        LOSS,
        DRAW
    }
}
