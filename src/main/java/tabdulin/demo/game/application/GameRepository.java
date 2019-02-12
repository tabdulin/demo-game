package tabdulin.demo.game.application;

import tabdulin.demo.game.model.Game;

import java.util.List;

/**
 * Interface to save and find saved games
 */
public interface GameRepository {

    List<Game> findAll();

    void save(Game game);
}
