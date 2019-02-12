package tabdulin.demo.game.application;

import tabdulin.demo.game.fight.Fight;
import tabdulin.demo.game.fight.FightFactory;
import tabdulin.demo.game.fight.Hit;
import tabdulin.demo.game.fight.Rival;
import tabdulin.demo.game.model.Game;

import java.util.List;

/**
 * Implementation of game service
 * User application factory to get instance
 *
 * @see ApplicationFactory
 */
class GameServiceImpl implements GameService {
    private FightFactory fightFactory;
    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository, FightFactory fightFactory) {
        this.fightFactory = fightFactory;
        this.gameRepository = gameRepository;
    }

    @Override
    public Game createNewGame(String playerName) {
        return new Game(playerName);
    }

    @Override
    public List<Game> loadSavedGames() {
        return gameRepository.findAll();
    }

    @Override
    public Rival exploreForTheRival() {
        return fightFactory.selectRandomRival();
    }

    @Override
    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    @Override
    public Fight fight(Hit playerHit, Rival rival) {
        return fightFactory.createFight(playerHit, rival);
    }

}
