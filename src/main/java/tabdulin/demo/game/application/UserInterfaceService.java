package tabdulin.demo.game.application;

import tabdulin.demo.game.fight.Fight;
import tabdulin.demo.game.fight.Hit;
import tabdulin.demo.game.fight.Rival;
import tabdulin.demo.game.model.Game;
import tabdulin.demo.game.model.GameOption;
import tabdulin.demo.game.model.GameStartMode;
import tabdulin.demo.game.player.Player;

import java.util.List;

public interface UserInterfaceService {
    /**
     * Welcomes player
     */
    void welcome();

    /**
     * Asks player to select game mode
     *
     * @return selected game mode
     *
     * @see  GameStartMode
     */
    GameStartMode selectGameMode();

    /**
     * Asks player to enter his/her name
     *
     * @return name of the player
     */
    String enterPlayerName();

    /**
     * Goodbyes player
     */
    void goodbye();

    /**
     * Asks player to select game option
     *
     * @param player Player to be asked
     *
     * @return selected game option
     *
     * @see GameOption
     */
    GameOption selectGameOption(Player player);

    /**
     * Asks player to select game of saved games
     *
     * @param games Saved games
     *
     * @return selected game
     */
    Game selectGame(List<Game> games);

    /**
     * Asks player to select hit
     *
     * @param rival Rival to hit
     *
     * @return selected hit
     */
    Hit selectHit(Rival rival);

    /**
     * Shows fight results
     *
     * @param fight
     */
    void showFightResults(Fight fight);

    /**
     * Shows informartion that there is no saved games
     */
    void noSavedGames();
}
