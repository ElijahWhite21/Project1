import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class CardGame {

    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private static final int TOTAL_CARDS = 60;

    public CardGame() {
        this.deck = new ArrayList<>();
        this.hand = new ArrayList<>();
    }

    /**
     * Adds 4 Pokemon cards AKA the (Charmander) then fills the rest
     * of the deck with the other cards at random
     * until the deck reaches the total card count
     */
    public void fillDeck() {
        deck.clear();
        for (int count = 0; count < 4; count++) {
            deck.add(new Charmander());
        }
        Random random = new Random();
        while (deck.size() < TOTAL_CARDS) {
            deck.add(random.nextBoolean() ? new Energy() : new RareCandy());
        }
    }

    /**
     * Draws a hand of 7 random cards from the deck
     * then removes each drawn card from the deck to make sure no dub in the hand
     */
    public void drawHand() {
        Random random = new Random();
        hand.clear();
        for (int i = 0; i < 7; i++) {
            int index = random.nextInt(deck.size());
            hand.add(deck.remove(index));
        }
    }

    /**
     * Checks if the drawn hand contains at least one Pokemon card
     *
     * @return true if there is at least one Pokemon card in the hand otherwise
     *         false
     */
    public boolean containsPokemon() {
        for (Card card : hand) {
            if (card instanceof Pokemon) {
                return true;
            }
        }
        return false;
    }

    /**
     * Runs simulations with varying numbers of Pokémon cards from 1 to 9
     */
    public void run() {
        for (int numPokemonCards = 1; numPokemonCards <= 9; numPokemonCards++) {
            simulateTrials(numPokemonCards, 10000);
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
        int reshuffleCounter = 0;
        int successfulDraws = 0;
        double[] candyOdds = new double[7];
        Arrays.fill(candyOdds, 0.0);

        for (int trial = 0; trial < trialCount; trial++) {
            setupDeckWithPokemon(numPokemon);
            int reshuffles = 0;

            drawHand();

            if (containsPokemon()) {
                successfulDraws++;
            } else {
                while (!containsPokemon()) {
                    reshuffles++;
                    setupDeckWithPokemon(numPokemon);
                    drawHand();
                }
            }

            reshuffleCounter += reshuffles;
            trackRareCandyOccurrences(candyOdds);
        }

        showSimulationResults(numPokemon, reshuffleCounter, successfulDraws, trialCount, candyOdds);
    }

    /**
     * Fills the deck with a set number of Pokemon cards then fills
     * the rest of the deck with random trainer rare candy and energy cards
     *
     * @param pokemonCount The number of Pokemon cards to include in the deck
     */
    private void setupDeckWithPokemon(int pokemonCount) {
        deck.clear();
        for (int i = 0; i < pokemonCount; i++) {
            deck.add(new Charmander());
        }
        Random rand = new Random();
        while (deck.size() < TOTAL_CARDS) {
            deck.add(rand.nextBoolean() ? new Energy() : new RareCandy());
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
        Random rand = new Random();
        int rareCandyCount = 0;

        ArrayList<Card> prizeDeck = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int index = rand.nextInt(deck.size());
            Card cardDrawn = deck.remove(index);
            prizeDeck.add(cardDrawn);

            if (cardDrawn instanceof RareCandy) {
                rareCandyCount++;
            }
        }

        for (Card card : prizeDeck) {
            deck.add(card);
        }

        candyProbabilities[rareCandyCount]++;
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
    private void showSimulationResults(int pokemonCount, int reshuffleTotal, int successCount, int totalTrials,
            double[] candyProbabilities) {
        double avgReshuffles = (double) reshuffleTotal / totalTrials;
        double successPercentage = (double) successCount / totalTrials * 100.0;

        System.out.println("====================================");
        System.out.printf(" Results for Deck with %d Pokémon:\n", pokemonCount);
        System.out.println("====================================");

        // Display the reshuffle and success rate
        System.out.printf("   Average Number of Reshuffles: %.2f\n", avgReshuffles);
        System.out.printf("   Probability of Drawing a Pokémon in Initial Hand: %.2f%%\n", successPercentage);

        System.out.println("\n   Rare Candy Distribution in Prize Deck:");
        System.out.println("   ---------------------------------------");

        // Display the odds of getting a rare candies
        for (int count = 0; count < candyProbabilities.length; count++) {
            candyProbabilities[count] = (candyProbabilities[count] / totalTrials) * 100.0;
            System.out.printf("   - %d Rare Candies: %.2f%% chance\n", count, candyProbabilities[count]);
        }
        System.out.println("====================================\n");
    }

}
