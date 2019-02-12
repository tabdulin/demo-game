package tabdulin.demo.game.model;

/**
 * Game option when the game started. Player can explore for the fight or exit (with or without saving game)
 */
public enum GameOption {
    EXPLORE_FOR_THE_FIGHT("Explore for the fight"),
    EXIT_AND_SAVE("Exit and save game"),
    EXIT_WITHOUT_SAVING("Exit without saving game");

    private String text;

    GameOption(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
