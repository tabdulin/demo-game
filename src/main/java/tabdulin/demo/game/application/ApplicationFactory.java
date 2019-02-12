package tabdulin.demo.game.application;

import tabdulin.demo.game.fight.FightFactory;

import java.io.File;

/**
 * Singleton factory to initialize services and factories
 */
public enum ApplicationFactory {
    INSTANCE;

    private UserInterfaceService consoleService;
    private GameRepository gameRepository;
    private GameService gameService;

    ApplicationFactory() {
        consoleService = new ConsoleService(System.in, System.out);
        gameRepository = new GameFileRepository(new File(".game"));
        gameService = new GameServiceImpl(gameRepository, FightFactory.INSTANCE);
    }

    public UserInterfaceService getConsoleService() {
        return consoleService;
    }

    public GameRepository getGameRepository() {
        return gameRepository;
    }

    public GameService getGameService() {
        return gameService;
    }
}
