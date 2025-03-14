public class RunCardGame {
    public static void main(String[] args) {
        CardGame pokeGame = new CardGame(); // Creating a new CardGame object
        pokeGame.run(); // Running the CardGame object

        // Run of the Monte Carlo or simulateTrials method
        int trials = 10000; // Setting the number of trials to 10000

        for (int i = 1; i <= 60; i++) // Loop through the number of Pokemon cards
        {
            pokeGame.simulateTrials(i, trials); // Running the simulateTrials method
        }
    }
}
