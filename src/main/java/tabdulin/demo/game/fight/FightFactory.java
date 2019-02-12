package tabdulin.demo.game.fight;

import tabdulin.demo.game.application.ApplicationFactory;

/**
 * Factory for creating fights.
 * Use application factory to get instance
 *
 * @see ApplicationFactory
 */
public enum FightFactory {
    INSTANCE;

    private RockScissorsPaperGameService rockScissorsPaperGameService;
    private RandomService randomService;

    FightFactory() {
        this.rockScissorsPaperGameService = new RockScissorsPaperGameService();
        this.randomService = new RandomService();
    }

    /**
     * Creates fight with a computed result against random rival's hit
     *
     * @param playerHit player's hit
     * @param rival player's rival
     *
     * @return fight
     * @see Fight
     */
    public Fight createFight(Hit playerHit, Rival rival) {
        Hit rivalHit = randomService.random(Hit.values());
        return createFight(playerHit, rival, rivalHit);
    }

    /**
     * Creates fight with a computed result against rival's hit
     *
     * @param playerHit player's hit
     * @param rival player's rival
     * @param rivalHit rival's hit
     *
     * @return fight
     * @see Fight
     */
    public Fight createFight(Hit playerHit, Rival rival, Hit rivalHit) {
        return new Fight(playerHit, rival, rivalHit, rockScissorsPaperGameService.fight(playerHit, rivalHit));
    }


    public Rival selectRandomRival() {
        return randomService.random(Rival.values());
    }
}
