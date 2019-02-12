package tabdulin.demo.game.model;

/**
 * Mode of starting game.
 * Player can star new game of resume saved before.
 */
public enum GameStartMode {
    NEW_GAME("New game"),
    RESUME("Resume");

    private String text;

    GameStartMode(String text) {
        this.text = text;
    }

}
