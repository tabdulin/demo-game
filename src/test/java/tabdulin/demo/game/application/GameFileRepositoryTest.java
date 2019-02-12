package tabdulin.demo.game.application;

import org.junit.jupiter.api.Test;
import tabdulin.demo.game.application.GameFileRepository;
import tabdulin.demo.game.application.GameRepository;
import tabdulin.demo.game.fight.Fight;
import tabdulin.demo.game.fight.FightFactory;
import tabdulin.demo.game.fight.Hit;
import tabdulin.demo.game.fight.Rival;
import tabdulin.demo.game.model.Game;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameFileRepositoryTest {

    @Test
    void findAll() {
        Game game = new Game("some player");
        game.addFight(FightFactory.INSTANCE.createFight(Hit.SCISSORS, Rival.ALBERT_EINSTEIN, Hit.SCISSORS));
        File file = new File(".find");
        if (file.exists()) {
            file.delete();
        }

        assertFalse(file.exists());
        GameRepository repository = new GameFileRepository(file);
        repository.save(game);
        repository.save(game);
        repository.save(game);

        List<Game> all = repository.findAll();
        assertEquals(3, all.size());
        all.forEach(found -> {
            assertEquals(game.getId(), found.getId());
            assertEquals(game.getPlayer().getName(), found.getPlayer().getName());
            assertEquals(game.getPlayer().getExperience().getValue(), found.getPlayer().getExperience().getValue());
            assertEquals(game.getDate(), found.getDate());
            assertEquals(game.getFights().size(), found.getFights().size());
            var gameFightsIterator = game.getFights().iterator();
            var foundFightsIterator = game.getFights().iterator();
            while (gameFightsIterator.hasNext()) {
                Fight fightNext = gameFightsIterator.next();
                Fight foundNext = foundFightsIterator.next();
                assertEquals(fightNext.getPlayerHit(), foundNext.getPlayerHit());
                assertEquals(fightNext.getRival(), foundNext.getRival());
                assertEquals(fightNext.getRivalHit(), foundNext.getRivalHit());
                assertEquals(fightNext.getResult(), foundNext.getResult());
            }
        });

        file.delete();
        assertFalse(file.exists());
    }

    @Test
    void save() {
        File file = new File(".save");
        if (file.exists()) {
            file.delete();
        }

        assertFalse(file.exists());
        GameRepository repository = new GameFileRepository(file);
        Game game = new Game();
        repository.save(game);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        file.delete();
        assertFalse(file.exists());
    }
}