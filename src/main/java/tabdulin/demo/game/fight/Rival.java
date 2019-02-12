package tabdulin.demo.game.fight;

/**
 * Player's rival in the game.
 * Famous physicist to make the game more funny
 */
public enum Rival {
    ALBERT_EINSTEIN("Albert Einstein"),
    RICHARD_FEYNMAN("Richard Feynman"),
    JACOB_BERNOULLI("Jacob Bernoulli"),
    ISAAC_NEWTON("Isaac Newton");

    private String name;

    Rival(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
