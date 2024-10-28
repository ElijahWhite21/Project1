public class GrassEnergy extends Energy {
    public GrassEnergy() {
        super("Grass");
    }

    @Override
    public void play() {
        System.out.println("Playing Grass Energy. Provides Grass-type energy.");
    }
}