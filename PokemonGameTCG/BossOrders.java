public class BossOrders extends Trainer {
    public BossOrders() {
        super("Boss's Orders", "Switch opponent's active Pokémon with a benched Pokémon.");
    }

    @Override
    public void play(Player opponent, Player player) {
        System.out.println("Using Boss's Orders!");

        if (opponent.hasBenchedPokemon()) {
            opponent.switchActiveWithBenched(); // Calls the opponent's switch method
            System.out.println("Switched opponent's active Pokémon with a benched Pokémon.");
        } else {
            System.out.println("Opponent has no benched Pokémon to switch.");
        }
    }
}
