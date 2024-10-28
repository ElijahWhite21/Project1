public class WaterEnergy extends Energy {

    public WaterEnergy() {
        super("Water");
    }

    @Override
    public void play() {
        System.out.println("Playing Water Energy. Provides Water-type energy.");
    }
}
