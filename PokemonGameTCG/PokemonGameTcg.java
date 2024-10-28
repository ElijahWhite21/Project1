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
    private List<Card> deck;
    private Player player1;
    private Player player2;
    private Scanner scanner;
    private List<Pokemon> bench;

    /**
     * Initializes the game, setting up the deck, players, and initial hands.
     */
    public PokemonGameTCG() {
        deck = new ArrayList<>();
        scanner = new Scanner(System.in);

        initializeDeck();

        System.out.println("Enter name for player 1: ");
        player1 = new Player(scanner.nextLine());
        System.out.println("Enter name for player 2: ");
        player2 = new Player(scanner.nextLine());

        dealInitialHands();
        dealPrizeCards(); // Deal prize cards after drawing initial hands
    }

    /**
     * Initializes the deck with 20 Pokémon cards and 40 Energy cards, then shuffles
     * it.
     */
    private void initializeDeck() {
        deck.clear();

        // Add 20 Pokémon cards
        for (int i = 0; i < 2; i++) {
            deck.add(new Pikachu());
            deck.add(new Charmander());
            deck.add(new MewTwo());
            deck.add(new Bulbasaur());
            deck.add(new Squirtle());
            deck.add(new Arcanine());
            deck.add(new JigglyPuff());
            deck.add(new Gengar());
            deck.add(new Snorlax());
            deck.add(new Zamazenta());

        }

        // Add 40 Energy cards, distributing 3 of each type for 10 types
        for (int i = 0; i < 3; i++) {
            deck.add(new Energy("Electric Energy"));
            deck.add(new Energy("Fire Energy"));
            deck.add(new Energy("Water Energy"));
            deck.add(new Energy("Grass Energy"));
            deck.add(new Energy("Psychic Energy"));
            deck.add(new Energy("Fighting Energy"));
            deck.add(new Energy("Darkness Energy"));
            deck.add(new Energy("Metal Energy"));
            deck.add(new Energy("Fairy Energy"));
            deck.add(new Energy("Dragon Energy"));
        }
        // Adding 10 Trainer cards, distributing 2 of each type for 5 types
        for (int i = 0; i < 5; i++) {
            deck.add(new ProfessorsResearch());
            // deck.add(new Marnie());
            deck.add(new BossOrders());
            // deck.add(new PokemonCommunication());
            // deck.add(new SwitchTrainer());
        }

        if (deck.size() == 60) {
            System.out.println("Deck is successfully initialized with 60 card");
        } else {
            System.out.println("Deck initialization error: " + deck.size() + " cards found.");
        }

        shuffleDeck();
    }

    /**
     * Shuffles the deck randomly using the Collections shuffle method.
     */
    private void shuffleDeck() {
        Collections.shuffle(deck, new Random());
    }

    /**
     * Deals an initial hand of 7 cards to each player.
     */
    private void dealInitialHands() {
        for (int i = 0; i < 7; i++) {
            drawCard(player1);
            drawCard(player2);
        }

        System.out.println("\n" + player1.getName() + "'s hand:");
        displayPlayerHand(player1);

        System.out.println("\n" + player2.getName() + "'s hand:");
        displayPlayerHand(player2);
    }

    /**
     * Deals 6 prize cards to each player.
     */
    private void dealPrizeCards() {
        // Give each player 6 prize cards from the deck
        for (int i = 0; i < 6; i++) {
            if (!deck.isEmpty()) {
                player1.addPrizeCard(deck.remove(0));
                player2.addPrizeCard(deck.remove(0));
            }
        }
    }

    /**
     * Displays the player's current hand.
     * 
     * @param player The player whose hand will be displayed.
     */
    private void displayPlayerHand(Player player) {
        List<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i).getName());
        }
    }

    /**
     * Handles the logic for a single player's turn, allowing them to take various
     * actions.
     * 
     * @param currentPlayer the player taking the turn
     * @param opponent      the opposing player
     */
    private void playerTurn(Player currentPlayer, Player opponent) {
        System.out.println("\n" + currentPlayer.getName() + "'s turn:");

        drawCard(currentPlayer);

        boolean turnActive = true;
        boolean energyAttached = false;

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

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayPlayerHand(currentPlayer);
                    break;
                case 2:
                    playPokemon(currentPlayer);
                    break;
                case 3:
                    playTrainer(currentPlayer, opponent);
                    break;
                case 4:
                    if (!energyAttached) {
                        attachEnergy(currentPlayer);
                        energyAttached = true;
                    } else {
                        System.out.println("You've already attached energy this turn.");
                    }
                    break;
                case 5:
                    boolean pokemonEliminated = currentPlayer.attack(opponent);
                    if (pokemonEliminated) {
                        currentPlayer.claimPrizeCard();
                        System.out.println(currentPlayer.getName() + " has claimed a prize card.");
                        System.out.println(
                                currentPlayer.getName() + " has " + currentPlayer.getRemainingPrizeCards()
                                        + " prize cards remaining.");
                        if (currentPlayer.getRemainingPrizeCards() == 0) {
                            System.out
                                    .println(currentPlayer.getName() + " wins the game by collecting all prize cards!");
                            return;
                        }
                    }
                    turnActive = false; // End turn after attack
                    pokemonEliminated = false;
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
     * 
     * @param player The player playing the Pokémon
     */
    private void playPokemon(Player player) {
        System.out.println("Choose a Pokémon to play:");
        List<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) instanceof Pokemon) {
                System.out.println((i + 1) + ". " + hand.get(i).getName());
            }
        }

        int choice = scanner.nextInt() - 1;
        if (choice >= 0 && choice < hand.size() && hand.get(choice) instanceof Pokemon) {
            Pokemon selectedPokemon = (Pokemon) hand.get(choice);
            player.playCard(selectedPokemon);
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
    private void playTrainer(Player player, Player opponent) {
        System.out.println("Choose a Trainer card to play:");
        List<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {

            if (hand.get(i) instanceof Trainer) {
                System.out.println((i + 1) + ". " + hand.get(i).getName());
            }
        }

        int choice = scanner.nextInt() - 1;
        if (choice >= 0 && choice < hand.size() && hand.get(choice) instanceof Trainer) {
            Trainer trainerCard = (Trainer) hand.remove(choice);

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
        this.bench.add(pokemon);
    }

    /**
     * Allows the player to attach an energy card to their active Pokémon.
     * 
     * @param player The player attaching the energy card.
     */
    private void attachEnergy(Player player) {
        System.out.println("Choose an Energy card to attach:");
        List<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) instanceof Energy) {
                System.out.println((i + 1) + ". " + hand.get(i).getName());
            }
        }

        int choice = scanner.nextInt() - 1;
        if (choice >= 0 && choice < hand.size() && hand.get(choice) instanceof Energy) {
            Energy energyCard = (Energy) hand.remove(choice);
            player.attachEnergy(energyCard);
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
        if (!deck.isEmpty()) {
            Card card = deck.remove(0);
            player.drawCard(card);
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
        boolean gameRunning = true;

        while (gameRunning) {
            playerTurn(player1, player2);
            if (checkWinCondition(player1)) {
                gameRunning = false;
                break;
            }

            playerTurn(player2, player1);
            if (checkWinCondition(player2)) {
                gameRunning = false;
                break;
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
    private void retreatPokemon(Player player) {
        if (player.getActivePokemon() == null) {
            System.out.println("No active Pokémon to retreat!");
            return;
        }

        System.out.println("Choose a Pokémon from your hand to make active:");
        List<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) instanceof Pokemon) {
                System.out.println((i + 1) + ". " + hand.get(i).getName());
            }
        }

        int choice = scanner.nextInt() - 1;
        if (choice >= 0 && choice < hand.size() && hand.get(choice) instanceof Pokemon) {
            Pokemon newActivePokemon = (Pokemon) hand.get(choice);
            player.retreatActivePokemon(newActivePokemon);
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
        PokemonGameTCG game = new PokemonGameTCG();
        game.startGame();
    }
}
