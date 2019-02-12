package tabdulin.demo.game.application;

import tabdulin.demo.game.fight.Fight;
import tabdulin.demo.game.fight.Hit;
import tabdulin.demo.game.fight.Rival;
import tabdulin.demo.game.model.Game;
import tabdulin.demo.game.model.GameOption;
import tabdulin.demo.game.model.GameStartMode;
import tabdulin.demo.game.player.Player;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Console user interface implementation
 * Use application factory to get instance
 *
 * @see ApplicationFactory
 */
class ConsoleService implements UserInterfaceService {
    private final Scanner in;
    private final PrintStream out;

    ConsoleService(InputStream in, OutputStream out) {
        this.in = new Scanner(in);
        this.out = new PrintStream(out);
    }

    @Override
    public void welcome () {
        out.println("Welcome to Demo Luck Fight game!");
    }

    @Override
    public GameStartMode selectGameMode() {
        return selectConsoleOption(GameStartMode.values(),
                mode -> {
                    switch (mode) {
                        case NEW_GAME: return "New game";
                        case RESUME: return "Resume game";
                        default: throw new IllegalStateException("Invalid game mode: " + mode);
                    }
                },
                "Select mode:\n" );
    }

    @Override
    public String enterPlayerName() {
        out.print("Create new character.\n" +
                "Enter your name: ");
        String name = null;
        while (name == null || name.isBlank()) {
            name = in.nextLine();
        }

        out.println();
        return name;
    }

    @Override
    public void goodbye() {
        out.println("Goodbye and come back ;-)!");
    }

    @Override
    public GameOption selectGameOption(Player player) {
        return selectConsoleOption(GameOption.values(), option -> option.toString(),
                "%s, your experience is %d.\nPlease select next step:\n", player.getName(), player.getExperience().getValue());
    }

    @Override
    public Game selectGame(List<Game> games) {
        return selectConsoleOption(games.toArray(new Game[0]),
                game -> new StringBuilder()
                        .append(game.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                        .append(" ")
                        .append(game.getPlayer().getName())
                        .append(" (experience : ").append(game.getPlayer().getExperience().getValue()).append(")")
                        .toString(),
                "Select game to resume\n");
    }

    @Override
    public Hit selectHit(Rival rival) {
        return selectConsoleOption(Hit.values(),
                hit -> hit.toString(),
                "Your rival is %s. Good luck!\nSelect your hit:\n", rival);
    }

    @Override
    public void showFightResults(Fight fight) {
        switch (fight.getResult()) {
            case VICTORY:
                out.printf("It's a victory :-). You gain experience by +1.\n");
                break;
            case DRAW:
                out.printf("It's a draw :-|.\n");
                break;
            case LOSS:
                out.printf("It's a loss :-(. You loose experience by -1.\n");
                break;
        }
        out.printf("Your rival %s hit with %s.\n", fight.getRival(), fight.getRivalHit());
        out.println();
    }

    @Override
    public void noSavedGames() {
        out.println("There is no saved games. Creating a new one.");
    }

    private <OPTION> OPTION selectConsoleOption(OPTION[] options,
                                                Function<OPTION, String> optionTextMapper,
                                                String title,
                                                Object... titleArgs) {
        out.printf(title, titleArgs);
        for (int i = 0; i < options.length; i++) {
            out.printf("%d. %s\n", i + 1, optionTextMapper.apply(options[i]));
        }

        out.print("\nYour input: ");
        OPTION option = null;
        while (option == null) {
            String line = in.nextLine();
            try {
                Integer id = Integer.parseInt(line);
                if (id != null && id > 0 && id <= options.length) {
                    option = options[id - 1];
                    break;
                }
            } catch (NumberFormatException e) {
            }
            out.printf("You entered invalid input: %s. Please try again: ", line);
        }

        out.println();
        return option;
    }
}
