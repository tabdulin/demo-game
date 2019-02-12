package tabdulin.demo.game.application;

import tabdulin.demo.game.fight.Fight;
import tabdulin.demo.game.fight.Hit;
import tabdulin.demo.game.fight.Rival;
import tabdulin.demo.game.model.Game;
import tabdulin.demo.game.model.GameOption;
import tabdulin.demo.game.model.GameStartMode;

import java.util.List;

/**
 * Game application's engine
 */
public class GameApplication {
    /**
     * User interaction interface
     */
    private UserInterfaceService uiService;
    /**
     * Game data holder
     */
    private Game game;

    /**
     * Creates application of the game
     *
     * @param uiService user interaction interface implementation
     */
    public GameApplication(UserInterfaceService uiService) {
        this.uiService = uiService;
    }

    /**
     * Provides workflow of the game
     */
    public final void play() {
        uiService.welcome();
        GameService gameService = ApplicationFactory.INSTANCE.getGameService();
        GameStartMode mode = uiService.selectGameMode();
        switch (mode) {
            case RESUME:
                List<Game> games = gameService.loadSavedGames();
                if (!games.isEmpty()) {
                    game = uiService.selectGame(games);
                    break;
                } else {
                    uiService.noSavedGames();
                }
            case NEW_GAME:
                String playerName = uiService.enterPlayerName();
                game = gameService.createNewGame(playerName);
                break;
            default:
                throw new IllegalStateException("Invalid game mode: " + mode);
        }

        while (true) {
            GameOption option = uiService.selectGameOption(game.getPlayer());
            switch (option) {
                case EXPLORE_FOR_THE_FIGHT:
                    Rival rival = gameService.exploreForTheRival();
                    Hit hit = uiService.selectHit(rival);
                    Fight fight = gameService.fight(hit, rival);
                    game.addFight(fight);
                    uiService.showFightResults(fight);
                    break;
                case EXIT_AND_SAVE:
                    gameService.saveGame(game);
                case EXIT_WITHOUT_SAVING:
                    uiService.goodbye();
                    return;
            }
        }
    }

    public Game getGame() {
        return game;
    }
}
