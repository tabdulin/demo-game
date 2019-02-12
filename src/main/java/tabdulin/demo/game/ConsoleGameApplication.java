package tabdulin.demo.game;

import tabdulin.demo.game.application.ApplicationFactory;
import tabdulin.demo.game.application.GameApplication;

/**
 * Console mode of the game
 */
public class ConsoleGameApplication extends GameApplication {

    public ConsoleGameApplication() {
        super(ApplicationFactory.INSTANCE.getConsoleService());
    }

    public static void main(String... args) {
        ConsoleGameApplication application = new ConsoleGameApplication();
        application.play();
    }

}
