package tabdulin.demo.game.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tabdulin.demo.game.fight.FightFactory;
import tabdulin.demo.game.fight.Hit;
import tabdulin.demo.game.fight.Rival;
import tabdulin.demo.game.model.Game;
import tabdulin.demo.game.model.GameOption;
import tabdulin.demo.game.model.GameStartMode;
import tabdulin.demo.game.player.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.function.BiConsumer;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class ConsoleServiceTest {

    private void test(BiConsumer<ConsoleService, OutputStream> function) {
        test("", function);
    }

    private void test(String input, BiConsumer<ConsoleService, OutputStream> function) {
        try (ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ConsoleService consoleService = new ConsoleService(in, out);
            function.accept(consoleService, out);
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    void welcome() {
        test("", (consoleService, output) -> {
            consoleService.welcome();
            assertEquals("Welcome to Demo Luck Fight game!\n", output.toString());
        });
    }

    @Test
    void goodbye() {
        test("", (consoleService, outputStream) -> {
           consoleService.goodbye();
           assertEquals("Goodbye and come back ;-)!\n", outputStream.toString());
        });
    }

    @Test
    void selectGameMode() {
        test("1", (consoleService, outputStream) -> {
            assertEquals(GameStartMode.NEW_GAME, consoleService.selectGameMode());
        });
    }

    @Test
    void selectInvalidGameMode() {
        test("3\n1\n", (consoleService, outputStream) -> {
            assertEquals(GameStartMode.NEW_GAME, consoleService.selectGameMode());
        });
    }

    @Test
    void enterPlayerName() {
        final String PLAYER = "Player";
        test(PLAYER + "\n", (consoleService, outputStream) -> {
            assertEquals(PLAYER, consoleService.enterPlayerName());
        });
    }

    @Test
    void selectGameOption() {
        Player player = new Player("Superman");
        test("1\n", (consoleService, outputStream) -> {
            Assertions.assertEquals(GameOption.EXPLORE_FOR_THE_FIGHT, consoleService.selectGameOption(player));
        });
        test("2\n", (consoleService, outputStream) -> {
            Assertions.assertEquals(GameOption.EXIT_AND_SAVE, consoleService.selectGameOption(player));
        });
        test("3\n", (consoleService, outputStream) -> {
            Assertions.assertEquals(GameOption.EXIT_WITHOUT_SAVING, consoleService.selectGameOption(player));
        });
    }
    
    @Test
    void selectGame() {
        Game akuna = new Game("Akuna");
        Game matata = new Game("Matata");

        test("1\n", (consoleService, outputStream) -> {
            Game game = consoleService.selectGame(asList(akuna, matata));
            assertEquals(akuna.getId(), game.getId());
        });
    }

    @Test
    void noSavedGames() {
        test("", (consoleService, outputStream) -> {
            consoleService.noSavedGames();

            assertEquals("There is no saved games. Creating a new one.\n", outputStream.toString());
        });
    }

    @Test
    void showFightResults() {
        test("", (consoleService, outputStream) -> {
            consoleService.showFightResults(FightFactory.INSTANCE.createFight(Hit.SCISSORS, Rival.RICHARD_FEYNMAN, Hit.PAPER));
            assertEquals("It's a victory :-). You gain experience by +1.\n" +
                    "Your rival Richard Feynman hit with Paper.\n\n", outputStream.toString());
        });

        test("", (consoleService, outputStream) -> {
            consoleService.showFightResults(FightFactory.INSTANCE.createFight(Hit.PAPER, Rival.RICHARD_FEYNMAN, Hit.SCISSORS));
            assertEquals("It's a loss :-(. You loose experience by -1.\n" +
                    "Your rival Richard Feynman hit with Scissors.\n\n", outputStream.toString());
        });

        test("", (consoleService, outputStream) -> {
            consoleService.showFightResults(FightFactory.INSTANCE.createFight(Hit.PAPER, Rival.RICHARD_FEYNMAN, Hit.PAPER));
            assertEquals("It's a draw :-|.\n" +
                    "Your rival Richard Feynman hit with Paper.\n\n", outputStream.toString());
        });
    }
}