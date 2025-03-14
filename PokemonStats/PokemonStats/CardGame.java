import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class CardGame {

    private ArrayList<Card> deck; // The deck of cards
    private ArrayList<Card> hand; // The hand of cards
    private static final int TOTAL_CARDS = 60; // The total number of cards in the deck

    /**
     * Constructor for the CardGame class that initializes the deck and hand
     */
    public CardGame() {
        this.deck = new ArrayList<>(); // Creating an array list for the deck
        this.hand = new ArrayList<>(); // Creating an array list for the hand
    }

    /**
     * Adds 4 Pokemon cards AKA the (Charmander) then fills the rest
     * of the deck with the other cards at random
     * until the deck reaches the total card count
     */
    public void fillDeck() {
        deck.clear(); // Clearing the deck
        for (int count = 0; count < 4; count++) // Adding 4 Charmander cards to the deck
        {
            deck.add(new Charmander()); // Adding the Charmander card to the deck
        }
        Random random = new Random(); // Creating a random object
        while (deck.size() < TOTAL_CARDS) // Filling the rest of the deck with random cards
        {
            deck.add(random.nextBoolean() ? new Energy() : new RareCandy()); // Adding a random card to the deck
        }
    }

    /**
     * Draws a hand of 7 random cards from the deck
     * then removes each drawn card from the deck to make sure no dub in the hand
     */
    public void drawHand() {
        Random random = new Random(); // Creating a random object
        hand.clear(); // Clearing the hand
        for (int i = 0; i < 7; i++) // Drawing 7 cards
        {
            int index = random.nextInt(deck.size()); // Getting a random index
            hand.add(deck.remove(index)); // Adding the card to the hand and removing it from the deck
        }
    }

    /**
     * Checks if the drawn hand contains at least one Pokemon card
     *
     * @return true if there is at least one Pokemon card in the hand otherwise
     *         false
     */
    public boolean containsPokemon() {
        for (Card card : hand) // Looping through the hand
        {
            if (card instanceof Pokemon) // Checking if the card is a Pokemon card
            {
                return true; // Returning true if there is a Pokemon card
            }
        }
        return false; // Returning false if there is no Pokemon card
    }

    /**
     * Runs simulations with varying numbers of Pokémon cards from 1 to 9
     */
    public void run() {
        for (int numPokemonCards = 1; numPokemonCards <= 9; numPokemonCards++) // Loop through the num of Pokemon cards

        {
            simulateTrials(numPokemonCards, 10000); // Running the simulation
        }
    }

    /**
     * Monte Carlo simulation used to determine the odds of drawing a Pokemon
     * card in the initial hand and tracks the distribution of rare Candy cards in
     * the prize deck
     *
     * @param numPokemon The number of Pokemon cards that need to be included in the
     *                   deck
     * @param trialCount The number of trials to run in the simulation
     */
    public void simulateTrials(int numPokemon, int trialCount) {
        int reshuffleCounter = 0; // Counter for the number of reshuffles
        int successfulDraws = 0; // Counter for the number of successful draws
        double[] candyOdds = new double[7]; // Array to store the occurrences of each possible count of Rare Candy cards
        Arrays.fill(candyOdds, 0.0); // Filling the array with 0.0

        for (int trial = 0; trial < trialCount; trial++) {
            setupDeckWithPokemon(numPokemon); // Setting up the deck with the Pokemon cards
            int reshuffles = 0; // Counter for the number of reshuffles

            drawHand(); // Drawing a hand

            if (containsPokemon()) {
                successfulDraws++; /// Incrementing the successful draw counter
            } else {
                while (!containsPokemon()) {
                    reshuffles++; // Incrementing the reshuffle counter
                    setupDeckWithPokemon(numPokemon); // Setting up the deck with the Pokemon cards
                    drawHand(); // Drawing a hand
                }
            }

            reshuffleCounter += reshuffles; // Adding the reshuffles to the reshuffle counter
            trackRareCandyOccurrences(candyOdds); // Tracking the rare candy occurrences
        }

        showSimulationResults(numPokemon, reshuffleCounter, successfulDraws, trialCount, candyOdds); // Show sim results
    }

    /**
     * Fills the deck with a set number of Pokemon cards then fills
     * the rest of the deck with random trainer rare candy and energy cards
     *
     * @param pokemonCount The number of Pokemon cards to include in the deck
     */
    private void setupDeckWithPokemon(int pokemonCount) {
        deck.clear(); // Clearing the deck
        for (int i = 0; i < pokemonCount; i++) // Adding the Pokemon cards to the deck
        {
            deck.add(new Charmander()); // Adding the Charmander card to the deck
        }
        Random rand = new Random(); // Creating a random object
        while (deck.size() < TOTAL_CARDS) {
            deck.add(rand.nextBoolean() ? new Energy() : new RareCandy()); // Adding a random card to the deck
        }
    }

    /**
     * Track the number of rare candy cards in the monte simulation prize deck of 6
     * cards
     * increments the count in the corresponding index of the candyProbabilities
     * array
     *
     * @param candyProbabilities Array to store the occurrences of each possible
     *                           count of Rare Candy cards (0-6)
     */
    private void trackRareCandyOccurrences(double[] candyProbabilities) {
        Random rand = new Random(); // Creating a random object
        int rareCandyCount = 0; // Counter for the number of rare candies

        ArrayList<Card> prizeDeck = new ArrayList<>(); // Creating an array list for the prize deck
        for (int i = 0; i < 6; i++) // Filling the prize deck with 6 cards
        {
            int index = rand.nextInt(deck.size()); // Getting a random index
            Card cardDrawn = deck.remove(index); // Removing the card from the deck
            prizeDeck.add(cardDrawn); // Adding the card to the prize deck

            if (cardDrawn instanceof RareCandy) // Checking if the card is a rare candy
            {
                rareCandyCount++; // Incrementing the rare candy counter
            }
        }

        for (Card card : prizeDeck) // Adding the cards back to the deck
        {
            deck.add(card); // Adding the card back to the deck
        }

        candyProbabilities[rareCandyCount]++; // Incrementing the rare candy count
    }

    /**
     * The results are to be displayed of the simulation. It includes the average
     * number of reshuffles
     * the prob of drawing a Pokemon in the initial hand and the distribution of
     * Rare Candy cards in the prize deck
     *
     * @param pokemonCount
     * @param reshuffleTotal
     * @param successCount
     * @param totalTrials
     * @param candyProbabilities
     */
    private void showSimulationResults(int pokemonCount, int reshuffleTotal, int successCount, int totalTrials, // Displaying
                                                                                                                // results

            double[] candyProbabilities) {
        double avgReshuffles = (double) reshuffleTotal / totalTrials; // Calculating the average number of reshuffles
        double successPercentage = (double) successCount / totalTrials * 100.0; // Calculating the success percentage

        // Displaying the results
        System.out.println("====================================");
        System.out.printf(" Results for Deck with %d Pokémon:\n", pokemonCount); // Displaying the results
        System.out.println("====================================");

        // Display the reshuffle and success rate
        System.out.printf("   Average Number of Reshuffles: %.2f\n", avgReshuffles); // Shows avg num of reshuffles
        System.out.printf("   Probability of Drawing a Pokémon in Initial Hand: %.2f%%\n", successPercentage); // Shows
                                                                                                               // success
                                                                                                               // %

        System.out.println("\n   Rare Candy Distribution in Prize Deck:"); // Displaying the rare candy distribution
        System.out.println("   ---------------------------------------");

        // Display the odds of getting a rare candies
        for (int count = 0; count < candyProbabilities.length; count++) // Looping through the rare candy probabilities
        {
            candyProbabilities[count] = (candyProbabilities[count] / totalTrials) * 100.0; // Calc rare candy prob
            System.out.printf("   - %d Rare Candies: %.2f%% chance\n", count, candyProbabilities[count]); // Shows rare
                                                                                                          // candy prob
        }
        System.out.println("====================================\n");
    }

}
