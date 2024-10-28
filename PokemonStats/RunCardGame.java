public class RunCardGame {
    public static void main(String[] args) {
        CardGame pokeGame = new CardGame();
        pokeGame.run();

        // Run of the Monte Carlo or simulateTrials method
        int trials = 10000;
        for (int i = 1; i <= 60; i++) {
            pokeGame.simulateTrials(i, trials);
        }
    }
}
