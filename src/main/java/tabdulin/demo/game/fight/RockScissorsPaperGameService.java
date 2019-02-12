package tabdulin.demo.game.fight;

/**
 * Service for providing fight algorithm in Rock-Scissors-Paper game
 */
class RockScissorsPaperGameService {
    /**
     * Defines game result for the player
     *
     * @param playerHit Player's hit
     * @param rivalHit Player's rival's hit
     *
     * @return either Victory, Loss or Draw
     * @throws IllegalStateException if invalid hits are passed
     */
    public Fight.Result fight(Hit playerHit, Hit rivalHit) {
        if (playerHit == rivalHit) return Fight.Result.DRAW;
        switch (playerHit) {
            case PAPER:
                if (rivalHit == Hit.ROCK) return Fight.Result.VICTORY;
                else return Fight.Result.LOSS;
            case ROCK:
                if (rivalHit == Hit.SCISSORS) return Fight.Result.VICTORY;
                else return Fight.Result.LOSS;
            case SCISSORS:
                if (rivalHit == Hit.PAPER) return  Fight.Result.VICTORY;
                else return Fight.Result.LOSS;
        }

        throw new IllegalStateException("Invalid hits: " + playerHit + " vs " + rivalHit);
    }
}
