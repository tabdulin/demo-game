package tabdulin.demo.game.application;

import org.junit.jupiter.api.Test;
import tabdulin.demo.game.application.ApplicationFactory;
import tabdulin.demo.game.application.GameService;
import tabdulin.demo.game.fight.Fight;
import tabdulin.demo.game.fight.Hit;
import tabdulin.demo.game.fight.Rival;
import tabdulin.demo.game.model.Game;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceImplTest {
    public static final String PLAYER_NAME = "Player";
    private GameService gameService = ApplicationFactory.INSTANCE.getGameService();

    @Test
    void createNewGame() {
        Game game = gameService.createNewGame(PLAYER_NAME);
        assertNotNull(game.getPlayer());
        assertEquals(PLAYER_NAME, game.getPlayer().getName());
        assertEquals(0, game.getPlayer().getExperience().getValue());
        assertNotNull(game.getId());
        assertNotNull(game.getDate());
        assertTrue(game.getFights().isEmpty());
    }

    @Test
    void exploreForTheFight() {
        Rival rival = gameService.exploreForTheRival();
        assertNotNull(rival);
    }

    @Test
    void fight() {
        Fight fight = gameService.fight(Hit.SCISSORS, Rival.ALBERT_EINSTEIN);
        assertNotNull(fight);
        assertEquals(Hit.SCISSORS, fight.getPlayerHit());
        assertEquals(Rival.ALBERT_EINSTEIN, fight.getRival());
        assertNotNull(fight.getRivalHit());
    }

}