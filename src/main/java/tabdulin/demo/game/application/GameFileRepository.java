package tabdulin.demo.game.application;

import tabdulin.demo.game.model.Game;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of GameRepository with a file as a storage
 * Use application factory to get instance
 *
 * @see ApplicationFactory
 */
class GameFileRepository implements GameRepository {
    private final File file;

    GameFileRepository(File file) {
        this.file = file;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot initialize game storage within file " + file.getAbsolutePath(), e);
        }
    }

    @Override
    public List<Game> findAll() {
        try {
            return Files.lines(file.toPath())
                    .map(line -> {
                        byte[] bytes = Base64.getDecoder().decode(line);
                        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
                            return (Game) ois.readObject();
                        } catch (Exception e) {
                            throw new RuntimeException("Games cannot be loaded from file " + file.getAbsolutePath(), e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Game game) {
        try (FileWriter writer = new FileWriter(file, true);
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream ous = new ObjectOutputStream(baos)){
            ous.writeObject(game);
            writer.write(Base64.getEncoder().encodeToString(baos.toByteArray()));
            writer.write("\n");
        } catch (Exception e) {
            throw new RuntimeException("Game cannt be saved into file " + file.getAbsolutePath(), e);
        }
    }
}
