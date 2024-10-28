public abstract class Trainer extends Card {

    private String effect;

    public Trainer(String name, String effect) {
        super(name);
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    // Default play method that calls the two-player play method
    @Override
    public void play() {
        // The default play method does nothing unless a specific player-opponent method
        // is used.
    }

    // Abstract method that subclasses must implement
    public abstract void play(Player player, Player opponent);
}
