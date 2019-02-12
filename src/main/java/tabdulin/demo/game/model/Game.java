package tabdulin.demo.game.model;

import tabdulin.demo.game.fight.Fight;
import tabdulin.demo.game.player.Player;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Game implements Serializable {
    private String id;
    private LocalDateTime date;
    private Player player;
    private List<Fight> fights;

    public Game() {
        this(null);
    }

    public Game(String name) {
        this.id = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.fights = new LinkedList<>();
        this.player = new Player(name);
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addFight(Fight fight) {
        this.fights.add(fight);
        switch (fight.getResult()) {
            case LOSS:
                player.getExperience().loose();
                break;
            case VICTORY:
                player.getExperience().gain();
                break;
        }
    }

    public List<? extends Fight> getFights() {
        return fights;
    }

}
