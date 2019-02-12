package tabdulin.demo.game;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import tabdulin.demo.game.application.*;
import tabdulin.demo.game.fight.Hit;
import tabdulin.demo.game.model.Game;
import tabdulin.demo.game.model.GameOption;
import tabdulin.demo.game.model.GameStartMode;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GameApplicationTest {
    private final String PLAYER_NAME = "Player";

    @Test
    void playWithoutSaving() {
        final int FIGHTS_NUMBER = 3;
        ApplicationFactory applicationFactory = Mockito.spy(ApplicationFactory.INSTANCE);
        UserInterfaceService userInterfaceService = Mockito.mock(UserInterfaceService.class);

        when(applicationFactory.getConsoleService()).thenReturn(userInterfaceService);

        when(userInterfaceService.selectGameMode()).thenReturn(GameStartMode.NEW_GAME);
        when(userInterfaceService.enterPlayerName()).thenReturn(PLAYER_NAME);
        when(userInterfaceService.selectGameOption(any())).then(new Answer() {
            private int fights = FIGHTS_NUMBER;
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                while (fights-- > 0) {
                    return GameOption.EXPLORE_FOR_THE_FIGHT;
                }

                return GameOption.EXIT_WITHOUT_SAVING;
            }
        });
        when(userInterfaceService.selectHit(any())).thenReturn(Hit.SCISSORS);

        GameApplication application = new GameApplication(userInterfaceService);
        application.play();

        Game game = application.getGame();
        assertEquals("Player", game.getPlayer().getName());
        assertEquals(FIGHTS_NUMBER, game.getFights().size());
    }

    @Test
    void resumeWithNoSavedGames() {
        ApplicationFactory applicationFactory = Mockito.spy(ApplicationFactory.INSTANCE);
        UserInterfaceService userInterfaceService = mock(UserInterfaceService.class);
        GameRepository gameRepository = mock(GameRepository.class);

        when(applicationFactory.getConsoleService()).thenReturn(userInterfaceService);
        when(applicationFactory.getGameRepository()).thenReturn(gameRepository);

        when(userInterfaceService.selectGameMode()).thenReturn(GameStartMode.RESUME);
        when(gameRepository.findAll()).thenReturn(new ArrayList<>(0));
        when(userInterfaceService.enterPlayerName()).thenReturn(PLAYER_NAME);
        when(userInterfaceService.selectGameOption(any())).thenReturn(GameOption.EXIT_WITHOUT_SAVING);
        verify(gameRepository, never()).save(any());

        GameApplication application = new GameApplication(userInterfaceService);
        application.play();

        Game game = application.getGame();
        assertEquals(PLAYER_NAME, game.getPlayer().getName());
        assertEquals(0, game.getFights().size());
    }
}