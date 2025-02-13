import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokemonMulliganSimulation {

    /**
     * Main method to run the simulation and write results to a CSV file.
     * 
     * @param args
     *
     */
    public static void main(String[] args) {
        // Output file path
        String filePath = "mulligan_results.csv"; // Specify the output file path

        // Create the CSV file and write the header
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { // BufferedWriter for efficient
                                                                                     // writing
            writer.write("Pokémon cards,Mulligan chance (%)\n"); // Header for CSV

            // Run simulations for decks with 1 to 60 Pokémon cards
            for (int pokemonCount = 1; pokemonCount <= 60; pokemonCount++) {
                double mulliganChance = simulateMulligan(pokemonCount); // Simulate the mulligan chance
                writer.write(String.format("%d,%.2f\n", pokemonCount, mulliganChance)); // Write results to CSV
            }

            System.out.println("Simulation results written to " + filePath); // Confirmation message

        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage()); // Error handling
        }
    }

    /**
     * Simulates the chance of getting a hand without a Pokémon card.
     * 
     * @param pokemonCount
     * @return
     */
    public static double simulateMulligan(int pokemonCount) {
        int mulligans = 0; // Counter for mulligans
        int numSimulations = 10000; // Define the number of simulations

        for (int i = 0; i < numSimulations; i++) {
            List<String> deck = new ArrayList<>(); // Create a new deck

            // Add Pokémon cards
            for (int j = 0; j < pokemonCount; j++) {
                deck.add("Pokemon"); // Add Pokémon cards to the deck
            }

            // Add Energy cards
            for (int j = pokemonCount; j < 60; j++) {
                deck.add("Energy"); // Add Energy cards to the deck
            }

            // Shuffle the deck
            Collections.shuffle(deck); // Shuffle the deck to randomize the order

            // Draw a hand of 7 cards
            List<String> hand = new ArrayList<>(deck.subList(0, 7)); // Draw the first 7 cards

            // Check if the hand contains a Pokémon card
            if (!hand.contains("Pokemon")) { // If no Pokémon card is found in the hand
                mulligans++; // Increment the mulligan counter if no Pokémon card is found
            }
        }

        // Calculate the mulligan chance as a percentage
        return (double) mulligans / numSimulations * 100; // Return the mulligan chance
    }
}
