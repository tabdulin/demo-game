package tabdulin.demo.game.application;

import tabdulin.demo.game.fight.Fight;
import tabdulin.demo.game.fight.Hit;
import tabdulin.demo.game.fight.Rival;
import tabdulin.demo.game.model.Game;

import java.util.List;

/**
 * Service for running game operations
 */
public interface GameService {
    /**
     * Create new game for the player
     *
     * @param playerName player's name
     * @return created game
     */
    Game createNewGame(String playerName);

    /**
     * Loading games saved before
     *
     * @return list of games or empty list of none exists
     */
    List<Game> loadSavedGames();

    /**
     * Explores for random rival
     *
     * @return random rival
     */
    Rival exploreForTheRival();

    /**
     * Creates a fight for the player with his/her rival. Rival hits with a random hit.
     *
     * @param playerHit player's hit
     * @param rival player's rival
     *
     * @return fight results
     */
    Fight fight(Hit playerHit, Rival rival);

    /**
     * Saves game
     *
     * @param game
     */
    void saveGame(Game game);
}
