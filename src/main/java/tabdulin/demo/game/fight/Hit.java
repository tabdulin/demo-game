package tabdulin.demo.game.fight;

/**
 * Hit of Rock-Paper-Scissors game
 */
public enum Hit {
    ROCK,
    PAPER,
    SCISSORS;

    @Override
    public String toString() {
        return name().substring(0, 1) + name().substring(1).toLowerCase();
    }

}
