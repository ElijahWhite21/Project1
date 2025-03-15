import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * PokemonGameTCG represents a simple Pokémon Trading Card Game simulator.
 * Players take turns drawing cards, playing Pokémon, attaching energy,
 * and attacking each other until one player collects all their prize cards.
 */
class PokemonGameTCG {
    private List<Card> deck; // The main deck of cards
    private Player player1; // Player 1
    private Player player2; // Player 2
    private Scanner scanner; // Scanner for user input
    private List<Pokemon> bench; // Bench for benched Pokémon

    /**
     * Initializes the game, setting up the deck, players, and initial hands.
     * The game starts by dealing 7 cards to each player and then dealing 6 prize
     * cards to each player.
     */
    public PokemonGameTCG() {
        deck = new ArrayList<>(); // Initialize the deck
        scanner = new Scanner(System.in); // Initialize the scanner

        initializeDeck(); // Initialize the deck with cards

        System.out.println("Enter name for player 1: ");
        player1 = new Player(scanner.nextLine()); // Create player 1
        System.out.println("Enter name for player 2: ");
        player2 = new Player(scanner.nextLine()); // Create player 2

        dealInitialHands(); // Deal initial hands to players
        dealPrizeCards(); // Deal prize cards after drawing initial hands
    }

    /**
     * Initializes the deck with 20 Pokémon cards and 40 Energy cards, then shuffles
     * it. The deck is then ready to be drawn from. The deck is a total of 60 cards.
     * The deck is initialized with 2 copies of each Pokémon card and 3 copies of
     * each Energy card.
     */
    private void initializeDeck() {
        deck.clear();

        // Add 20 Pokémon cards
        for (int i = 0; i < 2; i++) {
            deck.add(new Pikachu()); // Add 2 Pikachu cards
            deck.add(new Charmander()); // Add 2 Charmander cards
            deck.add(new MewTwo()); // Add 2 MewTwo cards
            deck.add(new Bulbasaur()); // Add 2 Bulbasaur cards
            deck.add(new Squirtle()); // Add 2 Squirtle cards
            deck.add(new Arcanine()); // Add 2 Arcanine cards
            deck.add(new JigglyPuff()); // Add 2 JigglyPuff cards
            deck.add(new Gengar()); // Add 2 Gengar cards
            deck.add(new Snorlax()); // Add 2 Snorlax cards
            deck.add(new Zamazenta()); // Add 2 Zamazenta cards

        }

        // Add 40 Energy cards, distributing 3 of each type for 10 types
        for (int i = 0; i < 3; i++) {
            deck.add(new Energy("Electric Energy")); // Add 3 Electric Energy cards
            deck.add(new Energy("Fire Energy")); // Add 3 Fire Energy cards
            deck.add(new Energy("Water Energy")); // Add 3 Water Energy cards
            deck.add(new Energy("Grass Energy")); // Add 3 Grass Energy cards
            deck.add(new Energy("Psychic Energy")); // Add 3 Psychic Energy cards
            deck.add(new Energy("Fighting Energy")); // Add 3 Fighting Energy cards
            deck.add(new Energy("Darkness Energy")); // Add 3 Darkness Energy cards
            deck.add(new Energy("Metal Energy")); // Add 3 Metal Energy cards
            deck.add(new Energy("Fairy Energy")); // Add 3 Fairy Energy cards
            deck.add(new Energy("Dragon Energy")); // Add 3 Dragon Energy cards
        }
        // Adding 10 Trainer cards, distributing 2 of each type for 5 types
        for (int i = 0; i < 5; i++) {
            deck.add(new ProfessorsResearch()); // adding professor's research
            deck.add(new BossOrders()); // adding boss orders
        }

        if (deck.size() == 60) // Check if the deck is properly initialized
        {
            System.out.println("Deck is successfully initialized with 60 card");
        } else {
            System.out.println("Deck initialization error: " + deck.size() + " cards found.");
        }

        shuffleDeck(); // Shuffle the deck
    }

    /**
     * Shuffles the deck randomly using the Collections shuffle method.
     */
    private void shuffleDeck() // Shuffle the deck
    {
        Collections.shuffle(deck, new Random()); // Shuffle the deck randomly
    }

    /**
     * Deals an initial hand of 7 cards to each player.
     */
    private void dealInitialHands() // Deal initial hands to players
    {
        for (int i = 0; i < 7; i++) // Deal 7 cards to each player
        {
            drawCard(player1); // Draw a card for player 1
            drawCard(player2); // Draw a card for player 2
        }

        System.out.println("\n" + player1.getName() + "'s hand:"); // Display player 1's hand
        displayPlayerHand(player1); // Display player 1's hand

        System.out.println("\n" + player2.getName() + "'s hand:"); // Display player 2's hand
        displayPlayerHand(player2); // Display player 2's hand
    }

    /**
     * Deals 6 prize cards to each player.
     */
    private void dealPrizeCards() {
        // Give each player 6 prize cards from the deck
        for (int i = 0; i < 6; i++) {
            if (!deck.isEmpty()) // If the deck is not empty
            {
                player1.addPrizeCard(deck.remove(0)); // Add a prize card to player 1
                player2.addPrizeCard(deck.remove(0)); // Add a prize card to player 2
            }
        }
    }

    /**
     * Displays the player's current hand. The player can choose to view their hand
     * at any time during their turn.
     * 
     * @param player The player whose hand will be displayed.
     */
    private void displayPlayerHand(Player player) // Display the player's hand
    {
        List<Card> hand = player.getHand(); // Get the player's hand
        for (int i = 0; i < hand.size(); i++) // Iterate over the cards in the hand
        {
            System.out.println((i + 1) + ". " + hand.get(i).getName());
        }
    }

    /**
     * Handles the logic for a single player's turn, allowing them to take various
     * actions. This includes drawing a card, playing Pokémon, playing Trainer
     * cards, attaching energy, attacking the opponent, retreating the active
     * Pokémon, and ending the turn.
     * 
     * @param currentPlayer the player taking the turn
     * @param opponent      the opposing player
     */
    private void playerTurn(Player currentPlayer, Player opponent) // Handle a player's turn
    {
        System.out.println("\n" + currentPlayer.getName() + "'s turn:");

        drawCard(currentPlayer); // Draw a card at the start of the turn

        boolean turnActive = true; // Flag to control the turn
        boolean energyAttached = false; // Flag to track if energy has been attached this turn

        while (turnActive) {
            System.out.println("---------------------------------");
            System.out.println("---------------------------------");
            System.out.println("\nChoose an action:");
            System.out.println("1. View hand");
            System.out.println("2. Play a Pokémon");
            System.out.println("3. Play a Trainer card");
            System.out.println("4. Attach an energy to a Pokémon");
            System.out.println("5. Attack opponent (ends turn)");
            System.out.println("6. Retreat active Pokémon");
            System.out.println("7. End turn");
            System.out.println("---------------------------------");
            System.out.println("---------------------------------");

            int choice = scanner.nextInt(); // Get the player's choice
            switch (choice) {
                case 1:
                    displayPlayerHand(currentPlayer); // Display the player's hand
                    break;
                case 2:
                    playPokemon(currentPlayer); // Call the playPokemon method here
                    break;
                case 3:
                    playTrainer(currentPlayer, opponent); // Call the playTrainer method here
                    break;
                case 4:
                    if (!energyAttached) {
                        attachEnergy(currentPlayer); // Call the attachEnergy method here
                        energyAttached = true; // Set the flag to true
                    } else {
                        System.out.println("You've already attached energy this turn.");
                    }
                    break;
                case 5:
                    boolean pokemonEliminated = currentPlayer.attack(opponent); // Call the attack method here
                    if (pokemonEliminated) {
                        currentPlayer.claimPrizeCard(); // Claim a prize card if the opponent's Pokémon is eliminated
                        System.out.println(currentPlayer.getName() + " has claimed a prize card.");
                        System.out.println(currentPlayer.getName() + " has " + currentPlayer.getRemainingPrizeCards()
                                + " prize cards remaining.");
                        if (currentPlayer.getRemainingPrizeCards() == 0) {
                            System.out
                                    .println(currentPlayer.getName() + " wins the game by collecting all prize cards!");
                            return;
                        }
                    }
                    turnActive = false; // End turn after attack
                    pokemonEliminated = false; // Reset the flag
                    break;
                case 6:
                    retreatPokemon(currentPlayer); // Call the retreat method here
                    break;
                case 7:
                    turnActive = false; // Manually end the turn
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    /**
     * Allows the player to play a Pokémon card from their hand
     * This method handles interactions that may affect both players, such as
     * "Boss's Orders"
     * 
     * @param player The player playing the Pokémon
     */
    private void playPokemon(Player player) {
        System.out.println("Choose a Pokémon to play:"); // Prompt the player to choose a Pokémon
        List<Card> hand = player.getHand(); // Get the player's hand
        for (int i = 0; i < hand.size(); i++) // Iterate over the cards in the hand
        {
            if (hand.get(i) instanceof Pokemon) // Check if the card is a Pokémon
            {
                System.out.println((i + 1) + ". " + hand.get(i).getName());
            }
        }

        int choice = scanner.nextInt() - 1; // Get the player's choice
        if (choice >= 0 && choice < hand.size() && hand.get(choice) instanceof Pokemon) // Check if the choice is valid
        {
            Pokemon selectedPokemon = (Pokemon) hand.get(choice); // Get the selected Pokémon
            player.playCard(selectedPokemon); // Play the Pokémon card
            System.out.println(selectedPokemon.getName() + " is now the active Pokémon.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    /**
     * Allows the player to play a Trainer card from their hand
     * This method handles interactions that may affect both players, such as
     * "Boss's Orders"
     *
     * @param player   The player playing the Trainer card
     * @param opponent The opposing player affected by certain Trainer cards
     */
    private void playTrainer(Player player, Player opponent) // Allow the player to play a Trainer card
    {
        System.out.println("Choose a Trainer card to play:"); // Prompt the player to choose a Trainer card
        List<Card> hand = player.getHand(); // Get the player's hand
        for (int i = 0; i < hand.size(); i++) // Iterate over the cards in the hand
        {

            if (hand.get(i) instanceof Trainer) // Check if the card is a Trainer
            {
                System.out.println((i + 1) + ". " + hand.get(i).getName());
            }
        }

        int choice = scanner.nextInt() - 1; // Get the player's choice
        if (choice >= 0 && choice < hand.size() && hand.get(choice) instanceof Trainer) // Check if the choice is valid
        {
            Trainer trainerCard = (Trainer) hand.remove(choice); // Get the selected Trainer card

            // Apply specific Trainer card effects based on the card type
            if (trainerCard instanceof BossOrders) {
                ((BossOrders) trainerCard).play(player, opponent); // For Boss's Orders, affect the opponent
            } else if (trainerCard instanceof ProfessorsResearch) {
                ((ProfessorsResearch) trainerCard).play(); // For Professor's Research, affect the player
            } else {
                trainerCard.play();
            }

            System.out.println(trainerCard.getName() + " has been played.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    /**
     * Checks if the player has any benched Pokémon.
     * 
     * @return true if there is at least one Pokémon on the bench.
     */
    public boolean hasBenchedPokemon() {
        return !bench.isEmpty();
    }

    /**
     * Adds a Pokémon to the bench.
     * 
     * @param pokemon The Pokémon to add to the bench.
     */
    public void addToBench(Pokemon pokemon) {
        this.bench.add(pokemon); // Add the Pokémon to the bench
    }

    /**
     * Allows the player to attach an energy card to their active Pokémon.
     * 
     * @param player The player attaching the energy card.
     */
    private void attachEnergy(Player player) {
        System.out.println("Choose an Energy card to attach:"); // Prompt the player to choose an Energy card
        List<Card> hand = player.getHand(); // Get the player's hand
        for (int i = 0; i < hand.size(); i++) // Iterate over the cards in the hand
        {
            if (hand.get(i) instanceof Energy) // Check if the card is an Energy
            {
                System.out.println((i + 1) + ". " + hand.get(i).getName());
            }
        }

        int choice = scanner.nextInt() - 1; // Get the player's choice
        if (choice >= 0 && choice < hand.size() && hand.get(choice) instanceof Energy) // Check if the choice is valid
        {
            Energy energyCard = (Energy) hand.remove(choice); // Get the selected Energy card
            player.attachEnergy(energyCard); // Attach the Energy card to the active Pokémon
        } else {
            System.out.println("Invalid choice.");
        }
    }

    /**
     * Draws a card from the deck and adds it to the player's hand.
     * 
     * @param player The player drawing the card.
     */
    private void drawCard(Player player) {
        if (!deck.isEmpty()) // If the deck is not empty
        {
            Card card = deck.remove(0); // Remove the top card from the deck
            player.drawCard(card); // Add the card to the player's hand
            System.out.println(player.getName() + " drew a card: " + card.getName());
        } else {
            System.out.println("The deck is empty. No card drawn.");
        }
    }

    /**
     * Starts the game loop, alternating turns between players until one wins.
     */
    public void startGame() {
        System.out.println("Starting Pokémon TCG game!");
        boolean gameRunning = true; // Flag to control the game loop

        while (gameRunning) {
            playerTurn(player1, player2); // Player 1's turn
            if (checkWinCondition(player1)) // Check if player 1 has won
            {
                gameRunning = false; // End the game loop
                break; // Exit the loop
            }

            playerTurn(player2, player1); // Player 2's turn
            if (checkWinCondition(player2)) // Check if player 2 has won
            {
                gameRunning = false; // End the game loop
                break; // Exit the loop
            }
        }
    }

    /**
     * Checks if the player has won the game by collecting all prize cards.
     * 
     * @param player The player to check.
     * @return True if the player has won, otherwise false.
     */
    private boolean checkWinCondition(Player player) {
        if (player.getRemainingPrizeCards() == 0) {
            System.out.println(player.getName() + " has won the game by collecting all prize cards!");
            return true;
        }
        return false;
    }

    /**
     * Allows the player to retreat their active Pokémon and select a new active
     * Pokémon
     * from their hand.
     * 
     * @param player The player retreating their active Pokémon.
     */
    private void retreatPokemon(Player player)// Allow the player to retreat their active Pokémon
    {
        if (player.getActivePokemon() == null) // If there is no active Pokémon
        {
            System.out.println("No active Pokémon to retreat!"); // Display an error message
            return; // Exit the method
        }

        System.out.println("Choose a Pokémon from your hand to make active:"); // Prompt the player to choose a Pokémon
        List<Card> hand = player.getHand(); // Get the player's hand
        for (int i = 0; i < hand.size(); i++) // Iterate over the cards in the hand
        {
            if (hand.get(i) instanceof Pokemon) // Check if the card is a Pokémon
            {
                System.out.println((i + 1) + ". " + hand.get(i).getName());
            }
        }

        int choice = scanner.nextInt() - 1; // Get the player's choice
        if (choice >= 0 && choice < hand.size() && hand.get(choice) instanceof Pokemon) // Check if the choice is valid
        {
            Pokemon newActivePokemon = (Pokemon) hand.get(choice); // Get the selected Pokémon
            player.retreatActivePokemon(newActivePokemon); // Retreat the active Pokémon
            System.out.println(player.getName() + " has retreated and made " + newActivePokemon.getName()
                    + " the new active Pokémon.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    /**
     * Main method to start the Pokémon TCG game.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        PokemonGameTCG game = new PokemonGameTCG(); // Create a new Pokémon TCG game
        game.startGame(); // Start the game
    }
}
