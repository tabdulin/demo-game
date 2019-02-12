package tabdulin.demo.game.fight;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static tabdulin.demo.game.fight.Fight.Result.*;
import static tabdulin.demo.game.fight.Hit.*;

class RockScissorsPaperGameServiceTest {
    private RockScissorsPaperGameService service = new RockScissorsPaperGameService();

    @ParameterizedTest
    @MethodSource("combinations")
    void fight(Fight.Result result, Hit playerHit, Hit rivalHit) {
        assertEquals(result, service.fight(playerHit, rivalHit));
    }

    static Stream<Arguments> combinations() {
        return Stream.of(
                arguments(VICTORY, SCISSORS, PAPER),
                arguments(VICTORY, ROCK, SCISSORS),
                arguments(VICTORY, PAPER, ROCK),
                arguments(DRAW, SCISSORS, SCISSORS),
                arguments(DRAW, ROCK, ROCK),
                arguments(DRAW, PAPER, PAPER),
                arguments(LOSS, SCISSORS, ROCK),
                arguments(LOSS, ROCK, PAPER),
                arguments(LOSS, PAPER, SCISSORS)
        );
    }

}