public class FireEnergy extends Energy {
    public FireEnergy() {
        super("Fire");
    }

    @Override
    public void play() {
        System.out.println("Playing Fire Energy. Provides Fire-type energy.");
    }
}