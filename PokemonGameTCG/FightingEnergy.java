public class FightingEnergy extends Energy {
    public FightingEnergy() {
        super("Fighting");
    }

    @Override
    public void play() {
        System.out.println("Playing Fighting Energy. Provides Fighting-type energy.");
    }
}
