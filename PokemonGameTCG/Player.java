import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name; // Name of the player
    private List<Card> hand; // Track cards in hand
    private List<Card> prizePile; // Track prize cards
    private List<Pokemon> bench = new ArrayList<>(); // Track benched Pokémon
    private Pokemon activePokemon;
    private List<Card> discardPile; // Track discarded cards
    private List<Card> deck; // Reference to the deck

    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor to initialize a player with a specified name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name; // Initialize the player's name
        this.hand = new ArrayList<>(); // Ensure hand is initialized here
        this.prizePile = new ArrayList<>(); // Ensure prizePile is initialized here
    }

    /**
     * @param card
     */
    public void drawCard(Card card) {
        hand.add(card); // Add card to initialized hand
    }

    /**
     * @param card
     */
    public void addPrizeCard(Card card) {
        prizePile.add(card); // Add prize card to initialized prizePile
    }

    /**
     * Claim a prize card from the prize pile and add it to the player's hand.
     */
    public void claimPrizeCard() // Claim a prize card
    {
        if (!prizePile.isEmpty()) // If there are prize cards remaining
        {
            Card prizeCard = prizePile.remove(0); // Remove the first prize card
            this.hand.add(prizeCard); // Add the prize card to the player's hand
            System.out.println(name + " claimed a prize card: " + prizeCard.getName());
        } else {
            System.out.println("No prize cards left to claim!");
        }
    }

    /**
     * @return
     */
    public int getRemainingPrizeCards() {
        return this.prizePile.size();
    }

    /**
     * @param The deck to draw cards from to fill the player's hand and prize pile
     *            with cards
     */
    public void drawPrizePile(List<Card> deck) {
        for (int i = 0; i < 6; i++) // Draw 6 prize cards
        {
            if (!deck.isEmpty()) // If the deck is not empty
            {
                this.prizePile.add(deck.remove(0)); // Remove the first card from the deck and add it to the prize pile
            }
        }
    }

    /**
     * @param The card to play from the player's hand
     */
    public void playCard(Card card) {
        card.play(); // Play the card
        if (card instanceof Pokemon) {
            setActivePokemon((Pokemon) card); // Set the active Pokémon
        }
        this.hand.remove(card); // Remove the card from the player's hand
    }

    /**
     * @param opponent player to attack with the player's active Pokémon against
     *                 their active Pokémon
     * @return
     */
    public boolean attack(Player opponent) {

        Pokemon opponentActivePokemon = opponent.getActivePokemon(); // Get the opponent's active Pokémon
        Pokemon playerActivePokemon = this.getActivePokemon(); // Get the player's active Pokémon

        if (opponentActivePokemon != null && playerActivePokemon != null) {
            System.out.println("Choose an attack:");
            System.out.println("1. Primary Attack" + " " + getActivePokemon().getPrimaryAttackName() + " "
                    + getActivePokemon().getPrimaryAttackDamage() + " " + "damage");
            System.out.println("2. Secondary Attack" + " " + getActivePokemon().getSecondaryAttackName() + " "
                    + getActivePokemon().getSecondaryAttackDamage() + " " + "damage");

            int choice = scanner.nextInt(); // Get the player's choice
            switch (choice) {
                case 1 -> {
                    this.activePokemon.primaryAttack(opponent.getActivePokemon()); // Attack the opponent's active
                                                                                   // Pokémon
                    System.out.println(opponent.getActivePokemon().getName() + "'s remaining HP: "
                            + opponent.getActivePokemon().getHp());
                }
                case 2 -> {
                    this.activePokemon.secondaryAttack(opponent.getActivePokemon());
                    System.out.println(opponent.getActivePokemon().getName() + "'s remaining HP:/n "
                            + opponent.getActivePokemon().getHp());
                }
                default -> System.out.println("Invalid choice.");
            }

            if (opponent.getActivePokemon().isFainted()) {
                System.out.println(opponent.getActivePokemon().getName() + " has fainted!");
                opponent.benchActivePokemon();
                // claimPrizeCard();
                return true;
            }
        } else {
            System.out.println("No active Pokémon available to attack!");
        }
        return false;
    }

    public void attachEnergy(Energy energyCard) // Attach an energy card to the active Pokémon
    {
        if (this.activePokemon != null) // If there is an active Pokémon
        {
            this.activePokemon.attachEnergy(energyCard); // Attach the energy card to the active Pokémon
            System.out.println(name + " attached " + energyCard.getName() + " to " + this.activePokemon.getName());
        } else {
            System.out.println("No active Pokémon to attach energy to!");
        }
    }

    public void benchActivePokemon() // Bench the active Pokémon
    {
        if (this.activePokemon != null) // If there is an active Pokémon
        {
            hand.add(this.activePokemon); // Add the active Pokémon to the player's hand
            System.out.println(activePokemon.getName() + " has been eliminated from the game\n");
            this.activePokemon = null; // Set the active Pokémon to null
        }
    }

    public void setActivePokemon(Pokemon newActive) // Set the active Pokémon
    {
        if (this.activePokemon != null) // If there is an active Pokémon
        {
            this.hand.add(this.activePokemon); // Add the active Pokémon to the player's hand
        }
        this.activePokemon = newActive; // Set the new active Pokémon
        this.hand.remove(newActive); // Remove the new active Pokémon from the hand
        System.out.println(newActive.getName() + " is now the active Pokémon");
    }

    public boolean hasBenchedPokemon() // Check if the player has benched Pokémon
    {
        return !bench.isEmpty(); // Return true if the bench is not empty
    }

    public void addToBench(Pokemon pokemon) // Add a Pokémon to the bench
    {
        bench.add(pokemon); // Add the Pokémon to the bench
    }

    public void switchActiveWithBenched() // Switch the active Pokémon with a benched Pokémon
    {
        if (!hasBenchedPokemon())// If there are no benched Pokémon
        {
            System.out.println("No benched Pokémon to switch with.");
            return;
        }

        // Swap the active Pokémon with a random one on the bench
        Pokemon benchedPokemon = bench.remove(0); // Remove the first benched Pokémon
        if (activePokemon != null) // If there is an active Pokémon
        {
            bench.add(activePokemon); // Add the active Pokémon to the bench
        }
        activePokemon = benchedPokemon; // Set the active Pokémon to the benched Pokémon

        System.out.println("Switched active Pokémon with " + activePokemon.getName());
    }

    /**
     * Discard the player's hand and draw a specified number of cards from the deck.
     * 
     * @param numCards
     */
    public void discardHandAndDraw(int numCards) {
        // Move all cards in hand to the discard pile
        discardPile.addAll(hand); // Add all cards in hand to the discard pile
        hand.clear(); // Clear the hand

        // Draw the specified number of cards from the deck
        for (int i = 0; i < numCards && !deck.isEmpty(); i++) {
            Card drawnCard = deck.remove(0); // Remove the first card from the deck
            hand.add(drawnCard); // Add the card to the hand
            System.out.println(name + " drew a card: " + drawnCard.getName());
        }

        System.out.println(name + " discarded their hand and drew " + numCards + " new cards.");
    }

    public Pokemon getActivePokemon() // Get the active Pokémon
    {
        return this.activePokemon; // Return the active Pokémon
    }

    public List<Card> getHand() // Get the player's hand
    {
        return this.hand; // Return the hand
    }

    public String getName() // Get the player's name
    {
        return name; // Return the name
    }

    public boolean hasWon() // Check if the player has won
    {
        return prizePile.isEmpty(); // Return true if the prize pile is empty
    }

    /**
     * @param newActivePokemon
     */
    public void retreatActivePokemon(Pokemon newActivePokemon) {

        if (this.activePokemon != null) {

            this.bench.add(this.activePokemon); // Move current active Pokémon to the bench

        }

        this.activePokemon = newActivePokemon; // Set the new active Pokémon

        this.hand.remove(newActivePokemon); // Remove the new active Pokémon from the hand

    }
}
