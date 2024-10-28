public class Energy extends Card {
    public Energy(String name) {
        super(name);
    }

    @Override
    public void play() {
        System.out.println("Energy card played: " + getName());
    }
}
